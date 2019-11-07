package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class SaveAlladdFieldAction extends BaseAction
{
	private String str;
	private List<String> fieldnames = new ArrayList<String>();
	private List<Integer> fieldcounts = new ArrayList<Integer>();
	private List<Integer> fielddays = new ArrayList<Integer>();
	private List<Integer> fieldadmindays = new ArrayList<Integer>();
	private List<String> fieldstatus = new ArrayList<String>();
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}

	//fieldnames属性的setter和getter方法
	public void setFieldnames(List<String> fieldnames)
	{
		this.fieldnames = fieldnames;
	}
	public List<String> getFieldnames()
	{
		return this.fieldnames;
	}

	//fieldcounts属性的setter和getter方法
	public void setFieldcounts(List<Integer> fieldcounts)
	{
		this.fieldcounts = fieldcounts;
	}
	public List<Integer> getFieldcounts()
	{
		return this.fieldcounts;
	}

	//fielddays属性的setter和getter方法
	public void setFielddays(List<Integer> fielddays)
	{
		this.fielddays = fielddays;
	}
	public List<Integer> getFielddays()
	{
		return this.fielddays;
	}

	//fieldadmindays属性的setter和getter方法
	public void setFieldadmindays(List<Integer> fieldadmindays)
	{
		this.fieldadmindays = fieldadmindays;
	}
	public List<Integer> getFieldadmindays()
	{
		return this.fieldadmindays;
	}

	//fieldstatus属性的setter和getter方法
	public void setFieldstatus(List<String> fieldstatus)
	{
		this.fieldstatus = fieldstatus;
	}
	public List<String> getFieldstatus()
	{
		return this.fieldstatus;
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
			if(fieldnames.isEmpty())
			{
				setStr("s5");	//场地名为空
				return SUCCESS;
			}

			if(fieldnames.size() > 1)
			{
				for(int m=0; m<fieldnames.size()-1; m++)
				{
					for(int n=m+1; n<fieldnames.size(); n++)
					{
						if(fieldnames.get(m).equals(fieldnames.get(n)))
						{
							setStr("s4 "+fieldnames.get(m));	//新增的场地名存在同名
							return SUCCESS;
						}
					}
				}
			}

			List<String> fl = bookbns.getAllFieldNames();
			
			if(!fl.isEmpty())
			{
				for(String fn:fieldnames)
				{
					if(fl.contains(fn))
					{
						setStr("s2 "+fn);	//系统中存在同名项目fn，请修改
						return SUCCESS;
					}
				}
			}

			for(int i=0; i<fieldnames.size(); i++)
			{
				//创建Field实例
				Field fd = new Field();
				fd.setName(fieldnames.get(i));
				fd.setCount(fieldcounts.get(i));
				fd.setDayAmount(fielddays.get(i));
				fd.setDayAmountofadmin(fieldadmindays.get(i));
				fd.setStatus(fieldstatus.get(i));

				bookbns.insertField(fd);
			}
		}

		return SUCCESS;
	}
}