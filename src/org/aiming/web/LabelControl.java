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
	 * @throws IOException 
	 */
	@RequestMapping(value="/bind")
	public void bind(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String data = request.getParameter("data");
		response.setContentType("application/json;charset=utf-8");
		if(null != null && !"".equals(data));
		try {
			List<String> labels = JsonUtil.toObject(data, List.class);
			if(null == labels || 0==labels.size()) 
				response.getWriter().write(JsonUtil.statusResponse(1, "激活绑定失败,请检查数据", ""));
			else if(labelService.labelBind(labels)){
				response.getWriter().write(JsonUtil.statusResponse(0, "激活绑定成功", ""));
			}else response.getWriter().write(JsonUtil.statusResponse(1, "激活绑定失败", ""));
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(JsonUtil.statusResponse(2, "激活绑定失败,后台错误", ""));
		}
		
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
			String id = request.getParameter("id");
			if(null!=id && !"".equals(id)){
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
		String id = request.getParameter("id");
		int washCountLimit = 3;
		response.setContentType("application/json;charset=utf-8");
		if(null!=id && !"".equals(id)){
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
		String id = request.getParameter("id");
		response.setContentType("application/json;charset=utf-8");
		if(null!=id && !"".equals(id)){
			if(labelService.labelScrap(id)){
				response.getWriter().write(JsonUtil.statusResponse(0, "手动报废成功", ""));
			}else response.getWriter().write(JsonUtil.statusResponse(1, "手动报废失败", ""));
		}else response.getWriter().write(JsonUtil.statusResponse(1, "请检查输入参数", ""));
	}
	/**
	 * 标签信息查询，用于移动端和web端,輸入参数为空时查询所有信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/query")
	public void query(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = null==request.getParameter("id")?"":request.getParameter("id");  //滤芯id
		String inuse = null==request.getParameter("inuse")?"":request.getParameter("inuse");  //是否在使用
		String alive = null==request.getParameter("alive")?"":request.getParameter("alive");  //是否报废
		String ac_id = null==request.getParameter("ac_id")?"":request.getParameter("ac_id");  //空调id，id前两位
		String aliveTime = null==request.getParameter("aliveTime")?"":request.getParameter("aliveTime");  //距离报废的时间，单位小时
		int page = null==request.getParameter("page")?0:Integer.parseInt(request.getParameter("page"));  //页数，从0开始
		response.setContentType("application/json;charset=utf-8");
		List<Label> list = labelService.labelQuery(id,inuse,alive,ac_id,aliveTime,page);
		if(null != list){
			if(0==list.size()) response.getWriter().write(JsonUtil.statusResponse(1, "没有符合条件的数据", ""));
			else response.getWriter().write(JsonUtil.statusResponse(0, labelService.getlabelSizeQuery(id, inuse, alive, ac_id, aliveTime), list));
		}else response.getWriter().write(JsonUtil.statusResponse(1, "查询失败", ""));
		
	}
	
	
}
