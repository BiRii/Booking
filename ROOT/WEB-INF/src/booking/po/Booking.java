package booking.po;

import java.sql.Date;

public class Booking
{
	//预订类的标识属性
	private Long bookId;
	//预订场地名称
	private String bookField;	
	//预订日期
	private Date bookDate;
	//预订场地号
	private String bookFieldNo;
	//预订时间
	private String bookTime;
	//预订用户
	private String bookUser;
	//预订提交的时间
	private String submitTime;

	//无参数的构造器
	public Booking()
	{}
	
	//初始化全部基本属性的构造器
	public Booking(Long bookId, String bookField, Date bookDate, String bookFieldNo, String bookTime, String bookUser, String submitTime)
	{
		this.bookId = bookId;
		this.bookField = bookField;
		this.bookDate = bookDate;
		this.bookFieldNo = bookFieldNo;
		this.bookTime = bookTime;
		this.bookUser = bookUser;
		this.submitTime = submitTime;
	}

	//bookId属性的setter和getter方法
	public void setBookId(Long bookId)
	{
		this.bookId = bookId;
	}
	public Long getBookId()
	{
		return this.bookId;
	}

	//bookField属性的setter和getter方法
	public void setBookField(String bookField)
	{
		this.bookField = bookField;
	}
	public String getBookField()
	{
		return this.bookField;
	}

	//bookDate属性的setter和getter方法
	public void setBookDate(Date bookDate)
	{
		this.bookDate = bookDate;
	}
	public Date getBookDate()
	{
		return this.bookDate;
	}
	
	//bookFieldNo属性的setter和getter方法
	public void setBookFieldNo(String bookFieldNo)
	{
		this.bookFieldNo = bookFieldNo;
	}
	public String getBookFieldNo()
	{
		return this.bookFieldNo;
	}
	
	//bookTime属性的setter和getter方法
	public void setBookTime(String bookTime)
	{
		this.bookTime = bookTime;
	}
	public String getBookTime()
	{
		return this.bookTime;
	}
	
	//bookUser属性的setter和getter方法
	public void setBookUser(String bookUser)
	{
		this.bookUser = bookUser;
	}
	public String getBookUser()
	{
		return this.bookUser;
	}

	//submitTime属性的setter和getter方法
	public void setSubmitTime(String submitTime)
	{
		this.submitTime = submitTime;
	}
	public String getSubmitTime()
	{
		return this.submitTime;
	}
}