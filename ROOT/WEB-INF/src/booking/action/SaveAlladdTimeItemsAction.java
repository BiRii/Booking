package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class SaveAlladdTimeItemsAction extends BaseAction
{
	private String str;
	private String username;
	private List<String> timenames = new ArrayList<String>();
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
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

	//timenames属性的setter和getter方法
	public void setTimenames(List<String> timenames)
	{
		this.timenames = timenames;
	}
	public List<String> getTimenames()
	{
		return this.timenames;
	}

	@Override
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
			if(timenames.isEmpty())
			{
				setStr("s4");	//时间项为空
				return SUCCESS;
			}
			
			for(int m=0; m<timenames.size()-1; m++)
			{
				for(int n=m+1; n<timenames.size(); n++)
				{
					if(timenames.get(m).equals(timenames.get(n)))
					{
						setStr("s5 "+timenames.get(m));	//新增的时间项存在同名
						return SUCCESS;
					}
				}
			}

			List<Timeitem> tl = bookbns.getTimeItemsByTimenames(timenames);
			
			if(!tl.isEmpty())
			{
				setStr("s6 "+tl.get(0).getItem());	//系统中存在同名项目，请修改
				return SUCCESS;
			}

			for(int i=0; i<timenames.size(); i++)
			{
				Timeitem ti = new Timeitem();	//创建Timeitem实例
				ti.setItem(timenames.get(i));
				
				bookbns.insertTimeItem(ti);
			}
		}

		return SUCCESS;
	}
}