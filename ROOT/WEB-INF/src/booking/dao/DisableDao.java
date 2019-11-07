package booking.dao;

import java.util.List;
import java.sql.Date;

import booking.po.*;

public interface DisableDao  
{
	List<Disable> findDisableByFieldAndDate(String fieldName, Date disableDate);

	void savePb(Disable pb);

	List<Disable> findPbByNow(Date today, int nowhour);

	List<Disable> findPbByIDs(List<Long> bookIDs);

	void delAllPb(List<Disable> pbList);

}