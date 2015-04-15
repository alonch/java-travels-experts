package travelexperts;

import travelexperts.TravelExpertsDB;
import static org.junit.Assert.*;
import org.junit.Test;


public class TravelExpertsDBTest {

	@Test
	public void connectionNotNull() {
		assertNotNull("connection is not null", TravelExpertsDB.GetConnection());
	}
}
