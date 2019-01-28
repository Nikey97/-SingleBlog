package cn.kuqi.Service;

import java.util.ArrayList;
import java.util.List;
import cn.kuqi.Pojo.Article;
import cn.kuqi.Pojo.ArticleClassfiy;
import cn.kuqi.Pojo.ArticleExt;
import cn.kuqi.Pojo.BlogInfoJoinTheme;
import cn.kuqi.Pojo.Bloginfo;
import cn.kuqi.Pojo.Link;
import cn.kuqi.Pojo.LinkExt;
import cn.kuqi.Pojo.MessageInfo;
import cn.kuqi.Pojo.Users;


public interface AdminBackstageService {
	
	//��̨����ϵͳ  --> ��̨��ҳ
	
	
	/**	��̨����ϵͳ --> ��̨��¼
	 * 	���������
	 * 		�û����͵�¼�˺ţ���̨��Ӧִ�е�¼�Ƿ�ɹ���
	 * 		
	 * 		1.�˺� ����
	 * 		2.�ж��˺��Ƿ����û����������� �� �����Ǽ������û������ڵ�Json
	 * */
	Integer QueryUserLoginInfo(String account,String psw) ;
	
	
	/**
	 * ��̨����ϵͳ --> ��ѯȫ��
	 * 
	 * ���������
	 * 		��ǰ�˵����ݱ��Խӣ����밴�ձ������ݷ���Json��
	 * */
	List<ArticleClassfiy> QueryAllArticleClassfiyService(Integer now, Integer max);
	 
	/**
	 * ��̨����ϵͳ --> ��ѯ�����¼��
	 * 
	 * ���������
	 * 		��ǰ�˵����ݱ��Խӣ����밴�ձ������ݷ���Json��
	 * */
	Integer QueryCountArticleClassfiyService();
	
	 /**
	  * ��̨����ϵͳ --> ��ѯ��������
	  * 
	  * */
	ArticleClassfiy QueryOneArticleClassfiyService(Integer Number);
	
	 /**
	  * ��̨����-->��ӷ���
	  * ���������
	  * 		controller������ӵķ�����Ϣ���ڷ���ִ�н��
	  * */
	Integer AddClassfiyService(String ClassfiyName,String ClassfiyRemark) ;
	 
	 /** 
	  * ��̨����-->���� ����-->���� 
	  *  �޸ķ��� 
	  *
	  * */
	 Integer UpdataArticleClassfiyService(Integer Number , String ClassfiyName, String ClassfiyRemark) ;
	 
	 /**
	  * ��̨����-->���� ����-->���� 
	  *  ɾ������ 
	  *
	  * */
	 MessageInfo DeleteArticleClassfiyService(ArrayList<Integer> classfiyList);
	 
	 /**
	  * ��̨����-->���²���  ��ѯ��������
	  * 
	  * */
	 List<Article> QueryAllArticleService(Integer now, Integer max);
	 
	 /**
	  * ��̨����-->��ѯ����������
	  * 
	  * */
	 Integer QueryAllArticleCommentsService(Integer Number);
	 
	 /**
	  * ��̨����-->��ѯ����δ��������
	  * 
	  * */
	 List<Article> QueryNotPublisArticleServices(Integer now, Integer max);
	 
	 /**
	  * ��̨����-->��ѯ����δ��������
	  * 
	  * */
	 Integer QueryNotPublisArticleCountServices();
	 
	 /**
	  * ��̨����-->���²���  ��ѯ�������¼�¼��
	  * 
	  * */
	 Integer QueryAllCountArticleService();
	 
	 /**
	  * ��̨����-->���²���  ��ѯ��ƪ����
	  * 
	  * 
	  * */
	 Article QueryArticleByNumberService(Integer Number);
	 
	 
	 /**
	  * ��̨����-->����ɾ��
	  * ���������
	  * 		Controller ����Ҫɾ�������±�ţ� ��ִ��sqlɾ��ѡ��ı��
	  * */
	 String DeleteArticleByNumberService(Integer Number) ;
	 
	 /**
	  * ��̨����--> �����޸�
	  * 
	  * */
	 String UpdataArticleByNumberService(Integer Number,String Content);
	 
	 /**
	  * ��̨����--> ʹ�÷�������ѯ��������
	  * 
	  * */
	 ArticleExt QueryArticleByClassfiyService(Integer ClassfiyNumber) ;
	 
	 /**
	  * ��̨����--> ������
	  * 
	  * */
	 String AddArticleService(Article article);
	 
	 /**
	  * ��̨����--> ��������    ���
	  * 
	  * */
	 Integer addLinkService(Link link) ;
	 
	 
	 /**
	  * ��̨����--> ��������    ��ѯ���е����ӻ����ǵ���
	  * 
	  * */
	 List<Link> queryLinkService(Integer pager,Integer max);
	 
	 /**
	  * ��̨����--> ��������    ɾ������
	  * 
	  * */
	 Integer deleteLinkService(Link link);
	 
	 /**
	  * ��̨����--> ��������    �޸�����
	  * 
	  * */
	 Integer alterLinkService(Link link);
	 
	 /**
	  * ��̨���� -->�û��ۺ�������ѯ
	  * ���������
	  *			�û�ִ�����������󣬲�ѯ�����ƶ��������û���
	  *			���������󣬲�ѯ�����������û�
	  * */
	 List<Users> queryUsersInfoByAllDataService(Users users);//�ۺ�������ѯ
	 
	 MessageInfo deleteUserByIDService(List<Integer> list);//ɾ���û�
	 
	 MessageInfo alterUserInfoByIdService(Users users);//�޸��û���Ϣ
	 
	 List<Users> queryUsersInfoByAllService(Integer now,Integer max); //�û���Ϣ��ҳ��ѯ
	 
	 
	 
	 
	 /**
	  * ��̨����-->������Ϣ��ѯ
	  * 	���������
	  * 			1.����ҳ��������Ӧ������Ϣ�����õ�����ʾ��   ��Ϣ��ѯ�ӿ�
	  * 			2.�޸���Ϣ		��Ϣ�޸Ľӿ�
	  * */
	 BlogInfoJoinTheme queryBlogInfoService();//��ѯ����
	 
	 Integer alterBlogInfoService(Bloginfo bloginfo);//�޸Ĳ�����Ϣ
	 
}
