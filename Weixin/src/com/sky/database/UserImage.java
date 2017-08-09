package com.sky.database;

public class UserImage
{
	private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	private String PicUrl;
	private String MediaId;
	
	
	public UserImage(String tousername,String fromusername,long createtime,String msgtype,String picurl,String mediaid)
	{
		this.ToUserName=tousername;
		this.FromUserName=fromusername;
		this.CreateTime=createtime;
		this.MsgType=msgtype;
		this.PicUrl=picurl;
		this.MediaId=mediaid;
	}
	
	
	
	public String getToUserName()
	{
		return ToUserName;
	}
	public void setToUserName(String toUserName)
	{
		ToUserName = toUserName;
	}
	public String getFromUserName()
	{
		return FromUserName;
	}
	public void setFromUserName(String fromUserName)
	{
		FromUserName = fromUserName;
	}
	public long getCreateTime()
	{
		return CreateTime;
	}
	public void setCreateTime(long createTime)
	{
		CreateTime = createTime;
	}
	public String getMsgType()
	{
		return MsgType;
	}
	public void setMsgType(String msgType)
	{
		MsgType = msgType;
	}
	public String getPicUrl()
	{
		return PicUrl;
	}
	public void setPicUrl(String picUrl)
	{
		PicUrl = picUrl;
	}



	public String getMediaId()
	{
		return MediaId;
	}



	public void setMediaId(String mediaId)
	{
		MediaId = mediaId;
	}



	
	
	

}
