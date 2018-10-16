package cn.kuqi.ServiceImpi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.kuqi.Service.AdminBackstageService;
import cn.kuqi.DateUtil.MyDateUtils;
import cn.kuqi.Mapper.AdminBackstageMapper;
import cn.kuqi.Pojo.Article;
import cn.kuqi.Pojo.ArticleClassfiy;
import cn.kuqi.Pojo.ArticleClassfiyExt;
import cn.kuqi.Pojo.ArticleExt;
import cn.kuqi.Pojo.Bloginfo;
import cn.kuqi.Pojo.Link;
import cn.kuqi.Pojo.LinkExt;
import cn.kuqi.Pojo.MessageInfo;
import cn.kuqi.Pojo.Users;
import cn.kuqi.Pojo.UsersExt;


@Service
public class AdminBackstageServiceImpi implements AdminBackstageService{

	@Autowired
	AdminBackstageMapper usersMapperExt;
	/*
	 * ��̨����ϵͳ --> ��̨��¼
	 * 
	 * */
	public Integer QueryUserLoginInfo(String account, String psw) {
		
		int LOGIN_STATUS_PSWERREO = 1;
		
		int LOGIN_STATUS_USERESERREO = 2;
		
		int LOGIN_STATUS_SUCCESS = 0;
		
		Users data_name = usersMapperExt.QueryUserInfoByName(account);
		
		Users data_email = usersMapperExt.QueryUserInfoByEmail(account);
		
		//ʹ�õ��˺����û���
		if (data_name != null) {
			
			if (data_name.getuPsw().equals(psw)) {
				//������ȷ
				return LOGIN_STATUS_SUCCESS;
			}else {
				//��������
				return LOGIN_STATUS_PSWERREO;
			}
			
		}
		
		//ʹ�õ��˺�������
		if (data_email != null) {
			
			if (data_email.getuPsw().equals(psw)) {
				//������ȷ
				return LOGIN_STATUS_SUCCESS;
			}else {
				//��������
				return LOGIN_STATUS_PSWERREO;
			}
			
		}
		
		return LOGIN_STATUS_USERESERREO;
	}

	/*  ��̨����-->���� ����-->����        ��ѯ���з���    */
	public ArticleClassfiyExt QueryAllArticleClassfiyService() {
		ArticleClassfiyExt articleClassfiyExt = new ArticleClassfiyExt(); 
		List<ArticleClassfiy> list= usersMapperExt.QueryAllClassfiy();
		articleClassfiyExt.setData(list);
		articleClassfiyExt.setCode(0);
		articleClassfiyExt.setMsg("");
		articleClassfiyExt.setCount(articleClassfiyExt.getData().size());
		return articleClassfiyExt;
	}

	/*  ��̨����-->���� ����-->����        ��ӷ���    */
	public MessageInfo AddClassfiyService(String ClassfiyName, String ClassfiyRemark) {
		
		MessageInfo msg = new MessageInfo(); 
		
		int i = usersMapperExt.InsertClassfiy(ClassfiyName, ClassfiyRemark);
		
		if (i == 1) {
			msg.setCode(i);
			msg.setMsg("��ӳɹ�");
			return msg;
		}else {
			msg.setCode(i);
			msg.setMsg("���ʧ�ܣ�����ϵ����Ա��");
			return msg;
		}
	}
	
	
	/*  ��̨����-->���� ����-->����        �޸Ĳ�ѯ����    */
	public ArticleClassfiy SelectArticleClassfiyService(Integer Number) {
		
		ArticleClassfiy articleClassfiy = usersMapperExt.SelectArtcleClassfiyByNumber(Number);
		//�ݴ��ж�
		return articleClassfiy;
	}
	
	
	/*  ��̨����-->���� ����-->����        �޸ķ���    */
	public MessageInfo UpdataArticleClassfiyService(Integer Number, String ClassfiyName, String ClassfiyRemark) {
		MessageInfo msg = new MessageInfo();
		
		ArticleClassfiy articleClassfiy = usersMapperExt.SelectArtcleClassfiyByNumber(Number);
		
		if (ClassfiyName.equals(articleClassfiy.getAcClassfiyname()) && ClassfiyRemark.equals(articleClassfiy.getAcRemark())) {
			msg.setCode(2);
			msg.setMsg("�ܽӷ��ʣ����Ĳ���û���޸�����");
			return msg;	
		}else {
			int i = usersMapperExt.UpdataArtcleClassfiyByNumber(Number, ClassfiyName, ClassfiyRemark);
			if (i == 1) {
				msg.setCode(0);
				msg.setMsg("�޸ĳɹ�");
			}else {
				msg.setCode(1);
				msg.setMsg("�޸�ʧ�ܣ�����ϵ����Ա");
			}
			return msg;
		}
	}
	
	/*  ��̨����-->���� ����-->����        ɾ������    */
	public MessageInfo DeleteArticleClassfiyService(Integer Number) {
		MessageInfo msg = new MessageInfo();
		
		ArticleClassfiy articleClassfiy = usersMapperExt.SelectArtcleClassfiyByNumber(Number);
		
		if (articleClassfiy != null) {
			int i = usersMapperExt.DeleteArtcleClassfiyByNumber(Number);
			if (i == 1) {
				msg.setCode(0);
				msg.setMsg("ɾ���ɹ�");
			}else {
				msg.setCode(1);
				msg.setMsg("ɾ��ʧ�ܣ�����ϵ����Ա");
			}
			return msg;
		}else {
			msg.setCode(2);
			msg.setMsg("�ܽӷ��ʣ�ɾ���ķ��಻����");
			return msg;
		}
	}
	
	/*  ��̨����-->���� ����-->����     ��ѯ��������    */
	public ArticleExt QueryAllArticleService() {
		ArticleExt articleExt = usersMapperExt.QueryAllArticle();
		articleExt.setCode(0);
		articleExt.setMsg("");
		articleExt.setCount(articleExt.getData().size());
		return articleExt;
	}
	
	/*  ��̨����-->���� ����-->����     ��ѯ��ƪ����  */
	public Article QueryArticleByNumberService(Integer Number) {
		Article article = usersMapperExt.QueryArticleByNumber(Number);
		return article;
	}
	
	
	/*  ��̨����-->���� ����-->����     ɾ����ƪ����    */
	public MessageInfo DeleteArticleByNumberService(Integer Number) {
		MessageInfo msg = new MessageInfo();
		
		Article article = usersMapperExt.QueryArticleByNumber(Number);
		
		if (article != null) {
			int i = usersMapperExt.DeleteArticleByNumber(Number);
			if (i == 1) {
				msg.setCode(0);
				msg.setMsg("ɾ���ɹ�");
			}else {
				msg.setCode(1);
				msg.setMsg("ɾ��ʧ��");
			}
			return msg;
		}else {
			msg.setCode(2);
			msg.setMsg("�ܽӷ��ʣ� ɾ�������²�����");
			return msg;
		}
	}
	
	/*  ��̨����-->���� ����-->����     �޸ĵ�ƪ����    */
	public String UpdataArticleByNumberService(Integer Number, String Content) {
		
		int i = usersMapperExt.UpdataArticleByNumber(Number, Content);//ִ�в���
		
		if (i == 1) {
			return "{\"code\":0,\"msg\":\"�޸ĳɹ�\",}";
		}else {
			return "{\"code\":1,\"msg\":\"�޸�ʧ��\",}";
		}
	}
	
	/*  ��̨����-->���� ����-->����   ʹ�÷����ѯ��ƪ����    */
	public ArticleExt QueryArticleByClassfiyService(Integer ClassfiyNumber) {
		ArticleExt articleExt = usersMapperExt.QueryArticleByClassfiy(ClassfiyNumber);
		articleExt.setCode(0);
		articleExt.setMsg("");
		articleExt.setCount(articleExt.getData().size());
		return articleExt;
	}
	
	/*  ��̨����-->���� ����-->����   ������   */
	public String AddArticleService(Article article) {
		MyDateUtils myDateUtils = new MyDateUtils();
		String time = myDateUtils.getSystemNowTime("yyyy��MM��dd�� HHʱmm��ss��");//��ȡʱ��
		
		article.setaViewcount(0);
		article.setaPraise(0);
		article.setaTrample(0);
		article.setaCollectedcount(0);
		article.setaPublishtime(time);
		int i = usersMapperExt.AddArticle(article);//���뵽���ݿ�
		
		if (i == 1) {
			return "�����ɹ�";
		}else{
			return "����ʧ��";
		}
	}
	
	/*  ��̨����-->���͹��� -->�������    ���   */
	public MessageInfo AddLinkService(Link link) {
		MessageInfo messageInfo = new MessageInfo();
		MyDateUtils myDateUtils = new MyDateUtils();
		String time = myDateUtils.getSystemNowTime("yyyy��MM��dd�� HHʱmm��ss��");//��ȡʱ��
		link.setlAddtime(time);
		link.setlClickcount(0);
		int i = usersMapperExt.AddLink(link);
		if (i == 1) {
			messageInfo.setCode(0);
			messageInfo.setMsg("��ӳɹ�");
			return messageInfo;
		}else {
			messageInfo.setCode(0);
			messageInfo.setMsg("���ʧ��");
			return messageInfo;
		}
	}
	
	/*  ��̨����-->���͹��� -->�������    ��ѯ���е����ӻ��߶���   */
	public LinkExt QueryLinkService(Link link) {
		
		if (link.getlNumber() != null) {
			LinkExt linkExt = new LinkExt();
			List<Link> datalist = new ArrayList<Link>();
			Link data = usersMapperExt.QueryLinkOne(link.getlNumber());
			datalist.add(data);
			linkExt.setData(datalist);
			linkExt.setCode(0);
			linkExt.setMsg("");
			linkExt.setCount(datalist.size());
			return linkExt;//���ص���������Ϣ
		}else {
			LinkExt linkExt = new LinkExt();
			List<Link> data= usersMapperExt.QueryLinks();
			linkExt.setData(data);
			linkExt.setCode(0);
			linkExt.setCount(data.size());
			linkExt.setMsg("");
			return linkExt;//�������ж�����Ϣ
		}
	}
	
	/* ��̨����-->���͹��� -->�������    ɾ������  */
	public MessageInfo DeleteLinkService(Link link) {
		MessageInfo messageInfo = new MessageInfo(); 
		Link data = usersMapperExt.QueryLinkOne(link.getlNumber());
		
		if (data != null) {
			int i = usersMapperExt.DeleteLinkByNumber(link.getlNumber());
			if (i == 1) {
				messageInfo.setCode(0);
				messageInfo.setMsg("ɾ���ɹ�");
				messageInfo.setStatus("");
			}else {
				messageInfo.setCode(1);
				messageInfo.setMsg("ɾ��ʧ��");
				messageInfo.setStatus("");
			}
			return messageInfo;
		}else {
			messageInfo.setCode(2);
			messageInfo.setMsg("�ܾ����ʣ�ɾ��������������");
			messageInfo.setStatus("");
			return messageInfo;
		}
	}
	
	/* ��̨����-->���͹��� -->�������    �޸�����  */
	public MessageInfo AlterLinkService(Link link) {
		MessageInfo messageInfo = new MessageInfo(); 
		Link data = usersMapperExt.QueryLinkOne(link.getlNumber());//��ѯ�Ƿ����Link
		
		if (data != null) {
			if (link.getlName().equals(data.getlName()) && link.getlLink().equals(data.getlLink())) {
				messageInfo.setCode(3);
				messageInfo.setMsg("ϵͳ��⵽��û���޸����ݣ��޷����²���");
			}else {
				int i = usersMapperExt.AlterLinkByNumber(link);
				if (i == 1) {
					messageInfo.setCode(0);
					messageInfo.setMsg("�޸ĳɹ�");
				}else {
					messageInfo.setCode(1);
					messageInfo.setMsg("�޸ĳɹ�");
				}
			}
			return messageInfo;
		}else {
			messageInfo.setCode(2);
			messageInfo.setMsg("�ܾ����ʣ��޸ĵ����Ӳ�����");
			return messageInfo;
		}
	}
	
	/* ��̨����-->���͹��� -->�������    �޸Ĳ�����Ϣ */
	public Bloginfo AlterBlogInfoByNumber(Bloginfo bloginfo, MessageInfo messageInfo) {
		if (bloginfo.getBiNumber() == 1) {
			
			return null;
		}else {
			Bloginfo data = usersMapperExt.QueryBlogInfoByNumber();
			return data;
		}
	}
	
	/* ��̨����-->�û����� -->����   ��ѯ�û�*/
	public UsersExt QueryUsersInfoByAllDataService(Users users) {
		UsersExt usersExt = new UsersExt(); 
		List<Users> data = usersMapperExt.QueryUsersInfoByAllData(users);
		usersExt.setData(data);
		usersExt.setCode(0);
		usersExt.setCount(data.size());
		usersExt.setMsg("");
		return usersExt;
	}
	
	/* ��̨����-->�û����� -->����   ɾ���û�*/
	public MessageInfo DeleteUserByIDService(Users users) {
		MessageInfo messageInfo = new MessageInfo();
		
		List<Users> data = usersMapperExt.QueryUsersInfoByAllData(users);
		
		if (data.size() != 0) {
			int i = usersMapperExt.DeleteUserByUID(users.getuId());
			if (i == 1) {
				messageInfo.setCode(0);
				messageInfo.setMsg("ɾ���ɹ�");
			}else {
				messageInfo.setCode(1);
				messageInfo.setMsg("ɾ��ʧ��, ϵͳִ���쳣");
			}
			return messageInfo;
		}else {
			messageInfo.setCode(2);
			messageInfo.setMsg("ɾ��ʧ��, ɾ�����û�������");
			return messageInfo;
		}
	}
	
	/* ��̨����-->�û����� -->����   ɾ���û�*/
	public MessageInfo AlterUserInfoByIdService(Users users) {
		MessageInfo messageInfo = new MessageInfo();
		Users data = usersMapperExt.QueryUserInfoByID(users);
		
		if (data != null) {
			int i = usersMapperExt.AlterUserInfo(users);
			
			if (i == 1) {
				messageInfo.setCode(0);
				messageInfo.setMsg("�޸ĳɹ�");
			}else {
				messageInfo.setCode(1);
				messageInfo.setMsg("�޸�ʧ�ܣ�ϵͳִ���쳣");
			}
			return messageInfo;
		}else {
			messageInfo.setCode(2);
			messageInfo.setMsg("�޸�ʧ�ܣ��޸ĵ��û���Ϣ������");
			return messageInfo;
		}
	}
	
	
	/* ��̨����-->���͹���  ��ѯ��Ϣ*/
	public Bloginfo QueryBlogInfoService() {
		Bloginfo bloginfo = usersMapperExt.QueryBlogInfoByNumber();
		return bloginfo;
	}
	
	/* ��̨����-->���͹��� �޸���Ϣ*/
	public MessageInfo AlterBlogInfoService(Bloginfo bloginfo) {
		MessageInfo messageInfo = new MessageInfo();
		int i = usersMapperExt.AlterBlogInfoByNumber(bloginfo);
		if (i == 1) {
			messageInfo.setCode(0);
			messageInfo.setMsg("�޸ĳɹ�");
		}else {
			messageInfo.setCode(0);
			messageInfo.setMsg("�޸�ʧ�ܣ�ϵͳִ���쳣");
		}
		return messageInfo;
	}
}
