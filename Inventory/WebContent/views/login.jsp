<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.inventory.util.RequestUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Inventory Solution</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<%
	String context = request.getContextPath();
%>
<link href="<%=context%>/css/css.css" rel="stylesheet" type="text/css" />
</head>


<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="147" background="<%=context%>/images/top02.gif"></td>
		</tr>
	</table>
	<form action="<%=context%>/login.do" method=post>
		<table width="1000" border="0" align="center" cellpadding="0"
			cellspacing="0" class="right-table03">
			<tr>
				<td width="221"><table width="95%" border="0" cellpadding="0"
						cellspacing="0" class="login-text01">

						<tr>
							<td><table width="100%" border="0" cellpadding="0"
									cellspacing="0" class="login-text01">
									<tr>
										<td align="center"><img
											src="<%=context%>/images/ico13.gif" width="107" height="97" /></td>
									</tr>
									<tr>
										<td height="40" align="center">&nbsp;</td>
									</tr>

								</table></td>
							<td><img src="<%=context%>/images/line01.gif" width="5"
								height="292" /></td>
						</tr>
					</table></td>
				<td><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="31%" height="35" class="login-text02">Username:<br /></td>
							<td width="30%"><input name="username" type="text" size="25" /></td>
							<td width="39%"><font color="red"> <%=RequestUtil.geteMessage(request, "Username_Error")%></td>
						</tr>
						<tr>
							<td height="35" class="login-text02">Password:<br /></td>
							<td><input name="password" type="password" size="25" /></td>
							<td><font color="red">  <%=RequestUtil.geteMessage(request, "Password_Error")%></td>
						</tr>


						<tr>
							<td height="35">&nbsp;</td>
							<td><input name="Submit2" type="submit"
								class="right-button01" value="Submit" /> <input
								name="Submit232" type="submit" class="right-button02"
								value="Reset" /></td>
						</tr>

					</table></td>
			</tr>
		</table>
	</form>
</body>
</html>