<%@page import="java.sql.ResultSet"%>
<%@page import="cn.supspider.Utils.DBUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理</title>
        <link rel="stylesheet" href="../Css/ManageStyle.css" type="text/css" />
        <link rel="stylesheet" href="../Css/normalize.css" type="text/css" />
        <script type="text/javascript" src="../Js/jquery-3.2.1.min.js" ></script>
        <script type="text/javascript" src="../Js/backmain.js" ></script>
        <script type="text/javascript" src="../Js/jquery.cookie.js"></script>
    </head>
    <!--
    	作者：13044926957@163.com
    	时间：2018-02-27
    	描述：该页面用于后台系统呈现:
    			主要功能:可以用于
    							1.启动和暂停spider进程
    							2.查询删除用户信息
    							3.网站主体信息查询修改
    							4.用户反馈信息查询
    							5.首页最新资源管理
    							6.广告内容的管理
    -->
    <body>
    <div id="head">
    <!--描述：头部-->
		<section>
			<h1>SupSpider后台管理 v1.0</h1>
		</section>
		<div>
			<%! 
				DBUtil dbUtil=new DBUtil(); 
			%>
			<%
				String sql="select * from user_feedback where f_look=0;";
				ResultSet set = dbUtil.DBQuery(sql);
				int count=0;
				while(set.next()){
					count++;
				}
			%>
			<div class="leftTip">
				<a href="#" class="a_normailze" title="反馈"><span class="icoTip" id="tipdate"><span class="tipData">(<%=count %>)</span></span></a>
			</div>
			<div class="rightExit">
				<a href="#" title="注销"><span class="icoExit" id="Exit"></span></a>
			</div>
		</div>
    </div>
    <div id="body">
    <!--描述：主体栏-->
    	<div class="leftwarp">
    	<!--左边导航栏-->
    		<div>
				<ul>
					<a href="javascript:void(0);"><li>首页<i class="icon"></i></li></a>
					<a href="javascript:void(0);"><li>网站信息管理<i class="icon"></i></li></a>
					<a href="javascript:void(0);"><li>用户信息管理<i class="icon"></i></li></a>
					<a href="javascript:void(0);"><li>创收信息管理<i class="icon"></i></li></a>
					<a href="javascript:void(0);"><li>用户反馈管理<i class="icon"></i></li></a>
					<a href="javascript:void(0);"><li>邮件收发管理<i class="icon"></i></li></a>
				</ul>    		
    		</div>
    	</div>
    	<iframe class="rightwarp" frameborder="0" scrolling="no">
    	<!--右边呈现栏:用于挨个呈现管理页面-->
    	</iframe>
    </div>
    <%
    	if(session.getAttribute("username")==null){
    		//当访问该页面不存在用户信息,就自动转向登录页面
    		out.print("<script>alert('您未曾登录,系统正在转跳!');</script>");
	 		response.setHeader("refresh", "1;default.jsp");
    	}
    %>
 	</body>
</html>