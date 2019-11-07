package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class ResetPswAction extends BaseAction
{
	private String str;
	private String userpassword;
	private String email;
	private String vercode;
	
	//str属性的setter和getter方法
	public void setStr(String str)
	{
		this.str = str;
	}
	public String getStr()
	{
		return this.str;
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

	//email属性的setter和getter方法
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}

	//vercode属性的setter和getter方法
	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		return this.vercode;
	}

	public String execute() throws Exception
	{
		Map session = ActionContext.getContext().getSession();

		email = email.trim();
		vercode = vercode.trim();

		if(userpassword == null || userpassword.equals(""))
		{
			setStr("密码不能为空");
			return SUCCESS;
		}
		else
		{
			String vcode = (String)session.get("verifycode3");
			if(vercode == null || vercode.equals(""))
			{
				setStr("验证码不能为空");
				return SUCCESS;
			}
			else if(!vercode.equals(vcode))
			{
				setStr("验证码不正确");
				return SUCCESS;
			}
			else
			{
				String mbox = (String)session.get("mailbox3");

				if(email == null || email.equals(""))
				{
					setStr("电子邮箱不能为空");
					return SUCCESS;
				}
				else if(email.contains(" "))
				{
					setStr("电子邮箱不能包含空格");
					return SUCCESS;
				}
				else if(!email.contains("."))
				{
					setStr("电子邮箱要包含.字符");
					return SUCCESS;
				}
				else if(email.length() > 512)
				{
					setStr("电子邮箱不能多于512个字符");
					return SUCCESS;
				}			
				else if(email.length() < 5)
				{
					setStr("电子邮箱不能少于5个字符");
					return SUCCESS;
				}	
				else if(!email.equals(mbox))
				{
					setStr("您现在输入的邮箱【" + email + "】与收到验证码的邮箱【" + mbox + "】不一致，如要修改则需重新发送验证码。");
					return SUCCESS;
				}
				else
				{
					List<User> ul = bookbns.getUserByEmail(email);
					if(ul.isEmpty())
					{
						setStr("没找到对应帐户");
						return SUCCESS;
					}

					//获取User实例
					User us = ul.get(0);
					String username = us.getUsername();
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

					String pswcombine = subrs1 + userpassword + subrs2 + username + regtime;

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

					session.put("verifycode3", null);
					session.put("mailbox3", null);
					setStr("重置密码成功");
				}
			}
		}

		return SUCCESS;
	}
}