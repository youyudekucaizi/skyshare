package com.sky;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sky.database.DataBase;
import com.sky.message.request.ImageMessage;
import com.sky.response.TextMessage;

public class CoreService
{
	
	//返回给微信服务器的是xml形式的字符串
	//返回给微信服务器
	public static String processRequest(HttpServletRequest request)
	{
		
		String respMessageString=null;
		try
		{
			String respContentString="请求错误，请重试";
			//MessageUtil
			MessageUtil messageUtil=new MessageUtil();
			Map<String ,String> requestMap=messageUtil.parseXml(request);
			//发送手机的账号
			String FromUserName=requestMap.get("FromUserName");
			//开发者微信账号
			String ToUserName=requestMap.get("ToUserName");
			
			//得到发送的类型 如果是文本可以继续操作得到的是发送方的请求类型
			String MsgType=requestMap.get("MsgType");
			/*
			 * //这个是回复给微信服务器的内容
				<xml>
  					<Content><![CDATA[收到，谢谢]]></Content>
  					<ToUserName><![CDATA[gh_cabc75d5e407]]></ToUserName>
  					<FromUserName><![CDATA[og6qlwaHbNxoCFO4pfC4r-GMaMbI]]></FromUserName>
  					<CreateTime><![CDATA[1501756662554]]></CreateTime>
  					<MsgType><![CDATA[text]]></MsgType>
  					<FuncFlage><![CDATA[0]]></FuncFlage>
				</xml>
			 */
			if(MessageUtil.REQUEST_MESSAGE_TYPE_TEXT.equals(MsgType))
			{
				System.out.println("发送的是文本消息");
				String content=requestMap.get("Content");

				if("订餐".equals(content))
				{
					System.out.println("-----------------------------");
					System.out.println(MsgType);
					System.out.println("-----------------------------");
					System.out.println("连接成功在Coreservice里面关键字是订餐");
					respContentString="谢谢,收到";//收到订餐回复信息，用户可在手机上看见
				}
//				else if("a".equals(content))
//				{
//					System.out.println("连接成功在Coreservice里面关键字是字母a");
//					respContentString="success";
//					
//				
//				}
				
				//回复的文本消息
				TextMessage textMessage=new TextMessage();
				
				textMessage.setToUserName(ToUserName);
				textMessage.setFromUserName(FromUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(MsgType);
				textMessage.setFuncFlage(1);//@huangzhenhau20170803
				//这边可以扩展其他的功能
				//例如收到图片信息在此扩展@huangzhenhua
				textMessage.setContent(respContentString);//收到文字消息回复
				respMessageString=MessageUtil.textMessageToXml(textMessage);
				//将获取的信息存入数据库20170804
				String usercontent=requestMap.get("Content");
				long createtime=Long.parseLong(requestMap.get("CreateTime"));
				DataBase.insert(FromUserName,ToUserName,MsgType,usercontent,createtime);
				
			}
			else if (MessageUtil.REQUEST_MESSAGE_TYPE_IMAGE.equals(MsgType)) 
			{
				System.out.println("发送的是图片消息");
				String PicUrl=requestMap.get("PicUrl");
				String mediaId=requestMap.get("MediaId");
				long CreateTime=Long.parseLong(requestMap.get("CreateTime"));
				//long MediaId=Long.parseLong(requestMap.get("MediaId"));
				com.sky.response.ImageMessage imageMessage=new com.sky.response.ImageMessage();
				
//				imageMessage.setToUserName(ToUserName);
//				imageMessage.setFromUserName(FromUserName);
//				imageMessage.setCreateTime(new Date().getTime());
//				imageMessage.setMsgType(MsgType);
				imageMessage.setToUserName(ToUserName);
				imageMessage.setFromUserName(FromUserName);
				imageMessage.setCreateTime(new Date().getTime());
				imageMessage.setPicUrl(PicUrl);
				imageMessage.setMediaId(mediaId);
				
//				System.out.println("-----------------------------");
//				System.out.println(MsgType);
//				System.out.println("-----------------------------");
				
				respMessageString=MessageUtil.imageMessageToXml(imageMessage);
				
				//将获取的图片信息放入数据库
				
				//20170807
				DataBase.insertimage(FromUserName, ToUserName, CreateTime, MsgType, PicUrl, mediaId);
			}
			//20170809这是补充的按钮响应文件
			else if (MsgType.equals(MessageUtil.REQUEST_MESSAGE_TYPE_EVENT)) 
			{
				String event=requestMap.get("Event");
				if(MessageUtil.EVENT_TYPE_CLICK.equals(event))
				{
					System.out.println("用户是点击按钮了");
					String eventkey=requestMap.get("EventKey");
					if("11".equals(eventkey))
					{
						//respContentString="您已经选择订餐";
						
						//这边消息没有完全补全Event和EventKey
						TextMessage textMessage=new TextMessage();
						textMessage.setToUserName(ToUserName);
						textMessage.setFromUserName(FromUserName);
						textMessage.setCreateTime(new Date().getTime());
						textMessage.setMsgType(MsgType);
						textMessage.setEvent(MessageUtil.EVENT_TYPE_CLICK);
						textMessage.setEventKey("11");
						long CreateTime=Long.parseLong(requestMap.get("CreateTime"));
						//String event=requestMap.get("Event");
						
						//textMessage.setContent(respContentString);//回复文字消息
						respMessageString=MessageUtil.textMessageToXml(textMessage);
						
						//将点击的事件存入数据库
						
						DataBase.insertbuttonevent(FromUserName, ToUserName, CreateTime, MsgType, "CLICK", "11");
					}
					else if("12".equals(eventkey))
					{
						//如果点击撤销订餐 那么就要从数据库中删除该记录
						//首先是返回给微信服务器的消息
						TextMessage textMessage=new TextMessage();
						textMessage.setToUserName(ToUserName);
						textMessage.setFromUserName(FromUserName);
						textMessage.setCreateTime(new Date().getTime());
						textMessage.setMsgType(MsgType);
						textMessage.setEvent(MessageUtil.EVENT_TYPE_CLICK);
						textMessage.setEventKey("12");
						long CreateTime=Long.parseLong(requestMap.get("CreateTime"));
						//String event=requestMap.get("Event");
						
						//textMessage.setContent(respContentString);//回复文字消息
						respMessageString=MessageUtil.textMessageToXml(textMessage);
						DataBase.deletebuttonevent(FromUserName);
						
					}
				}
			}
			
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return respMessageString;
		
	
		
	}

}
