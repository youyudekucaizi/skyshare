package com.sky;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil
{
	
	private static String token="weixin";
	
	public static boolean checkSignature(String signature,String timestamp,String nonce )
	{
		String [] arrStrings=new String[]{token,timestamp,nonce};
		Arrays.sort(arrStrings);
		StringBuilder content=new StringBuilder();
		for(int i=0;i<arrStrings.length;i++)
		{
			content.append(arrStrings[i]);
		}
		MessageDigest mdDigest=null;
		String tmpString=null;
		
		try
		{
			mdDigest=MessageDigest.getInstance("SHA-1");
			byte[] digest=mdDigest.digest(content.toString().getBytes());
			tmpString=byteToStr(digest);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		content=null;
		return tmpString!=null?tmpString.equals(signature.toUpperCase()):false;
			
	}
	
	//将字节数组转换为十六进制字符串
	private static String byteToStr(byte[]byteArr)
	{
		String string="";
		for(int i=0;i<byteArr.length;i++)
		{
			string+=byteToHexStr(byteArr[i]);
			
			
		}
		return string;
	}
	
	private static String byteToHexStr(byte mByte)
	{
		char []Digit={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[]tempArr=new char[2];
		tempArr[0]=Digit[(mByte>>>4)&0X0F];
		tempArr[1]=Digit[mByte&0X0F];
		
		String string=new String(tempArr);
		return string;
	}
	
	
	

}
