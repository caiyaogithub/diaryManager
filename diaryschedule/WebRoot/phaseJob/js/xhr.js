alert("xhr.js") ; 
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