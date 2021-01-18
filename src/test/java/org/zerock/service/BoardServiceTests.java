package org.zerock.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Setter(onMethod_ = @Autowired)
	private BoardSerivce service;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(service);	
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		int before = mapper.getList().size();
		
		service.register(board);
		
		int after = mapper.getList().size();
		
		assertEquals(before+1, after);
	}
	
	@Test
	public void testGetList() {
		List<BoardVO> list = service.getList();
		assertNotNull(list);
		assertNotEquals(list.size(), 0);
		
	}
	
	@Test
	public void testGet() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		service.register(board);
		
		BoardVO vo = service.get(board.getBno());
		
		assertNotNull(vo);
		assertEquals(vo.getBno(), board.getBno());
	}
	
	@Test
	public void testRemove() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		service.register(board);
		
		assertTrue(service.remove(board.getBno()));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		service.register(board);
		
		board.setTitle("변경하는 글");
		board.setContent("변경하는 내용");
		
		assertTrue(service.modify(board));
		
		BoardVO up = service.get(board.getBno());
		
		assertEquals("변경하는 글", up.getTitle());
		assertEquals("변경하는 내용", up.getContent());
		
	}
}
