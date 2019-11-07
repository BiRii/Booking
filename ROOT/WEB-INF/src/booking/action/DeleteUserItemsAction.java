package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class DeleteUserItemsAction extends BaseAction
{
	private String str;
	private String username;
	private List<String> bookUserNames = new ArrayList<String>();
	
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

	//bookUserNames属性的setter和getter方法
	public void setBookUserNames(List<String> bookUserNames)
	{
		this.bookUserNames = bookUserNames;
	}
	public List<String> getBookUserNames()
	{
		return this.bookUserNames;
	}

	public String execute() throws Exception
	{
		String sn = (String)ActionContext.getContext().getSession().get("sessusername");

		if(sn==null)
		{
			setStr("s1");	//会话已失效，请重新登录
			return SUCCESS;
		}
		else if(!username.equals(sn))
		{
			setStr("s2");	//帐户username已退出，现在的帐户是sn，点击确定或关闭后将返回sn的管理界面
			return SUCCESS;
		}
		else
		{
			List<Integer> prerogative = bookbns.getPrerogativeByName(sn);

			if(prerogative.isEmpty())
			{
				setStr("s5");	//系统已不存在当前登录用户
				ActionContext.getContext().getSession().put("sessusername", null);
				return SUCCESS;
			}

			if(prerogative.get(0) != 1)
			{
				setStr("s3");	//当前登录用户不是管理员，将返回首页
				return SUCCESS;
			}
		}

		if(bookUserNames.isEmpty())
		{
			setStr("s6");	//系统接收到的需删除帐户为空
			return SUCCESS;
		}
		
		List<User> ul = bookbns.getUserByNames(bookUserNames);

		if(ul.isEmpty())
		{
			setStr("s4");	//可删除的条目已不存在
		}
		else
		{
			bookbns.deleteAllUser(ul);
		}

		return SUCCESS;
	}
}