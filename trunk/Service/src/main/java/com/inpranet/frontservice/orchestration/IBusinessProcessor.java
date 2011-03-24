package com.inpranet.frontservice.orchestration;

import com.inpranet.habit.service.GeoPos;
import com.inpranet.habit.service.User;


public interface IBusinessProcessor {

	public void receiveRawPositions(final User user, final GeoPos pos);
	//public Collection<Document> receiveDocumentOrder(final User user);
}
