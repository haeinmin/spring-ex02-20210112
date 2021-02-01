package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.Sample1Mapper;
import org.zerock.mapper.Sample2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Log4j
public class SampleTxService {
	
	@Setter(onMethod_= @Autowired)
	private Sample1Mapper mapper1;
	@Setter(onMethod_= @Autowired)
	private Sample2Mapper mapper2;
	
	@Transactional
	public void addData(String value) {
		mapper1.insertCol1(value);
		mapper2.insertCol2(value);
	}
}
