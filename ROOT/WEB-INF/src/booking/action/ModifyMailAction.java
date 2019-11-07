package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class ModifyMailAction extends BaseAction
{
	private String str;
	private String newmail;
	private String email;
	private String vercode;
	private String newcode;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}

	//newmail属性的setter和getter方法
	public void setNewmail(String newmail)
	{
		this.newmail = newmail;
	}
	public String getNewmail()
	{
		return this.newmail;
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

	//newcode属性的setter和getter方法
	public void setNewcode(String newcode)
	{
		this.newcode = newcode;
	}
	public String getNewcode()
	{
		return this.newcode;
	}
	
	@Override
	public String execute() throws Exception
	{
		setStr(bookbns.ModifyMail(email, vercode, newmail, newcode));		
		return SUCCESS;
	}
}