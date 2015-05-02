package uk.co.jpereira.isu.chemistry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.Test;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.units.KiloGram;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.isu.units.derived.MassConcentration;
import uk.co.jpereira.isu.units_accepted.Liter;

import static org.junit.Assert.*;

public class MassConcentrationTest {

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void testNoUnitsSet() {
        MassConcentration massConcentration = new MassConcentration();
        try {
            massConcentration.solve();
            fail("Exception should have been raised");
        } catch (MissingParameters e) {

        }
    }

    @Test
    public void testLiterNotSet() {
        MassConcentration massConcentration = new MassConcentration();
        try {
            KiloGram kg = new KiloGram();
            kg.setAmount(10.0);
            massConcentration.setKiloGram(kg);
            massConcentration.solve();
            fail("Exception should have been raised");
        } catch (MissingParameters e) {

        }
    }

    @Test
    public void testNoLiterSolve() throws MissingParameters {
        MassConcentration massConcentration = new MassConcentration();
        KiloGram kg = new KiloGram();
        kg.setAmount(10.0);
        massConcentration.setKiloGram(kg);
        massConcentration.setAmount(5.);
        massConcentration.solve();
        assertEquals(2., massConcentration.getLiter().getAmount(), 0.1);
    }

    @Test
    public void testNoKilogramSolve() throws MissingParameters {
        MassConcentration massConcentration = new MassConcentration();
        Liter liter = new Liter(UnitModifier.DECI);
        liter.setAmount(2.0);
        massConcentration.setLiter(liter);
        massConcentration.setAmount(10.);
        massConcentration.solve();
        assertEquals(2., massConcentration.getKiloGram().getAmount(), 0.1);
    }
    @Test
    public void testSolve() throws MissingParameters {
        MassConcentration massConcentration = new MassConcentration();
        KiloGram kg = new KiloGram();
        kg.setAmount(10.0);
        massConcentration.setKiloGram(kg);
        Liter liter = new Liter(UnitModifier.DECI);
        liter.setAmount(2.0);
        massConcentration.setLiter(liter);
        massConcentration.solve();
        assertEquals(50., massConcentration.getAmount(), 0.1);
    }

    @Test
    public void testGetRepresentation() throws ParseException, MissingParameters {
        MassConcentration massConcentration = new MassConcentration();
        KiloGram kg = new KiloGram(20.5, UnitModifier.Unit);
        Liter l = new Liter(5.1, UnitModifier.YOTTA);
        massConcentration.setLiter(l);
        massConcentration.setKiloGram(kg);
        massConcentration.solve();
        String repr = "{\"amount\": 4.019607843137255E-24, \"subunits\": [{\"amount\": 20.5, \"unit_mod\": \"g\"}, {\"amount\": 5.1, \"unit_mod\": \"Ymol\"}]}";
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(repr);
        obj.put("class", MassConcentration.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("class", KiloGram.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("unit_mod", UnitModifier.Unit);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("class", Liter.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("unit_mod", UnitModifier.YOTTA);
        assertEquals("Compared representation", obj, massConcentration.getRepresentation());
    }

    @Test
    public void testSetRepresentation() throws ParseException, MissingParameters {
        MassConcentration massConcentration = new MassConcentration();
        KiloGram kg = new KiloGram(20.5, UnitModifier.Unit);
        Liter l = new Liter(5.1, UnitModifier.YOTTA);
        massConcentration.setLiter(l);
        massConcentration.setKiloGram(kg);
        massConcentration.solve();
        String repr = "{\"amount\": 4.019607843137255E-24, \"subunits\": [{\"amount\": 20.5, \"unit_mod\": \"g\"}, {\"amount\": 5.1, \"unit_mod\": \"Ymol\"}]}";
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(repr);
        obj.put("class", MassConcentration.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("class", KiloGram.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("unit_mod", UnitModifier.Unit);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("class", Liter.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("unit_mod", UnitModifier.YOTTA);
        MassConcentration m1 = new MassConcentration();
        m1.loadFromRepresentation(obj);
        m1.solve();
        assertTrue("Compared representation", m1.equals(massConcentration));
    }
}
