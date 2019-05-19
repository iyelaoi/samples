package wqz.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	
	//handler mapping
	@RequestMapping("/helloworld")
	public String hello(){
		System.out.println("======================hello world=================");
		return "success";
	}
}
