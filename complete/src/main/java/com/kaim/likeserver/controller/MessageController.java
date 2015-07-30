package com.kaim.likeserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaim.likeserver.dto.Message;
import com.kaim.likeserver.result.MessageResult;

@RestController
public class MessageController {
    @RequestMapping("/message")
    public MessageResult getMessage(@RequestParam(value="name", defaultValue="World") String name) {
    	List<Message> messageList = new ArrayList<Message>();
    	messageList.add(new Message("hahah", "ddd"));
    	messageList.add(new Message("hoho", "iii"));
    	
    	return new MessageResult(messageList, 11, 22);
    }
}
