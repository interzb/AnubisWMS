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
<%@page import="com.inventory.model.Stock"%>
<%@page import="com.inventory.model.Transfer"%>

<%
	String context = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Inventory</title>
		<link rel="stylesheet" rev="stylesheet"
			href="<%=context%>/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css" href="<%=context%>/css/tcal.css" />
		<script type="text/javascript" src="<%=context%>/js/tcal.js"></script>

		<script language="JavaScript" type="text/javascript">
	function selectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "id") {
				obj[i].checked = true;
			}
		}
	}

	function unselectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "id") {
				if (obj[i].checked == true)
					obj[i].checked = false;
				else
					obj[i].checked = true;
			}
		}
	}
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


	<%
		PageView pageView = (PageView) request.getAttribute("transfers");
	%>

	<body class="ContentBody">
		<form action="<%=context%>/listTransfer.do" method="post" name="fom"
			id="fom" target="mainFrame">
			<div class="MainDiv">
				<table width="99%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							Stock Query
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
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Original Branch: 
											 
													

													<select name="fromBranchId" size="1" style="width: 80px">
														<%
															List<Branch> branches = (List<Branch>) request
																	.getAttribute("branchs");
															for (Branch branch : branches) {
														%>
														<option value="<%=branch.getId()%>"><%=branch.getName()%></option>
														<%
															}
														%>

													</select>
													Destination:
													<select name="toBranchId" size="1" style="width: 80px">
														<%
															 
															for (Branch branch : branches) {
														%>
														<option value="<%=branch.getId()%>"><%=branch.getName()%></option>
														<%
															}
														%>

													</select>
													 
											 
												 
													 Date From : 
													<input type=text name=startDate class=tcal size="15"/>
													To:
													<input type=text name=endDate class=tcal size="15"/>
													 
												 
													 By ID : 
													<input type=text name=transferId size=4>
													
													<input type=submit value=Search>
													 <br/>&nbsp;
													 
												</td>
											</tr>
											
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table id="subtree1" style="DISPLAY: " width="100%" border="0"
											cellspacing="0" cellpadding="0">

											<tr>
												<td>
													<table width="95%" border="0" align="center"
														cellpadding="0" cellspacing="0">


														<tr>
															<td height="40" class="font42">
																<table width="100%" border="0" cellpadding="4"
																	cellspacing="1" bgcolor="#464646" class="newfont03">

																	<tr>
																		<!--                    <td width="18%" align="center" bgcolor="#EEEEEE"> <a href="#" class="right-font08" onclick="selectAll();">Select All</a>&nbsp;&nbsp;&nbsp;<a href="#" class="right-font08" onclick="unselectAll();">UnSelect All</a></span></td>-->
																		<td width="10%" height="20" align="center"
																			bgcolor="#EEEEEE">
																			ID
																		</td>
																		<td width="10%" height="20" align="center"
																			bgcolor="#EEEEEE">
																			Date
																		</td>
																		
																		<td width="12%" align="center" bgcolor="#EEEEEE">
																			Original  
																		</td>
																		<td width="12%" align="center" bgcolor="#EEEEEE">
																			Destination  
																		</td>
																		<td width="12%" align="center" bgcolor="#EEEEEE">
																			Product
																		</td>
																		<td width="5%" align="center" bgcolor="#EEEEEE">
																			Stocks
																		</td>
																		<td width="5%" align="center" bgcolor="#EEEEEE">
																			Transfer Quantity
																		</td>
																		
																		 
																		<td width="15%" align="center" bgcolor="#EEEEEE">
																			Expiration Date
																		</td>
																		<td width="3%" align="center" bgcolor="#EEEEEE">
																			Status
																		</td>
																		<td width="20%" align="center" bgcolor="#EEEEEE">
																			Action
																		</td>
																		
																	</tr>

																	<%
																		List<BaseVO> bvs = pageView.getRecords();
																		if (bvs != null && bvs.size() > 0) {

																			for (BaseVO bv : bvs) {

																				Transfer p = (Transfer) bv;
																	%>
																	<tr align="center">
																		<!--                    <td bgcolor="#FFFFFF"><input type="checkbox" name="id"/></td>-->
																		 
																		
																		<td bgcolor="#FFFFFF"><%=p.getId()%></td>
																		<td bgcolor="#FFFFFF"><%=p.getCreattime().substring(0,10)%></td>
																		<td bgcolor="#FFFFFF"><%=p.getFromBranchName()%></td>
																		<td bgcolor="#FFFFFF"><%=p.getToBranchName()%></td>
																		<td bgcolor="#FFFFFF"><%=p.getProductName()%></td>	
																		<td bgcolor="#FFFFFF"><%=p.getStocks()%></td>	
																		<td bgcolor="#FFFFFF"><%=p.getQuantity()%></td>	
																		<td bgcolor="#FFFFFF"><%=p.getExpiration()%></td>	
																		<td bgcolor="#FFFFFF"><%=p.getStatusString()%></td>	
																		
																		<td bgcolor="#FFFFFF">
																		
																		<%
																		
																			if(p.getStatus()==0){
																		%>
																			<a href="acceptTransfer.do?transferId=<%=p.getId() %>">Accept</a> 
																			<a href="denyTransfer.do?transferId=<%=p.getId() %>">Deny</a> 
																		<% 	
																			}
																		
																		%>
																		
																		
																		</td>	
																	</tr>

																	<%
																		}
																		}
																	%>

																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<table width="95%" border="0" align="center" cellpadding="0"
											cellspacing="0">
											<tr>
												<td height="6">
													<img src="../images/spacer.gif" width="1" height="1" />
												</td>
											</tr>
											<tr>
												<td height="33">
													<table width="100%" border="0" align="center"
														cellpadding="0" cellspacing="0" class="right-font08">
														<tr>
															<td width="99%">



																<%
																	PageIndex pageIndex = pageView.getPageindex();
																	for (long i = pageIndex.getStartindex(); i <= pageIndex
																			.getEndindex(); i++) {
																%>

																<a href="listTransfer.do?currentPage=<%=i%>"
																	class="right-font08"><%=i%></a>
																<%
																	}
																%>
																Total:
																<span class="right-text09"> <span
																	class="right-text09"><%=pageView.getTotalrecord()%></span>
																	Records |<%=pageView.getTotalpage()%></span> |
																<span class="right-text09">Current:<%=pageView.getCurrentpage()%></span>



															</td>
															<td width="1%">
																<table width="20" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td width="1%">
																		</td>
																		<td width="87%">
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							</div>
							</form>
	</body>
</html>
