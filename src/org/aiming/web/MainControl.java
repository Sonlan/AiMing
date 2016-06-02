package org.aiming.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/main")
public class MainControl {
	@RequestMapping(value="/toRegister")
	public String toRegister(){
		return "/main/register";
	}
	@RequestMapping(value="/toCheck")
	public String toCheck(){
		return "/main/check";
	}
	@RequestMapping(value="/toManul")
	public String toManul(){
		return "/main/manul";
	}
	@RequestMapping(value="/toActive")
	public String toActive(){
		return "/main/active";
	}
	
}
