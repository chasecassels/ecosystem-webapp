package simple;


import org.tinylog.Logger;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import javax.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nfis.db.TomcatConnectionManager;

public class EcozoneServletTest {
	
	@Mock
	private EcozoneServlet ecozoneServlet;

	/**
	 * Testing the Ecozone Servlet
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
    void doGetTest() throws ServletException, IOException {
        
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    
        
        when(request.getServletPath()).thenReturn("/new");

        new EcozoneServlet().doGet(request, response);
        
        //verify(ecozoneServlet, times(1)).showNewForm(request, response);

        Logger.info("EcozoneServlet test passed.");
    }

}





















