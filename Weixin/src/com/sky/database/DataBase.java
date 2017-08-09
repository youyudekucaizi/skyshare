package com.sky.database;

import org.apache.ibatis.session.SqlSession;

import com.sky.database.util.AutoSqlSessionFactory;

public class DataBase
{

	public static synchronized void insert(String FromUserName,String ToUserName,String msgType,String usercontent,long createtime)
	{
		//考虑高并发 ，假如许多人一起订餐会不会使得主键id值混乱
		//Integer id=1;mysql自动主键增长策略是在哪个字段 如果没有显示的指定
		
			SqlSession session=null;
			session=AutoSqlSessionFactory.getSqlSession();
			
			User user=new User(ToUserName, FromUserName, createtime, msgType, usercontent);
			session.insert("com.sky.mapper.UserMapper.save",user);
			session.commit();
			session.close();
	}
	//这是图片信息要插入到数据库的
	public static synchronized void insertimage(String FromUserName,String ToUserName,long CreateTime,String MsgType,String PicUrl,String MediaId)
	{
		SqlSession session=null;
		session=AutoSqlSessionFactory.getSqlSession();
		
		//UserImage userImage=new UserImage(ToUserName,FromUserName,CreateTime,MsgType,PicUrl,MediaId);
		
		UserImage userImage=new UserImage(ToUserName,FromUserName,CreateTime,MsgType,PicUrl,MediaId);
		session.insert("com.sky.mapper.UserImageMapper.save",userImage);
		session.commit();
		session.close();
		
	}
	
	//这些按钮响应事件要存入数据库
	public static  synchronized void insertbuttonevent(String FromUserName,String ToUserName,long CreateTime,String MsgType,String Event,String EventKey)
	{
		SqlSession session=null;
		session=AutoSqlSessionFactory.getSqlSession();
		UserButton userButton=new UserButton(ToUserName,FromUserName, CreateTime, MsgType, Event, EventKey);
		session.insert("com.sky.mapper.UserBUttonMapper.save",userButton);
		session.commit();
		session.close();
		
	}
	//这些按钮响应事件要从数据库中删除
	public static synchronized void deletebuttonevent(String FromUserName)
	{
		SqlSession session=null;
		session=AutoSqlSessionFactory.getSqlSession();
		UserButtonDelete userButtonDelete=new UserButtonDelete(FromUserName);
		session.delete("com.sky.mapper.UserBUttonMapper.delete",userButtonDelete);
		session.commit();
		session.close();
	}

}
