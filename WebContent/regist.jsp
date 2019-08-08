<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: rgb(1, 1, 15); FONT-FAMILY: 宋体;font-weight:bold
}
TD {
	FONT-SIZE: 12px; COLOR: rgb(1, 1, 15); FONT-FAMILY: 宋体;font-weight:bold
}
</STYLE>
<META content="MSHTML 6.00.6000.16809" name=GENERATOR></HEAD>
<BODY>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath}/user_registe.action" method=post target="main">
<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" 
  border=0></TD></TR>
  <TR>
    <TD background=images/bg.png height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=100><IMG src="images/iconuser.png" style="HEIGHT: 12.5px; width:12.5px ;BORDER-TOP-WIDTH:0px ;BORDER-RIGHT-WIDTH:0px ;BORDER-LEFT-WIDTH:0px ;BORDER-BOTTOM-WIDTH:0px ;border:0"> 登录名:</TD>
                <TD style="HEIGHT: 28px" width=150><INPUT id=txtName 
                  style="WIDTH: 130px" name=user_code></TD>
                <TD style="HEIGHT: 28px" width=370><SPAN 
                  id=RequiredFieldValidator3 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px" width=100><IMG src="images/iconps2.png" style="HEIGHT: 12.5px; width:12.5px ;BORDER-TOP-WIDTH:0px ;BORDER-RIGHT-WIDTH:0px ;BORDER-LEFT-WIDTH:0px ;BORDER-BOTTOM-WIDTH:0px ;border:0"> 登录密码:</TD>
                <TD style="HEIGHT: 28px" width=150><INPUT id=txtPwd style="WIDTH: 130px" 
                  type=password name=user_password></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN></TD></TR>
                   <TR>
                <TD style="HEIGHT: 28px" width=100><IMG src="images/iconps2.png" style="HEIGHT: 12.5px; width:12.5px ;BORDER-TOP-WIDTH:0px ;BORDER-RIGHT-WIDTH:0px ;BORDER-LEFT-WIDTH:0px ;BORDER-BOTTOM-WIDTH:0px ;border:0"> 用户姓名:</TD>
                <TD style="HEIGHT: 28px" width=150><INPUT id=txtPwd style="WIDTH: 130px" 
                  type=text name=user_name></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入姓名</SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px" width=100><IMG src="images/check.png" style="HEIGHT: 12.5px; width:12.5px ;BORDER-TOP-WIDTH:0px ;BORDER-RIGHT-WIDTH:0px ;BORDER-LEFT-WIDTH:0px ;BORDER-BOTTOM-WIDTH:0px ;border:0"> 验证码:</TD>
                <TD style="HEIGHT: 28px"><INPUT id=txtcode 
                  style="WIDTH: 130px" ></TD>
                <TD style="HEIGHT: 28px">&nbsp;</TD></TR>
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
                <TD></TD>
                <TD><INPUT id=btn 
                  style="height: 20px; width: 60px;background:url(images/bgregisterbutton2.png)" 
                  type=submit value="" name=btn> 
              </TD>
              </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD><IMG src="images/login_3.jpg" 
border=0></TD></TR></TBODY></TABLE></DIV></DIV>


</FORM></BODY></HTML>
