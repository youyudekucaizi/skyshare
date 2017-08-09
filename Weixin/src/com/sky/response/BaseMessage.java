package com.sky.response;

public class BaseMessage
{
	private String ToUserName;
	
	private String FromUserName;
	
	private long CreateTime;
	
	private String MsgType;
	
	private int FuncFlage;
	/*
	 * 接下来补充的都是响应按钮事件20170809
	 */
	private String Event;
	private String EventKey;
	
	

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

	public int getFuncFlage()
	{
		return FuncFlage;
	}

	public void setFuncFlage(int funcFlage)
	{
		FuncFlage = funcFlage;
	}
	
	

}
