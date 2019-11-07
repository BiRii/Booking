$(document).ready(function(){

/*******①返回首页********/
	$("div#topdiv").on("click","span#returnhome",function(){
		window.location.assign("/");
	});

/********②场地名目管理块********/
	$("table#toptable").on("click","td#fielditem",function(){
		$(this).siblings().removeClass("selected");
		
		if(!$(this).hasClass("selected"))
		{
			$(this).addClass("selected");
		}

		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"queryplayground.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/***③点击页码查询场地名目********/
	$("div#showall").on("click","table#tbplayground span.sp1",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"queryplayground.action",
			data:
			{
				username:usname,
				pagination:$(this).attr("data-no")
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/***④显示所有页码页************/
	$("div#showall").on("mouseenter","span.sp2",function(){
		$("div#turnpage").css("display","block");
	});

	$("div#showall").on("mouseleave","div#turnpage",function(){
		$("div#turnpage").css("display","none");
	});

/***⑤点击页码查询场地名目2********/
	$("div#showall").on("click","table#turnto td.td1",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"queryplayground.action",
			data:
			{
				username:usname,
				pagination:$(this).text()
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/***⑥修改场地名********/
	$("div#showall").on("click","table#tbplayground span.fieldname",function(){
		var n = $(this).text();
		var td = $(this).parent();
		td.html("<input type='text' name='FieldName' class='fieldinput' required >");
		td.siblings("td.operation").html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='delete'>删除</span>");
		td.children("input.fieldinput").focus().val(n);
	});
/***⑦修改场地数量********/
	$("div#showall").on("click","table#tbplayground span.fieldcount",function(){
		var n = $(this).text();
		var td = $(this).parent();
		td.html("<input type='number' name='FieldCount' class='countinput' min='1' required >");
		td.siblings("td.operation").html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='delete'>删除</span>");
		td.children("input.countinput").focus().val(n);
	});
/***⑧修改场地天数********/
	$("div#showall").on("click","table#tbplayground span.fielddays",function(){
		var n = $(this).text();
		var td = $(this).parent();
		td.html("<input type='number' name='FieldDays' class='daysinput' min='1' required >");
		td.siblings("td.operation").html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='delete'>删除</span>");
		td.children("input.daysinput").focus().val(n);
	});

/***(66)点击修改管理员可订天数********/
	$("div#showall").on("click","table#tbplayground span.adminfielddays",function(){
		var n = $(this).text();
		var td = $(this).parent();
		td.html("<input type='number' name='adminFieldDays' class='admindaysinput' min='1' required >");
		td.siblings("td.operation").html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='delete'>删除</span>");
		td.children("input.admindaysinput").focus().val(n);
	});

/***⑨修改场地可订状态********/
	$("div#showall").on("click","table#tbplayground span.fieldstatus",function(){
		var n = $(this).text();
		var td = $(this).parent();
		if(n=="可订")
		{td.html("<form><select name='status'><option value='可订'>可订</option><option value='暂停预订'>暂停预订</option></select></form>");}
		else
		{td.html("<form><select name='status'><option value='暂停预订'>暂停预订</option><option value='可订'>可订</option></select></form>");}
		td.siblings("td.operation").html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='delete'>删除</span>");
	});
/***⑩点击修改一行********/
	$("div#showall").on("click","table#tbplayground span.modify",function(){
		var td = $(this).parent();

		var f = td.siblings("td.category").children("span.fieldname").text();
		var c = td.siblings("td.quantity").children("span.fieldcount").text();
		var d = td.siblings("td.days").children("span.fielddays").text();
		var ad = td.siblings("td.admindays").children("span.adminfielddays").text();
		var s = td.siblings("td.state").children("span.fieldstatus").text();

		td.siblings("td.category").html("<input type='text' name='FieldName' class='fieldinput' required >");
		td.siblings("td.quantity").html("<input type='number' name='FieldCount' class='countinput' min='1' required value="+c+">");
		td.siblings("td.days").html("<input type='number' name='FieldDays' class='daysinput' min='1' required value="+d+">");
		td.siblings("td.admindays").html("<input type='number' name='adminFieldDays' class='admindaysinput' min='1' required value="+ad+">");

		if(s=="可订")
		{td.siblings("td.state").html("<form><select name='status'><option value='可订'>可订</option><option value='暂停预订'>暂停预订</option></select></form>");}
		else
		{td.siblings("td.state").html("<form><select name='status'><option value='暂停预订'>暂停预订</option><option value='可订'>可订</option></select></form>");}

		td.html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='delete'>删除</span>");

		td.siblings("td.category").children("input.fieldinput").focus().val(f);
	});
/***⑾点击保存修改一行场地项********/
	$("div#showall").on("click","table#tbplayground span.savemodify",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();
		var num = td.siblings("td.number").text();
		var currentfname = td.siblings("td.number").attr("data-fname");
		var f = td.siblings("td.category").children("input").val();
		var c = td.siblings("td.quantity").children("input").val();
		var d = td.siblings("td.days").children("input").val();
		var ad = td.siblings("td.admindays").children("input").val();
		var s = td.siblings("td.state").find("select").val();

		if(f!=undefined)
		{
			f = $.trim(f);
			if(f=="")
			{
				alert("场地名不能为空");
				return;
			}
		}

		if(c!=undefined)
		{
			c = $.trim(c);
			if(isNaN(c))
			{
				alert("场地数处请填写纯数字");
				return;
			}
			else
			{
				if(c=="")
				{
					alert("场地数不能为空");
					return;
				}
			}
		}

		if(d!=undefined)
		{
			d = $.trim(d);
			if(isNaN(d))
			{
				alert("可订天数处请填写纯数字");
				return;
			}
			else
			{
				if(d=="")
				{
					alert("可订天数不能为空");
					return;
				}	
			}
		}

		if(ad!=undefined)
		{
			ad = $.trim(ad);

			if(isNaN(ad))
			{
				alert("管理员可订天数处请填写纯数字");
				return;
			}
			else
			{
				if(ad=="")
				{
					alert("管理员可订天数不能为空");
					return;
				}
			}
		}

		var r=confirm("确定保存吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"savemdfield.action",
			data:
			{
				nowfname:currentfname,
				username:usname,
				fname:f,
				fcount:c,
				fdays:d,
				fadmindays:ad,
				fstatus:s
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s4")
				{
					alert("该条目已不存在");
					td.parent().remove();
				}
				else
				{
					alert("修改成功");
					var r = new Array();
					r = rs.split(" ");
					td.parent().html("<td class='number' data-fname='"+r[0]+"'>"+num+"</td><td class='category'><span class='fieldname'>"+r[0]+"</span></td><td class='quantity'><span class='fieldcount'>"+r[1]+"</span></td><td class='days'><span class='fielddays'>"+r[2]+"</span></td><td class='admindays'><span class='adminfielddays'>"+r[3]+"</span></td><td class='state'><span class='fieldstatus'>"+r[4]+"</span></td><td class='operation'><span class='modify'>修改</span> <span class='delete'>删除</span></td>");
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});
/***⑿点击放弃修改一行********/
	$("div#showall").on("click","table#tbplayground span.discardmodify",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();
		var num = td.siblings("td.number").text();
		var currentfname = td.siblings("td.number").attr("data-fname");

		$.ajax({
			type:"post",
			url:"discardmodify.action",
			data:
			{
				username:usname,
				nowfname:currentfname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s4")
				{
					alert("该条目已不存在");
					td.parent().remove();
				}
				else
				{
					var r = new Array();
					r = rs.split(" ");
					td.parent().html("<td class='number' data-fname='"+r[0]+"'>"+num+"</td><td class='category'><span class='fieldname'>"+r[0]+"</span></td><td class='quantity'><span class='fieldcount'>"+r[1]+"</span></td><td class='days'><span class='fielddays'>"+r[2]+"</span></td><td class='admindays'><span class='adminfielddays'>"+r[3]+"</span></td><td class='state'><span class='fieldstatus'>"+r[4]+"</span></td><td class='operation'><span class='modify'>修改</span> <span class='delete'>删除</span></td>");
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});
/***⒀点击删除一行********/
	$("div#showall").on("click","table#tbplayground span.delete",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();
		var currentfname = td.siblings("td.number").attr("data-fname");

		var r=confirm("确定删除吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"deletefield.action",
			data:
			{
				username:usname,
				nowfname:currentfname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s4")
				{
					alert("该条目已不存在");
					td.parent().remove();
				}
				else
				{
					alert("删除成功");
					td.parent().remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});
/***(27)点击选定一行********/
	$("div#showall").on("click","table#tbplayground td.number",function(){
		var tr = $(this).parent();

		if(tr.hasClass("slct"))
		{
			tr.removeClass("slct");
		}
		else
		{
			tr.addClass("slct");
		}
	});
/***⒁点击删除选定行********/
	$("div#showall").on("click","table#tbplayground span.cl2",function(){
		var x = $("table#tbplayground tr.slct td.number").length;
		if(x==0)
		{
			alert("请先点击相应行号进行选定！");
			return;
		}

		var fname;
		var fnames = new Array();
		for(var y=0; y<x; y++)
		{
			fname = $("table#tbplayground tr.slct td.number:eq("+y+")").attr("data-fname");
			fnames[y] = fname;
		}

		var usname = $("span#usname").text();

		var r=confirm("确定删除选定项吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"delsltdfield.action",
			traditional:true,
			data:
			{
				username:usname,
				fieldNames:fnames
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else
				{
					alert("删除成功");
					$("table#tbplayground tr.slct").remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});

	});
/***⒂点击删除全部********/
	$("div#showall").on("click","table#tbplayground span.cl3",function(){
		var r=confirm("确定要全部删除吗？\n所有页的条目都将被删除，包括正在编辑和添加的条目");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"deleteallfields.action",
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("系统无记录，没删除任何系统记录项，但正在编辑和添加的条目会被移除");
					$("table#toptable td#fielditem").click();
				}
				else
				{
					alert("删除成功");
					$("table#toptable td#fielditem").click();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});

	});
/***⒃点击添加一行********/
	$("div#showall").on("click","table#tbplayground span.addfield",function(){
		var tr = $(this).parents("tr");
		
		if(tr.prev().hasClass("traddfield"))
		{
			var num = tr.prev().children("td.newnumber").text();
			num++;
		}
		else
		{
			var num = 1;
		}
		
		tr.before("<tr class='trcontent traddfield'><td class='newnumber'>"+num+"</td><td><input type='text' name='FieldName' class='fieldinput' required ></td><td><input type='number' name='FieldCount' class='countinput' min='1' required ></td><td><input type='number' name='FieldDays' class='daysinput' min='1' required ></td><td><input type='number' name='adminFieldDays' class='admindaysinput' min='1' required ></td><td><form><select name='status'><option value='可订'>可订</option><option value='暂停预订'>暂停预订</option></select></form></td><td><span class='saveaddfield'>保存添加</span> <span class='discardaddfield'>放弃添加</span></td></tr>");
		tr.prev().find("input.fieldinput").focus();

		var x = $("table#tbplayground td.optall span.savealladdfield").length;
		if(x==0)
		{
			$("div#showall table#tbplayground td.optall span.cl3").after(" <span class='savealladdfield'>保存全部添加</span> <span class='discardalladdfield'>放弃全部添加</span>");
		}
	});
/***⒄点击保存添加一行********/
	$("div#showall").on("click","table#tbplayground span.saveaddfield",function(){
		var tr = $(this).parents("tr");
		var f = $.trim(tr.find("input.fieldinput").val());
		var c = $.trim(tr.find("input.countinput").val());
		var d = $.trim(tr.find("input.daysinput").val());
		var ad2 = $.trim(tr.find("input.admindaysinput").val());
		var s = tr.find("select").val();
		
		if(f=="")
		{
			alert("场地名不能为空");
			tr.find("input.fieldinput").focus();
			return;
		}

		if(c=="")
		{
			alert("场地数不能为空");
			tr.find("input.countinput").focus();
			return;
		}
		else
		{
			if(isNaN(c))
			{
				alert("场地数处请填写纯数字");
				tr.find("input.countinput").focus();
				return;
			}
		}

		if(d=="")
		{
			alert("可订天数不能为空");
			tr.find("input.daysinput").focus();
			return;
		}	
		else
		{
			if(isNaN(d))
			{
				alert("可订天数处请填写纯数字");
				tr.find("input.daysinput").focus();
				return;
			}
		}

		if(ad2=="")
		{
			alert("管理员可订天数不能为空");
			tr.find("input.admindaysinput").focus();
			return;
		}	
		else
		{
			if(isNaN(ad2))
			{
				alert("管理员可订天数处请填写纯数字");
				tr.find("input.admindaysinput").focus();
				return;
			}
		}

		var r=confirm("确定保存吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"saveaddfield.action",
			data:
			{
				fname:f,
				fcount:c,
				fdays:d,
				fadmindays:ad2,
				fstatus:s
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s2")
				{
					alert("系统中已存在同名项目，不能再添加，请修改场地名");
					tr.find("input.fieldinput").focus();
				}
				else
				{
					alert("保存成功");
					tr.html("<td class='number' data-fname='"+f+"'></td><td class='category'><span class='fieldname'>"+f+"</span></td><td class='quantity'><span class='fieldcount'>"+c+"</span></td><td class='days'><span class='fielddays'>"+d+"</span></td><td class='admindays'><span class='adminfielddays'>"+ad2+"</span></td><td class='state'><span class='fieldstatus'>"+s+"</span></td><td class='operation'><span class='modify'>修改</span> <span class='delete'>删除</span></td>");
					tr.removeClass("traddfield");

					if($("table#tbplayground tr").hasClass("norecord"))
					{
						$("table#tbplayground tr.norecord").remove();
					}

					var x2 = $("table#tbplayground tr.traddfield").length;
					if(x2==0)
					{
						$("div#showall table#tbplayground td.optall").html("<span class='cl3'>全部删除</span>");
					}
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});

	});

/***⒅点击放弃添加一行********/
	$("div#showall").on("click","table#tbplayground span.discardaddfield",function(){
		$(this).parents("tr").remove();

		var x = $("table#tbplayground tr.traddfield").length;
		if(x==0)
		{
			$("div#showall table#tbplayground td.optall").html("<span class='cl3'>全部删除</span>");
		}
	});

/***⒆点击添加多行********/
	$("div#showall").on("click","table#tbplayground span.addfields",function(){
		var tr = $(this).parents("tr");

		if(tr.prev().hasClass("traddfield"))
		{
			var num = tr.prev().children("td.newnumber").text();
			num++;
		}
		else
		{
			var num = 1;
		}

		for(var i=0; i<20; i++)
		{
			tr.before("<tr class='trcontent traddfield'><td class='newnumber'>"+num+"</td><td><input type='text' name='FieldName' class='fieldinput' required ></td><td><input type='number' name='FieldCount' class='countinput' min='1' required ></td><td><input type='number' name='FieldDays' class='daysinput' min='1' required ></td><td><input type='number' name='adminFieldDays' class='admindaysinput' min='1' required ></td><td><form><select name='status'><option value='可订'>可订</option><option value='暂停预订'>暂停预订</option></select></form></td><td><span class='saveaddfield'>保存添加</span> <span class='discardaddfield'>放弃添加</span></td></tr>");
			num++;
		}
		tr.prev().find("input.fieldinput").focus();

		var x = $("table#tbplayground td.optall span.savealladdfield").length;
		if(x==0)
		{
			$("div#showall table#tbplayground td.optall span.cl3").after(" <span class='savealladdfield'>保存全部添加</span> <span class='discardalladdfield'>放弃全部添加</span>");
		}
	});
/***⒇点击放弃全部添加********/
	$("div#showall").on("click","table#tbplayground span.discardalladdfield",function(){
		var r=confirm("确定放弃全部添加吗？");
		if(r==false)
		{
			return;
		}

		$("table#tbplayground tr.traddfield").remove();
		
		var x = $("table#tbplayground tr.traddfield").length;
		if(x==0)
		{
			$("div#showall table#tbplayground td.optall").html("<span class='cl3'>全部删除</span>");
		}

	});
/***(21)点击保存全部添加********/
	$("div#showall").on("click","table#tbplayground span.savealladdfield",function(){
		var x = $("table#tbplayground tr.traddfield").length;
		if(x==0)
		{
			alert("不存在需添加的条目，不能保存");
			return;
		}

		var f;
		var c;
		var d;
		var ad3;
		var s;
		var fnames = new Array();
		var fcounts = new Array();
		var fdays = new Array();
		var fadmindays = new Array();
		var fstatus = new Array();

		var m = 0;
		
		for(var y=0; y<x; y++)
		{
			f = $.trim($("table#tbplayground tr.traddfield input.fieldinput:eq("+y+")").val());

			if(f!="")
			{
				c = $.trim($("table#tbplayground tr.traddfield input.countinput:eq("+y+")").val());
				d = $.trim($("table#tbplayground tr.traddfield input.daysinput:eq("+y+")").val());
				ad3 = $.trim($("table#tbplayground tr.traddfield input.admindaysinput:eq("+y+")").val());
				s = $("table#tbplayground tr.traddfield select[name=status]:eq("+y+")").val();

				if(c=="")
				{
					alert("场地数不能为空");
					$("table#tbplayground tr.traddfield input.countinput:eq("+y+")").focus();
					return;
				}
				else
				{
					if(isNaN(c))
					{
						alert("场地数处请填写纯数字");
						$("table#tbplayground tr.traddfield input.countinput:eq("+y+")").focus();
						return;
					}
				}

				if(d=="")
				{
					alert("可订天数不能为空");
					$("table#tbplayground tr.traddfield input.daysinput:eq("+y+")").focus();
					return;
				}
				else
				{
					if(isNaN(d))
					{
						alert("可订天数处请填写纯数字");
						$("table#tbplayground tr.traddfield input.daysinput:eq("+y+")").focus();
						return;
					}
				}

				if(ad3=="")
				{
					alert("管理员可订天数不能为空");
					$("table#tbplayground tr.traddfield input.admindaysinput:eq("+y+")").focus();
					return;
				}
				else
				{
					if(isNaN(ad3))
					{
						alert("管理员可订天数处请填写纯数字");
						$("table#tbplayground tr.traddfield input.admindaysinput:eq("+y+")").focus();
						return;
					}
				}

				fnames[m] = f;
				fcounts[m] = c;
				fdays[m] = d;
				fadmindays[m] = ad3;
				fstatus[m] = s;
				
				m++;
			}
		}

		if(fnames.length==0)
		{
			alert("所有场地名都为空，请至少填写一个");
			return;
		}

		if(fnames.length > 1)
		{
			for(var i=0; i<fnames.length-1; i++)
			{
				for(var n=i+1; n<fnames.length; n++)
				{
					if(fnames[i]===fnames[n])
					{
						alert("【"+fnames[i]+"】存在同名，请修改");
						return;
					}
				}
			}
		}

		var r=confirm("确定保存吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"savealladdfield.action",
			traditional:true,
			data:
			{
				fieldnames:fnames,
				fieldcounts:fcounts,
				fielddays:fdays,
				fieldadmindays:fadmindays,
				fieldstatus:fstatus
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s5")
				{
					alert("系统得到的场地名为空，出现程序异常");					
				}
				else if(rs.substring(0,2)=="s4")
				{
					var samename = rs.split(" ",2)[1];
					alert("提交的场地名存在同名【"+samename+"】，不能添加，请修改场地名");
				}
				else if(rs.split(" ",1)[0]=="s2")
				{
					var samename = rs.split(" ",2)[1];
					alert("系统中存在同名【"+samename+"】，不能添加，请修改场地名");					
				}
				else
				{
					alert("保存成功");
					$("table#toptable td#fielditem").click();
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});

	});

/********(22)时间项管理块********/
	$("table#toptable").on("click","td#timeitem",function(){
		var usname = $("span#usname").text();

		if(!$(this).hasClass("selected"))
		{
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");
		}

		$.ajax({
			type:"post",
			url:"querytimeitem.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/******(23)修改时间项********/
	$("div#showall").on("click","table#tbtimeitem span.timename",function(){
		var n = $(this).text();
		var td = $(this).parent();
		td.html("<input type='text' name='TimeName' class='timeinput' required >");
		td.siblings("td.operation").html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='delete'>删除</span>");
		td.children("input.timeinput").focus().val(n);
	});

/******(24)点击保存修改一行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.savemodify",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();
		var num = td.siblings("td.number").text();
		var currenttimename = td.siblings("td.number").attr("data-tname");
		var t = td.siblings("td.category").children("input").val();

		if($.trim(t)=="" || t==undefined)
		{
			alert("时间项不能为空");
			return;
		}
		else
		{
			t = $.trim(t);
		}

		var r=confirm("确定保存吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"savemdtime.action",
			data:
			{
				username:usname,
				nowtimename:currenttimename,
				timename:t
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s6")
				{
					alert("系统中已存在同名时间段，请修改");
					td.siblings("td.category").children("input").focus();
				}
				else if(rs=="s4")
				{
					alert("该条目已不存在");
					td.parent().remove();
				}
				else if(rs=="s5")
				{
					alert("程序异常，需保存值为空");
				}
				else
				{
					alert("修改成功");
					td.parent().html("<td class='number' data-tname="+t+">"+num+"</td><td class='category'><span class='timename'>"+t+"</span></td><td class='operation'><span class='modify'>修改</span> <span class='delete'>删除</span></td>");
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});

	});

/***(25)点击修改一行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.modify",function(){
		var td = $(this).parent();
		var t = td.siblings("td.category").children("span.timename").text();
		td.siblings("td.category").html("<input type='text' name='TimeName' class='timeinput' required >");
		td.html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='delete'>删除</span>");
		td.siblings("td.category").children("input.timeinput").focus().val(t);
	});

/***(26)点击放弃修改一行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.discardmodify",function(){
		var td = $(this).parent();
		var curtname = td.siblings("td.number").attr("data-tname");
		td.siblings("td.category").html("<span class='timename'>"+curtname+"</span>");
		td.html("<span class='modify'>修改</span> <span class='delete'>删除</span>");
	});

/***(28)点击删除一行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.delete",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();
		var currenttname = td.siblings("td.number").attr("data-tname");

		var r=confirm("确定删除吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"deletetimeitem.action",
			data:
			{
				username:usname,
				nowtimename:currenttname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s4")
				{
					alert("系统中已不存在该条目");
					td.parent().remove();
				}
				else
				{
					alert("删除成功");
					td.parent().remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/***(29)点击选定一行时间项********/
	$("div#showall").on("click","table#tbtimeitem td.number",function(){
		var tr = $(this).parent();

		if(tr.hasClass("slct"))
		{
			tr.removeClass("slct");
		}
		else
		{
			tr.addClass("slct");
		}
	});

/***(30)点击删除选定行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.cl2",function(){
		var x = $("table#tbtimeitem tr.slct td.number").length;
		if(x==0)
		{
			alert("请先点击相应行号进行选定！");
			return;
		}

		var tname;
		var tnames = new Array();
		for(var y=0; y<x; y++)
		{
			tname = $("table#tbtimeitem tr.slct td.number:eq("+y+")").attr("data-tname");			
			tnames[y] = tname;
		}

		var usname = $("span#usname").text();

		var r=confirm("确定删除选定项吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"delsltdtime.action",
			traditional:true,
			data:
			{
				username:usname,
				timeItemNames:tnames
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s4")
				{
					alert("系统无记录，没删除任何系统记录项");
					$("table#tbtimeitem tr.slct").remove();
				}
				else
				{
					alert("删除成功");
					$("table#tbtimeitem tr.slct").remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(31)点击添加一行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.addtimeitem",function(){
		var tr = $(this).parents("tr");
		
		if(tr.prev().hasClass("traddtimeitem"))
		{
			var num = tr.prev().children("td.newnumber").text();
			num++;
		}
		else
		{
			var num = 1;
		}

		tr.before("<tr class='trcontent traddtimeitem'><td class='newnumber'>"+num+"</td><td><input type='text' name='TimeName' class='timeinput' required ></td><td><span class='saveaddtimeitem'>保存添加</span> <span class='discardaddtimeitem'>放弃添加</span></td></tr>");
		tr.prev().find("input.timeinput").focus();

		var x = $("table#tbtimeitem td.optall span.savealladdtimeitem").length;
		if(x==0)
		{
			$("div#showall table#tbtimeitem td.optall span.cl3").after(" <span class='savealladdtimeitem'>保存全部添加</span> <span class='discardalladdtimeitem'>放弃全部添加</span>");
		}
	});

/***(32)点击添加多行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.addtimeitems",function(){
		var tr = $(this).parents("tr");

		if(tr.prev().hasClass("traddtimeitem"))
		{
			var num = tr.prev().children("td.newnumber").text();
			num++;
		}
		else
		{
			var num = 1;
		}

		for(var i=0; i<20; i++)
		{
			tr.before("<tr class='trcontent traddtimeitem'><td class='newnumber'>"+num+"</td><td><input type='text' name='TimeName' class='timeinput' required ></td><td><span class='saveaddtimeitem'>保存添加</span> <span class='discardaddtimeitem'>放弃添加</span></td></tr>");
			num++;
		}
		tr.prev().find("input.timeinput").focus();

		var x = $("table#tbtimeitem td.optall span.savealladdtimeitem").length;
		if(x==0)
		{
			$("div#showall table#tbtimeitem td.optall span.cl3").after(" <span class='savealladdtimeitem'>保存全部添加</span> <span class='discardalladdtimeitem'>放弃全部添加</span>");
		}
	});

/***(33)点击保存添加一行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.saveaddtimeitem",function(){
		var usname = $("span#usname").text();

		var tr = $(this).parents("tr");
		var t = $.trim(tr.find("input.timeinput").val());
		
		if(t=="")
		{
			alert("时间项不能为空");
			tr.find("input.timeinput").focus();
			return;
		}

		var r=confirm("确定保存吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"saveaddtimeitem.action",
			data:
			{
				username:usname,
				timename:t
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s4")
				{
					alert("系统中已存在同名项目，不能再添加，请修改时间段");
					tr.find("input.timeinput").focus();
				}
				else
				{
					alert("保存成功");
					tr.html("<td class='number' data-tname='"+t+"'></td><td class='category'><span class='timename'>"+t+"</span></td><td class='operation'><span class='modify'>修改</span> <span class='delete'>删除</span></td>");
					tr.removeClass("traddtimeitem");

					if($("table#tbtimeitem tr").hasClass("norecord"))
					{
						$("table#tbtimeitem tr.norecord").remove();
					}

					var x3 = $("table#tbtimeitem tr.traddtimeitem").length;
					if(x3==0)
					{
						$("div#showall table#tbtimeitem td.optall").html("<span class='cl3'>全部删除</span>");
					}

				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});

	});

/***(34)点击放弃添加一行时间项********/
	$("div#showall").on("click","table#tbtimeitem span.discardaddtimeitem",function(){
		$(this).parents("tr").remove();
		var x = $("table#tbtimeitem tr.traddtimeitem").length;
		if(x==0)
		{
			$("div#showall table#tbtimeitem td.optall").html("<span class='cl3'>全部删除</span>");
		}
	});

/***(35)点击删除全部时间项********/
	$("div#showall").on("click","table#tbtimeitem span.cl3",function(){
		var r=confirm("确定要全部删除吗？\n所有页的、系统中存在的条目都将被删除");
		if(r==false)
		{
			return;
		}

		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"deletealltimeitems.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("系统无记录，没删除任何系统记录项");
					$("table#tbtimeitem td.number").parent().remove();
				}
				else
				{
					alert("删除成功");
					$("table#tbtimeitem td.number").parent().remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});

	});

/***(36)点击保存全部添加时间项********/
	$("div#showall").on("click","table#tbtimeitem span.savealladdtimeitem",function(){
		var usname = $("span#usname").text();

		var x = $("table#tbtimeitem tr.traddtimeitem").length;
		if(x==0)
		{
			alert("不存在需添加的条目，不能保存");
			return;
		}

		var t;
		var tnames = new Array();

		var m = 0;
		
		for(var y=0; y<x; y++)
		{
			t = $.trim($("table#tbtimeitem tr.traddtimeitem input.timeinput:eq("+y+")").val());

			if(t!="")
			{
				tnames[m] = t;				
				m++;
			}
		}

		if(tnames.length==0)
		{
			alert("所有时间项都为空，请至少填写一个");
			return;
		}

		for(var i=0; i<tnames.length-1; i++)
		{
			for(var n=i+1; n<tnames.length; n++)
			{
				if(tnames[i]===tnames[n])
				{
					alert("【"+tnames[i]+"】存在同名，请修改");
					return;
				}
			}
		}

		var r=confirm("确定保存吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"savealladdtimeitems.action",
			traditional:true,
			data:
			{
				username:usname,
				timenames:tnames
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("系统得到的时间项为空，出现程序异常");					
				}
				else if(rs.substring(0,2)=="s5")
				{
					var samename = rs.split(" ",2)[1];
					alert("提交的时间项存在同名【"+samename+"】，不能添加，请修改时间项");
				}
				else if(rs.split(" ",1)[0]=="s6")
				{
					var samename = rs.split(" ",2)[1];
					alert("系统中存在同名【"+samename+"】，不能添加，请修改时间项");					
				}
				else
				{
					alert("保存成功");
					$("table#toptable td#timeitem").click();
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});

	});

/***(37)点击放弃全部添加时间项********/
	$("div#showall").on("click","table#tbtimeitem span.discardalladdtimeitem",function(){
		var r=confirm("确定放弃全部添加吗？");
		if(r==false)
		{
			return;
		}

		$("table#tbtimeitem tr.traddtimeitem").remove();
		
		var x = $("table#tbtimeitem tr.traddtimeitem").length;
		if(x==0)
		{
			$("div#showall table#tbtimeitem td.optall").html("<span class='cl3'>全部删除</span>");
		}
	});

/***(38)点击页码查询时间项********/
	$("div#showall").on("click","table#tbtimeitem span.sp1",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"querytimeitem.action",
			data:
			{
				username:usname,
				pagination:$(this).attr("data-no")
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/********(39)预订管理块********/
	$("table#toptable").on("click","td#bookitem",function(){
		var usname = $("span#usname").text();

		if(!$(this).hasClass("selected"))
		{
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");
		}

		$.ajax({
			type:"post",
			url:"querybookitem.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});

	});

/***(40)点击删除一行预订********/
	$("div#showall").on("click","table#tbbookitem span.delete",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();

		var booid = new Array();

		booid[0] = td.siblings("td.number").attr("data-id");

		var r=confirm("确定删除吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"delbookitems.action",
			traditional:true,
			data:
			{
				username:usname,
				bookIDs:booid
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("系统中已不存在该条目");
					td.parent().remove();
				}
				else if(rs.split(" ",1)[0]=="s5")
				{
					alert("该预订日期已过，不能再删除");
					$("table#toptable td#bookitem").click();
				}
				else if(rs.split(" ",1)[0]=="s6")
				{
					alert("该预订时间段已过，不能再删除");
					$("table#toptable td#bookitem").click();
				}
				else
				{
					alert("删除成功");
					td.parent().remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(41)点击选定一行预订项********/
	$("div#showall").on("click","table#tbbookitem td.number",function(){
		var tr = $(this).parent();

		if(tr.hasClass("slct"))
		{
			tr.removeClass("slct");
		}
		else
		{
			tr.addClass("slct");
		}
	});

/***(42)点击删除选定行预订项********/
	$("div#showall").on("click","table#tbbookitem span.cl2",function(){
		var x = $("table#tbbookitem tr.slct td.number").length;
		if(x==0)
		{
			alert("请先点击相应行号进行选定！");
			return;
		}

		var bookids = new Array();
		for(var y=0; y<x; y++)
		{
			bookids[y] = $("table#tbbookitem tr.slct td.number:eq("+y+")").attr("data-id");			
		}

		var usname = $("span#usname").text();

		var r=confirm("确定删除选定项吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"delbookitems.action",
			traditional:true,
			data:
			{
				username:usname,
				bookIDs:bookids
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s4")
				{
					alert("系统无记录，没删除任何系统记录项");
					$("table#tbbookitem tr.slct").remove();
				}
				else if(rs.split(" ",1)[0]=="s5")
				{
					var pastdate = rs.split(" ",2)[1];
					alert("此预订日期【"+pastdate+"】已过，不能再删除");
					$("table#toptable td#bookitem").click();
				}
				else if(rs.split(" ",1)[0]=="s6")
				{
					var pasttime = rs.split(" ",2)[1];
					alert("此预订时间段【"+pasttime+"】已过，不能再删除");
					$("table#toptable td#bookitem").click();
				}
				else
				{
					alert("删除成功");
					$("table#tbbookitem tr.slct").remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(43)点击删除全部预订项********/
	$("div#showall").on("click","table#tbbookitem span.cl3",function(){
		var r=confirm("确定要全部删除吗？\n所有页的、系统中存在的条目都将被删除");
		if(r==false)
		{
			return;
		}

		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"delallbookitems.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("系统中无符合条件的记录，没有删除任何预订项");
					$("table#tbbookitem td.number").parent().remove();
				}
				else
				{
					alert("删除成功");
					$("table#tbbookitem td.number").parent().remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});

	});

/***(44)点击页码查询预订项********/
	$("div#showall").on("click","table#tbbookitem span.sp1",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"querybookitem.action",
			data:
			{
				username:usname,
				pagination:$(this).attr("data-no")
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(45)点击页码查询预订项2********/
	$("div#showall").on("click","table#turnto td.td3",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"querybookitem.action",
			data:
			{
				username:usname,
				pagination:$(this).attr("data-no")
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/********(46)用户管理块********/
	$("table#toptable").on("click","td#useritem",function(){
		var usname = $("span#usname").text();

		if(!$(this).hasClass("selected"))
		{
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");
		}

		$.ajax({
			type:"post",
			url:"queryuseritem.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(47)点击删除一行用户项********/
	$("div#showall").on("click","table#tbuseritem span.delete",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();

		var bookusname = new Array();

		bookusname[0] = td.siblings("td.busername").text();

		var r=confirm("确定删除吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"deleteuseritems.action",
			traditional:true,
			data:
			{
				username:usname,
				bookUserNames:bookusname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s5")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s6")
				{
					alert("系统接收到的需删除帐户为空");
				}
				else if(rs=="s4")
				{
					alert("系统中已不存在该条目");
					td.parent().remove();
				}
				else
				{
					alert("删除成功");
					td.parent().remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(48)点击选定一行用户项********/
	$("div#showall").on("click","table#tbuseritem td.number",function(){
		var tr = $(this).parent();

		if(tr.hasClass("slct"))
		{
			tr.removeClass("slct");
		}
		else
		{
			tr.addClass("slct");
		}
	});

/***(49)点击删除选定行用户项********/
	$("div#showall").on("click","table#tbuseritem span.cl2",function(){
		var x = $("table#tbuseritem tr.slct td.busername").length;
		if(x==0)
		{
			alert("请先点击相应行号进行选定！");
			return;
		}

		var busernames = new Array();
		for(var y=0; y<x; y++)
		{
			busernames[y] = $("table#tbuseritem tr.slct td.busername:eq("+y+")").text();
		}

		var usname = $("span#usname").text();

		var r=confirm("确定删除选定项吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"deleteuseritems.action",
			traditional:true,
			data:
			{
				username:usname,
				bookUserNames:busernames
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s5")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s6")
				{
					alert("系统接收到的需删除帐户为空");
				}
				else if(rs=="s4")
				{
					alert("系统无记录，没删除任何系统记录项");
					$("table#tbuseritem tr.slct").remove();
				}
				else
				{
					alert("删除成功");
					$("table#tbuseritem tr.slct").remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/******(50)修改是否管理员项********/
	$("div#showall").on("click","table#tbuseritem span.isadmin",function(){
		var td = $(this).parent();
		td.html("<form><select name='isadmin'><option value='是'>是</option><option value='否'>否</option></select></form>");
		td.siblings("td.operation").html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='resetpsw'>重置密码</span> <span class='delete'>删除</span>");
	});

/***(51)点击修改一行用户项********/
	$("div#showall").on("click","table#tbuseritem span.modify",function(){
		var td = $(this).parent();
		td.siblings("td.isnotadmin").html("<form><select name='isadmin'><option value='是'>是</option><option value='否'>否</option></select></form>");
		td.html("<span class='savemodify'>保存修改</span> <span class='discardmodify'>放弃修改</span> <span class='resetpsw'>重置密码</span> <span class='delete'>删除</span>");
	});

/******(52)点击保存修改一行用户项********/
	$("div#showall").on("click","table#tbuseritem span.savemodify",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();
		var busername = td.siblings("td.busername").text();
		var is = td.siblings("td.isnotadmin").find("select").val();

		if($.trim(is)=="" || is==undefined)
		{
			alert("是否管理员的值不能为空");
			return;
		}
		else
		{
			is = $.trim(is);
		}

		var r=confirm("确定保存吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"savemdisadmin.action",
			data:
			{
				username:usname,
				bookUserName:busername,
				isAdmin:is
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s5")
				{
					alert("系统已不存在该条目");
					td.parent().remove();
				}
				else if(rs=="s7")
				{
					alert("与系统值相同，不需修改");
				}
				else if(rs=="s6")
				{
					alert("程序异常，需保存值为空");
				}
				else
				{
					alert("修改成功");
					td.siblings("td.isnotadmin").html("<span class='isadmin'>"+is+"</span>");
					td.html("<span class='modify'>修改</span> <span class='delete'>删除</span>");
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/***(53)点击放弃修改一行用户项********/
	$("div#showall").on("click","table#tbuseritem span.discardmodify",function(){
		var td = $(this).parent();
		var busername = td.siblings("td.busername").text();
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"getisadmin.action",
			data:
			{
				username:usname,
				bookUserName:busername
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s5")
				{
					alert("系统已不存在该用户");
					td.parent().remove();
				}
				else
				{
					td.siblings("td.isnotadmin").html("<span class='isadmin'>"+rs+"</span>");
					td.html("<span class='modify'>修改</span> <span class='resetpsw'>重置密码</span> <span class='delete'>删除</span>");
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/***(54)点击删除全部用户项********/
	$("div#showall").on("click","table#tbuseritem span.cl3",function(){
		var r=confirm("确定要全部删除吗？\n系统中存在的帐户都将被删除");
		if(r==false)
		{
			return;
		}

		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"delalluseritem.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s5")
				{
					alert("系统中无符合条件的记录，没有删除任何项");
					$("table#tbuseritem td.number").parent().remove();
				}
				else
				{
					alert("删除成功");
					$("table#toptable td#useritem").click();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});

	});

/***(55)点击页码查询用户项********/
	$("div#showall").on("click","table#tbuseritem span.sp1",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"queryuseritem.action",
			data:
			{
				username:usname,
				pagination:$(this).attr("data-no")
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定或关闭后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});

	});

/***(56)点击页码查询用户项2********/
	$("div#showall").on("click","table#turnto td.td4",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"queryuseritem.action",
			data:
			{
				username:usname,
				pagination:$(this).attr("data-no")
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});

	});

/*******(57)用户退出*********/
	$("div#topdiv").on("click","span#logout",function(){
		$.ajax({
			type:"post",
			url:"processlogout.action",
			success:function()
			{
				window.location.assign("/");
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/********(58)暂停管理块********/
	$("table#toptable").on("click","td#pauseitem",function(){
		var usname = $("span#usname").text();

		if(!$(this).hasClass("selected"))
		{
			$(this).siblings().removeClass("selected");
			$(this).addClass("selected");
		}

		$.ajax({
			type:"post",
			url:"querypauseitem.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});

	});

/***(59)点击删除一行暂停项********/
	$("div#showall").on("click","table#tbpauseitem span.delete",function(){
		var usname = $("span#usname").text();

		var td = $(this).parent();

		var booid = new Array();

		booid[0] = td.siblings("td.number").attr("data-id");

		var r=confirm("确定取消吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"delpauseitems.action",
			traditional:true,
			data:
			{
				username:usname,
				bookIDs:booid
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("系统中已不存在该条目");
					td.parent().remove();
				}
				else if(rs.split(" ",1)[0]=="s5")
				{
					alert("该暂停日期已过，不能再取消");
					$("table#toptable td#pauseitem").click();
				}
				else if(rs.split(" ",1)[0]=="s6")
				{
					alert("该暂停时间段已过，不能再取消");
					$("table#toptable td#pauseitem").click();
				}
				else
				{
					alert("取消成功");
					td.parent().remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(60)点击选定一行暂停项********/
	$("div#showall").on("click","table#tbpauseitem td.number",function(){
		var tr = $(this).parent();

		if(tr.hasClass("slct"))
		{
			tr.removeClass("slct");
		}
		else
		{
			tr.addClass("slct");
		}
	});

/***(61)点击取消选定行暂停项********/
	$("div#showall").on("click","table#tbpauseitem span.cl2",function(){
		var x = $("table#tbpauseitem tr.slct td.number").length;
		if(x==0)
		{
			alert("请先点击相应行号进行选定！");
			return;
		}

		var bookids = new Array();
		for(var y=0; y<x; y++)
		{
			bookids[y] = $("table#tbpauseitem tr.slct td.number:eq("+y+")").attr("data-id");			
		}

		var usname = $("span#usname").text();

		var r=confirm("确定取消选定项吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"delpauseitems.action",
			traditional:true,
			data:
			{
				username:usname,
				bookIDs:bookids
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");					
				}
				else if(rs=="s4")
				{
					alert("系统无记录，没取消任何系统记录项");
					$("table#tbpauseitem tr.slct").remove();
				}
				else if(rs.split(" ",1)[0]=="s5")
				{
					var pastdate = rs.split(" ",2)[1];
					alert("此暂停日期【"+pastdate+"】已过，不能再取消");
					$("table#toptable td#pauseitem").click();
				}
				else if(rs.split(" ",1)[0]=="s6")
				{
					var pasttime = rs.split(" ",2)[1];
					alert("此暂停时间段【"+pasttime+"】已过，不能再取消");
					$("table#toptable td#pauseitem").click();
				}
				else
				{
					alert("取消成功");
					$("table#tbpauseitem tr.slct").remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(62)点击取消全部暂停项********/
	$("div#showall").on("click","table#tbpauseitem span.cl3",function(){
		var r=confirm("确定要全部取消吗？\n所有页的、系统中存在的条目都将被取消");
		if(r==false)
		{
			return;
		}

		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"delallpauseitems.action",
			data:
			{
				username:usname
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");					
				}
				else if(rs=="s3")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("系统中无符合条件的记录，没有取消任何暂停项");
					$("table#tbpauseitem td.number").parent().remove();
				}
				else
				{
					alert("取消成功");
					$("table#tbpauseitem td.number").parent().remove();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});

	});

/***(63)点击页码查询暂停项********/
	$("div#showall").on("click","table#tbpauseitem span.sp1",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"querypauseitem.action",
			data:
			{
				username:usname,
				pagination:$(this).attr("data-no")
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***(64)点击页码查询暂停项2********/
	$("div#showall").on("click","table#turnto td.td3",function(){
		var usname = $("span#usname").text();

		$.ajax({
			type:"post",
			url:"querypauseitem.action",
			data:
			{
				username:usname,
				pagination:$(this).attr("data-no")
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s2")
				{
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的管理界面");
					window.location.assign("myadmin");
				}
				else if(rs=="s3")
				{
					alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					$("#showall").html(rs);
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/************(65)预订查询及取消块********/
	$("div#topdiv").on("click","span#spanquery",function(){
		$.ajax({
			type:"post",
			url:"querybooking.action",
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
					window.location.assign("/");
				}
				else if(rs=="s4")
				{
					alert("当前登录帐户已不存在，点击确定或关闭后将返回首页");
					window.location.assign("/");
				}
				else
				{
					var doc = document.open("text/html","replace");
					doc.write(rs);
					doc.close();
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/************(66)统一设置场地数块********/
	$("div#showall").on("keyup","table#tbplayground input.countinputs",function(){
		$("input.countinput").val($(this).val());
	});

/************(67)统一设置可订天数块********/
	$("div#showall").on("keyup","table#tbplayground input.daysinputs",function(){
		$("input.daysinput").val($(this).val());
	});

/************(68)统一设置管理员可订天数块********/
	$("div#showall").on("keyup","table#tbplayground input.admindaysinputs",function(){
		$("input.admindaysinput").val($(this).val());
	});

/************(69)统一设置场地可订状态******************/
	$("div#showall").on("change","table#tbplayground select[name=statuss]",function(){
		$("select[name=status]").val($(this).val());
	});

/************(70)点击重置用户密码*******************/
	$("div#showall").on("click","table#tbuseritem span.resetpsw",function(){
		var td = $(this).parent();
		var usrna = td.siblings("td.busername").text();
		$("span.usrname").text(usrna);

		$("div#overlay").css("display","block");
		$("div#resetpswdiv").css("display","block");
		$("#pid3").focus();
	});

	$("div#resetpswdiv").on("click","span#spnresetcls",function(){
		$("div#resetpswdiv").css("display","none");
		$("div#overlay").css("display","none");
		validator3.resetForm();
		$("div#hint3").html("&nbsp;");
		$("span.usrname").text("");
		$("input#pid3").val("");
	});

	var validator3 = $("#formresetpsw").validate({
		onkeyup:false,
		
		errorPlacement:function(error, element){
			error.appendTo(element.parent());
		},

		submitHandler:function(form){	//通过之后回调
			var psw3 = $("input#pid3").val();
			var pswhash3 = CryptoJS.SHA512(psw3);
			$("input#pid3").val(pswhash3);

			$("div#hint3").html("发送中……");
			$.ajax({
				type:"post",		
				url:"resetuspw.action",
				data:
				{
					username:$("span.usrname").text(),
					userpassword:$("input#pid3").val()
				},
				success:function(result)
				{
					var rs3 = $.trim(result);

					if(rs3=="s1")
					{
						alert("会话已失效，请重新登录！");
						window.location.assign("/");
					}
					else if(rs3=="s2")
					{
						alert("系统已不存在当前登录用户\n点击确定或关闭后将返回首页");
						window.location.assign("/");
					}
					else if(rs3=="s3")
					{
						alert("当前登录用户不是管理员\n点击确定或关闭后将返回首页");
						window.location.assign("/");
					}
					else
					{
						$("div#hint3").html(rs3);
						$("input#pid3").val("");
						setTimeout(function(){$("div#hint3").html("&nbsp;");},3000);
					}
				},
				error:function(xhr)
				{
					$("div#hint3").html("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
					setTimeout(function(){$("div#hint3").html("&nbsp;");},3000);
				}
			});
		},
		
		invalidHandler:function(form, validator3){  //不通过回调 
			return false; 
		}
	});

});