package com.inpranet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/*")
public class HomePageController {
 
 
	@RequestMapping(method=RequestMethod.POST)
	 public void retourneRep(HttpServletRequest request, HttpServletResponse response) { 
		try {
			String content = IOUtils.toString(request.getInputStream());
			
			
			
			 response.sendError(HttpServletResponse.SC_OK, "lol");

		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}