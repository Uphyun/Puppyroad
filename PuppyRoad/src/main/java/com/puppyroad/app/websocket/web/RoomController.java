package com.puppyroad.app.websocket.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.puppyroad.app.security.service.LoginMemberVO;
import com.puppyroad.app.util.SecurityUtil;
import com.puppyroad.app.websocket.service.ChatMessageDTO;
import com.puppyroad.app.websocket.service.ChatMessageService;
import com.puppyroad.app.websocket.service.ChatRoomDTO;
import com.puppyroad.app.websocket.service.ChatRoomService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoomController {
	
	@Autowired
    private final ChatRoomService chatRoomService;
	
	@Autowired
	private final ChatMessageService chatMessageService;
	

    //채팅방 목록 조회
    @GetMapping("chat/rooms")
    public ModelAndView roomList(){
        ModelAndView mv = new ModelAndView("chat/rooms");
        mv.addObject("rlist", chatRoomService.getRoomList());
        return mv;
    }
    
    //내 채팅방 목록
    @GetMapping("chat/myChat")
    public String myRoomList(ChatRoomDTO chatRoomDTO, ChatMessageDTO chatMessageDTO,Model model) {
    	String mcode = SecurityUtil.memberCode();
    	
		chatRoomDTO.setSender(mcode);
		chatRoomDTO.setRecipient(mcode);
		
		List<ChatRoomDTO> list = chatRoomService.getMyRoomList(chatRoomDTO);
    	model.addAttribute("myList", list); 
    	
    	
    	model.addAttribute("myChat", chatMessageService.getMessageList(chatMessageDTO));
    	
        return "/chat/myChat";
    }
    
    //신청시 매칭 채팅방 : post
    @GetMapping("user/matchChat")
    public String myRoomInsert(ChatRoomDTO chatRoomDTO, RedirectAttributes rttr, HttpServletRequest req){
    	String mcode = SecurityUtil.memberCode();
    	String title = req.getParameter("title");
    	String writer = req.getParameter("writer");
    	String type = req.getParameter("chattingType");
    	
    	chatRoomDTO.setSender(mcode);
    	chatRoomDTO.setRecipient(writer);
    	chatRoomDTO.setRoomName(title);
    	
    	int result = chatRoomService.addRoom(chatRoomDTO);
    	return "redirect:/chat/myChat";
    }

    //채팅방 개설 : post
    @PostMapping("chat/room")
    public String roomInsert(ChatRoomDTO chatRoomDTO, RedirectAttributes rttr){
    	int result = chatRoomService.addRoom(chatRoomDTO);
    	if ( result == 1)
    		rttr.addFlashAttribute("roomNames", chatRoomDTO.getRoomName());
    	return "redirect:/chat/rooms";
    }
    
    //채팅방 조회 : get
    @GetMapping("chat/room")
    public void roomInfo(ChatRoomDTO chatRoomDTO, ChatMessageDTO chatMessageDTO, Model model){
    	model.addAttribute("room", chatRoomService.getRoomInfo(chatRoomDTO));
    	model.addAttribute("myChats", chatMessageService.getMessageList(chatMessageDTO));
    }
    
    //채팅 목록 가져오기 : ajax
    @GetMapping("chat/message")
    @ResponseBody // => AJAX
    public List<ChatMessageDTO> getMassageList(ChatMessageDTO chatMessageDTO) {
    	
		return chatMessageService.getMessageList(chatMessageDTO);
    	
    }
    

}