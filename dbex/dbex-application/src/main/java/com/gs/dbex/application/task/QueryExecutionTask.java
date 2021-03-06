package com.gs.dbex.application.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.gs.dbex.common.enums.QueryTypeEnum;
import com.gs.dbex.common.exception.DbexException;
import com.gs.dbex.core.Transaction;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.sql.SqlQuery;
import com.gs.dbex.service.DbexServiceBeanFactory;
import com.gs.dbex.service.QueryExecutionService;
import com.gs.utils.jdbc.ResultSetDataTable;

public class QueryExecutionTask extends
		SwingWorker<ResultSetDataTable, SqlQuery> implements WorkerTaskConstants{

	
	private static final Logger LOGGER = Logger.getLogger(QueryExecutionTask.class);
	
	
	private SqlQuery sqlQuery;
	private ConnectionProperties connectionProperties;
	private QueryExecutionService queryExecutionService;
	private Transaction<
		? extends Connection, 
		? extends Statement, 
		? extends PreparedStatement, 
		? extends ResultSet> currentTransaction;
	private ResultSetDataTable resultSetDataTable;
	private String catalogName;

	public QueryExecutionTask(ConnectionProperties connectionProperties, SqlQuery sqlQuery) {
		this.connectionProperties = connectionProperties;
		this.sqlQuery = sqlQuery;
		this.queryExecutionService = DbexServiceBeanFactory.getBeanFactory()
				.getQueryExecutionService();
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public SqlQuery getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(SqlQuery sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public QueryExecutionService getQueryExecutionService() {
		return queryExecutionService;
	}

	public void setQueryExecutionService(
			QueryExecutionService queryExecutionService) {
		this.queryExecutionService = queryExecutionService;
	}
	
	public ResultSetDataTable getResultSetDataTable() {
		return resultSetDataTable;
	}

	@Override
	protected void process(List<SqlQuery> chunks) {

	}

	@Override
	protected ResultSetDataTable doInBackground() throws Exception {
		Long startTime = System.currentTimeMillis();
		Long totalTime = 0L;
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Query execution task STARTED @ " + new Date());
		}
		firePropertyChange(PROPERTY_PROGRESS, null, TASK_STATUS_START);
		try{
			currentTransaction = getQueryExecutionService().createTransaction(connectionProperties);
			currentTransaction.setCatalogName(catalogName);
			int rows = 0;
			if (null != currentTransaction) {
				if(QueryTypeEnum.SELECT.equals(sqlQuery.getQueryType())){
					resultSetDataTable = getQueryExecutionService().executeQuery(connectionProperties, sqlQuery, currentTransaction);
				} else {
					rows = getQueryExecutionService().executeNonQuery(connectionProperties, sqlQuery, currentTransaction);
					currentTransaction.commit();
				}
			} else {
				firePropertyChange(TASK_STATUS_ABORT, null, "Cannot create transaction.");
			}
			Long endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Query execution task ENDED @ " + new Date() + ". Total Time taken = " + totalTime + "ms");
			}
			if(null != resultSetDataTable){
				firePropertyChange(TASK_STATUS_DONE, totalTime, resultSetDataTable);
			} else {
				firePropertyChange(TASK_STATUS_DONE, totalTime, new Integer(rows));
			}
		}catch(DbexException exception ){
			firePropertyChange(TASK_STATUS_FAILED, totalTime, exception);
		}catch(Exception exception ){
			firePropertyChange(TASK_STATUS_FAILED, totalTime, exception);
		}
		
		return resultSetDataTable;
	}

	@Override
	protected void done() {
		if (null != currentTransaction) {
			try {
				currentTransaction.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				firePropertyChange(TASK_STATUS_FAILED, null, e);
			}
			currentTransaction.close();
		}
		firePropertyChange(TASK_STATUS_DONE, null, null);
	}

	public void stop() {
		if (null != currentTransaction) {
			try {
				currentTransaction.abort();
			} catch (SQLException e) {
				e.printStackTrace();
				firePropertyChange(TASK_STATUS_FAILED, null, e);
			}
		}
		cancel(true);
		firePropertyChange(TASK_STATUS_ABORT, null, null);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

}
