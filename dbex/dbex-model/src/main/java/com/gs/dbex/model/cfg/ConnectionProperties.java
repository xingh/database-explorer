/**
 * 
 */
package com.gs.dbex.model.cfg;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.sql.DataSource;

import com.gs.utils.common.FieldSpecificComparator;
import com.gs.utils.text.StringUtil;

/**
 * @author sabuj.das
 * 
 */
@Entity
@Table(
		name="DBEX_CONNECTION_PROPERTIES", 
		schema="dbex_configuration"
	)
public class ConnectionProperties implements Serializable,
		Comparable<ConnectionProperties> {

	/**
	 * serialVersionUID = 2717753646686919478L;
	 */
	private static final long serialVersionUID = 2717753646686919478L;

	public static final FieldSpecificComparator<ConnectionProperties, Integer>
		DISPLAY_ORDER_COMPARATOR = new FieldSpecificComparator<ConnectionProperties, Integer>("displayOrder");
	
	private Long connectionPropId;
	private Long userId;
	private String connectionName;
	private String databaseType;
	private String connectionUrl;
	private Integer displayOrder;
	private DatabaseConfiguration databaseConfiguration;
	
	private Integer versionNumber;

	private transient DataSource dataSource;
	private transient boolean propertySaved = true;

	
	@Id
	@Column(name="CONNECTION_PROP_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getConnectionPropId() {
		return connectionPropId;
	}

	public void setConnectionPropId(Long connectionPropId) {
		this.connectionPropId = connectionPropId;
	}
	
	@Column(name="USER_ID", nullable=false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ConnectionProperties() {
		this("UN-NAMED");
	}

	public ConnectionProperties(String connName) {
		this.connectionName = connName;
		databaseConfiguration = new DatabaseConfiguration();
	}

	public int compareTo(ConnectionProperties o) {
		return connectionName.compareTo(o.getConnectionName());
	}

	protected void finalize() throws Throwable {
		super.finalize();
		dataSource = null;
	}

	@Transient
	public boolean isPropertySaved() {
		return propertySaved;
	}

	public void setPropertySaved(boolean propertySaved) {
		this.propertySaved = propertySaved;
	}

	/**
	 * @return the connectionName
	 */
	public String getConnectionName() {
		return connectionName;
	}

	/**
	 * @param connectionName
	 *            the connectionName to set
	 */
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	/**
	 * @return the connectionUrl
	 */
	public String getConnectionUrl() {
		return connectionUrl;
	}

	/**
	 * @param connectionUrl
	 *            the connectionUrl to set
	 */
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	/**
	 * @return the databaseType
	 */
	public String getDatabaseType() {
		return databaseType;
	}

	/**
	 * @param databaseType
	 *            the databaseType to set
	 */
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	/**
	 * @return the dataSource
	 */
	@Transient
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(columnDefinition="CONNECTION_PROP_ID")
	//@JoinTable(name = "DBEX_DATABASE_CONFIGURATION",
		//	joinColumns = @JoinColumn(name="CONNECTION_PROP_ID")
			//, inverseJoinColumns = @JoinColumn(name="passport_fk")
	//)
	//@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	/*@OneToOne(targetEntity=DatabaseConfiguration.class,
			//mappedBy="CONNECTION_PROP_ID",
			cascade=CascadeType.ALL, 
			fetch=FetchType.EAGER)
    @JoinColumn(referencedColumnName="CONNECTION_PROP_ID")*/
	
	
	@OneToOne(targetEntity=DatabaseConfiguration.class, cascade=CascadeType.ALL)
	@JoinColumn(name="CONNECTION_PROP_ID")
	public DatabaseConfiguration getDatabaseConfiguration() {
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(
			DatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}
	
	@Version
	@Column(name="VERSION_NUMBER")
	public Integer getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}

	
	@Override
	public String toString() {
		return (StringUtil.hasValidContent(getConnectionName())) ? getConnectionName()
				: "UN-NAMED";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((connectionName == null) ? 0 : connectionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ConnectionProperties)) {
			return false;
		}
		ConnectionProperties other = (ConnectionProperties) obj;
		if (connectionName == null) {
			if (other.connectionName != null) {
				return false;
			}
		} else if (!connectionName.equals(other.connectionName)) {
			return false;
		}
		return true;
	}

	public ConnectionProperties copyAll(){
		ConnectionProperties p1 = new ConnectionProperties();
		p1.setConnectionName(getConnectionName());
		p1.setConnectionPropId(null);
		p1.setConnectionUrl(getConnectionUrl());
		p1.setDatabaseConfiguration(getDatabaseConfiguration().copyAll());
		p1.setDatabaseType(getDatabaseType());
		p1.setDisplayOrder(getDisplayOrder());
		p1.setPropertySaved(false);
		p1.setUserId(getUserId());
		p1.setVersionNumber(0);
		
		return p1;
	}
}
