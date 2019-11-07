package booking.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import booking.po.*;
import booking.dao.*;

public class TimeitemDaoImpl extends HibernateDaoSupport implements TimeitemDao
{
	public List<String> findAllTimeitems()
	{
		return (List<String>)getHibernateTemplate().find("select item from Timeitem");
	}

	public List<Timeitem> getAllTimeItem()
	{
		return (List<Timeitem>)getHibernateTemplate().find("from Timeitem");
	}

	public List<Timeitem> getTimeItemByName(String nowtimename)
	{
		return (List<Timeitem>)getHibernateTemplate().findByNamedParam("from Timeitem where item = :n", "n", nowtimename);
	}

	public void updateTimeitem(Timeitem ti)
	{
		getHibernateTemplate().saveOrUpdate(ti);
	}

	public void deleteTimeItem(Timeitem t)
	{
		getHibernateTemplate().delete(t);
	}

	public List<Timeitem> getTimeItemsByTimenames(List<String> timeItemNames)
	{
		return (List<Timeitem>)getHibernateTemplate().findByNamedParam("from Timeitem where item IN :n", "n", timeItemNames);
	}

	public void deleteSltTimeItems(List<Timeitem> timeList)
	{
		getHibernateTemplate().deleteAll(timeList);
	}

	public void insertTimeItem(Timeitem ti)
	{
		getHibernateTemplate().save(ti);
	}

}