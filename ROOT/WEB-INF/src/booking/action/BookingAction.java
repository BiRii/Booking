package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;
import java.text.SimpleDateFormat;

import booking.action.base.BaseAction;
import booking.po.*;

public class BookingAction extends BaseAction
{
	private String str;	
	private int num;
	private String selField;
	private String selDate;
	private List<String> selTitle = new ArrayList<String>();
	private List<String> selNo = new ArrayList<String>();
	private List<String> selTime = new ArrayList<String>();
	private List<String> bookedTitle = new ArrayList<String>();

	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}
	
	//num属性的setter和getter方法
	public void setNum(int num)
	{
		this.num = num;
	}
	public int getNum()
	{
		return this.num;
	}
	
	//selField属性的setter和getter方法
	public void setSelField(String selField)
	{
		this.selField = selField;
	}
	public String getSelField()
	{
		return this.selField;
	}

	//selDate属性的setter和getter方法
	public void setSelDate(String selDate)
	{
		this.selDate = selDate;
	}
	public String getSelDate()
	{
		return this.selDate;
	}

	//selTitle属性的setter和getter方法
	public void setSelTitle(List<String> selTitle)
	{
		this.selTitle = selTitle;
	}
	public List<String> getSelTitle()
	{
		return this.selTitle;
	}
	
	//selNo属性的setter和getter方法
	public void setSelNo(List<String> selNo)
	{
		this.selNo = selNo;
	}
	public List<String> getSelNo()
	{
		return this.selNo;
	}

	//selTime属性的setter和getter方法
	public void setSelTime(List<String> selTime)
	{
		this.selTime = selTime;
	}
	public List<String> getSelTime()
	{
		return this.selTime;
	}

	//bookedTitle属性的setter和getter方法
	public void setBookedTitle(List<String> bookedTitle)
	{
		this.bookedTitle = bookedTitle;
	}
	public List<String> getBookedTitle()
	{
		return this.bookedTitle;
	}

	public String execute() throws Exception
	{
		String suname = (String)ActionContext.getContext().getSession().get("sessusername");

		if(suname==null)
		{
			setStr("s1");	//请先登录
			return SUCCESS;
		}

		List<User> usl = bookbns.getUserByName(suname);

		if(usl.isEmpty())
		{
			setStr("s5");	//当前登录帐户已不存在
			ActionContext.getContext().getSession().put("sessusername", null);
			return SUCCESS;
		}

		java.sql.Date sltDate = java.sql.Date.valueOf(selDate);
		List<String> titles = new ArrayList<String>();
		
		List<Disable> dl = bookbns.getDisableByFieldAndDate(selField, sltDate);
		if(!dl.isEmpty())
		{
			for(Disable d:dl)
			{
				titles.add(d.getFieldNo()+" "+d.getDisableTime());
			}
			for(String t:selTitle)
			{
				if(titles.contains(t))
				{
					bookedTitle.add(t);
				}
			}
			if(!bookedTitle.isEmpty())
			{
				setStr("s3");	//下列场地已被暂停预订，不能再订
				return SUCCESS;
			}			
		}
		
		List<Booking> bl = bookbns.getBookingByFieldAndDate(selField, sltDate);		
		if(!bl.isEmpty())
		{
			for(Booking b:bl)
			{
				titles.add(b.getBookFieldNo()+" "+b.getBookTime());
			}
			for(String t:selTitle)
			{
				if(titles.contains(t))
				{
					bookedTitle.add(t);
				}
			}
			if(!bookedTitle.isEmpty())
			{
				setStr("s4");	//下列场地已被抢先预订，不能再订			
				return SUCCESS;
			}
		}

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone tz = TimeZone.getTimeZone("Asia/Chongqing");
		Calendar cal = Calendar.getInstance(tz);
		ft.setTimeZone(tz);
		
		for(int i=0; i<num; i++)
		{
			//创建Booking实例
			Booking bk = new Booking();
			bk.setBookUser(suname);
			bk.setBookField(selField);
			bk.setBookDate(sltDate);
			bk.setBookFieldNo(selNo.get(i));
			bk.setBookTime(selTime.get(i));			
			bk.setSubmitTime(ft.format(cal.getTime()));
			
			bookbns.insert(bk);
		}
		
		setStr("s2");	//预订成功
		return SUCCESS;
	}
}