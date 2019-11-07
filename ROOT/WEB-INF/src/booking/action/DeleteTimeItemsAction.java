package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class DeleteTimeItemsAction extends BaseAction
{
	private String str;
	private String username;
	private List<String> timeItemNames = new ArrayList<String>();
	
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

	//timeItemNames属性的setter和getter方法
	public void setTimeItemNames(List<String> timeItemNames)
	{
		this.timeItemNames = timeItemNames;
	}
	public List<String> getTimeItemNames()
	{
		return this.timeItemNames;
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
		else
		{
			List<Timeitem> timeList = bookbns.getTimeItemsByTimenames(timeItemNames);

			if(timeList.isEmpty())
			{
				setStr("s4");	//系统无记录
			}
			else
			{
				bookbns.deleteSltTimeItems(timeList);
			}
		}

		return SUCCESS;
	}
}