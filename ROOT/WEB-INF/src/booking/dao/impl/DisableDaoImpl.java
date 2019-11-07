package booking.dao.impl;

import java.util.List;
import java.sql.Date;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import booking.po.*;
import booking.dao.*;

public class DisableDaoImpl extends HibernateDaoSupport implements DisableDao
{
	public List<Disable> findDisableByFieldAndDate(String fieldName, Date disableDate)
	{
		return (List<Disable>)getHibernateTemplate().findByNamedParam("from Disable where fieldName = :f and disableDate = :d", new String[]{"f", "d"}, new Object[]{fieldName, disableDate});
	}

	public void savePb(Disable pb)
	{
		getHibernateTemplate().save(pb);
	}

	public List<Disable> findPbByNow(Date today, int nowhour)
	{
		List<Disable> pauseList = (List<Disable>)getHibernateTemplate().findByNamedParam("from Disable where disableDate > :tod", "tod", today);
		List<Disable> pauseList2 = (List<Disable>)getHibernateTemplate().findByNamedParam("from Disable where disableDate = :tod", "tod", today);
		
		if(!pauseList2.isEmpty())
		{
			for(Disable da:pauseList2)
			{
				if(Integer.parseInt(da.getDisableTime().substring(6, 8)) > nowhour)
				{
					pauseList.add(da);
				}
			}
		}

		return pauseList;
	}

	public List<Disable> findPbByIDs(List<Long> bookIDs)
	{
		return (List<Disable>)getHibernateTemplate().findByNamedParam("from Disable where id IN :n", "n", bookIDs);
	}

	public void delAllPb(List<Disable> pbList)
	{
		getHibernateTemplate().deleteAll(pbList);
	}

}