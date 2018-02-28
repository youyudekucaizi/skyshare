package com.sky.information;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.button.AccessToken;
import com.sky.button.Button;
import com.sky.button.CommonButton;
import com.sky.button.Menu;
import com.sky.button.ParentButton;
import com.sky.https.util.HttpsUtil;

public class UserInformation
{
	private static Logger logger = LoggerFactory.getLogger(UserInformation.class);

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
			JSONObject resultJsonObject=HttpsUtil.userInformation(getUser(), accessToken.getToken());
			
			
			System.out.println(resultJsonObject);
			
			
			String userOpenId=(String)resultJsonObject.get("openid");
			String nickName=(String)resultJsonObject.get("nickname");
			String province=(String)resultJsonObject.get("province");
			System.out.println(userOpenId);
			System.out.println(nickName);
			System.out.println(province);

//			// 判断菜单创建结果
//			if (0 == result)
//				logger.info("菜单创建成功！");
//			else
//				logger.info("菜单创建失败，错误码：" + result);
		}
	}
	private static UserList getUser()
	{
		User user1=new User();
		user1.setOpenid("oaQNcwphPA9_qLYuszahHSM6GB3Q");
		user1.setLang("zh_CN");
		
		User user2=new User();
		user2.setOpenid("oaQNcwvtrLnVbz6SxnKwUlG37wGk");
		user2.setLang("zh_CN");
		
		
		UserList userList=new UserList();
		userList.setUser_list(new User[]{user1,user2});
		
		return userList;
		
	}

	
}
