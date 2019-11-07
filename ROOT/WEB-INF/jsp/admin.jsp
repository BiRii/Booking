<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<s:if test="str.equals('s1')">
		<script type="text/javascript">
			alert("会话已失效，请重新登录！\n点击确定或关闭后将返回首页");
			window.location.assign("/");
		</script>
	</s:if>

	<s:if test="str.equals('s3')">
		<script type="text/javascript">
			alert("系统已不存在当前登录帐户\n点击确定或关闭后将返回首页");
			window.location.assign("/");
		</script>
	</s:if>

	<s:if test="str.equals('s2')">
		<script type="text/javascript">
			alert("您不是管理员，不能访问！\n点击确定或关闭后将返回首页");
			window.location.assign("/");
		</script>
	</s:if>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>预订系统管理中心</title>

	<link rel="stylesheet" type="text/css" href="../css/admin.css">

	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/messages_zh.js"></script>
	<script type="text/javascript" src="../js/sha512.js"></script>

	<script type="text/javascript" src="../js/admin.js"></script>

</head>
<body>
	<div id="topdiv">
		欢迎您，<span id="usname"><s:property value="suname"/></span> <span class="under" id="logout">退出</span> <span class="under" id="spanquery">预订查询及取消</span> <span class='under' id='returnhome'> 返回首页</span>
	</div>

	<div class="one">预订系统管理中心</div>
	<div class="navigation">
		<table id="toptable" cellspacing="0">
		<tr>
			<td id="fielditem">场地名目管理</td>
			<td id="timeitem">时间项管理</td>
			<td id="bookitem">预订管理</td>
			<td id="useritem">帐户管理</td>
			<td id="pauseitem">暂停管理</td>
		</tr>
		</table>
	</div>
	<br>
	<div id="showall"><h2>请点击上面的菜单项进入操作</h2></div>

	<div id="resetpswdiv">
		<span id="spanreset">重置帐户密码</span><span id="spnresetcls">关闭</span>
		<form id="formresetpsw">
			<div id="hint3">&nbsp;</div>
			<table id="tbresetid">
				<tr>
					<td>帐户名：</td><td><span class="usrname"></span></td>
				</tr>
				<tr>
					<td><label id="plb3" for="pid3">新密码：</label></td>
					<td><input type="password" name="userpassword3" class="pip3" id="pid3" minlength="6" maxlength="512" required /></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" id="resetsbid" value="提交" title="点击提交" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="overlay"></div>

</body>
</html>