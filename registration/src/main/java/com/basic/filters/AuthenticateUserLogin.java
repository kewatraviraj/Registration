package com.basic.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.basic.database.Database;

/**
 * Servlet Filter implementation class Authenticate
 */
public class AuthenticateUserLogin implements Filter {
	 private ServletContext context;
    /**
     * Default constructor. 
     */
    public AuthenticateUserLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		Properties prop=new Properties();
	    InputStream input = Database.class.getClassLoader().getResourceAsStream("messages.properties");
	    prop.load(input);
	    input.close();
	    
		HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession(false);

        if (session.getAttribute("user") != null) {   //checking whether the session exists
        	 chain.doFilter(request, response);
        } else {
        	req.setAttribute("message", prop.getProperty("sessionexpire"));
        	req.getRequestDispatcher("index.jsp").forward(request, response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		 this.context = fConfig.getServletContext();
	     this.context.log("AuthenticationFilter initialized");
	}

}
