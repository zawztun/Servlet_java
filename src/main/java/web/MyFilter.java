package web;

import jakarta.servlet.Filter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class MyFilter
 */
public class MyFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
       
  
    public void init(FilterConfig fConfig) throws ServletException {
	
    	this.context = fConfig.getServletContext();
    	this.context.log("Middle ware start and  init");
	}
    
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		String user = request.getParameter("txt_uid");
		String pswd = request.getParameter("txt_pswd");
		PrintWriter out = response.getWriter();
		
		if(user == null || pswd == null) {
			chain.doFilter(request, response);
		}
		
		else if(user.equals("zawtun")&& pswd.equals("1234")) {
			Cookie c = new Cookie("user", user);
		
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setContentType("text/html");
			httpResponse.addCookie(c);
		
			System.out.println(user + " \n" + pswd + "\n");
			out.println("<p style=\"color:Blue;font-size:50px;text-align:center\"> " + ( "\n" +" SUCCESS"));
		}else {
			response.setContentType("text/html");
   			out.println("<p style=\"color:Red;font-size:30px;text-align:center\"> " + ("\n" +"404 ERROR"));
		}

		// pass the request along the filter chain
		
	}


	public void destroy() {
		// TODO Auto-generated method stub
	}

}
