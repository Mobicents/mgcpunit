package org.mobicents.commtesting.mgcpUnit.utils;

import org.mobicents.media.server.utils.Text;

/**
 * Enum for the event types
 * 
 * @author <a href="mailto:gvagenas@gmail.com">gvagenas@gmail.com</a>
 */

public class EventTypes {

    public final static Text CREATE_CONNECTION = new Text("CRCX");
    public final static Text MODIFY_CONNECTION = new Text("MDCX");
    public final static Text DELETE_CONNECTION = new Text("DLCX");
    public final static Text REQUEST_NOTIFICATION = new Text("RQNT");
    public final static Text REQUEST_NOTIFY = new Text("NTFY");
    public final static Text ENDPOINT_CONFIGURATION = new Text("EPCF");
	
}
