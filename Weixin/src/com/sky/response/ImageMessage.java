package com.sky.response;

public class ImageMessage extends BaseMessage
{
	private String PicUrl;
	private String  MediaId;
	


	public String getMediaId()
	{
		return MediaId;
	}

	public void setMediaId(String mediaId)
	{
		MediaId = mediaId;
	}

	public String getPicUrl()
	{
		return PicUrl;
	}

	public void setPicUrl(String picUrl)
	{
		PicUrl = picUrl;
	}
	

}
