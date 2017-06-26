package cn.leo.xback.service.sys;

import java.util.List;
import java.util.Map;

import cn.leo.xback.po.sys.SysMenu;

public interface SysMenuService {
	public List<SysMenu> selectAll();

	public List<SysMenu> selectByParams(Map<String, Object> paramsMap);

	public Long selectByParamsCount(Map<String, Object> paramsMap);
	
	public Long updateSysMenu(SysMenu sysMenu);
}
