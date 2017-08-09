package com.sky.database;

public class User
{
	
	//private Integer id;//192.168.3.195
	private Integer id;
	private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	private String Content;
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
	public String getContent()
	{
		return Content;
	}
	public void setContent(String content)
	{
		Content = content;
	}
	
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public User()
	{
		
	}
	@Override
	public String toString()
	{
		return "User [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + ", Content=" + Content + "]";
	}
	/*
	 * private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	private String Content;
	 */

	public User(String tousername,String fromusername,long createtime,String msgtype,String content )
	{
		//this.id=id;
		this.FromUserName=fromusername;
		this.ToUserName=tousername;
		this.CreateTime=createtime;
		this.MsgType=msgtype;
		this.Content=content;
	}
	
	
	

}
