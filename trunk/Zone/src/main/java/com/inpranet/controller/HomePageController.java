package com.inpranet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomePageController {
 
 
	@RequestMapping(value = "/*")
	 public void retourneRep(HttpServletRequest request, HttpServletResponse response) { 
		try {
			String content = IOUtils.toString(request.getInputStream());
			
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}