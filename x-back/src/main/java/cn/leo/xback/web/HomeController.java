package cn.leo.xback.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.leo.xback.po.sys.SysMenu;
import cn.leo.xback.service.sys.SysMenuService;
import cn.leo.xback.vo.sys.EasyuiSysMenuVo;
@Controller
public class HomeController {
	@Autowired
	private SysMenuService sysMenuService;

	// 主页
	@RequestMapping({ "/", "/home" })
	public String homepage() {
		return "home";
	}

	// 主页菜单
	@RequestMapping({ "/home/menu" })
	@ResponseBody
	public List<EasyuiSysMenuVo> homeMenu() {
		List<SysMenu> selectAll = sysMenuService.selectAll();
		
		return initHomeMenu(selectAll);
		
		
//		LinkedList<EasyuiSysMenuVo> list = new LinkedList<EasyuiSysMenuVo>();
//		EasyuiSysMenuVo easyuiSysMenuVo1 = new EasyuiSysMenuVo(1, "系统管理", null, null);
//
//		LinkedList<EasyuiSysMenuVo> easyuiSysMenuVo1Children = new LinkedList<EasyuiSysMenuVo>();
//		
//		EasyuiSysMenuVo easyuiSysMenuVo12 = new EasyuiSysMenuVo(12, "菜单管理", null, null);
//		easyuiSysMenuVo1Children.add(easyuiSysMenuVo12);
//		Map<String, String> easyuiSysMenuVo12attributes = new HashMap<String, String>();
//		easyuiSysMenuVo12attributes.put("url", "http://www.baidu.com");
//		easyuiSysMenuVo12.setAttributes(easyuiSysMenuVo12attributes);
//		easyuiSysMenuVo12.setState("open");
//		
//		EasyuiSysMenuVo easyuiSysMenuVo11 = new EasyuiSysMenuVo(11, "权限管理", null, null);
//		easyuiSysMenuVo1Children.add(easyuiSysMenuVo11);
//		
//		
//		
//		easyuiSysMenuVo1.setChildren(easyuiSysMenuVo1Children);
//		
//		list.add(easyuiSysMenuVo1);
//		return list;
	}
	
	private List<EasyuiSysMenuVo> initHomeMenu(List<SysMenu> sysMenuList){
		LinkedList<EasyuiSysMenuVo> list = new LinkedList<EasyuiSysMenuVo>();
		return list;
	}
}
