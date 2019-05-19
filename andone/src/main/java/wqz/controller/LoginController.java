package wqz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wqz.repository.UserRepository;
import wqz.domain.User;

@Controller
public class LoginController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/loginpage",method=RequestMethod.GET)
	public String loginpage(HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user != null){
			session.removeAttribute("user");
		}
		return "loginpage";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpSession session,@ModelAttribute("username") String username,@ModelAttribute("password") String password,
			ModelMap modelmap){
		System.out.println("login controller");
		User user = null;
		if(username.equals("") || username==null){
			return "loginpage";
		}
		user = userRepository.findByUsername(username);
		if(user == null){
			System.out.println("用户不存在");
			modelmap.addAttribute("loginResult", "用户不存在！！");
			return "loginpage";
		}
		if(password==null || password.equals("") || !password.equals(user.getPassword())){
			System.out.println("密码错误");
			modelmap.addAttribute("loginResult", "密码输入有误！！");
			return "loginpage";
		}
		modelmap.addAttribute("loginResult", "登陆成功");
		session.setAttribute("user", user);
		return "redirect:gates";
	}
	
	@RequestMapping(value="registpage",method=RequestMethod.GET)
	public String registpage(){
		return "registpage";
	}
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String regist(@ModelAttribute("username") String username,@ModelAttribute("password") String password,
			ModelMap modelmap){
		User user = userRepository.findByUsername(username);
		if(user != null){
			System.out.println("用户已经存在");
			modelmap.addAttribute("registResult", "用户已经存在！！");
			return "registpage";
		}
		if(password==null || password.equals("") || username == null || username.equals("")){
			System.out.println("输入有误");
			modelmap.addAttribute("registResult", "输入有误！！");
			return "registpage";
		}
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setStatus(true);
		userRepository.save(user);
		modelmap.addAttribute("registResult", "注册成功！！");
		return "loginpage";
	}
}
