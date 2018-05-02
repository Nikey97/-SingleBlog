package cn.supspider.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.supspider.Utils.UtilMethods;
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
	public String SignUp() throws IOException {
		userinfo.setActive(0);
		userinfo.setSignInTime(utilMethods.getNowSystemTime());
		userinfo.setSignUpTime(utilMethods.getNowSystemTime());
		hibernateTemplate.save(userinfo);
		//�����ʼ�
		return SUCCESS;
	}
	//�û���¼����
	@SuppressWarnings("unchecked")
	public String SignIn() throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		@SuppressWarnings("unused")
		List<userinfo> list=(List<cn.supspider.bean.userinfo>) hibernateTemplate.find("from userinfo where Email=? and PassWord=?", userinfo.getEmail(),userinfo.getPassWord());
		if(!list.isEmpty()) {
			//��¼�ɹ�!
			out.print(1);
			context.getSession().put("userId", userinfo.getId());//��session�б����¼��¼
			System.out.println("��"+"++"+userinfo.getEmail()+"+++"+userinfo.getPassWord());
		}else {
			//������������
			out.print(0);
			System.out.println("��"+"++"+userinfo.getEmail()+"+++"+userinfo.getPassWord());
		}
		return NONE;
	}
	
	
	
	
	
}
