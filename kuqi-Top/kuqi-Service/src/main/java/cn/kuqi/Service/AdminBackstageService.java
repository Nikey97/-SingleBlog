/**
 * 
 */
package cn.kuqi.Service;

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

/**
 * @author Nikey
 *
 */
public interface AdminBackstageService {
		
	//��̨����ϵͳ  --> ��̨��ҳ
	
	
	/*	��̨����ϵͳ --> ��̨��¼
	 * 	���������
	 * 		�û����͵�¼�˺ţ���̨��Ӧִ�е�¼�Ƿ�ɹ���
	 * 		
	 * 		1.�˺� ����
	 * 		2.�ж��˺��Ƿ����û����������� �� �����Ǽ������û������ڵ�Json
	 * */
	Integer QueryUserLoginInfo(String account,String psw) ;
	
	
	/*
	 * ��̨����ϵͳ --> ��ѯȫ��
	 * 
	 * ���������
	 * 		��ǰ�˵����ݱ��Խӣ����밴�ձ������ݷ���Json��
	 * 
	 * 
	 * */
	 ArticleClassfiyExt QueryAllArticleClassfiyService();
	 
	 
	 /*
	  * ��̨����-->��ӷ���
	  * ���������
	  * 		controller������ӵķ�����Ϣ���ڷ���ִ�н��
	  * 
	  * */
	 MessageInfo AddClassfiyService(String ClassfiyName,String ClassfiyRemark) ;
	 
	 
	 /*
	  * ��̨����-->�޸Ĳ�ѯ����
	  * 
	  * */
	 ArticleClassfiy SelectArticleClassfiyService(Integer Number);
	 
	 
	 /* 
	  * ��̨����-->���� ����-->���� 
	  *  �޸ķ��� 
	  *
	  * */
	 MessageInfo UpdataArticleClassfiyService(Integer Number , String ClassfiyName, String ClassfiyRemark) ;
	 
	 /* 
	  * ��̨����-->���� ����-->���� 
	  *  ɾ������ 
	  *
	  * */
	 MessageInfo DeleteArticleClassfiyService(Integer Number) ;
	 
	 /*
	  * ��̨����-->���²���  ��ѯ��������
	  * 
	  * 
	  * */
	 ArticleExt QueryAllArticleService();
	 
	 /*
	  * ��̨����-->���²���  ��ѯ��ƪ����
	  * 
	  * 
	  * */
	 Article QueryArticleByNumberService(Integer Number);
	 
	 
	 
	 /*
	  * ��̨����-->����ɾ��
	  * ���������
	  * 		Controller ����Ҫɾ�������±�ţ� ��ִ��sqlɾ��ѡ��ı��
	  * */
	 MessageInfo DeleteArticleByNumberService(Integer Number) ;
	 
	 /*
	  * ��̨����--> �����޸�
	  * 
	  * */
	 String UpdataArticleByNumberService(Integer Number,String Content);
	 
	 /*
	  * ��̨����--> ʹ�÷�������ѯ��������
	  * 
	  * */
	 ArticleExt QueryArticleByClassfiyService(Integer ClassfiyNumber) ;
	 
	 /*
	  * ��̨����--> ������
	  * 
	  * */
	 String AddArticleService(Article article);
	 
	 /*
	  * ��̨����--> ��������    ���
	  * 
	  * */
	 MessageInfo AddLinkService(Link link) ;
	 
	 
	 /*
	  * ��̨����--> ��������    ��ѯ���е����ӻ����ǵ���
	  * 
	  * */
	 LinkExt QueryLinkService(Link link);
	 
	 /*
	  * ��̨����--> ��������    ɾ������
	  * 
	  * */
	 MessageInfo DeleteLinkService(Link link);
	 
	 /*
	  * ��̨����--> ��������    �޸�����
	  * 
	  * */
	 MessageInfo AlterLinkService(Link link);
	 
	 /*
	  * ��̨����--> ��������    �޸Ĳ�����Ϣ
	  * */
	 Bloginfo AlterBlogInfoByNumber(Bloginfo bloginfo, MessageInfo messageInfo);
	 
	 
	 
	 
	 /*
	  * ��̨���� -->�û��ۺ�������ѯ
	  * ���������
	  *			�û�ִ�����������󣬲�ѯ�����ƶ��������û���
	  *			���������󣬲�ѯ�����������û�
	  * */
	 UsersExt QueryUsersInfoByAllDataService(Users users);//�ۺ�������ѯ
	 
	 MessageInfo DeleteUserByIDService(Users users);//ɾ���û�
	 
	 MessageInfo AlterUserInfoByIdService(Users users);//�޸��û���Ϣ
	 
	 
	 /*
	  * ��̨����-->������Ϣ��ѯ
	  * 	���������
	  * 			1.����ҳ��������Ӧ������Ϣ�����õ�����ʾ��   ��Ϣ��ѯ�ӿ�
	  * 			2.�޸���Ϣ		��Ϣ�޸Ľӿ�
	  * */
	 Bloginfo QueryBlogInfoService();//��ѯ����
	 
	 MessageInfo AlterBlogInfoService(Bloginfo bloginfo);//�޸Ĳ�����Ϣ
	 
}
