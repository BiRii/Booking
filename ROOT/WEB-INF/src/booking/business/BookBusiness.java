package booking.business;

import java.util.List;
import java.sql.Date;

import booking.po.*;

public interface BookBusiness
{
	List<Field> getAllField();
		
	List<String> getAllTimeitems();
	
	List<Booking> getBookingByFieldAndDate(String bookField, Date bookDate);

	List<Disable> getDisableByFieldAndDate(String fieldName, Date disableDate);

	void saveUser(User us);
	
	void insert(Booking bk);

	void savePb(Disable pb);

	String getVercode(String email);
	
	String getMailcode(String email);

	List<User> getUserByEmail(String email);

	String RetrieveName(String email, String vercode);
	
	String ModifyMail(String email, String vercode, String newmail, String newcode);

	List<String> getAllName();

	String valiUsername(String username);
	
	String valiEmail(String email);
	
	List<Booking> getBookingByFieldAndDay(String bookUser, String selectedFieldName, Date selectedDate);

	Booking getBookingById(Long bookID);

	void deleteBooking(Booking b);
	
	List<Booking> getBookingByIDs(List<Long> bookIDs);

	List<Booking> getBookingByUserAndNow(String bookUser, Date today, int nowhour);

	void deleteAllBooking(List<Booking> bookList);
	
	List<Integer> getPrerogativeByName(String suname);

	List<Booking> getBookingByUserAndFieldAndNow(String bookUser, String selectedFieldName, Date today, int nowhour);

	List<Booking> getBookingByUserAndDateAndNow(String bookUser, Date selectedDate);

	void updateField(Field f);

	String deleteFieldByFName(String nowfname);

	List<Field> getFieldByFieldname(String fname);

	void deleteFields(List<Field> sltdflist);
	
	void deleteAllFields(List<Field> fieldList);

	void insertField(Field fd);

	List<Timeitem> getAllTimeItem();

	List<Timeitem> getTimeItemByName(String nowtimename);

	void updateTimeitem(Timeitem ti);

	List<String> getAllFieldNames();

	void deleteTimeItem(Timeitem t);

	List<Timeitem> getTimeItemsByTimenames(List<String> timeItemNames);

	void deleteSltTimeItems(List<Timeitem> timeList);

	void insertTimeItem(Timeitem ti);

	List<Booking> getBookingByNow(Date today, int nowhour);

	List<User> getAllUser();

	List<User> getUserByName(String suname);

	List<User> getUserByNames(List<String> bookUserNames);

	void deleteAllUser(List<User> ul);

	void updateUser(User u);

	List<User> getUserByPrerogative();

	List<Disable> getPbByNow(Date today, int nowhour);

	List<Disable> getPbByIDs(List<Long> bookIDs);

	void deleteAllPb(List<Disable> pbList);

}