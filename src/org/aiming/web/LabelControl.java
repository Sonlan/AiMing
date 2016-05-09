package org.aiming.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/label")
public class LabelControl {
	/**
	 * 标签激活绑定
	 * @return
	 */
	@RequestMapping(value="/bind")
	public String bind(){
		return null;
	}
}
