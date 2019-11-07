package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class SavemodifyTimeAction extends BaseAction
{
	private String str;
	private String username;
	private String nowtimename;
	private String timename;
	
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

	//nowtimename属性的setter和getter方法
	public void setNowtimename(String nowtimename)
	{
		this.nowtimename = nowtimename;
	}
	public String getNowtimename()
	{
		return this.nowtimename;
	}

	//timename属性的setter和getter方法
	public void setTimename(String timename)
	{
		this.timename = timename;
	}
	public String getTimename()
	{
		return this.timename;
	}

	@Override
	public String execute() throws Exception
	{
		String sn = (String)ActionContext.getContext().getSession().get("sessusername");
		if(sn==null)
		{
			setStr("s1");	//会话已失效，请重新登录
		}
		else if(!username.equals(sn))
		{
			setStr("s2");	//帐户username已退出，现在的帐户是sn，点击确定或关闭后将返回sn的管理界面
		}
		else if(bookbns.getPrerogativeByName(sn).get(0) != 1)
		{
			setStr("s3");	//该用户不是管理员，将返回首页
		}
		else if(!bookbns.getTimeItemByName(timename).isEmpty())
		{
			setStr("s6");	//系统中已存在同名时间段，请修改
		}
		else
		{
			List<Timeitem> tl = bookbns.getTimeItemByName(nowtimename);
			if(tl.isEmpty())
			{
				setStr("s4");	//该条目已不存在
			}
			else
			{
				Timeitem ti = tl.get(0);	//获取Timeitem实例
				
				if(timename!=null)
				{
					ti.setItem(timename);
					bookbns.updateTimeitem(ti);
				}
				else
				{
					setStr("s5");	//程序异常，需保存值为空
				}
			}
		}

		return SUCCESS;
	}
}