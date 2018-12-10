package com.jawang.board.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jawang.board.BoardDTO;
import com.jawang.board.BoardService;
import com.jawang.util.Pager;

@Service
public class NoticeService implements BoardService{
	
	@Inject
	private NoticeDAO noticeDAO;
	
	public List<BoardDTO> list(Pager pager) throws Exception{
		pager.makeRow();
		int totalCount = noticeDAO.totalCount(pager);
		pager.makePage(totalCount);
		
		
		return noticeDAO.list(pager);
	}
	
	//select
	public BoardDTO select(int num) throws Exception{
		
		
		return noticeDAO.select(num);
	}
	
	//insert
	public int insert(BoardDTO boardDTO) throws Exception{
		
		
		
		return noticeDAO.insert(boardDTO);
	}
	
	//update
	public int update(BoardDTO boardDTO)throws Exception{
		
		
		return noticeDAO.update(boardDTO);
	}
	
	//delete
	public int delete(int num) throws Exception{
		
		
		return noticeDAO.delete(num);
	}

}
