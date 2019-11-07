<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table id="tbplayground" cellspacing="0px">
	<tr class="trbottom">
		<th>行号</th><th>场地名目</th><th>场地数</th><th>场地可订天数</th><th>管理员可订天数</th><th>场地可订状态</th><th>操作</th>
	</tr>
	<s:if test="subplaygroundList.isEmpty()">
		<tr class="norecord"><td colspan="7">系统无记录</td></tr>
	</s:if>
	<s:else>
	<s:iterator value="subplaygroundList" var="var1" status="st1">
		<tr class="trcontent">
			<td class="number" data-fname="<s:property value='#var1.getName()'/>"><s:property value="#st1.count+(pagination-1)*15"/></td><td class="category"><span class="fieldname"><s:property value="#var1.getName()"/></span></td><td class="quantity"><span class="fieldcount"><s:property value="#var1.getCount().toString()"/></span></td><td class="days"><span class="fielddays"><s:property value="#var1.getDayAmount()"/></span></td><td class="admindays"><span class="adminfielddays"><s:property value="#var1.getDayAmountofadmin()"/></span></td><td class="state"><span class="fieldstatus"><s:property value="#var1.getStatus()"/></span></td><td class="operation"><span class="modify">修改</span> <span class="delete">删除</span></td>
		</tr>
	</s:iterator>
	</s:else>
	<tr><td>统一设置</td><td></td><td><input type="number" name="FieldCounts" class="countinputs" min="1" required ></td><td><input type="number" name="FieldDayss" class="daysinputs" min="1" required ></td><td><input type="number" name="adminFieldDayss" class="admindaysinputs" min="1" required ></td><td><form><select name="statuss"><option value="可订">可订</option><option value="暂停预订">暂停预订</option></select></form></td><td><span class="addfield">添加新项</span> <span class="addfields">添加20项</span></td></tr>
	<tr class="trbottom">
		<td><span class="cl2">删除选定项</span></td><td colspan="5"><span class="sp1" data-no="1">首页</span>
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
					<td class="td1" data-no="<s:property value='#var5'/>" <s:if test="#var5==pagination">id="slctd2"</s:if>><s:property value="#var5"/></td>
					<s:if test="(#st5.count%25==0) && (#st5.count!=pages)">
						</tr><tr>
					</s:if>
				</s:iterator>
			</s:bean>
		</tr>
	</table>
</div>
