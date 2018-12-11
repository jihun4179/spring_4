package com.jawang.board.notice;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jawang.board.BoardDTO;
import com.jawang.board.BoardService;
import com.jawang.file.FileDAO;
import com.jawang.file.FileDTO;
import com.jawang.util.FileSarver;
import com.jawang.util.Pager;

@Service
public class NoticeService implements BoardService{
	
	@Inject
	private NoticeDAO noticeDAO;
	
	@Inject
	private FileDAO fileDAO;
	
	public List<BoardDTO> list(Pager pager) throws Exception{
		pager.makeRow();
		int totalCount = noticeDAO.totalCount(pager);
		pager.makePage(totalCount);

		return noticeDAO.list(pager);
	}
	
	//select
	public BoardDTO select(int num) throws Exception{
		FileDTO fileDTO = new FileDTO();
		fileDTO.setNum(num);
		fileDTO.setKind("n");
		List<FileDTO> files = fileDAO.list(fileDTO);
		return noticeDAO.select(num);
	}

	public String select(int num, Model model, RedirectAttributes rd) throws Exception{
		FileDTO fileDTO = new FileDTO();
		fileDTO.setNum(num);
		fileDTO.setKind("n");
		List<FileDTO> files = fileDAO.list(fileDTO);
		BoardDTO boardDTO = noticeDAO.select(num);
		String path= "";
		if(boardDTO != null) {
		model.addAttribute("dto", boardDTO);
		model.addAttribute("files", files);
		path="board/boardSelect";
		}else {
			rd.addFlashAttribute("msg", "해당 글은 없습니다");
			path="./noticeList";
		}
		return path;
	}
	
	public int insert(BoardDTO boardDTO) {
		return 0;
	}
	
	
	//insert
	public int insert(BoardDTO boardDTO , MultipartFile [] f1 , HttpSession session) throws Exception{
		FileSarver fs = new FileSarver();
		String realPath = session.getServletContext().getRealPath("resources/notice");
		
		int num = noticeDAO.getNum();
		boardDTO.setNum(num);
		int result = noticeDAO.insert(boardDTO);
		
		if(result<1) {
			throw new Exception();
		}
		
		for(MultipartFile mFile: f1) {
			if(result<1) {
				throw new Exception();
				//continue;
			}
			FileDTO fileDTO = new FileDTO();
			fileDTO.setOname(mFile.getOriginalFilename());
			fileDTO.setFname(fs.saveFile3(realPath, mFile));
			fileDTO.setKind("n");
			fileDTO.setNum(num);
			result = fileDAO.insert(fileDTO);
		}
		
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
