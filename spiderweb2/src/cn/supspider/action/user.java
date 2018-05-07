package cn.supspider.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import cn.supspider.Utils.UtilMethods;
import cn.supspider.bean.ResourceAll;
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
	ActionContext context=ActionContext.getContext();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	
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
	@SuppressWarnings("unchecked")
	public String SignIn() throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		System.out.println("����:"+userinfo.getEmail()+"|"+"����"+userinfo.getPassWord());
		List<userinfo> list = (List<cn.supspider.bean.userinfo>) hibernateTemplate.find("from userinfo where Email=? and PassWord=?", userinfo.getEmail(),userinfo.getPassWord());
		if(!list.isEmpty()) {
			out.print(1);
			context.getSession().put("user_name", userinfo.getUserName());//�����û���id
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
	public String getSearchName() {
		return SearchName;
	}
	public void setSearchName(String searchName) {
		SearchName = searchName;
	}
	public String QueryResultInfo() {
		//��ȡǰ̨����,�����ݿ�ģ����ѯ.
		/*	1.�������������ݿ�������ϳ�һ��
		 * 	2.����ҳ�����²�ѯ
		 * 	3.������ҳ����������
		 * */
		ValueStack stack=ActionContext.getContext().getValueStack();
		@SuppressWarnings("unchecked")//ģ����ѯ
		List<ResourceAll> list = (List<ResourceAll>) hibernateTemplate.find("from ResourceAll where R_name like '%"+SearchName+"%'");
		SearchName=getSearchName();//����ֵ����ֵջ
		stack.set("Reslist", list);//����ֵջ
		return "resultall";
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
		if(list.isEmpty()) {
			System.out.println("�ǿյ�");
		}
		for (ResourceAll res : list) {
			resourceAll.setR_name(res.getR_name());
			resourceAll.setR_size(res.getR_size());
			resourceAll.setR_type(res.getR_type());
			resourceAll.setR_from(res.getR_from());
			resourceAll.setR_intotime(res.getR_intotime());
		}
		return "OneResultAll";
	}
	
	
	
}
