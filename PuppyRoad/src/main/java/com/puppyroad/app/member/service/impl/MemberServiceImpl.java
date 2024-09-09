package com.puppyroad.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.puppyroad.app.member.mapper.MemberMapper;
import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper memberMapper;
	@Value("${coolsms.api.key}")
	private String apiKey;
	@Value("${coolsms.api.secret}")
	private String apiSecret;
	

	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public String addMember(MemberVO memberVO) {

		int result = memberMapper.insertMember(memberVO);

		return result == 1 ? memberVO.getMemberCode() : "fail";
	}

	@Override
	public int idCheck(String userId) {

		return memberMapper.idCheck(userId);
	}

	@Override
	public void certifiedPhoneNumber(String phoneNumber, String cerNum) {
		

		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey,
				apiSecret, "https://api.coolsms.co.kr");

		Message message = new Message();
		message.setFrom("01033513743");
		message.setTo(phoneNumber);
		message.setText("[PUPPYROAD] 본인확인 인증번호 : [" + cerNum + "]입니다.");

		try {
			// send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
			messageService.send(message);
		} catch (NurigoMessageNotReceivedException exception) {
			// 발송에 실패한 메시지 목록을 확인할 수 있습니다!
			System.out.println(exception.getFailedMessageList());
			System.out.println(exception.getMessage());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}

	@Override
	public List<MemberVO> findId(String phoneNumber) {
		
		return memberMapper.idFind(phoneNumber);
	}

	@Override
	public MemberVO memberGetInfo(MemberVO memberVO) {
		
		return memberMapper.memberGetInfo(memberVO);
		
	}

	@Override
	public String memberUpdate(MemberVO memberVO) {
		int result = memberMapper.memberUpdate(memberVO);
		return result == 1 ? memberVO.getMemberCode() : "fail";
	}



}
