package com.sky.database.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class AutoSqlSessionFactory
{
	private static SqlSessionFactory sqlSessionFactory=null;
	static
	{
		try
		{
			InputStream inputStream=Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
			//sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
			//SqlSession session=sqlSessionFactory.openSession();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static SqlSession getSqlSession()
	{
		return sqlSessionFactory.openSession();
	}
	public static SqlSessionFactory getSqlSssionFactory()
	{
		return sqlSessionFactory;
	}

}
