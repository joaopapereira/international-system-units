package uk.co.jpereira.isu.units.derived;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.UnitModifier;

import static org.junit.Assert.*;

public class MolarMassTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		/**
		 * Logger.getLogger(MolarMass.class.getPackage().getName()).setLevel(Level.FINEST);
		 *
		 * ConsoleHandler ch = new ConsoleHandler();
		 * ch.setLevel(Level.FINEST);
		 * Logger.getLogger(MolarMass.class.getPackage().getName()).setUseParentHandlers(false);
		 * Logger.getLogger(MolarMass.class.getPackage().getName()).addHandler(ch);
		 */
		
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void testCalculateUnitExceptionNoUnits() {
		MolarMass molarMass = new MolarMass();
		try{
			molarMass.solve();
		}catch(MissingParameters e){
			return;
		}
		fail("Should have given one Exception");
	}
	@Test
	public void testCalculateUnitExceptionOnlyMass() {
		MolarMass molarMass = new MolarMass(new KiloGram());
		try{
			molarMass.solve();
		}catch(MissingParameters e){
			return;
		}
		fail("Should have given one Exception");
	}
	@Test
	public void testCalculateUnitExceptionOnlyMole() {
		MolarMass molarMass = new MolarMass(new Mole());
		try{
			molarMass.solve();
		}catch(MissingParameters e){
			return;
		}
		fail("Should have given one Exception");
	}
	@Test
	public void testCalculateUnitExceptionOnlyAmount() {
		MolarMass molarMass = new MolarMass();
		molarMass.setAmount(10.);
		try{
			molarMass.solve();
		}catch(MissingParameters e){
			return;
		}
		fail("Should have given one Exception");
	}
	
	@Test
	public void testGetMassAlreadyPresent() throws MissingParameters {
		MolarMass molarMass = new MolarMass(new KiloGram(5.));
		assertEquals("Mass should be 5", 5, molarMass.getMass().getAmount(), 0.);
	}
	@Test
	public void testGetMassNotPresent() throws MissingParameters {
		MolarMass molarMass = new MolarMass(new Mole(5.));
		molarMass.setAmount(0.1);
		assertEquals("Mass should be .5", .5, molarMass.getMass().getAmount(), 0.);
	}

	@Test
	public void testGetMoleAlreadyPresent() throws MissingParameters {
		MolarMass molarMass = new MolarMass(new Mole(1.));
		assertEquals("Mass should be 1", 1, molarMass.getMole().getAmount(), 0.);
	}
	@Test
	public void testGetMoleNotPresent() throws MissingParameters {
		MolarMass molarMass = new MolarMass(new KiloGram(5.));
		molarMass.setAmount(0.1);
		assertEquals("Mass should be 50", 50, molarMass.getMole().getAmount(), 0.);
	}

	@Test
	public void testGetAmount() throws MissingParameters {
		MolarMass molarMass = new MolarMass(new KiloGram(20.5, UnitModifier.Unit), new Mole(5.1));
		assertEquals("Mass should be 4.01960784314", 4.0196, molarMass.getAmount(), 0.0001);
	}

	@Test
	public void testGetSmallNameWithoutMass(){
		MolarMass molarMass = new MolarMass(new Mole(5.1));
		assertEquals("Small name should be MolarMass", "MolarMass", molarMass.smallName());
	}
	@Test
	public void testGetSmallNameWithoutMole(){
		MolarMass molarMass = new MolarMass(new KiloGram(20.5));
		assertEquals("Small name should be MolarMass", "MolarMass", molarMass.smallName());
	}
	@Test
	public void testGetSmallName(){
		MolarMass molarMass = new MolarMass(new KiloGram(20.5), new Mole(5.1));
		assertEquals("Small name should be Kg/mol", "kg/mol", molarMass.smallName());
	}
	@Test
	public void testGetSmallNameWithModifier(){
		MolarMass molarMass = new MolarMass(new KiloGram(20.5, UnitModifier.Unit), new Mole(5.1,UnitModifier.YOTTA));
		assertEquals("Small name should be g/mol", "g/mol", molarMass.smallName());
	}
	

	@Test
	public void testGetNameWithoutMass(){
		MolarMass molarMass = new MolarMass(new Mole(5.1));
		assertEquals("Small name should be MolarMass", "MolarMass", molarMass.name());
	}
	@Test
	public void testGetNameWithoutMole(){
		MolarMass molarMass = new MolarMass(new KiloGram(20.5));
		assertEquals("Small name should be MolarMass", "MolarMass", molarMass.name());
	}
	@Test
	public void testGetName(){
		MolarMass molarMass = new MolarMass(new KiloGram(20.5), new Mole(5.1));
		assertEquals("Small name should be Gram/Mole", "Gram/Mole", molarMass.name());
	}
	@Test
	public void testGetNameWithModifier(){
		MolarMass molarMass = new MolarMass(new KiloGram(20.5, UnitModifier.Unit), new Mole(5.1,UnitModifier.YOTTA));
		assertEquals("Small name should be Gram/Mole", "Gram/Mole", molarMass.name());
	}

	@Test
	public void testGetUnitRepresentation() throws ParseException {
		MolarMass molarMass = new MolarMass(new KiloGram(20.5, UnitModifier.Unit), new Mole(5.1, UnitModifier.YOTTA));
		String repr = "{\"amount\": 4.019607843137255, \"subunits\": [{\"amount\": 20.5, \"unit_mod\": \"g\"}, {\"amount\": 5.1, \"unit_mod\": \"Ymol\"}]}";
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(repr);
		obj.put("class", MolarMass.class);
		((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("class", KiloGram.class);
		((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("unit_mod", UnitModifier.Unit);
		((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("class", Mole.class);
		((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("unit_mod", UnitModifier.YOTTA);
		assertEquals("Compared representation", obj, molarMass.getRepresentation());
	}

	@Test
	public void testSetUnitRepresentation() throws ParseException, MissingParameters {
		MolarMass molarMass = new MolarMass(new KiloGram(20.5, UnitModifier.Unit), new Mole(5.1, UnitModifier.YOTTA));
		String repr = "{\"amount\": 4.019607843137255, \"subunits\": [{\"amount\": 20.5, \"unit_mod\": \"g\"}, {\"amount\": 5.1, \"unit_mod\": \"Ymol\"}]}";
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(repr);
		obj.put("class", MolarMass.class);
		((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("class", KiloGram.class);
		((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("unit_mod", UnitModifier.Unit);
		((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("class", Mole.class);
		((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("unit_mod", UnitModifier.YOTTA);
		MolarMass m1 = new MolarMass();
		m1.loadFromRepresentation(obj);
		assertTrue("Compared representation", m1.equals(molarMass));
	}

}
