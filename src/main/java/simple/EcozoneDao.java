package simple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tinylog.Logger;


/**
 * This DAO class provides CRUD, search, and sort operations for the
 * terrestrial ecozones table in the database.
 * 
 * @author ccassels
 *
 */
public class EcozoneDao {
	
	
	public EcozoneDao() {}
	
    //prepared statements for basic crud operations
	private static final String INSERT_ECOZONES = "INSERT INTO ecozones" + " (name, totalArea, "
	+ "population, vegetation, provinces) VALUES " + " (?, ?, ?, ?, ?);";
    private static final String SELECT_ECOZONE_BY_NAME = "select name, totalArea, population, "
    + "vegetation, provinces from ecozones where name =?";
    private static final String SELECT_ALL_ECOZONES = "select * from ecozones";
    private static final String DELETE_ECOZONES = "delete from ecozones where name = ?;";
    private static final String UPDATE_ECOZONES = "update ecozones set totalArea = ?, "
    + "population = ?, vegetation = ?, provinces = ? where name = ?;";  
    //prepared statement for search functionality. Allows any characters before or after input and ignores case
    private static final String SEARCH_ECOZONES = "select * from ecozones where lower(name) like "
    + "lower(?) or lower(provinces) like lower(?) or lower(vegetation) like lower(?) ";    
        
    
    /**
     * insert a new entry into the ecozone table
     * 
     * @param ecozone
     * @param connection
     * @return
     * @throws Exception
     */
    public boolean insertEcozone(Ecozone ecozone, Connection connection) {
    	boolean rowInserted = false;
        try
        (PreparedStatement statement = connection.prepareStatement(INSERT_ECOZONES)) {
            statement.setString(1, ecozone.getName());
            statement.setInt(2, ecozone.getTotalArea());
            statement.setInt(3, ecozone.getPopulation());
            statement.setString(4, ecozone.getVegetation());
            statement.setString(5, ecozone.getProvinces());
            rowInserted = statement.executeUpdate() > 0;
        	statement.close();
        } catch (SQLException e) {
            Logger.error(e.getErrorCode());
        }
        return rowInserted;
    }
    
    
    /** 
     * method for editing data for a particular ecozone. Does not allow name changes
     * 
     * @param ecozone
     * @param connection
     * @return
     * @throws SQLException
     */
    public boolean updateEcozone(Ecozone ecozone, Connection connection) throws SQLException {
        boolean rowUpdated = false;
        try
        (PreparedStatement statement = connection.prepareStatement(UPDATE_ECOZONES)) {
            statement.setInt(1, ecozone.getTotalArea());
            statement.setInt(2, ecozone.getPopulation());
            statement.setString(3, ecozone.getVegetation());
            statement.setString(4, ecozone.getProvinces());
            statement.setString(5, ecozone.getName());
            rowUpdated = statement.executeUpdate() > 0;      
        	statement.close();
        } catch (SQLException e) {
        	Logger.error(e.getErrorCode());
        }
        return rowUpdated;
    }
    
    
    
    /**
     * Get a single row from the Ecozones table. Used for edit and delete functionality
     * 
     * @param name
     * @param connection
     * @return
     * @throws SQLException
     */
    public Ecozone selectEcozone(String name, Connection connection) throws SQLException{
        Ecozone ecozone = null;
        try
        (PreparedStatement statement = connection.prepareStatement(SELECT_ECOZONE_BY_NAME)) {
        	statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	int totalArea = rs.getInt("totalArea");
                int population = rs.getInt("population");
                String vegetation = rs.getString("vegetation");
                String provinces = rs.getString("provinces");
                ecozone = new Ecozone(name, totalArea, population, vegetation, provinces);
            }
        	statement.close();
        } catch (SQLException e) {
        	Logger.error(e.getErrorCode());
        }
        return ecozone;
    }
    
    
    
    /**
     * collect all rows from the Ecozones table
     * 
     * @param connection
     * @return
     * @throws SQLException
     */
    public List < Ecozone > selectAllEcozones(Connection connection) throws SQLException{
        List < Ecozone > ecozones = new ArrayList < > ();
        try
        (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ECOZONES)) {
        	generateEcozoneList(statement, ecozones);
        	statement.close();
        } catch (SQLException e) {
            Logger.error(e.getErrorCode());
        }
        return ecozones;
    }
    
    
    
    /**
     * Note we cannot use statement setString method here as we are inserting column 
     * names and an ordering clause. 
     * 
     * @param column
     * @param order
     * @param connection
     * @return
     * @throws SQLException
     */
    public List < Ecozone > sortEcozones(String column, String order, Connection connection) throws SQLException {
        List < Ecozone > ecozones = new ArrayList < > ();
        
        if (!(order.equals("asc")) && !(order.equals("desc")) || (!(column.equals("totalarea")) 
        && !(column.equals("name")) && !(column.equals("population")) && !(column.equals("vegetation")) 
        && !(column.equals("provinces")))){
        	throw new SQLException("invalid order or column");
        }
        
        String sorter = "select * from ecozones order by" + " " + column + " " + order;
        try
        (PreparedStatement statement = connection.prepareStatement(sorter)) {
        	generateEcozoneList(statement, ecozones);
        	statement.close();
        } catch (SQLException e) {
        	Logger.error(e.getErrorCode());
        }
        return ecozones;
    }
    
    
    
    /**
     * search method. Set parameters to the same so we are searching both through 
     * provinces, tree types, and ecozone names. may adjust later to search any column
     * 
     * @param input
     * @param connection
     * @return
     * @throws SQLException
     */
    public List < Ecozone > searchEcozones(String input, Connection connection) throws SQLException {
        List < Ecozone > ecozones = new ArrayList < > ();
        try
        (PreparedStatement statement = connection.prepareStatement(SEARCH_ECOZONES)) {
        	statement.setString(1, input);
        	statement.setString(2, input);
        	statement.setString(3, input);
        	generateEcozoneList(statement, ecozones);
        	statement.close();
        } catch (SQLException e) {
        	Logger.error(e.getErrorCode());
        }
        return ecozones;
    }

    
    
    /** remove an ecozone from the database
     * 
     * 
     * @param name
     * @param connection
     * @return rowDeleted: true if row with given name has been deleted
     * @throws SQLException
     */
    public boolean deleteEcozone(String name, Connection connection) throws SQLException {
        boolean rowDeleted = false;
        try
        (PreparedStatement statement = connection.prepareStatement(DELETE_ECOZONES)) {
            statement.setString(1, name);
            rowDeleted = statement.executeUpdate() > 0;
        	statement.close();
        } catch (SQLException e) {
        	Logger.error(e.getErrorCode());
        }
        return rowDeleted;
    }
    
    
    /**generates a list of ecozones for the given prepared statement and empty list.
     * used for listing all, searching, and sorting.
     * 
     * @param statement
     * @param ecozones
     * @throws SQLException
     */
    public void generateEcozoneList(PreparedStatement statement, List < Ecozone > ecozones) 
    throws SQLException {
    	ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            int totalArea = rs.getInt("totalArea");
            int population = rs.getInt("population");
            String vegetation = rs.getString("vegetation");
            String provinces = rs.getString("provinces");
            ecozones.add(new Ecozone(name, totalArea, population, vegetation, provinces));
        }
    }
}