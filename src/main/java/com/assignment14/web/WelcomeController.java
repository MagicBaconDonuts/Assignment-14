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
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment14.Service.ChannelService;
import com.assignment14.Service.MessageService;
import com.assignment14.Service.UserService;
import com.assignment14.domain.Channel;
import com.assignment14.domain.Message;
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
	public Boolean saveMess(@RequestBody User user) throws JSONException {// save the messeage via the user and withing what channel they are in 
		//find the user by username since its inquie and then I would add the message to the user and save the user and the message
		String username = user.getUsername();
		List<User> allUsers = userService.findAll();
		User userPresent = null;
		for(User checkUser: allUsers) {
				JSONObject obj = new JSONObject(checkUser.getUsername());
				System.out.println(obj.getString(username));
			if(username == obj.getString(username)) {
				userPresent = checkUser;
			}
		}
		userPresent.getMessage().add(user.getMessage().get(0));
		System.out.println(userPresent.toString());
		return true;
	}
	
}
