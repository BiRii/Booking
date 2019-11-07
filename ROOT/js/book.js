$(document).ready(function(){
/*******1、帐户退出*********/
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

/************2、预订查询及取消块********/
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
				$("div#query").html("错误代码：" + xhr.status + "\n错误描述：" + xhr.statusText);
			}
		});
	});

/************3、进入系统管理中心********/
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

/***********4、修改邮箱块***************/
	var t5;
	var t6;
	
	$("div#topdiv").on("click","span#modifymail",function(){
		$("div#overlay").css("display","block");
		$("div#modifymaildiv").css("display","block");
		$("#empuid5").focus();
	});

	$("div#modifymaildiv").on("click","span#spnmdfmlcls",function(){
		$("div#modifymaildiv").css("display","none");
		$("div#overlay").css("display","none");
		validator5.resetForm();
		$("div#hint5").html("&nbsp;");
		$("input#empuid5").val("");
		$("input#verifyid5").val("");
		$("input#newmailid5").val("");
		$("input#verifyid6").val("");
		clearTimeout(t5);
		$("input#verifybutton5").removeAttr("disabled");
		$("input#verifybutton5").css("cursor","pointer");
		$("input#verifybutton5").val("点击发送");
		clearTimeout(t6);
		$("input#verifybutton6").removeAttr("disabled");
		$("input#verifybutton6").css("cursor","pointer");
		$("input#verifybutton6").val("点击发送");
	});

	$("form#formmdfml").on("click","input#verifybutton5",function(){
		var m = $("input#empuid5").val();
		if(m=="")
		{
			$("div#hint5").html("邮箱不能为空");
			setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf("@") == -1)
		{
			$("div#hint5").html("邮箱地址要包含@字符");
			setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf(".") == -1)
		{
			$("div#hint5").html("邮箱地址要包含.字符");
			setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
			return;
		}
		else
		{
			$("input#verifybutton5").attr("disabled","disabled");
			$("input#verifybutton5").css("cursor","wait");
			var c=60;
			
			timedCount5();
			
			function timedCount5()
			{
				$("input#verifybutton5").val(c + "秒后可再点击");
				c--;
				t5=setTimeout(function(){timedCount5()},1000);
				if(c==-1)
				{
					clearTimeout(t5);
					$("input#verifybutton5").removeAttr("disabled");
					$("input#verifybutton5").css("cursor","pointer");
					$("input#verifybutton5").val("点击发送");
				}
			}

			$("div#hint5").html("发送中……");
			$.ajax({
				type:"post",
				url:"getmailcode.action",		
				data:
				{
					email:m
				},
				success:function(result)
				{
					var rst = $.trim(result);
					if(rst=="s1")
					{
						$("div#hint5").html("该邮箱不存在，请修改");
						clearTimeout(t5);
						$("input#verifybutton5").removeAttr("disabled");
						$("input#verifybutton5").css("cursor","pointer");
						$("input#verifybutton5").val("点击发送");						
					}
					else
					{
						$("div#hint5").html(rst);
					}
					setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
				},
				error:function(xhr)
				{
					$("div#hint5").html("验证码发送失败，请检查邮箱地址，如邮箱地址无问题则是系统出故障。");				
					setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
					clearTimeout(t5);
					$("input#verifybutton5").removeAttr("disabled");
					$("input#verifybutton5").css("cursor","pointer");
					$("input#verifybutton5").val("点击发送");
				}
			});
		}
	});

	$("form#formmdfml").on("click","input#verifybutton6",function(){
		var m = $("input#newmailid5").val();
		if(m=="")
		{
			$("div#hint5").html("新邮箱不能为空");
			setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf("@") == -1)
		{
			$("div#hint5").html("新邮箱地址要包含@字符");
			setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf(".") == -1)
		{
			$("div#hint5").html("新邮箱地址要包含.字符");
			setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
			return;
		}
		else
		{
			$("input#verifybutton6").attr("disabled","disabled");
			$("input#verifybutton6").css("cursor","wait");
			var c=60;
			
			timedCount6();
			
			function timedCount6()
			{
				$("input#verifybutton6").val(c + "秒后可再点击");
				c--;
				t6=setTimeout(function(){timedCount6()},1000);
				if(c==-1)
				{
					clearTimeout(t6);
					$("input#verifybutton6").removeAttr("disabled");
					$("input#verifybutton6").css("cursor","pointer");
					$("input#verifybutton6").val("点击发送");
				}
			}

			$("div#hint5").html("发送中……");
			$.ajax({
				type:"post",
				url:"getvercode.action",		
				data:
				{
					email:m
				},
				success:function(result)
				{
					var rst = $.trim(result);
					if(rst=="s1")
					{
						$("div#hint5").html("该邮箱已存在，请修改");
						clearTimeout(t6);
						$("input#verifybutton6").removeAttr("disabled");
						$("input#verifybutton6").css("cursor","pointer");
						$("input#verifybutton6").val("点击发送");
						
					}
					else
					{
						$("div#hint5").html(rst);
					}
					setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
				},
				error:function(xhr)
				{
					$("div#hint5").html("验证码发送失败，请检查邮箱地址，如邮箱地址无问题则是系统出故障。");				
					setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
					clearTimeout(t6);
					$("input#verifybutton6").removeAttr("disabled");
					$("input#verifybutton6").css("cursor","pointer");
					$("input#verifybutton6").val("点击发送");
				}
			});
		}
	});

	var validator5 = $("#formmdfml").validate({
		onkeyup:false,
		
		errorPlacement:function(error, element){
			error.appendTo(element.parent());
		},

		submitHandler:function(form){	//通过之后回调
			var nowmail=$("input#empuid5").val();
			var newemail=$("input#newmailid5").val();
			if(nowmail==newemail)
			{
				$("div#hint5").html("新邮箱不能与现在的邮箱相同");
				setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
				return;
			}
			
			$("div#hint5").html("发送中……");
			$.ajax({
				type:"post",		
				url:"mdfmail.action",
				data:
				{
					email:nowmail,
					vercode:$("input#verifyid5").val(),
					newmail:newemail,
					newcode:$("input#verifyid6").val()
				},
				success:function(result){
					var rs = $.trim(result);					
					if(rs=="修改邮箱成功")
					{
						$("div#hint5").html(rs);
						$("input#empuid5").val("");
						$("input#verifyid5").val("");
						$("input#newmailid5").val("");
						$("input#verifyid6").val("");
						clearTimeout(t5);						
						$("input#verifybutton5").removeAttr("disabled");
						$("input#verifybutton5").css("cursor","pointer");
						$("input#verifybutton5").val("点击发送");
						clearTimeout(t6);
						$("input#verifybutton6").removeAttr("disabled");
						$("input#verifybutton6").css("cursor","pointer");
						$("input#verifybutton6").val("点击发送");
					}
					else
					{
						$("div#hint5").html(rs);					
						setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
					}
				},
				error:function(xhr)
				{	
					$("div#hint5").html("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
					setTimeout(function(){$("div#hint5").html("&nbsp;");},3000);
				}
			});
		},
		
		invalidHandler:function(form, validator5){  //不通过回调 
			return false; 
		}
	});

/******************5、找回帐户名块***********************/
	var t4;

	$("div#topdiv").on("click","span#retrieve",function(){
		$("div#overlay").css("display","block");
		$("div#retrievediv").css("display","block");
		$("#empuid4").focus();
	});

	$("div#retrievediv").on("click","span#spnretrievecls",function(){
		$("div#retrievediv").css("display","none");
		$("div#overlay").css("display","none");
		validator4.resetForm();
		$("div#hint4").html("&nbsp;");
		$("input#empuid4").val("");
		$("input#verifyid4").val("");
		clearTimeout(t4);
		$("input#verifybutton4").removeAttr("disabled");
		$("input#verifybutton4").css("cursor","pointer");
		$("input#verifybutton4").val("点击发送");		
	});

	$("form#formretrieve").on("click","input#verifybutton4",function(){
		var m = $("input#empuid4").val();
		if(m=="")
		{
			$("div#hint4").html("邮箱不能为空");
			setTimeout(function(){$("div#hint4").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf("@") == -1)
		{
			$("div#hint4").html("邮箱地址要包含@字符");
			setTimeout(function(){$("div#hint4").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf(".") == -1)
		{
			$("div#hint4").html("邮箱地址要包含.字符");
			setTimeout(function(){$("div#hint4").html("&nbsp;");},3000);
			return;
		}
		else
		{
			$("input#verifybutton4").attr("disabled","disabled");
			$("input#verifybutton4").css("cursor","wait");
			var c=60;
			
			timedCount4();
			
			function timedCount4()
			{
				$("input#verifybutton4").val(c + "秒后可再点击");
				c--;
				t4=setTimeout(function(){timedCount4()},1000);
				if(c==-1)
				{
					clearTimeout(t4);
					$("input#verifybutton4").removeAttr("disabled");
					$("input#verifybutton4").css("cursor","pointer");
					$("input#verifybutton4").val("点击发送");
				}
			}

			$("div#hint4").html("发送中……");
			$.ajax({
				type:"post",
				url:"getmailcode.action",		
				data:
				{
					email:m
				},
				success:function(result)
				{
					var rst = $.trim(result);
					if(rst=="s1")
					{
						$("div#hint3").html("该邮箱不存在，请修改");
						clearTimeout(t4);
						$("input#verifybutton4").removeAttr("disabled");
						$("input#verifybutton4").css("cursor","pointer");
						$("input#verifybutton4").val("点击发送");						
					}
					else
					{
						$("div#hint4").html(rst);
					}
					setTimeout(function(){$("div#hint4").html("&nbsp;");},3000);
				},
				error:function(xhr)
				{
					$("div#hint4").html("验证码发送失败，请检查邮箱地址，如邮箱地址无问题则是系统出故障。");				
					setTimeout(function(){$("div#hint4").html("&nbsp;");},3000);
					clearTimeout(t4);
					$("input#verifybutton4").removeAttr("disabled");
					$("input#verifybutton4").css("cursor","pointer");
					$("input#verifybutton4").val("点击发送");
				}
			});
		}
	});

	var validator4 = $("#formretrieve").validate({
		onkeyup:false,
		
		errorPlacement:function(error, element){
			error.appendTo(element.parent());
		},

		submitHandler:function(form){	//通过之后回调
			$("div#hint4").html("发送中……");
			$.ajax({
				type:"post",		
				url:"retrievename.action",
				data:
				{
					email:$("input#empuid4").val(),
					vercode:$("input#verifyid4").val()
				},
				success:function(result){
					var rs = $.trim(result);					
					if(rs.indexOf("找回帐户名成功") != -1)
					{
						$("div#hint4").html(rs);
						$("input#empuid4").val("");
						$("input#verifyid4").val("");
						clearTimeout(t4);
						$("input#verifybutton4").removeAttr("disabled");
						$("input#verifybutton4").css("cursor","pointer");
						$("input#verifybutton4").val("点击发送");						
					}
					else
					{
						$("div#hint4").html(rs);					
						setTimeout(function(){$("div#hint4").html("&nbsp;");},3000);
					}
				},
				error:function(xhr)
				{	
					$("div#hint4").html("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
					setTimeout(function(){$("div#hint4").html("&nbsp;");},3000);
				}
			});
		},
		
		invalidHandler:function(form, validator4){  //不通过回调 
			return false; 
		}
	});

/********************6、重置密码块************************/
	var t3;

	$("div#topdiv").on("click","span.resetpsw",function(){
		$("div#overlay").css("display","block");
		$("div#resetpswdiv").css("display","block");
		$("#empuid3").focus();
	});

	$("div#resetpswdiv").on("click","span#spnresetcls",function(){
		$("div#resetpswdiv").css("display","none");
		$("div#overlay").css("display","none");
		validator3.resetForm();
		$("div#hint3").html("&nbsp;");
		$("input#empuid3").val("");
		$("input#verifyid3").val("");
		$("input#pid3").val("");		
		clearTimeout(t3);
		$("input#verifybutton3").removeAttr("disabled");
		$("input#verifybutton3").css("cursor","pointer");
		$("input#verifybutton3").val("点击发送");		
	});

	$("form#formresetpsw").on("click","input#verifybutton3",function(){
		var m = $("input#empuid3").val();
		if(m=="")
		{
			$("div#hint3").html("邮箱不能为空");
			setTimeout(function(){$("div#hint3").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf("@") == -1)
		{
			$("div#hint3").html("邮箱地址要包含@字符");
			setTimeout(function(){$("div#hint3").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf(".") == -1)
		{
			$("div#hint3").html("邮箱地址要包含.字符");
			setTimeout(function(){$("div#hint3").html("&nbsp;");},3000);
			return;
		}
		else
		{
			$("input#verifybutton3").attr("disabled","disabled");
			$("input#verifybutton3").css("cursor","wait");
			var c=60;
			
			timedCount3();
			
			function timedCount3()
			{
				$("input#verifybutton3").val(c + "秒后可再点击");
				c--;
				t3=setTimeout(function(){timedCount3()},1000);
				if(c==-1)
				{
					clearTimeout(t3);
					$("input#verifybutton3").removeAttr("disabled");
					$("input#verifybutton3").css("cursor","pointer");
					$("input#verifybutton3").val("点击发送");
				}
			}

			$("div#hint3").html("发送中……");
			$.ajax({
				type:"post",
				url:"getmailcode.action",		
				data:
				{
					email:m
				},
				success:function(result)
				{
					var rst = $.trim(result);
					if(rst=="s1")
					{
						$("div#hint3").html("该邮箱不存在，请修改");
						clearTimeout(t3);
						$("input#verifybutton3").removeAttr("disabled");
						$("input#verifybutton3").css("cursor","pointer");
						$("input#verifybutton3").val("点击发送");						
					}
					else
					{
						$("div#hint3").html(rst);
					}
					setTimeout(function(){$("div#hint3").html("&nbsp;");},3000);
				},
				error:function(xhr)
				{
					$("div#hint3").html("验证码发送失败，请检查邮箱地址，如邮箱地址无问题则是系统出故障。");				
					setTimeout(function(){$("div#hint3").html("&nbsp;");},3000);
					clearTimeout(t3);
					$("input#verifybutton3").removeAttr("disabled");
					$("input#verifybutton3").css("cursor","pointer");
					$("input#verifybutton3").val("点击发送");
				}
			});
		}
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
				url:"resetpsw.action",
				data:
				{
					email:$("input#empuid3").val(),
					vercode:$("input#verifyid3").val(),
					userpassword:$("input#pid3").val()
				},
				success:function(result)
				{
					var rs3 = $.trim(result);					
					if(rs3=="重置密码成功")
					{
						$("div#hint3").html(rs3);
						$("input#empuid3").val("");
						$("input#verifyid3").val("");
						$("input#pid3").val("");
						clearTimeout(t3);
						$("input#verifybutton3").removeAttr("disabled");
						$("input#verifybutton3").css("cursor","pointer");
						$("input#verifybutton3").val("点击发送");						
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

/********************登录块************************/
	$("div#topdiv").on("click","span#lgspn",function(){
		$("div#overlay").css("display","block");
		$("div#logindiv").css("display","block");
		$("#uid").focus();
	});

	$("div#logindiv").on("click","span#spanclose",function(){
		$("div#logindiv").css("display","none");
		$("div#overlay").css("display","none");
		validator2.resetForm();
		$("div#hint").html("&nbsp;");
		$("input#uid").val("");
		$("input#pid").val("");		
	});
	
	var validator2 = $("#formlogin").validate({
		onkeyup:false,
		
		errorPlacement:function(error, element){
			error.appendTo(element.parent());
		},

		submitHandler:function(form){	//通过之后回调 
			var usr = $("input#uid").val().toLowerCase();

			var psw2 = $("input#pid").val();
			var pswhash2 = CryptoJS.SHA512(psw2);
			$("input#pid").val(pswhash2);

			$.ajax({
				type:"post",
				url:"processlogin.action",
				data:
				{
					username:usr,
					userpassword:$("input#pid").val()
				},
				success:function(result)
				{
					switch(result)
					{
						case "s1":
							$("input#uid").val("");
							$("input#pid").val("");
							$("div#hint").html("登录成功");

							setTimeout(function(){pause3()},1000);
							function pause3()
							{
								window.location.assign("/");
							}
							break;
						case "s2":
							$("div#hint").html("密码错误");
							$("input#pid").val("");
							setTimeout(function(){$("div#hint").html("&nbsp;");},3000);
							break;
						case "s3":
							$("div#hint").html("帐户名不存在");
							$("input#pid").val("");
							setTimeout(function(){$("div#hint").html("&nbsp;");},3000);
							break;
						case "s4":
							$("div#hint").html("累计密码输入错误次数超过10次，须重置密码后才能登录");
							$("input#pid").val("");
							setTimeout(function(){$("div#hint").html("&nbsp;");},3000);
							break;
						default:
							$("div#hint").html("登录出错，重试几次如仍不行则是系统出故障");
							$("input#pid").val("");
							setTimeout(function(){$("div#hint").html("&nbsp;");},3000);
					}
				},
				error:function(xhr)
				{
					alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
				}
			});
		},
		
		invalidHandler:function(form, validator2){  //不通过回调 
			return false; 
		}
	});

/********7、注册帐户块********/
	var t;

	$("div#topdiv").on("click","span#regspn",function(){
		$("div#overlay").css("display","block");
		$("div#registerdiv").css("display","block");
		$("#uid2").focus();
	});

	$("div#registerdiv").on("click","span#spnclose",function(){
		$("div#registerdiv").css("display","none");
		$("div#overlay").css("display","none");
		validator.resetForm();
		$("div#hint2").html("&nbsp;");
		$("input#uid2").val("");
		$("input#pid2").val("");
		$("input#empuid").val("");
		$("input#verifyid").val("");
		clearTimeout(t);
		$("input#verifybutton").removeAttr("disabled");
		$("input#verifybutton").css("cursor","pointer");
		$("input#verifybutton").val("点击发送");		
	});

	$("form#formregister").on("click","input#verifybutton",function(){
		var m = $("input#empuid").val();
		if(m=="")
		{
			$("div#hint2").html("邮箱不能为空");
			setTimeout(function(){$("div#hint2").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf("@") == -1)
		{
			$("div#hint2").html("邮箱地址要包含@字符");
			setTimeout(function(){$("div#hint2").html("&nbsp;");},3000);
			return;
		}
		else if(m.indexOf(".") == -1)
		{
			$("div#hint2").html("邮箱地址要包含.字符");
			setTimeout(function(){$("div#hint2").html("&nbsp;");},3000);
			return;
		}
		else
		{
			$("input#verifybutton").attr("disabled","disabled");
			$("input#verifybutton").css("cursor","wait");
			var c=60;
			
			timedCount();
			
			function timedCount()
			{
				$("input#verifybutton").val(c + "秒后可再点击");
				c--;
				t=setTimeout(function(){timedCount()},1000);
				if(c==-1)
				{
					clearTimeout(t);
					$("input#verifybutton").removeAttr("disabled");
					$("input#verifybutton").css("cursor","pointer");
					$("input#verifybutton").val("点击发送");
				}
			}

			$("div#hint2").html("发送中……");
			$.ajax({
				type:"post",
				url:"getvercode.action",		
				data:
				{
					email:m
				},
				success:function(result)
				{
					var rst = $.trim(result);
					if(rst=="s1")
					{
						$("div#hint2").html("该邮箱已存在，请修改");
						clearTimeout(t);
						$("input#verifybutton").removeAttr("disabled");
						$("input#verifybutton").css("cursor","pointer");
						$("input#verifybutton").val("点击发送");
						
					}
					else
					{
						$("div#hint2").html(rst);
					}
					setTimeout(function(){$("div#hint2").html("&nbsp;");},3000);
				},
				error:function(xhr)
				{
					$("div#hint2").html("验证码发送失败，请检查邮箱地址，如邮箱地址无问题则是系统出故障。");				
					setTimeout(function(){$("div#hint2").html("&nbsp;");},3000);
					clearTimeout(t);
					$("input#verifybutton").removeAttr("disabled");
					$("input#verifybutton").css("cursor","pointer");
					$("input#verifybutton").val("点击发送");
				}
			});
		}
	});
	
	var validator = $("#formregister").validate({
		onkeyup:false,
		
		errorPlacement:function(error, element){
			error.appendTo(element.parent());
		},
	
		rules:{
			username:{remote:"validateusname.action"},
			email:{remote:"validatemailbox.action"}
		},
		
		messages:{
			username:{remote:"该帐户名已存在"},
			email:{remote:"该邮箱已存在"}
		},

		submitHandler:function(form){	//通过之后回调
			var psw = $("input#pid2").val();
			var pswhash = CryptoJS.SHA512(psw);
			$("input#pid2").val(pswhash);

			$("div#hint2").html("发送中……");
			$.ajax({
				type:"post",		
				url:"processregister.action", 
				data:
				{
					username:$("input#uid2").val().toLowerCase(),
					userpassword:$("input#pid2").val(),
					email:$("input#empuid").val(),
					vercode:$("input#verifyid").val()
				},
				success:function(result){
					var rs2 = $.trim(result);					
					if(rs2=="注册成功")
					{
						$("div#hint2").html("注册成功，现在就可以 <button type='button' id='loginbt'>登录</button>");
						$("input#uid2").val("");
						$("input#pid2").val("");
						$("input#empuid").val("");
						$("input#verifyid").val("");
						clearTimeout(t);
						$("input#verifybutton").removeAttr("disabled");
						$("input#verifybutton").css("cursor","pointer");
						$("input#verifybutton").val("点击发送");						
					}
					else
					{
						$("div#hint2").html(rs2);
						$("input#pid2").val("");
						setTimeout(function(){$("div#hint2").html("&nbsp;");},3000);
					}
				},
				error:function(xhr)
				{	
					$("div#hint2").html("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
					setTimeout(function(){$("div#hint2").html("&nbsp;");},3000);
				}
			});
		},
		
		invalidHandler:function(form, validator){  //不通过回调 
			return false; 
		}
	});

	$("div#hint2").on("click","button#loginbt",function(){
		$("span#spnclose").click();
		$("div#overlay").css("display","block");		
		$("div#logindiv").css("display","block");
		$("#uid").focus();
	});
	
/*①***************/
	$("table.tbsite").on("click","td",function(){
		var fieldName1;
		var selectedDate1;
	
		$("table.tbsite td.selected").removeClass("selected");
		$(this).addClass("selected");
		fieldName1 = $("table.tbsite td.selected").text();
		selectedDate1 = $("table.tbdate td.selected").text().split(" ",1)[0];
		$.ajax({
			type:"post",
			url:"",		
			data:
			{
				selectedFieldName:fieldName1,
				selectedDate:selectedDate1
			},
			success:function(result)
			{
				var rs = $.trim(result);
				var doc = document.open("text/html","replace");
				doc.write(rs);
				doc.close();
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});
/*②***************/	
	$("table.tbdate").on("click","td",function(){
		var fieldName2;
		var selectedDate2;
	
		$("table.tbdate td.selected").removeClass("selected");
		$(this).addClass("selected");
		fieldName2 = $("table.tbsite td.selected").text();
		selectedDate2 = $("table.tbdate td.selected").text().split(" ",1)[0];
		$.ajax({
			type:"post",
			url:"",
			data:
			{
				selectedFieldName:fieldName2,
				selectedDate:selectedDate2
			},
			success:function(result)
			{
				var rs = $.trim(result);
				var doc = document.open("text/html","replace");
				doc.write(rs);
				doc.close();
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});
/*③***************/
	$("table.SiteTime").on("click","th.sltall",function(){
		if($(this).hasClass("slct"))
		{
			$("table.SiteTime th,table.SiteTime td.t1").removeClass("slct");			
			$("table.SiteTime td[data-status='available']").removeClass("selected");
			$(this).attr("title","单击全选");
			$("table.SiteTime th.t2").attr("title","列全选");
			$("table.SiteTime td.t1").attr("title","行全选");
		}
		else
		{
			$("table.SiteTime th,table.SiteTime td.t1").addClass("slct");
			$("table.SiteTime td[data-status='available']").addClass("selected");
			$(this).attr("title","取消全选");
			$("table.SiteTime th.t2").attr("title","取消列全选");
			$("table.SiteTime td.t1").attr("title","取消行全选");
		}
	});

	$("table.SiteTime").on("click","th.t2",function(){
		if($(this).hasClass("slct"))
		{
			$(this).removeClass("slct");
			var dataCol = $(this).attr("data-col");
			$("table.SiteTime td[data-status='available'][data-column="+dataCol+"]").removeClass("selected");
			$(this).attr("title","列全选");
		}
		else
		{
			$(this).addClass("slct");		
			var dataCol = $(this).attr("data-col");
			$("table.SiteTime td[data-status='available'][data-column="+dataCol+"]").addClass("selected");
			$(this).attr("title","取消列全选");
		}
	});
	  
	$("table.SiteTime").on("click","td.t1",function(){
		if($(this).hasClass("slct"))
		{
			$(this).removeClass("slct");
			var dataTd = $(this).attr("data-td");
			$("table.SiteTime td[data-status='available'][data-row="+dataTd+"]").removeClass("selected");
			$(this).attr("title","行全选");
		}
		else
		{
			$(this).addClass("slct");
			var dataTd = $(this).attr("data-td");
			$("table.SiteTime td[data-status='available'][data-row="+dataTd+"]").addClass("selected");
			$(this).attr("title","取消行全选");
		}
	});

	$("table.SiteTime").on("click","td[data-status='available']",function(){
		$(this).toggleClass("selected");
	});

/***④提交预订*************/
	$("body").on("click","button.sbt",function(){
		var siteName3;
		var selectedDate3;
		var siteNo3 = new Array();
		var selectedTime3 = new Array();
		var x3 = 0;

		if($("table.tbsite td").hasClass("selected"))
		{
			siteName3 = $("table.tbsite td.selected").text();
		}
		else
		{
			alert("请先选定活动项目！");
			return;
		}

		if($("table.tbdate td").hasClass("selected"))
		{
			selectedDate3 = $("table.tbdate td.selected").text().split(" ",1)[0];
		}
		else
		{
			alert("请先选定预订日期！");
			return;
		}
		
		if($("table.SiteTime td").hasClass("selected"))
		{
			x3 = $("table.SiteTime td.selected").length;
			var tt;
			var titles = new Array();
			var nt1 = new Array();
			for(var y=0; y<x3; y++)
			{
				tt = $("table.SiteTime td.selected:eq("+y+")").attr("title");
				titles[y] = tt;
				nt1 = tt.split(" ");
				siteNo3[y] = nt1[0];
				selectedTime3[y] = nt1[1];
			}
		}
		else
		{
			alert("请先选定场地号和预订时间！");
			return;
		}
		
		var r=confirm("确定预订吗？");
		if(r==false)
		{
			return;
		}
  
		$.ajax({
			type:"post",
			url:"bk.action",
			traditional:true,
			data:
			{
				selField:siteName3,
				selDate:selectedDate3,
				selTitle:titles,
				selNo:siteNo3,				
				selTime:selectedTime3,
				num:x3
			},
			success:function(result)
			{
				var rs = $.trim(result);
				$("div#caution div#contentid").html(rs);
				$("div#overlay").css("display","block");
				$("div#caution").css("display","block");
				
				if(rs=="预订成功")
				{
					setTimeout(function(){pause()},1000);
					function pause()
					{
						var fieldName3;
						var selectedDate3;

						fieldName3 = $("table.tbsite td.selected").text();
						selectedDate3 = $("table.tbdate td.selected").text().split(" ",1)[0];
						$.ajax({
							type:"post",
							url:"",
							data:
							{
								selectedFieldName:fieldName3,
								selectedDate:selectedDate3
							},
							success:function(result)
							{
								var rs = $.trim(result);
								var doc = document.open("text/html","replace");
								doc.write(rs);
								doc.close();
							},
							error:function(xhr)
							{
								alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
							}
						});
					}
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});
	
	$("div#caution").on("click","span#closeid",function(){
		$("div#caution").css("display","none");
		$("div#overlay").css("display","none");
	});

	$("div#caution").on("click","button#login",function(){
		$("div#caution").css("display","none");
		$("div#logindiv").css("display","block");
		$("#uid").focus();
	});

	$("div#caution").on("click","button#register",function(){
		$("div#caution").css("display","none");
		$("div#registerdiv").css("display","block");
		$("#uid2").focus();
	});

/***⑤提交暂停***************/
	$("body").on("click","button.pausebooking",function(){
		var siteName4;
		var selectedDate4;
		var siteNo4 = new Array();
		var selectedTime4 = new Array();
		var x4 = 0;
		
		if($("table.tbsite td").hasClass("selected"))
		{
			siteName4 = $("table.tbsite td.selected").text();
		}
		else
		{
			alert("请先选定活动项目！");
			return;
		}

		if($("table.tbdate td").hasClass("selected"))
		{
			selectedDate4 = $("table.tbdate td.selected").text().split(" ",1)[0];
		}
		else
		{
			alert("请先选定日期！");
			return;
		}
		
		if($("table.SiteTime td").hasClass("selected"))
		{
			x4 = $("table.SiteTime td.selected").length;
			var tt2;
			var titles2 = new Array();
			var nt2 = new Array();
			for(var y=0; y<x4; y++)
			{
				tt2 = $("table.SiteTime td.selected:eq("+y+")").attr("title");
				titles2[y] = tt2;
				nt2 = tt2.split(" ");
				siteNo4[y] = nt2[0];
				selectedTime4[y] = nt2[1];
			}
		}
		else
		{
			alert("请先选定场地号和时间！");
			return;
		}

		var r=confirm("确定暂停吗？");
		if(r==false)
		{
			return;
		}

		$.ajax({
			type:"post",
			url:"pausebooking.action",
			traditional:true,
			data:
			{
				selField:siteName4,
				selDate:selectedDate4,
				selTitle:titles2,
				selNo:siteNo4,				
				selTime:selectedTime4,
				num:x4
			},
			success:function(result)
			{
				var rs = $.trim(result);
				$("div#caution div#contentid").html(rs);
				$("div#overlay").css("display","block");
				$("div#caution").css("display","block");

				if(rs=="暂停成功")
				{
					setTimeout(function(){pause4()},1000);
					function pause4()
					{
						var fieldName4;
						var selectedDate5;

						fieldName4 = $("table.tbsite td.selected").text();
						selectedDate5 = $("table.tbdate td.selected").text().split(" ",1)[0];
						$.ajax({
							type:"post",
							url:"",
							data:
							{
								selectedFieldName:fieldName4,
								selectedDate:selectedDate5
							},
							success:function(result)
							{
								var rs = $.trim(result);
								var doc = document.open("text/html","replace");
								doc.write(rs);
								doc.close();
							},
							error:function(xhr)
							{
								alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
							}
						});
					}
				}
			},
			error:function(xhr)
			{
				alert("错误代码:" + xhr.status + "\n错误描述:" + xhr.statusText);
			}
		});
	});

});