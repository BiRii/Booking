package booking.dao;

import java.util.List;

import booking.po.*;

public interface FieldDao  
{
	List<Field> findAllField();

	List<Field> getFieldByFName(String nowfname);

	List<String> getAllFieldNames();

	void update(Field f);

	void deleteField(Field f);

	void deleteFields(List<Field> sltdflist);

	void deleteAllFields(List<Field> fieldList);

	void saveField(Field fd);

}