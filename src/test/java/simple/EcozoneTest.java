package simple;


import org.tinylog.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

// Testing the Ecozone Model
public class EcozoneTest {

	Ecozone ecozone = new Ecozone("testName", 1, 2, "testTree", "testProvince");
	
	//get methods
	@Test
    void getNameTest() {
		assertEquals("testName", ecozone.getName());
		Logger.info("getName test passed.");
	}
	
	@Test
	void getTotalAreaTest() {
		assertEquals(1, ecozone.getTotalArea());
		Logger.info("getTotalArea test passed.");
	}
	
	@Test
	void getPopulationTest() {
		assertEquals(2, ecozone.getPopulation());
		Logger.info("getPopulation test passed.");
	}
	
	@Test
	void getVegetationTest() {
		assertEquals("testTree", ecozone.getVegetation());
		Logger.info("getVegetation test passed.");
	}
	
	@Test
	void getProvincesTest() {
		assertEquals("testProvince", ecozone.getProvinces());
		Logger.info("getProvinces test passed.");
	}
	
	
	
	//set methods
	@Test
	void setNameTest() {
		ecozone.setName("newName");
		assertEquals("newName", ecozone.name);
		Logger.info("setName test passed.");
	}
	
	@Test
	void setTotalAreaTest() {
		ecozone.setTotalArea(2);
		assertEquals(2, ecozone.totalArea);
		Logger.info("setTotalArea test passed.");
	}
	
	@Test
	void setPopulationTest() {
		ecozone.setPopulation(3);
		assertEquals(3, ecozone.population);
		Logger.info("setPopulation test passed.");
	}
	
	@Test
	void setVegetationTest() {
		ecozone.setVegetation("newTree");
		assertEquals("newTree", ecozone.vegetation);
		Logger.info("setVegetation test passed.");
	}
	
	@Test
	void setProvincesTest() {
		ecozone.setProvinces("newProvince");
		assertEquals("newProvince", ecozone.provinces);
		Logger.info("setProvinces test passed.");
	}
	
	@Test
	void validateNameTest() {
		ecozone.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		try {
			Ecozone.validateEcozone(ecozone);
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
	
	
	@Test
	void validateTotalAreaTest() {
		ecozone.setName("newName"); //reset name. prevents another name error to allow testing for area error
		ecozone.setTotalArea(-1);
		try {
			Ecozone.validateEcozone(ecozone);
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
	
	@Test
	void validatePopulationTest() {
		ecozone.setTotalArea(2); //reset area. prevents another area error to allow testing for population error
		ecozone.setPopulation(2000000000);
		try {
			Ecozone.validateEcozone(ecozone);
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
	
	@Test
	void validateVegetationTest() {
		ecozone.setPopulation(3);
		ecozone.setVegetation("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		try {
			Ecozone.validateEcozone(ecozone);
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
	
	@Test
	void validateProvincesTest() {
		ecozone.setVegetation("newTree");
		ecozone.setProvinces("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		try {
			Ecozone.validateEcozone(ecozone);
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}
	
}

