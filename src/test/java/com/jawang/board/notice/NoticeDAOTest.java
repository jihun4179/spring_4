package com.jawang.board.notice;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jawang.board.BoardDTO;
import com.jawang.s4.AbstractTestCare;

public class NoticeDAOTest extends AbstractTestCare{

	
	@Inject
	private NoticeDAO noticeDAO;
	
	@AfterClass
	public static void finish() {
		System.out.println("Finish Test");
	}
	
	@BeforeClass
	public static void start() {
		System.out.println("Start Test");
	}
	
	@Before
	public void before() {
		System.out.println("Before Test");
	}
	
	@After
	public void after() {
		System.out.println("After Test");
	}
	
	@Test
	public void deleteTest() {
		System.out.println("Delete Test");
	}
	
	@Test
	public void updateTest() {
		System.out.println("Update Test");
	}
	
	@Test
	public void insertTest() {
		System.out.println("Insert Test");
	}
	
	@Test
	public void test() {
		System.out.println("List Test");
	}

}
