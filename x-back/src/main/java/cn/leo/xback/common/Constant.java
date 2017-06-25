package cn.leo.xback.common;

public class Constant {

	/**
	 * 分页默认的每页条数
	 */
	public final static Long DEFAULT_PAGE_SIZE = Long.valueOf(10);

	/**
	 * 系统菜单等级
	 */
	public enum SysMemuLevel {
		FIRST_LEVEL_MENU(1, "一级菜单"), SECOND_LEVEL_MENU(2, "二级菜单"), THIRDLY_LEVEL_MENU(3, "三级菜单");

		private int code;
		private String cnName;

		private SysMemuLevel(int code, String cnName) {
			this.code = code;
			this.cnName = cnName;
		}

		public int getCode() {
			return this.code;
		}

		public String getCnName() {
			return this.cnName;
		}

	}

	/**
	 * 系统菜单类型
	 */
	public enum SysMemuType {
		NODE_MENU(1, "节点菜单"), URL_MENU(2, "url菜单");

		private int code;
		private String cnName;

		private SysMemuType(int code, String cnName) {
			this.code = code;
			this.cnName = cnName;
		}

		public int getCode() {
			return this.code;
		}

		public String getCnName() {
			return this.cnName;
		}

	}

}
