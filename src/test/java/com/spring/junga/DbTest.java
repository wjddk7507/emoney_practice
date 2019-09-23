package com.spring.junga;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DbTest {
	@Inject
	private DataSource ds;
	@Test
	public void testConection()throws Exception{
		Connection con = null;
		try {
			con = ds.getConnection();
			System.out.println(con);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}	
	}
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession()){
			System.out.println(session);
		}catch(Exception e) {
			System.out.println("세션 생성 실패");
		}
	}
}