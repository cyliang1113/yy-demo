package cn.leo.xback.po.sys;

import java.util.List;

import cn.leo.xback.common.Constant;

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

	// url值: 当菜单类型为2时, 才有url值
	private String menuUrl;

	// 菜单权重, 排序用, 同级菜单weight值越小, 菜单显示时排在前面
	private Integer menuWeight;

	// 父菜单id
	private Integer parentMenuId;

	// 父菜单, 一级菜单没有父菜单
	private SysMenu parentMenu;

	// 子菜单
	private List<SysMenu> childrenMenu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getMenuWeight() {
		return menuWeight;
	}

	public void setMenuWeight(Integer menuWeight) {
		this.menuWeight = menuWeight;
	}

	public Integer getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public SysMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(SysMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<SysMenu> getChildrenMenu() {
		return childrenMenu;
	}

	public void setChildrenMenu(List<SysMenu> childrenMenu) {
		this.childrenMenu = childrenMenu;
	}

	public String getMenuLevelCnName() {
		return Constant.SysMemuLevel.getCnNameByCode(this.menuLevel);
	}

	public String getMenuTypeCnName() {
		return Constant.SysMemuType.getCnNameByCode(this.menuType);
	}
	
	public boolean getIsUrlMenu(){
		return Constant.SysMemuType.URL_MENU.getCode() == this.menuType;
	}

}
