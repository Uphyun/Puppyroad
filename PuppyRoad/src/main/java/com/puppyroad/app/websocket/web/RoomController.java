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

    // 등록 - 페이지 : get
    @GetMapping("room")
    public String roomInfo(ChatRoomDTO chatRoomDTO, Model model){
    	model.addAttribute("room", chatRoomService.getRoomInfo(chatRoomDTO));
    	return "chat/rooms";
    }
    
    // 등록 - 페이지 : post
    @PostMapping("room")
    public String roomInsert(@RequestParam ChatRoomDTO chatRoomDTO, RedirectAttributes rttr){
        rttr.addFlashAttribute("roomName", chatRoomService.addRoom(chatRoomDTO));
        return "chat/rooms";
    }

}