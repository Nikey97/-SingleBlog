package cn.supspider.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import cn.supspider.Utils.QueryPagingPage;
import cn.supspider.Utils.UtilMethods;
import cn.supspider.bean.ResourceAll;
import cn.supspider.bean.userFeedback;
import cn.supspider.bean.userinfo;

	
@SuppressWarnings("serial")
@Transactional
public class user extends ActionSupport implements ModelDriven<userinfo>{
	//注入用户bean
	private userinfo userinfo;
	public void setUserinfo(userinfo userinfo) {
		this.userinfo = userinfo;
	}
	//注入工具
	private UtilMethods utilMethods;
	public void setUtilMethods(UtilMethods utilMethods) {
		this.utilMethods = utilMethods;
	}
	//注入hibernate模板
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	//获取response对象
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	
	//获取分页的类
	private QueryPagingPage query;
	public void setQuery(QueryPagingPage query) {
		this.query = query;
	}
	
	//重写接口中的方法
	@Override
	public userinfo getModel() {
		// TODO Auto-generated method stub
		return userinfo;
	}
	//用户注册操作
	private String ToEmailaddress;
	public String getToEmailaddress() {
		return ToEmailaddress;
	}
	public void setToEmailaddress(String toEmailaddress) {
		ToEmailaddress = toEmailaddress;
	}
	//注入资源库
	private ResourceAll resourceAll;
	public ResourceAll getResourceAll() {
		return resourceAll;
	}
	public void setResourceAll(ResourceAll resourceAll) {
		this.resourceAll = resourceAll;
	}
	//用户反馈表
	private userFeedback userFeedback;
	public void setUserFeedback(userFeedback userFeedback) {
		this.userFeedback = userFeedback;
	}
	public userFeedback getUserFeedback() {
		return userFeedback;
	}
	
	//
	
	public String SignUp() throws Exception {
		@SuppressWarnings("unchecked")
		List<userinfo> list = (List<cn.supspider.bean.userinfo>) hibernateTemplate.find("from userinfo where Email=?", userinfo.getEmail());
		if(!list.isEmpty()) {
			this.addActionError("该邮箱已注册,请换一个注册"); 
			return INPUT;
		}else {
			userinfo.setActive(0);
			userinfo.setSignInTime(utilMethods.getNowSystemTime());
			userinfo.setSignUpTime(utilMethods.getNowSystemTime());
			userinfo.setCode(utilMethods.CreateSuperCode());//写入邮件验证码
			hibernateTemplate.save(userinfo);//持久化到数据库
			//发送邮件
			utilMethods.Send(userinfo.getEmail(), userinfo.getCode(),userinfo.getId());
			ToEmailaddress=utilMethods.FindEmailContext(userinfo.getEmail());
			System.out.println(ToEmailaddress);
		}
		return SUCCESS;
	}
	//用户登录操作
	//为向值栈中存入用户信息,用于前台验证登录等操作
	@SuppressWarnings("unchecked")
	private String username;
	public String getUsername() {
		return username;
	}
	public String SignIn() throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();
		System.out.println("邮箱:"+userinfo.getEmail()+"|"+"密码"+userinfo.getPassWord());
		List<userinfo> list = (List<cn.supspider.bean.userinfo>) hibernateTemplate.find("from userinfo where Email=? and PassWord=?", userinfo.getEmail(),userinfo.getPassWord());
		for (userinfo user : list) {
			username=user.getUserName();
		}//从list集合中取出用户名
		if(!list.isEmpty()) {
			out.print(1);
			session.setAttribute("user_name", username);//存入用户的名
		}else {
			out.print(0);
		}
		return NONE;
	}
	//邮箱注册响应校验
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SuppressWarnings("unchecked")
	public String SignUpCheckout() throws ParseException {
		@SuppressWarnings("unused")
		List<userinfo> list = (List<cn.supspider.bean.userinfo>) hibernateTemplate.find("from userinfo where id=? and code=? and active=?", userinfo.getId(),userinfo.getCode(),0);
		if(!list.isEmpty()) {
			for (userinfo userinfo : list) {
				String NowDate=utilMethods.getNowSystemTime();
				String SignUpDate=userinfo.getSignUpTime();
				Date DateNow=df.parse(NowDate);
				Date DateSignUp=df.parse(SignUpDate);
				long s=(DateNow.getTime()-DateSignUp.getTime())/1000;
				int m=(int) (s/60);
				if(m<=30) {
					//激活成功
					userinfo.setId(userinfo.getId());
					userinfo.setActive(1);
					hibernateTemplate.saveOrUpdate(userinfo);
					return "su";
				}else {
					//激活失败
					this.addActionError("很抱歉,您没有在指定时间内激活账号.请重新注册!");
					return INPUT;
				}
			}
		}else {
			this.addActionError("激活账号异常:1.该账号不存在 2.该账号已经激活");
			return INPUT;
		}
		return NONE;
	}
	//词条模糊搜索
	private String SearchName;
	private int PageCount;
	
	public String getSearchName() {
		return SearchName;
	}
	public void setSearchName(String searchName) {
		SearchName = searchName;
	}
	public int getPageCount() {
		return PageCount;
	}
	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}

	@SuppressWarnings("unchecked")
	public String QueryResultInfo() {
		//获取前台数据,对数据库模糊查询.
		/*	1.将三个分类数据库进行整合成一个
		 * 	2.改首页的最新查询
		 * 	3.完善首页的搜索功能
		 * */
		//查询是否登录,并返回用户名
		HttpSession session = request.getSession();
		if(session.getAttribute("user_name")!=null) {
			username=(String) session.getAttribute("user_name");
		}
		ValueStack stack=ActionContext.getContext().getValueStack();//模糊查询
		SearchName=getSearchName();//搜索值存入值栈
		int AllCount = hibernateTemplate.find("from ResourceAll where R_name like '%"+SearchName+"%'").size();//查询所有的个数
		if(AllCount/8==0) {
			PageCount=1;
			Last=0;
			Next=0;
		}else {
			PageCount=AllCount/8;
			//禁止上一个
			Last=0;
			Next=1;
		}
		stack.set("NowPage", 1);
		List<ResourceAll> list = query.getListForpage("from ResourceAll where R_name like '%"+SearchName+"%'", 0, 8);//分页查询条数
		stack.set("Reslist", list);//存入值栈
		return "resultall";
	} 
	//首页模糊搜索分页请求
		/*步奏:
		 * 		1.新建bean,所有的属性的setget方法.
		 * 		2.查出所有符合的信息,每个页面固定的列数
		 * 		3.
		 * */
	private String getSearchName;
	private int NowPage;
	private int Next;
	private int Last;
	public int getNowPage() {
		return NowPage;
	}
	public void setNowPage(int nowPage) {
		NowPage = nowPage;
	}
	public String getGetSearchName() {
		return getSearchName;
	}
	public void setGetSearchName(String getSearchName) {
		this.getSearchName = getSearchName;
	}
	public int getNext() {
		return Next;
	}
	public void setNext(int next) {
		Next = next;
	}
	public int getLast() {
		return Last;
	}
	public void setLast(int last) {
		Last = last;
	}
	@SuppressWarnings({ "unchecked", "unused" })
	public String QueryPaingRes() throws UnsupportedEncodingException {
		System.out.println("NowPages:"+NowPage);
		ValueStack stack=ActionContext.getContext().getValueStack();//模糊查询
		getSearchName=new String(SearchName.getBytes("ISO-8859-1"),"UTF-8");
		SearchName=getSearchName;//继续放入值栈
		stack.set("NowPage", NowPage);//当前页数存入值栈   
		int AllCount = hibernateTemplate.find("from ResourceAll where R_name like '%"+SearchName+"%'").size();//查询所有的个数
		if(AllCount/8==0) {
			PageCount=1;
			//搜素只有一个,禁止上一页下一页
			Last=0;
			Next=0;
		}else {
			PageCount=AllCount/8;
			if(NowPage==1) {
				//为第一个
				Last=0;
				Next=1;
			}else if(PageCount==NowPage) {
				//为最后一个
				Last=1;
				Next=0;
			}else {
				//不为第一个也不为第二个
				Last=1;
				Next=1;
			}
		}
		List<ResourceAll> list = query.getListForpage("from ResourceAll where R_name like '%"+SearchName+"%'", 8*(NowPage-1), 8);
		stack.set("Reslist", list);//存入值栈
		return "resultalls";
	}
	
	//返回查询页面的所有信息
	private int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String QueryResultAllInfo() {
		@SuppressWarnings({ "unchecked", "unused" })
		List<ResourceAll> list = (List<ResourceAll>) hibernateTemplate.find("from ResourceAll where number=?", number);//查询
		int Count=0;//查询次数
		for (ResourceAll res : list) {
			resourceAll.setNumber(number);
			resourceAll.setR_name(res.getR_name());
			resourceAll.setR_size(res.getR_size());
			resourceAll.setR_doctype(res.getR_doctype());
			resourceAll.setR_type(res.getR_type());
			resourceAll.setR_from(res.getR_from());
			resourceAll.setR_intotime(res.getR_intotime());
			Count=(res.getR_count())+1;
			resourceAll.setR_count(Count);//向数据库加一操作
			resourceAll.setR_link(res.getR_link());
		}
		hibernateTemplate.clear();
		hibernateTemplate.update(resourceAll);
		//查询是否登录,并返回用户名
		HttpSession session = request.getSession();
		if(session.getAttribute("user_name")!=null) {
			username=(String) session.getAttribute("user_name");
		}
		return "OneResultAll";
	}
	
	//用户注销账户操作
	public String UserCancel() {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_name")!=null) {
			session.removeAttribute("user_name");
		}
		return "index";
	}
	
	//用户提交反馈反馈信息持久化
	private String uName;
	private String fTitle;
	private String fContext;
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfContext() {
		return fContext;
	}
	public void setfContext(String fContext) {
		this.fContext = fContext;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public String UserFeedback() throws IOException, ParseException {
		/*步奏:
		 * 		1.获取前台的用户名,反馈标题,意见或信息
		 * 		2.根据获取的用户名,查询并写入数据库
		 * */
		PrintWriter out=response.getWriter();
		List<userFeedback> listTime = (List<cn.supspider.bean.userFeedback>) hibernateTemplate.find("from userFeedback where look=0 and userName=?",uName);
		if(!listTime.isEmpty()) {
			String SubTime="";
			for (userFeedback Feedback : listTime) {
				SubTime=Feedback.getSubmitTime();
			}
			Date dateSub = df.parse(SubTime);
			Date dateNow = df.parse(utilMethods.getNowSystemTime());
			long s=(dateNow.getTime()-dateSub.getTime())/1000;//计算提交的时间有没有过一天
			int day=(int) (s/(24*3600));
			if(day>1) {
				List<userinfo> list = (List<cn.supspider.bean.userinfo>) hibernateTemplate.find("from userinfo where UserName=?", uName);
				for (userinfo user : list) {
					userinfo.setId(user.getId());
				}
				userFeedback.setUserinfo(userinfo);
				userFeedback.setUserName(getuName());
				userFeedback.setTitle(getfTitle());
				userFeedback.setContext(getfContext());
				userFeedback.setSubmitTime(utilMethods.getNowSystemTime());
				userFeedback.setLook(0);
				hibernateTemplate.save(userFeedback);//储存到数据库
				out.print(1);
			}else{
				out.print(2);
			}
		}else {
			List<userinfo> list = (List<cn.supspider.bean.userinfo>) hibernateTemplate.find("from userinfo where UserName=?", uName);
			for (userinfo user : list) {
				userinfo.setId(user.getId());
			}
			userFeedback.setUserinfo(userinfo);
			userFeedback.setUserName(getuName());
			userFeedback.setTitle(getfTitle());
			userFeedback.setContext(getfContext());
			userFeedback.setSubmitTime(utilMethods.getNowSystemTime());
			userFeedback.setLook(0);
			hibernateTemplate.save(userFeedback);//储存到数据库
			out.print(1);
		}
		return NONE;
	}
	
	//转跳到回显资源
	private int code;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@SuppressWarnings({ "unchecked", "unused" })
	public String ResultLinkBack() {
		System.out.println("资源链接回显请求!"+"---number:"+number);
		ValueStack stack=ActionContext.getContext().getValueStack();
		//查询是否登录,并返回用户名
		HttpSession session = request.getSession();
		if(session.getAttribute("user_name")!=null) {
			username=(String) session.getAttribute("user_name");
			List<userinfo> list = (List<userinfo>) hibernateTemplate.find("from userinfo where UserName=? and active=0", username);
			if(!list.isEmpty()) {
			   stack.set("code", 2);//没激活
			   System.out.println(2);
			   return "ERBack";
			}else {
			   return "RLBack";
			}
		}else {
			System.out.println(1);
			stack.set("code", 1);//没登录
			return "ERBack";
		}
	}
	
	
}
