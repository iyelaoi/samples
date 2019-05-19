<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" session="true" %>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>控制门列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="refresh" content="10">

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
  .asd {
	color: #993333;
}
  .asd {
	font-size: 24px;
	font-weight: bold;
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
	var str = date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日" + "星期" + week + " " + date.getHours() + "时" + date.getMinutes() + "分" + date.getMinutes() + "分" + date.getSeconds() + "秒";
	//将格式化好的时间显示在叶面的span标签体中
	var span = document.getElementById("mydate");
	//给span标签设置标签体内容
	span.innerHTML = str.fontcolor("red");
	//调用定时函数
	window.setTimeout("showDate()", 1000);
	
}
	function heart(obj){
		var str = document.getElementById(obj.id+"text").value;
		var url = "${pageContext.request.contextPath}/gateControl?gateName="+obj.id+"&heart="+str;
		document.getElementById(obj.id).setAttribute("href",url);
		
	
	
	}
</script>

 </head>
  
  <body bgcolor="#00CCFF" onLoad="showDate()">
  
 
  <table width="90%" border="0" align="center">
    <tr class="cc">
      <td class="zz">当前系统时间：<span id="mydate"></span></td>
      <td  class="yy">当前用户:<span>${loginInfo.username}&nbsp;<a href="${pageContext.request.contextPath }/login">注销</a></span></td>
    </tr>
  </table>
  <p>&nbsp; </p>
  <p class="aa"><strong>控制门列表</strong></p>
    <table border="2" width="80%" align="center" cellpadding="5" cellspacing="0" >
  		<tr bgcolor="#00FFFF" class="asd">
  			<td>序号</td>
  			<td>门编号</td>
  			<td>门名称</td>
  			<td>门地址</td>
  			<td>当前状态</td>
  			<td>历史纪录</td>
  			<td>门控制</td>
  			<td>心跳控制</td>
  			
  		</tr>
  		<!-- 迭代数据 -->
  		<c:choose>
  			<c:when test="${not empty requestScope.pageBean.pageData}">
  				<c:forEach var="gate" items="${requestScope.pageBean.pageData}" varStatus="vs">
  					<tr bgcolor="#CCFFFF">
  						<td rowspan="2">${vs.count }</td>
  						<td rowspan="2">${gate.id }</td>
  						<td rowspan="2">${gate.name }</td>
  						<td rowspan="2">${gate.address }</td>
  						<td rowspan="2">  
  						<c:set var="status" value="${gate.status}"></c:set>
						    <c:choose>
						    	<c:when test="${status == 0}">
						    		关闭
						    	</c:when>
						    	<c:when test="${status == 1}">
						    		打开
						    	</c:when>
						    	<c:when test="${status == 2}">
						    		挂起
						    	</c:when>
						    	<c:when test="${status == 3}">
						    		掉线
						    	</c:when>
						    </c:choose>
</td>
  						<td rowspan="2"><a href="${pageContext.request.contextPath }/list?gateName=${gate.name}">详情</a></td>
  						<td rowspan="2"><a href="${pageContext.request.contextPath }/gateControl?gateName=${gate.name}&ctr=${gate.status}">
  						<c:set var="status" value="${gate.status}"></c:set>
						    <c:choose>
						    	<c:when test="${status == 0}">
						    		<input type="button" value="打开">
						    	</c:when>
						    	<c:when test="${status == 1}">
						    		<input type="button" value="关闭">
						    	</c:when>
						    	<c:when test="${status == 2}">
						    		<input type="button" value="唤醒">
						    	</c:when>
						    	<c:when test="${status == 3}">
						    		<input type="button" value="重启">
						    	</c:when>
						    	<c:otherwise>
						    		异常  ${status}
						    	</c:otherwise>
						    </c:choose>
  						
  						</a>
  						</td>
  						<td>当前门端心跳： 
  						<c:set var="hn" value="${gate.name}"></c:set>
  							<c:choose>
						    	<c:when test="${hn == 'gate1'}">
						    		${gate1heart}
						    	</c:when>
						    	<c:when test="${hn == 'gate2'}">
						    		${gate2heart}
						    	</c:when>
						    	<c:when test="${hn == 'gate3'}">
						    		${gate3heart}
						    	</c:when>
						    	<c:when test="${hn == 'gate4'}">
						    		${gate4heart}
						    	</c:when>
						    	<c:otherwise>
						    		异常  ${status}
						    	</c:otherwise>
						    </c:choose>
  						 ms</td>
  					</tr>
  					<tr bgcolor="#CCFFFF">
  					  <td><input id="${gate.name}text" type="text"/>ms<a href="#" id="${gate.name }" onclick="heart(this)"><input type="button" value="设置" /></a></td>
				  </tr>
  				</c:forEach>
  			</c:when>
  			<c:otherwise>
  				<tr bgcolor="#CCFFFF">
  					<td colspan="7">对不起，没有你要找的数据</td>
  				</tr>
  			</c:otherwise>
  		</c:choose>
  		
  		<tr bgcolor="#CCFFFF">
  			<td colspan="7" align="center">
  				当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
  				
  				<a href="${pageContext.request.contextPath }/gates?currentPage=1">首页</a>
  				<a href="${pageContext.request.contextPath }/gates?currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>
  				<a href="${pageContext.request.contextPath }/gates?currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>
  				<a href="${pageContext.request.contextPath }/gates?currentPage=${requestScope.pageBean.totalPage}">末页</a>
  			</td>
  		</tr>
  		
  	</table>
  </body>
</html>
