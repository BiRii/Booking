package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class DeleteAllFieldsAction extends BaseAction
{
	private String str;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}

	@Override
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
			List<Field> fieldList = bookbns.getAllField();
			
			if(fieldList.isEmpty())
			{
				setStr("s2");	//系统无记录
			}
			else
			{
				bookbns.deleteAllFields(fieldList);
			}
		}

		return SUCCESS;
	}
}