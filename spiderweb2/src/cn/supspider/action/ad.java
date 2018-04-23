package cn.supspider.action;


import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletConfigAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.supspider.bean.userbean;

@Transactional	
public class ad extends ActionSupport implements ModelDriven<userbean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ��action���ڹ���Ա��¼:
	 * 
	 * 
	 * */
	private userbean bean=new userbean();
	public userbean getBean() {
		return bean;
	}
	public void setBean(userbean bean) {
		this.bean = bean;
	}
	
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	ActionContext context=ActionContext.getContext();
	
	public String execute() throws Exception {
		//����Ա��¼��֤
		@SuppressWarnings("unchecked")
		List<userbean> list=(List<userbean>) hibernateTemplate.find("FROM userbean WHERE name=? AND password=?", bean.getName(),bean.getPassword());
		if(list.isEmpty()) {
			return "failed";
		}else {
			//���û�������session,�������ж��Ƿ��¼.
			context.getSession().put("username", bean.getName());
			return SUCCESS;
		}
	}
	
	//ע����¼����
	public String exit() {
		return "exit";
	}
	
	public userbean getModel() {
		return bean;
	}
}
