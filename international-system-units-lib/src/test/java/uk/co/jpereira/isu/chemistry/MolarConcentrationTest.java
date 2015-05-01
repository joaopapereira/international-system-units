package uk.co.jpereira.isu.chemistry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.Test;
import uk.co.jpereira.isu.exception.MissingParameters;
import uk.co.jpereira.isu.units.Mole;
import uk.co.jpereira.isu.units.UnitModifier;
import uk.co.jpereira.isu.units_accepted.Liter;

import static org.junit.Assert.*;

public class MolarConcentrationTest {

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void testNoUnitsSet() {
        MolarConcentration molarConcentration = new MolarConcentration();
        try {
            molarConcentration.solve();
            fail("Exception should have been raised");
        } catch (MissingParameters e) {

        }
    }

    @Test
    public void testLiterNotSet() {
        MolarConcentration molarConcentration = new MolarConcentration();
        try {
            Mole kg = new Mole();
            kg.setAmount(10.0);
            molarConcentration.setMole(kg);
            molarConcentration.solve();
            fail("Exception should have been raised");
        } catch (MissingParameters e) {

        }
    }

    @Test
    public void testSolve() throws MissingParameters {
        MolarConcentration molarConcentration = new MolarConcentration();
        Mole kg = new Mole();
        kg.setAmount(10.0);
        molarConcentration.setMole(kg);
        Liter liter = new Liter();
        liter.setAmount(2.0);
        molarConcentration.setLiter(liter);
        molarConcentration.solve();
        assertEquals(5., molarConcentration.getAmount(), 0.1);
    }

    @Test
    public void testGetRepresentation() throws ParseException, MissingParameters {
        MolarConcentration molarConcentration = new MolarConcentration();
        Mole kg = new Mole(20.5, UnitModifier.Unit);
        Liter l = new Liter(5.1, UnitModifier.YOTTA);
        molarConcentration.setLiter(l);
        molarConcentration.setMole(kg);
        molarConcentration.solve();
        String repr = "{\"amount\": 4.019607843137255E-24, \"subunits\": [{\"amount\": 20.5, \"unit_mod\": \"g\"}, {\"amount\": 5.1, \"unit_mod\": \"Ymol\"}]}";
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(repr);
        obj.put("class", MolarConcentration.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("class", Mole.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("unit_mod", UnitModifier.Unit);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("class", Liter.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("unit_mod", UnitModifier.YOTTA);
        assertEquals("Compared representation", obj, molarConcentration.getRepresentation());
    }

    @Test
    public void testSetRepresentation() throws ParseException, MissingParameters {
        MolarConcentration molarConcentration = new MolarConcentration();
        Mole kg = new Mole(20.5, UnitModifier.Unit);
        Liter l = new Liter(5.1, UnitModifier.YOTTA);
        molarConcentration.setLiter(l);
        molarConcentration.setMole(kg);
        molarConcentration.solve();
        String repr = "{\"amount\": 4.019607843137255E-24, \"subunits\": [{\"amount\": 20.5, \"unit_mod\": \"g\"}, {\"amount\": 5.1, \"unit_mod\": \"Ymol\"}]}";
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(repr);
        obj.put("class", MolarConcentration.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("class", Mole.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(0)).put("unit_mod", UnitModifier.Unit);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("class", Liter.class);
        ((JSONObject) ((JSONArray) obj.get("subunits")).get(1)).put("unit_mod", UnitModifier.YOTTA);
        MolarConcentration m1 = new MolarConcentration();
        m1.loadFromRepresentation(obj);
        m1.solve();
        assertTrue("Compared representation", m1.equals(molarConcentration));
    }
}
