package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class CancelBookAction extends BaseAction
{
	private String str;
	private String username;
	private Long bookID;
	
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

	//bookID属性的setter和getter方法
	public void setBookID(Long bookID)
	{
		this.bookID = bookID;
	}
	public Long getBookID()
	{
		return this.bookID;
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
			setStr("s2");	//帐户username已退出，现在的帐户是sn，点击确定后将返回sn的预订情况
		}
		else
		{
			Booking b = bookbns.getBookingById(bookID);
			
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			TimeZone tz = TimeZone.getTimeZone("Asia/Chongqing");
			ft.setTimeZone(tz);

			Calendar cal = Calendar.getInstance(tz);
			int nowhour = cal.get(Calendar.HOUR_OF_DAY);
			
			String nowDate = ft.format(cal.getTime());
			java.sql.Date today = java.sql.Date.valueOf(nowDate);

			java.sql.Date sltdate = b.getBookDate();
			
			if(b==null)
			{
				setStr("s6");	//预订结果不存在，没有取消任何预订
			}
			else if(sltdate.compareTo(today) < 0)
			{
				setStr("s8");	//该日期已过，不能再取消预订
			}
			else if(sltdate.compareTo(today)==0 && (Integer.parseInt(b.getBookTime().substring(0, 2)) < nowhour))
			{
				setStr("s9");	//该时间已过，不能再取消预订
			}
			else
			{
				bookbns.deleteBooking(b);
			}
		}

		return SUCCESS;
	}
}