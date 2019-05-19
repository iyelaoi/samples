package wqz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wqz.repository.UserRepository;
import wqz.domain.GateStatus;
import wqz.domain.User;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	
	public void printpage(Page<GateStatus> page){

	}
	
	@RequestMapping(value="/userMannage",method=RequestMethod.GET)
	public String userMannage(ModelMap modelMap){
		List<User> userlist = userRepository.findAll();
		modelMap.addAttribute("userlist",userlist);
		return "userlist";
	}
}
