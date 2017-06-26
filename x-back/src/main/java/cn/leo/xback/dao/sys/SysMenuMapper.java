package cn.leo.xback.dao.sys;

import java.util.List;
import java.util.Map;

import cn.leo.xback.po.sys.SysMenu;

public interface SysMenuMapper {

	// 查询所有
	public List<SysMenu> selectAll();

	// 条件查询
	public List<SysMenu> selectByParams(Map<String, Object> paramsMap);

	public Long selectByParamsCount(Map<String, Object> paramsMap);
	
	public Long updateSysMenu(SysMenu sysMenu);
}
