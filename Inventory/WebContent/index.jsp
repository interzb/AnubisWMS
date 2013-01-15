 <%
 
 if(session.getAttribute("USER")==null){
	 response.sendRedirect("views/login.jsp");
	 
 }
 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Inventory</title>
</head>


<frameset rows="80,600,80" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="files/top.html" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="213,*" frameborder="no" border="0" framespacing="0">
    <frame src="files/left.html" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="files/mainfra.html" name="mainFrame" id="mainFrame" title="mainFrame" />
  </frameset>
  <frame src="files/footer.htm" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>
