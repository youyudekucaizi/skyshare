package com.sky.button;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.https.util.HttpsUtil;

public class MenuCreate
{
	private static Logger logger = LoggerFactory.getLogger(MenuCreate.class);

	public static void main(String[] args)
	{
		String appId = "wx26234ebbfc61d838";
		String appsecret = "2fac9581bd801c4736b28ff8c6071ff6";
		AccessToken accessToken = HttpsUtil.getAccessToken(appId, appsecret);

		// if (null != accessToken)
		// {
		// int result = HttpsUtil.createMenu(getMenu(),
		// accessToken.getToken());
		// if (0 == result)
		// {
		// logger.info("菜单创建成功");
		// } else
		// {
		// logger.info("菜单创建失败");
		//
		// }
		// }
		if (null != accessToken)
		{
			// 调用接口创建菜单
			int result = HttpsUtil.createMenu(getMenu(), accessToken.getToken());

			// 判断菜单创建结果
			if (0 == result)
				logger.info("菜单创建成功！");
			else
				logger.info("菜单创建失败，错误码：" + result);
		}
	}

	private static Menu getMenu()
	{
		ParentButton mainButton1 = new ParentButton();
		mainButton1.setName("订餐系统");
		

		
		
		
		CommonButton commonButton11 = new CommonButton();
		commonButton11.setName("订餐");
		commonButton11.setType("click");
		commonButton11.setKey("11");

		CommonButton commonButton12 = new CommonButton();
		commonButton12.setName("取消订餐");
		commonButton12.setType("click");
		commonButton12.setKey("12");

		CommonButton commonButton13 = new CommonButton();
		commonButton13.setName("敬请期待");
		commonButton13.setType("click");
		commonButton13.setKey("13");

//		CommonButton commonButton21 = new CommonButton();
//		commonButton21.setName("二一子菜单");
//		commonButton21.setType("click");
//		commonButton21.setKey("21");
//
//		CommonButton commonButton22 = new CommonButton();
//		commonButton22.setName("二二子菜单");
//		commonButton22.setType("click");
//		commonButton22.setKey("22");
//
//		CommonButton commonButton23 = new CommonButton();
//		commonButton23.setName("二三子菜单");
//		commonButton23.setType("click");
//		commonButton23.setKey("23");
//
//		CommonButton commonButton31 = new CommonButton();
//		commonButton31.setName("三一子菜单");
//		commonButton31.setType("click");
//		commonButton31.setKey("31");
//
//		CommonButton commonButton32 = new CommonButton();
//		commonButton32.setName("三二子菜单");
//		commonButton32.setType("click");
//		commonButton32.setKey("32");
//
//		CommonButton commonButton33 = new CommonButton();
//		commonButton33.setName("三三子菜单");
//		commonButton33.setType("click");
//		commonButton33.setKey("33");
		
		
		
		

		
//		ParentButton mainButton2 = new ParentButton();
//		mainButton2.setName("二主");
//		mainButton2.setSub_Button(new CommonButton[]
//		{ commonButton21, commonButton22, commonButton23 });
//
//		ParentButton mainButton3 = new ParentButton();
//		mainButton3.setName("三主");
//		mainButton3.setSub_Button(new CommonButton[]
//		{ commonButton31, commonButton32, commonButton33 });

		mainButton1.setSub_button(new CommonButton[]
				{ commonButton11, commonButton12, commonButton13 });
		//封装整个菜单
		Menu menu = new Menu();
		menu.setButton(new Button[]
				{ mainButton1});
//		menu.setButton(new Button[]
//		{ mainButton1, mainButton2, mainButton3 });
		return menu;

		/*
		 * log4j.rootLogger=info,console,file
		 * log4j.appender.console=org.apache.log4j.ConsoleAppender
		 * log4j.appender.console.layout=org.apache.log4j.PatternLayout
		 * log4j.appender.console.layout.ConversionPattern=[%-5p] %m%n
		 * log4j.appender.file=org.apache.log4j.DailyRollingFileAppender
		 * log4j.appender.file.DatePattern='-'yyyy-MM-dd
		 * log4j.appender.file.File=./logs/weixinmpmenu.log
		 * log4j.appender.file.Append=true
		 * log4j.appender.file.layout=org.apache.log4j.PatternLayout
		 * log4j.appender.file.layout.ConversionPattern=[%-5p] %d %37c %3x -
		 * %m%n
		 */
	}

}
