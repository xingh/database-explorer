/**
 * 
 */
package com.gs.dbex.integration.impl.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gs.dbex.common.enums.ReadDepthEnum;
import com.gs.dbex.common.exception.DbexException;
import com.gs.dbex.common.exception.ErrorCodeConstants;
import com.gs.dbex.core.CatalogGrabber;
import com.gs.dbex.core.mysql.MysqlDbGrabber;
import com.gs.dbex.core.mysql.MysqlMetaQueryConstants;
import com.gs.dbex.integration.DatabaseMetadataIntegration;
import com.gs.dbex.integration.IntegrationBeanFactory;
import com.gs.dbex.integration.impl.DatabaseMetadataIntegrationImpl;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.db.Constraint;
import com.gs.dbex.model.db.Database;
import com.gs.dbex.model.db.Schema;
import com.gs.dbex.model.db.Table;
import com.gs.utils.jdbc.JdbcUtil;
import com.mysql.jdbc.PreparedStatement;

/**
 * @author Sabuj.das
 *
 */
public class MysqlDatabaseMetadataIntegrationImpl extends
		DatabaseMetadataIntegrationImpl {

	private static Logger logger = Logger.getLogger(MysqlDatabaseMetadataIntegrationImpl.class);
	
	private CatalogGrabber dbGrabber;
	
	@Override
	public Set<String> getAvailableSchemaNames(
			ConnectionProperties connectionProperties, ReadDepthEnum readDepthEnum) throws DbexException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Database readDatabase(ConnectionProperties connectionProperties,
			ReadDepthEnum readDepthEnum) throws DbexException {
		logger.debug("START:: Reading Full database.");
		if(connectionProperties == null){
			throw new DbexException(ErrorCodeConstants.CANNOT_CONNECT_DB);
		}
		Database database = null;
		try {
			if(dbGrabber != null)
				database = dbGrabber.grabDatabaseByCatalog(connectionProperties, connectionProperties.getDatabaseConfiguration().getSchemaName(), readDepthEnum);
		} catch (SQLException e) {
			logger.error(e);
			throw new DbexException(null, e.getMessage());
		} 
		logger.debug("END:: Reading Full database.");
		return database;
	}

	public Schema readSchema(ConnectionProperties connectionProperties,
			String schemaName, ReadDepthEnum readDepthEnum) {
		// TODO Auto-generated method stub
		return null;
	}

	public Table readTable(ConnectionProperties connectionProperties,
			String schemaName, String tableName, ReadDepthEnum readDepthEnum) throws DbexException {
		logger.debug("START:: readTable()");
		if(connectionProperties == null){
			throw new DbexException(ErrorCodeConstants.CANNOT_CONNECT_DB);
		}
		Table table = null;
		try {
			if(dbGrabber != null)
				table = dbGrabber.grabTable(connectionProperties, schemaName, tableName, readDepthEnum);
		} catch (SQLException e) {
			logger.error(e);
			throw new DbexException(null, e.getMessage());
		} 
		logger.debug("END:: readTable()");
		return table;
	}

	public CatalogGrabber getDbGrabber() {
		return dbGrabber;
	}

	public void setDbGrabber(CatalogGrabber dbGrabber) {
		this.dbGrabber = dbGrabber;
	}

	public ResultSet getAllConstraints(Connection connection,
			String schemaName, String tableName) throws DbexException {
		if(logger.isDebugEnabled()){
			logger.debug("Enter:: getAllConstraints()");
		}
		if(connection == null){
			throw new DbexException(ErrorCodeConstants.CANNOT_CONNECT_DB);
		}
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(
					MysqlMetaQueryConstants.GET_ALL_CONSTRAINTS_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			preparedStatement.setString(1, schemaName);
			preparedStatement.setString(2, schemaName);
			preparedStatement.setString(3, tableName);
			if(logger.isDebugEnabled()){
				logger.debug("Executing SQL: [ " + preparedStatement.getPreparedSql() + " ] schema:=" + schemaName + " table:=" + tableName);
			}
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e);
			throw new DbexException(null, e.getMessage());
		} finally {
			//JdbcUtil.close(connection);
		}
		if(logger.isDebugEnabled()){
			logger.debug("Exit:: getLimitedResultset()");
		}
		return resultSet;
	}
	
	public Set<Constraint> getAllConstraints(ConnectionProperties connectionProperties,
			String schemaName, String tableName, ReadDepthEnum readDepthEnum) throws DbexException {
		
		return null;
	}

	@Override
	public List<String> getSystemFunctions(
			ConnectionProperties connectionProperties) throws DbexException {
		DatabaseMetadataIntegration metadataIntegration=IntegrationBeanFactory.getBeanFactory().getGenericMetadataIntegration();
		if(null != metadataIntegration){
			return metadataIntegration.getSystemFunctions(connectionProperties);
		}
		return new ArrayList<String>();
	}
	@Override
	public List<String> getNumericFunctions(
			ConnectionProperties connectionProperties) throws DbexException {
		DatabaseMetadataIntegration metadataIntegration=IntegrationBeanFactory.getBeanFactory().getGenericMetadataIntegration();
		if(null != metadataIntegration){
			return metadataIntegration.getNumericFunctions(connectionProperties);
		}
		return new ArrayList<String>();
	}
	@Override
	public List<String> getStringFunctions(
			ConnectionProperties connectionProperties) throws DbexException {
		DatabaseMetadataIntegration metadataIntegration=IntegrationBeanFactory.getBeanFactory().getGenericMetadataIntegration();
		if(null != metadataIntegration){
			return metadataIntegration.getStringFunctions(connectionProperties);
		}
		return new ArrayList<String>();
	}
	@Override
	public List<String> getTimeDateFunctions(
			ConnectionProperties connectionProperties) throws DbexException {
		DatabaseMetadataIntegration metadataIntegration=IntegrationBeanFactory.getBeanFactory().getGenericMetadataIntegration();
		if(null != metadataIntegration){
			return metadataIntegration.getTimeDateFunctions(connectionProperties);
		}
		return new ArrayList<String>();
	}

	
}
