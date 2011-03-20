package com.inpranet.habit.service;

import javax.xml.ws.Endpoint;

public class HabitServicePublisher {
	public static void main (String[] args) {
		Endpoint.publish("http://localhost/9999/HabitService", new HabitServiceImp());
	}
}
