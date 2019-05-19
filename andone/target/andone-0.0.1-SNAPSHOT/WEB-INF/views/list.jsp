<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" session="true" %>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>分页查询门记录</title>
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
      <td  class="yy">当前用户:<span>${loginInfo.username}<a href="${pageContext.request.contextPath }/login">注销</a></span></td>
    </tr>
  </table>
  <p>&nbsp; </p>
  <p class="aa"><strong>${gateName}门状态记录</strong></p>
  <table border="2" width="80%" align="center" cellpadding="5" cellspacing="0">
  		<tr bgcolor="#CC33CC" class="bb">
  			<td>序号</td>
  			<td>编号</td>
  			<td>开始时间</td>
  			<td>结束时间</td>
  			<td>门状态</td>
  		</tr>
  		<!-- 迭代数据 -->
  		<c:choose>
  			<c:when test="${not empty requestScope.pageBean.pageData}">
  				<c:forEach var="gate" items="${requestScope.pageBean.pageData}" varStatus="vs">
  					<tr bgcolor="#CC99FF">
  						<td>${vs.count }</td>
  						<td>${gate.id }</td>
  						<td>${gate.st }</td>
  						<td>${gate.et }</td>
  						<td>${gate.status }</td>
  					</tr>
  				</c:forEach>
  			</c:when>
  			<c:otherwise>
  				<tr bgcolor="#FF0000">
  					<td colspan="5" class="cc">对不起，没有你要找的数据</td>
  				</tr>
  			</c:otherwise>
  		</c:choose>
  		
  		<tr bgcolor="#00FFFF">
  			<td colspan="5" align="center">
  				当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
  				
  				<a href="${pageContext.request.contextPath }/list?currentPage=1&gateName=${gateName}">首页</a>
  				<a href="${pageContext.request.contextPath }/list?currentPage=${requestScope.pageBean.currentPage-1}&gateName=${gateName}">上一页 </a>
  				<a href="${pageContext.request.contextPath }/list?currentPage=${requestScope.pageBean.currentPage+1}&gateName=${gateName}">下一页 </a>
  				<a href="${pageContext.request.contextPath }/list?currentPage=${requestScope.pageBean.totalPage}&gateName=${gateName}">末页</a>
  			</td>
  		</tr>
  		
  	</table>
  <p>&nbsp;</p>
  <table width="80%" border="0" align="center">
    <tr>
      <td width="26%">&nbsp;</td>
      <td width="48%" align="center"><span class="zzz"><a href="${pageContext.request.contextPath }/gates" class="zzz">
      <input name="button" type="button" class="bb" id="button" value="返回到主界面">
      </a></span></td>
      <td width="26%"><a href="${pageContext.request.contextPath }/index?gateName=${gateName}" class="zzzzz"><input type="button" name="button2" id="button2" value="刷新"></a></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</body>
</html>









