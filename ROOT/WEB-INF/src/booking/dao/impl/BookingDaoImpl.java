package booking.dao.impl;

import java.sql.Date;
import java.util.*;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import booking.po.*;
import booking.dao.*;

public class BookingDaoImpl extends HibernateDaoSupport implements BookingDao
{	
	public List<Booking> findBookingByFieldAndDate(String bookField, Date bookDate)
	{
		return (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookField = :f and bookDate = :d", new String[]{"f", "d"}, new Object[]{bookField, bookDate});
	}

	public Booking get(Long id)
	{
		return (Booking)getHibernateTemplate().get(Booking.class, id);
	}

	public void save(Booking bk)
	{
		getHibernateTemplate().save(bk);
	}

	public List<Booking> findBookingByFieldAndDay(String bookUser, String selectedFieldName, Date selectedDate)
	{
		return (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookUser = :u and bookField = :f and bookDate = :d", new String[]{"u", "f", "d"}, new Object[]{bookUser, selectedFieldName, selectedDate});
	}

	public void deleteBooking(Booking b)
	{
		getHibernateTemplate().delete(b);
	}

	public List<Booking> getBookingByIDs(List<Long> bookIDs)
	{
		return (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookId IN :n", "n", bookIDs);
	}

	public List<Booking> findBookingByUserAndNow(String bookUser, Date today, int nowhour)
	{
		List<Booking> bookList = new ArrayList<Booking>();
		List<Booking> bookList2 = new ArrayList<Booking>();

		bookList = (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookUser = :u and bookDate > :tod", new String[]{"u", "tod"}, new Object[]{bookUser, today});
		bookList2 = (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookUser = :u and bookDate = :tod", new String[]{"u", "tod"}, new Object[]{bookUser, today});
		
		if(!bookList2.isEmpty())
		{
			for(Booking b:bookList2)
			{
				if(Integer.parseInt(b.getBookTime().substring(6, 8)) > nowhour)
				{
					bookList.add(b);
				}
			}
		}
		
		return bookList;
	}

	public void deleteAllBooking(List<Booking> bookList)
	{
		getHibernateTemplate().deleteAll(bookList);
	}

	public List<Booking> findBookingByUserAndFieldAndNow(String bookUser, String selectedFieldName, Date today, int nowhour)
	{
		List<Booking> bookList = new ArrayList<Booking>();
		List<Booking> bookList2 = new ArrayList<Booking>();

		bookList = (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookUser = :u and bookField = :n and bookDate > :tod", new String[]{"u", "n", "tod"}, new Object[]{bookUser, selectedFieldName, today});
		bookList2 = (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookUser = :u and bookField = :n and bookDate = :tod", new String[]{"u", "n", "tod"}, new Object[]{bookUser, selectedFieldName, today});

		if(!bookList2.isEmpty())
		{
			for(Booking b:bookList2)
			{
				if(Integer.parseInt(b.getBookTime().substring(6, 8)) > nowhour)
				{
					bookList.add(b);
				}
			}
		}
		
		return bookList;
	}

	public List<Booking> findBookingByUserAndDateAndNow(String bookUser, Date selectedDate)
	{
		return (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookUser = :u and bookDate = :d", new String[]{"u", "d"}, new Object[]{bookUser, selectedDate});
	}

	public List<Booking> getBookingByNow(Date today, int nowhour)
	{
		List<Booking> bookList = (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookDate > :tod", "tod", today);
		List<Booking> bookList2 = (List<Booking>)getHibernateTemplate().findByNamedParam("from Booking where bookDate = :tod", "tod", today);
		
		if(!bookList2.isEmpty())
		{
			for(Booking b:bookList2)
			{
				if(Integer.parseInt(b.getBookTime().substring(6, 8)) > nowhour)
				{
					bookList.add(b);
				}
			}
		}
		
		return bookList;
	}

}