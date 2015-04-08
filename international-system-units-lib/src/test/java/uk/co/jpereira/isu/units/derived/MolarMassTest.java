package uk.co.jpereira.isu.units.derived;

import static org.junit.Assert.*;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.isue.exception.MissingParameters;

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
			molarMass.calculateUnit();
		}catch(MissingParameters e){
			return;
		}
		fail("Should have given one Exception");
	}
	@Test
	public void testCalculateUnitExceptionOnlyMass() {
		MolarMass molarMass = new MolarMass(new KiloGram());
		try{
			molarMass.calculateUnit();
		}catch(MissingParameters e){
			return;
		}
		fail("Should have given one Exception");
	}
	@Test
	public void testCalculateUnitExceptionOnlyMole() {
		MolarMass molarMass = new MolarMass(new Mole());
		try{
			molarMass.calculateUnit();
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
			molarMass.calculateUnit();
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
}
