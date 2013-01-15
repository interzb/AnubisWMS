<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.inventory.util.RequestUtil"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.springframework.ui.ModelMap"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="com.inventory.model.PageView"%>
<%@page import="java.util.List"%>
<%@page import="com.inventory.model.BaseVO"%>
<%@page import="com.inventory.model.PageIndex"%>
<%@page import="com.inventory.model.Branch"%>
<%@page import="com.inventory.model.Product"%>
<%@page import="com.inventory.util.Constants"%>

<%
	String context = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Inventory</title>
		<link rel="stylesheet" rev="stylesheet" href="<%=context%>/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="<%=context%>/css/tcal.css" />
		<script type="text/javascript" src="<%=context%>/js/tcal.js"></script>
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

		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">
						Entry of Goods
					</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width: 100%">


							<tr>
								<td width="100%">

									<table width="99%" border="0" cellpadding="0" cellspacing="0">

										<tr>
											<td height="30">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">

												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<form action="<%=context%>/enterStockDo.do" method="post">
										<table id="subtree1" style="DISPLAY: " width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tr>
												<td>
													<table width="95%" border="0" align="center"
														cellpadding="0" cellspacing="0">

														<tr>
															<td>
																Branch:

																<select name="branch" size="1" style="width: 80px">
																	<%
																		List<Branch> branches = (List<Branch>) request
																				.getAttribute("branchs");
																		for (Branch branch : branches) {
																	%>
																	<option value="<%=branch.getId()%>"><%=branch.getName()%></option>
																	<%
																		}
																	%>

																</select> <%=RequestUtil.geteMessage(request,"info") %>
																<br />
																&nbsp;
															</td>
														</tr>
														<tr>
															<td height="40" class="font42">
																<table width="100%" border="0" cellpadding="4"
																	cellspacing="1" bgcolor="#464646" class="newfont03">
																	<tr>
																		<td colspan=4 bgcolor="#FFFFFF">
																			Goods:
																		</td>
																	</tr>
																	<tr>
																		<td width="15%" height="20" align="center"
																			bgcolor="#EEEEEE">
																			Code
																		</td>
																		<td width="15%" align="center" bgcolor="#EEEEEE">
																			Product
																		</td>
																		<td width="15%" align="center" bgcolor="#EEEEEE">
																			Entry
																		</td>
																		<td width="15%" align="center" bgcolor="#EEEEEE">
																			Expiration
																		</td>
																	</tr>
																	<%
																		for (int i = 1; i <= Constants.SIZE; i++) {
																	%>
																	<tr>
																		<td width="15%" height="20" align="center"
																			bgcolor="#FFFFFF">
																			<select id="productId<%=i%>" name="productId<%=i%>"
																				size="1" style="width: 80px"
																				onchange="document.getElementById('name<%=i%>').options[this.selectedIndex].selected=true;">
																				<%
																					List<Product> products = (List<Product>) request.getAttribute("products");
																						for (Product product : products) {
																				%>
																				<option value="<%=product.getId()%>"><%=product.getBarcode()%></option>
																				<%
																					}
																				%>

																			</select>
																		</td>
																		<td width="15%" align="center" bgcolor="#FFFFFF">
																			<select id="name<%=i%>" name="name<%=i%>" size="1"
																				style="width: 80px"
																				onchange="document.getElementById('productId<%=i%>').options[this.selectedIndex].selected=true;">
																				<%
																					for (Product product : products) {
																				%>
																				<option value="<%=product.getName()%>"><%=product.getName()%></option>
																				<%
																					}
																				%>

																			</select>
																		</td>
																		<td width="15%" align="center" bgcolor="#FFFFFF">
																			<input type=text name="quantity<%=i %>" size=6 />
																		</td>
																		<td width="15%" align="center" bgcolor="#FFFFFF">
																			<input type=text name="expiration<%=i %>" class="tcal" size=16/>
																		</td>
																	</tr>
																	<%
																		}
																	%>
																	<tr>
																		<td colspan=4 bgcolor="#FFFFFF">
																			<br />

																			<input type="submit" value="Finsh" />

																		</td>

																	</tr>


																</table>
															</td>
														</tr>
													</table>

												</td>
											</tr>
										</table>
									</form>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>

	</body>
</html>
