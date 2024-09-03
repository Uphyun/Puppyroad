package com.puppyroad.app.websocket.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.puppyroad.app.websocket.service.ChatRoomDTO;
import com.puppyroad.app.websocket.service.ChatRoomService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("chat")
public class RoomController {
	
	@Autowired
    private final ChatRoomService chatRoomService;

    //채팅방 목록 조회
    @GetMapping("rooms")
    public ModelAndView roomList(){
        ModelAndView mv = new ModelAndView("chat/rooms");
        mv.addObject("list", chatRoomService.getRoomList());
        return mv;
    }

    //채팅방 개설 : post
    @PostMapping("room")
    public String roomInsert(ChatRoomDTO chatRoomDTO, RedirectAttributes rttr){
    	rttr.addFlashAttribute("roomName", chatRoomService.addRoom(chatRoomDTO));
    	return "redirect:/chat/rooms";
    }
    
    //채팅방 조회 : get
    @GetMapping("room")
    public void roomInfo(ChatRoomDTO chatRoomDTO, Model model){
    	model.addAttribute("room", chatRoomService.getRoomInfo(chatRoomDTO));
    }
    

}