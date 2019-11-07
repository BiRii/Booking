package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class CancelAllBookAction extends BaseAction
{
	private String str;
	private String username;
	private String selectedFieldName;
	private String selectedDate;

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

	//selectedFieldName属性的setter和getter方法
	public void setSelectedFieldName(String selectedFieldName)
	{
		this.selectedFieldName = selectedFieldName;
	}
	public String getSelectedFieldName()
	{
		return this.selectedFieldName;
	}

	//selectedDate属性的setter和getter方法
	public void setSelectedDate(String selectedDate)
	{
		this.selectedDate = selectedDate;
	}
	public String getSelectedDate()
	{
		return this.selectedDate;
	}

	@Override
	public String execute() throws Exception
	{
		String suname = (String)ActionContext.getContext().getSession().get("sessusername");
		if(suname==null)
		{
			setStr("s1");	//会话已失效，请重新登录
		}
		else if(!username.equals(suname))
		{
			setStr("s2");	//帐户username已退出，现在的帐户是suname，点击确定后将返回suname的预订情况
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

			List<Booking> bookList = new ArrayList<Booking>();

			if(selectedFieldName==null || selectedDate==null)
			{
				setStr("s4");	//请先选定场地名和日期
				return SUCCESS;
			}
			else if(selectedFieldName.equals("全部") && selectedDate.equals("全部"))
			{
				bookList = bookbns.getBookingByUserAndNow(suname, today, nowhour);
			}
			else if(!selectedFieldName.equals("全部") && selectedDate.equals("全部"))
			{
				bookList = bookbns.getBookingByUserAndFieldAndNow(suname, selectedFieldName, today, nowhour);
			}
			else if(selectedFieldName.equals("全部") && !selectedDate.equals("全部"))
			{
				java.sql.Date sltdate = java.sql.Date.valueOf(selectedDate);
				
				if(sltdate.compareTo(today) > 0)
				{
					bookList = bookbns.getBookingByUserAndDateAndNow(suname, sltdate);
				}
				else if(sltdate.compareTo(today) == 0)
				{
					bookList = bookbns.getBookingByUserAndDateAndNow(suname, sltdate);
					
					if(!bookList.isEmpty())
					{
						for(Booking b:bookList)
						{
							if(Integer.parseInt(b.getBookTime().substring(0, 2)) < nowhour)
							{
								bookList.remove(b);
							}
						}
					}
				}
				else
				{
					setStr("s7");	//该日期已过，不能取消
					return SUCCESS;
				}
			}
			else
			{
				java.sql.Date sltdate = java.sql.Date.valueOf(selectedDate);
				
				if(sltdate.compareTo(today) > 0)
				{
					bookList = bookbns.getBookingByFieldAndDay(suname, selectedFieldName, sltdate);
				}
				else if(sltdate.compareTo(today) == 0)
				{
					bookList = bookbns.getBookingByFieldAndDay(suname, selectedFieldName, sltdate);
					
					if(!bookList.isEmpty())
					{
						for(Booking b:bookList)
						{
							if(Integer.parseInt(b.getBookTime().substring(0, 2)) < nowhour)
							{
								bookList.remove(b);
							}
						}
					}
				}
				else
				{
					bookList = bookbns.getBookingByUserAndFieldAndNow(suname, selectedFieldName, today, nowhour);
				}
			}

			if(bookList.isEmpty())
			{
				setStr("s5");	//预订结果不存在，没有取消任何预订
				return SUCCESS;
			}
			else
			{
				bookbns.deleteAllBooking(bookList);
			}
		}

		return SUCCESS;
	}
}