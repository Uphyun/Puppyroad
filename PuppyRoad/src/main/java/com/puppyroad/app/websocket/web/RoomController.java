package com.puppyroad.app.websocket.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.puppyroad.app.match.service.MatchService;
import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;
import com.puppyroad.app.puppy.service.PuppyVO;
import com.puppyroad.app.util.SecurityUtil;
import com.puppyroad.app.websocket.service.ChatMessageDTO;
import com.puppyroad.app.websocket.service.ChatMessageService;
import com.puppyroad.app.websocket.service.ChatRoomDTO;
import com.puppyroad.app.websocket.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoomController {
	
	@Autowired
    private final ChatRoomService chatRoomService;
	
	@Autowired
	private final ChatMessageService chatMessageService;
	
	@Autowired
	private final MatchService matchService;
	
    //채팅방 목록 조회
    @GetMapping("chat/rooms")
    public ModelAndView roomList(){
        ModelAndView mv = new ModelAndView("chat/rooms");
        mv.addObject("rlist", chatRoomService.getRoomList());
        return mv;
    }
    
    //내 채팅방 목록
    @GetMapping("chat/myChat")
    public String myRoomList(PetStarProfileVO petStarProfileVO,ChatRoomDTO chatRoomDTO, ChatMessageDTO chatMessageDTO, Model model, PuppyVO puppyVO) {
    	String mcode = SecurityUtil.memberCode();
    	
		chatRoomDTO.setSender(mcode);
		chatRoomDTO.setRecipient(mcode);
		puppyVO.setClientUserId(mcode);
		petStarProfileVO.setMemberCode(mcode);
		
		PetStarProfileVO myPro = chatRoomService.getMyPorfile(petStarProfileVO);
		model.addAttribute("myPro", myPro);
		
		List<ChatRoomDTO> list = chatRoomService.getMyRoomList(chatRoomDTO);
    	model.addAttribute("myList", list); 
    	
    	model.addAttribute("myChat", chatMessageService.getMessageList(chatMessageDTO));
    	
    	List<PuppyVO> dogs = matchService.getDogList(puppyVO);
    	model.addAttribute("myDogs", dogs);
    	
        return "chat/myChat";
    }
    
    //신청시 매칭 채팅방 : post
    @GetMapping("user/matchChat")
    public String myRoomInsert(ChatRoomDTO chatRoomDTO, MatchVO matchVO){
    	System.err.println(matchVO.getTitle());
    	System.err.println(matchVO.getWriter());
    	
    	String mcode = SecurityUtil.memberCode();
    	
    	chatRoomDTO.setSender(mcode);
    	
    	chatRoomService.addRoom(chatRoomDTO, matchVO);
    	return "redirect:/chat/myChat";
    }

    /**
    //채팅방 개설 : post
    @PostMapping("chat/room")
    public String roomInsert(ChatRoomDTO chatRoomDTO, RedirectAttributes rttr){
    	int result = chatRoomService.addRoom(chatRoomDTO);
    	if ( result == 1)
    		rttr.addFlashAttribute("roomNames", chatRoomDTO.getRoomName());
    	return "redirect:/chat/rooms";
    }
    */
    
    //채팅방 조회 : get
    @GetMapping("chat/room")
    public void roomInfo(ChatRoomDTO chatRoomDTO, ChatMessageDTO chatMessageDTO, Model model){
    	model.addAttribute("room", chatRoomService.getRoomInfo(chatRoomDTO));
    	model.addAttribute("myChats", chatMessageService.getMessageList(chatMessageDTO));
    }
    
    //채팅 목록 가져오기 : AJAX
    @GetMapping("chat/message")
    @ResponseBody // => AJAX
    public List<ChatMessageDTO> getMassageList(ChatMessageDTO chatMessageDTO) {
		return chatMessageService.getMessageList(chatMessageDTO);
    }
    
    //채팅방 도시 가져오기 : AJAX
    @GetMapping("chat/address")
    @ResponseBody // => AJAX
    public List<MatchVO> getAddress(MatchVO matchVO) {
		return matchService.getIdList(matchVO);
    	
    }

}