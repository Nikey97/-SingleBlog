/**
 * 
 */
package cn.kuqi.Service;

import cn.kuqi.Pojo.Article;
import cn.kuqi.Pojo.ArticleClassfiy;
import cn.kuqi.Pojo.ArticleClassfiyExt;
import cn.kuqi.Pojo.ArticleExt;
import cn.kuqi.Pojo.MessageInfo;

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
	 ArticleExt QueryArticleByClassfiy(Integer ClassfiyNumber) ;
}
