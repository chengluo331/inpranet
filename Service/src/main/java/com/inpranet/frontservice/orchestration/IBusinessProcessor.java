package com.inpranet.frontservice.orchestration;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.User;


public interface IBusinessProcessor {

	public void receiveRawPositions(final User user, final GeoPos pos);
	//public Collection<Document> receiveDocumentOrder(final User user);
}
