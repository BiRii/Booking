<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>预订查询及取消</title>

	<link rel="stylesheet" type="text/css" href="../css/query.css">

	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/query.js"></script>
	<script type="text/javascript" src="../js/messages_zh.js"></script>
	
</head>
<body>
	<div id="topdiv">
		欢迎您，<span id="usname"><s:property value="suname"/></span> <span class="under" id="logout">退出</span> <s:if test="prerogative==1"><span class="under" id="spanadmin">系统管理中心</span></s:if> <span class='under' id='returnhome'> 返回首页</span>
	</div>

	<div class="one">预订查询及取消</div>
	<br/>

<div id="query">
	<s:if test="str.equals('s3')">
		<h1 class="hint1">系统里没有活动项目名</h1>
	</s:if>
	<s:else>
		<table class="tbsite" cellspacing="3px">
			<tr class='trbgcolor0'>
				<s:iterator value="fieldName" var="var1" status="st1">
					<td <s:if test="#var1==selectedFieldName">class="selected"</s:if> ><s:property value="#var1"/></td>
					<s:if test="#st1.count%14==0">
						</tr><tr class="trbgcolor<s:property value='#st1.count/15'/>">
					</s:if>
				</s:iterator>
			</tr>
		</table>
		<br/>

		<table class="tbdate" cellspacing="3px">
			<tr class="DateTRcolor0">
				<s:iterator value="dateweek" var="var2" status="st2" >
					<td <s:if test="#var2.startsWith(selectedDate)">class="selected"</s:if>><s:property value="#var2"/></td>
					<s:if test="#st2.count%11==0">
						</tr><tr class="DateTRcolor<s:property value='#st2.count/11'/>">
					</s:if>
				</s:iterator>
			</tr>
		</table>
		<br/>
		
		<s:if test="str.equals('s2')">
			<h1 class="hint1">您还没预订过上面指定条件的场地</h1>
		</s:if>		
		<s:else>
			<table id="tbbooking" cellspacing="3px">
				<tr>
					<th>序号</th><th>预订项目</th><th>预订日期</th><th>场地号</th><th>时间段</th><th>提交时间</th><th>操作</th>
				</tr>
				<s:iterator value="subbookList" var="var3" status="st3">
					<tr>
						<td class="t1 selectable" data-bookid="<s:property value='#var3.getBookId()'/>" data-row="<s:property value='#st3.count'/>"><s:property value="#st3.count+(pagination-1)*10"/></td><td class="selectable" data-row="<s:property value='#st3.count'/>"><s:property value="#var3.getBookField()"/></td><td class="selectable" data-row="<s:property value='#st3.count'/>"><s:property value="#var3.getBookDate().toString()"/></td><td class="selectable" data-row="<s:property value='#st3.count'/>"><s:property value="#var3.getBookFieldNo()"/></td><td class="selectable" data-row="<s:property value='#st3.count'/>"><s:property value="#var3.getBookTime()"/></td><td class="selectable" data-row="<s:property value='#st3.count'/>"><s:property value="#var3.getSubmitTime()"/></td><td class="cl cl1" data-bookid="<s:property value='#var3.getBookId()'/>">取消预订</td>
					</tr>
				</s:iterator>
				<tr>
					<td class="t1 cl2">取消选定预订</td><td class="t2" colspan="5"><span class="sp1" data-no="1">首页</span>
						<s:bean name="org.apache.struts2.util.Counter">
							<s:param name="first" value="firstPagination"/>
							<s:param name="last" value="lastPagination"/>
							<s:iterator var="var4" status="st4">
								<span class="sp1" data-no="<s:property value='#var4'/>" <s:if test="#var4==pagination">id="slctd"</s:if>><s:property value="#var4"/></span>
							</s:iterator>
						</s:bean>
					<span class="sp1" data-no="<s:property value='pages'/>">末页</span> <span class="sp2">显示所有页码</span> 共<s:property value="pages"/>页</td><td class="cl cl3">全部取消</td>
				</tr>
			</table>
		</s:else>
	</s:else>

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

</div>

</body>
</html>