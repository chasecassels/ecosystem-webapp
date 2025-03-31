package simple;

import java.util.regex.Pattern;

/* This class acts as the 'model layer'. 
 * It mirrors an ecozone entity in the database.
 * 
 * population is approximate.
 * vegetation lists the top 3 most common tree genus. Arctic ecozones have 'none' listed here.
 * provinces lists the provinces where the given ecozone can be found
 */
public class Ecozone {
	
	protected String name;
	protected int totalArea;
    protected int population;
    protected String vegetation;
    protected String provinces;
    
    public Ecozone() {}
    
    
    /** represents an entry in the ecozones table
     * 
     * @param name
     * @param totalArea
     * @param population
     * @param vegetation
     * @param provinces
     */
    public Ecozone(String name, int totalArea, int population, String vegetation, String provinces) {
        this.name = name;
        this.totalArea = totalArea;
        this.population = population;
        this.vegetation = vegetation;
        this.provinces = provinces;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTotalArea() {
        return totalArea;
    }
    public void setTotalArea(int totalArea) {
        this.totalArea = totalArea;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public String getVegetation() {
        return vegetation;
    }
    public void setVegetation(String vegetation) {
        this.vegetation = vegetation;
    }
    public String getProvinces() {
        return provinces;
    }
    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }
    
    
    /** throws an exception if any of the inputs are
     *  of an invalid format. To be called before entering 
     *  any new entries into the ecozones table
     * 
     * @param ecozone
     * @throws Exception
     */
    public static void validateEcozone(Ecozone ecozone) throws Exception {
    	if ((ecozone.getPopulation() < 0) || (ecozone.getPopulation() > 1000000000)) {
    		throw new Exception("Please enter a population value between 0 and 1000000000");
    	} else if ((ecozone.getTotalArea() < 0) || (ecozone.getTotalArea() > 9985000)) {
    		throw new Exception("Please enter an area value between 0 and 9985000");
    	} else if ((ecozone.getName().length() > 50) || !(ecozone.getName().matches(("[a-zA-Z][a-zA-Z ]+[a-zA-Z]$")))) {
    		throw new Exception("Please enter a valid name with fewer than 50 characters");
    	} else if ((ecozone.getVegetation().length() > 50) || !(ecozone.getVegetation().matches(("[a-zA-Z]+")))) {
    		throw new Exception("Please enter a valid tree genus with fewer than 50 characters");
    	} else if ((ecozone.getProvinces().length() > 50) || !(ecozone.getProvinces().matches(("[a-zA-Z]+(, [a-zA-Z]+)*$")))) {
    		throw new Exception("Please enter a valid series of provinces, ie: 'BC, AB'");
    	}
    }
}
















