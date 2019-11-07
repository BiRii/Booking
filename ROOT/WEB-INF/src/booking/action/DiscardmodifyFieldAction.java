package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class DiscardmodifyFieldAction extends BaseAction
{
	private String str;
	private String nowfname;
	private String username;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}

	//nowfname属性的setter和getter方法
	public void setNowfname(String nowfname)
	{
		this.nowfname = nowfname;
	}
	public String getNowfname()
	{
		return this.nowfname;
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
			setStr("s2");	//帐户username已退出，现在的帐户是sn，点击确定后将返回sn的管理界面
		}
		else if(bookbns.getPrerogativeByName(sn).get(0) != 1)
		{
			setStr("s3");	//该用户不是管理员，将返回首页
		}
		else
		{
			List<Field> fl = bookbns.getFieldByFieldname(nowfname);
			
			if(fl.isEmpty())
			{
				setStr("s4");	//该条目已不存在
			}
			else
			{
				Field f = fl.get(0);	//获取Field实例
				setStr(f.getName()+" "+f.getCount()+" "+f.getDayAmount()+" "+f.getDayAmountofadmin()+" "+f.getStatus());
			}
		}

		return SUCCESS;
	}
}