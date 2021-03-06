/**
 * 
 */
package com.gs.dbex.design;

import org.apache.log4j.Logger;

import com.gs.dbex.common.enums.DesignDepthEnum;

/**
 * @author Sabuj.das
 *
 */
public final class DbexDesignContext {

	private static final Logger logger = Logger.getLogger(DbexDesignContext.class);
	
	private static DbexDesignContext instance;
	
	private DbexDesignContext() {
		if(logger.isDebugEnabled()){
			logger.debug("Initializing DbexDesignContext !!!");
		}
	}

	public static DbexDesignContext getInstance() {
		if(null == instance)
			instance = new DbexDesignContext();
		return instance;
	}
	
	private int zoomScale = DbexDesignConstants.DEFAULT_SCALE;
	public int getZoomScale() {
		return zoomScale;
	}
	public void setZoomScale(int zoomScale) {
		this.zoomScale = zoomScale;
	}
	
	public DesignDepthEnum designDepthEnum = DesignDepthEnum.NAME_ONLY;
	
	
}
