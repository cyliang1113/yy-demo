package cn.leo.ibatisdemo.comm;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class BaseIbatisDao {

	private SqlMapClientTemplate sqlMapClientTemplate;

	public Object queryForObject(String statementName) throws DataAccessException {
		return queryForObject(statementName, null);
	}

	public Object queryForObject(final String statementName, final Object parameterObject) {
		return sqlMapClientTemplate.queryForObject(statementName, parameterObject);
	}

	public List queryForList(final String statementName) {
		return queryForList(statementName, null);
	}

	public List queryForList(final String statementName, final Object parameterObject) {
		return sqlMapClientTemplate.queryForList(statementName, parameterObject);
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
}
