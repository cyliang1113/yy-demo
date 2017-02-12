package cn.leo.xback.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.leo.xback.dao.sys.SysMenuMapper;
import cn.leo.xback.po.sys.SysMenu;
import cn.leo.xback.service.sys.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper mapper;

	@Override
	public List<SysMenu> selectAll() {
		return mapper.selectAll();
	}

}
