package com.jawang.board.notice;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Inject
	public void test() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setTitle("test");
		noticeDTO.setWriter("test");
		noticeDTO.setContents("test");
		
		map.put("noticeDTO", new NoticeDAO());
		map.put("num", 0);
		noticeDAO.test(map);
		System.out.println(map.get("num"));
	}
	
}
