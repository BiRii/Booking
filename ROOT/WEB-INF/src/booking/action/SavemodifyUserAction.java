package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class SavemodifyUserAction extends BaseAction
{
	private String str;
	private String username;
	private String bookUserName;
	private String isAdmin;
	
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

	//bookUserName属性的setter和getter方法
	public void setBookUserName(String bookUserName)
	{
		this.bookUserName = bookUserName;
	}
	public String getBookUserName()
	{
		return this.bookUserName;
	}

	//isAdmin属性的setter和getter方法
	public void setIsAdmin(String isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	public String getIsAdmin()
	{
		return this.isAdmin;
	}

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
		else
		{
			List<Integer> prerogative = bookbns.getPrerogativeByName(sn);

			if(prerogative.isEmpty())
			{
				setStr("s3");	//系统已不存在当前登录用户
				ActionContext.getContext().getSession().put("sessusername", null);
				return SUCCESS;
			}

			if(prerogative.get(0) != 1)
			{
				setStr("s4");	//当前登录用户不是管理员，将返回首页
				return SUCCESS;
			}

			List<String> bookUserNames = new ArrayList<String>();
			bookUserNames.add(bookUserName);

			List<User> ul = bookbns.getUserByNames(bookUserNames);

			if(ul.isEmpty())
			{
				setStr("s5");	//该条目已不存在
			}
			else
			{
				User u = ul.get(0);	//获取User实例

				long i = u.getPrerogative();

				if((isAdmin.equals("是") && i == 1) || (isAdmin.equals("否") && i == 0))
				{
					setStr("s7");	//与系统值相同，不需修改
					return SUCCESS;
				}

				if(isAdmin!=null)
				{
					if(isAdmin.equals("是"))
					{
						u.setPrerogative(1);
					}
					else
					{
						u.setPrerogative(0);
					}

					bookbns.updateUser(u);
				}
				else
				{
					setStr("s6");	//程序异常，需保存值为空
				}
			}
		}

		return SUCCESS;
	}
}