package cn.supspider.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Id;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletConfigAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.supspider.Utils.ToJsonType;
import cn.supspider.Utils.UtilMethods;
import cn.supspider.bean.ad_allWebinfo;
import cn.supspider.bean.advs;
import cn.supspider.bean.userbean;
import cn.supspider.bean.userinfo;
import net.sf.json.JSONArray;

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
	//ע��javabean
	private userbean bean;
	public void setBean(userbean bean) {
		this.bean = bean;
	}
	//ע��hibernateģ��
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	ActionContext context=ActionContext.getContext();
	
	//ע��Json������
	private ToJsonType ToJson;
	public void setToJson(ToJsonType toJson) {
		ToJson = toJson;
	}
	//save��վ��Ϣ
	private ad_allWebinfo ad_allWebinfo;
	public void setAd_allWebinfo(ad_allWebinfo ad_allWebinfo) {
		this.ad_allWebinfo = ad_allWebinfo;
	}
	//��ȡresponse����
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request=ServletActionContext.getRequest();
	
	//ע����bean
	private advs advs;
	public void setAdvs(advs advs) {
		this.advs = advs;
	}
	//ע���ȡ��ǰϵͳʱ��ķ���
	private UtilMethods utilMethods;
	public void setUtilMethods(UtilMethods utilMethods) {
		this.utilMethods = utilMethods;
	}
	//ע��userinfo���û�����
	private userinfo userinfo;
	public void setUserinfo(userinfo userinfo) {
		this.userinfo = userinfo;
	}

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
	
	public userbean getModel() {
		return bean;
	}
	
	//ע����¼����
	public String exit() {
		return "exit";
	}
	
	//���չ���ҳ�����վ��Ϣ�������������ݷ���
	public String QueryWebInfo() throws IOException {
		@SuppressWarnings({ "unchecked", "unused" })
		List<ad_allWebinfo> list_webinfo=(List<ad_allWebinfo>) hibernateTemplate.find("FROM ad_allWebinfo WHERE number=?", 1);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		if(!list_webinfo.isEmpty()) {
			JSONArray json=ToJson.List2Json(list_webinfo);
			String Sjson=json.toString();
			out.print(Sjson);//��������
			System.out.println("����ҳ�涯��"+Sjson);
		}
		return NONE;
	}
	
	//�޸Ĵ洢��վ��Ϣ
	private String web_Name;
	private String web_Keyword;
	private String web_Introduce;
	private int userid;
	public String getWeb_Name() {
		return web_Name;
	}
	public void setWeb_Name(String web_Name) {
		this.web_Name = web_Name;
	}
	public String getWeb_Keyword() {
		return web_Keyword;
	}
	public void setWeb_Keyword(String web_Keyword) {
		this.web_Keyword = web_Keyword;
	}
	public String getWeb_Introduce() {
		return web_Introduce;
	}
	public void setWeb_Introduce(String web_Introduce) {
		this.web_Introduce = web_Introduce;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	//������Ӧ
	public String SaveWebInfo() throws IOException {
		
		if(getWeb_Name().equals("")&&getWeb_Keyword().equals("")&&getWeb_Introduce().equals("")) {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			out.print("send_null");//��������
		}else {
			ad_allWebinfo.setNumber(1);
			ad_allWebinfo.setWeb_Name(getWeb_Name());
			ad_allWebinfo.setWeb_Keyword(getWeb_Keyword());
			ad_allWebinfo.setWeb_Introduce(getWeb_Introduce());
			ad_allWebinfo.setWeb_Open(0);
			hibernateTemplate.update(ad_allWebinfo);//�������ݿ�
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			out.print(1);//��������
		}
		return NONE;
	}
	
	//��ѯ�û�
	private String search_input;
	public String getSearch_input() {
		return search_input;
	}
	public void setSearch_input(String search_input) {
		this.search_input = search_input;
	}
	public String QueryUserinfo() throws IOException {
		@SuppressWarnings({ "unchecked", "unused" })
		List<userinfo> listName=(List<userinfo>) hibernateTemplate.find("FROM userinfo WHERE UserName=?", getSearch_input());
		@SuppressWarnings({ "unchecked", "unused" })
		List<userinfo> listEmail=(List<userinfo>) hibernateTemplate.find("FROM userinfo WHERE Email=?", getSearch_input());
		if(!listName.isEmpty()) {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			JSONArray json=ToJson.List2Json(listName);
			System.out.println(json);
			out.println(json);
		}else if(!listEmail.isEmpty()) {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			JSONArray json=ToJson.List2Json(listName);
			System.out.println(json);
			out.println(json);
		}else {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=response.getWriter();
			System.out.println(0);
			out.println(0);
		}
		return NONE;
	}
	//��ѯ�����û�(��ԭjsp��������)
	
	//��ѯ������
	private String adv;
	public String getAdv() {
		return adv;
	}
	public void setAdv(String adv) {
		this.adv = adv;
	}
	public String QueryAdvsInfo() throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		@SuppressWarnings("unchecked")
		List<advs> adv_code=(List<cn.supspider.bean.advs>) hibernateTemplate.find("FROM advs WHERE advs_name=?", getAdv());
		if(!adv_code.isEmpty()) {
			out.println(ToJson.List2Json(adv_code));//���ز�ѯֵ
			System.out.println(ToJson.List2Json(adv_code));
		}else {
			out.println(0);//��ѯδ��
		}
		return NONE;
	}
	
	
	//�޸Ĺ�����
	private String Advs_code;
	private String Advs_name;
	private int Advs_id;
	public String getAdvs_code() {
		return Advs_code;
	}
	public void setAdvs_code(String advs_code) {
		Advs_code = advs_code;
	}
	public String getAdvs_name() {
		return Advs_name;
	}
	public void setAdvs_name(String advs_name) {
		Advs_name = advs_name;
	}
	public int getAdvs_id() {
		return Advs_id;
	}
	public void setAdvs_id(int advs_id) {
		Advs_id = advs_id;
	}

	public String UpdateAdvsCode() throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		advs.setId(getAdvs_id());
		advs.setAdvs_name(getAdvs_name());
		advs.setAdvs_context(getAdvs_code());
		advs.setAdvs_altertime(utilMethods.getNowSystemTime());
		hibernateTemplate.saveOrUpdate(advs);
		out.println(1);
		return NONE;
	}
	//ɾ��ָ���Ĺ��
	public String DeleteAdvsCode() throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		advs.setId(getAdvs_id());
		advs.setAdvs_name(getAdvs_name());
		advs.setAdvs_context(null);
		advs.setAdvs_altertime(utilMethods.getNowSystemTime());
		hibernateTemplate.update(advs);
		out.println(1);
		return NONE;
	}
	//�û�����ϵͳɾ���û�
	public String DeleteUserInfo() throws IOException {
		userinfo.setId(getUserid());
		hibernateTemplate.delete(userinfo);
		return "deletesu";
	}
	
}
