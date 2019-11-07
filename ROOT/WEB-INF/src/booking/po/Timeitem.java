package booking.po;

import java.util.*;

public class Timeitem
{
	//定义标识属性
	private Long id;
	//定义Timeitem实例的item属性
	private String item;

	//无参数的构造器
	public Timeitem()
	{}
	
	//初始化全部属性的构造器
	public Timeitem(Long id , String item)
	{
		this.id = id;
		this.item = item;
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
	
	//item属性的setter和getter方法
	public void setItem(String item)
	{
		this.item = item;
	}
	public String getItem()
	{
		return this.item;
	}
}