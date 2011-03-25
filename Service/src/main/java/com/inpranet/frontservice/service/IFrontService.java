package com.inpranet.frontservice.service;


import java.util.Collection;

import com.inpranet.core.model.Document;
import com.inpranet.habit.service.GeoPos;


public interface IFrontService {

	void postGeoPos(/*String id,*/ GeoPos pos);
	
	public Collection<Document> getDocumentList(/*String id*/);
	
	public GeoPos  testJSON();
	
}
