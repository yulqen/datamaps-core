/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matthewlemon.datamaps.core.gateways;

import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.Context;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author lemon
 */
public class FileDatamapGatewayTest {

	private static FileDatamapGateway gateway;

	public FileDatamapGatewayTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
		TestSetup.setUpSampleData();
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		gateway =  new FileDatamapGateway();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	@Ignore("Not yet implemented")
	public void testFileDataGatewayExists() throws DuplicateDatamapException {
		assertEquals(new Datamap("tits"), gateway.createDatamap("Test Datamap in File"));
	}
}
