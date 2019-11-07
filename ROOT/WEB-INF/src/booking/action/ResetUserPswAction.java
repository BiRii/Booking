package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class ResetUserPswAction extends BaseAction
{
	private String str;
	private String username;
	private String userpassword;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
	}

	//username属性的setter和getter方法
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

	//userpassword属性的setter和getter方法
	public void setUserpassword(String userpassword)
	{
		this.userpassword = userpassword;
	}
	public String getUserpassword()
	{
		return this.userpassword;
	}

	public String execute() throws Exception
	{
		String suname = (String)ActionContext.getContext().getSession().get("sessusername");

		if(suname==null)
		{
			setStr("s1");	//会话已失效，请重新登录
			return SUCCESS;
		}

		List<User> usl = bookbns.getUserByName(suname);

		if(usl.isEmpty())
		{
			setStr("s2");	//系统已不存在当前登录帐户
			ActionContext.getContext().getSession().put("sessusername", null);
			return SUCCESS;
		}
		else
		{
			User u = usl.get(0);
			if(u.getPrerogative() != 1)
			{
				setStr("s3");	//当前登录用户不是管理员
				return SUCCESS;
			}
		}

		if(userpassword == null || userpassword.equals(""))
		{
			setStr("程序异常，系统收到的密码为空");	//密码不能为空
			return SUCCESS;
		}
		else
		{
			if(username == null || username.equals(""))
			{
				setStr("程序异常，系统收到的用户名为空");	//用户名不能为空
				return SUCCESS;
			}
			else if(username.length() > 512)
			{
				setStr("程序异常，系统收到的用户名超过512个字符");	//用户名不能多于512个字符
				return SUCCESS;
			}
			else
			{
				List<User> ul = bookbns.getUserByName(username);
				if(ul.isEmpty())
				{
					setStr("系统已不存在该帐户，不能被重置密码");	//系统已不存在该帐户
					return SUCCESS;
				}

				//获取User实例
				User us = ul.get(0);
				String username2 = us.getUsername();
				String regtime = us.getRegistertime();

				String base = "`1234567890-=qwertyuiop[]asdfghjkl;zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:ZXCVBNM<>?αβγδεζηθικλμνξοπρστυφχψωабвгдеёжзийклмнопрстуфхцчшщъыьэюяㄅㄉˇˋㄓˊ˙ㄚㄞㄢㄦㄆㄊㄍㄐㄔㄗㄧㄛㄟㄣㄇㄋㄎㄑㄕㄘㄨㄜㄠㄤㄈㄌㄏㄒㄖㄙㄩㄝㄡㄥāáǎàōóǒòêēéěèīíǐìūúǔùǖǘǚǜüぁぃぅぇぉかきくけこんさしすせそたちつってとゐなにぬねのはひふへほゑまみむめもゃゅょゎをⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫ①②③④⑤⑥⑦⑧⑨⑩⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇§№☆★○●◎◇◆□℃‰€■△▲※→←↑↓〓¤°＃＆＠＼︿＿―♂♀覔覕视覗觇覙覚覛覜覝覞覟覠觋摇尧遥窑谣姚咬舀药要耀椰噎耶抑踘踙踚踛踜踠蜷踤踥踦踧踨碰踭踰踲踳踊踶踷踸踻踼踾踿蹃蹅蹆跄蹍蹎蹏蹐溜蹔跸蹖蹗蹘蹚蹛蹜蹝蹞迹跖蹡蹢蹒踪蹥蹧蹨蹪蹫蹮蹱邸邰郏郅邾郐郄郇郓郦郢郜郗郛郫郯郾鄄鄢鄞鄣鄱鄯鄹酃酆刍奂劢劬劭劾哿勐勖勰叟燮矍廴凵凼鬯厶弁畚巯坌垩垡塾墼壅壑圩圬圪圳圹圮圯坜圻坂坩垅坫垆坼坻坨坭坶坳垭垤垌垲埏垧垴垓垠埕埘埚埙埒垸埴埯埸埤埝蹳蹵蹷蹸蹹跷蹻蹽蹾躀躂躃躄躆躈趸踌跻躌跃躎踯跞踬蹰躖躗躘躙跹躛躝躟躠蹑躢躣躤蹿躜躧躨躩躏躭躮躰躱躳躴躵躶躷躸躹躻躼躽躾躿躯軁軂軃軄軅軆軇軈軉车轧轨军軏軐軑轩軓轫軕軖軗軘軙軚轭軜軝軞软軠軡転軣軤堋堍埽埭堀堞堙塄堠塥塬墁墉墚墀馨鼙懿艹艽艿芏芊芨芄芎芑芗芙芫芸芾芰苈苊苣芘芷芮苋苌กขคฆงจฉชซธฌญฎฏฐฑฒณดตถทธนบปผพฝฟภมยรฤฦลวศษสหฬอฮอะอัอาอิอีอึอือุอูเอะเอแอะแอเอาะเอาเอิเอีะเอืะเอืโอไอใออำสบายดีไหมยินดีที่พบกันใหม่ئۇيغۇرتىلى【】（）；’，。、：”《》？";
				Random random = new Random();
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < 128; i++)
				{
					int number = random.nextInt(base.length());
					sb.append(base.charAt(number));
				}
				String RandomString = sb.toString();
				String subRandomString = RandomString.substring(4, 124);
				String subrs1 = subRandomString.substring(0, 70);
				String subrs2 = subRandomString.substring(70);

				String pswcombine = subrs1 + userpassword + subrs2 + username2 + regtime;

				byte[] secretBytes = null;
				try
				{
					secretBytes = MessageDigest.getInstance("SHA-512").digest(pswcombine.getBytes());
				}
				catch(NoSuchAlgorithmException e)
				{
					throw new RuntimeException("没有这个Hash算法！");
				}

				String pswhash = new BigInteger(1, secretBytes).toString(16);

				us.setUserpass(pswhash);
				us.setRandomcode(RandomString);
				us.setErrtime(0);
				bookbns.updateUser(us);

				setStr("重置密码成功");	//重置密码成功
			}

		}

		return SUCCESS;
	}
}