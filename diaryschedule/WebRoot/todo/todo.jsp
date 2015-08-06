<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>待办事项</title>
</head>
<script language="javascript">
String.prototype.trim=function(){
   return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim=function(){
   return this.replace(/(^\s*)/g,"");
}
String.prototype.rtrim=function(){
    return this.replace(/(\s*$)/g,"");
}
String.format = function(str) {
	var args = arguments, re = new RegExp("%([1-" + args.length + "])", "g");
	return String(str).replace(re,
	function($1, $2) {
	return args[$2];
	}
	);
};
</script>

<script>
	/**修改待办事项**/
	function modifyTodo(obj){
		// 遍历该行td元素
		var trChilds = obj.parentNode.parentNode.getElementsByTagName("TD") ;
		for(var i = 0 ; i < 4 ; i ++){
			if(i == 0)continue ;
			// 获取单元格里的值
			var value = trChilds[i].innerHTML.trim() ;
			var form = document.createElement("input") ;
			form.setAttribute("type","text") ;
			form.setAttribute("value",value) ;
			trChilds[i].innerHTML = "" ;
			trChilds[i].appendChild(form) ;
		}
		obj.innerHTML = "提交修改" ;
		obj.setAttribute("onclick","modifySubmit(this)") ;
	}
	/**提交待办事项修改**/
	function modifySubmit(obj){
		// 获取参数
		var trChilds = obj.parentNode.parentNode.getElementsByTagName("TD") ;
		var id,desc,end_date,priority ;
		id = trChilds[0].innerHTML.trim() ;
		desc = trChilds[1].firstChild.value ;
		end_date = trChilds[2].firstChild.value ;
		priority = trChilds[3].firstChild.value ;
		
		var xmlhttp = createXMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			var responsedata = xmlhttp.responseText ;
			alert("服务器返回的结果为：" + responsedata);
			window.location.reload();
			
		}
		};
		xmlhttp.open("POST","modifyTodo",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var param = "id=" + id + 
					"&desc="+desc+ 
					"&end_date="+end_date+ 
					"&priority="+priority ;
		xmlhttp.send(param);
	}
	function delTodo(obj){
		/* tr下的第一个元素竟然是一个text，不是td */
		var id = obj.parentNode.parentNode.childNodes[1].innerHTML ;
		var xmlhttp = createXMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			var responsedata = xmlhttp.responseText ;
			alert("服务器返回的结果为：" + responsedata);
			window.location.reload();
		}
		};
		xmlhttp.open("POST","delTodo",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var param = "id=" + id ;
		xmlhttp.send(param);
	}
	/**添加待办事项**/
	function addTodo(obj){

		/**创建表单元素*/
		var formdiv = document.createElement("div") ;
					formdiv.setAttribute("id","formdiv") ;
					var descInput = document.createElement("input") ;
		descInput.setAttribute("class","desc") ;
					descInput.setAttribute("type","text") ;
					var end_dateInput = document.createElement("input") ;
		end_dateInput.setAttribute("class","end_date") ;
					end_dateInput.setAttribute("type","text") ;
					var priorityInput = document.createElement("input") ;
		priorityInput.setAttribute("class","priority") ;
					priorityInput.setAttribute("type","text") ;
		var submit = document.createElement("button") ;
					submit.setAttribute("onclick","addTodoSubmit(this)") ;
		submit.innerHTML = "提交" ;
		/**创建表单元素结束*/
		var span  ;
					span = document.createElement("span") ;
					span.innerHTML = "desc" ;
					formdiv.appendChild(span.cloneNode(true)) ;
		formdiv.appendChild(descInput) ;
					formdiv.appendChild(document.createElement("br")) ;
					span.innerHTML = "end_date" ;
					formdiv.appendChild(span.cloneNode(true)) ;
		formdiv.appendChild(end_dateInput) ;
					formdiv.appendChild(document.createElement("br")) ;
					span.innerHTML = "priority" ;
					formdiv.appendChild(span.cloneNode(true)) ;
		formdiv.appendChild(priorityInput) ;
					formdiv.appendChild(document.createElement("br")) ;
		formdiv.appendChild(submit) ;
		
		document.getElementById("addTodo").appendChild(formdiv) ;
		
	}
	/**添加待办事项提交**/
	function addTodoSubmit(obj){
		var container = obj.parentNode ;
		var desc = container.getElementsByClassName("desc")[0].value ;
		var end_date = container.getElementsByClassName("end_date")[0].value ;
		var priority = container.getElementsByClassName("priority")[0].value ;
		
		var xmlhttp = createXMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			var responsedata = xmlhttp.responseText ;
			alert("服务器返回的结果为：" + responsedata);
			obj.parentNode.parentNode.removeChild(obj.parentNode) ;
		    window.location.reload() ;
		}
		};
		xmlhttp.open("POST","addTodo",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var param = "desc="+desc+ 
					"&end_date="+end_date+ 
					"&priority="+priority ;
		xmlhttp.send(param);
	}
	function createXMLHttpRequest() {
		var xmlhttp ;
		if (window.XMLHttpRequest)
	    	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  		xmlhttp=new XMLHttpRequest();
	  		return xmlhttp ;
	   	 }
		else
	 	 {// code for IE6, IE5
	  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  		return xmlhttp ;
	  	 }
	}
</script>
<body>
	<h2>待办事项</h2>
	<c:if test="${empty todoList}">
		<b>你暂时没有待办事项哦！</b>
	</c:if>
	<table>
			<tr>
				<th>id</th>
				<th>desc</th>
				<th>end_date</th>
				<th>priority</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${todoList}" var="item">
  			 	<tr>
  			 		<td>
  			 			<c:out value="${item.id}"/>
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.desc}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.end_date}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.priority}" />
  			 		</td>
  			 		<td>
  			 			<button type = "button" onclick = "modifyTodo(this)" >修改待办事项</button>
  			 		</td>
  			 		<td>
  			 			<button type = "button" onclick = "delTodo(this)" >删除待办事项</button>
  			 		</td>
  			 	</tr>
			</c:forEach>
			</table>
			<button onclick = "addTodo()">添加待办事项</button>
			<div id = "addTodo"></div>
</body>
</html>