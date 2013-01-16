<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.inventory.util.RequestUtil"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.springframework.ui.ModelMap"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@page import="com.inventory.model.BaseVO"%>
<%@page import="com.inventory.model.Family"%>
<%@page import="com.inventory.model.Branch"%>

<%
String context = request.getContextPath();
 
 
 List<BaseVO> families = (List<BaseVO>) request.getAttribute("Families");
		
List<BaseVO> branches =  (List<BaseVO>)request.getAttribute("Branches");
		
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
	<form action="<%=context %>/addProduct.do" method="post"  name="fom"
		id="fom" target="mainFrame">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">Add Product</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width: 100%">


							<TR>
								<TD width="100%">
									<fieldset style="height: 100%;">
										<legend>Product</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											 
											
											<tr>
												<td nowrap align="right" width="13%">Product Name:</td>
												<td width="41%"><input name="name" class="text"
													style="width: 250px" type="text" size="40" /> <span
													class="red"> *<%=RequestUtil.geteMessage(request, "Name_Error") %></span></td>

											</tr>
											
											<tr>
												<td nowrap align="right">Product Sku:</td>
												<td><input name="sku" id="" class="text"
													style="width: 154px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Sku_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Product Barcode:</td>
												<td><input name="barcode" id="" class="text"
													style="width: 154px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Barcode_Error") %></span></td>
											</tr>
											
											
											<tr>
												<td nowrap align="right">Product Delivery Type:</td>
												<td><input name="delivery_type" id="" class="text" style="width: 30px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Delivery_type_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Measurement Unit</td>
												<td><input name="unit_of_measurement" id="" class="text" style="width: 154px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Unit_of_measurement_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Product Status:</td>
												<td> 
												<select name=status>
													<option value=active>active</option>
													<option value=inactive>inactive</option>
												</select>
												
												 <span class="red"> *<%=RequestUtil.geteMessage(request, "Barcode_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Client Cost:</td>
												<td><input name="client_cost" id="" class="text"
													style="width: 154px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Client_cost_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Product Division :</td>
												<td><input name="division" id="" class="text"
													style="width: 154px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Division_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Product Group :</td>
												<td><input name="group" id="" class="text"
													style="width: 154px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Group_Error") %></span></td>
											</tr>
											
											 
											
											<tr>
												<td nowrap align="right">Product category:</td>
												<td>
												
												<select name=category>
													<%
														for(BaseVO v : families)  {
														Family f = (Family)v;
													%>
														<option value="<%=f.getName() %>"><%=f.getName() %></option>
													<%} %>
												</select>
											  <span class="red">*<%=RequestUtil.geteMessage(request, "Category_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Product Subcategory :</td>
												<td><input name="subcategory" id="" class="text" value=""
													style="width: 154px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Subcategory_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Package Policy :</td>
												<td><input name="package_policy" id="" class="text"
													style="width: 154px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Package_policy_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Provider Id :</td>
												<td><input name="provider_id" id="" class="text"
													style="width: 50px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Provider_id_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Client Price :</td>
												<td><input name="client_price" id="" class="text"
													style="width: 50px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Client_price_Error") %></span></td>
											</tr>
											
											<tr>
												<td nowrap align="right">Client Tax :</td>
												<td><input name="client_tax" id="" class="text"
													style="width: 50px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Client_tax_Error") %></span></td>
											</tr>
											
											
											<tr>
												<td nowrap align="right">Modified Flag :</td>
												<td><input name="modified_flag" id="" class="text"
													style="width: 50px" /> <span class="red"> *<%=RequestUtil.geteMessage(request, "Modified_flag_Error") %></span></td>
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
