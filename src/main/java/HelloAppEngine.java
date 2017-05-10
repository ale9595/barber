import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import java.util.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAppEngine extends HttpServlet {
    public static LinkedList n_queue = new LinkedList();
    public LinkedList c_queue = new LinkedList();
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  	
	  	String name = request.getParameter("name");
		String cell = request.getParameter("cell");

		response.setContentType("text/html");
		printHeader(response);

	    if (request.getParameter("remove") == null && ((name!=null && !name.isEmpty()) && (cell!=null && !cell.isEmpty()))){
	    	addtoQueue(name,cell);
	    	String link = createlink();
	    	printBody(name, link, response);
	    }
	    
	    if (request.getParameter("remove") == null && ((name==null || name.isEmpty()) || cell==null || cell.isEmpty())){
	    	printEmpty(response);
	    }
	    
	    
	    if (request.getParameter("remove") != null && n_queue.size() != 0) {
	    	removeFirst();
	    	String link = createlink();
	    	printBye(name, link, response);
	    }
	    
	    if (request.getParameter("remove") != null && n_queue.size() == 0) {
	    	printError(response);
	    }
	    

	    printFooter(response);
	  }
	  
	  public void addtoQueue(String name, String cell) {
		  n_queue.add(name);
		  c_queue.add(cell);
	  }
	  
	  public void removeFirst(){
		  n_queue.removeFirst();
		  c_queue.removeFirst();
	  }
	  
	  public String createlink(){
		  String name = (String) n_queue.getFirst();
		  String email = (String) c_queue.getFirst();
		  String print = ("<a href=\"mailto:"+email+"?"+"subject="+name+"%20your%20turn%20in%20line%20has%20come!&body=Come%20to%20the%20hair%20cutter%20in%20the%20next%2010%20minutes%20to%20avoid%20losing%20your%20spot%20in%20line.\">Alert the next person in line.</a>");
		  return print;
	  }
	  
	  public static void printHeader(HttpServletResponse resp) throws IOException {
			resp.getWriter().println("<html>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("<pre>");
												
	  }
	  
	  public static void printFooter(HttpServletResponse resp) throws IOException {
			resp.getWriter().println("</pre>");
			resp.getWriter().println("</body>");
			resp.getWriter().println("</html>");
		}
	  
	  public static void printBody(String name, String link, HttpServletResponse resp) throws IOException {
	    	resp.getWriter().println("The queue is displayed below, "+name+" was just added.\n");	
	    	resp.getWriter().println(n_queue);
			resp.getWriter().println();
			resp.getWriter().println("<a href=\"index.html\">Back</a>");
		}
	  
	  public static void printBye(String name, String link, HttpServletResponse resp) throws IOException {
	    	resp.getWriter().println("The queue is displayed below, you just removed the first customer. Please alert the next in line by clicking the link below.\n");	
	    	resp.getWriter().println(n_queue);
			resp.getWriter().println();
			resp.getWriter().println(link);
			resp.getWriter().println();
			resp.getWriter().println("<a href=\"index.html\">Back</a>");
		}
	  
	  public static void printError(HttpServletResponse resp) throws IOException {
	    	resp.getWriter().println("Please add a customer to the queue first. Cannot remove customer from empty queue.");	
			resp.getWriter().println("<a href=\"index.html\">Back</a>");
		}
	  
	  public static void printEmpty(HttpServletResponse resp) throws IOException {
	    	resp.getWriter().println("Either your customer name or email was empty. Please go back and try again.");	
			resp.getWriter().println("<a href=\"index.html\">Back</a>");
		}
}

//problems I have:
//clicking remove first doesn't redirect to /bye