package cn.kuqi.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kuqi.Pojo.ArticleClassfiy;
import cn.kuqi.Pojo.ArticleClassfiyExt;
import cn.kuqi.Pojo.MessageInfo;
import cn.kuqi.ServiceImpi.AdminBackstageServiceImpi;


@Controller
@RequestMapping("/admin")
public class AdminBackStageController {
	
	@Autowired 
	AdminBackstageServiceImpi adminBackstageServiceImpi;
	
	
	@SuppressWarnings("unused")
	@RequestMapping("/index")//����ϵͳ��ҳ
	public String index(HttpServletRequest request,Model model,HttpSession session) {
		System.out.println("/index��    ִ��");
		session = request.getSession();
		String username = (String) session.getAttribute("account");
		System.out.println(username);
		if (!username.equals("") || username !=null) {
			//���ڹ���Ա��¼,���ز�ѯ���ݡ�
			
			return "admin/index";
		}else {
			//δ��¼������ض�����login��
			return "redirect:admin/login";
		}
	}
	
	@RequestMapping("/login")//����ϵͳ��¼
	public String login(HttpServletRequest request, Model model, HttpSession session, String account, String psw) {
		
		
		int code = adminBackstageServiceImpi.QueryUserLoginInfo(account, psw);
		
		//��¼�ɹ�
		if (code == 0) {
			session.setAttribute("account", account);//��session�д����û�
			return "redirect:admin/index";
		}
		//�������
		if (code == 1) {
			model.addAttribute("status", "���������������ٴ�����");
		}
		//�˺Ų�����
		if (code == 2) {
			model.addAttribute("status", "������˺Ų����ڣ����֤������");
		}
		
		return null;
	}
	
	@RequestMapping(value= ("/query_classfiy.do"),method= {RequestMethod.GET})//��ȡȫ������ӿ�
	public@ResponseBody ArticleClassfiyExt QueryClassfiy() {
		
		ArticleClassfiyExt articleClassfiyExt = adminBackstageServiceImpi.QueryAllArticleClassfiyService();
		
		return articleClassfiyExt;
	}
	
	@RequestMapping(value= ("/add_classfiy.do"),method= {RequestMethod.POST})//���һ������
	public @ResponseBody MessageInfo AddClassfiy(@RequestBody ArticleClassfiy articleClassfiy) {
		
		MessageInfo msg = adminBackstageServiceImpi.AddClassfiyService(articleClassfiy.getAcClassfiyname(), articleClassfiy.getAcRemark());
		
		return msg;
	}
	
	@RequestMapping(value= ("/alter_query_classfiy.do"),method= {RequestMethod.POST})//�޸Ĳ�ѯ����
	public@ResponseBody ArticleClassfiy AlterQueryClassfiy(@RequestBody ArticleClassfiy articleClassfiy,HttpServletResponse response) throws IOException {
		
		ArticleClassfiy Classfiy = adminBackstageServiceImpi.SelectArticleClassfiyService(articleClassfiy.getAcNumber());
		//�ݴ��ж�
		if (Classfiy != null) {
			return Classfiy;
		}else {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("{\"code\":1,\"msg\":\"���಻����\"}");
			out.close();//���ش�����Ϣ
			return null;
		}
	}
	
	@RequestMapping(value= ("/alter_classfiy.do "),method= {RequestMethod.POST})//�޸ķ���
	public@ResponseBody MessageInfo AlterClassfiy(@RequestBody ArticleClassfiy articleClassfiy) {
		MessageInfo msg = adminBackstageServiceImpi.UpdataArticleClassfiyService(articleClassfiy.getAcNumber(), articleClassfiy.getAcClassfiyname(), articleClassfiy.getAcRemark());
		return msg;
	}
	
	@RequestMapping(value= ("/delete_classfiy.do "),method= {RequestMethod.POST})//ɾ�����·���
	public @ResponseBody MessageInfo DeleteAlterClassfiy(@RequestBody ArticleClassfiy articleClassfiy) {
		MessageInfo messageInfo = adminBackstageServiceImpi.DeleteArticleClassfiyService(articleClassfiy.getAcNumber());
		return messageInfo;
	}
	
}
