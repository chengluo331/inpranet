package com.inpranet.frontservice.service;


import com.inpranet.core.model.GeoPos;


public interface IFrontService {

	void postGeoPos(/*String id,*/ GeoPos pos);
	
	//public Collection<Document> getDocumentList(/*String id*/);
	
	public GeoPos  testJSON();
	
}
