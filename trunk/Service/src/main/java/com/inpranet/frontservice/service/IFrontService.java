package com.inpranet.frontservice.service;

import com.inpranet.habit.service.GeoPos;


public interface IFrontService {

	void postGeoPos(/*String id,*/ GeoPos pos);
	
	void getDocumentList(String id);
	
	public GeoPos  testJSON();
	
}
