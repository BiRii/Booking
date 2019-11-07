package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class CancelBookingAction extends BaseAction
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
			setStr("s2");	//帐户username已退出，现在的帐户是sn，点击确定后将返回sn的预订情况
		}
		else
		{
			List<Booking> bookList = bookbns.getBookingByIDs(bookIDs);
			
			if(bookList.isEmpty())
			{
				setStr("s5");	//预订结果不存在，没有取消任何预订
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
				
				for(Booking b:bookList)
				{
					sltdate = b.getBookDate();
					if(sltdate.compareTo(today) < 0)
					{
						bookList.remove(b);
					}
					else
					{
						if((sltdate.compareTo(today) == 0) && (Integer.parseInt(b.getBookTime().substring(0, 2)) < nowhour))
						{
							bookList.remove(b);
						}
					}
				}

				if(!bookList.isEmpty())
				{
					bookbns.deleteAllBooking(bookList);
				}
			}
		}

		return SUCCESS;
	}
}