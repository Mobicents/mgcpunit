package org.mobicents.commtesting.mgcpUnit.requests;

import org.mobicents.arquillian.mediaserver.api.MgcpUnitRequest;
import org.mobicents.arquillian.mediaserver.api.MgcpUnitRequestType;
import org.mobicents.media.server.mgcp.message.MgcpRequest;
import org.mobicents.media.server.mgcp.message.Parameter;
import org.mobicents.media.server.utils.Text;

/**
 * Represents a Notify Mgcp request
 * 
 * @author <a href="mailto:gvagenas@gmail.com">gvagenas@gmail.com</a>
 */

public class NotifyRequest implements MgcpUnitRequest {

	/*
	 * NTFY 147483669 mobicents/ivr/1@127.0.0.1:2427 MGCP 1.0
	 * N: restcomm@127.0.0.1:2727
	 * O: AU/oc(rc=100)
     * X: 1
	 */
	
	private MgcpRequest request;
	private String returnCode;
	private MgcpUnitRequestType type;

	public NotifyRequest(MgcpRequest request) {
		this.setRequest(request);
		setType(MgcpUnitRequestType.NotifyRequestType);
	}

	public MgcpRequest getRequest() {
		return request;
	}

	public void setRequest(MgcpRequest request) {
		this.request = request;
	}

	@Override
	public void parseRequest(){
		Parameter auParam = request.getParameter(Parameter.REASON_CODE);
		Text value = auParam.getValue();
		setReturnCode(value.toString());
	}


	public MgcpUnitRequestType getType() {
		return type;
	}

	public void setType(MgcpUnitRequestType type) {
		this.type = type;
	}

	@Override
	public int getTxId() {
		return request.getTxID();
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

}
