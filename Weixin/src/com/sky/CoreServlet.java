package com.sky;

import java.io.IOException;

import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class CoreServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用于确认请求信息来自微信服务端
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		PrintWriter outPrintWriter = response.getWriter();
		
		//下面要删掉
//		PrintWriter outPrintWriter = response.getWriter();
//		outPrintWriter.print("suceess");
		

		if (SignUtil.checkSignature(signature, timestamp, nonce))
		{
			outPrintWriter.print(echostr);
		}
		System.out.println("接入成功");
		outPrintWriter.close();
		outPrintWriter = null;

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		
		//调用业务来接收消息，处理消息,返回的是xml字符串
		//CoreService coreService=new CoreService();
		String respMessageString=CoreService.processRequest(request);
		
		PrintWriter out=null;
		try
		{
			out=response.getWriter();
			out.print(respMessageString);//返回给微信客户端信息
			
		} catch (IOException e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		finally
		{
			out.close();
		}
 
		
		
		System.out.println("整个流程处理成功");
		System.out.println(respMessageString);

	}

	

}
