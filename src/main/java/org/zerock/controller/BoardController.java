package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardSerivce;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {
	
	private BoardSerivce service;

	//211p table
	// @RequestMapping(value="/list", method = RequestMethod.GET) 아래와 동일 
/*	@GetMapping("/list") 
	// if return type of handler method is void, request path becomes the name of view(jsp)
	// ex) this method runs on (/board/list) -> /board/list.jsp
	
	public void list(Model model) {
		log.info("*********list**********");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);
		
	}
*/
	@GetMapping("/list")
	public void list(@ModelAttribute("cri") Criteria cri, Model model) { // 수정, 삭제 후 목록 눌렀을 때 cri라는 attr이 없어서 오류가 나므로 model attribute 이름이 cri로 넘어가도록 명시해줘야 함 
		List<BoardVO> list = service.getList(cri);
		int total = service.getTotal(cri);
		PageDTO dto = new PageDTO(cri, total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", dto);
		
	}
	
	@GetMapping("/register")
	public void register(@ModelAttribute("cri") Criteria cri) {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		/*
		BoardVO board = new BoardVO();
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));
		이 네 줄의 일을 위에 파라미터에 board를 넣음으로써 해결 가능
		*/
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		rttr.addFlashAttribute("message", board.getBno() + "번 글이 등록되었습니다.");
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})     		 // if multiple, put it in an array
	public void get(Long bno, @ModelAttribute("cri") Criteria cri, Model model) {     //parameter에 model 명시하면 dispatcherservlet에서부터 사용 가능
												//when return type is void, it is forwarded to the request mapping path
		log.info("get method - bno: " + bno);
		log.info(cri);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
//		model.addAttribute("cri", cri);
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) { //@RequestParam은 생략 가능 
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", bno + "번 글이 삭제되었습니다.");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());  //if redirected, add attr on RedirectAttributes
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board,Criteria cri, RedirectAttributes rttr) {
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", board.getBno() + "번 글이 수정되었습니다.");
		}
		log.info(cri);
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}
	
	
	
}
