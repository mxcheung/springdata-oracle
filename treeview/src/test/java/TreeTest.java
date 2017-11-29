package au.com.lonsec.service.company.tree;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.lonsec.service.company.lookup.model.LookupValue;

public class TreeTest {

    private final static String JSON_STRING = "{\"value\":\"AEQ\",\"children\":[{\"value\":\"AEQ:ALP\",\"children\":[{\"value\":\"AEQ:ALP:AR\",\"children\":[]}]},{\"value\":\"AEQ:ASC\",\"children\":[]}]}";

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        Tree tree = new Tree("Root", getChildren("Root"));

        String json = this.mapper.writeValueAsString(tree);
        assertEquals(JSON_STRING, json);
    }

    @Test
    public void shouldDeserialize() throws JSONException, JsonParseException, JsonMappingException, IOException {
        Tree tree = mapper.readValue(JSON_STRING, Tree.class);
        assertEquals("AEQ", tree.getValue());
        assertEquals(2, tree.getChildren().size());
    }

    List<Tree> getChildren(String parentCode) {
        List<Tree> children = new ArrayList<Tree>();
        List<LookupValue> lookups = getLookupList(parentCode, getLookupList());
        for (LookupValue lookup : lookups) {
            Tree child1 = new Tree(lookup.getKey(), getChildren(lookup.getKey()));
            children.add(child1);

        }
        return children;
    }


    private List<LookupValue> getLookupList() {
        List<LookupValue> lookups = new ArrayList<LookupValue>();
        lookups.add(getLookupValue("Root", "Root", null));
        lookups.add(getLookupValue("AEQ", "Australia Equities", "Root"));
        lookups.add(getLookupValue("AEQ:ALP", "AEQ:ALP", "AEQ"));
        lookups.add(getLookupValue("AEQ:ASC", "AEQ:ASC", "AEQ"));
        lookups.add(getLookupValue("AEQ:ALP:AR", "AEQ:ASC", "AEQ:ALP"));
        lookups.add(getLookupValue("PI", "Property", "Root"));
        return lookups;
    }

    private List<LookupValue> getLookupList(String parentCode, List<LookupValue> lookups) {
        List<LookupValue> filteredlookups = new ArrayList<LookupValue>();
        for (LookupValue lookup : lookups) {
            if ((lookup.getParentCode() != null) && (lookup.getParentCode().equalsIgnoreCase(parentCode))) {
                filteredlookups.add(lookup);
            }

        }
        return filteredlookups;
    }

    private LookupValue getLookupValue(String key, String value, String parentCode) {
        LookupValue lookup = new LookupValue();
        lookup.setKey(key);
        lookup.setValue(value);
        lookup.setParentCode(parentCode);
        return lookup;
    }

}