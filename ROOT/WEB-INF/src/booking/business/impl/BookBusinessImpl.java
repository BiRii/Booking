package booking.business.impl;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map;
import java.sql.Date;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import java.util.*;

import booking.business.BookBusiness;
import booking.dao.*;
import booking.po.*;

public class BookBusinessImpl implements BookBusiness
{
	//以下是该业务逻辑组件所依赖的DAO组件
	private FieldDao fieldDao;
	private BookingDao bookingDao;
	private DisableDao disableDao;
	private TimeitemDao timeitemDao;
	private UserDao userDao;
	//业务逻辑组件发送邮件所依赖的两个Bean
	private MailSender mailSender;
	private SimpleMailMessage message;
	
	//为业务逻辑组件依赖注入DAO组件所需的setter方法	
	public void setFieldDao(FieldDao fieldDao) 
	{
		this.fieldDao = fieldDao; 
	}

	public void setBookingDao(BookingDao bookingDao) 
	{
		this.bookingDao = bookingDao; 
	}

	public void setDisableDao(DisableDao disableDao) 
	{
		this.disableDao = disableDao; 
	}

	public void setTimeitemDao(TimeitemDao timeitemDao) 
	{
		this.timeitemDao = timeitemDao; 
	}

	public void setUserDao(UserDao userDao) 
	{
		this.userDao = userDao; 
	}

	//为业务逻辑组件注入两个邮件发送Bean的setter方法
	public void setMailSender(MailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	public void setMessage(SimpleMailMessage message)
	{
		this.message = message;
	}
	
	public List<Field> getAllField()
	{
		return fieldDao.findAllField();
	}
	
	public List<String> getAllTimeitems()
	{
		return timeitemDao.findAllTimeitems();
	}	

	public List<Booking> getBookingByFieldAndDate(String bookField, Date bookDate)
	{
		return bookingDao.findBookingByFieldAndDate(bookField, bookDate);
	}	

	public List<Disable> getDisableByFieldAndDate(String fieldName, Date disableDate)
	{
		return disableDao.findDisableByFieldAndDate(fieldName, disableDate);
	}	

	public void saveUser(User us)
	{
		userDao.save(us);
	}

	public void insert(Booking bk)
	{
		bookingDao.save(bk);
	}

	public void savePb(Disable pb)
	{
		disableDao.savePb(pb);
	}

	public String getVercode(String email)
	{
		if(email == null||email.equals(""))
		{
			return "电子邮箱不能为空";
		}
		else if(email.contains(" "))
		{
			return "电子邮箱不能包含空格";
		}
		else if(!email.contains("@"))
		{
			return "电子邮箱必须包含@符号";
		}
		else if(!email.contains("."))
		{
			return "电子邮箱必须包含.符号";
		}
		else if(email.length() > 30)
		{
			return "电子邮箱不能多于30个字符";
		}
		else
		{
			String boo = valiEmail(email);
			if(boo.equals("false"))
			{
				return "s1";	//该邮箱已存在，请修改
			}
			
			Map session = ActionContext.getContext().getSession();
			Random rnd = new Random();
			String vercode = String.valueOf(rnd.nextInt(100000000));			
			session.put("verifycode", vercode);
			//发送邮件
			SimpleMailMessage msg = new SimpleMailMessage(this.message);
			msg.setTo(email);		
			msg.setText("验证码是: " + vercode + "，10分钟内有效。");
			mailSender.send(msg);
			
			session.put("mailbox", email);
			
			Timer t = new Timer();
			TimerTask task = new TimerTask()
			{
				@Override
				public void run()
				{
					session.put("verifycode", null);
				}
			};
			t.schedule(task, 600000);
			
			return "发送成功";				
		}		
	}

	public String getMailcode(String email)
	{
		if(email == null||email.equals(""))
		{
			return "电子邮箱不能为空";
		}
		else if(email.contains(" "))
		{
			return "电子邮箱不能包含空格";
		}
		else if(!email.contains("@"))
		{
			return "电子邮箱必须包含“@”符号";
		}
		else if(!email.contains("."))
		{
			return "电子邮箱必须包含“.”符号";
		}
		else if(email.length() > 30)
		{
			return "电子邮箱不能多于30个字符";
		}
		else
		{
			String boo = valiEmail(email);
			if(boo.equals("true"))
			{
				return "s1";	//该邮箱不存在，请修改
			}
			
			Map session = ActionContext.getContext().getSession();
			Random rnd = new Random();
			String vercode = String.valueOf(rnd.nextInt(100000000));			
			session.put("verifycode3", vercode);
			//发送邮件
			SimpleMailMessage msg = new SimpleMailMessage(this.message);
			msg.setTo(email);		
			msg.setText("验证码是: " + vercode + "，10分钟内有效。");
			mailSender.send(msg);
			
			session.put("mailbox3", email);
			
			Timer t = new Timer();
			TimerTask task = new TimerTask()
			{
				@Override
				public void run()
				{
					session.put("verifycode3", null);
				}
			};
			t.schedule(task, 600000);
			
			return "发送成功";			
		}		
	}

	public List<User> getUserByEmail(String email)
	{
		return userDao.findUserByEmail(email);
	}

	public String RetrieveName(String email, String vercode)
	{
		Map session = ActionContext.getContext().getSession();

		String vcode = (String)session.get("verifycode3");
		if(vercode == null||vercode.equals(""))
		{
			return "验证码不能为空";
		}
		else if(vercode.contains(" "))
		{
			return "验证码不能包含空格";
		}
		else if(!vercode.equals(vcode))
		{
			return "验证码不正确";
		}	
		else
		{
			String mbox = (String)session.get("mailbox3");				
			if(email == null||email.equals(""))
			{
				return "电子邮箱不能为空";
			}
			else if(email.contains(" "))
			{
				return "电子邮箱不能包含空格";
			}
			else if(!email.contains("."))
			{
				return "电子邮箱要包含.字符";
			}
			else if(email.length() > 30)
			{
				return "电子邮箱不能多于30个字符";
			}			
			else if(email.length() < 5)
			{
				return "电子邮箱不能少于5个字符";
			}	
			else if(!email.equals(mbox))
			{
				return "您现在输入的邮箱【" + email + "】与收到验证码的邮箱【" + mbox + "】不一致，如要修改则需重新发送验证码。";
			}
			else
			{
				List<User> ul = userDao.findUserByEmail(email);
				if(ul.isEmpty())
				{
					return "没找到对应帐户";
				}
				session.put("verifycode3", null);
				session.put("mailbox3", null);
				//获取User实例
				User us = ul.get(0);
				return "找回用户名成功，您的用户名是：" + us.getUsername();
			}
		}
	}

	public String ModifyMail(String email, String vercode, String newmail, String newcode)
	{
		if(newmail.equals(email))
		{
			return "新邮箱不能与现在的邮箱相同";
		}
		
		Map session = ActionContext.getContext().getSession();

		String vcode = (String)session.get("verifycode3");
		if(vercode == null||vercode.equals(""))
		{
			return "验证码不能为空";
		}
		else if(vercode.contains(" "))
		{
			return "验证码不能包含空格";
		}
		else if(!vercode.equals(vcode))
		{
			return "验证码不正确";
		}	
		else
		{
			String mbox = (String)session.get("mailbox3");
			if(email == null||email.equals(""))
			{
				return "电子邮箱不能为空";
			}
			else if(email.contains(" "))
			{
				return "电子邮箱不能包含空格";
			}
			else if(!email.contains("."))
			{
				return "电子邮箱要包含.字符";
			}
			else if(email.length() > 30)
			{
				return "电子邮箱不能多于30个字符";
			}			
			else if(email.length() < 5)
			{
				return "电子邮箱不能少于5个字符";
			}	
			else if(!email.equals(mbox))
			{
				return "您现在输入的邮箱【" + email + "】与收到验证码的邮箱【" + mbox + "】不一致，如要修改则需重新发送验证码。";
			}
			else
			{
				String ncode = (String)session.get("verifycode");
				if(newcode == null||newcode.equals(""))
				{
					return "新邮箱验证码不能为空";
				}
				else if(newcode.contains(" "))
				{
					return "新邮箱验证码不能包含空格";
				}
				else if(!newcode.equals(ncode))
				{
					return "新邮箱验证码不正确";
				}	
				else
				{
					String nbox = (String)session.get("mailbox");
					if(newmail == null||newmail.equals(""))
					{
						return "新电子邮箱不能为空";
					}
					else if(newmail.contains(" "))
					{
						return "新电子邮箱不能包含空格";
					}
					else if(!newmail.contains("."))
					{
						return "新电子邮箱要包含.字符";
					}
					else if(newmail.length() > 30)
					{
						return "新电子邮箱不能多于30个字符";
					}			
					else if(newmail.length() < 5)
					{
						return "新电子邮箱不能少于5个字符";
					}
					else if(!newmail.equals(nbox))
					{
						return "您现在输入的新邮箱【" + newmail + "】与收到验证码的新邮箱【" + nbox + "】不一致，如要修改则需重新发送验证码。";
					}
					else
					{
						List<User> ul = userDao.findUserByEmail(email);
						if(ul.isEmpty())
						{
							return "没找到对应帐户";
						}
						//获取User实例
						User us = ul.get(0);
						us.setEmail(newmail);
						userDao.update(us);
						session.put("verifycode3", null);
						session.put("verifycode", null);
						session.put("mailbox3", null);
						session.put("mailbox", null);
						return "修改邮箱成功";
					}
				}
			}
		}
	}

	public List<String> getAllName()
	{
		return userDao.findAllName();
	}

	public String valiUsername(String username)
	{
		List<String> nl = userDao.findAllName();
		if(nl.contains(username))
		{
			return "false";
		}
		return "true";
	}
	
	public String valiEmail(String email)
	{
		List<String> ml = userDao.findAllEmail();
		if(ml.contains(email))
		{
			return "false";
		}		
		return "true";
	}

	public List<Booking> getBookingByFieldAndDay(String bookUser, String selectedFieldName, Date selectedDate)
	{
		return bookingDao.findBookingByFieldAndDay(bookUser, selectedFieldName, selectedDate);
	}

	public Booking getBookingById(Long bookID)
	{
		return bookingDao.get(bookID);
	}

	public void deleteBooking(Booking b)
	{
		bookingDao.deleteBooking(b);
	}

	public List<Booking> getBookingByIDs(List<Long> bookIDs)
	{
		return bookingDao.getBookingByIDs(bookIDs);
	}

	public List<Booking> getBookingByUserAndNow(String bookUser, Date today, int nowhour)
	{
		return bookingDao.findBookingByUserAndNow(bookUser, today, nowhour);
	}

	public void deleteAllBooking(List<Booking> bookList)
	{
		bookingDao.deleteAllBooking(bookList);
	}
	
	public List<Integer> getPrerogativeByName(String suname)
	{
		return userDao.findPrerogativeByName(suname);
	}

	public List<Booking> getBookingByUserAndFieldAndNow(String bookUser, String selectedFieldName, Date today, int nowhour)
	{
		return bookingDao.findBookingByUserAndFieldAndNow(bookUser, selectedFieldName, today, nowhour);
	}

	public List<Booking> getBookingByUserAndDateAndNow(String bookUser, Date selectedDate)
	{
		return bookingDao.findBookingByUserAndDateAndNow(bookUser, selectedDate);
	}

	public void updateField(Field f)
	{
		fieldDao.update(f);
	}

	public String deleteFieldByFName(String nowfname)
	{
		List<Field> fl = fieldDao.getFieldByFName(nowfname);
		
		if(fl.isEmpty())
		{
			return "s4";	//该条目已不存在
		}
		else
		{
			Field f = fl.get(0);	//获取Field实例
			fieldDao.deleteField(f);
			return "s5";
		}
	}

	public List<Field> getFieldByFieldname(String fname)
	{
		return fieldDao.getFieldByFName(fname);
	}

	public void deleteFields(List<Field> sltdflist)
	{
		fieldDao.deleteFields(sltdflist);
	}

	public void deleteAllFields(List<Field> fieldList)
	{
		fieldDao.deleteAllFields(fieldList);
	}

	public void insertField(Field fd)
	{
		fieldDao.saveField(fd);
	}

	public List<Timeitem> getAllTimeItem()
	{
		return timeitemDao.getAllTimeItem();
	}

	public List<Timeitem> getTimeItemByName(String nowtimename)
	{
		return timeitemDao.getTimeItemByName(nowtimename);
	}

	public void updateTimeitem(Timeitem ti)
	{
		timeitemDao.updateTimeitem(ti);
	}

	public List<String> getAllFieldNames()
	{
		return fieldDao.getAllFieldNames();
	}	

	public void deleteTimeItem(Timeitem t)
	{
		timeitemDao.deleteTimeItem(t);
	}

	public List<Timeitem> getTimeItemsByTimenames(List<String> timeItemNames)
	{
		return timeitemDao.getTimeItemsByTimenames(timeItemNames);
	}

	public void deleteSltTimeItems(List<Timeitem> timeList)
	{
		timeitemDao.deleteSltTimeItems(timeList);
	}

	public void insertTimeItem(Timeitem ti)
	{
		timeitemDao.insertTimeItem(ti);
	}

	public List<Booking> getBookingByNow(Date today, int nowhour)
	{
		return bookingDao.getBookingByNow(today, nowhour);
	}

	public List<User> getAllUser()
	{
		return userDao.findAll();
	}

	public List<User> getUserByName(String suname)
	{
		return userDao.findUserByName(suname);
	}

	public List<User> getUserByNames(List<String> bookUserNames)
	{
		return userDao.getUserByNames(bookUserNames);
	}

	public void deleteAllUser(List<User> ul)
	{
		userDao.deleteAllUser(ul);
	}

	public void updateUser(User u)
	{
		userDao.update(u);
	}

	public List<User> getUserByPrerogative()
	{
		return userDao.getUserByPrerogative();
	}

	public List<Disable> getPbByNow(Date today, int nowhour)
	{
		return disableDao.findPbByNow(today, nowhour);
	}

	public List<Disable> getPbByIDs(List<Long> bookIDs)
	{
		return disableDao.findPbByIDs(bookIDs);
	}

	public void deleteAllPb(List<Disable> pbList)
	{
		disableDao.delAllPb(pbList);
	}

}