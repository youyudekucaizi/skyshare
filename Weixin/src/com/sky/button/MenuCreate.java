package com.sky.button;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.https.util.HttpsUtil;

public class MenuCreate
{
	private static Logger logger = LoggerFactory.getLogger(MenuCreate.class);

	public static void main(String[] args)
	{
		String appId = "wxfc2c731001b33da8";
		String appsecret = "000000000";
		AccessToken accessToken =HttpsUtil.getAccessToken(appId, appsecret);

		if (null != accessToken)
		{
			int result = HttpsUtil.createMenu(getMenu(),
					accessToken.getToken());
			if (0 == result)
			{
				logger.info("菜单创建成功");
			} else
			{
				logger.info("菜单创建失败");

			}
		}
	}
	
	private static Menu getMenu()
	{
		CommonButton commonButton11=new CommonButton();
		commonButton11.setName("订餐");
		commonButton11.setType("click");
		commonButton11.setKey("11");
		
		CommonButton commonButton12=new CommonButton();
		commonButton12.setName("取消订餐");
		commonButton12.setType("click");
		commonButton12.setKey("12");
		
		CommonButton commonButton13=new CommonButton();
		commonButton13.setName("待补充 敬请期待");
		commonButton13.setType("click");
		commonButton13.setKey("13");
		
		CommonButton commonButton21=new CommonButton();
		commonButton21.setName("二级菜单的第一个子菜单");
		commonButton21.setType("click");
		commonButton21.setKey("21");
		
		CommonButton commonButton22=new CommonButton();
		commonButton22.setName("二级菜单的第二个子菜单");
		commonButton22.setType("click");
		commonButton22.setKey("22");
		
		CommonButton commonButton23=new CommonButton();
		commonButton23.setName("二级菜单的第三个子菜单");
		commonButton23.setType("click");
		commonButton23.setKey("23");
		
		
		CommonButton commonButton31=new CommonButton();
		commonButton31.setName("三级菜单的第一个子菜单");
		commonButton31.setType("click");
		commonButton31.setKey("31");
		
		CommonButton commonButton32=new CommonButton();
		commonButton32.setName("三级菜单的第二个子菜单");
		commonButton32.setType("click");
		commonButton32.setKey("32");
		
		CommonButton commonButton33=new CommonButton();
		commonButton33.setName("三级菜单的第三个子菜单");
		commonButton33.setType("click");
		commonButton33.setKey("33");
		
		ParentButton mainButton1=new ParentButton();
		mainButton1.setName("订餐系统");
		mainButton1.setSub_Button(new CommonButton[]{commonButton11,commonButton12,commonButton13});
		
		ParentButton mainButton2=new ParentButton();
		mainButton2.setName("二级主菜单的名字");
		mainButton2.setSub_Button(new CommonButton[]{commonButton21,commonButton22,commonButton23});
		
		ParentButton mainButton3=new ParentButton();
		mainButton3.setName("三级主菜单的名字");
		mainButton3.setSub_Button(new CommonButton[]{commonButton31,commonButton32,commonButton33});
		
		Menu menu=new Menu();
		menu.setButton(new Button[]{mainButton1,mainButton2,mainButton3});
		return menu;
		
		/*
		 * log4j.rootLogger=info,console,file
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%-5p] %m%n
log4j.appender.file=org.apache.log4j.DailyRollingFileAppender
log4j.appender.file.DatePattern='-'yyyy-MM-dd
log4j.appender.file.File=./logs/weixinmpmenu.log
log4j.appender.file.Append=true
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%-5p] %d %37c %3x - %m%n
		 */
	}

}
