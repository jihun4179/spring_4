package com.jawang.s4;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jawang.board.BoardDTO;
import com.jawang.board.notice.NoticeService;
import com.jawang.util.FileSarver;
import com.jawang.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Inject
	private NoticeService noticeService;
	
	@RequestMapping(value="noticeList")
	public String list(Model model, Pager pager) throws Exception{
		//밑 코드들을 매개변수 (Pager pager)를 받음으로서 생략가능
		//Pager pager = new Pager();
		/*pager.setCurPage(curPage);
		pager.setKind(kind);
		pager.setSearch(search);
		System.out.println(pager.getCurPage());
		System.out.println(pager.getKind());
		System.out.println(pager.getSearch());*/
		
		
		List<BoardDTO> ar = noticeService.list(pager);
		model.addAttribute("board", "notice");
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		return "board/boardList";
	}
	@RequestMapping(value="noticeSelect")
	public String select(Model model, int num, RedirectAttributes rd) throws Exception{
		String path = noticeService.select(num , model, rd);
		
		/*model.addAttribute("board", "notice");
		model.addAttribute("dto",boardDTO);*/
		return path;
	}
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String write(BoardDTO boardDTO, HttpSession session , MultipartFile [] f1 ,RedirectAttributes rd) throws Exception{
		String realPath = session.getServletContext().getRealPath("resources/upload");
		//System.out.println(realPath);
		//System.out.println(f1);
		//System.out.println("write : " + boardDTO.getTitle());
		int result = noticeService.insert(boardDTO);
	
		return "redirect:./noticeList";
		
	}
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public String update(Model model) {
		model.addAttribute("board", "notice");
		return "board/boardUpdate";
	}
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public void update(BoardDTO boardDTO) {
		System.out.println("update : " + boardDTO.getTitle());
	}
	@RequestMapping(value="noticeDelete", method=RequestMethod.POST)
	public void delete(int num) {
		System.out.println("delete : " + num);
	}
	
	
	

}
