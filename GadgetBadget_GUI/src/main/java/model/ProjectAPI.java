 package model;

 import java.io.IOException;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 import javax.servlet.ServletException;
 import javax.servlet.annotation.WebServlet;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 /**
  * Servlet implementation class OrderAPI
  */
 @WebServlet("/ProjectAPI")
 public class ProjectAPI extends HttpServlet {
 	private static final long serialVersionUID = 1L;
        
 	Project projectObj = new Project();
     public ProjectAPI() {
         super();
         // TODO Auto-generated constructor stub
     }

 	/**
 	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 	 */
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		response.getWriter().append("Served at: ").append(request.getContextPath());
 	}

 	/**
 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 	 */
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		String outputString = projectObj.insertproject(
 				request.getParameter("projectCode"),
 				request.getParameter("projectTitle"),  
 				request.getParameter("projectManager"), 
 				request.getParameter("projectCategory")); 

 		response.getWriter().write(outputString);
 		
 		doGet(request, response);
 	}

 	/**
 	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
 	 */
 	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		Map paras = getParasMap(request);

 		String outputString = projectObj.updateproject(
 				paras.get("projectID").toString(),
 				paras.get("projectCode").toString(),
 				paras.get("projectTitle").toString(),
 				paras.get("projectManager").toString(),
 				paras.get("projectCategory").toString()); 

 		response.getWriter().write(outputString);
 	}

 	/**
 	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
 	 */
 	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		Map paras = getParasMap(request);
 		String output = projectObj.deleteproject(paras.get("projectID").toString());
 		response.getWriter().write(output); 
 	}

 	// Convert request parameters to a Map
 		private static Map getParasMap(HttpServletRequest request) {
 			
 			Map<String, String> map = new HashMap<String, String>();
 			
 			try {			
 				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
 				String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
 				scanner.close();
 				
 				String[] params = queryString.split("&");
 				for (String param : params) {
 					String[] p = param.split("=");
 					map.put(p[0], p[1]);
 				}
 				
 			} catch (Exception e) {
 			  }
 			
 			return map;
 		}
 }
