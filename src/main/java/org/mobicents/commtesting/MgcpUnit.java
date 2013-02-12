package org.mobicents.commtesting;

import org.apache.log4j.Logger;
import org.mobicents.arquillian.mediaserver.api.MgcpEventListener;

/**
 * @author <a href="mailto:gvagenas@gmail.com">gvagenas</a>
 */

public class MgcpUnit {

	private Logger logger = Logger.getLogger(MgcpUnit.class);
	
	private MgcpEventListener mgcpEventListener;
	
	public MgcpUnit() {
		mgcpEventListener = new MgcpEventListenerImpl();
		logger.debug("New MgcpUnit instantiated");
	}
	
	public MgcpEventListener getMgcpEventListener(){
		return mgcpEventListener;
	}
	
}
