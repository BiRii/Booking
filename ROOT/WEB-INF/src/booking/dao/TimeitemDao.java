package booking.dao;

import java.util.List;

import booking.po.*;

public interface TimeitemDao  
{
	List<String> findAllTimeitems();

	List<Timeitem> getAllTimeItem();

	List<Timeitem> getTimeItemByName(String nowtimename);

	void updateTimeitem(Timeitem ti);

	void deleteTimeItem(Timeitem t);

	List<Timeitem> getTimeItemsByTimenames(List<String> timeItemNames);

	void deleteSltTimeItems(List<Timeitem> timeList);

	void insertTimeItem(Timeitem ti);

}