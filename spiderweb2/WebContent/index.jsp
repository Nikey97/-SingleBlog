<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Keywords" content="数据库取信息(网站关键字)">
<meta name="Description" content="数据库取信息(网站描述)">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>数据库取信息(网站名称)</title>
	<link rel="stylesheet" href="Css/bootstrap.min.css">
	<link rel="stylesheet" href="Css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="Css/main.css">
	<link rel="stylesheet" href="Css/normalize.css" type="text/css" />
	<script type="text/javascript" src="Js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="Js/bootstrap.min.js"></script>
	<script type="text/javascript" src="Js/main.js"></script>
	<script type="text/javascript" src="Js/collapse.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
	<div class="container-fluid searchDiv" id="SearchWarp">
		<!--  首页导航\搜索框   -->
		<nav class="navbar searchNav">
			<div class="container-fluid">
				<div class="navbar-header">
				  <button type="button" class="navbar-toggle collapsed sr-only" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				  </button>
				  <div class="spanNameDiv">
					<a href="index.jsp"><span class="spanName">SupSpider</span></a>
				  </div>
				</div>
				<div class="collapse navbar-collapse MobileMune" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav MobileMune_ul">
						<li><a href="#" class="buttonLogin">登录</a></li>
						<li><a href="#" class="buttonLogin">关于</a></li>
					</ul>
				</div>
			</div>
		</nav>
		
		
		<div class="col-md-12">
			<div class="col-md-6 div_center">
				<div class="Slogen_wrap">
					<h1>SupSpider</h1>
					<span>我们不生产资源,我们只是互联网资源的搬运工!</span>
					<span name="data">共收录了<mark>12890</mark>个资源</span>
				</div>
				<div class="div_search">
					<form action="#" method="post">
						<input type="text" class="div_searchInput" placeholder="请输入电影名|音乐名|资源关键字" name="SearchName" >
					</form>
				</div>
				<div class="div_HotSearch">
					<ul class="ul_normalize">
						<a href="#"><li><span class="icon_hot">头牌玩家</span></li></a>
						<a href="#"><li><span class="icon_hot">头牌玩家</span></li></a>
						<a href="#"><li><span class="icon_hot">头牌玩家</span></li></a>
						<a href="#"><li><span class="icon_hot">头牌玩家</span></li></a>
						<a href="#"><li><span class="icon_hot">头牌玩家</span></li></a>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid fleshDiv" id="flashDiv">
		<!--  最新资源分享框  -->
		<h2>最新资源</h2>
		<div class="share_Music col-md-4 col-sm-4">
			<div class="Music_context">
				<table class="table" id="app">
					<caption style="text-align: center;"><span class="Music_icon"></span></caption>
					<thead>
						<tr>
							<td><b>文件名</b></td>
							<td><b>入库时间</b></td>
							<td><b>来源</b></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>1天前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>{{ message }}</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一周前</td>
							<td>百度云</td>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="share_Movie col-md-4 col-sm-4">
			<div class="Movie_context">
				<table class="table">
					<caption style="text-align: center;"><span class="Movie_icon"></span></caption>
					<thead>
						<tr>
							<td><b>文件名</b></td>
							<td><b>入库时间</b></td>
							<td><b>来源</b></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>1天前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>3天前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一周前</td>
							<td>百度云</td>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="share_Other col-md-4 col-sm-4">
			<div class="Other_context">
				<table class="table">
					<caption style="text-align: center;"><span class="Other_icon"></span></caption>
					<thead>
						<tr>
							<td><b>文件名</b></td>
							<td><b>入库时间</b></td>
							<td><b>来源</b></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>1天前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>3天前</td>
							<td>百度云</td>
						</tr>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一周前</td>
							<td>百度云</td>
						<tr>
							<td><a href="#">头牌玩家</a></td>
							<td>一月前</td>
							<td>百度云</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="container-fluid Bottom_warp">
		<!--  底部栏 -->
		<span>Copyright © <a href="index.jsp" class="Bottom_warp_a">SupSpider资源搜索</a></span>
	</div>
	<div class="HideFloatWarp">
		<!-- 漂浮栏:反馈|返回顶部|关注官微|白色的导航栏 -->
		<a href="#SearchWarp" title="返回顶部">
			<div class="Float_Top">
				<span class="topicon">top</span>
			</div>
		</a>
		<a href="#" title="关注官微">
			<div class="Float_QeCode">
				<img src="Img/CGCode.png" />
			</div>
		</a>
		<a href="#" title="用户反馈">
			<div class="Float_feedback">
				<img src="Img/email.png" />
			</div>
		</a>
	</div>
</body>
</html>