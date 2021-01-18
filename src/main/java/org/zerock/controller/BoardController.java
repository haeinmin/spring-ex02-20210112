package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
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
	@GetMapping("/list") 
	// if return type of handler method is void, request path becomes the name of view(jsp)
	// ex) this method runs on (/board/list) -> /board/list.jsp
	
	public void list(Model model) {
		log.info("*********list**********");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/register")
	public void register() {
		
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
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public void get(Long bno, Model model) {     //parameter에 model 명시하면 dispatcherservlet에서부터 사용 가능 
		log.info("get method - bno: " + bno);
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) { //@RequestParam은 생략 가능 
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
}
