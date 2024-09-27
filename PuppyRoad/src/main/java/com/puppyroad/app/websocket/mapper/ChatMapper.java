package com.puppyroad.app.websocket.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;
import com.puppyroad.app.websocket.service.ChatRoomDTO;

public interface ChatMapper {
	// 전체 방 리스트 조회
	public List<ChatRoomDTO> selectRoomList();
	// my 전체 방
	public List<ChatRoomDTO> selectMyRoomList(ChatRoomDTO chatRoomDTO);
	
	// 방 추가
	public int insertRoom(@Param("chatRoomDTO") ChatRoomDTO chatRoomDTO, @Param("matchVO") MatchVO matchVO);
	// 단건 조회
	public ChatRoomDTO selectRoomInfo(ChatRoomDTO chatRoomDTO);
	// 단건 내 프로필
	public PetStarProfileVO selectMyProfile(PetStarProfileVO petStarProfileVO);

}
