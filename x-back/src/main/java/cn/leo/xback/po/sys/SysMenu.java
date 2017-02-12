package cn.leo.xback.po.sys;

import java.util.List;

/**
 * 系统菜单, 菜单最多为三级, 一级菜单一定是节点, 二级菜单可以是节点或url, 三级菜单一定是url
 */
public class SysMenu {
	// 主键
	private Integer id;

	// 菜单名称
	private String menuName;

	// 菜单等级: 1, 一级菜单; 2, 二级菜单; 3, 三级菜单;
	private Integer menuLevel;

	// 菜单类型: 1, 节点; 2, url;
	private Integer menuType;

	// url值
	private Integer menuUrl;

	// 父菜单id
	private Integer parentMenuId;

	// 父菜单, 一级菜单没有父菜单
	private SysMenu parentMenu;

	// 子菜单
	private List<SysMenu> childrenMenu;

	// 菜单权重, 同一级菜单weight值越小, 菜单显示时排在前面
	private Integer menuWeight;

}
