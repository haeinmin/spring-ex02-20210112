package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	@Setter(onMethod_= @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_= @Autowired)
	private BoardMapper boardMapper;
	
	@Override
	@Transactional
	public int register(ReplyVO vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}
	
	@Override
	public ReplyVO get(Long rno) {
		log.info("get......" + rno);
		return mapper.read(rno);
	}
	
	@Override
	public int modify(ReplyVO vo) {
		log.info("modify....."+vo);
		return mapper.update(vo);
	}
	
	@Override
	@Transactional
	public int remove(Long rno) {
		ReplyVO vo = mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("get Reply list of Board" + bno);
		return mapper.getListWithPaging(cri, bno);
	}
	
	
}

