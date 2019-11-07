package booking.po;

import java.util.*;

public class Field
{
	//定义标识属性
	private Long id;
	//定义Site实例的name属性
	private String name;
	//定义Site实例的count属性
	private int count;
	//定义Site实例的dayAmount属性
	private int dayAmount;
	//admin的dayAmount属性
	private int dayAmountofadmin;
	//定义Site实例的status属性
	private String status;

	//无参数的构造器
	public Field()
	{}
	
	//初始化全部属性的构造器
	public Field(Long id, String name, int count, int dayAmount, int dayAmountofadmin, String status)
	{
		this.id = id;
		this.name = name;
		this.count = count;
		this.dayAmount = dayAmount;
		this.dayAmountofadmin = dayAmountofadmin;
		this.status = status;
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
	
	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	
	//count属性的setter和getter方法
	public void setCount(int count)
	{
		this.count = count;
	}
	public int getCount()
	{
		return this.count;
	}

	//dayAmount属性的setter和getter方法
	public void setDayAmount(int dayAmount)
	{
		this.dayAmount = dayAmount;
	}
	public int getDayAmount()
	{
		return this.dayAmount;
	}

	//dayAmountofadmin属性的setter和getter方法
	public void setDayAmountofadmin(int dayAmountofadmin)
	{
		this.dayAmountofadmin = dayAmountofadmin;
	}
	public int getDayAmountofadmin()
	{
		return this.dayAmountofadmin;
	}

	//status属性的setter和getter方法
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getStatus()
	{
		return this.status;
	}
}