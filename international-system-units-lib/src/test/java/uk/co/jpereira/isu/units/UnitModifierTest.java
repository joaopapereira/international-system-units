package uk.co.jpereira.isu.units;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitModifierTest {
	static class TestObject{
		public UnitModifier modifier;
		public double factorValue;
		public double numberToConvert;
		public Double convertedResult;
		public String fullName;
		public String smallName;
		public TestObject(UnitModifier modifier, double factorValue, double numberToConvert, String fullName, String smallName){
			this.modifier = modifier;
			this.factorValue = factorValue;
			this.numberToConvert = numberToConvert;
			this.convertedResult = numberToConvert / factorValue;
			this.fullName = fullName;
			this.smallName = smallName;
		}
	}
	private static ArrayList<TestObject> toTest = new ArrayList<TestObject>() {{
		   add(new TestObject(UnitModifier.YOTTA, 1000000000000000000000000. , 79.6, "YOTTA", "Y"));
		   add(new TestObject(UnitModifier.ZETTA,    1000000000000000000000. , 79.6, "ZETTA", "Z"));
		   add(new TestObject(UnitModifier.EXA,         1000000000000000000. , 79.6, "EXA",   "E"));
		   add(new TestObject(UnitModifier.PETA,           1000000000000000. , 79.6, "PETA",  "P"));
		   add(new TestObject(UnitModifier.TERA,              1000000000000. , 79.6, "TERA",  "T"));
		   add(new TestObject(UnitModifier.GIGA,                 1000000000. , 79.6, "GIGA",  "G"));
		   add(new TestObject(UnitModifier.MEGA,                    1000000. , 79.6, "MEGA",  "M"));
		   add(new TestObject(UnitModifier.KILO,                       1000. , 79.6, "KILO",  "k"));
		   add(new TestObject(UnitModifier.HECTO,                       100. , 79.6, "HECTO", "h"));
		   add(new TestObject(UnitModifier.DECA,                         10. , 79.6, "DECA",  "da"));
		   

		   add(new TestObject(UnitModifier.DECI,  0.1                        , 79.6, "DECI",  "d"));
		   add(new TestObject(UnitModifier.CENTI, 0.01                       , 79.6, "CENTI", "c"));
		   add(new TestObject(UnitModifier.MILLI, 0.001                      , 79.6, "MILLI", "m"));
		   add(new TestObject(UnitModifier.MICRO, 0.000001                   , 79.6, "MICRO", "μ"));
		   add(new TestObject(UnitModifier.NANO,  0.000000001                , 79.6, "NANO",  "n"));
		   add(new TestObject(UnitModifier.PICO,  0.000000000001             , 79.6, "PICO",  "p"));
		   add(new TestObject(UnitModifier.FEMTO, 0.000000000000001          , 79.6, "FEMTO", "f"));
		   add(new TestObject(UnitModifier.ATTO,  0.000000000000000001       , 79.6, "ATTO",  "a"));
		   add(new TestObject(UnitModifier.ZEPTO, 0.000000000000000000001    , 79.6, "ZEPTO", "z"));
		   add(new TestObject(UnitModifier.YOCTO, 0.000000000000000000000001 , 79.6, "YOCTO", "y"));
																			}};
	@BeforeClass
	public static void setUpAfterClass() throws Exception {
		
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Test
	public void testFactorValue(){
		for(TestObject t: toTest){
			assertEquals("Factor of [" + t.fullName +"] should be "+ t.factorValue, t.factorValue, t.modifier.getFactor(),0);
		}
	}
	@Test
	public void testSmallRepresentaton(){
		for(TestObject t: toTest){
			assertEquals("small name of [" + t.fullName +"] check", t.smallName, t.modifier.getSmallRepr());
			assertEquals("Retrieve modifier by using small representation of [" + t.fullName +"]", 
						 t.modifier,
						 UnitModifier.getEnum(t.smallName));
		}
	}
	@Test
	public void testNormalRepresentaton(){
		for(TestObject t: toTest){
			assertEquals("Normal name of [" + t.fullName +"] check", t.fullName, t.modifier.toString());
			assertEquals("Retrieve modifier by using normal representation of [" + t.fullName +"]", 
						 t.modifier,
						 UnitModifier.getEnum(t.fullName));
		}
	}
	@Test
	public void testConvert(){
		for(TestObject t: toTest){
			assertEquals("Convertion of [" + t.fullName +"] check", t.convertedResult, t.modifier.convert(t.numberToConvert),0);
		}
	}

}
