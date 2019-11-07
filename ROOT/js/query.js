$(document).ready(function(){
/************进入系统管理中心********/
	$("div#topdiv").on("click","span#spanadmin",function(){
		$.ajax({
			type:"post",
			url:"myadmin.action",
			success:function(result)
			{
				var rs = $.trim(result);
				var doc = document.open("text/html","replace");
				doc.write(rs);
				doc.close();
			},
			error:function(xhr)
			{
				$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/********⑾单击取消全部预订*********/
	$("table#tbbooking").on("click","td.cl3",function(){
		var fn = $("table.tbsite td.selected").text();
		var sd = ($("table.tbdate td.selected").text()=="全部")?"全部":$("table.tbdate td.selected").text().split(" ",1)[0];
		
		var usname = $("span#usname").text();
		
		var r=confirm("确定取消全部预订吗？\n所有页的预订都会被取消");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"cancelallbook.action",
			traditional:true,
			data:
			{
				username:usname,
				selectedFieldName:fn,
				selectedDate:sd
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
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
				else if(rs=="s5")
				{
					alert("预订结果已不存在，没有取消任何预订，点击确定或关闭后将返回最新预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
				else if(rs=="s7")
				{
					alert("该日期已过，不能取消，点击确定或关闭后将返回最新预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
				else
				{
					$.ajax({
						type:"post",
						url:"querybooking.action",
						data:
						{
							selectedFieldName:fn,
							selectedDate:sd
						},
						success:function(result)
						{
							var rs3 = $.trim(result);
							if(rs3=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs3);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/********⑩点击单行取消预订*********/
	$("table#tbbooking").on("click","td.cl1",function(){
		var pg = $("span#slctd").attr("data-no");
		var fn = $("table.tbsite td.selected").text();
		var sd = ($("table.tbdate td.selected").text()=="全部")?"全部":$("table.tbdate td.selected").text().split(" ",1)[0];

		var bookid = $(this).attr("data-bookid");
		
		var usname = $("span#usname").text();
		
		var r=confirm("确定取消该预订吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"cancelbook.action",
			traditional:true,
			data:
			{
				username:usname,
				bookID:bookid
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
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});					
				}
				else if(rs=="s6")
				{
					alert("预订结果已不存在，没有取消任何预订，点击确定或关闭后将返回最新预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{						
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
				else if(rs=="s8")
				{
					alert("该日期已过，不能再取消预订，点击确定或关闭后将返回最新预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{				
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
				else if(rs=="s9")
				{
					alert("该时间已过，不能再取消预订，点击确定或关闭后将返回最新预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
				else
				{
					$.ajax({
						type:"post",
						url:"querybooking.action",
						data:
						{
							pagination:pg,
							selectedFieldName:fn,
							selectedDate:sd
						},
						success:function(result)
						{
							var rs3 = $.trim(result);
							if(rs3=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs3);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/*******⑨点击取消选定预订***********/
	$("table#tbbooking").on("click","td.cl2",function(){
		var x = $("table#tbbooking td.t1.slct").length;
		if(x==0)
		{
			alert("请先点击相应行进行选定！");
			return;
		}
		
		var pg = $("span#slctd").attr("data-no");
		var fn = $("table.tbsite td.selected").text();
		var sd = ($("table.tbdate td.selected").text()=="全部")?"全部":$("table.tbdate td.selected").text().split(" ",1)[0];

		var bookid;
		var bookids = new Array();
		for(var y=0; y<x; y++)
		{
			bookid = $("table#tbbooking td.t1.slct:eq("+y+")").attr("data-bookid");
			bookids[y] = bookid;
		}
		
		var usname = $("span#usname").text();
		
		var r=confirm("确定取消选定的预订吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"cancelbooking.action",
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
					alert("帐户"+usname+"已退出，点击确定后将返回最新登录帐户的预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});					
				}
				else if(rs=="s5")
				{
					alert("预订结果已不存在，没有取消任何预订，点击确定或关闭后将返回最新预订情况");
					$.ajax({
						type:"post",
						url:"querybooking.action",
						success:function(result)
						{
							var rs2 = $.trim(result);
							if(rs2=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs2);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
				else
				{
					$.ajax({
						type:"post",
						url:"querybooking.action",
						data:
						{
							pagination:pg,
							selectedFieldName:fn,
							selectedDate:sd
						},
						success:function(result)
						{
							var rs3 = $.trim(result);
							if(rs3=="s1")
							{
								alert("会话已失效，请重新登录！");
								window.location.assign("/");
							}
							else
							{
								var doc = document.open("text/html","replace");
								doc.write(rs3);
								doc.close();
							}
						},
						error:function(xhr)
						{
							$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
						}
					});
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

/***⑧点击日期查询预订********/
	$("table.tbdate").on("click","td",function(){
		$("table.tbdate td.selected").removeClass("selected");
		$(this).addClass("selected");

		$.ajax({
			type:"post",
			url:"querybooking.action",
			data:
			{
				selectedFieldName:$("table.tbsite td.selected").text(),
				selectedDate:($("table.tbdate td.selected").text()=="全部")?"全部":$("table.tbdate td.selected").text().split(" ",1)[0]
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
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
	
/***⑦点击场地名称查询预订********/
	$("table.tbsite").on("click","td",function(){	
		$("table.tbsite td.selected").removeClass("selected");
		$(this).addClass("selected");

		$.ajax({
			type:"post",
			url:"querybooking.action",		
			data:
			{
				selectedFieldName:$("table.tbsite td.selected").text(),
				selectedDate:($("table.tbdate td.selected").text()=="全部")?"全部":$("table.tbdate td.selected").text().split(" ",1)[0]
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
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

/*******⑥返回首页********/
	$("div#topdiv").on("click","span#returnhome",function(){
		window.location.assign("/");
	});

/*******⑤用户退出*********/
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

/*******④点击行选定********/
	$("table#tbbooking").on("click","td.selectable",function(){
		if($(this).hasClass("slct"))
		{
			var datarow = $(this).attr("data-row");
			$("table#tbbooking td.selectable[data-row="+datarow+"]").removeClass("slct");
		}
		else
		{
			var datarow = $(this).attr("data-row");
			$("table#tbbooking td.selectable[data-row="+datarow+"]").addClass("slct");
		}
	});

/*********③显示转到页************/
	$("span.sp2").mouseenter(
		function()
		{
			$("div#turnpage").css("display","block");
		}
	);

	$("div#turnpage").mouseleave(
		function()
		{
			$("div#turnpage").css("display","none");
		}
	);

/***②点击页码查询预订********/
	$("table#tbbooking").on("click","span.sp1",function(){
		$.ajax({
			type:"post",
			url:"querybooking.action",
			data:
			{
				pagination:$(this).attr("data-no"),
				selectedFieldName:$("table.tbsite td.selected").text(),
				selectedDate:($("table.tbdate td.selected").text()=="全部")?"全部":$("table.tbdate td.selected").text().split(" ",1)[0]
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
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
				$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/***①点击页码查询预订2********/
	$("table#turnto").on("click","td",function(){
		$.ajax({
			type:"post",
			url:"querybooking.action",
			data:
			{
				pagination:$(this).text(),
				selectedFieldName:$("table.tbsite td.selected").text(),
				selectedDate:($("table.tbdate td.selected").text()=="全部")?"全部":$("table.tbdate td.selected").text().split(" ",1)[0]
			},
			success:function(result)
			{
				var rs = $.trim(result);
				if(rs=="s1")
				{
					alert("会话已失效，请重新登录！");
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
				$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});
});