package com.sky;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sky.response.ImageMessage;
import com.sky.response.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil
{
	public MessageUtil()
	{
		
	}
	
	public static final String REQUEST_MESSAGE_TYPE_TEXT = "text";
	public static final String REQUEST_MESSAGE_TYPE_IMAGE="image";
	//接下来是要解析用户的点击事件0807 
	//注意：个人订阅号在开发者模式下不能创建自定义菜单
	public static final String REQUEST_MESSAGE_TYPE_EVENT="event";
	public static final String EVENT_TYPE_CLICK="CLICK";
	
	// 解析微信服务器发过来的请求
	@SuppressWarnings("unchecked")
	public  Map<String, String> parseXml(HttpServletRequest request
			) throws IOException, DocumentException
	{
		Map<String, String> map = new HashMap<String, String>();

		InputStream inputStream = request.getInputStream();
		//读取输入流
		SAXReader reader = new SAXReader();

		Document docunemt = reader.read(inputStream);

		Element rootElement = docunemt.getRootElement();

		List<Element> elements = rootElement.elements();

		for (Element e : elements)
		{
			
			map.put(e.getName(), e.getText());
		}
		
		//下面的要删掉的@huangzhenhua
		//发送手机的账号
		String FromUserName=map.get("FromUserName");
		//开发者微信账号
		String ToUserName=map.get("ToUserName");
		String msgType=map.get("MsgType");
		System.out.println("FromUserName:"+ "\n"+FromUserName+"ToUserName:  "+"\n"+ToUserName+"\n"+"msgType:  "+msgType);
		

		
		
		
		
		
		inputStream.close();
		inputStream = null;
		
		

		return map;
	}

	// 文本消息装换成xml回复
	

	public static String textMessageToXml(TextMessage textMessage)
	{
		//XStream xStream=new XStream();

		xStream.alias("xml", textMessage.getClass());

		return xStream.toXML(textMessage);
	}
	
	//这边待修改@huangzhenhua20170807
	public static String  imageMessageToXml(ImageMessage imageMessage)
	{
		xStream.alias("xml", imageMessage.getClass());

		return xStream.toXML(imageMessage);
	}

	
	
	//匿名内部类 扩展Xstream功能@huangzhenhua
		public static XStream xStream = new XStream(new XppDriver()
		{
			public HierarchicalStreamWriter createWriter(Writer out)
			{
				return new PrettyPrintWriter(out)
				{
					// 对所有 xml 节点的转换都增加 CDATA 标记
					boolean cdata = true;

//					public void startNode(String name, Class clazz)
//					{
//						super.startNode(name, clazz);
//					}

					protected void writeText(QuickWriter writer, String text)
					{
						if (cdata)
						{
							writer.write("<![CDATA[");
							writer.write(text);
							//writer.write("]]>");
							writer.write("]]>");//视频里面的
						} else
						{
							//writer.write(text);
							writer.write(text);
						}
					}
				};
			}
		});
	
	
	

}
