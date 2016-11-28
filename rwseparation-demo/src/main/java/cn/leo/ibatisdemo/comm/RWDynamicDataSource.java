package cn.leo.ibatisdemo.comm;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 读写分离数据源
 * 
 * @author chenyouliang
 * 
 */
public class RWDynamicDataSource extends AbstractRoutingDataSource {

	public static final String DEFAULT_DATA_SOURCE = "defaultDataSource";
	public static final String READ_ONLY_DATA_SOURCE = "readOnlyDataSource";

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSouceName = RWDynamicDataSourceNameHolder.getDataSouceName();
		if (dataSouceName == null) {
			dataSouceName = DEFAULT_DATA_SOURCE;
		}
		logger.debug("RWDynamicDataSource.determineCurrentLookupKey(): " + dataSouceName);
		return dataSouceName;
	}

}
