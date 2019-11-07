package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class DeleteBookItemsAction extends BaseAction
{
	private String str;
	private String username;
	private List<Long> bookIDs = new ArrayList<Long>();
	
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

	//bookIDs属性的setter和getter方法
	public void setBookIDs(List<Long> bookIDs)
	{
		this.bookIDs = bookIDs;
	}
	public List<Long> getBookIDs()
	{
		return this.bookIDs;
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
		else if(bookbns.getPrerogativeByName(sn).get(0) != 1)
		{
			setStr("s3");	//该用户不是管理员，将返回首页
		}
		else
		{
			List<Booking> bl = bookbns.getBookingByIDs(bookIDs);

			if(bl.isEmpty())
			{
				setStr("s4");	//该条目已不存在
			}
			else
			{
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
				TimeZone tz = TimeZone.getTimeZone("Asia/Chongqing");
				ft.setTimeZone(tz);

				Calendar cal = Calendar.getInstance(tz);
				int nowhour = cal.get(Calendar.HOUR_OF_DAY);

				String nowDate = ft.format(cal.getTime());
				java.sql.Date today = java.sql.Date.valueOf(nowDate);

				java.sql.Date sltdate;
				
				for(Booking b:bl)
				{
					sltdate = b.getBookDate();
					if(sltdate.compareTo(today) < 0)
					{
						setStr("s5 "+sltdate);	//该预订日期已过，不能再删除
						return SUCCESS;
					}
					else
					{
						if((sltdate.compareTo(today) == 0) && (Integer.parseInt(b.getBookTime().substring(0, 2)) < nowhour))
						{
							setStr("s6 "+b.getBookTime());	//该预订时间段已过，不能再删除
							return SUCCESS;
						}
					}
				}

				bookbns.deleteAllBooking(bl);				
			}
		}

		return SUCCESS;
	}
}