package booking.action.base;

import com.opensymphony.xwork2.ActionSupport;

import booking.business.*;

public class BaseAction extends ActionSupport
{
	protected BookBusiness bookbns;

	public void setBookbns(BookBusiness bookbns)
	{
		this.bookbns = bookbns;
	}
}