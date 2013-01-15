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
	<form action="<%=context %>/addBranch.do" method="post"  name="fom"
		id="fom" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">Add Branch</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width: 100%">


							<TR>
								<TD width="100%">
									<fieldset style="height: 100%;">
										<legend>Branch</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											 
											
											<tr>
												<td nowrap align="right" width="13%">Branch Name:</td>
												<td width="41%"><input name="name" class="text"
													style="width: 150px" type="text" size="40" /> <span
													class="red"> *<%=RequestUtil.geteMessage(request, "Name_Error") %></span></td>

											</tr>
											<tr>
												<td nowrap align="right">Branch Address:</td>
												<td><input name="address" id="" class="text"
													style="width: 254px" /> <span class="red">*<%=RequestUtil.geteMessage(request, "Address_Error") %></span></td>
											</tr>

											 


										</table>
										<br />
									</fieldset>
								</TD>
							</TR>

						</TABLE>


					</td>
				</tr>




				<TR>
					<td  colspan="2" align="center"> <span
													class="blue">  <%=RequestUtil.geteMessage(request, "Final_Message") %></span></td>
				</TR>

				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="submit" name="Submit" value="Save" class="button"
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
