package uk.co.jpereira.isu.utils;

import org.junit.*;

public class SharedMemoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSharedMemory() {
		SharedMemory mem = new SharedMemory();
		//MeterToKilometer translator = new MeterToKilometer();
		//assertEquals("Bamm", translator, mem.findTranslators(Meter.class, KiloMeter.class));
	}

}
