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
	//ע���û�bean
	private userinfo userinfo;
	public void setUserinfo(userinfo userinfo) {
		this.userinfo = userinfo;
	}
	//ע�빤��
	private UtilMethods utilMethods;
	public void setUtilMethods(UtilMethods utilMethods) {
		this.utilMethods = utilMethods;
	}
	//ע��hibernateģ��
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	//��ȡresponse����
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	
	//��ȡ��ҳ����
	private QueryPagingPage query;
	public void setQuery(QueryPagingPage query) {
		this.query = query;
	}
	
	//��д�ӿ��еķ���
	@Override
	public userinfo getModel() {
		// TODO Auto-generated method stub
		return userinfo;
	}
	//�û�ע�����
	private String ToEmailaddress;
	public String getToEmailaddress() {
		return ToEmailaddress;
	}
	public void setToEmailaddress(String toEmailaddress) {
		ToEmailaddress = toEmailaddress;
	}
	//ע����Դ��
	private ResourceAll resourceAll;
	public ResourceAll getResourceAll() {
		return resourceAll;
	}
	public void setResourceAll(ResourceAll resourceAll) {
		this.resourceAll = resourceAll;
	}
	//�û�������
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
			this.addActionError("��������ע��,�뻻һ��ע��"); 
			return INPUT;
		}else {
			userinfo.setActive(0);
			userinfo.setSignInTime(utilMethods.getNowSystemTime());
			userinfo.setSignUpTime(utilMethods.getNowSystemTime());
			userinfo.setCode(utilMethods.CreateSuperCode());//д���ʼ���֤��
			hibernateTemplate.save(userinfo);//�־û������ݿ�
			//�����ʼ�
			utilMethods.Send(userinfo.getEmail(), userinfo.getCode(),userinfo.getId());
			ToEmailaddress=utilMethods.FindEmailContext(userinfo.getEmail());
			System.out.println(ToEmailaddress);
		}
		return SUCCESS;
	}
	//�û���¼����
	//Ϊ��ֵջ�д����û���Ϣ,����ǰ̨��֤��¼�Ȳ���
	@SuppressWarnings("unchecked")
	private String username;
	public String getUsername() {
		return username;
	}
	public String SignIn() throws IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();
		System.out.println("����:"+userinfo.getEmail()+"|"+"����"+userinfo.getPassWord());
		List<userinfo> list = (List<cn.supspider.bean.userinfo>) hibernateTemplate.find("from userinfo where Email=? and PassWord=?", userinfo.getEmail(),userinfo.getPassWord());
		for (userinfo user : list) {
			username=user.getUserName();
		}//��list������ȡ���û���
		if(!list.isEmpty()) {
			out.print(1);
			session.setAttribute("user_name", username);//�����û�����
		}else {
			out.print(0);
		}
		return NONE;
	}
	//����ע����ӦУ��
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
					//����ɹ�
					userinfo.setId(userinfo.getId());
					userinfo.setActive(1);
					hibernateTemplate.saveOrUpdate(userinfo);
					return "su";
				}else {
					//����ʧ��
					this.addActionError("�ܱ�Ǹ,��û����ָ��ʱ���ڼ����˺�.������ע��!");
					return INPUT;
				}
			}
		}else {
			this.addActionError("�����˺��쳣:1.���˺Ų����� 2.���˺��Ѿ�����");
			return INPUT;
		}
		return NONE;
	}
	//����ģ������
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
		//��ȡǰ̨����,�����ݿ�ģ����ѯ.
		/*	1.�������������ݿ�������ϳ�һ��
		 * 	2.����ҳ�����²�ѯ
		 * 	3.������ҳ����������
		 * */
		//��ѯ�Ƿ��¼,�������û���
		HttpSession session = request.getSession();
		if(session.getAttribute("user_name")!=null) {
			username=(String) session.getAttribute("user_name");
		}
		ValueStack stack=ActionContext.getContext().getValueStack();//ģ����ѯ
		SearchName=getSearchName();//����ֵ����ֵջ
		int AllCount = hibernateTemplate.find("from ResourceAll where R_name like '%"+SearchName+"%'").size();//��ѯ���еĸ���
		if(AllCount/8==0) {
			PageCount=1;
			Last=0;
			Next=0;
		}else {
			PageCount=AllCount/8;
			//��ֹ��һ��
			Last=0;
			Next=1;
		}
		stack.set("NowPage", 1);
		List<ResourceAll> list = query.getListForpage("from ResourceAll where R_name like '%"+SearchName+"%'", 0, 8);//��ҳ��ѯ����
		stack.set("Reslist", list);//����ֵջ
		return "resultall";
	} 
	//��ҳģ��������ҳ����
		/*����:
		 * 		1.�½�bean,���е����Ե�setget����.
		 * 		2.������з��ϵ���Ϣ,ÿ��ҳ��̶�������
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
		ValueStack stack=ActionContext.getContext().getValueStack();//ģ����ѯ
		getSearchName=new String(SearchName.getBytes("ISO-8859-1"),"UTF-8");
		SearchName=getSearchName;//��������ֵջ
		stack.set("NowPage", NowPage);//��ǰҳ������ֵջ   
		int AllCount = hibernateTemplate.find("from ResourceAll where R_name like '%"+SearchName+"%'").size();//��ѯ���еĸ���
		if(AllCount/8==0) {
			PageCount=1;
			//����ֻ��һ��,��ֹ��һҳ��һҳ
			Last=0;
			Next=0;
		}else {
			PageCount=AllCount/8;
			if(NowPage==1) {
				//Ϊ��һ��
				Last=0;
				Next=1;
			}else if(PageCount==NowPage) {
				//Ϊ���һ��
				Last=1;
				Next=0;
			}else {
				//��Ϊ��һ��Ҳ��Ϊ�ڶ���
				Last=1;
				Next=1;
			}
		}
		List<ResourceAll> list = query.getListForpage("from ResourceAll where R_name like '%"+SearchName+"%'", 8*(NowPage-1), 8);
		stack.set("Reslist", list);//����ֵջ
		return "resultalls";
	}
	
	//���ز�ѯҳ���������Ϣ
	private int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String QueryResultAllInfo() {
		@SuppressWarnings({ "unchecked", "unused" })
		List<ResourceAll> list = (List<ResourceAll>) hibernateTemplate.find("from ResourceAll where number=?", number);//��ѯ
		int Count=0;//��ѯ����
		for (ResourceAll res : list) {
			resourceAll.setNumber(number);
			resourceAll.setR_name(res.getR_name());
			resourceAll.setR_size(res.getR_size());
			resourceAll.setR_doctype(res.getR_doctype());
			resourceAll.setR_type(res.getR_type());
			resourceAll.setR_from(res.getR_from());
			resourceAll.setR_intotime(res.getR_intotime());
			Count=(res.getR_count())+1;
			resourceAll.setR_count(Count);//�����ݿ��һ����
			resourceAll.setR_link(res.getR_link());
		}
		hibernateTemplate.clear();
		hibernateTemplate.update(resourceAll);
		//��ѯ�Ƿ��¼,�������û���
		HttpSession session = request.getSession();
		if(session.getAttribute("user_name")!=null) {
			username=(String) session.getAttribute("user_name");
		}
		return "OneResultAll";
	}
	
	//�û�ע���˻�����
	public String UserCancel() {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_name")!=null) {
			session.removeAttribute("user_name");
		}
		return "index";
	}
	
	//�û��ύ����������Ϣ�־û�
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
		/*����:
		 * 		1.��ȡǰ̨���û���,��������,�������Ϣ
		 * 		2.���ݻ�ȡ���û���,��ѯ��д�����ݿ�
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
			long s=(dateNow.getTime()-dateSub.getTime())/1000;//�����ύ��ʱ����û�й�һ��
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
				hibernateTemplate.save(userFeedback);//���浽���ݿ�
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
			hibernateTemplate.save(userFeedback);//���浽���ݿ�
			out.print(1);
		}
		return NONE;
	}
	
	//ת����������Դ
	private int code;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@SuppressWarnings({ "unchecked", "unused" })
	public String ResultLinkBack() {
		System.out.println("��Դ���ӻ�������!"+"---number:"+number);
		ValueStack stack=ActionContext.getContext().getValueStack();
		//��ѯ�Ƿ��¼,�������û���
		HttpSession session = request.getSession();
		if(session.getAttribute("user_name")!=null) {
			username=(String) session.getAttribute("user_name");
			List<userinfo> list = (List<userinfo>) hibernateTemplate.find("from userinfo where UserName=? and active=0", username);
			if(!list.isEmpty()) {
			   stack.set("code", 2);//û����
			   System.out.println(2);
			   return "ERBack";
			}else {
			   return "RLBack";
			}
		}else {
			System.out.println(1);
			stack.set("code", 1);//û��¼
			return "ERBack";
		}
	}
	
	
}
