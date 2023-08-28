package com.mgmoura.errors;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
	
	@Controller
	public class ErrorController {
		
		  @RequestMapping("/error404")
		    public void handleError404(HttpServletResponse response) throws IOException {
		        response.sendRedirect("http://localhost:4200");
		    }

}
