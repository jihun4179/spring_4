package com.jawang.util;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jawang.s4.AbstractTestCase;

public class PagerTest extends AbstractTestCase {

	private static Pager pager;
	
	@BeforeClass
	public static void setData() {
		pager = new Pager();
	}
	
	@Test
	public void test() {
		
		//curPage X		1
		//assertEquals(1, pager.getCurPage());
		
		//perBlock X	5
		//assertEquals(5, pager.getPerBlock());
		
		//perPage X		10
		//assertEquals(10, pager.getPerPage());
		
		//kind X		title
		//assertEquals("title", pager.getKind());
		
		//search X		""
		//assertEquals("", pager.getSearch());
		
		pager.makeRow();
		assertEquals(10, pager.getLastRow());
		
		
		
		
	}

}
