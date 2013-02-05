package org.mobicents.commtesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.mobicents.arquillian.mediaserver.api.MgcpEventListener;
import org.mobicents.arquillian.mediaserver.api.MgcpUnitRequest;
import org.mobicents.arquillian.mediaserver.api.MgcpUnitRequestType;
import org.mobicents.commtesting.mgcpUnit.requests.PlayAnnouncementRequest;
import org.mobicents.commtesting.mgcpUnit.utils.EventTypes;
import org.mobicents.media.server.mgcp.MgcpEvent;
import org.mobicents.media.server.mgcp.controller.signal.Signal;
import org.mobicents.media.server.mgcp.message.MgcpMessage;
import org.mobicents.media.server.mgcp.message.MgcpRequest;
import org.mobicents.media.server.mgcp.message.MgcpResponse;
import org.mobicents.media.server.mgcp.message.Parameter;
import org.mobicents.media.server.mgcp.tx.cmd.CreateConnectionCmd;
import org.mobicents.media.server.mgcp.tx.cmd.DeleteConnectionCmd;
import org.mobicents.media.server.mgcp.tx.cmd.EndpointConfigurationCmd;
import org.mobicents.media.server.mgcp.tx.cmd.ModifyConnectionCmd;
import org.mobicents.media.server.mgcp.tx.cmd.NotificationRequestCmd;
import org.mobicents.media.server.mgcp.tx.cmd.NotifyCmd;
import org.mobicents.media.server.spi.listener.Event;
import org.mobicents.media.server.utils.Text;

/**
 * @author <a href="mailto:gvagenas@gmail.com">gvagenas</a>
 */

public class MgcpEventListenerImpl implements MgcpEventListener {

	private Logger logger = Logger.getLogger(MgcpEventListenerImpl.class);

	private ArrayList<MgcpRequest> requests = new ArrayList<MgcpRequest>();
	private ArrayList<MgcpResponse> responses = new ArrayList<MgcpResponse>();
	private ArrayList<MgcpUnitRequest> playAnnouncementRequests = new ArrayList<MgcpUnitRequest>();

	private MgcpRequest lastRequestMessage;
	private MgcpResponse lastResponseMessage;

	@Override
	public void process(Event event) {
		MgcpEvent mgcpEvent = (MgcpEvent)event;
		MgcpMessage message = mgcpEvent.getMessage();

		if(mgcpEvent.getEventID() == MgcpEvent.REQUEST){
			MgcpRequest req = (MgcpRequest)message;
			requests.add(req);
			setLastRequestMessage(req);
			classifyEvent(mgcpEvent);
		} else {
			MgcpResponse resp = (MgcpResponse)message;
			responses.add(resp);
			setLastResponseMessage(resp);
		}

		logger.debug("New dispatched eventId: "+message.getTxID()+" message: "+mgcpEvent.getMessage());
	}

	private void classifyEvent(MgcpEvent event) {

		MgcpRequest request;
		Text command;

		switch (event.getEventID()) {
		case MgcpEvent.REQUEST :
			request = (MgcpRequest) event.getMessage();
			command = request.getCommand();

			//select action using message type and execute action
			if (command.equals(EventTypes.CREATE_CONNECTION)) {
//				return crcx;
			} else if (command.equals(EventTypes.MODIFY_CONNECTION)) {
//				return mdcx;
			} else if (command.equals(EventTypes.ENDPOINT_CONFIGURATION)) {
//				return epcf;
			} else if (command.equals(EventTypes.DELETE_CONNECTION)) {
				if(request.getParameter(Parameter.REASON_CODE)!=null) {
					//its connection deletion from ms
//					return ntfy;	
				}
				else {
					//its delete connection request
//					return dlcx;
				}
			} else if (command.equals(EventTypes.REQUEST_NOTIFICATION)) {
				Parameter param = request.getParameter(Parameter.REQUESTED_SIGNALS);
				if (param.getValue().toString().contains("AU/pa")) {
					playAnnouncementRequests.add(new PlayAnnouncementRequest(request));
				}
					
			} else if (command.equals(EventTypes.REQUEST_NOTIFY)) {
//				return ntfy;
			}

			break;
		case MgcpEvent.RESPONSE :
			break;
		}
	}
	
	@Override
	public Collection<MgcpUnitRequest> getPlayAnnoRequestsReceived(){
		if (!playAnnouncementRequests.isEmpty()){
			return playAnnouncementRequests;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean checkForSuccessfulResponse(int txId){

		for (Iterator MgcpResponsesIter = responses.iterator(); MgcpResponsesIter.hasNext();) {
			MgcpResponse resp = (MgcpResponse) MgcpResponsesIter.next();
			if(resp.getTxID()==txId && resp.getResponseCode()==200)
				return true;
		}
		return false;
	}

	public ArrayList<MgcpRequest> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<MgcpRequest> requests) {
		this.requests = requests;
	}


	public ArrayList<MgcpResponse> getResponses() {
		return responses;
	}


	public void setResponses(ArrayList<MgcpResponse> responses) {
		this.responses = responses;
	}


	public MgcpRequest getLastRequestMessage() {
		return lastRequestMessage;
	}


	public void setLastRequestMessage(MgcpRequest lastRequestMessage) {
		this.lastRequestMessage = lastRequestMessage;
	}


	public MgcpResponse getLastResponseMessage() {
		return lastResponseMessage;
	}


	public void setLastResponseMessage(MgcpResponse lastResponseMessage) {
		this.lastResponseMessage = lastResponseMessage;
	}

}
