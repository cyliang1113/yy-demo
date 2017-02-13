package cn.leo.xback.web;

import java.util.Collections;
import java.util.Comparator;
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
	}

	// 初始化主页菜单, 使其为树结构用于前端显示
	private List<EasyuiSysMenuVo> initHomeMenu(List<SysMenu> sysMenuList) {

		List<EasyuiSysMenuVo> lv1MenuList = new LinkedList<EasyuiSysMenuVo>();
		Map<Integer, EasyuiSysMenuVo> lv1MenuMap = new HashMap<Integer, EasyuiSysMenuVo>();

		List<EasyuiSysMenuVo> lv2MenuList = new LinkedList<EasyuiSysMenuVo>();
		Map<Integer, EasyuiSysMenuVo> lv2MenuMap = new HashMap<Integer, EasyuiSysMenuVo>();

		List<EasyuiSysMenuVo> lv3MenuList = new LinkedList<EasyuiSysMenuVo>();

		if (sysMenuList != null) {
			for (SysMenu sysMenu : sysMenuList) {
				EasyuiSysMenuVo menuVo = new EasyuiSysMenuVo(sysMenu.getId(), sysMenu.getMenuName(), sysMenu.getMenuLevel(),
						sysMenu.getMenuType(), sysMenu.getMenuUrl(), sysMenu.getMenuWeight(), sysMenu.getParentMenuId());
				if (menuVo.getmLevel() == 1) {
					lv1MenuList.add(menuVo);
					lv1MenuMap.put(menuVo.getId(), menuVo);
				} else if (menuVo.getmLevel() == 2) {
					lv2MenuList.add(menuVo);
					lv2MenuMap.put(menuVo.getId(), menuVo);
				} else if (menuVo.getmLevel() == 3) {
					lv3MenuList.add(menuVo);
				}
			}

			for (int i = 0; i < lv3MenuList.size(); i++) {
				EasyuiSysMenuVo lv3Menu = lv3MenuList.get(i);
				Integer parentId = lv3Menu.getpId();
				EasyuiSysMenuVo lv2Menu = lv2MenuMap.get(parentId);
				if (lv2Menu != null) {
					if (lv2Menu.getChildren() == null) {
						lv2Menu.setChildren(new LinkedList<EasyuiSysMenuVo>());
					}
					lv2Menu.getChildren().add(lv3Menu);
				}
			}

			for (int i = 0; i < lv2MenuList.size(); i++) {
				EasyuiSysMenuVo lv2Menu = lv2MenuList.get(i);
				Integer parentId = lv2Menu.getpId();
				EasyuiSysMenuVo lv1Menu = lv1MenuMap.get(parentId);
				if (lv1Menu != null) {
					if (lv1Menu.getChildren() == null) {
						lv1Menu.setChildren(new LinkedList<EasyuiSysMenuVo>());
					}
					lv1Menu.getChildren().add(lv2Menu);
				}
			}

		}

		if (lv1MenuList.size() > 0) {
			Collections.sort(lv1MenuList, new Comparator<EasyuiSysMenuVo>() {
				@Override
				public int compare(EasyuiSysMenuVo o1, EasyuiSysMenuVo o2) {
					return o1.getmWeight() - o2.getmWeight();
				}

			});
		}

		for (EasyuiSysMenuVo lv1Menu : lv1MenuList) {
			if (lv1Menu.getmType() == 1 && lv1Menu.getChildren() != null && lv1Menu.getChildren().size() > 0) {
				Collections.sort(lv1Menu.getChildren(), new Comparator<EasyuiSysMenuVo>() {
					@Override
					public int compare(EasyuiSysMenuVo o1, EasyuiSysMenuVo o2) {
						return o1.getmWeight() - o2.getmWeight();
					}

				});
			}
		}

		return lv1MenuList;
	}
}
