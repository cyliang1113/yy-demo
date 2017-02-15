package cn.leo.xback.web.sys;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.leo.xback.common.Constant;
import cn.leo.xback.common.Page;
import cn.leo.xback.po.sys.SysMenu;
import cn.leo.xback.service.sys.SysMenuService;

@Controller
@RequestMapping("/sysmanage/menumanage")
public class SysMenuManageController {
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SysMenuService sysMenuService;

	// 菜单管理页
	@RequestMapping({ "/page" })
	public String sysMenuManage() {
		return "sys/menumanage";
	}

	@RequestMapping({ "/load" })
	@ResponseBody
	public Page<SysMenu> load(Page<SysMenu> page, SysMenu menu) {
		HashMap<String, Object> params = new HashMap<String, Object>();

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
			if (page.getPageSize() != null && page.getPageSize() >= Constant.DEFAULT_PAGE_SIZE) {
				Long start = (page.getCurrentPage() - 1) * page.getPageSize();
				Long end = page.getPageSize();
				params.put("start", start);
				params.put("end", end);
			}
		}

		List<SysMenu> items = sysMenuService.selectByParams(params);
		Long total = sysMenuService.selectByParamsCount(params);

		page.setItems(items);
		page.setTotal(total);

		return page;
	}

}
