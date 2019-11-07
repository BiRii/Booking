package booking.action;

import java.util.*;
import java.text.SimpleDateFormat;
import com.opensymphony.xwork2.ActionContext;

import booking.action.base.BaseAction;
import booking.po.*;

public class IndexAction extends BaseAction
{
	//下面的属性并不用于封装用户请求参数，而用于封装Action输出到JSP页面的信息
	private String nowDate;
	private long nowhour;
	private String str;
	private String suname;
	private long prerogative;
	private List<String> fieldNames = new ArrayList<String>();
	private	List<String> fieldStatus = new ArrayList<String>();	
	private List<String> dateweek = new ArrayList<String>();
	private List<String> timeItems = new ArrayList<String>();
	private long rowCount;
	private long colCount;
	private List<String> titles = new ArrayList<String>();
	private List<String> titles2 = new ArrayList<String>();
	private String selectedFieldName;
	private String selectedDate;
	private String selectedFieldStatus;

	//nowDate属性的setter和getter方法
	public void setNowDate(String nowDate)
	{
		this.nowDate = nowDate;
	}
	public String getNowDate()
	{
		return this.nowDate;
	}

	//nowhour属性的setter和getter方法
	public void setNowhour(long nowhour)
	{
		this.nowhour = nowhour;
	}	
	public long getNowhour()
	{
		return this.nowhour;
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

	public void setFieldNames(List<String> fieldNames)
	{
		this.fieldNames = fieldNames;
	}
	public List<String> getFieldNames()
	{
		 return this.fieldNames;
	}
	
	//fieldStatus属性的setter和getter方法
	public void setFieldStatus(List<String> fieldStatus)
	{
		this.fieldStatus = fieldStatus;
	}	
	public List<String> getFieldStatus()
	{
		return this.fieldStatus;
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
	
	//timeItems属性的setter和getter方法
	public void setTimeItems(List<String> timeItems)
	{
		this.timeItems = timeItems;
	}	
	public List<String> getTimeItems()
	{
		return this.timeItems;
	}
	
	//rowCount属性的setter和getter方法
	public void setRowCount(long rowCount)
	{
		this.rowCount = rowCount;
	}	
	public long getRowCount()
	{
		return this.rowCount;
	}

	//colCount属性的setter和getter方法
	public void setColCount(long colCount)
	{
		this.colCount = colCount;
	}	
	public long getColCount()
	{
		return this.colCount;
	}	
	
	//titles属性的setter和getter方法
	public void setTitles(List<String> titles)
	{
		this.titles = titles;
	}	
	public List<String> getTitles()
	{
		return this.titles;
	}

	//titles2属性的setter和getter方法
	public void setTitles2(List<String> titles2)
	{
		this.titles2 = titles2;
	}	
	public List<String> getTitles2()
	{
		return this.titles2;
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
	
	//selectedFieldStatus属性的setter和getter方法
	public void setSelectedFieldStatus(String selectedFieldStatus)
	{
		this.selectedFieldStatus = selectedFieldStatus;
	}
	public String getSelectedFieldStatus()
	{
		return this.selectedFieldStatus;
	}
	
	public String execute() throws Exception
	{
		suname = (String)ActionContext.getContext().getSession().get("sessusername");

		int i = 0;

		if(suname!=null)
		{
			List<User> usl = bookbns.getUserByName(suname);

			if(usl.isEmpty())
			{
				ActionContext.getContext().getSession().put("sessusername", null);	//当前登录帐户已不存在
			}
			else
			{
				User u = usl.get(0);

				if(u.getPrerogative() != null)
				{
					i = u.getPrerogative();
					setPrerogative(i);
				}
			}
		}

		List<Field> fl = bookbns.getAllField();
		
		if(fl.isEmpty())
		{
			setStr("s3");	//系统里没有活动项目名
			return SUCCESS;			
		}
		else
		{
			for(Field f:fl)
			{
				fieldNames.add(f.getName());
				fieldStatus.add(f.getStatus());
			}
		}
		
		if(selectedFieldName == null)
		{
			setSelectedFieldName(fieldNames.get(0));
		}
		
		long dayNumber = 10;

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

				setRowCount(f2.getCount());
				setSelectedFieldStatus(f2.getStatus());
			}
		}

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sft = new SimpleDateFormat("EEEE");
		TimeZone tz = TimeZone.getTimeZone("Asia/Chongqing");
		Calendar cal = Calendar.getInstance(tz);
		ft.setTimeZone(tz);
		sft.setTimeZone(tz);

		java.util.Date gtime;
		for(long n=0; n<dayNumber; n++)
		{
			gtime = cal.getTime();
			dateweek.add(ft.format(gtime)+" "+sft.format(gtime));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		Calendar cal2 = Calendar.getInstance(tz);
		if(selectedDate == null || selectedDate.equals("全部"))
		{
			setSelectedDate(ft.format(cal2.getTime()));
		}
		else
		{
			java.util.Date date2 = ft.parse(selectedDate);
			String sltdw = selectedDate+" "+sft.format(date2);
			if(!dateweek.contains(sltdw))
			{
				setSelectedDate(ft.format(cal2.getTime()));
			}
		}
		
		setTimeItems(bookbns.getAllTimeitems());
		setColCount(timeItems.size());

		java.sql.Date sltDate = java.sql.Date.valueOf(selectedDate);
		
		List<Booking> bl = bookbns.getBookingByFieldAndDate(selectedFieldName, sltDate);
		for(Booking b:bl)
		{
			titles.add(b.getBookFieldNo()+" "+b.getBookTime());
		}

		List<Disable> dl = bookbns.getDisableByFieldAndDate(selectedFieldName, sltDate);
		for(Disable d:dl)
		{
			titles2.add(d.getFieldNo()+" "+d.getDisableTime());
		}

		Calendar cal3 = Calendar.getInstance(tz);
		nowhour = cal3.get(Calendar.HOUR_OF_DAY);
		nowDate = ft.format(cal3.getTime());
		
		return SUCCESS;
	}
}