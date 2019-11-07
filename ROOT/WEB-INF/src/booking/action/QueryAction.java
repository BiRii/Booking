package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class QueryAction extends BaseAction
{
	private long firstPagination;
	private long lastPagination;
	private String suname;
	private long prerogative;
	private int pagination = 1;
	private int pages;
	private List<Booking> bookList = new ArrayList<Booking>();
	private List<Booking> subbookList = new ArrayList<Booking>();	
	private String str;
	private List<String> fieldName = new ArrayList<String>();
	private List<String> dateweek = new ArrayList<String>();
	private String selectedFieldName = "全部";
	private String selectedDate = "全部";

	//firstPagination属性的setter和getter方法
	public void setFirstPagination(long firstPagination)
	{
		this.firstPagination = firstPagination;
	}
	public long getFirstPagination()
	{
		return this.firstPagination;
	}

	//lastPagination属性的setter和getter方法
	public void setLastPagination(long lastPagination)
	{
		this.lastPagination = lastPagination;
	}
	public long getLastPagination()
	{
		return this.lastPagination;
	}

	//suname属性的setter和getter方法
	public void setSuname(String suname)
	{
		this.suname = suname;
	}
	public String getSuname()
	{
		return this.suname;
	}

	//prerogative属性的setter和getter方法
	public void setPrerogative(long prerogative)
	{
		this.prerogative = prerogative;
	}	
	public long getPrerogative()
	{
		return this.prerogative;
	}

	//pagination属性的setter和getter方法
	public void setPagination(int pagination)
	{
		this.pagination = pagination;
	}
	public int getPagination()
	{
		return this.pagination;
	}

	//pages属性的setter和getter方法
	public void setPages(int pages)
	{
		this.pages = pages;
	}
	public long getPages()
	{
		return this.pages;
	}

	//bookList属性的setter和getter方法
	public void setBookList(List<Booking> bookList)
	{
		this.bookList = bookList;
	}
	public List<Booking> getBookList()
	{
		 return this.bookList;
	}

	//subbookList属性的setter和getter方法
	public void setSubbookList(List<Booking> subbookList)
	{
		this.subbookList = subbookList;
	}
	public List<Booking> getSubbookList()
	{
		 return this.subbookList;
	}

	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}

	//fieldName属性的setter和getter方法
	public void setFieldName(List<String> fieldName)
	{
		this.fieldName = fieldName;
	}
	public List<String> getFieldName()
	{
		 return this.fieldName;
	}

	//dateweek属性的setter和getter方法
	public void setDateweek(List<String> dateweek)
	{
		this.dateweek = dateweek;
	}	
	public List<String> getDateweek()
	{
		return this.dateweek;
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

	public String execute() throws Exception
	{
		suname = (String)ActionContext.getContext().getSession().get("sessusername");

		long i = 0;

		if(suname==null)
		{
			setStr("s1");	//会话已失效，请重新登录
			return ERROR;
		}

		List<User> ul = bookbns.getUserByName(suname);

		if(ul.isEmpty())
		{
			setStr("s4");	//当前登录帐户已不存在
			return ERROR;
		}
		else
		{
			User u = ul.get(0);

			if(u.getPrerogative() != null)
			{
				i = u.getPrerogative();
				setPrerogative(i);
			}
		}

		List<Field> fl = bookbns.getAllField();
		
		if(fl.isEmpty())
		{
			setStr("s3");	//系统里没有活动项目名
			return SUCCESS;
		}

		fieldName.add("全部");
		for(Field f1:fl)
		{
			fieldName.add(f1.getName());
		}
		
		int dayNumber = 10;

		if(!selectedFieldName.equals("全部"))
		{
			if(fieldName.contains(selectedFieldName))
			{
				for(Field f2:fl)
				{
					if(selectedFieldName.equals(f2.getName()))
					{
						if(i==1)
						{
							dayNumber = f2.getDayAmountofadmin();
						}
						else
						{
							dayNumber = f2.getDayAmount();
						}
					}
				}
			}
			else
			{
				setSelectedFieldName("全部");
			}
		}

		if(selectedFieldName.equals("全部"))
		{
			List<Integer> days = new ArrayList<Integer>();

			if(i==1)
			{
				for(Field f3:fl)
				{
					days.add(f3.getDayAmountofadmin());
				}

				int max = days.get(0);

				for(int m=0; m<days.size(); m++)
				{
					if(max<days.get(m))
					{
						max = days.get(m);
					}
				}

				dayNumber = max;
			}
			else
			{
				for(Field f3:fl)
				{
					days.add(f3.getDayAmount());
				}

				int max = days.get(0);

				for(int m=0; m<days.size(); m++)
				{
					if(max<days.get(m))
					{
						max = days.get(m);
					}
				}

				dayNumber = max;
			}
		}

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sft = new SimpleDateFormat("EEEE");
		TimeZone tz = TimeZone.getTimeZone("Asia/Chongqing");
		Calendar cal = Calendar.getInstance(tz);
		ft.setTimeZone(tz);
		sft.setTimeZone(tz);

		dateweek.add("全部");
		java.util.Date gtime;
		for(long n=0; n<dayNumber; n++)
		{
			gtime = cal.getTime();
			dateweek.add(ft.format(gtime)+" "+sft.format(gtime));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		if(!selectedDate.equals("全部"))
		{
			java.util.Date date2 = ft.parse(selectedDate);
			String sltdw = selectedDate+" "+sft.format(date2);
			if(!dateweek.contains(sltdw))
			{
				setSelectedDate("全部");
			}
		}

		Calendar cal2 = Calendar.getInstance(tz);
		int nowhour = cal2.get(Calendar.HOUR_OF_DAY);
		String nowDate = ft.format(cal2.getTime());

		java.sql.Date today = java.sql.Date.valueOf(nowDate);

		if(selectedFieldName.equals("全部") && selectedDate.equals("全部"))
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
				bookList = bookbns.getBookingByUserAndNow(suname, today, nowhour);
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
			setStr("s2");	//还没预订过从今天起的场地
			return SUCCESS;
		}

		ComparatorBooking comparator = new ComparatorBooking();
		bookList.sort(comparator);
		
		int bksize = bookList.size();
		
		if(bksize%10 != 0)
		{
			pages = bksize/10 + 1;
		}
		else
		{
			pages = bksize/10;
		}

		if(pagination > pages)
		{
			pagination = pages;
		}
		
		int fromIndex = (pagination-1)*10;
		int reachableIndex = fromIndex + 10;
		int toIndex;
		
		if(bksize > reachableIndex)
		{
			toIndex = reachableIndex;
		}
		else
		{
			toIndex = bksize;
		}

		subbookList = bookList.subList(fromIndex, toIndex);

		long reachablePagination;
		if(pagination == 1)
		{
			reachablePagination = 10;
		}
		else
		{
			reachablePagination = pagination + 8;
		}
		
		if(pages > reachablePagination)
		{
			lastPagination = reachablePagination;
		}
		else
		{
			lastPagination = pages;
		}
		
		if(lastPagination > 9)
		{
			firstPagination = lastPagination - 9;
		}
		else
		{
			firstPagination = 1;
		}
		
		return SUCCESS;
	}
}

//具体的比较类，实现Comparator接口
class ComparatorBooking implements Comparator
{
	public int compare(Object arg0, Object arg1)
	{
		Booking bk0 = (Booking)arg0;
		Booking bk1 = (Booking)arg1;

		//首先按预订日期排列，如果预订日期相同，则按活动项目名排列
		int flag = bk0.getBookDate().compareTo(bk1.getBookDate());
		if(flag==0)
		{
			return bk0.getBookField().compareTo(bk1.getBookField());
		}
		else
		{
			return flag;
		}
	}
}