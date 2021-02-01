package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.DataSourceTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr = {205L, 208L, 209L, 210L, 212L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testDelete() {
		Long rno = 1L;
		mapper.delete(rno);
	}
	
	@Test
	public void testUpdate() {
		ReplyVO vo = new ReplyVO();
		vo.setRno(2L);
		vo.setReply("수정된 댓글");
		mapper.update(vo);
		
		vo = mapper.read(2L);
		assertEquals("수정된 댓글", vo.getReply());
	}
	
	@Test
	public void testRead() {
		Long rno = 11L;
		ReplyVO vo = mapper.read(rno);
		assertEquals("댓글 테스트1", vo.getReply());
	}
	
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			log.info(i+","+(i%5));
			
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testCreate2() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(212L); //tbl_board에 있는 값 삽입 
		vo.setReply("댓글 테스트");
		vo.setReplyer("user00");
		mapper.insert(vo);
		
		try {
			vo.setBno(242L);
			mapper.insert(vo);
			fail();
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testList() {
		List<ReplyVO> list = mapper.getListWithPaging(null, 205L);
		assertNotEquals(0, list.size());
	}
}