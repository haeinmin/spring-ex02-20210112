package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardSerivce {
	public void register(BoardVO board);
	
//	public List<BoardVO> getList();
	
	public BoardVO get(Long bno);
	
	public boolean remove(Long bno);
	
	public boolean modify(BoardVO board);
	
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);

}
