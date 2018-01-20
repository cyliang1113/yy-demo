package cn.leo.xback.web.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.leo.xback.common.Constant;
import cn.leo.xback.common.Constant.SysMemuLevel;
import cn.leo.xback.common.Constant.SysMemuType;
import cn.leo.xback.common.Page;
import cn.leo.xback.po.sys.SysMenu;
import cn.leo.xback.service.sys.SysMenuService;

@Controller
@RequestMapping("/sysmanage/menumanage")
public class SysMenuManageController {
	 // private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 菜单管理页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping({ "/page" })
	public String sysMenuManage(Model model) {
		SysMemuLevel[] sysMemuLevelArr = Constant.SysMemuLevel.values();
		SysMemuType[] sysMemuTypeArr = Constant.SysMemuType.values();

		model.addAttribute("sysMemuLevelArr", sysMemuLevelArr);
		model.addAttribute("sysMemuTypeArr", sysMemuTypeArr);

		return "sys/menumanage";
	}

	/**
	 * 菜单列表查询
	 * 
	 * @param page
	 * @param menu
	 * @return
	 */
	@RequestMapping({ "/load" })
	@ResponseBody
	public Page<SysMenu> load(Page<SysMenu> page, SysMenu menu) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (StringUtils.isNotEmpty(menu.getMenuName())) {
			params.put("menuName", "%" + menu.getMenuName() + "%");
		}
		if (menu.getMenuLevel() != null) {
			params.put("menuLevel", menu.getMenuLevel());
		}
		if (menu.getMenuType() != null) {
			params.put("menuType", menu.getMenuType());
		}

		params.put("orderField", "id");
		params.put("orderType", "desc");

		if (page.getCurrentPage() != null && page.getCurrentPage() >= 1) {
			Long start = (page.getCurrentPage() - 1) * page.getPageSize();
			params.put("start", start);
		} else {
			params.put("start", 0);
		}

		if (page.getPageSize() != null && page.getPageSize() >= 1) {
			Long end = page.getPageSize();
			params.put("end", end);
		} else {
			params.put("end", Constant.DEFAULT_PAGE_SIZE);
		}

		List<SysMenu> items = sysMenuService.selectByParams(params);
		Long total = sysMenuService.selectByParamsCount(params);

		page.setItems(items);
		page.setTotal(total);

		return page;
	}

	@RequestMapping({ "/menuedit" })
	public String sysMenuEdit(SysMenu menu, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", menu.getId());
		List<SysMenu> items = sysMenuService.selectByParams(params);
		if(items != null && items.size() == 1){
			model.addAttribute("sysMenu", items.get(0));
		}
		return "sys/menuedit";
	}
	
	@RequestMapping({ "/menuedit/save" })
	@ResponseBody
	public String load(SysMenu menu) {
		sysMenuService.updateSysMenu(menu);
		return "ok";
	}

}
