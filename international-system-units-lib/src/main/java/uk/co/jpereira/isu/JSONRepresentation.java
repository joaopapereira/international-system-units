package uk.co.jpereira.isu;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by blue on 01/05/2015.
 */
public interface JSONRepresentation {
    String SUBUNITS = "subunits";
    String CLASS = "class";
    String AMOUNT = "amount";
    String UNITMODIFIER = "unit_mod";

    /**
     * Function will generate the JSON representation
     * of the object
     *
     * @return
     */
    JSONObject getRepresentation();

    /**
     * Function will load the object with the help of the
     * JSON representation
     *
     * @param object JSON Representaion of the object
     */
    void loadFromRepresentation(JSONObject object);

    /**
     * Generates the base JSON object
     *
     * @return Base JSON Object
     */
    default JSONObject generateBaseObject() {
        JSONObject obj = new JSONObject();
        obj.put(CLASS, getClass());
        return obj;
    }

    /**
     * Add Sub Units if it is the case
     *
     * @param obj     Object where the units will be added
     * @param subunit Unit that will be added
     * @return Resulting object
     */
    default JSONObject addSubUnit(JSONObject obj, JSONObject subunit) {
        JSONArray arr = getSubUnits(obj);
        if (arr == null) {
            arr = new JSONArray();
        }
        arr.add(subunit);
        obj.put(SUBUNITS, arr);
        return obj;
    }

    /**
     * Retrieve all the sub units that are present in the json object
     *
     * @param obj Object to check
     * @return Returns an array of JSON objects or null
     */
    default JSONArray getSubUnits(JSONObject obj) {
        if (obj.containsKey(SUBUNITS)) {
            return (JSONArray) obj.get(SUBUNITS);
        }
        return null;
    }
}
