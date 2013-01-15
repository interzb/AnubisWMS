<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.inventory.util.RequestUtil"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.springframework.ui.ModelMap"%>
<%@page import="org.springframework.ui.Model"%>

<%
String context = request.getContextPath();
 
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Inventory</title>
<link rel="stylesheet" rev="stylesheet" href="<%=context %>/css/style.css"
	type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">
 
</script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>
</head>

 

<body class="ContentBody">
	<form action="<%=context %>/productExport.do" method="post"  name="fom"
		id="fom" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">Export Product</th>
				</tr>
				 



				 
				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="submit" name="Submit" value="Export" class="button"
						  />  <input type="button" name="Submit2"
						value="Cancel" class="button" onclick="window.history.go(-1);" /></TD>
				</TR>
			</TABLE>


			</td>
			</tr>

	</table>

		</div>
	</form>
</body>
</html>
