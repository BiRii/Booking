package booking.dao.impl;

import java.util.*;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import booking.po.*;
import booking.dao.*;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao  
{
	public User get(int id)
	{
		return (User)getHibernateTemplate().get(User.class, id);
	}

	public void save(User user)
	{
		getHibernateTemplate().save(user);
	}

	public void update(User user)
	{
		getHibernateTemplate().saveOrUpdate(user);
	}

	public void delete(int id)
	{
		getHibernateTemplate().delete(get(id));
	}

	public void delete(User user)
	{
		getHibernateTemplate().delete(user);
	}

	public List<User> findAll()
	{
		return (List<User>)getHibernateTemplate().find("from User");
	}

	public List<String> findAllName()
	{
		return (List<String>)getHibernateTemplate().find("select username from User");
	}

	public List<User> findUserByEmail(String email)
	{
		return (List<User>)getHibernateTemplate().findByNamedParam("from User where email = :e", "e", email);
	}
	
	public List<String> findAllEmail()
	{
		return (List<String>)getHibernateTemplate().find("select email from User");
	}

	public List<Integer> findPrerogativeByName(String suname)
	{
		return (List<Integer>)getHibernateTemplate().findByNamedParam("select prerogative from User where username = :n", "n", suname);
	}

	public List<User> findUserByName(String suname)
	{
		return (List<User>)getHibernateTemplate().findByNamedParam("from User where username = :n", "n", suname);
	}

	public List<User> getUserByNames(List<String> bookUserNames)
	{
		return (List<User>)getHibernateTemplate().findByNamedParam("from User where username IN :n", "n", bookUserNames);
	}

	public void deleteAllUser(List<User> ul)
	{
		getHibernateTemplate().deleteAll(ul);
	}

	public List<User> getUserByPrerogative()
	{
		return (List<User>)getHibernateTemplate().find("from User where prerogative = 1");
	}

}