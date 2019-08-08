<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags"  prefix="s" %>
<html>
<head>
<TITLE>客户拜访列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="js/jquery-3.4.0.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#currentpage").val(page);
		}
		document.customerForm.submit();
	}
</SCRIPT>
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
		$('#visit_endtime').datepick({dateFormat:'yy-mm-dd'}); 
	});
</script>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<s:form id="saleVisitForm" name="saleVisitForm" action="saleVisit_findAll" namespace="/" method="post" theme="simple">	
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户拜访记录 &gt; 拜访列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>拜访时间：从</TD>
													<TD>
														<input type="text"  readonly="readonly" name="visit_time"  id="visit_time" value="<s:date name="visit_time" format="yyyy-mm-dd"/>"/>
													</TD>
													<TD>到</TD>
													<TD>
														<input type="text"  readonly="readonly" name="visit_endtime"  id="visit_endtime" value="<s:date name="visit_endtime" format="yyyy-mm-dd"/>"/>
													</TD>
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>业务员名称</TD>
													<TD>客户名称</TD>
													<TD>约谈时间</TD>
													<TD>约谈地址</TD>
													<TD>约谈详情</TD>
													<TD>下次约定时间</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="list">
												<TR 
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><s:property value="user.user_name"/></TD>
													<TD><s:property value="customer.cust_name"/></TD>
													<TD><s:date name="visit_time" format="yyyy-MM-dd"/></TD>
													<TD><s:property value="visit_addr"/></TD>
													<TD><s:property value="visit_detail"/></TD>
													<TD><s:date name="visit_nexttime" format="yyyy-MM-dd"/></TD>
													<TD>
													<a href="${pageContext.request.contextPath}/saleVisit_edit.action?visit_id=<s:property value="visit_id"/>">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath}/saleVisit_delete.action?visit_id=<s:property value="visit_id"/>">删除</a>
													</TD>
												</TR>
												</s:iterator>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="totalcount"/></B>]条记录,[<B><s:property value="totalpage"/></B>]页
												,每页显示
												<select name="pagesize" onchange="to_page()">
												<option value="3" <s:if test="pagesize==3">selected</s:if>>3</option>
												<option value="5" <s:if test="pagesize==5">selected</s:if>>5</option>
												<option value="10"<s:if test="pagesize==10">selected</s:if>>10</option>
												</select>
												条
												<s:if test="currentpage!=1">
												[<A href="javascript:to_page(1)">首页</A>]
												[<A href="javascript:to_page(<s:property value="currentpage-1"/>)">前一页</A>]
												</s:if>
												<B>
												<s:iterator var="i" begin="1" end="totalpage">
													<s:if test="#i==currentpage">
														<s:property value="#i"/>
													</s:if>
													<s:else>
														<a href="javascript:to_page(<s:property value="#i"/>)"><s:property value="#i"/></a>
													</s:else>
												</s:iterator>
												</B>
												<s:if test="currentpage!=totalpage">
												[<A href="javascript:to_page(<s:property value="currentpage+1"/>)">后一页</A>] 
												[<A href="javascript:to_page(<s:property value="totalpage"/>)">尾页</A>]
												</s:if>
												到
												<input type="text" size="3"  id="currentpage" name="currentpage" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
