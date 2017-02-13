package cn.leo.xback.web.sys;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.leo.xback.po.sys.SysMenu;
import cn.leo.xback.service.sys.SysMenuService;

@Controller
public class SysMenuManageController {
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping({ "/sysmanage/menumanage" })
	public String sysMenuManage() {
		return "sys/menumanage";
	}

}
