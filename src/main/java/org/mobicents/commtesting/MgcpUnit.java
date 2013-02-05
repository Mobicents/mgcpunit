package org.mobicents.commtesting;

import org.mobicents.arquillian.mediaserver.api.MgcpEventListener;

/**
 * @author <a href="mailto:gvagenas@gmail.com">gvagenas</a>
 */

public class MgcpUnit {

	private MgcpEventListener mgcpEventListener;
	
	public MgcpUnit() {
		mgcpEventListener = new MgcpEventListenerImpl();
	}
	
	public MgcpEventListener getMgcpEventListener(){
		return mgcpEventListener;
	}
	
}
