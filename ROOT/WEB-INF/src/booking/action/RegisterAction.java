package booking.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

import java.util.*;

import booking.action.base.BaseAction;
import booking.po.*;

public class RegisterAction extends BaseAction
{
	private String str;
	private String username;
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

		username = username.trim();
		userpassword = userpassword.trim();
		email = email.trim();
		vercode = vercode.trim();

		if(username == null || username.equals(""))
		{
			setStr("用户名不能为空");
			return SUCCESS;
		}
		else if(username.length() > 512)
		{
			setStr("用户名不能多于512个字符");
			return SUCCESS;
		}
		else
		{
			List<String> nl = bookbns.getAllName();
			if(nl.contains(username))
			{
				setStr("用户名已存在，请更换");
				return SUCCESS;
			}
		
			if(userpassword == null || userpassword.equals(""))
			{
				setStr("密码不能为空");
				return SUCCESS;
			}
			else if(userpassword.length() > 512)
			{
				setStr("密码不能多于512个字符");
				return SUCCESS;
			}
			else
			{
				String vcode = (String)session.get("verifycode");
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
					String mbox = (String)session.get("mailbox");				
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
					else if(!email.contains("@"))
					{
						setStr("电子邮箱要包含@字符");
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
					else if(!email.equals(mbox))
					{
						setStr("您现在输入的邮箱【" + email + "】与收到验证码的邮箱【" + mbox + "】不一致，如要修改则需重新发送验证码。");
						return SUCCESS;
					}
					else
					{
						String base = "`1234567890-=qwertyuiop[]asdfghjkl;zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:ZXCVBNM<>?αβγδεζηθικλμνξοπρστυφχψωабвгдеёжзийклмнопрстуфхцчшщъыьэюяㄅㄉˇˋㄓˊ˙ㄚㄞㄢㄦㄆㄊㄍㄐㄔㄗㄧㄛㄟㄣㄇㄋㄎㄑㄕㄘㄨㄜㄠㄤㄈㄌㄏㄒㄖㄙㄩㄝㄡㄥāáǎàōóǒòêēéěèīíǐìūúǔùǖǘǚǜüぁぃぅぇぉかきくけこんさしすせそたちつってとゐなにぬねのはひふへほゑまみむめもゃゅょゎをⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫ①②③④⑤⑥⑦⑧⑨⑩⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇§№☆★○●◎◇◆□℃‰€■△▲※→←↑↓〓¤°＃＆＠＼︿＿―♂♀覔覕视覗觇覙覚覛覜覝覞覟覠觋摇尧遥窑谣姚咬舀药要耀椰噎耶爷野冶也页掖业叶曳腋夜液一壹医揖铱依伊衣颐夷遗移仪胰疑沂宜姨彝椅蚁倚已乙矣以艺抑易邑屹亿役臆逸肄疫亦裔意毅忆义益溢诣议谊译异翼翌绎茵荫因殷音阴姻吟银淫寅饮尹引隐覢覣覤覥觎覧覨覩亲覫觊覭覮觏覰覱觐観覴覵覶觑覸覹觉覻覼览覾觌观觃觍觓筋觕觗觘觙觛觝觟觠觡觢觤觧觨觩觪觬觭觮觰觱觲觞觵觯觷触觹觺觻觼觽觾觿訁订讣訄訅訆计訉讯訋讧訍讨訏讦訑訒训訔讪讫托记訙訚讹訜讶印英樱婴鹰应缨莹萤营荧蝇迎赢盈影颖硬映哟拥佣臃痈庸雍踊蛹咏泳涌永恿勇用幽优悠忧尤由邮铀犹油游酉有友右佑釉诱又幼迂淤于盂榆虞愚舆余俞逾鱼愉渝渔隅予娱雨与屿禹宇语羽玉域芋郁吁遇喻峪御愈欲狱育誉訞讼訠訡欣诀訤讷訦訧訨訩访訫訬设訮訯訰许訲訳诉訵诃訷訸訹诊注证訽訿詀诂詂詃詄詅诋詇詉詊詋詌詍讵詏诈詑诒詓诏评詖詗诎詙詚诅詜詝词詟咏诩询诣詤詥试詧詨诗詪诧诟诡诠詯诘话该详詴诜詶詷詸詺詻诙詽詾诖誀浴寓裕预豫驭鸳渊冤元垣袁原援辕园员圆猿源缘远苑愿怨院曰约越跃钥岳粤月悦阅耘云郧匀陨允运蕴酝晕韵孕匝砸杂栽哉灾宰载再在咱攒暂赞赃脏葬遭糟凿藻枣早澡蚤躁噪造皂灶燥责择则泽贼怎增憎曾赠扎喳渣札轧誁誂誃诔诛诓夸誈誋志认誎誏誐诳诶誔诞誖誗诱誙诮誛誜誝语誟诚诫誢诬误诰诵誧诲誩说誫説読誮誯谁誱课誳誴誵谇誷誸诽誺誻谊誽誾调諀諁谄諃谆諅諆谈諈诿諊请諌诤諎诹諐诼谅諓諔諕论谂諘諙諚谀谍諝谝諟諠諡诨諣铡闸眨栅榨咋乍炸诈摘斋宅窄债寨瞻毡詹粘沾盏斩辗崭展蘸栈占战站湛绽樟章彰漳张掌涨杖丈帐账仗胀瘴障招昭找沼赵照罩兆肇召遮折哲蛰辙者锗蔗这浙珍斟真甄砧臻贞针侦枕疹诊震振镇阵蒸挣睁征狰争怔整拯正政谔諥谛谐諨諩諪谏諬谕谘諯諰讳諲谙諴諵谌讽诸諹谚諻谖諽诺諿谋谒谓謃誊诌謆謈謉谎謋謌謍谜謏谧謑謒謓谑謕谡谤謘谦谥讲謜谢謞謟谣謡謢謣謤謥謧谟謩謪谪谬謭謮謯謰謱謲讴謴謵謶謷謸谨謺謻謼謽谩謿譀哗譂譃譄譅帧症郑证芝枝支吱蜘知肢脂汁之织职直植殖执值侄址指止趾只旨纸志挚掷至致置帜峙制智秩稚质炙痔滞治窒中盅忠钟衷终种肿重仲众舟周州洲诌粥轴肘帚咒皱宙昼骤珠株蛛朱猪诸诛逐竹烛煮拄瞩嘱主著柱助蛀贮铸筑譆譇譈证譊譋譌譍谲讥譐譑譒譓譔譕谮譗识谯谭譛谱譝譞噪譠譡譢譣譤譥譧譨譩譪谵譭譮译议譱譲譳谴譵譶护譸譹譺譻譼誉譾譿读讁讂讃讄讅讆讇讈讉变讋讌讍雠讏讐讑谗让讔谰谶讗讘讙赞讛谠讝谳讟讬讱讻诇诐诪谉谞住注祝驻抓爪拽专砖转撰赚篆桩庄装妆撞壮状椎锥追赘坠缀谆准捉拙卓桌琢茁酌啄着灼浊兹咨资姿滋淄孜紫仔籽滓子自渍字鬃棕踪宗综总纵邹走奏揍租足卒族祖诅阻组钻纂嘴醉最罪尊遵昨左佐柞做作坐座谸谹谺谻谼谽谾谿豀豂豃豄豅岂丰豋豍竖豏丰豑豒豓艳豖豗豘豙豛豜豝豞豟豠豣豤豥豦豧豨豩猪豭豮豯豰豱豲豴豵豶豷豻豼豽豾豿貀貁貃貄貆貇貈貋狸貎貏貐貑貒猫貕貖貗貙貚貛貜贝贞貟负财贡貣貤貥貦贫货贩贪贯责貭亍丌兀丐廿卅丕亘丞鬲孬噩丨禺丿匕乇夭爻卮氐囟胤馗毓睾鼗丶亟鼐乜乩亓芈孛啬嘏仄厍厝厣厥厮靥赝匚叵匦匮匾赜卦卣刂刈刎刭刳刿剀剌剞剡剜蒯剽劂劁劐劓冂罔亻仃仉仂仨仡仫仞伛仳伢佤仵伥伧伉伫佞佧攸佚佝貮贮贳貱赀贰贵貵贬买贷貹贶费贴贻貾贸贺贲赂赁贿赅賆资贾賉贼賋賌賍賎賏賐赈赊宾賔赇賖賗賘賙赉賛赐賝赏賟赔赓贤卖贱賥赋赕賨賩质賫账赌賮賯賰賱賲賳赖賵賶賷賸賹赚赙购赛赜賿贀贁贂贃贽赘贆贇赠贉赞贋贌赡佟佗伲伽佶佴侑侉侃侏佾佻侪佼侬侔俦俨俪俅俚俣俜俑俟俸倩偌俳倬倏倮倭俾倜倌倥倨偾偃偕偈偎偬偻傥傧傩傺僖儆僭僬僦僮儇儋仝氽佘佥俎龠汆籴兮巽黉馘冁夔勹匍訇匐凫夙兕亠兖亳衮袤亵脔裒禀嬴蠃羸冫冱冽冼贎赢赆贑贒赃贔贕赎赝贘贙贚赣贜贠赑赒赗赟赥赨赩赪赬赮赯赱赲赸赹赺赻赼赽赾赿趀趂趃趆趇趈趉趌趍趎趏趐趒趓赶趖趗趘赵趚趛趜趝趞趠趡趢趤趥趦趧趋趩趪趫趬趭趮趯趰趱趶趷趹趻趽跀跁跂跅跇跈跉跊跍跐跒跓跔凇冖冢冥讠讦讧讪讴讵讷诂诃诋诏诎诒诓诔诖诘诙诜诟诠诤诨诩诮诰诳诶诹诼诿谀谂谄谇谌谏谑谒谔谕谖谙谛谘谝谟谠谡谥谧谪谫谮谯谲谳谵谶卩卺阝阢阡阱阪阽阼陂陉陔陟陧陬陲陴隈隍隗隰邗邛邝邙邬邡邴邳邶邺跕跘跙跜跠迹跢跥跦跧跩跭跮跰跱跲跴跶局跾跿踀踁踂踃踄踆踇踈踋踍踎践踑踒踓踕踖踗踘踙踚踛踜踠蜷踤踥踦踧踨碰踭踰踲踳踊踶踷踸踻踼踾踿蹃蹅蹆跄蹍蹎蹏蹐溜蹔跸蹖蹗蹘蹚蹛蹜蹝蹞迹跖蹡蹢蹒踪蹥蹧蹨蹪蹫蹮蹱邸邰郏郅邾郐郄郇郓郦郢郜郗郛郫郯郾鄄鄢鄞鄣鄱鄯鄹酃酆刍奂劢劬劭劾哿勐勖勰叟燮矍廴凵凼鬯厶弁畚巯坌垩垡塾墼壅壑圩圬圪圳圹圮圯坜圻坂坩垅坫垆坼坻坨坭坶坳垭垤垌垲埏垧垴垓垠埕埘埚埙埒垸埴埯埸埤埝蹳蹵蹷蹸蹹跷蹻蹽蹾躀躂躃躄躆躈趸踌跻躌跃躎踯跞踬蹰躖躗躘躙跹躛躝躟躠蹑躢躣躤蹿躜躧躨躩躏躭躮躰躱躳躴躵躶躷躸躹躻躼躽躾躿躯軁軂軃軄軅軆軇軈軉车轧轨军軏軐軑轩軓轫軕軖軗軘軙軚轭軜軝軞软軠軡転軣軤堋堍埽埭堀堞堙塄堠塥塬墁墉墚墀馨鼙懿艹艽艿芏芊芨芄芎芑芗芙芫芸芾芰苈苊苣芘芷芮苋苌กขคฆงจฉชซธฌญฎฏฐฑฒณดตถทธนบปผพฝฟภมยรฤฦลวศษสหฬอฮอะอัอาอิอีอึอือุอูเอะเอแอะแอเอาะเอาเอิเอีะเอืะเอืโอไอใออำสบายดีไหมยินดีที่พบกันใหม่ئۇيغۇرتىلى【】（）；’，。、：”《》？";
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
						
						SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						TimeZone tz = TimeZone.getTimeZone("Asia/Chongqing");
						Calendar cal = Calendar.getInstance(tz);
						ft.setTimeZone(tz);
						String regtime = ft.format(cal.getTime());

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

						//创建User实例
						User us = new User();
						us.setUsername(username);
						us.setUserpass(pswhash);
						us.setEmail(email);
						us.setErrtime(0);
						us.setPrerogative(0);
						us.setRandomcode(RandomString);
						us.setRegistertime(regtime);
						bookbns.saveUser(us);
						session.put("verifycode", null);
						session.put("mailbox", null);
						setStr("注册成功");
						return SUCCESS;
					}
				}
			}
		}

	}
}