/**
 * 
 */
package com.gs.dbex.core.mysql;

/**
 * @author Sabuj.das
 * 
 */
public final class MysqlMetaQueryConstants {

	public static final String GET_ALL_TABLE_NAMES = "SELECT * FROM INFORMATION_SCHEMA.TABLES ORDER BY TABLE_SCHEMA, TABLE_NAME";
	public static final String GET_ALL_TABLE_NAMES_BY_SCHEMA_SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=? ORDER BY TABLE_NAME";
	public static final String GET_ALL_COLUMNS_FOR_TABLE_QUERY = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=? AND TABLE_NAME=? ORDER BY TABLE_NAME, ORDINAL_POSITION";

	public static final String GET_TABLE_QUERY = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=? AND TABLE_NAME=? ORDER BY TABLE_NAME";

	public static final String GET_TABLE_NAMES_SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA LIKE ? AND TABLE_NAME LIKE ? ORDER BY TABLE_NAME";
	public static final String GET_TABLE_SQL = "SELECT * "
			+ "FROM INFORMATION_SCHEMA.TABLES T, INFORMATION_SCHEMA.COLUMNS C "
			+ "WHERE T.TABLE_SCHEMA LIKE ? AND T.TABLE_NAME LIKE ? AND T.TABLE_NAME = C.TABLE_NAME AND T.TABLE_SCHEMA = C.TABLE_SCHEMA  ORDER BY TABLE_NAME";

	public static final String GET_ALL_SCHEMA_NAMES_SQL = "SELECT * FROM INFORMATION_SCHEMA.SCHEMATA ORDER BY SCHEMA_NAME";

	public static final String GET_COLUMN_LIST_SQL = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ? ORDER BY ORDINAL_POSITION";

	public static final String GET_ALL_CONSTRAINTS_SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_SCHEMA = ? AND TABLE_SCHEMA = ? AND TABLE_NAME = ? ORDER BY CONSTRAINT_NAME";
}
