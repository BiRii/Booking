package booking.dao;

import java.util.List;

import booking.po.User;

public interface UserDao  
{
	User get(int id);	/*根据id查找用户 @param id 需要查找的用户id */

	void save(User user);	/*增加用户 @param user 需要增加的用户 */

	void update(User user);	/*修改用户 @param user 需要修改的用户 */

	void delete(int id); /*删除用户 @param id 需要删除的用户id */

	void delete(User user);	/*删除用户 @param user 需要删除的用户 */

	List<User> findAll();	/*查询全部用户 @return 获得全部用户 */

	List<String> findAllName(); //查询全部用户名

	List<User> findUserByEmail(String email);

	List<String> findAllEmail();
	
	List<Integer> findPrerogativeByName(String suname);

	List<User> findUserByName(String suname);

	List<User> getUserByNames(List<String> bookUserNames);

	void deleteAllUser(List<User> ul);

	List<User> getUserByPrerogative();

}