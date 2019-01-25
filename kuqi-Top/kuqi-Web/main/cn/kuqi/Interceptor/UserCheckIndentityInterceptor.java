package cn.kuqi.Interceptor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.kuqi.Annotation.CheckUserIdentity;

public class UserCheckIndentityInterceptor implements HandlerInterceptor{
	
	/**
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��4������9:24:07
	 * �������ܣ�����һ�еĽӿڣ�����Ǹ��ӿڼ�����Ӧ��ע�⡣������Ӧ�Ĳ���
	 * ������ע���ڴ�����ӳ����֮��������ִ��֮ǰִ�и÷���
	 * @version
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean pass = true;
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			CheckUserIdentity checkUserIdentity = handlerMethod.getMethod().getAnnotation(CheckUserIdentity.class);
			if (checkUserIdentity != null) {
				System.out.println("ע��");
				HttpSession session = request.getSession();
				String username = (String) session.getAttribute("account");
				System.out.println("��⵽�û���"+username);
				if (username == null) {
					pass = false;
					response.sendRedirect(request.getContextPath()+"/admin/login");
				}else {
					pass = true;
				}
			}else {
				pass=true;
			}
		}
		return pass;
	}
	
	/**
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��4������9:24:07
	 * �������ܣ�����һ�еĽӿڣ�����Ǹ��ӿڼ�����Ӧ��ע�⡣������Ӧ�Ĳ���
	 * ������ע����������ִ��֮�󷵻���ͼ֮ǰִ�и÷���
	 * @version
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws IOException, ServletException{
	}
	
	/**
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��4������9:24:07
	 * �������ܣ�����һ�еĽӿڣ�����Ǹ��ӿڼ�����Ӧ��ע�⡣������Ӧ�Ĳ���
	 * ������ע���ڷ�����ͼ֮��ִ������������
	 * @version
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
