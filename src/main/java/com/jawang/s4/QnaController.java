package com.jawang.s4;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jawang.board.BoardDTO;
import com.jawang.board.qna.QnaDTO;
import com.jawang.board.qna.QnaService;
import com.jawang.util.Pager;


@Controller
@RequestMapping(value="/qna/**")
public class QnaController {
	
	@Inject
	private QnaService qnaService;
	
	//list
	@RequestMapping(value="qnaList")
	public String list(Model model, Pager pager) throws Exception{
		model.addAttribute("board","qna");
		/*Pager pager = new Pager();
		pager.setCurPage(curPage);*/
		List<BoardDTO> ar = qnaService.list(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		return "board/boardList";
	}
	//select
	@RequestMapping(value="qnaSelect")
	public String select(int num, Model model, RedirectAttributes rd)throws Exception {
		model.addAttribute("board", "qna");
		BoardDTO boardDTO = qnaService.select(num);
		String path = "";
		if(boardDTO != null) {
			path = "board/boardSelect";
			model.addAttribute("dto", boardDTO);
		}else {
			path = "redirect:./qnaList";
			rd.addFlashAttribute("msg", "해당글이 존재 하지 않습니다");
		}
		
		return path;
	}
	//write
	@RequestMapping(value="qnaWrite", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("board", "qna");
		return "board/boardWrite";
	}
	@RequestMapping(value="qnaWrite", method=RequestMethod.POST)
	public void write(BoardDTO boardDTO) {
		System.out.println("write : " + boardDTO.getTitle());
	}
	//update
	@RequestMapping(value="qnaUpdate", method=RequestMethod.GET)
	public String update(Model model, int num) throws Exception{
		model.addAttribute("board", "qna");
		BoardDTO boardDTO = qnaService.select(num);
		model.addAttribute("dto", boardDTO);
		return "board/boardUpdate";
	}
	@RequestMapping(value="qnaUpdate", method= RequestMethod.POST)
	public String update(BoardDTO boardDTO, RedirectAttributes rd) throws Exception{
		int result = 0;
		return "";
	}
	//delete
	@RequestMapping(value="qnaDelete",  method= RequestMethod.POST)
	public void delete(int num) {
		System.out.println("delete : " + num);
	}
	//reply
	@RequestMapping(value="qnaReply",  method= RequestMethod.GET)
	public String reply(Model model, int num) {
		model.addAttribute("board", "qna");
		model.addAttribute("num", num);
		return "board/boardReply";
	}
	@RequestMapping(value="qnaReply", method= RequestMethod.POST)
	public String reply(QnaDTO qnaDTO) throws Exception{
		int result = qnaService.reply(qnaDTO);
		//System.out.println("reply : " + boardDTO.getTitle());
		return "redirect:./qnaSelect?num="+qnaDTO.getNum();
	}
}
