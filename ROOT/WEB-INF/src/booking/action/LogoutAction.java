package booking.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

public class LogoutAction extends ActionSupport
{
	public String execute() throws Exception
	{
		ActionContext.getContext().getSession().put("sessusername", null);
		return SUCCESS;
	}
}