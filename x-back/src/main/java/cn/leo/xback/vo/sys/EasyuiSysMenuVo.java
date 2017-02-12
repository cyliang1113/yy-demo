package cn.leo.xback.vo.sys;

import java.util.List;
import java.util.Map;

public class EasyuiSysMenuVo {

	public EasyuiSysMenuVo() {

	}

	public EasyuiSysMenuVo(Integer id, String text, Map<String, String> attributes, List<EasyuiSysMenuVo> children) {
		super();
		this.id = id;
		this.text = text;
		this.attributes = attributes;
		this.children = children;
	}

	// id：节点的 id，它对于加载远程数据很重要
	private Integer id;

	// text：要显示的节点文本
	private String text;

	// state：节点状态，'open' 或 'closed'，默认是 'open'.当设置为 'closed'
	// 时，该节点有子节点，并且将从远程站点加载它们
	private String state = "closed";

	// attributes：给一个节点添加的自定义属性
	private Map<String, String> attributes;

	// children：定义了一些子节点的节点数组
	private List<EasyuiSysMenuVo> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public List<EasyuiSysMenuVo> getChildren() {
		return children;
	}

	public void setChildren(List<EasyuiSysMenuVo> children) {
		this.children = children;
	}

}
