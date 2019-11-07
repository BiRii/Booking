package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class SavemodifyFieldAction extends BaseAction
{
	private String str;
	private String nowfname;
	private String username;
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

	//nowfname属性的setter和getter方法
	public void setNowfname(String nowfname)
	{
		this.nowfname = nowfname;
	}
	public String getNowfname()
	{
		return this.nowfname;
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
		else if(!username.equals(sn))
		{
			setStr("s2");	//帐户username已退出，现在的帐户是sn，点击确定或关闭后将返回sn的管理界面
		}
		else if(bookbns.getPrerogativeByName(sn).get(0) != 1)
		{
			setStr("s3");	//该用户不是管理员，将返回首页
		}
		else
		{
			List<Field> fl = bookbns.getFieldByFieldname(nowfname);

			if(fl.isEmpty())
			{
				setStr("s4");	//该条目已不存在
			}
			else
			{
				Field f = fl.get(0);	//获取Field实例
				
				if(fname!=null)
				{
					f.setName(fname);
				}

				if(fcount!=0)
				{
					f.setCount(fcount);
				}

				if(fdays!=0)
				{
					f.setDayAmount(fdays);
				}

				if(fadmindays!=0)
				{
					f.setDayAmountofadmin(fadmindays);
				}

				if(fstatus!=null)
				{
					f.setStatus(fstatus);
				}

				bookbns.updateField(f);

				List<Field> fl2 = new ArrayList<Field>();

				if(fname==null)
				{
					fl2 = bookbns.getFieldByFieldname(nowfname);
				}
				else
				{
					fl2 = bookbns.getFieldByFieldname(fname);
				}
				
				Field f2 = fl2.get(0);
				
				setStr(f2.getName()+" "+f2.getCount()+" "+f2.getDayAmount()+" "+f2.getDayAmountofadmin()+" "+f2.getStatus());
			}
		}

		return SUCCESS;
	}
}