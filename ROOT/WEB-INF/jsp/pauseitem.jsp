<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table id="tbpauseitem" cellspacing="0px">
	<tr class="trbottom">
		<th>行号</th><th>暂停项目</th><th>暂停日期</th><th>暂停场地号</th><th>暂停时间段</th><th>暂停操作人</th><th>暂停提交时间</th><th>操作</th>
	</tr>
	<s:if test="subpauseItemList.isEmpty()">
		<tr><td colspan="8">系统无符合条件的记录</td></tr>
	</s:if>
	<s:else>
		<s:iterator value="subpauseItemList" var="var1" status="st1">
			<tr class="trcontent">
				<td class="number" data-id="<s:property value='#var1.getId()'/>"><s:property value="#st1.count+(pagination-1)*15"/></td><td><s:property value="#var1.getFieldName()"/></td><td><s:property value="#var1.getDisableDate().toString()"/></td><td><s:property value="#var1.getFieldNo()"/></td><td><s:property value="#var1.getDisableTime()"/></td><td><s:property value="#var1.getPauseUser()"/></td><td><s:property value="#var1.getSubmitTime()"/></td><td class="operation"><span class="delete">取消暂停</span></td>
			</tr>
		</s:iterator>
	</s:else>
	<tr class="trbottom">
		<td><span class="cl2">取消选定项</span></td><td colspan="6"><span class="sp1" data-no="1">首页</span>
			<s:bean name="org.apache.struts2.util.Counter">
				<s:param name="first" value="firstPagination"/>
				<s:param name="last" value="lastPagination"/>
				<s:iterator var="var4" status="st4">
					<span class="sp1" data-no="<s:property value='#var4'/>" <s:if test="#var4==pagination">id="slctd"</s:if>><s:property value="#var4"/></span>
				</s:iterator>
			</s:bean>
		<span class="sp1" data-no="<s:property value='pages'/>">末页</span> <span class="sp2">显示所有页码</span> 共<s:property value="pages"/>页</td><td class="optall"><span class="cl3">全部取消</span></td>
	</tr>
</table>

<div id="turnpage">
	<table id="turnto">
		<tr>
			<s:bean name="org.apache.struts2.util.Counter">
				<s:param name="first" value="1"/>
				<s:param name="last" value="pages"/>
				<s:iterator var="var5" status="st5">
					<td class="td3" data-no="<s:property value='#var5'/>" <s:if test="#var5==pagination">id="slctd2"</s:if>><s:property value="#var5"/></td>
					<s:if test="(#st5.count%25==0) && (#st5.count!=pages)">
						</tr><tr>
					</s:if>
				</s:iterator>
			</s:bean>
		</tr>
	</table>
</div>