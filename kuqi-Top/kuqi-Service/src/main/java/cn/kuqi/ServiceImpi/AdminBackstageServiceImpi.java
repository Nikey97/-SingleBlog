package cn.kuqi.ServiceImpi;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.kuqi.Service.AdminBackstageService;
import cn.kuqi.DateUtil.MyDateUtils;
import cn.kuqi.Mapper.AdminBackstageMapper;
import cn.kuqi.Pojo.Article;
import cn.kuqi.Pojo.ArticleClassfiy;
import cn.kuqi.Pojo.ArticleExt;
import cn.kuqi.Pojo.BlogInfoJoinTheme;
import cn.kuqi.Pojo.Bloginfo;
import cn.kuqi.Pojo.Link;
import cn.kuqi.Pojo.LinkExt;
import cn.kuqi.Pojo.MessageInfo;
import cn.kuqi.Pojo.Users;

@Service
@Transactional
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
	
	/**  
	  * @user: Nikey 
	  * @MethodName: QueryAllArticleClassfiyService
	  * @Description: ��ѯ���з���  
	  * @return return_type    
	  * @date: 2018��11��29�� ����3:21:22  
	  * @todo: TODO
	  */
	public List<ArticleClassfiy> QueryAllArticleClassfiyService(Integer now, Integer max) {
		return usersMapperExt.QueryAllClassfiy(now,max);
	}
	
	public Integer QueryCountArticleClassfiyService() {
		return usersMapperExt.QueryCountClassfiy();
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName:QueryOneArticleClassfiyService
	  * @Description: ��ѯ��������  
	  * @return ArticleClassfiy     
	  * @date: 2018��11��29�� ����4:11:24  
	  * @todo: TODO
	  */
	public ArticleClassfiy QueryOneArticleClassfiyService(Integer Number) {
		return usersMapperExt.QueryOneClassfiy(Number);
	}

	/**  
	  * @user: Nikey 
	  * @MethodName: AddClassfiyService
	  * @Description: ������·���  
	  * @return return_type     
	  * @date: 2018��11��29�� ����1:38:00  
	  * @todo: TODO
	  */
	@Transactional(readOnly=true)
	public Integer AddClassfiyService(String ClassfiyName, String ClassfiyRemark) {
		return usersMapperExt.InsertClassfiy(ClassfiyName, ClassfiyRemark);
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: UpdataArticleClassfiyService
	  * @Description: �޸����·���   
	  * @return return_type     
	  * @date: 2018��11��29�� ����5:29:05  
	  * @todo: TODO
	  */
	public Integer UpdataArticleClassfiyService(Integer Number, String ClassfiyName, String ClassfiyRemark) {
		int msgs;
		ArticleClassfiy articleClassfiy = usersMapperExt.SelectArtcleClassfiyByNumber(Number);
		if (ClassfiyName.equals(articleClassfiy.getAcClassfiyname()) && ClassfiyRemark.equals(articleClassfiy.getAcRemark()))
		{
			return 2;	
		}
		else
		{
			int i = usersMapperExt.UpdataArtcleClassfiyByNumber(Number, ClassfiyName, ClassfiyRemark);
			if (i == 1) 
			{
				msgs = 1;
			}
			else 
			{
				msgs = 0;
			}
			return msgs;
		}
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName:DeleteArticleClassfiyService
	  * @Description:ɾ�����֧ࣨ��������   
	  * @return return_type     
	  * @date: 2018��11��29�� ����9:20:39  
	  * @todo: TODO
	  */
	public MessageInfo DeleteArticleClassfiyService(ArrayList<Integer> classfiyList) {
		int success = 0, error = 0;
		MessageInfo msg = new MessageInfo();
		for (Integer number : classfiyList) {
			System.out.println(number);
			int i = usersMapperExt.DeleteArtcleClassfiyByNumber(number);
			if (i == 1)
				success++;
			else 
				error++;
		}
		msg.setCode(0);
		msg.setMsg("��ɾ������:"+classfiyList.size()+"�����ɹ���"+success+"��ʧ�ܣ�"+error);
		return msg;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: QueryAllArticleService
	  * @Description: ��ѯ������Ϣ   ����ҳ��
	  * @return Article     
	  * @date: 2018��12��3�� ����9:42:01  
	  * @todo: TODO
	  */
	public List<Article> QueryAllArticleService(Integer now, Integer max) {
		List<Article> article = usersMapperExt.QueryAllArticle(now,max);
		return article;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: QueryAllArticleService
	  * @Description: ��ѯ�������м�¼���������ṩ��ҳ���ҳ  
	  * @return Integer     
	  * @date: 2018��12��3�� ����9:53:24  
	  * @todo: TODO
	  */
	public Integer QueryAllCountArticleService() {
		return usersMapperExt.QueryCountArticle();
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: QueryArticleByNumberService
	  * @Description: ��ѯ��ƪ����  
	  * @return Article     
	  * @date: 2018��12��4�� ����11:50:17  
	  * @todo: TODO
	  */
	public Article QueryArticleByNumberService(Integer Number) {
		Article article = usersMapperExt.QueryArticleByNumber(Number);
		return article;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: DeleteArticleByNumberService
	  * @Description: ɾ����ƪ����  
	  * @return return_type     
	  * @date: 2018��12��4�� ����11:50:17  
	  * @todo: TODO
	  */
	public String DeleteArticleByNumberService(Integer Number) {
		String msgs = null;
		Article article = usersMapperExt.QueryArticleByNumber(Number);
		if (article != null) {
			int i = usersMapperExt.DeleteArticleByNumber(Number);
			if (i == 1) {
				msgs = "ɾ���ɹ�";
			}else {
				msgs = "ɾ��ʧ��";
			}
		}else {
			msgs = "���²�����";
		}
		return msgs;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName:
	  * @Description: ��ѯ�������е����ۼ�¼��  
	  * @return int     
	  * @date: 2018��12��4�� ����2:56:55  
	  * @todo: TODO
	  */
	public Integer QueryAllArticleCommentsService(Integer Number) {
		return usersMapperExt.QueryArticleAllComment(Number);
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: QueryNotPublisArticleServices
	  * @Description: ��ѯ����δ����������  
	  * @return List<Article>     
	  * @date: 2018��12��6�� ����9:45:26  
	  * @todo: TODO
	  */
	public List<Article> QueryNotPublisArticleServices(Integer now, Integer max) {
		return usersMapperExt.QueryAllDraft(now, max);
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: QueryNotPublisArticleCountServices
	  * @Description: ��ѯδ�Ķ����µ��ܼ�¼��  
	  * @return Integer     
	  * @date: 2018��12��6�� ����9:55:16  
	  * @todo: TODO
	  */
	public Integer QueryNotPublisArticleCountServices() {
		return usersMapperExt.QueryAllDraftCount();
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
	public Integer addLinkService(Link link) {
		MyDateUtils myDateUtils = new MyDateUtils();
		List<Link> linkList = usersMapperExt.queryLinks(0,5,link.getlName(),null,null);
		if (linkList.size() != 0) {
			return 0;
		}//���ظ�������
		String time = myDateUtils.getSystemNowTime("yyyy��MM��dd�� HHʱmm��ss��");//��ȡʱ��
		link.setlAddtime(time);
		link.setlClickcount(0);
		link.setlShow(link.getlShow());
		return usersMapperExt.addLink(link);
	}
	
	/*  ��̨����-->���͹��� -->�������    ��ҳ��ѯ�������ӻ��߶���   */
	public List<Link> queryLinkService(Integer pager,Integer max) {
		return usersMapperExt.queryLinks(pager,max,null,null,null);
	}
	
	/*  ��̨����-->���͹��� -->�������    ��ҳ��ѯ���е�����   */
	public Integer queryAllLinkService() {
		List<Link> list = usersMapperExt.queryAllLinks();
		return list.size();
	}
	
	/*  ��̨����-->���͹��� -->�������    ��ҳ��ѯָ�������ӻ��߶���   */
	public List<Link> queryOneLinkService(Integer Number) {
		return usersMapperExt.queryLinks(0,5,null,null,Number);
	}
	
	/* ��̨����-->���͹��� -->�������    ɾ������  */
	public Integer deleteLinkService(Link link) {
		List<Link> listLink = usersMapperExt.queryLinks(0,5,link.getlName(),null,null);
		if (listLink.size() != 0) {
			return usersMapperExt.deleteLinkByNumber(link.getlNumber());
		}else {
			return 0;
		}
	}
	
	/* ��̨����-->���͹��� -->�������    �޸�����  */
	
	public Integer alterLinkService(Link link) {
		int i;
		List<Link> listLink = usersMapperExt.queryLinks(0,5,null,null,link.getlNumber());
		if (listLink.size() != 0) {
			i = usersMapperExt.alterLinkByNumber(link);
		}else {
			i = 0;
		}
		return i;
	}
	
	/* ��̨����-->�û����� -->����   ��ѯ�û�*/
	public List<Users> queryUsersInfoByAllDataService(Users users) {
		return usersMapperExt.queryUsersInfoByAllData(users);
	}
	
	/* ��̨����-->�û����� -->����   ��ҳ��ѯ�û� */
	public List<Users> queryUsersInfoByAllService(Integer now, Integer max) {
		return usersMapperExt.queryAllUser(now, max);
	}
	
	
	/* ��̨����-->�û����� -->����   ɾ���û�*/
	public MessageInfo DeleteUserByIDService(Users users) {
		MessageInfo messageInfo = new MessageInfo();
		
		List<Users> data = usersMapperExt.queryUsersInfoByAllData(users);
		
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
	public BlogInfoJoinTheme queryBlogInfoService() {
		return usersMapperExt.queryBlogInfoByNumber();
	}
	
	/* ��̨����-->���͹��� �޸���Ϣ*/
	public Integer alterBlogInfoService(Bloginfo bloginfo) {
		return usersMapperExt.alterBlogInfoByNumber(bloginfo);
	}

	
}
