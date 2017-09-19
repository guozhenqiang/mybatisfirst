package cn.itcast.mybatis.mapper;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import cn.itcast.mybatis.po.User;

public class OrdersMapperCustomTest {

	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindOrdersUser() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		//调用userMapper的方法
		
		List<OrdersCustom> list = ordersMapperCustom.findOrdersUser();
		
		sqlSession.close();
		
		System.out.println(list);
	}
	
	@Test
	public void testFindOrdersUserResultMap() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		//调用userMapper的方法
		
		List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();
		
		sqlSession.close();
		
		System.out.println(list);
	}
	
	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		//调用userMapper的方法
		
		List<Orders> list = ordersMapperCustom.findOrdersAndOrderDetailResultMap();
		
		sqlSession.close();
		
		System.out.println(list);
	}
	
	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		
		//调用userMapper的方法
		
		List<User> list = ordersMapperCustom.findUserAndItemsResultMap();
		
		sqlSession.close();
		
		System.out.println(list);
	}
	
	// 查询订单关联查询用户，用户信息使用延迟加载
	@Test
	public void testFindOrdersUserLazyLoading() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();// 创建代理对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		// 查询订单信息（单表）
		List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading();

		// 遍历上边的订单列表
		for (Orders orders : list) {
			// 执行getUser()去查询用户信息，这里实现按需加载
			User user = orders.getUser();
			System.out.println(user);
		}

	}
	

}
