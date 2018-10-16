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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.kuqi.JsonUtil.JsonAllUtil;
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
	
	@RequestMapping(value= ("/query_classfiy.do"),method= {RequestMethod.GET})//��ȡȫ������
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
	
	@RequestMapping(value= ("/alter_classfiy.do"),method= {RequestMethod.POST})//�޸ķ���
	public@ResponseBody MessageInfo AlterClassfiy(@RequestBody ArticleClassfiy articleClassfiy) {
		MessageInfo msg = adminBackstageServiceImpi.UpdataArticleClassfiyService(articleClassfiy.getAcNumber(), articleClassfiy.getAcClassfiyname(), articleClassfiy.getAcRemark());
		return msg;
	}
	
	@RequestMapping(value= ("/delete_classfiy.do "),method= {RequestMethod.POST})//ɾ�����·���
	public @ResponseBody MessageInfo DeleteAlterClassfiy(@RequestBody ArticleClassfiy articleClassfiy) {
		MessageInfo messageInfo = adminBackstageServiceImpi.DeleteArticleClassfiyService(articleClassfiy.getAcNumber());
		return messageInfo;
	}
	
	@RequestMapping(value= ("/query_article.do"),method= {RequestMethod.GET})//���²���  
	public @ResponseBody ArticleExt QueryAllArticle() {
		ArticleExt articleExt = adminBackstageServiceImpi.QueryAllArticleService();
		return articleExt;
	} 
	
	@RequestMapping(value= ("/delete_article.do"),method= {RequestMethod.POST})//ɾ������
	public@ResponseBody MessageInfo DeleteArticleByNumber(@RequestBody Article article) {
		MessageInfo msg = adminBackstageServiceImpi.DeleteArticleByNumberService(article.getaNumber());
		return msg;
	}
	/*
	 *   �޸�����
	 * 		���������
	 *			controller����ǰ̨�����±�ţ�Dao��ѯ�������ݡ� 
	 * 			��ת�����༭ҳ��
	 * */
	@RequestMapping(value= ("/alter_article"),method= {RequestMethod.POST})
	public String AlterArticleByNumber(Integer ArticleNumber,String ArticleContent,Model model) {
		
		//��ѯ����ִ�е�
		if (ArticleNumber != null && ArticleContent == null) {
			Article article = adminBackstageServiceImpi.QueryArticleByNumberService(ArticleNumber);
			model.addAttribute("article", article);//��ҳ���������
			return null;//ת�򷢲���ҳ��
		}
		
		//�޸�ִ�е�
		if (ArticleNumber != null && ArticleContent != null) {
			String json = adminBackstageServiceImpi.UpdataArticleByNumberService(ArticleNumber, ArticleContent);//�õ�ִ�н��
			model.addAttribute("msg", json);//��ҳ���������
			return null;//ת�򷢲���ҳ��
		}
		
		model.addAttribute("msg", "�����쳣������ϵ����Ա");
		return null;//ת�򷢲���ҳ��
	}
	
	@RequestMapping(value= ("/queryClassfiy_article.do"),method= {RequestMethod.POST})//ʹ�÷�������ѯ����
	public @ResponseBody ArticleExt QueryArticleByClassfiy(@RequestBody ArticleClassfiy articleClassfiy) {
		ArticleExt articleExt = adminBackstageServiceImpi.QueryArticleByClassfiyService(articleClassfiy.getAcNumber());
		return articleExt;
	}
	
	@RequestMapping(value= ("/add_article.do"),method= {RequestMethod.POST})//ʹ�÷�������ѯ����
	public String AddArticle(Article article,Model model) {
		String msg = adminBackstageServiceImpi.AddArticleService(article);
		model.addAttribute("msg", msg);
		return null;//ת����������ҳ
	}
	
	@RequestMapping(value= ("/add_link.do"),method= {RequestMethod.POST})//�������
	public @ResponseBody MessageInfo AddLink(@RequestBody Link link) {
		MessageInfo messageInfo = adminBackstageServiceImpi.AddLinkService(link);
		return messageInfo;
	}
	
	@RequestMapping(value= ("/query_link.do"),method= {RequestMethod.POST})//��ѯ�������ӻ��ߵ���  
	public@ResponseBody LinkExt QueryLink(@RequestBody Link link) {
		LinkExt linkExt = adminBackstageServiceImpi.QueryLinkService(link);
		return linkExt;
	}
	
	@RequestMapping(value= ("/delete_link.do"),method= {RequestMethod.POST})//ɾ������
	public@ResponseBody MessageInfo DeleteLink(@RequestBody Link link) {
		MessageInfo messageInfo = adminBackstageServiceImpi.DeleteLinkService(link);
		return messageInfo;
	}
	
	@RequestMapping(value= ("/alter_link.do"),method= {RequestMethod.POST})//�޸�����
	public@ResponseBody LinkExt AlterLink(@RequestBody Link link, HttpServletRequest request, HttpServletResponse response) throws IOException {
		JsonAllUtil jsonutil = new JsonAllUtil();//��ȡת������
		
		if (link.getlNumber() != null && link.getlName() == null && link.getlLink() == null) {
			//����link����
			LinkExt linkExt = adminBackstageServiceImpi.QueryLinkService(link);
			return linkExt;
		}  
		if (link.getlNumber() != null && link.getlName() != null && link.getlLink() != null) {
			//�޸�����
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();//��ȡ���Զ���
			MessageInfo messageInfo = adminBackstageServiceImpi.AlterLinkService(link);
			String msg = jsonutil.PojoToJson(messageInfo);
			out.println(msg);
			out.close();
		}
		return null;
	}
	
	
	
	/*
	 *	����ϵͳ --> �û����� 
	 * 	���������
	 * 			���û�������ɾ�Ĳ�
	 * */
	@RequestMapping(value= ("/query_user.do"),method= {RequestMethod.POST})//��������ѯ�û�
	public @ResponseBody UsersExt QueryUser (@RequestBody Users users) {
		UsersExt usersExt = adminBackstageServiceImpi.QueryUsersInfoByAllDataService(users);
		return usersExt;
	}
	
	@RequestMapping(value= ("/delete_user.do"),method= {RequestMethod.POST})//ɾ���û�
	public@ResponseBody MessageInfo DeleteUserByID(@RequestBody Users users) {
		MessageInfo messageInfo = adminBackstageServiceImpi.DeleteUserByIDService(users);
		return messageInfo;
	}
	
	@RequestMapping(value= ("/alter_user.do"),method= {RequestMethod.POST})//�޸��û���Ϣ
	public @ResponseBody MessageInfo AlterUsersByID(@RequestBody Users users, HttpServletResponse response) throws IOException {
		MessageInfo messageInfo = adminBackstageServiceImpi.AlterUserInfoByIdService(users);
		return messageInfo;
	}
	
	
	/*
	 * ���͹���-->��Ϣ���� 
	 * 	���������
	 * 			1.����ҳ��������Ӧ������Ϣ�����õ�����ʾ��   ��Ϣ��ѯ�ӿ�
	 * 			2.�޸���Ϣ		��Ϣ�޸Ľӿ�
	 * */
	@RequestMapping(value= ("/query_bloginfo.do"),method= {RequestMethod.GET})//��ѯ������Ϣ
	public @ResponseBody Bloginfo QueryBlogInfo() {
		Bloginfo bloginfo = adminBackstageServiceImpi.QueryBlogInfoService();
		return bloginfo;
	}
	
	@RequestMapping(value= ("/alter_bloginfo.do"),method= {RequestMethod.POST})//�޸Ĳ�����Ϣ
	public @ResponseBody MessageInfo AlterBlogInfo(@RequestBody Bloginfo bloginfo) {
		MessageInfo messageInfo = adminBackstageServiceImpi.AlterBlogInfoService(bloginfo);
		return messageInfo;
	}
	
	
	/*
	 *  ���͹���--> �������    
	 * 				�������:
	 * 					1.�ϴ�����
	 * 					2.�޸�������Ϣ
	 * 					3.ɾ������
	 * */
	@RequestMapping(value= ("/upload_theme.do"),method= {RequestMethod.POST})//�ϴ�����
	public String UploadTheme(@RequestParam("file") MultipartFile meMultipartFile) {
		
		return null;
	}
	
}
