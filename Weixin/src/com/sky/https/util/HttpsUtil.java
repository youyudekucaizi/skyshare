package com.sky.https.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.button.AccessToken;
import com.sky.button.Menu;
import com.sky.trustmanager.util.MyTrustManager;

public class HttpsUtil
{
	private static Logger logger = LoggerFactory.getLogger(HttpsUtil.class);

	/**
	 * 30. * 发起 https 请求并获取结果 31. * 32. * @param requestUrl 请求地址 33. * @param
	 * requestMethod 请求方式（GET、POST） 34. * @param outputStr 提交的数据 35. * @return
	 * JSONObject(通过 JSONObject.get(key)的方式获取 json 对象的属性值) 36.
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputString)
	{
		JSONObject jsonObject = null;
		StringBuffer stringBuffer = new StringBuffer();
		try
		{
			TrustManager[] tManagers =
			{ new MyTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tManagers, new java.security.SecureRandom());

			SSLSocketFactory ssfFactory = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url
					.openConnection();
			httpsURLConnection.setSSLSocketFactory(ssfFactory);

			httpsURLConnection.setDoOutput(true);
			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setUseCaches(false);
			// p102页@huangzhenhua20170807end

			httpsURLConnection.setRequestMethod(requestMethod);

			if ("GET".equals(requestMethod))
				httpsURLConnection.connect();

			if (null != outputString)
			{
				OutputStream outputStream = httpsURLConnection
						.getOutputStream();
				outputStream.write(outputString.getBytes("UTF-8"));
				outputStream.close();
			}

			InputStream inputStream = httpsURLConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String string = null;
			while ((string = bufferedReader.readLine()) != null)
			{
				stringBuffer.append(string);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpsURLConnection.disconnect();
			jsonObject = JSONObject.fromObject(stringBuffer.toString());

		} catch (ConnectException e)
		{
			logger.error("weixin server connection timed out");
			// TODO: handle exception
		} catch (Exception e)
		{
			logger.error("https request error:{}", e);
		}
		return jsonObject;
	}

	public final static String access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public static AccessToken getAccessToken(String appid, String appsecret)
	{
		AccessToken accessToken = null;
		String requestUrl = access_token.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);// 接受无参数的
		if (null != jsonObject)
		{
			try
			{
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e)
			{
				accessToken = null;
				logger.error("获取token失败", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				// TODO: handle exception
			}
		}
		return accessToken;
	}

	// 创建自定义菜单
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// return 0 表示成功，其他值表示失败
	public static int createMenu(Menu menu, String accesstoken)
	{

		int result = 0;
		// 获取创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accesstoken);
		String jsonMenu = JSONObject.fromObject(menu).toString();

		// 创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject)
		{
			if (0 != jsonObject.getInt("errcode"))
			{
				result = jsonObject.getInt("errcode");
				logger.error("创建菜单失败", jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));

			}

		}
		return result;
	}

}
