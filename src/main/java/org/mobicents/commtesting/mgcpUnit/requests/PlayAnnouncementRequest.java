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

//	RQNT 15 mobicents/ivr/1@127.0.0.1:2427 MGCP 1.0
//	N: restcomm@127.0.0.1:2727
//	R: AU/oc(N),AU/of(N)
//	X: 1
//	S: AU/pa(an=file:///data/devWorkspace/eclipse/localWorkspace/restcomm/restcomm.testsuite/CompatibilityTests/target/mss-tomcat-embedded-6/webapps/restcomm.core-1.0.0.CR1-SNAPSHOT/cache/ttsapi/af4815f86dfed595dbb1911982ea0a945a5117d82b4be0457af3be1b492f2e91.wav it=1)

	
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
