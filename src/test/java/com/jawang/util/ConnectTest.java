package com.jawang.util;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class ConnectTest extends Exception {

	@Inject
	private SqlSession sqlsSession;
	
	
	@Test
	public void test() {
		assertNotNull(sqlsSession.getConnection());
		
	}

}
