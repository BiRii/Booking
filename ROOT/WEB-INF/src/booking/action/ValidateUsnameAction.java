package booking.action;

import booking.action.base.BaseAction;

public class ValidateUsnameAction extends BaseAction
{
	private String str;
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
		setStr(bookbns.valiUsername(username));
		return SUCCESS;
	}
}