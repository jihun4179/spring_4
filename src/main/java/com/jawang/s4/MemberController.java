package com.jawang.s4;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jawang.member.MemberDTO;
import com.jawang.member.MemberService;

@Controller
@RequestMapping(value="/member/**")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	//회원가입
	@RequestMapping(value="join", method = RequestMethod.GET)
	public void join() throws Exception{
		
	}
	@RequestMapping(value="join", method = RequestMethod.POST)
	public String join(MemberDTO memberDTO,RedirectAttributes rd) throws Exception{
		memberDTO.setGrade(9);
		int result = memberService.join(memberDTO);
		String path = "redirect:../";
		if(result<1) {
			path="redirect:./join";
			rd.addFlashAttribute("msg", "회원가입 실패");
		}
		return path;
	}
	
	//중복확인
	@RequestMapping(value="idCheck")
	public String idCheck(String id,Model model) throws Exception{
		MemberDTO memberDTO = memberService.idCheck(id);
		int result = 0;
		// 1이면 사용 가능 , 0이면 사용 불가능
		if(memberDTO == null) {
			result = 1;
		}
		
		model.addAttribute("result",result );
		return "common/result";
	}
	
	//로그인
	@RequestMapping(value="login", method = RequestMethod.GET)
	public void login() throws Exception{
		
	}
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(MemberDTO memberDTO, HttpSession session ,RedirectAttributes rd) throws Exception{
		memberDTO = memberService.login(memberDTO);
		String path = "";
		if(memberDTO != null) {
			session.setAttribute("member", memberDTO);
			path = "redirect:../";
		}else {
			path = "redirect:./login";
			rd.addFlashAttribute("msg", "로그인 실패");
		}
		return path;
	}
	
	//마이페이지
	@RequestMapping(value="myPage")
	public void myPage() throws Exception{
		
	}
	
	//수정
	@RequestMapping(value="update")
	public void update() throws Exception{
		
	}
	@RequestMapping(value="update", method = RequestMethod.POST)
	public int update(MemberDTO memberDTO) throws Exception{
		
		return 0;
	}
	
	//탈퇴
	@RequestMapping(value="delete")
	public int delete(String id) throws Exception{
		
		return 0;
	}
	
	
	
	
	
	
	
}
