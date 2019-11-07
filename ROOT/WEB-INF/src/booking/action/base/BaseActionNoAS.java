package booking.action.base;

import com.opensymphony.xwork2.Action;

import booking.business.BookBusiness;

public class BaseActionNoAS implements Action
{
	protected BookBusiness bookbns;

	public void setBookbns(BookBusiness bookbns)
	{
		this.bookbns = bookbns;
	}

	public String execute() throws Exception
	{
		return SUCCESS;
	}
}