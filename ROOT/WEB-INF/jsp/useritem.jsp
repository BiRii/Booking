<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table id="tbuseritem" cellspacing="0px">
	<tr class="trbottom">
		<th>行号</th><th>帐户名</th><th>帐户邮箱</th><th>是否管理员</th><th>操作</th>
	</tr>
	<s:if test="subuserItemList.isEmpty()">
		<tr><td colspan="5">系统无记录</td></tr>
	</s:if>
	<s:else>
		<s:iterator value="subuserItemList" var="var1" status="st1">
			<tr class="trcontent">
				<td class="number"><s:property value="#st1.count+(pagination-1)*15"/></td><td class="busername"><s:property value="#var1.getUsername()"/></td><td><s:property value="#var1.getEmail()"/></td><td class="isnotadmin"><s:if test="#var1.getPrerogative()==1"><span class="isadmin">是</span></s:if><s:else><span class="isadmin">否</span></s:else></td><td class="operation"><span class="modify">修改</span> <span class="resetpsw">重置密码</span> <span class="delete">删除</span></td>
			</tr>
		</s:iterator>
	</s:else>
	<tr class="trbottom">
		<td><span class="cl2">删除选定项</span></td><td colspan="3"><span class="sp1" data-no="1">首页</span>
			<s:bean name="org.apache.struts2.util.Counter">
				<s:param name="first" value="firstPagination"/>
				<s:param name="last" value="lastPagination"/>
				<s:iterator var="var4" status="st4">
					<span class="sp1" data-no="<s:property value='#var4'/>" <s:if test="#var4==pagination">id="slctd"</s:if>><s:property value="#var4"/></span>
				</s:iterator>
			</s:bean>
		<span class="sp1" data-no="<s:property value='pages'/>">末页</span> <span class="sp2">显示所有页码</span> 共<s:property value="pages"/>页</td><td class="optall"><span class="cl3">全部删除</span></td>
	</tr>
</table>

<div id="turnpage">
	<table id="turnto">
		<tr>
			<s:bean name="org.apache.struts2.util.Counter">
				<s:param name="first" value="1"/>
				<s:param name="last" value="pages"/>
				<s:iterator var="var5" status="st5">
					<td class="td4" data-no="<s:property value='#var5'/>" <s:if test="#var5==pagination">id="slctd2"</s:if>><s:property value="#var5"/></td>
					<s:if test="(#st5.count%25==0) && (#st5.count!=pages)">
						</tr><tr>
					</s:if>
				</s:iterator>
			</s:bean>
		</tr>
	</table>
</div>