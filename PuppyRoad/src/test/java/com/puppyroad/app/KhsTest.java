package com.puppyroad.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.puppyroad.app.petstarprofile.mapper.PetstarProfileMapper;
import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
@SpringBootTest
class KhsTest {
	@Autowired
	PetstarProfileMapper profileMapper;

	// 전체조회
	 //@Test
	void profileList() {
		 PetStarProfileVO profileVO = new PetStarProfileVO();
		List<PetStarProfileVO> list = profileMapper.selectProfileList(profileVO);
		assertTrue(!list.isEmpty());
	}
	
	// 단건조회
	//@Test
	void profileInfo() {
		PetStarProfileVO profileVO = new PetStarProfileVO();
		profileVO.setMemberCode("W240905001");
		
		PetStarProfileVO findVO = profileMapper.selectPetStarProfileInfo(profileVO);
		assertEquals("활달해용", findVO.getPersonality());
	}

	// 등록
	@Test
	void profileInsert() {
		PetStarProfileVO profileVO = new PetStarProfileVO();
		profileVO.setProfilePicture("강아지");
		profileVO.setGender(1);
		profileVO.setPersonality("얌전..");
		profileVO.setNickname("후추");
		profileVO.setMemberCode("W240906002");
		
		int result = profileMapper.insertProfile(profileVO);
		System.out.println(profileVO.getMemberCode());
		
		assertEquals(1, result);
	}
	

}
