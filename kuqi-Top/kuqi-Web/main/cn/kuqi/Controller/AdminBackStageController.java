package cn.kuqi.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import cn.kuqi.Annotation.CheckUserIdentity;
import cn.kuqi.DateUtil.ZipUtils;
import cn.kuqi.Pojo.Article;
import cn.kuqi.Pojo.ArticleClassfiy;
import cn.kuqi.Pojo.ArticleExt;
import cn.kuqi.Pojo.BlogInfoJoinTheme;
import cn.kuqi.Pojo.Bloginfo;
import cn.kuqi.Pojo.Link;
import cn.kuqi.Pojo.MessageInfo;
import cn.kuqi.Pojo.Users;
import cn.kuqi.Poptis.PropertiesUtils;
import cn.kuqi.ServiceImpi.AdminBackstageServiceImpi;

@Controller
@RequestMapping("/admin")
public class AdminBackStageController {
	
	@Autowired 
	AdminBackstageServiceImpi adminBackstageServiceImpi;
	
	/**
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��4������11:40:45
	 * �������ܣ�����ҳ����ҳ�Ĺ���
	 * ������ע����������֤�û���ͨ�����session�л�ȡ�û��˺ŷ���
	 * @version
	 */
	@CheckUserIdentity
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String acc = (String) session.getAttribute("account");
		model.addAttribute("account", acc);
		return "admin/index.html";
	}
	
	/**
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��4������8:59:34
	 * �������ܣ��û���¼�ӿڣ����գ��˺š����룩
	 * ������ע���˻�����Ϊ�ձ�ʾ�������ҳ�棬���˺�����˵����¼����
	 * @version
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model, 
			HttpSession session, String account, String psw) {
		if (account != null && psw != null) {
			int code = adminBackstageServiceImpi.QueryUserLoginInfo(account, psw);
			if (code == 0) {
				session.setAttribute("account", account);//��session�д����û�
				return "redirect:index";//��¼�ɹ�
			}
			if (code == 1) {
				model.addAttribute("status", "���������������ٴ�����");//�������
			}
			if (code == 2) {
				model.addAttribute("status", "������˺Ų����ڣ����֤������");//�˺Ų�����
			}
		}else {
			String acc = (String) session.getAttribute("account");
			model.addAttribute("account", acc);//����ʾ�û���ʱ���ѯ�Ƿ����û���¼
		}
		return "admin/login.html";
	}
	
	/**
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��4������11:42:26
	 * �������ܣ�����ҳ���û�ע���ӿ�
	 * ������ע���ж��Ƿ�����û������ھ�ע���������ھͷ�����Ϣ
	 * @version
	 */
	@CheckUserIdentity
	@RequestMapping("/logout")
	public String UserLogout(HttpSession session, Model model) {
		String msg = null;
		String acc = (String) session.getAttribute("account");
		if (acc != null) {
			session.removeAttribute("account");//ɾ���û���Ϣ
			model.addAttribute("status", "ע���ɹ���");
			msg = "admin/login.html";
		}
		return msg;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: QueryClassfiy
	  * @Description: ��ȡȫ���ķ��֧ࣨ�ַ�ҳ��   
	  * @return ArticleClassfiyExt     
	  * @date: 2018��11��23�� ����11:03:14  
	  * @todo: TODO
	  */
	@Transactional(readOnly = true)
	@RequestMapping("/classfiy")
	public String queryClassfiy(Model model, Integer pager) {
		int pagers = 0;
		if (pager == null || pager <= 0) {
			pager = 0;
		}else {
			pagers = pager-1;
		}
		int PagerAll = adminBackstageServiceImpi.QueryCountArticleClassfiyService();
		List<ArticleClassfiy> list = adminBackstageServiceImpi.QueryAllArticleClassfiyService(pagers*5,5);
		model.addAttribute("dataList", list);
		model.addAttribute("pagerAll", PagerAll);
		model.addAttribute("pagerNow", pager);
		return "admin/classify.html";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName:QueryClassfiyUpdata
	  * @Description: ͨ�������Ų����·�����Ϣ   
	  * @return      
	  * @date: 2018��11��29�� ����4:07:35  
	  * @todo: TODO
	  */
	@RequestMapping("/query_classfiy.do")
	public @ResponseBody ArticleClassfiy queryClassfiyUpdata(int number) {
		return adminBackstageServiceImpi.QueryOneArticleClassfiyService(number);
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: AddClassfiy
	  * @Description: ����������µķ������  ������Ƿ��¼��
	  * @return MessageInfo     
	  * @date: 2018��11��23�� ����10:59:43  
	  * @todo: TODO
	  */
	@CheckUserIdentity
	@RequestMapping(value= ("/add_classfiy.do"),method= {RequestMethod.POST})//���һ������
	public String AddClassfiy(String classfiyName, String classfiyDescription, Model model) {
		int i = adminBackstageServiceImpi.AddClassfiyService(classfiyName, classfiyDescription);
		if (i == 1) {
			model.addAttribute("msgs", "��ӳɹ���");
		}else {
			model.addAttribute("msgs", "���ʧ�ܣ�");
		}
		return "admin/classify.html";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName:AlterClassfiy
	  * @Description:�޸Ĳ�ѯ����  
	  * @return return_type     
	  * @date: 2018��11��29�� ����5:11:29  
	  * @todo: TODO
	  */
	@CheckUserIdentity
	@RequestMapping(value= ("/alter_classfiy.do"),method= {RequestMethod.POST})//�޸ķ���
	public String AlterClassfiy(Integer classfiyNumberUpdata, String classfiyNameUpdata, String classfiyDescriptionUpdata, Model model) {
		int i = adminBackstageServiceImpi.UpdataArticleClassfiyService(classfiyNumberUpdata,classfiyNameUpdata,classfiyDescriptionUpdata);
		switch (i) {
		case 0:
			model.addAttribute("msgs", "�޸�ʧ�ܣ�");
			break;
		case 1:
			model.addAttribute("msgs", "�޸ĳɹ���");
			break;
		case 2:
			model.addAttribute("msgs", "ϵͳ��⣺û���޸����ݣ���ִ�и��¡�");
			break;
		default:
			break;
		}
		return "admin/classify.html";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: DeleteAlterClassfiy
	  * @Description:ɾ�����·��� 
	  * @return return_type    
	  * @date: 2018��11��29�� ����5:42:22  
	  * @todo: TODO
	  */
	@CheckUserIdentity
	@RequestMapping(value= ("/delete_classfiy.do"),method= {RequestMethod.POST})
	public @ResponseBody MessageInfo DeleteAlterClassfiy(@RequestParam("classfiyList[]") ArrayList<Integer> classfiyList, 
			Model model) {
		return adminBackstageServiceImpi.DeleteArticleClassfiyService(classfiyList);
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: QueryAllArticle
	  * @Description: ��ѯ���£�֧�ַ�ҳ��  
	  * @return ArticleExt     
	  * @date: 2018��12��3�� ����9:32:54  
	  * @todo: TODO
	  */
	@CheckUserIdentity
	@RequestMapping(value= ("/article"),method= {RequestMethod.GET})
	public String QueryAllArticle(Model model, Integer pager) {
		int pagers = 0;
		if (pager == null || pager <= 0) {
			pager = 0;
		}else {
			pagers = pager-1;
		}
		int all = adminBackstageServiceImpi.QueryAllCountArticleService();
		List<Article> articles = adminBackstageServiceImpi.QueryAllArticleService(pagers*5, 5);
		model.addAttribute("dataList", articles);
		model.addAttribute("pagerAll", all);
		model.addAttribute("pagerNow", pager);
		return "admin/operation.html";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: DeleteArticleByNumber
	  * @Description: ɾ������  
	  * @return return_type     
	  * @date: 2018��12��4�� ����11:38:27  
	  * @todo: TODO
	  */
	@CheckUserIdentity
	@RequestMapping(value= ("/delete_article.do"),method= {RequestMethod.POST})//ɾ������
	public @ResponseBody MessageInfo DeleteArticleByNumber(Integer number, Model model) {
		return null;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: draft
	  * @Description: ��ѯδ����������   ��֧�ַ�ҳ��
	  * @return String     
	  * @date: 2018��12��6�� ����9:58:55  
	  * @todo: TODO
	  */
	@CheckUserIdentity
	@RequestMapping("/draft")
	public String draft(Model model, Integer pager) {
		int pagers = 0;
		if (pager == null || pager <= 0) {
			pager = 0;
		}else {
			pagers = pager-1;
		}
		int all = adminBackstageServiceImpi.QueryNotPublisArticleCountServices();
		List<Article> articles= adminBackstageServiceImpi.QueryNotPublisArticleServices(pagers*5, 5);
		model.addAttribute("dataList", articles);
		model.addAttribute("pagerAll", all);
		model.addAttribute("pagerNow", pager);
		return "admin/draft.html";
	}
	
	/*
	 *   �޸�����
	 * 		���������
	 *			controller����ǰ̨�����±�ţ�Dao��ѯ�������ݡ� 
	 * 			��ת�����༭ҳ��
	 * */
	@RequestMapping(value= ("/alter_article"),method= {RequestMethod.POST})
	public String alterArticleByNumber(Integer ArticleNumber,String ArticleContent,Model model) {
		
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
	public @ResponseBody ArticleExt queryArticleByClassfiy(@RequestBody ArticleClassfiy articleClassfiy) {
		ArticleExt articleExt = adminBackstageServiceImpi.QueryArticleByClassfiyService(articleClassfiy.getAcNumber());
		return articleExt;
	}
	
	@RequestMapping(value= ("/add_article.do"),method= {RequestMethod.POST})//ʹ�÷�������ѯ����
	public String addArticle(Article article,Model model) {
		String msg = adminBackstageServiceImpi.AddArticleService(article);
		model.addAttribute("msg", msg);
		return null;//ת����������ҳ
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: 
	  * @Description: ��������: �������
	  * @return String     
	  * @date: 2019��1��16�� ����4:36:30  
	  * @todo: TODO
	  */
	@RequestMapping(value= ("/add_link.do"),method= {RequestMethod.POST})
	public @ResponseBody MessageInfo addLink(@RequestBody Link link) {
		MessageInfo messageInfo = getMessageInfoInstance();
		Integer i = adminBackstageServiceImpi.addLinkService(link);
		if (i == 0) {
			messageInfo.setCode(1);
			messageInfo.setMsg("Fail! ����������ظ�");
		}else if (i == 1){
			messageInfo.setCode(0);
			messageInfo.setMsg("Ok! ��ӳɹ�");
		}
		return messageInfo;
	}
	
	/**
	 * @author Nikey
	 * @Description: �������ӣ���ҳ��ѯ
	 * @MethodName: queryLink
	 * @param String ҳ��ת��
	 */
	@RequestMapping(value="/link")  
	public String queryLink(Model model, Integer pager) {
		int pagers = 0;
		if (pager == null || pager <= 0) {
			pager = 0;
		}else {
			pagers = pager-1;
		}
		List<Link> link = adminBackstageServiceImpi.queryLinkService(pagers,5);
		int all = adminBackstageServiceImpi.queryAllLinkService();
		model.addAttribute("dataList", link);
		model.addAttribute("pagerAll", all);
		model.addAttribute("pagerNow", pager);
		return "admin/friendLink.html";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: deleteLink
	  * @Description: �������ӣ� ɾ������  
	  * @return MessageInfo json��Ӧ��Ϣ�ṩ     
	  * @date: 2019��1��21�� ����5:54:06  
	  * @todo: TODO
	  */
	@RequestMapping(value= ("/delete_link.do"),method= {RequestMethod.POST})//ɾ������
	public @ResponseBody MessageInfo deleteLink(@RequestBody Link link) {
		MessageInfo messageInfo = getMessageInfoInstance();
		int i = adminBackstageServiceImpi.deleteLinkService(link);
		if (i == 1) {
			messageInfo = returnJsonMsgs(messageInfo, 0, "Ok!, ɾ���ɹ�", "100", true);
		}else {
			messageInfo = returnJsonMsgs(messageInfo, 1, "Fail!, �����ڸ�����", "404", false);
		}
		return messageInfo;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: alterLink
	  * @Description: ��������: �޸�����
	  * @return return_type     
	  * @date: 2019��1��21�� ����6:41:11  
	  * @todo: TODO
	  */
	@RequestMapping(value= ("/alter_link.do"),method= {RequestMethod.POST})
	public@ResponseBody MessageInfo alterLink(@RequestBody Link link) throws IOException {
		MessageInfo messageInfo = getMessageInfoInstance();
		int i = adminBackstageServiceImpi.alterLinkService(link);
		if (i == 1) {
			messageInfo = returnJsonMsgs(messageInfo, 0, "Ok!,�޸ĳɹ�", "100", true);
		}else {
			messageInfo = returnJsonMsgs(messageInfo, 1, "Fail!,�����ڸ�����", "404", false);
		}
		return messageInfo;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: queryAlterLink
	  * @Description: ��ѯ�޸�������Ϣ  
	  * @return List     
	  * @date: 2019��1��21�� ����7:19:31  
	  * @todo: TODO
	  */
	@RequestMapping(value= ("/query_AlterLink.do"),method= {RequestMethod.POST})
	public @ResponseBody List<Link> queryAlterLink(Integer number) throws IOException {
		return adminBackstageServiceImpi.queryOneLinkService(number);
	}
	
	
	
	/**  
	  * @user: Nikey 
	  * @MethodName: queryUser 
	  * @Description: ����ϵͳ --> �û�����    ��������ѯ�û� 
	  * @return String     
	  * @date: 2018��12��8�� ����5:42:34  
	  * @todo: TODO
	  */  
	@RequestMapping(value= ("/users"))
	public String queryUser (Integer pager, Model model) {
		int pagers = 0;
		if (pager == null || pager <= 0) {
			pager = 0;
		}else {
			pagers = pager-1;
		}
		List<Users> list = adminBackstageServiceImpi.queryUsersInfoByAllService(pagers, 5);
		int usersAll = adminBackstageServiceImpi.queryCountUserInfo();
		model.addAttribute("pagerAll", usersAll);
		model.addAttribute("pagerNow", pager);
		model.addAttribute("dataList", list);
		return "admin/users.html";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: queryUsers
	  * @Description: �û�������ѯ   
	  * @return String     
	  * @date: 2019��1��27�� ����3:03:43  
	  * @todo: TODO
	  */
	@RequestMapping(value="/user")
	public String queryUsers(String user, Integer pager, Model model) {
		int pagers = 0;
		int listSize;
		if (pager == null || pager <= 0) {
			pager = 0;
		}else {
			pagers = pager-1;
		}
		List<Users> list = adminBackstageServiceImpi.queryUsersService(pagers, 5, user);
		if (list == null) {
			listSize = 0; 
		}else {
			listSize = list.size();
		}
		model.addAttribute("pagerAll", listSize);
		model.addAttribute("pagerNow", pager);
		model.addAttribute("dataList", list);
		return "admin/users.html";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName:deleteUserByID
	  * @Description: �û����� -- ɾ���û�(֧�ֵ���������)   
	  * @return MessageInfo     
	  * @date: 2019��1��27�� ����3:52:21  
	  * @todo: TODO
	  */
	@RequestMapping(value= ("/delete_user.do"),method= {RequestMethod.POST})
	public@ResponseBody MessageInfo deleteUserByID(@RequestParam("usersList[]") ArrayList<Integer> users) {
		return adminBackstageServiceImpi.deleteUserByIDService(users);
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: queryAlterUsersByID
	  * @Description: �û�����--��ѯ�޸��û�   
	  * @return List<Users>     
	  * @date: 2019��1��27�� ����4:59:59  
	  * @todo: TODO
	  */
	@RequestMapping(value= ("/query_AlterUser.do"),method= {RequestMethod.POST})
	public @ResponseBody List<Users> queryAlterUsersByID(@RequestParam("usersList[]") ArrayList<Integer> id) {
		return adminBackstageServiceImpi.queryUsersService(0, 5, id.get(0).toString());
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName:alterUsersByID
	  * @Description: �û�����--�޸��û�   
	  * @return MessageInfo     
	  * @date: 2019��1��27�� ����4:59:59  
	  * @todo: TODO
	  */
	@RequestMapping(value= ("/alter_user.do"),method= {RequestMethod.POST})
	public @ResponseBody MessageInfo alterUsersByID(@RequestBody Users users) throws IOException {
		return adminBackstageServiceImpi.alterUserInfoByIdService(users);
	}
	
	
	
	/**
	 * ���͹���-->��Ϣ���� 
	 * 	���������
	 * 			1.����ҳ��������Ӧ������Ϣ�����õ�����ʾ��   ��Ϣ��ѯ�ӿ�
	 * 			2.�޸���Ϣ		��Ϣ�޸Ľӿ�
	 * @author Nikey
	 * @param
	 * */
	
	@RequestMapping(value= ("/alter_bloginfo.do"),method= {RequestMethod.POST})//�޸Ĳ�����Ϣ
	public String alterBlogInfo(Bloginfo bloginfo) {
		adminBackstageServiceImpi.alterBlogInfoService(bloginfo);
		return "redirect:updataBlog";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: updataBlogInfo
	  * @Description: ��ѯ��ʾ������Ϣҳ  
	  * @return String     
	  * @date: 2019��1��7�� ����9:39:02  
	  * @todo: TODO
	  */
	@RequestMapping(value= ("/updataBlog"))
	public String updataBlogInfo(Model model) {
		BlogInfoJoinTheme bTheme = adminBackstageServiceImpi.queryBlogInfoService();
		model.addAttribute("data", bTheme);
		return "admin/webinfo.html";
	}
	
	/**
	 * �������� -- ͷ���ϴ�
	 * @author Nikey
	 * @param HttpServletRequest ����ǰ�˵��ļ�
	 * @return String ��Ӧ����
	 * */
	@RequestMapping(value= ("/upload_imgHead.do"),method= {RequestMethod.POST})
	public String uploadHeadImg(MultipartFile meFile) {
		if (meFile != null) {
			try {
				String newFileName = (UUID.randomUUID()+meFile.getOriginalFilename()).replaceAll("-", "");
				String rootUrl = "G:";
				String url = "/temp/";
				meFile.transferTo(new File(rootUrl+url+newFileName));
				Bloginfo bloginfo = getBloginfoInstance();
				bloginfo.setBiHeadportrait(url+newFileName);
				adminBackstageServiceImpi.alterBlogInfoService(bloginfo);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:updataBlog";
	}
	
	
	/*
	 *  ���͹���--> �������    
	 * 				�������:
	 * 					1.�ϴ����⣺�ϴ��ļ��У�css��ʽ�ļ���ͼƬ��Դ��
	 * 					2.�޸�������Ϣ
	 * 					3.ɾ������
	 * */
	
	@SuppressWarnings("static-access")
	@RequestMapping("/upload_theme.do")//�ϴ�����
	public String UploadTheme(HttpServletRequest request,Model model){
		MultipartResolver cResolver = new CommonsMultipartResolver();
		if (cResolver.isMultipart(request)) {//�ж��ǲ���Multipart��ʽ������
			MultipartHttpServletRequest httpservletrequest = (MultipartHttpServletRequest) request;//���ļ���ʽ������װ��MultipartHttpServletRequest�����С�
			List<MultipartFile> list = httpservletrequest.getFiles("meFile");//ͨ�����ø÷���ȡ���ļ���list
			for (int i = 0; i < list.size(); i++) {
				try {
					list.get(i).transferTo(new File("G:/temp/"+list.get(i).getOriginalFilename()));
					ZipUtils.resolveZip("G:/temp/"+list.get(i).getOriginalFilename());
				} catch (IllegalStateException e) {
					e.printStackTrace();
					model.addAttribute("msg", "�ϴ�ʧ��");
					return "error";
				} catch (IOException e) {
					e.printStackTrace();
					model.addAttribute("msg", "�ϴ�ʧ��");
					return "error";
				}
			}
		}
		model.addAttribute("msg", "�ϴ��ɹ�");
		return "index";
	}
	
	@RequestMapping("/textUpload.do")
	public String TextUpload(String text,String title,Model model) {
		System.out.println("title: "+title+" "+"text: "+text);
		model.addAttribute("title", title);
		model.addAttribute("text", text);
		return "admin/showText.html";
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: install
	  * @Description: ���Ͱ�װ·��  
	  * @return {@link String}      
	  * @throws IOException 
	  * @throws FileNotFoundException 
	  * @date: 2019��3��26�� ����4:01:51  
	  * @todo: TODO
	  */
	@RequestMapping("/install")
	public String install() throws FileNotFoundException, IOException {
		PropertiesUtils propertiesUtils = new PropertiesUtils();
		propertiesUtils.isFile();
		return null;
	}
	
	/**
	 * ��������
	 * */
	static Bloginfo bloginfo;
	static MessageInfo messageInfo;
	
	/**
	 * @author Nikey
	 * @Description: ����Bloginfo
	 * */
	private synchronized static Bloginfo getBloginfoInstance() {
		if (bloginfo == null) {
			bloginfo = new Bloginfo();
		}
		return bloginfo;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName:getMessageInfoInstance
	  * @Description: ����MessageInfo  
	  * @return MessageInfo     
	  * @date: 2019��1��16�� ����4:41:03  
	  * @todo: TODO
	  */
	private synchronized static MessageInfo getMessageInfoInstance() {
		if (messageInfo == null) {
			messageInfo = new MessageInfo();
		}
		return messageInfo;
	}
	
	/**  
	  * @user: Nikey 
	  * @MethodName: returnOk
	  * @Description: ����json��Ӧ����Ϣ  
	  * @return MessageInfo     
	  * @date: 2019��1��21�� ����7:01:57  
	  * @todo: TODO
	  */
	private static MessageInfo returnJsonMsgs(MessageInfo messageInfo, int code, String msg, String status, boolean pass) {
		messageInfo.setCode(code);
		messageInfo.setMsg(msg);
		messageInfo.setStatus(status);
		messageInfo.setPass(pass);
		return messageInfo;
	}
	
	
}

