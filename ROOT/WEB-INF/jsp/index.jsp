<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>场地预订</title>

	<link rel="stylesheet" type="text/css" href="../css/book.css">

	<script type="text/javascript" src="../js/jquery.min.js" ></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js" ></script>
	<script type="text/javascript" src="../js/messages_zh.js" ></script>
	<script type="text/javascript" src="../js/sha512.js"></script>

	<script type="text/javascript" src="../js/book.js" ></script>
	
</head>
<body>
	<div id="topdiv">
		<s:if test="suname!=null">
			<span>欢迎您，<s:property value="suname"/></span> <span class="under resetpsw">重置密码</span> <span id="modifymail" class="under">修改邮箱</span> <span class="under" id="spanquery">预订查询及取消</span> <s:if test="prerogative==1"><span class="under" id="spanadmin">系统管理中心</span></s:if> <span class="under" id="logout">退出</span>
		</s:if>
		<s:else>
			<span id="regspn" class="under">注册帐户</span>
			<span id="lgspn" class="under">帐户登录</span>
			<span class="under resetpsw">重置密码</span>
			<span id="retrieve" class="under">找回帐户名</span>			
		</s:else>
	</div>

	<div id="logindiv">
		<span id="spanlogin">帐户登录</span><span id="spanclose">关闭</span>
		<form id="formlogin">
			<div id="hint">&nbsp;</div>
			<table id="tblogid">
				<tr>
					<td class="lbl"><label id="ulb" for="uid">帐户名</label></td>
					<td><input type="text" name="username2" class="uip" id="uid" minlength="2" maxlength="512" required /></td>
				</tr>
				<tr>
					<td class="lbl"><label id="plb" for="pid">密码</label></td>
					<td><input type="password" name="userpassword2" class="pip" id="pid" minlength="6" maxlength="512" required /></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" id="lgsbid" value="登录" title="点击登录"/></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="retrievediv">
		<span id="spanretrieve">找回帐户名</span><span id="spnretrievecls">关闭</span>
		<form id="formretrieve">
			<div id="hint4">&nbsp;</div>
			<table id="tbretrieveid">
				<tr>
					<td class="lbl"><label id="emaillb4" for="empuid4">电子邮箱</label></td>
					<td><input type="email" name="email4" class="emailip4" id="empuid4" data-rule-email="true" minlength="5" maxlength="512" required /></td>
				</tr>
				<tr>
					<td class="lbl"><label id="verifylb4" for="verifyid4">电子邮箱验证码</label></td>
					<td><input type="text" name="vercode4" class="verifyip4" id="verifyid4" maxlength="8" required /> <input type="button" id="verifybutton4" value="点击发送" /></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" id="retrievesbid" value="找回帐户名" title="点击提交" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="resetpswdiv">
		<span id="spanreset">重置密码</span><span id="spnresetcls">关闭</span>
		<form id="formresetpsw">
			<div id="hint3">&nbsp;</div>
			<table id="tbresetid">
				<tr>
					<td class="lbl"><label id="emaillb3" for="empuid3">电子邮箱</label></td>
					<td><input type="email" name="email3" class="emailip3" id="empuid3" data-rule-email="true" minlength="5" maxlength="512" required /></td>
				</tr>
				<tr>
					<td class="lbl"><label id="verifylb3" for="verifyid3">电子邮箱验证码</label></td>
					<td><input type="text" name="vercode3" class="verifyip3" id="verifyid3" maxlength="8" required /> <input type="button" id="verifybutton3" value="点击发送" /></td>
				</tr>
				<tr>
					<td class="lbl"><label id="plb3" for="pid3">新密码</label></td>
					<td><input type="password" name="userpassword3" class="pip3" id="pid3" minlength="6" maxlength="512" required /></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" id="resetsbid" value="重置密码" title="点击提交" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="modifymaildiv">
		<span id="spanmdfml">修改邮箱</span><span id="spnmdfmlcls">关闭</span>
		<form id="formmdfml">
			<div id="hint5">&nbsp;</div>
			<table id="tbmdfmlid">
				<tr>
					<td class="lbl"><label id="emaillb5" for="empuid5">电子邮箱</label></td>
					<td><input type="email" name="email5" class="emailip5" id="empuid5" data-rule-email="true" minlength="5" maxlength="512" required /></td>
				</tr>
				<tr>
					<td class="lbl"><label id="verifylb5" for="verifyid5">电子邮箱验证码</label></td>
					<td><input type="text" name="vercode5" class="verifyip5" id="verifyid5" maxlength="8" required /> <input type="button" id="verifybutton5" value="点击发送" /></td>
				</tr>
				<tr>
					<td class="lbl"><label id="newmaillb5" for="newmailid5">新电子邮箱</label></td>
					<td><input type="email" name="newmail" class="newmailip" id="newmailid5" data-rule-email="true" minlength="5" maxlength="512" required /></td>
				</tr>
				<tr>
					<td class="lbl"><label id="verifylb6" for="verifyid6">新电子邮箱验证码</label></td>
					<td><input type="text" name="vercode6" class="verifyip6" id="verifyid6" maxlength="8" required/> <input type="button" id="verifybutton6" value="点击发送"/></td>
				</tr>				
				<tr>
					<td></td><td><input type="submit" id="mdfmlsbid" value="修改邮箱" title="点击提交"/></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="registerdiv">
		<span id="spanreg">注册帐户</span><span id="spnclose">关闭</span>
		<form id="formregister">
			<div id="hint2">&nbsp;</div>
			<table id="tbid">
				<tr>
					<td class="lbl"><label id="ulb2" for="uid2">帐户名</label></td>
					<td><input type="text" name="username" class="uip2" id="uid2" minlength="2" maxlength="512" required/></td>
				</tr>
				<tr>
					<td class="lbl"><label id="plb2" for="pid2">密码</label></td>
					<td><input type="password" name="userpassword" class="pip2" id="pid2" minlength="6" maxlength="512" required/></td>
				</tr>
				<tr>
					<td class="lbl"><label id="emaillb" for="empuid">电子邮箱</label></td>
					<td><input type="email" name="email" class="emailip" id="empuid" data-rule-email="true" minlength="5" maxlength="512" required/></td>
				</tr>
				<tr>
					<td class="lbl"><label id="verifylb" for="verifyid">电子邮箱验证码</label></td>
					<td><input type="text" name="vercode" class="verifyip" id="verifyid" maxlength="8" required/> <input type="button" id="verifybutton" value="点击发送"/></td>
				</tr>				
				<tr>
					<td></td><td><input type="submit" id="regsbid" value="提交注册" title="点击提交"/></td>
				</tr>
			</table>		
		</form>
	</div>
	
	<div id="overlay"></div>

	<div class="one">微山湖活动场地预订系统</div>
	<br>

<div id="query">
<s:if test="str.equals('s3')">
	<h1 id="hint1">系统里没有活动项目名</h1>
</s:if>
<s:else>
	<table class="tbsite" cellspacing="3px">
		<tr class='trbgcolor0'>
			<s:iterator value="fieldNames" var="var1" status="st1" >
				<td data-fieldstatus="<s:property value='fieldStatus.get(#st1.index)'/>" <s:if test="#var1==selectedFieldName">class="selected"</s:if> ><s:property value="#var1"/></td>
				<s:if test="#st1.count%14==0">
					</tr><tr class="trbgcolor<s:property value='#st1.count/14'/>">
				</s:if>
			</s:iterator>
		</tr>
	</table>
	<br>

	<table class="tbdate" cellspacing="3px">
		<tr class="DateTRcolor0">	
			<s:iterator value="dateweek" var="var2" status="st2">
				<td <s:if test="#var2.startsWith(selectedDate)">class="selected"</s:if>><s:property value="#var2"/></td>
				<s:if test="#st2.count%10==0">
					</tr><tr class="DateTRcolor<s:property value='#st2.count/10'/>">
				</s:if>
			</s:iterator>
		</tr>
	</table>
	<br>
	
	<table class="SiteTime" cellspacing="3px">
		<tr>
			<th class="sltall" title="单击全选">
				<div class="out">
					<b>时间</b>
					<em>场地号</em>
				</div>
			</th>

			<s:iterator value="timeItems" var="var3" status="st3">
				<th class="t2" title="列全选" data-col="<s:property value='#st3.count'/>"><s:property value="#var3"/></th>
			</s:iterator>
		</tr>
		
		<s:if test="selectedFieldStatus.equals('暂停预订')">
			<s:bean name="org.apache.struts2.util.Counter">  
				<s:param name="first" value="1"/>  
				<s:param name="last" value="rowCount"/>  
				<s:iterator status="st4">
					<tr>
						<td class="t1" title="行全选" data-td="<s:property value='#st4.count'/>"><s:property value="#st4.count"/></td>
						<s:bean name="org.apache.struts2.util.Counter">  
							<s:param name="first" value="1"/>  
							<s:param name="last" value="colCount"/>
							<s:iterator value="timeItems" var="var5" status="st5">
								<s:set var="ttl"><s:property value="#st4.count"/>号场地 <s:property value="#var5"/></s:set>							
								<td title="<s:property value='#ttl'/>"	data-row="<s:property value='#st4.count'/>" data-column="<s:property value='#st5.count'/>" 
									data-status="paused"
									>暂停预订</td>
							</s:iterator>  
						</s:bean>  
					</tr>
				</s:iterator>  
			</s:bean>		
		</s:if>
		<s:else>
			<s:bean name="org.apache.struts2.util.Counter">
				<s:param name="first" value="1"/>
				<s:param name="last" value="rowCount"/>
				<s:iterator status="st4">  
					<tr>
						<td class="t1" title="行全选" data-td="<s:property value='#st4.count'/>"><s:property value="#st4.count"/></td>
						<s:bean name="org.apache.struts2.util.Counter">  
							<s:param name="first" value="1"/>  
							<s:param name="last" value="colCount"/>  
							<s:iterator value="timeItems" var="var5" status="st5">
								<s:set var="ttl"><s:property value="#st4.count"/>号场地 <s:property value="#var5"/></s:set>							
								<td title="<s:property value='#ttl'/>"	data-row="<s:property value='#st4.count'/>" data-column="<s:property value='#st5.count'/>" 
									<s:if test="#ttl in titles">
										data-status="booked"
										>已被订</td>
									</s:if>
									<s:elseif test="#ttl in titles2">
										data-status="disabled"
										>暂停预订</td>
									</s:elseif>
									<s:elseif test="selectedDate.equals(nowDate) && #var5.substring(0,2)<nowhour">
										data-status="expired"
										>时间已过</td>
									</s:elseif>
									<s:else>
										data-status="available"
										>可订</td>
									</s:else>							
							</s:iterator>  
						</s:bean>  
					</tr>
				</s:iterator>  
			</s:bean>
		</s:else>
	</table>
	<br>
	
	<div id="caution">
		<span id="cauid">系统提示</span><span id="closeid">关闭</span>		
		<div id="contentid"></div>
	</div>
	
	<button type="button" class="sbt" title="点击预订">预订</button>

	<s:if test="prerogative==1"><button type="button" class="pausebooking" title="点击暂停">暂停预订</button></s:if>

</s:else>
</div>

</body>
</html>