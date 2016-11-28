package cn.leo.ibatisdemo.comm;

/**
 * 读写分离数据源Holder
 * 
 * @author chenyouliang
 * 
 */
public class RWDynamicDataSourceNameHolder {

	public static final ThreadLocal<String> dataSourceName = new ThreadLocal<String>();

	public static void setDataSourceName(String name) {
		dataSourceName.set(name);
	}

	public static String getDataSouceName() {
		return dataSourceName.get();
	}

	public static void clear() {
		dataSourceName.remove();
	}

}
