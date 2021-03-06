/**
 * 
 */
package com.gs.dbex.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.gs.dbex.common.enums.ReadDepthEnum;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.db.Column;
import com.gs.dbex.model.db.Database;
import com.gs.dbex.model.db.ForeignKey;
import com.gs.dbex.model.db.PrimaryKey;
import com.gs.dbex.model.db.Schema;
import com.gs.dbex.model.db.Table;

/**
 * @author sabuj.das
 * 
 */
public interface SchemaGrabber extends DbGrabber {

	public Database grabDatabaseBySchema(ConnectionProperties connectionProperties,
			String databaseName, ReadDepthEnum readDepth) throws SQLException;

	public List<Schema> grabSchema(ConnectionProperties connectionProperties, ReadDepthEnum readDepth)
			throws SQLException;
	
	public Schema grabSchema(ConnectionProperties connectionProperties, String schemaName, ReadDepthEnum readDepth)
			throws SQLException;

	public Set<String> getAvailableSchemaNames(ConnectionProperties connectionProperties)
			throws SQLException;
	
	public Table grabTable(ConnectionProperties connectionProperties, String schemaName,
			String tableName, ReadDepthEnum readDepth) throws SQLException;

	public List<Column> getColumnList(ConnectionProperties connectionProperties, 
			Table table,
			ReadDepthEnum readDepth) throws SQLException;

	public List<Column> getColumnList(ConnectionProperties connectionProperties,
			String tableName, ReadDepthEnum readDepth)
			throws SQLException;

	public List<PrimaryKey> grabPrimaryKeys(ConnectionProperties connectionProperties,
			String schemaName, String tableName, ReadDepthEnum readDepth)
			throws SQLException;

	public List<ForeignKey> grabImportedKeys(ConnectionProperties connectionProperties,
			Table table, ReadDepthEnum readDepth)
			throws SQLException;

	public List<ForeignKey> grabExportedKeys(ConnectionProperties connectionProperties,
			Table table, ReadDepthEnum readDepth)
			throws SQLException;
}
