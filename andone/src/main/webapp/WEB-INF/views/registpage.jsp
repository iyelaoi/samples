<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style type="text/css">
  .aa {
	text-align: center;
	font-size: 36px;
}
  .bb {
	font-size: 16px;
}
  .bb {
	font-weight: bold;
}
  .bb td {
	font-size: 18px;
}
  .bb {
	text-align: center;
}
  .bb td {
	font-size: 24px;
}
  .cc {
	color: #FF0;
	font-weight: bold;
	text-align: center;
}
  .ddd {
	text-align: right;
}
  .zz {
	text-align: left;
}
  .yy {
	text-align: right;
}
  .cc {
	font-weight: bold;
	color: #003;
}
  .zzz {
}
  #button2 {
	text-align: center;
}
  .zzzzz {
	text-align: left;
}
  .zzz {
	text-align: center;
}
  .qwe {
	text-align: center;
}
  .zxc {
	text-align: center;
}
  .sdasd {
	font-size: 36px;
	font-weight: bold;
	color: #F00;
	text-align: center;
}
  .qwe {
	text-align: center;
}
  </style>
      <script type="text/javascript"> 
    function showDate() {
	//获取系统时间
	var date = new Date();
	//获取年 月 日时分秒
	var week = date.getDay();
	switch(week){
		case 0: week="天";break;
		case 1: week="一";break;
		case 2: week="二";break;
		case 3: week="三";break;
		case 4: week="四";break;
		case 5: week="五";break;
		case 6: week="六";break;
		default: week = "错误" ;break;
		
		}
	var str = date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日" + "星期" + week + " " + date.getHours() + "时" + date.getMinutes() + "分" + date.getMinutes() + "分" + date.getSeconds() + "秒"
	//将格式化好的时间显示在叶面的span标签体中
	var span = document.getElementById("mydate");
	//给span标签设置标签体内容
	span.innerHTML = str.fontcolor("red");
	//调用定时函数
	window.setTimeout("showDate()", 1000);
}
</script>
  </head>
  
  <body bgcolor="#00CCFF" onLoad="showDate()">
   <table width="90%"border="0" align="center">
    <tr class="cc">
      <td class="zz">当前系统时间：<span id="mydate"></span></td>
      <td  class="yy">当前用户:<span>${loginInfo.username}&nbsp;<a href="${pageContext.request.contextPath }/loginpage">注销</a></span></td>
    </tr>
  </table>
  <p>&nbsp; </p>
  <p class="aa"><strong>注册界面</strong></p>
  	<form name="frm1" action="${pageContext.request.contextPath }/regist" method="post" >
  		<table width="30%" height="160" border="1" align="center">
  			<tr bgcolor="#00FFFF">
  				<td class="bb">用户名</td>
  				<td class="zxc">
  					<input type="text" name="username" class="bb"/>
  					${requestScope.registResult } <!-- 如果用户名存在注册失败，给用户提示 -->
  				</td>
  			</tr>
  			<tr bgcolor="#00FFFF">
  				<td class="bb">密码</td>
  				<td class="zxc"><input type="password" name="password" class="bb"/></td>
  			</tr>
  			<tr bgcolor="#00FFFF">
  				<td colspan="2" class="zxc">
  					<input type="submit" value="点击注册" class="bb">
  				</td>
  			</tr>
  		</table>
  	
  	</form>
    <p>&nbsp;</p>
    <table width="80%" border="0" align="center">
  <tr>
    <td class="qwe"><span class="sdasd">欢迎进入门禁管理系统</span></td>
  </tr>
</table>
  </body>
</html>
