
<%@page import="java.util.List"%>
<%@page import="com.inventory.model.BaseVO"%>
<%@page import="com.inventory.model.Product"%>
<%@page import="com.inventory.model.PageView"%>
<%@page import="com.inventory.model.PageIndex"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Inventory</title>
<style type="text/css">
<!--
body {
	margin-left: 5px;
	margin-top: 5px;
	margin-right: 5px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "Verdana";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "Verdana";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "Verdana";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "Verdana";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>


<%
String context = request.getContextPath();
 
PageView pageView = (PageView)request.getAttribute("Products");
 
%>

<link href="<%=context %>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=context %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=context %>/js/xiangmu.js"></script>
</head>
<SCRIPT language=JavaScript>
 
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "id"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "id"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

 
</SCRIPT>

<body>


<div class="MainDiv">
<form name="fom" id="fom" method="post" action="">
<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
<tr>
					<th class="tablestyle_title">Product List</th>
				</tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="62" background="../images/nav04.gif">
          
		   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="21"><img src="../images/ico07.gif" width="20" height="18" /></td>
			<td width="550">By time
			 <input name="textfield" type="text" size="12" readonly="readonly"/><span class="newfont06">To</span>
			 <input name="textfield" type="text" size="12" readonly="readonly"/>	
			 <input name="Submit" type="button" class="right-button02" value="search" />
			 </td>
			 <td width="132" align="left"> 
			    </td>	
		  </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

          	 
              <tr>
                <td height="40" class="font42"><table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
                   
                  <tr>
                    <td width="14%" align="center" bgcolor="#EEEEEE"> <a href="#" class="right-font08" onclick="selectAll();">Select All</a>&nbsp;&nbsp;&nbsp;<a href="#" class="right-font08" onclick="unselectAll();">UnSelect All</a></span></td>
                    <td width="19%" height="20" align="center" bgcolor="#EEEEEE">Name</td>
                    <td width="20%" align="center" bgcolor="#EEEEEE">Sku</td>
                    <td width="21%" align="center" bgcolor="#EEEEEE">Family</td>
                    <td width="26%" align="center" bgcolor="#EEEEEE">Price</td>
                  </tr>
                  
                  <%
                   List<BaseVO> bvs = pageView.getRecords();
                   if(bvs!=null&&bvs.size()>0) { 
                   		 
                  		for(BaseVO bv : bvs) {
                  		
                  			Product p = (Product)bv;
                  %>
                  <tr align="center">
                    <td bgcolor="#FFFFFF"><input type="checkbox" name="id"/></td>
                    <td height="20" bgcolor="#FFFFFF"><%=p.getName() %></td>
                    <td bgcolor="#FFFFFF"><%=p.getSku() %></td>
                    <td bgcolor="#FFFFFF"><%=p.getCategory() %></td>
                    <td bgcolor="#FFFFFF"><a href="#">Del</a>|<a href="takexiaoximingxi.htm">View</a></td>
                  </tr>
                  
                  <%
                  	}
                  }
                  %>
                   
                </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="6"><img src="../images/spacer.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="33"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
              <tr>
                <td width="99%">Total: <span class="right-text09"><%=pageView.getTotalpage() %></span>   |  <span class="right-text09">Current:<%=pageView.getCurrentpage() %></span>  
            
                
                <% 
                	PageIndex pageIndex = pageView.getPageindex();
                	for(long i = pageIndex.getStartindex();i<=pageIndex.getEndindex();i++){
                %>
                
                	<a href="productList.do?currentPage=<%=i %>" class="right-font08"><%=i %></a>
                <% 
                	}
                %>
                 
                 
                </td>
                <td width="1%"><table width="20" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="1%"><input name="textfield3" type="text" class="right-textfield03" size="1" /></td>
                      <td width="87%"><input name="Submit23222" type="submit" class="right-button06" value=" " />
                      </td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</div>
</form>

 

</body>
</html>
  