package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class QueryPauseItemAction extends BaseAction
{
	private String str;
	private String username;
	private List<Disable> pauseItemList = new ArrayList<Disable>();
	private int firstPagination;
	private int lastPagination;
	private int pagination = 1;
	private int pages;
	private List<Disable> subpauseItemList = new ArrayList<Disable>();	
	
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

	//pauseItemList属性的setter和getter方法
	public void setPauseItemList(List<Disable> pauseItemList)
	{
		this.pauseItemList = pauseItemList;
	}
	public List<Disable> getPauseItemList()
	{
		 return this.pauseItemList;
	}

	//firstPagination属性的setter和getter方法
	public void setFirstPagination(int firstPagination)
	{
		this.firstPagination = firstPagination;
	}
	public int getFirstPagination()
	{
		return this.firstPagination;
	}

	//lastPagination属性的setter和getter方法
	public void setLastPagination(int lastPagination)
	{
		this.lastPagination = lastPagination;
	}
	public int getLastPagination()
	{
		return this.lastPagination;
	}

	//pagination属性的setter和getter方法
	public void setPagination(int pagination)
	{
		this.pagination = pagination;
	}
	public int getPagination()
	{
		return this.pagination;
	}

	//pages属性的setter和getter方法
	public void setPages(int pages)
	{
		this.pages = pages;
	}
	public int getPages()
	{
		return this.pages;
	}

	//subpauseItemList属性的setter和getter方法
	public void setSubpauseItemList(List<Disable> subpauseItemList)
	{
		this.subpauseItemList = subpauseItemList;
	}
	public List<Disable> getSubpauseItemList()
	{
		 return this.subpauseItemList;
	}

	public String execute() throws Exception
	{
		String suname = (String)ActionContext.getContext().getSession().get("sessusername");

		if(suname==null)
		{
			setStr("s1");	//会话已失效，请重新登录
			return ERROR;
		}

		if(!username.equals(suname))
		{
			setStr("s2");	//帐户username已退出，现在的帐户是suname，点击确定或关闭后将返回suname的管理界面
			return ERROR;
		}

		List<User> usl = bookbns.getUserByName(suname);

		if(usl.isEmpty())
		{
			setStr("s3");	//当前登录帐户已不存在
			ActionContext.getContext().getSession().put("sessusername", null);
			return ERROR;
		}
		else
		{
			User u = usl.get(0);
			if(u.getPrerogative() != 1)
			{
				setStr("s4");	//当前登录用户不是管理员
				return ERROR;
			}
		}

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		TimeZone tz = TimeZone.getTimeZone("Asia/Chongqing");
		ft.setTimeZone(tz);

		Calendar cal = Calendar.getInstance(tz);
		int nowhour = cal.get(Calendar.HOUR_OF_DAY);

		String nowDate = ft.format(cal.getTime());
		java.sql.Date today = java.sql.Date.valueOf(nowDate);

		pauseItemList = bookbns.getPbByNow(today, nowhour);
		
		if(pauseItemList.isEmpty())
		{
			return SUCCESS;			
		}

		int pgsize = pauseItemList.size();
		
		if(pgsize%15 != 0)
		{
			pages = pgsize/15 + 1;
		}
		else
		{
			pages = pgsize/15;
		}

		if(pagination > pages)
		{
			pagination = pages;
		}
		
		int fromIndex = (pagination-1)*15;
		int reachableIndex = fromIndex + 15;
		int toIndex;
		
		if(pgsize > reachableIndex)
		{
			toIndex = reachableIndex;
		}
		else
		{
			toIndex = pgsize;
		}

		subpauseItemList = pauseItemList.subList(fromIndex, toIndex);

		int reachablePagination;
		if(pagination == 1)
		{
			reachablePagination = 10;
		}
		else
		{
			reachablePagination = pagination + 8;
		}
		
		if(pages > reachablePagination)
		{
			lastPagination = reachablePagination;
		}
		else
		{
			lastPagination = pages;
		}
		
		if(lastPagination > 9)
		{
			firstPagination = lastPagination - 9;
		}
		else
		{
			firstPagination = 1;
		}

		return SUCCESS;
	}
}