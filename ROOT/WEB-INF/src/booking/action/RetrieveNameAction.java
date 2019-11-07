package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class RetrieveNameAction extends BaseAction
{
	private String str;
	private String email;
	private String vercode;
	
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

	//vercode属性的setter和getter方法
	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		return this.vercode;
	}
	
	@Override
	public String execute() throws Exception
	{
		setStr(bookbns.RetrieveName(email, vercode));		
		return SUCCESS;
	}
}