/**
 * 
 */
package com.gs.dbex.historyMgr;

import java.util.List;

import com.gs.dbex.common.exception.DbexException;
import com.gs.dbex.model.cfg.ConnectionProperties;
import com.gs.dbex.model.cfg.JdbcDriverConfiguration;
import com.gs.dbex.model.syntax.StyleConfiguration;

/**
 * @author sabuj.das
 *
 */
public interface ApplicationDataHistoryMgr {

	public List<ConnectionProperties> loadAllConnectionProperties();
	
	public List<ConnectionProperties> loadAllConnectionProperties(String fileName);
	
	public ConnectionProperties getConnectionProperties(String connectionName);
	
	public boolean saveAllConnectionProperties(List<ConnectionProperties> connectionPropertiesList);
	
	public boolean saveAllConnectionProperties(List<ConnectionProperties> connectionPropertiesList, String fileName);
	
	public List<JdbcDriverConfiguration> getAllJdbcDriverConfiguration();
	
	public List<JdbcDriverConfiguration> getAllJdbcDriverConfiguration(String fileName);
	
	public boolean saveAllJdbcDriverConfiguration(List<JdbcDriverConfiguration> driverConfigurations);
	
	public boolean saveAllJdbcDriverConfiguration(List<JdbcDriverConfiguration> driverConfigurations, String fileName);
	
	public StyleConfiguration getStyleConfiguration(String styleConfigFileName) throws DbexException;
	
	public Boolean saveStyleConfiguration(StyleConfiguration styleConfiguration, String styleConfigFileName) throws DbexException;
}
