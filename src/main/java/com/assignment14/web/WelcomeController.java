package com.assignment14.web;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment14.Service.ChannelService;
import com.assignment14.Service.MessageService;
import com.assignment14.Service.UserService;
import com.assignment14.domain.Channel;
import com.assignment14.domain.Message;
import com.assignment14.domain.MessageDTO;
import com.assignment14.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WelcomeController {

	@Autowired
	private ChannelService channelService;
	@Autowired
	private UserService userService;
	@Autowired 
	private MessageService messageService;

	@GetMapping("/welcome")
	public String getWelcomePage(ModelMap model) {
		List<Channel> allChannels = channelService.findAll();
		model.put("channels", allChannels);
		return "welcome";
	}
	@GetMapping("/channel/{channelId}")
	public String getChannelPage(@PathVariable Long channelId, ModelMap model) {
		Optional<Channel> channel = channelService.findById(channelId);
		List<Message> messages = messageService.findAllByChannelId(channelId);
		Channel mainChannel = channel.get();
		model.put("channel", mainChannel);
		model.put("message", messages);
		return "channel";
	}
	@PostMapping("/channel/{channelId}")
	public String postChannelPage(Message message) {
		Message messagesSaved = messageService.save(message);
		return "redirect:/channel";
	}
	
	@PostMapping("/saveUser")
	@ResponseBody
	public Boolean saveUser (@RequestBody User user) {
		user = userService.save(user);
		return (user != null);		
	}
	@PostMapping("/saveMess")
	@ResponseBody
	public Boolean saveMess(@RequestBody User user){//note to self: I did it by user since I couldnt pass a message to many errors
		String username = user.getUsername();
		String[] usernameData = username.split(":");
		String usernameInfo = null;
		for(String data: usernameData) {
			usernameInfo = data;
		}
		int lengthOfUsername = usernameInfo.length();
		usernameInfo= usernameInfo.substring(1, lengthOfUsername - 2);
		List<User> allUsers = userService.findAll();
		User userPresent = null;
		for(User checkUser: allUsers) {
			String userInfo = checkUser.getUsername();
			if(usernameInfo.equals(userInfo)) {
				userPresent = checkUser;
				break;
			}
		}
		String mess = user.getMessage().get(0).getMessage();
		Message fullMess = new Message();
		Optional<Channel> channelFound = channelService.findById(user.getId());// note to self im finding by user id(its actualuy the value of the channelid since passing in the channel to the user wosent working)
		Channel fullChannel = channelFound.get();
		fullMess.setChannel(fullChannel);
		fullMess.setMessage(user.getMessage().get(0).getMessage());
		fullMess.setUser(userPresent);
		messageService.save(fullMess);
		return true;
	}
	@PostMapping("/channel/{channelId}/getMessages")
	@ResponseBody
	public List<MessageDTO> getMessages(@PathVariable String channelId){
		String numOnly = channelId.replaceAll("[^0-9]", "");
		Long channel = Long.parseLong(numOnly);
		List<Message> listOfMessages = messageService.findAllByChannelId(channel);
		return messageService.findAllByChannelIdDTO(channel);
	}
	
}
