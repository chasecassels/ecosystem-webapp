package simple;

/* This class acts as the 'controller layer'
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

import org.tinylog.Logger;

public class EcozoneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EcozoneDao ecozoneDao;
    private DbUtils dbUtils;
    private Connection connection;
    
    
    public void init() {
        ecozoneDao = new EcozoneDao();
        dbUtils = new DbUtils();
    }
    
    /** calls doGet method
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	Logger.info("doPost Method for the EcozoneServlet");
        doGet(request, response);
    }
 
    /** doGet method. Calls methods based on action specified in 
     * the url path. Lists all ecozones with the listEcozone method by default
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	Logger.info("doGet Method for the EcozoneServlet");
        String action = request.getServletPath();

        try {
            switch (action) {
            case "/new":
            	Logger.info("adding new ecozone");
                showNewForm(request, response);
                break;
            case "/about":
            	Logger.info("viewing about page");
            	showAboutPage(request, response);
            //update or insert use same method
            case "/update":
            case "/insert":
            	Logger.info("saving new ecozone");
                insertOrUpdateEcozone(request, response);
                break;
            case "/delete":
            	Logger.info("deleting ecozone");
                deleteEcozone(request, response);
                break;
            case "/edit":
            	Logger.info("editing ecozone");
                showEditForm(request, response);
                break;
            case "/search":
            	Logger.info("searching ecozones");
                searchEcozones(request, response);
                break;
            case "/sort":
            	Logger.info("sorting ecozones");
                sortEcozones(request, response);
                break;
            case "/error":
            	showErrorPage(request, response);
            	break;
            case "/stats":
            	showStatsPage(request, response);
            	break;
            default:
                listEcozone(request, response);
                break;
            }
        } catch (Exception ex) {
        	throw new ServletException(ex);
        }
    }
    
    
    
    /** display about page
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     * @throws NamingException
     */
    private void showAboutPage(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException, IOException, ServletException, NamingException {
    	Logger.info("showAboutPage method for the EcozoneServlet");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/About.jsp");
        dispatcher.forward(request, response); 
    }
    
    /** display error page
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     * @throws NamingException
     */
    private void showErrorPage(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException, IOException, ServletException, NamingException {
    	Logger.info("showErrorPage method for the EcozoneServlet");
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Error.jsp");
        dispatcher.forward(request, response); 
    }
    
    /**
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     * @throws NamingException
     */
    private void showStatsPage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException, NamingException {
    	    	Logger.info("showEditForm Method for the EcozoneServlet");
    	    	try {
    		        String name = request.getParameter("name");
    		        connection = dbUtils.createConnection();
    		        Ecozone existingEcozone = ecozoneDao.selectEcozone(name, connection);
    		        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Ecozone-stats.jsp");
    		        request.setAttribute("ecozone", existingEcozone);
    		        dispatcher.forward(request, response);
    	    	} catch (Exception e){
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Error.jsp");
    	    		String error = "invalid zone";
    	    		request.setAttribute("error", error);
    		        dispatcher.forward(request, response);
    	    		Logger.error(e.getMessage());
    	    	} finally {
    	    		dbUtils.releaseConnection(connection);
    	    	}
    	    }  
    
    /** method to list all ecozones in the database
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     * @throws NamingException
     */
    private void listEcozone(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException, NamingException {
    	Logger.info("listEcozone Method for the EcozoneServlet");
    	try {  		
	    	connection = dbUtils.createConnection();
	        List < Ecozone > listEcozone = ecozoneDao.selectAllEcozones(connection);
	        request.setAttribute("listEcozone", listEcozone);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Ecozone-list.jsp");
	        dispatcher.forward(request, response);
    	} catch (Exception e){
    		System.err.println(e);
    	} finally {
    		dbUtils.releaseConnection(connection);
    	}
    }
    
    /** search method
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     * @throws NamingException
     */
    private void searchEcozones(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException, NamingException {
    	Logger.info("searchEcozones Method for the EcozoneServlet");
    	try {
	    	String input = request.getParameter("input");
	    	//allow search to match any instances of the given input, 
	    	//not only exact matches
	    	input = "%" + input + "%";
	    	connection = dbUtils.createConnection();
	        List < Ecozone > listEcozone = ecozoneDao.searchEcozones(input, connection);
	        request.setAttribute("listEcozone", listEcozone);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Ecozone-list.jsp");
	        dispatcher.forward(request, response);        
    	} catch (Exception e){
    		System.err.println(e);
    	} finally {
    		dbUtils.releaseConnection(connection);
    	}
    }
    
    /** sort method
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     * @throws NamingException
     */
    private void sortEcozones(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException, NamingException {
    	Logger.info("sortEcozones Method for the EcozoneServlet");
    	try {
	    	String column = request.getParameter("column");
	    	String order = request.getParameter("order");
	    	connection = dbUtils.createConnection();
	    	List < Ecozone > listEcozone = ecozoneDao.sortEcozones(column, order, connection);
	        request.setAttribute("listEcozone", listEcozone);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Ecozone-list.jsp");
	        dispatcher.forward(request, response);
    	} catch (Exception e){
    		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Error.jsp");
    		String error = e.getMessage();
    		request.setAttribute("error", error);
	        dispatcher.forward(request, response);
    		Logger.error(e.getMessage());
    	} finally {
    		dbUtils.releaseConnection(connection);
    	}
    }
    
    
    
    /** method to display form to add a new ecozone
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	Logger.info("showNewForm Method for the EcozoneServlet");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Ecozone-form.jsp");
        dispatcher.forward(request, response);        
    }
    
    
    
    /** method to display form to edit an existing ecozone
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * @throws NamingException
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, NamingException {
    	Logger.info("showEditForm Method for the EcozoneServlet");
    	try {
	        String name = request.getParameter("name");
	        connection = dbUtils.createConnection();
	        Ecozone existingEcozone = ecozoneDao.selectEcozone(name, connection);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Ecozone-form.jsp");
	        request.setAttribute("ecozone", existingEcozone);
	        dispatcher.forward(request, response);
    	} catch (Exception e){
    		System.err.println(e);
    	} finally {
    		dbUtils.releaseConnection(connection);
    	}
    }

    
    
    /** method to insert or update a new ecozone
     * 
     * @param request
     * @param response
     * @throws NumberFormatException
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void insertOrUpdateEcozone(HttpServletRequest request, HttpServletResponse response)
    throws NumberFormatException, SQLException, IOException, ServletException {
    	try {
	    	Logger.info("insertEcozone Method for the EcozoneServlet");
	        String name = request.getParameter("name");
	        int totalArea = Integer.parseInt(request.getParameter("totalArea"));
	        int population = Integer.parseInt(request.getParameter("population"));
	        String vegetation = request.getParameter("vegetation");
	        String provinces = (request.getParameter("provinces").toUpperCase());
	        Ecozone zone = new Ecozone(name, totalArea, population, vegetation, provinces);
	        Ecozone.validateEcozone(zone); //exception will be thrown here if inputs are invalid and show Error form
	        String action = request.getServletPath();
	        connection = dbUtils.createConnection();
	        if (action == "/insert") {     	
	        	ecozoneDao.insertEcozone(zone, connection);
	        } else {
	        	ecozoneDao.updateEcozone(zone, connection);
	        }
	        response.sendRedirect("list");

        } catch (Exception e){
        		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Error.jsp");
        		String error = e.getMessage();
        		request.setAttribute("error", error);
    	        dispatcher.forward(request, response);
        		Logger.error(e.getMessage());
        } finally {
    		dbUtils.releaseConnection(connection);
        }        	
    }

    
    /** method to delete an ecozone
     * 
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws NamingException
     */
    private void deleteEcozone(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, NamingException {
    	Logger.info("deleteEcozone Method for the EcozoneServlet");
    	try {
	    	String name = request.getParameter("name");
	    	connection = dbUtils.createConnection();
	        ecozoneDao.deleteEcozone(name, connection);
	        response.sendRedirect("list");
    	} catch (Exception e){
    		System.err.println(e);
    	} finally {
    		dbUtils.releaseConnection(connection);
    	}
    }  
}