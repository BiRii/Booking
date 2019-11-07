<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table id="tbtimeitem" cellspacing="0px">
	<tr class="trbottom">
		<th>行号</th><th>时间项</th><th>操作</th>
	</tr>
	<s:if test="subtimeItemList.isEmpty()">
		<tr class="norecord"><td colspan="3">系统无记录</td></tr>
	</s:if>
	<s:else>
	<s:iterator value="subtimeItemList" var="var1" status="st1">
		<tr class="trcontent">
			<td class="number" data-tname="<s:property value='#var1.getItem()'/>"><s:property value="#st1.count+(pagination-1)*15"/></td><td class="category"><span class="timename"><s:property value="#var1.getItem()"/></span></td><td class="operation"><span class="modify">修改</span> <span class="delete">删除</span></td>
		</tr>
	</s:iterator>
	</s:else>
	<tr><td colspan="2"></td><td><span class="addtimeitem">添加新项</span> <span class="addtimeitems">添加20项</span></td></tr>
	<tr class="trbottom">
		<td><span class="cl2">删除选定项</span></td><td><span class="sp1" data-no="1">首页</span>
			<s:bean name="org.apache.struts2.util.Counter">
				<s:param name="first" value="firstPagination"/>
				<s:param name="last" value="lastPagination"/>
				<s:iterator var="var4" status="st4">
					<span class="sp1" data-no="<s:property value='#var4'/>" <s:if test="#var4==pagination">id="slctd"</s:if>><s:property value="#var4"/></span>
				</s:iterator>
			</s:bean>
		<span class="sp1" data-no="<s:property value='pages'/>">末页</span> 共<s:property value="pages"/>页</td><td class="optall"><span class="cl3">全部删除</span></td>
	</tr>

</table>