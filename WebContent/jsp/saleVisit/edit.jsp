<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<TITLE>添加拜访记录</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<!-- 引入 jQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.4.2.js"></script>
	<link   type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/jquery/jquery.datepick.css" >
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.datepick.js" ></script>
<!-- 引入中文插件
		<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.datepick-zh-CN.js"></script>
 -->
<script type="text/javascript">
	$(function(){
		$('#visit_time').datepick({dateFormat:'yy-mm-dd'});   
		$('#visit_nexttime').datepick({dateFormat:'yy-mm-dd'});   
	});
</script>
</head>
<BODY>
	<s:form id="form1" name="form1" action="saleVisit_update" namespace="/" method="post" theme="simple">
		<s:hidden name="visit_id"></s:hidden>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath}/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath}/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath}/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：拜访记录管理 &gt; 修改拜访记录</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>拜访客户：</td>
								<td>
								<s:select name="customer.cust_id" list="custlist"  headerKey="" headerValue="--请选择" listKey="cust_id" listValue="cust_name"/>	
								</td>
								<td>业务员名称：</td>
								<td>
								<s:select list="userlist" name="user.user_id" headerKey="" headerValue="--请选择" listKey="user_id" listValue="user_name"/>
							</tr>
							<TR>
								
								<td>拜访时间：</td>
								<td>
								<input width="180px" maxlength="50" name="visit_time" id="visit_time" value='<s:date name="visit_time" format="yyyy-MM-dd"/>'/>
								<!-- <input width="180px" maxlength="50" name="visit_time" id="visit_time" value='<s:date name="visit_time" format="yyyy-mm-dd"/>'/>
								<s:textfield  readonly="true" cssStyle="WIDTH: 180px" cssClass="textbox" id="visit_time" maxlength="50" name="visit_time"></s:textfield>
								-->	
								</td>
								<td>拜访地址 ：</td>
								<td>
								<s:textfield  id="visit_addr" cssStyle="WIDTH: 180px" cssClass="textbox" maxlength="50" name="visit_addr"></s:textfield>
								</td>
							</TR>
							<TR>
								<td>约谈详情 ：</td>
								<td>
							<s:textfield cssStyle="WIDTH: 180px" cssClass="textbox" id="" maxlength="50" name="visit_detail"></s:textfield>
								</td>
								<td>下次约谈时间 ：</td>
								<td>
								<input width="180px" maxlength="50" name="visit_nexttime" id="visit_nexttime" value='<s:date name="visit_nexttime" format="yyyy-MM-dd"/>'/>
								<!-- <s:textfield readonly="true" cssStyle="WIDTH: 180px" cssClass="textbox" id="visit_nexttime" maxlength="50" name="visit_nexttime"></s:textfield>
								 -->
								</td>
							</TR>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align="center" width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</s:form>
</BODY>
</HTML>
