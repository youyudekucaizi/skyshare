package com.sky.database;

public class UserButton
{
	
	private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	private String Event;
	private String EventKey;
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
	public String getEvent()
	{
		return Event;
	}
	public void setEvent(String event)
	{
		Event = event;
	}
	public String getEventKey()
	{
		return EventKey;
	}
	public void setEventKey(String eventKey)
	{
		EventKey = eventKey;
	}
	public UserButton(String toUserName, String fromUserName, long createTime,
			String msgType, String event, String eventKey)
	{
		//super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Event = event;
		EventKey = eventKey;
	}
	public UserButton()
	{
		
	}
	
	

}
