package booking.dao;

import java.sql.Date;
import java.util.List;

import booking.po.*;

public interface BookingDao  
{
	List<Booking> findBookingByFieldAndDate(String bookField, Date bookDate);

	Booking get(Long id);
	
	void save(Booking bk);
	
	List<Booking> findBookingByFieldAndDay(String bookUser, String selectedFieldName, Date selectedDate);
	
	void deleteBooking(Booking b);
	
	List<Booking> getBookingByIDs(List<Long> bookIDs);

	List<Booking> findBookingByUserAndNow(String bookUser, Date today, int nowhour);

	void deleteAllBooking(List<Booking> bookList);

	List<Booking> findBookingByUserAndFieldAndNow(String bookUser, String selectedFieldName, Date today, int nowhour);

	List<Booking> findBookingByUserAndDateAndNow(String bookUser, Date selectedDate);

	List<Booking> getBookingByNow(Date today, int nowhour);

}