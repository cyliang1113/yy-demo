package cn.leo.ibatisdemo.comm;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class BaseIbatisDao {
	protected Logger log = Logger.getLogger(this.getClass());

	private SqlMapClientTemplate sqlMapClientTemplate;

	protected Object queryForObject(String statementName) throws DataAccessException {
		return queryForObject(statementName, null);
	}

	protected Object queryForObject(final String statementName, final Object parameterObject) {
		return sqlMapClientTemplate.queryForObject(statementName, parameterObject);
	}

	protected List queryForList(final String statementName) {
		return queryForList(statementName, null);
	}

	protected List queryForList(final String statementName, final Object parameterObject) {
		return sqlMapClientTemplate.queryForList(statementName, parameterObject);
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
}
