package booking.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import booking.po.*;
import booking.dao.*;

public class FieldDaoImpl extends HibernateDaoSupport implements FieldDao
{
	public List<Field> findAllField()
	{
		return (List<Field>)getHibernateTemplate().find("from Field");
	}

	public List<Field> getFieldByFName(String nowfname)
	{
		return (List<Field>)getHibernateTemplate().findByNamedParam("from Field where name = :n", "n", nowfname);
	}

	public List<String> getAllFieldNames()
	{
		return (List<String>)getHibernateTemplate().find("select name from Field");
	}

	public void update(Field f)
	{
		getHibernateTemplate().saveOrUpdate(f);
	}

	public void deleteField(Field f)
	{
		getHibernateTemplate().delete(f);
	}

	public void deleteFields(List<Field> sltdflist)
	{
		getHibernateTemplate().deleteAll(sltdflist);
	}

	public void deleteAllFields(List<Field> fieldList)
	{
		getHibernateTemplate().deleteAll(fieldList);
	}

	public void saveField(Field fd)
	{
		getHibernateTemplate().save(fd);
	}

}