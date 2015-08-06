<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>阶段任务</title>
<!-- <script src="js/xhr.js"></script> -->

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

<script type = "text/javascript">
	/**删除阶段任务**/
	function delPhaseJob(obj){
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
		xmlhttp.open("POST","delPhaseJob",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var param = "id=" + id ;
		xmlhttp.send(param);
	}
	/**修改阶段任务**/
	function modifyPhaseJob(obj){
		// 遍历该行td元素
		var trChilds = obj.parentNode.parentNode.getElementsByTagName("TD") ;
		for(var i = 0 ; i < 6 ; i ++){
			if(i == 0)continue ;
			// 获取单元格里的值
			var value = trChilds[i].innerHTML.trim() ;
			var form = document.createElement("input") ;
			form.setAttribute("type","text") ;
			form.setAttribute("value",value) ;
			trChilds[i].innerHTML = "" ;
			trChilds[i].appendChild(form) ;
		}
		obj.innerHTML = "提交" ;
		obj.setAttribute("onclick","modifySubmit(this)") ;
	}
	/**修改提交**/
	function modifySubmit(obj){
		// 获取参数
		var trChilds = obj.parentNode.parentNode.getElementsByTagName("TD") ;
		var id,desc,start_date,end_date,priority,target ;
		id = trChilds[0].innerHTML.trim() ;
		desc = trChilds[1].firstChild.value ;
		start_date = trChilds[2].firstChild.value ;
		end_date = trChilds[3].firstChild.value ;
		priority = trChilds[4].firstChild.value ;
		target = trChilds[5].firstChild.value ;
		
		var xmlhttp = createXMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			var responsedata = xmlhttp.responseText ;
			alert("服务器返回的结果为：" + responsedata);
			window.location.reload();
			
		}
		};
		xmlhttp.open("POST","modifyPhaseJob",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var param = "id=" + id + 
					"&desc="+desc+ 
					"&start_date="+start_date+ 
					"&end_date="+end_date+ 
					"&priority="+priority+ 
					"&target="+target ;
		xmlhttp.send(param);
	}
	/**添加阶段任务**/
	function addPhasejob(obj){
	
		/**创建表单元素*/
		var formdiv = document.createElement("div") ;
					formdiv.setAttribute("id","formdiv") ;
					var descInput = document.createElement("input") ;
		descInput.setAttribute("class","desc") ;
					descInput.setAttribute("type","text") ;
					var start_dateInput = document.createElement("input") ;
		start_dateInput.setAttribute("class","start_date") ;
					start_dateInput.setAttribute("type","text") ;
					var end_dateInput = document.createElement("input") ;
		end_dateInput.setAttribute("class","end_date") ;
					end_dateInput.setAttribute("type","text") ;
					var priorityInput = document.createElement("input") ;
		priorityInput.setAttribute("class","priority") ;
					priorityInput.setAttribute("type","text") ;
					var targetInput = document.createElement("input") ;
		targetInput.setAttribute("class","target") ;
					targetInput.setAttribute("type","text") ;
		var submit = document.createElement("button") ;
					submit.setAttribute("onclick","addPhasejobSubmit(this)") ;
		submit.innerHTML = "提交" ;
		/**创建表单元素结束*/
		var span  ;
					span = document.createElement("span") ;
					span.innerHTML = "desc" ;
					formdiv.appendChild(span.cloneNode(true)) ;
		formdiv.appendChild(descInput) ;
					formdiv.appendChild(document.createElement("br")) ;
					span = document.createElement("span") ;
					span.innerHTML = "start_date" ;
					formdiv.appendChild(span.cloneNode(true)) ;
		formdiv.appendChild(start_dateInput) ;
					formdiv.appendChild(document.createElement("br")) ;
					span = document.createElement("span") ;
					span.innerHTML = "end_date" ;
					formdiv.appendChild(span.cloneNode(true)) ;
		formdiv.appendChild(end_dateInput) ;
					formdiv.appendChild(document.createElement("br")) ;
					span = document.createElement("span") ;
					span.innerHTML = "priority" ;
					formdiv.appendChild(span.cloneNode(true)) ;
		formdiv.appendChild(priorityInput) ;
					formdiv.appendChild(document.createElement("br")) ;
					span = document.createElement("span") ;
					span.innerHTML = "target" ;
					formdiv.appendChild(span.cloneNode(true)) ;
		formdiv.appendChild(targetInput) ;
					formdiv.appendChild(document.createElement("br")) ;
		formdiv.appendChild(submit) ;
		
		document.getElementById("addphase").appendChild(formdiv) ;
	}
	/**提交阶段任务添加**/
	function addPhasejobSubmit(obj){
		var container = obj.parentNode ;
		var desc = container.getElementsByClassName("desc")[0].value ;
		var start_date = container.getElementsByClassName("start_date")[0].value ;
		var end_date = container.getElementsByClassName("end_date")[0].value ;
		var priority = container.getElementsByClassName("priority")[0].value ;
		var target = container.getElementsByClassName("target")[0].value ;
		
		var xmlhttp = createXMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			var responsedata = xmlhttp.responseText ;
			alert("服务器返回的结果为：" + responsedata);
			obj.parentNode.parentNode.removeChild(obj.parentNode) ;
		    window.location.reload() ;
		}
		};
		xmlhttp.open("POST","addPhaseJob",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var param = "desc="+desc+ 
					"&start_date="+start_date+ 
					"&end_date="+end_date+ 
					"&priority="+priority+ 
					"&target="+target ;
		xmlhttp.send(param);
	}
	/**查看进度**/
	function progressInfo(obj){
		// 获取阶段任务id
		var phaseId = obj.parentNode.parentNode.getElementsByTagName("TD")[0].innerHTML ;
		var xmlhttp = createXMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			var responsedata = eval(xmlhttp.responseText) ;
			if(responsedata.length == 0){
				alert("该阶段任务没有完成进度项！") ;
			}else{
				document.getElementById("processInfo").innerHTML = "" ;
				var header = String.format(
				"<tr><th>%1</th><th>%2</th><th>%3</th><th>%4</th></tr>","desc","start_date","end_date","target"
				) ;
				var content = "" ;
				for(var i = 0 , max = responsedata.length ; i < max ; i ++){
					content += 
					String.format("<tr><td>%1</td><td>%2</td><td>%3</td><td>%4</td></tr>",
					responsedata[i].desc , responsedata[i].start_date , responsedata[i].end_date , responsedata[i].target) ;
				}
				var processInfo = "<table>"+ header + content + "</table>" ;
				document.getElementById("processInfo").innerHTML = processInfo ;
				// 创建删除完成进度项的按钮
				var delDisplay = document.createElement("button") ;
				delDisplay.innerHTML = "关闭进度显示" ;
				delDisplay.setAttribute("onclick", "javascript:document.getElementById('processInfo').innerHTML = ''") ;
				document.getElementById("processInfo").appendChild(delDisplay) ;
			}
		}
		};
		xmlhttp.open("POST","lookProgress",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var param = "id=" + phaseId ;
		xmlhttp.send(param);
	}
	/**添加完成进度项**/
	function addProgressItem(obj){
		// 获取阶段任务id
		var phasejobId = obj.parentNode.parentNode.getElementsByTagName("TD")[0].innerHTML ;
		// 创建填写完成进度项的输入框
		var addProgressDiv = document.createElement("div") ;
			var input = document.createElement("input") ;
			
		var desc = input.cloneNode(true) ; desc.setAttribute("class", "desc") ;
		var start_date = input.cloneNode(true) ; start_date.setAttribute("class","start_date") ;
		var end_date = input.cloneNode(true) ; end_date.setAttribute("class" , "end_date") ;
		var target = input.cloneNode(true) ; target.setAttribute("class","target") ;
		var submit = document.createElement("button") ;
			submit.setAttribute("onclick","submitAddProgressItem(this , "+ phasejobId +")") ;
			submit.innerHTML = "添加" ;
		var span = document.createElement("span") ;
		var br = document.createElement("br") ;
		descSpan = span.cloneNode(true) ; descSpan.innerHTML = "desc" ;
		addProgressDiv.appendChild(descSpan) ; 
 		addProgressDiv.appendChild(desc) ;
 		addProgressDiv.appendChild(br.cloneNode(true)) ;
 		start_dateSpan = span.cloneNode(true) ; start_dateSpan.innerHTML = "start_date" ;
		addProgressDiv.appendChild(start_dateSpan) ; 
		addProgressDiv.appendChild(start_date) ;
		addProgressDiv.appendChild(br.cloneNode(true)) ;
		end_dateSpan = span.cloneNode(true) ; end_dateSpan.innerHTML = "end_date" ;
		addProgressDiv.appendChild(end_dateSpan) ; 
		addProgressDiv.appendChild(end_date) ;
		addProgressDiv.appendChild(br.cloneNode(true)) ;
		targetSpan = span.cloneNode(true) ; targetSpan.innerHTML = "target" ;
		addProgressDiv.appendChild(targetSpan) ; 
		addProgressDiv.appendChild(target) ;
		addProgressDiv.appendChild(br.cloneNode(true)) ;
		addProgressDiv.appendChild(submit) ;
		document.getElementById("addProcessDiv").appendChild(addProgressDiv) ;
	}
	/**提交添加完成进度项**/
	function submitAddProgressItem(obj , phasejobId){
		// 获取提交参数
		var container = obj.parentNode ;
		var desc = container.getElementsByClassName("desc")[0].value ;
		var start_date = container.getElementsByClassName("start_date")[0].value ;
		var end_date = container.getElementsByClassName("end_date")[0].value ;
		var target = container.getElementsByClassName("target")[0].value ;
		
		var xmlhttp = createXMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			var responsedata = xmlhttp.responseText ;
			alert("服务器返回的结果为：" + responsedata);
			obj.parentNode.parentNode.removeChild(obj.parentNode) ;
		    window.location.reload() ;
		}
		};
		xmlhttp.open("POST","addProgressItem",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var param = "phasejobId=" + phasejobId + 
					"&desc="+desc+ 
					"&start_date="+start_date+ 
					"&end_date="+end_date+ 
					"&target="+target ;
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


</head>

<body>
	<h2>阶段任务</h2>
	<br/>
	<c:if test="${empty phaseJobs}">
		<b>你暂时没有阶段任务哦！</b>
	</c:if>
	<table>
			<tr>
				<th>id</th>
				<th>desc</th>
				<th>start_date</th>
				<th>end_date</th>
				<th>priority</th>
				<th>target</th>
				<th></th>
			</tr>
			<c:forEach items="${phaseJobs}" var="item">
  			 	<tr>
  			 		<td>
  			 			<c:out value="${item.id}"/>
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.desc}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.start_date}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.end_date}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.priority}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.target}" />
  			 		</td>
  			 		<td>
  			 			<button type = "button" onclick = "progressInfo(this)" >查看进度</button>
  			 		</td>
  			 		<td>
  			 			<button type = "button" onclick = "delPhaseJob(this)" >删除阶段任务</button>
  			 		</td>
  			 		<td>
  			 			<button type = "button" onclick = "modifyPhaseJob(this)" >修改阶段任务</button>
  			 		</td>
  			 		<td>
  			 			<button type = "button" onclick = "addProgressItem(this)" >添加完成进度项</button>
  			 		</td>
  			 	</tr>
			</c:forEach>
			</table>
			<button onclick = "addPhasejob()">添加阶段任务</button>
			<div id = "addphase"></div>
			
			<div id = "processInfo" ></div>
			<div id = "addProcessDiv"></div>
</body>
</html>