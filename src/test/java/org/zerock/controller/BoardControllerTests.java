package org.zerock.controller;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class BoardControllerTests {
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Before // 다른 메소드 이전에 실행하도록 하는 어노테이션
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testExist() {
		assertNotNull(ctx);
		assertNotNull(mockMvc);
	}

	@Test
	public void testList() throws Exception {
		/*
		 * ResultActions result =
		 * mockMvc.perform(MockMvcRequestBuilders.get("/board/list")); MvcResult rs =
		 * result.andReturn(); ModelAndView mv = rs.getModelAndView();
		 * log.info(mv.getView()); log.info(mv.getModel().get("list"));
		 * 
		 * 아래와 같은 code
		 */
		Object o = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModel()
				.get("list");

		assertNotNull(o);
		assertTrue(o instanceof List);
		assertNotEquals(((List) o).size(), 0);
	}


	@Test
	public void testRegister() throws Exception {
		int before = mapper.getList().size();

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "test new title").param("content", "test new content").param("writer", "user00"))
				.andReturn();

		ModelAndView mv = result.getModelAndView();
		FlashMap map = result.getFlashMap();

		int after = mapper.getList().size();

		assertEquals(before + 1, after);
		assertEquals("redirect:/board/list", mv.getViewName());
		assertNotNull(map.get("result"));
	}

	@Test
	public void testGet() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "1")).andReturn();
		String viewName = result.getModelAndView().getViewName();
		Map<String, Object> modelMap = result.getModelAndView().getModel();

		assertEquals("board/get", viewName);
		assertNotNull(modelMap.get("board"));
		assertEquals(new Long(1), ((BoardVO) modelMap.get("board")).getBno());
	}

	@Test
	public void testModify() throws Exception {
		BoardVO board = new BoardVO();
		board.setContent("new content");
		board.setTitle("new title");
		board.setWriter("user00");

		mapper.insertSelectKey(board);

		Long key = board.getBno();

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", key + "")
				.param("title", "수정된 제목")
				.param("content", "수정된 내용")
				.param("writer", "user00"))
				.andReturn();

		FlashMap map = result.getFlashMap();
		String viewName = result.getModelAndView().getViewName();
		BoardVO mod = mapper.read(key);

		assertEquals("수정된 제목", mod.getTitle());
		assertEquals("수정된 내용", mod.getContent());
		assertEquals(map.get("result"), "success");
		assertEquals("redirect:/board/list", viewName);
	}

	@Test
	public void testRemove() throws Exception {
		BoardVO board = new BoardVO();
		board.setContent("new content");
		board.setTitle("new title");
		board.setWriter("user00");

		mapper.insertSelectKey(board);
		
		Long key = board.getBno();
		
		int before = mapper.getList().size();
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", key + ""))
				.andReturn();
		
		int after = mapper.getList().size();
		
		assertEquals(before-1, after);
		
		String viewName = result.getModelAndView().getViewName();
		assertEquals("redirect:/board/list", viewName);
		
		assertEquals("success", result.getFlashMap().get("result"));
	}
	
	@Test
	public void testListPaging() throws Exception {
		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "10"))
				.andReturn();
		Map<String, Object> model = res.getModelAndView().getModel();
		List list = (List) model.get("list");
		
		assertEquals(10, list.size());
	}

}
