<%@ page contentType="text/html; charset=utf-8" language="java"%><%@ taglib prefix="s" uri="/struts-tags"%><s:if test="str.equals('s1')">您好，请先登录<br/><br/><button type="button" id="login">点击登录</button>&nbsp;&nbsp;<button type="button" id="register">注册帐户</button></s:if><s:elseif test="str.equals('s2')" >预订成功</s:elseif>

<s:elseif test="str.equals('s3')" >
下列场地已被暂停预订，不能再订：
<s:property value="bookedTitle"/>
</s:elseif>

<s:elseif test="str.equals('s4')" >
下列场地已被预订，不能再订：
<s:property value="bookedTitle"/>
</s:elseif>

<s:elseif test="str.equals('s5')" >
当前登录帐户已不存在，请刷新页面切换登录帐户
</s:elseif>

<s:else>
出现错误：<s:property value="str"/>
</s:else>