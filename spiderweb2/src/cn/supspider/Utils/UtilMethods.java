package cn.supspider.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class UtilMethods implements SendImpl{
		//ע������������
		//�����ʼ����˻�����
		
	   
	   
	    

		//�������ڿ��ٻ�ȡϵͳ��ǰʱ��
		public String getNowSystemTime() {
			@SuppressWarnings("unused")
			Date date=new Date();
			@SuppressWarnings("unused")
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(date);
		}
		//�����ʼ�����
		
		public int Send(String receiveMailAccount,int verifity_code) throws Exception{
			
			String myEmailAccount = "netspider@yeah.net";
			String myEmailPassword = "1069yu";
			String myEmailSMTPHost = "smtp.yeah.net";
			
			Properties props=new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.smtp.host", myEmailSMTPHost);   // �����˵������ SMTP ��������ַ(������spring���)
	        props.setProperty("mail.smtp.auth", "true");
	        
			Session session = Session.getInstance(props);
			
			session.setDebug(true);  
			
			MimeMessage message=createMimeMessage(session,myEmailAccount,receiveMailAccount);
			
			Transport transport=session.getTransport();
			
			transport.connect(myEmailAccount, myEmailPassword);
			
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
			
			return 1;
		}
		//�����ʼ�����
		@SuppressWarnings("unused")
		private MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception, Exception {
			
			MimeMessage message=new MimeMessage(session);
			
			message.setFrom(new InternetAddress(sendMail,"SupSpider�ʼ�������-NO.9527","UTF-8"));
			
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMail, "UTF-8"));
			
			message.setSubject("��ӭע��SupSpider��Դ����կ,�������Ӽ��������˻�!","UTF-8");
			
			message.setContent("<!DOCTYPE html><html><head><meta charset=\"utf-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\"><title>�ʼ���֤</title><style type=\"text/css\">html,body{width: 100%;height: 100%;margin: 0;padding: 0;}</style></head><body><div style=\"width: 100%;height: 70px;background: #6495ED;\"><span style=\"color: #fff; line-height: 70px; font-weight: 600; margin-left: 10px;\"><a href=\"#\" style=\"text-decoration: none;color: inherit;\">SupSpider</a></span></div><div style=\"width: 100%; height: 100%; background: #F5F5F5; text-indent: 15px;padding-top: 30px;\"><span style=\"font-size: 15px; color: #696969;\">���������������ʵ���Դ����������һ�����߸������ŵ��û����顣����ʼ�ն����Ǿ仰�����ǲ�������Դ������ֻ�ǻ�������Դ�İ��˹�����ӭ��ע��΢����ʱ�õ����µ���Դ��̬��</span><a href=\"http://localhost:8080/spiderweb2/\" style=\"display: block; margin-left: auto; margin-right: auto; width: 90px; height: 30px; margin-top: 30px; background: #7CCD7C; padding-top: 10px; text-decoration: none; color: #fff; border: 1px soild #EBEBEB;\">�������</a></div></body></html>","text/html;charset=UTF-8");
			
			message.setSentDate(new Date());
			
			message.saveChanges();
			
			return message;
		}
		
		//������֤��
		@Test
		public int CreateSuperCode() {
			int code=(int) ((Math.random()*9+1)*1000000);
			return code;
		}
		
}
