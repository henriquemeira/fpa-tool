package co.h2a.fpatool.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParamdbTest {

	@Test
	public void testConnected() {
		Paramdb paramdb = new Paramdb();
		assertTrue(paramdb.isConnected());
	}
	
	@Test
	public void testDbFileExists() {
		Paramdb paramdb = new Paramdb();
		assertTrue(paramdb.dbFileExists());
	}
	
	@Test
	public void testGetConfigUserEmail() {
		Paramdb paramdb = new Paramdb();
		assertNotNull(paramdb.getConfigUserEmail());
		System.out.println(paramdb.getConfigUserEmail());
	}

}
