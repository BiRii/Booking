package booking.action;

import booking.action.base.BaseAction;

public class GetVercodeAction extends BaseAction
{
	private String str;
	private String email;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
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
	
	@Override
	public String execute() throws Exception
	{	
		setStr(bookbns.getVercode(email));
		return SUCCESS;
	}
}