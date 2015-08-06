<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>日记管理</title>
</head>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script>
<script language="javascript">
/**扩展字符串格式化工具**/
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
<body>
<h2>日记管理</h2>
	<div id = "diaryList">
	<c:if test="${empty diary}">
		<b>你暂时没有日记哦！</b>
	</c:if>
	<table>
			<tr>
				<th>id</th>
				<th>title</th>
				<th>date</th>
				<th>mood</th>
				<th>weather</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${diary}" var="item">
  			 	<tr>
  			 		<td>
  			 			<c:out value="${item.id}"/>
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.title}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.date}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.mood}" />
  			 		</td>
  			 		<td>
  			 			<c:out value = "${item.weather}" />
  			 		</td>
  			 		<td style="display: none;">
  			 			<c:out value = "${item.content}" />
  			 		</td>
  			 		<td>
  			 			<button onclick = "detail(this.parentNode.parentNode.getElementsByTagName('td')[5].innerHTML)">查看日记</button>
  			 		</td>
  			 		<td>
  			 			<button type = "button" onclick = "delDiary(this)" >删除日志</button>
  			 		</td>
  			 		<td>
  			 			<button type = "button" onclick = "modifyDiary(this)" >修改日志</button>
  			 		</td>
  			 	</tr>
			</c:forEach>
		</table>
	</div>
			<button onclick = "addDiary(this)">添加日志</button>
			
			
			
			<div id = "addDiary" >
			
			<span>标题： </span><input type = "text" id = "title" />
				
				<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
				<script type="text/javascript">
       			 var ue = UE.getEditor('editor');
    		    </script>
    		    
    		    <span>选择心情： </span>
    		    <select id = "mood">
    		    	<option>---选择心情---</option>
    		    	<option value = "高兴">高兴</option>
    		    	<option value = "忧伤">忧伤</option>
    		    	<option value = "寂寞">寂寞</option>
    		    	<option value = "意气风发">意气风发</option>
    		    	<option value = "郁郁寡欢">郁郁寡欢</option>
    		    </select>
    		    <span>选择天气： </span>
    		    <select id = "weather">
    		    	<option>---选择天气---</option>
    		    	<option value = "阴">阴</option>
    		    	<option value = "晴">晴</option>
    		    	<option value = "晴转多云">晴转多云</option>
    		    	<option value = "雾">雾</option>
    		    	<option value = "阴">雨夹雪</option>
    		    	<option value = "阴">雷阵雨</option>
    		    </select>
    		    <button onclick = "submitAddDiary()" id = "submitModify">提交日志</button>
			</div>
<script type = "text/javascript">
	/**查看日记**/
	function detail( diary ){
		/*在这里获取的diaryId包含格式*/
		console.log(diary) ;
		window.open(diary) ;
	}
	/**删除日记**/
	function delDiary(obj ){
	var diaryId = obj.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML.trim() ;
	var htmlUrl = obj.parentNode.parentNode.getElementsByTagName("td")[5].innerHTML.trim() ;
		 //向addDiary发起一个异步的Ajax GET请求, 请求超时时间为10s， 请求完成后执行相应的回调。
				 UE.ajax.request( 'delDiary', {
				
				     //请求方法。可选值： 'GET', 'POST'，默认值是'POST'
				     method: 'POST',
				
				     //超时时间。 默认为5000， 单位是ms
				     timeout: 10000,
				
				     //是否是异步请求。 true为异步请求， false为同步请求
				     async: true,
				
				     //请求携带的数据。如果请求为GET请求， data会经过stringify后附加到请求url之后。
				     data: {
				     	 id : diaryId ,
				     	 htmlUrl : htmlUrl
				     },
				
				     //请求成功后的回调， 该回调接受当前的XMLHttpRequest对象作为参数。
				     onsuccess: function ( xhr ) {
				         alert(xhr.responseText) ;
				     },
				
				     //请求失败或者超时后的回调。
				     onerror: function ( xhr ) {
				          alert( 'Ajax请求失败' );
				     }
				 } );
	}
	/**修改日记**/
	function modifyDiary(obj){
		var id =  obj.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML.trim() ;
		var title = obj.parentNode.parentNode.getElementsByTagName("td")[1].innerHTML.trim() ;
		var mood = obj.parentNode.parentNode.getElementsByTagName("td")[3].innerHTML.trim() ;
		var weather = obj.parentNode.parentNode.getElementsByTagName("td")[4].innerHTML.trim() ;
		var htmlUrl = obj.parentNode.parentNode.getElementsByTagName("td")[5].innerHTML.trim() ;
		
		 //向addDiary发起一个异步的Ajax GET请求, 请求超时时间为10s， 请求完成后执行相应的回调。
				 UE.ajax.request( 'getDiaryContent', {
				
				     //请求方法。可选值： 'GET', 'POST'，默认值是'POST'
				     method: 'POST',
				
				     //超时时间。 默认为5000， 单位是ms
				     timeout: 10000,
				
				     //是否是异步请求。 true为异步请求， false为同步请求
				     async: true,
				
				     //请求携带的数据。如果请求为GET请求， data会经过stringify后附加到请求url之后。
				     data: {
				     	 htmlUrl : htmlUrl
				     },
				
				     //请求成功后的回调， 该回调接受当前的XMLHttpRequest对象作为参数。
				     onsuccess: function ( xhr ) {
				     	document.getElementById('diaryList').style.visibility="hidden" ;
						document.getElementById('addDiary').style.visibility="visible" ; 
						document.getElementById("title").value = title  ;
						document.getElementById("mood").value = mood  ;
						document.getElementById("weather").value = weather  ;
						UE.getEditor('editor').execCommand('insertHtml', xhr.responseText) ;
						document.getElementById("submitModify").setAttribute("onclick", "submitModify()") ;
						// diaryId 和 HtmlUrl 的值放进隐藏表单中，方便submitModify()方法获取
						var diaryIdInput = document.createElement("input") ;
						diaryIdInput.setAttribute("value", id) ;
						diaryIdInput.setAttribute("id", "diaryId") ;
						diaryIdInput.style.display="none" ;
						
						var htmlUrlInput = document.createElement("input") ;
						htmlUrlInput.setAttribute("value", htmlUrl) ;
						htmlUrlInput.setAttribute("id", "htmlUrl") ;
						htmlUrlInput.style.display="none" ;
						
						document.body.appendChild(diaryIdInput) ;
						document.body.appendChild(htmlUrlInput) ;
						document.getElementById("submitModify").innerHTML = "提交修改" ;
				     },
				
				     //请求失败或者超时后的回调。
				     onerror: function ( xhr ) {
				          alert( 'Ajax请求失败' );
				     }
				 } );
				 
	}
	/**提交修改**/
	function submitModify(){
		var id = document.getElementById("diaryId").value.trim() ;
		var htmlUrl = document.getElementById("htmlUrl").value.trim() ;
		var title = document.getElementById("title").value.trim() ;
		var mood = document.getElementById("mood").value.trim() ;
		var weather = document.getElementById("weather").value.trim() ;
			 //向addDiary发起一个异步的Ajax GET请求, 请求超时时间为10s， 请求完成后执行相应的回调。
				 UE.ajax.request( 'modifyDiary', {
				
				     //请求方法。可选值： 'GET', 'POST'，默认值是'POST'
				     method: 'POST',
				
				     //超时时间。 默认为5000， 单位是ms
				     timeout: 10000,
				
				     //是否是异步请求。 true为异步请求， false为同步请求
				     async: true,
				
				     //请求携带的数据。如果请求为GET请求， data会经过stringify后附加到请求url之后。
				     data: {
				     	 id : id ,
				     	 title : title ,
				     	 mood : mood ,
				     	 weather : weather ,
				         contentHtml : ue.getAllHtml(),
				         htmlUrl : htmlUrl 
				     },
				
				     //请求成功后的回调， 该回调接受当前的XMLHttpRequest对象作为参数。
				     onsuccess: function ( xhr ) {
				         alert(xhr.responseText) ;
				         window.location.reload() ;
				     },
				
				     //请求失败或者超时后的回调。
				     onerror: function ( xhr ) {
				          alert( 'Ajax请求失败' );
				     }
				 } );
				 
	}
	/**隐藏文本编辑器**/
	window.onload = function(){
		document.getElementById('diaryList').style.visibility="visible" ;
		document.getElementById('addDiary').style.visibility="hidden" ; 
	}
	
	/**页面仅显示文本编辑器**/
	function addDiary(obj){
		document.getElementById('diaryList').style.visibility="hidden" ;
		document.getElementById('addDiary').style.visibility="visible" ; 
		obj.style.visibility = "hidden" ;
	}
	/**提交文本编辑**/
	function submitAddDiary(){
		var title = document.getElementById("title").value ;
		var mood = document.getElementById("mood").value ;
		var weather = document.getElementById("weather").value ;
		
			 //向addDiary发起一个异步的Ajax GET请求, 请求超时时间为10s， 请求完成后执行相应的回调。
				 UE.ajax.request( 'addDiary', {
				
				     //请求方法。可选值： 'GET', 'POST'，默认值是'POST'
				     method: 'POST',
				
				     //超时时间。 默认为5000， 单位是ms
				     timeout: 10000,
				
				     //是否是异步请求。 true为异步请求， false为同步请求
				     async: true,
				
				     //请求携带的数据。如果请求为GET请求， data会经过stringify后附加到请求url之后。
				     data: {
				     	 title : title ,
				     	 mood : mood ,
				     	 weather : weather ,
				         contentHtml : ue.getAllHtml()
				     },
				
				     //请求成功后的回调， 该回调接受当前的XMLHttpRequest对象作为参数。
				     onsuccess: function ( xhr ) {
				         alert(xhr.responseText) ;
				         window.location.reload() ;
				     },
				
				     //请求失败或者超时后的回调。
				     onerror: function ( xhr ) {
				          alert( 'Ajax请求失败' );
				     }
				 } );
				 
	}
</script>
</body>
</html>