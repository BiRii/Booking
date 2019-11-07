package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class LoginAction extends BaseAction
{
	private String str;
	private String username;
	private String userpassword;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
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

	//userpassword属性的setter和getter方法
	public void setUserpassword(String userpassword)
	{
		this.userpassword = userpassword;
	}
	public String getUserpassword()
	{
		return this.userpassword;
	}

	public String execute() throws Exception
	{
		username = username.trim();
		userpassword = userpassword.trim();

		List<String> userNames = new ArrayList<String>();
		userNames.add(username);

		List<User> uslist = bookbns.getUserByNames(userNames);

		if(uslist.size() == 1)
		{
			User us = uslist.get(0);

			int errtime = us.getErrtime();
			
			if(errtime > 10)
			{
				setStr("s4");	//累计密码输入错误次数超过10次，须重置密码后才能登录
				return SUCCESS;
			}

			String rdcode = us.getRandomcode();
			String regtime = us.getRegistertime();
			String psw = us.getUserpass();

			String subRandomString = rdcode.substring(4, 124);
			String subrs1 = subRandomString.substring(0, 70);
			String subrs2 = subRandomString.substring(70);

			String pswcombine = subrs1 + userpassword + subrs2 + username + regtime;

			byte[] secretBytes = null;
			try
			{
				secretBytes = MessageDigest.getInstance("SHA-512").digest(pswcombine.getBytes());
			}
			catch(NoSuchAlgorithmException e)
			{
				throw new RuntimeException("没有这个Hash算法！");
			}

			String pswhash = new BigInteger(1, secretBytes).toString(16);
			
			if(pswhash.equals(psw))
			{
				setStr("s1");	//登录成功
				ActionContext.getContext().getSession().put("sessusername", username);
			}
			else
			{
				setStr("s2");	//密码错误
				errtime++;
				us.setErrtime(errtime);
				bookbns.updateUser(us);

				return SUCCESS;
			}

		}
		else
		{
			setStr("s3");	//用户名不存在
			return SUCCESS;
		}

		return SUCCESS;
	}
}