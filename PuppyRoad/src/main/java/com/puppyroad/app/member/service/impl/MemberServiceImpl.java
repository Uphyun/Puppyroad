package com.puppyroad.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puppyroad.app.member.mapper.MemberMapper;
import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;
import com.puppyroad.app.petstarprofile.mapper.PetstarProfileMapper;
import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;

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
	
	private PetstarProfileMapper profileMapper;

	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper, PetstarProfileMapper profileMapper) {
		this.memberMapper = memberMapper;
		this.profileMapper = profileMapper;
	}

	// 등록
	@Override
	@Transactional
	public String addMember(MemberVO memberVO) {
		int result = memberMapper.insertMember(memberVO);

        if (result == 1) {
            PetStarProfileVO profile = new PetStarProfileVO();
            profile.setMemberCode(memberVO.getMemberCode());
            profile.setNickname(memberVO.getNickName());
            profile.setProfilePicture(null); 
            profile.setGender(0); 
            profile.setInfo(""); 

            profileMapper.insertProfile(profile);
            return memberVO.getMemberCode();
        }

        return "fail";
	}

	@Override
	public int idCheck(String userId) {

		return memberMapper.idCheck(userId);
	}

	@Override
	public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret,
				"https://api.coolsms.co.kr");

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

	@Override
	public int memberDelete(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return memberMapper.memberDelete(memberVO);
	}

}
