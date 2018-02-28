package com.sky.button;

public class AccessToken
{
	//首先是获取凭证接口，微信服务器会返回结果
	private String token;
	private int expiresIn;
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public int getExpiresIn()
	{
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn)
	{
		this.expiresIn = expiresIn;
	}
	

}
