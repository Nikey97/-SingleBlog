package cn.kuqi.Interceptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.kuqi.Annotation.IsInstall;
import test.PropertyTest;

public class IsInstallInterceptor implements HandlerInterceptor{
	
	private static final Logger logger = Logger.getLogger(IsInstallInterceptor.class); 
	
	/**
	 *       ִ�нӿ��ж�/kuqi-Dao/src/main/resources/db.properties�Ƿ�Ϊ��
	* 
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��15�� ����3:53:54  
	 * �������ܣ�����һ�еĽӿڣ�����Ǹ��ӿڼ�����Ӧ��ע�⡣������Ӧ�Ĳ���
	 * ������ע���ڴ�����ӳ����֮��������ִ��֮ǰִ�и÷���
	 * @version
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean pass = true;
		Properties properties = new Properties();
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			IsInstall isInstall = handlerMethod.getMethod().getAnnotation(IsInstall.class);
			if (isInstall != null) {
				ClassLoader classLoader = IsInstallInterceptor.class.getClassLoader();
				InputStream is = classLoader.getResourceAsStream("db.properties");
				properties.load(is);
				String user = properties.getProperty("jdbc.user");
				String psw = properties.getProperty("jdbc.psw");
				if (user == null && psw == null) {
					pass = false;
					response.sendRedirect("");//�ض��򵽰�װҳ��
				}
			}
		}
		return pass;
	}
	
	/**
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��15�� ����3:53:54  
	 * �������ܣ�����һ�еĽӿڣ�����Ǹ��ӿڼ�����Ӧ��ע�⡣������Ӧ�Ĳ���
	 * ������ע����������ִ��֮�󷵻���ͼ֮ǰִ�и÷���
	 * @version
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws IOException, ServletException{
	}
	
	/**
	 * �����ˣ�Nikey
	 * ����ʱ�䣺2018��11��15�� ����3:53:54
	 * �������ܣ�����һ�еĽӿڣ�����Ǹ��ӿڼ�����Ӧ��ע�⡣������Ӧ�Ĳ���
	 * ������ע���ڷ�����ͼ֮��ִ������������
	 * @version
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
