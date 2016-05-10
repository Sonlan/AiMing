package org.aiming.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aiming.entity.Label;
import org.aiming.service.LabelService;
import org.aiming.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/label")
public class LabelControl {
	@Autowired
	private LabelService labelService;
	/**
	 * 标签激活绑定
	 * @return
	 */
	@RequestMapping(value="/bind")
	public void bind(HttpServletRequest request,HttpServletResponse response){
		String labels = request.getParameter("labels");
		response.setContentType("application/json;charset=utf-8");
		JsonUtil.toObject(labels, Label.class);
		//TO-DO
	}
	/**
	 * 滤芯安装
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/deploy")
	public void deploy(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=utf-8");
		try {
			long id = null == request.getParameter("id")?0:Long.parseLong((request.getParameter("id")));
			if(0!=id){
				if(labelService.labelDepoy(id)){
					response.getWriter().write(JsonUtil.statusResponse(0, "安装成功", ""));
				}else {
					response.getWriter().write(JsonUtil.statusResponse(1, "安装失败", ""));
				}
			}else response.getWriter().write(JsonUtil.statusResponse(1, "请检查输入参数", ""));
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(JsonUtil.statusResponse(2, "后台错误", ""));
		}
	}
	/**
	 * 滤芯拆卸操作
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
		long id = null == request.getParameter("id")?0:Long.parseLong((request.getParameter("id")));
		int washCountLimit = 3;
		response.setContentType("application/json;charset=utf-8");
		if(0!=id){
			Label label = labelService.labelRemove(id);
			if(null != label){
				//判断清洗次数是否超过上限
				Properties prop=new Properties();
				prop.load(new InputStreamReader(LabelControl.class.getClassLoader().getResourceAsStream("workConig.properties"), "UTF-8"));
				washCountLimit = Integer.parseInt((prop.getProperty("washCountLimit")));
				if(label.getWashing_count()>=washCountLimit){
					response.getWriter().write(JsonUtil.statusResponse(1, "已达到清洗次数上限", ""));
				}else
					response.getWriter().write(JsonUtil.statusResponse(0, "拆卸成功", ""));
			}
		}else response.getWriter().write(JsonUtil.statusResponse(1, "请检查输入参数", ""));
	}
	/**
	 * 手动报废
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/scrap")
	public void scrap(HttpServletRequest request, HttpServletResponse response) throws IOException{
		long id = null == request.getParameter("id")?0:Long.parseLong((request.getParameter("id")));
		response.setContentType("application/json;charset=utf-8");
		if(0!=id){
			if(labelService.labelScrap(id)){
				response.getWriter().write(JsonUtil.statusResponse(0, "手动报废成功", ""));
			}else response.getWriter().write(JsonUtil.statusResponse(1, "手动报废失败", ""));
		}else response.getWriter().write(JsonUtil.statusResponse(1, "请检查输入参数", ""));
	}
	/**
	 * 标签信息查询，用于移动端
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/query")
	public void query(HttpServletRequest request, HttpServletResponse response) throws IOException{
		long id = null == request.getParameter("id")?0:Long.parseLong((request.getParameter("id")));
		response.setContentType("application/json;charset=utf-8");
		List<Label> list = labelService.labelQuery(id);
		if(null != list && 0 != list.size()){
			response.getWriter().write(JsonUtil.statusResponse(0, "查询成功", list));
		}else response.getWriter().write(JsonUtil.statusResponse(1, "查询失败", ""));
	}
}
