package com.puppyroad.app.payment.mapper;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.payment.service.PaymentVO;
import com.puppyroad.app.payment.service.VbankVO;

public interface PaymentMapper {
	//결제 사항 조회
	public MatchVO selectMatchInfo(MatchVO matchVO);
	//첫 결제 등록
	public int insertPayInfo(PaymentVO paymentVO);
	//가상계좌 추가 등록
	public int insertAddVbankInfo(VbankVO vbankVO);
}
