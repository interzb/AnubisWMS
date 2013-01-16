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
	String url = request.getRequestURL().toString();
	
	System.out.println(url);
	String path = url.substring(0,url.indexOf("views"));
	System.out.println(path);
 
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Inventory</title>
		<link rel="stylesheet" rev="stylesheet"
			href="<%=context%>/css/style.css" type="text/css" media="all" />
		<link rel="stylesheet" type="text/css"
			href="<%=context%>/css/tcal.css" />
		<script type="text/javascript" src="<%=context%>/js/tcal.js"></script>

		<script type="text/javascript"
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

		<script language="JavaScript" type="text/javascript">
		var path = '<%=path%>' ;
 		$(function(){  
               $('.originalBranch').change(function(){ 
               		 
               		if($('.originalBranch').val().length == 0){  
                  	 	 
               		} else{  
               			document.getElementById("productId").options.length=1;
               			$.ajax({  
                 			url:path+'productsInBranch.do?branchId='+$('.originalBranch').val(),  
                 			 
                 			error:function(){  
                 				alert(path);
                 				alert("Loading error occured,Please try again later");  
                 			},  
                 			success:function(data){  
                  				 var values=data.split('@#@#@#');
                  				  
                  				 if(values.length>=3){
                  				 	for(var i = 0;i+3<values.length-1;i+=4){
                  				 		document.getElementById("productId").options.add(new Option(values[i+1]+",Expiration="+values[i+2]+",Quantity="+values[i+3],values[i])); 
                  				 	}	
                  				 }
        					}  
	                	});
	              }  
               });  
         });   
         
         
         $(function(){  
               $('.submit').click(function(){ 
               		 
               		if($('.originalBranch').val().length == 0){  
                  	 	 alert("Please select original branch");
               			return;
               		} 
               		if($('.destinationBranch').val().length == 0){  
                  	 	 alert("Please select destination branch");
                  	 	 return;
               		} 
               		if($('.destinationBranch').val() == $('.originalBranch').val()){  
                  	 	 alert("Origial brnach cant be same with detination");
                  	 	 return;
               		}  
               		
               		if($('.productId').val().length == 0){  
                  	 	 alert("Please select product");
                  	 	 return;
               		} 
               		
               		if($('.quantity').val().length == 0){  
                  	 	 alert("Please input quantity,positive integer");
                  	 	 return;
               		}  
               		
               		//send ajax
               		
               		 $.ajax({  
                 			url:path+'newTransferDo.do?quantity='+$('.quantity').val()+"&originalBranch="+$('.originalBranch').val()+"&destinationBranch="+$('.destinationBranch').val()+"&fromStockId="+$('.productId').val(),  
                 			 
                 			error:function(){  
                 				alert("Loading error occured,Please try again later");  
                 			},  
                 			success:function(data){  
                  				 alert(data);
        					}  
	                	});
               		
               });  
         });   
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
						Fill Transfer
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
									<form action="<%=context%>/addTransfer.do" method="post">
										<table id="subtree1" style="DISPLAY: " width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tr>
												<td>
													<table width="50%" border="0" align="center"
														cellpadding="0" cellspacing="0">
<tr>
															<td class="hint" colspan=2> 
																 
															</td>
														</tr>
														<tr>
															<td>
																Original&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Branch:
															</td>
															<td align="left">
																<select class="originalBranch" name="branch" size="1"
																	style="width: 120px" onchange="">
																	<option value="">
																		Select Branch
																	</option>
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
																<br />
																&nbsp;
															</td>
														</tr>

														<tr>
															<td>
																Destination Branch:
															</td>
															<td align="left">
																<select id="destinationBranch"  class="destinationBranch" name="destinationBranch" size="1" style="width: 120px">
																	<option value="">
																		Select Branch
																	</option>
																	<%
																		branches = (List<Branch>) request.getAttribute("branchs");

																		for (Branch branch : branches) {
																	%>
																	<option value="<%=branch.getId()%>"><%=branch.getName()%></option>
																	<%
																		}
																	%>

																</select>
																<br />
																&nbsp;
															</td>
														</tr>

														<tr>
															<td>
																Product:
															</td>
															<td align="left">
																<select class="productId" id="productId" name="productId" size="1" style="width: 280px">

																	<option value="">
																		Select Product
																	</option>

																</select>
																<br />
																&nbsp;
															</td>
														</tr>
														<tr>
															<td>
																Transfer Quantity:
															</td>
															<td align="left">
																 <input class="quantity" type="text" name="quantity" id="quantity" size="4" >
																<br />
																&nbsp;
															</td>
														</tr>
														
														<tr>
															<td colspan=2 align=center>
																<input class="submit" type="button" value="Submit">
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
