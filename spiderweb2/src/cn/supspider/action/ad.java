package cn.supspider.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.supspider.Utils.HibernateUtil;
import cn.supspider.bean.userbean;

public class ad extends ActionSupport implements ModelDriven<userbean>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ��action���ڹ���Ա��¼:
	 * 
	 * 		
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
	
	SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	Session session=sessionFactory.openSession();
	Transaction t=session.beginTransaction();

	public String execute() throws Exception {
		
		//��¼У��ҵ���߼�
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession pagesession=request.getSession();
		try {
			Query query=session.createQuery("from userbean where name=? and password=?");
			query.setParameter(0, bean.getName());
			query.setParameter(1, bean.getPassword());
			List<userbean> list=query.list();
			if(list.size()>0) {
				pagesession.setAttribute("username", bean.getName());//��ҳ��session������д洢�û���Ϣ
				return "success";
			}
			t.commit();//�ύ����
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			t.rollback();//����ع�
		}finally {
			session.close();
			sessionFactory.close();
		}
		return "failed";
	}
	
	public String exit() {
		//ע����¼����
		System.out.println("exitִ��......");
		return "exit";
	}
	
	@Override
	public userbean getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
}
