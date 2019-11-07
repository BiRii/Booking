package booking.action;

import java.util.*;
import java.text.SimpleDateFormat;
import com.opensymphony.xwork2.ActionContext;

import booking.action.base.BaseAction;
import booking.po.*;

public class AdminAction extends BaseAction
{
	//下面的属性并不用于封装用户请求参数，而用于封装Action输出到JSP页面的信息
	private String str;
	private String suname;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}

	//suname属性的setter和getter方法
	public void setSuname(String suname)
	{
		this.suname = suname;
	}
	public String getSuname()
	{
		return this.suname;
	}

	public String execute() throws Exception
	{
		List<User> ul = bookbns.getUserByPrerogative();
		if(ul.isEmpty())
		{
			return ERROR;	//系统无管理员
		}

		suname = (String)ActionContext.getContext().getSession().get("sessusername");

		if(suname==null)
		{
			setStr("s1");	//会话已失效，请重新登录
			return SUCCESS;
		}

		List<User> usl = bookbns.getUserByName(suname);

		if(usl.isEmpty())
		{
			setStr("s3");	//当前登录帐户已不存在
			ActionContext.getContext().getSession().put("sessusername", null);
			return SUCCESS;
		}
		else
		{
			User u = usl.get(0);
			if(u.getPrerogative() != 1)
			{
				setStr("s2");	//当前登录用户不是管理员，将返回首页
				return SUCCESS;
			}
		}

		return SUCCESS;
	}
}