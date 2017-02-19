package cn.leo.xback.vo.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

public class EasyuiSysMenuVo {

	public EasyuiSysMenuVo() {

	}

	public EasyuiSysMenuVo(Integer id, String text, Integer level, Integer type, String url, Integer weight, Integer pId) {
		super();
		this.id = id;
		this.text = text;
		this.mLevel = level;
		this.mType = type;
		if (type != null) {
			if (type == 2) {
				// 为url菜单
				this.state = null;
				Map<String, String> attr = new HashMap<String, String>();
				attr.put("url", url);
				this.attributes = attr;
			} else if (type == 1) {
				// 为节点菜单
				this.state = "closed";
			}
		}
		this.mWeight = weight;
		this.pId = pId;
	}

	// id：节点的 id，它对于加载远程数据很重要
	private Integer id;

	// text：要显示的节点文本
	private String text;

	// state：节点状态，'open' 或 'closed'，默认是 'open'.当设置为 'closed'
	// 时，该节点有子节点，并且将从远程站点加载它们
	private String state;

	// attributes：给一个节点添加的自定义属性
	private Map<String, String> attributes;

	// children：定义了一些子节点的节点数组
	private List<EasyuiSysMenuVo> children;

	// 菜单等级: 1, 一级菜单; 2, 二级菜单; 3, 三级菜单;
	private Integer mLevel;

	// 菜单类型: 1, 节点; 2, url;
	private Integer mType;

	// 菜单权重, 排序用, 同级菜单weight值越小, 菜单显示时排在前面
	private Integer mWeight;

	// 父菜单id
	private Integer pId;

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

	@JsonIgnore
	public Integer getmLevel() {
		return mLevel;
	}

	public void setmLevel(Integer mLevel) {
		this.mLevel = mLevel;
	}

	@JsonIgnore
	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
	}

	@JsonIgnore
	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	@JsonIgnore
	public Integer getmWeight() {
		return mWeight;
	}

	public void setmWeight(Integer mWeight) {
		this.mWeight = mWeight;
	}

}
