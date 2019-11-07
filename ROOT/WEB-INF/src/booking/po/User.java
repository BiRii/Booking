package booking.po;

import java.util.*;

public class User
{
	//标识属性
	private Long id;
	//用户名属性
	private String username;
	//密码属性
	private String userpass;
	//电子邮件属性
	private String email;
	//密码错误次数
	private int errtime;
	//用户权限
	private Integer prerogative;
	//随机码
	private String randomcode;
	//注册时间
	private String registertime;

	//无参数的构造器
	public User()
	{}
	
	//初始化全部基本属性的构造器
	public User(Long id, String username, String userpass, String email, int errtime, Integer prerogative, String randomcode, String registertime)
	{
		this.id = id;
		this.username = username;
		this.userpass = userpass;
		this.email = email;
		this.errtime = errtime;
		this.prerogative = prerogative;
		this.randomcode = randomcode;
		this.registertime = registertime;
	}

	//id属性的setter和getter方法
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getId()
	{
		return this.id;
	}

	//username属性的setter和getter方法
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

	//userpass属性的setter和getter方法
	public void setUserpass(String userpass)
	{
		this.userpass = userpass;
	}
	public String getUserpass()
	{
		return this.userpass;
	}

	//email属性的setter和getter方法
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}

	//errtime属性的setter和getter方法
	public void setErrtime(int errtime)
	{
		this.errtime = errtime;
	}
	public int getErrtime()
	{
		return this.errtime;
	}

	//prerogative属性的setter和getter方法
	public void setPrerogative(Integer prerogative)
	{
		this.prerogative = prerogative;
	}
	public Integer getPrerogative()
	{
		return this.prerogative;
	}

	//randomcode属性的setter和getter方法
	public void setRandomcode(String randomcode)
	{
		this.randomcode = randomcode;
	}
	public String getRandomcode()
	{
		return this.randomcode;
	}

	//registertime属性的setter和getter方法
	public void setRegistertime(String registertime)
	{
		this.registertime = registertime;
	}
	public String getRegistertime()
	{
		return this.registertime;
	}

}