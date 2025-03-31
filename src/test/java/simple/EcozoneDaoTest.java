package simple;


import org.tinylog.Logger;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

@ExtendWith(MockitoExtension.class)
public class EcozoneDaoTest {
	
	private Ecozone ecozone;
	
	@Mock
	private TomcatConnectionManager tcm;
	
	@Mock
	private DbUtils dbUtils;
	
	@Mock 
	private Connection connection;

	@Mock
	private ResultSet rs;
	
	@Mock 
	private PreparedStatement ps;
	
	@Mock
	private EcozoneDao ecozoneDao;
	
//	@BeforeEach
//	public void setUp() throws SQLException {
//		
//        when(connection.prepareStatement(any(String.class))).thenReturn(ps);
//        when(tcm.getConnection()).thenReturn(connection);
//		
//		ecozone = new Ecozone();
//		ecozone.setName("testZone");
//		ecozone.setTotalArea(1);
//		ecozone.setPopulation(2);
//		ecozone.setVegetation("testTree");
//		ecozone.setProvinces("testPlace");
//		
//		when(rs.first()).thenReturn(true);
//		when(rs.getString(1)).thenReturn(ecozone.getName());
//        when(rs.getInt(2)).thenReturn(ecozone.getTotalArea());
//        when(rs.getInt(3)).thenReturn(ecozone.getPopulation());
//        when(rs.getString(4)).thenReturn(ecozone.getVegetation());
//        when(rs.getString(5)).thenReturn(ecozone.getProvinces());
//		when(ps.executeQuery()).thenReturn(rs);
//		
//		
//	}
//	
//	@Test
//	public void insertAndSelectEcozoneTest() throws Exception {
//
//		EcozoneDao ecozoneDao = new EcozoneDao();
//		ecozoneDao.insertEcozone(ecozone, connection);
//		Ecozone otherEcozone = ecozoneDao.selectEcozone("testZone", connection);
//		Logger.info(otherEcozone.getVegetation());
//		assertEquals(ecozone, otherEcozone);
//		
//	}
	
	@Test
	public void sortEcozonesNegativeTest() throws SQLException {
		
		EcozoneDao ecozoneDao = new EcozoneDao();
		SQLException exception = assertThrows(SQLException.class, () ->
		ecozoneDao.sortEcozones("test", "test", connection));
		
		Logger.info(exception);
		
		assertEquals("invalid order or column", exception.getMessage());
	}
	
	
	
	
//	@Test
//	public void updateEcozoneTest() throws SQLException {
//		
//		EcozoneDao ecozoneDao = new EcozoneDao();
//		ecozoneDao.updateEcozone(ecozone, connection);
//		Ecozone otherEcozone = ecozoneDao.selectEcozone("testZone", connection);
//		assertEquals(ecozone, otherEcozone);
//		
//	}
//	
//	@Test
//	public void deleteEcozoneTest() throws SQLException {
//		
//	}
//	
//	@Test
//	public void searchEcozones() throws SQLException {
//		
//	}
	
}