package org.mobicents.commtesting.mgcpUnit.requests;

import java.util.Collection;
import java.util.Iterator;

import org.mobicents.arquillian.mediaserver.api.MgcpUnitRequest;
import org.mobicents.arquillian.mediaserver.api.MgcpUnitRequestType;
import org.mobicents.media.server.mgcp.message.MgcpRequest;
import org.mobicents.media.server.mgcp.message.Parameter;
import org.mobicents.media.server.utils.Text;

/**
 * @author <a href="mailto:gvagenas@gmail.com">gvagenas</a>
 */

public class PlayAnnouncementRequest implements MgcpUnitRequest {

	private MgcpRequest request;
	private String file;
	private String iterations;
	private MgcpUnitRequestType type;

	public PlayAnnouncementRequest(MgcpRequest request) {
		this.setRequest(request);
		setType(MgcpUnitRequestType.PlayAnnouncementRequestType);
	}

	public MgcpRequest getRequest() {
		return request;
	}

	public void setRequest(MgcpRequest request) {
		this.request = request;
	}

	@Override
	public void parseRequest(){

		Parameter auParam = request.getParameter(Parameter.REQUESTED_SIGNALS);
		Text value = auParam.getValue();

		Collection<Text> texts = value.split(' ');
		for (Iterator<Text> iterator = texts.iterator(); iterator.hasNext();) {
			Text text = iterator.next();
			if(text.toString().startsWith("file:")){
				file = text.toString();
			} else if (text.toString().startsWith("it")){
				iterations = text.toString();
			}
		}
	}
	
	public String getAnnouncementFile(){
		return file;
	}

	public String getIterations(){
		return iterations;
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
	
}
