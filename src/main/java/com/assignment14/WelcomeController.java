package com.assignment14;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.assignment14.Service.ChannelService;
import com.assignment14.Service.MessageService;
import com.assignment14.Service.UserService;
import com.assignment14.domain.Channel;
import com.assignment14.domain.Message;
import com.assignment14.domain.User;

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
	public Boolean saveUser (@RequestBody User user) {
		user = userService.save(user);
		return true;
		
	}
	@PostMapping("/saveMess")
	public String saveMess(@RequestBody User user) {// save the messeage via the user and withing what channel they are in 
		return "redirect:/channel/" + 1;
	}
	
}
