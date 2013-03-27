package org.mobicents.commtesting;

import org.apache.log4j.Logger;
import org.mobicents.arquillian.mediaserver.api.MgcpEventListener;

/**
 * The main MgcpUnit class. 
 * 
 * Use an instance of this class in order to get MgcpEventListener and register it with an embedded Mediaserver.
 * 
 * @author <a href="mailto:gvagenas@gmail.com">gvagenas@gmail.com</a>
 */

public class MgcpUnit {

	private Logger logger = Logger.getLogger(MgcpUnit.class);
	
	private MgcpEventListener mgcpEventListener;
	
	public MgcpUnit() {
		mgcpEventListener = new MgcpEventListenerImpl();
		logger.debug("New MgcpUnit instantiated");
	}
	
	/**
	 * 
	 * @return MgcpEventListener
	 */
	public MgcpEventListener getMgcpEventListener(){
		return mgcpEventListener;
	}
	
}
