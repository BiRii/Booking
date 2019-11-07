package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class SaveaddFieldAction extends BaseAction
{
	private String str;
	private String fname;
	private int fcount;
	private int fdays;
	private int fadmindays;
	private String fstatus;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}

	//fname属性的setter和getter方法
	public void setFname(String fname)
	{
		this.fname = fname;
	}
	public String getFname()
	{
		return this.fname;
	}

	//fcount属性的setter和getter方法
	public void setFcount(int fcount)
	{
		this.fcount = fcount;
	}
	public int getFcount()
	{
		return this.fcount;
	}

	//fdays属性的setter和getter方法
	public void setFdays(int fdays)
	{
		this.fdays = fdays;
	}
	public int getFdays()
	{
		return this.fdays;
	}

	//fadmindays属性的setter和getter方法
	public void setFadmindays(int fadmindays)
	{
		this.fadmindays = fadmindays;
	}
	public int getFadmindays()
	{
		return this.fadmindays;
	}

	//fstatus属性的setter和getter方法
	public void setFstatus(String fstatus)
	{
		this.fstatus = fstatus;
	}
	public String getFstatus()
	{
		return this.fstatus;
	}

	public String execute() throws Exception
	{
		String sn = (String)ActionContext.getContext().getSession().get("sessusername");
		if(sn==null)
		{
			setStr("s1");	//会话已失效，请重新登录
		}
		else if(bookbns.getPrerogativeByName(sn).get(0) != 1)
		{
			setStr("s3");	//该用户不是管理员，将返回首页
		}
		else
		{
			List<Field> fl = bookbns.getFieldByFieldname(fname);
			if(fl.isEmpty())
			{
				//创建Field实例
				Field fd = new Field();
				fd.setName(fname);
				fd.setCount(fcount);
				fd.setDayAmount(fdays);
				fd.setDayAmountofadmin(fadmindays);
				fd.setStatus(fstatus);
				
				bookbns.insertField(fd);
			}
			else
			{
				setStr("s2");	//系统中已存在同名项目
			}
		}

		return SUCCESS;
	}
}