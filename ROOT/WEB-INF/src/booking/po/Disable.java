package booking.po;

import java.sql.Date;

public class Disable
{
	//标识属性
	private Long id;
	//场地名
	private String fieldName;
	//日期
	private Date disableDate;	
	//场地号
	private String fieldNo;
	//时间
	private String disableTime;
	//用户
	private String pauseUser;
	//提交的时间
	private String submitTime;

	//无参数的构造器
	public Disable()
	{}
	
	//初始化全部基本属性的构造器
	public Disable(Long id, String fieldName, Date disableDate, String fieldNo, String disableTime, String pauseUser, String submitTime)
	{
		this.id = id;
		this.fieldName = fieldName;
		this.disableDate = disableDate;
		this.fieldNo = fieldNo;
		this.disableTime = disableTime;
		this.pauseUser = pauseUser;
		this.submitTime = submitTime;
	}

	//id属性的setter和getter方法
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getId()
	{
		return this.id;
	}

	//fieldName属性的setter和getter方法
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}
	public String getFieldName()
	{
		return this.fieldName;
	}

	//disableDate属性的setter和getter方法
	public void setDisableDate(Date disableDate)
	{
		this.disableDate = disableDate;
	}
	public Date getDisableDate()
	{
		return this.disableDate;
	}
	
	//fieldNo属性的setter和getter方法
	public void setFieldNo(String fieldNo)
	{
		this.fieldNo = fieldNo;
	}
	public String getFieldNo()
	{
		return this.fieldNo;
	}
	
	//disableTime属性的setter和getter方法
	public void setDisableTime(String disableTime)
	{
		this.disableTime = disableTime;
	}
	public String getDisableTime()
	{
		return this.disableTime;
	}

	//pauseUser属性的setter和getter方法
	public void setPauseUser(String pauseUser)
	{
		this.pauseUser = pauseUser;
	}
	public String getPauseUser()
	{
		return this.pauseUser;
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