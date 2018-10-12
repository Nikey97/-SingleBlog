package cn.kuqi.ServiceImpi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kuqi.Mapper.AdminBackstageMapper;
import cn.kuqi.Pojo.ArticleClassfiy;
import cn.kuqi.Pojo.ArticleClassfiyExt;
import cn.kuqi.Pojo.MessageInfo;
import cn.kuqi.Pojo.Users;
import cn.kuqi.Service.AdminBackstageService;

@Service
public class AdminBackstageServiceImpi implements AdminBackstageService{

	@Autowired
	AdminBackstageMapper usersMapperExt;
	/*
	 * ��̨����ϵͳ --> ��̨��¼
	 * 
	 * 
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
		
		ArticleClassfiyExt articleClassfiyExt=new ArticleClassfiyExt();
		
		List<ArticleClassfiy> list= usersMapperExt.QueryAllClassfiy();
		
		articleClassfiyExt.setData(list);
		
		articleClassfiyExt.setCode(0);
		
		articleClassfiyExt.setMsg("");
		
		articleClassfiyExt.setCount(list.size());
		
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
	
	
}
