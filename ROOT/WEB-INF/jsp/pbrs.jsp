<%@ page contentType="text/html; charset=utf-8" language="java"%><%@ taglib prefix="s" uri="/struts-tags"%><s:if test="str.equals('s1')">会话已失效，请重新登录<br/><br/><button type="button" id="login">点击登录</button>&nbsp;&nbsp;<button type="button" id="register">注册帐户</button></s:if><s:elseif test="str.equals('s2')" >暂停成功</s:elseif>

<s:elseif test="str.equals('s3')" >
下列场地已被暂停预订，请先取消选定或者刷新页面重新选择：
<s:property value="bookedTitle"/>
</s:elseif>

<s:elseif test="str.equals('s4')" >
下列场地已被预订，请先取消选定或者刷新页面重新选择，如仍需暂停预订，请先到【预订管理】处删除对应的预订项再暂停：
<s:property value="bookedTitle"/>
</s:elseif>

<s:elseif test="str.equals('s5')" >
当前登录帐户已不存在，请刷新页面切换登录帐户
</s:elseif>

<s:elseif test="str.equals('s6')" >
当前登录帐户不是管理员，不能进行暂停操作
</s:elseif>

<s:else>
出现错误：<s:property value="str"/>
</s:else>