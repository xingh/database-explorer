package com.gs.dbex.integration.impl.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.gs.dbex.common.enums.ReadDepthEnum;
import com.gs.dbex.common.exception.DbexException;
import com.gs.dbex.common.exception.ErrorCodeConstants;
import com.gs.dbex.core.CatalogGrabber;
import com.gs.dbex.core.mysql.MysqlMetaQueryConstants;
import com.gs.dbex.integration.impl.DatabaseMetadataIntegrationImpl;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.db.Database;
import com.gs.dbex.model.db.Schema;
import com.gs.dbex.model.db.Table;
import com.gs.utils.jdbc.JdbcUtil;

/**
 * @author Sabuj Das
 *
 */
public class SqlServerDatabaseMetadataIntegrationImpl extends
		DatabaseMetadataIntegrationImpl {

	private static Logger logger = Logger.getLogger(SqlServerDatabaseMetadataIntegrationImpl.class);
	
	private CatalogGrabber dbGrabber;
	
	public CatalogGrabber getDbGrabber() {
		return dbGrabber;
	}

	public void setDbGrabber(CatalogGrabber dbGrabber) {
		this.dbGrabber = dbGrabber;
	}

	public Database readDatabase(ConnectionProperties connectionProperties,
			ReadDepthEnum readDepthEnum) throws DbexException {
		logger.debug("START:: Reading Full database.");
		if(connectionProperties == null){
			throw new DbexException(ErrorCodeConstants.CANNOT_CONNECT_DB);
		}
		Connection connection = null; 
		Database database = null;
		try {
			connection = connectionProperties.getDataSource().getConnection();
			if(dbGrabber != null)
				database = dbGrabber.grabDatabaseByCatalog(connection, connectionProperties.getDatabaseConfiguration().getSchemaName(), readDepthEnum);
		} catch (SQLException e) {
			logger.error(e);
			throw new DbexException(null, e.getMessage());
		} finally {
			JdbcUtil.close(connection);
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
		Connection connection = null; 
		Table table = null;
		try {
			connection = connectionProperties.getDataSource().getConnection();
			if(dbGrabber != null)
				table = dbGrabber.grabTable(connection, schemaName, tableName, readDepthEnum);
		} catch (SQLException e) {
			logger.error(e);
			throw new DbexException(null, e.getMessage());
		} finally {
			JdbcUtil.close(connection);
		}
		logger.debug("END:: readTable()");
		return table;
	}

	@Override
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
				logger.debug("Executing SQL: [ " + MysqlMetaQueryConstants.GET_ALL_CONSTRAINTS_SQL + " ] schema:=" + schemaName + " table:=" + tableName);
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


}
