<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "diaryschedule.dataBean.UserLoginInfo" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<script type="text/javascript" language="javascript">   
	function iFrameHeight() {   
		var ifm= document.getElementById("iframepage");   
		var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
		if(ifm != null && subWeb != null) {
		   ifm.height = subWeb.body.scrollHeight;
		   ifm.width = subWeb.body.scrollWidth;
		}   
	}   
</script>
  <head>
    
    <title>home page </title>
  </head>
  <h2>个人日程管理</h2>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" align = "center">
	  <tr>
	    <td width="11%">　<a href = "lookPhaseJob" target="main">阶段任务管理</a></td>
	    <td width="17%">　<a href = "lookTodo" target="main">待办事项管理</a></td>
	    <td width="13%"> 　<a href = "lookDiary" target="main">日记管理</a></td>
	    <td width="45%">&nbsp;</td>
	    <td width="14%">欢迎   <span id = "userName">${sessionScope.user.nickname}</span> 登陆</td>
	  </tr>
    </table>
	<iframe name = "main" width="100%" frameborder="0"  id="iframepage" name="iframepage" scrolling="yes" width="100%" onLoad="iFrameHeight()" ></iframe>
  <body>
  </body>
</html>
