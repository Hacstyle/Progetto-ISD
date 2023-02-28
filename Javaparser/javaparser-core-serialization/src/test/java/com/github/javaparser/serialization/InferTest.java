package com.github.javaparser.serialization;

import java.util.HashMap;
import java.util.Map;

import javax.json.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InferTest {
    
    JsonObjectBuilder builder;
    JsonObject obj;
    Map<String, JsonValue> map = new HashMap<>();

    @BeforeEach
    void setUp()
    {

        for(int i=0; i<10000; i++)
        {
           builder = Json.createObjectBuilder().add(Integer.toString(i), Integer.toString(i)); 
        }

        obj=builder.build();

        for(int i=0; i<10000; i++)
        {
            map.put(Integer.toString(i), Json.createValue(Integer.toString(i)));
        }

    }

    @Test
    void testKeySet_1()
    {
        long start1 = System.nanoTime();
        for (String name : obj.keySet())
        {
            map.put(name, obj.get(name));
        }

        long end1 = System.nanoTime();
        long duration1 = (end1 - start1);


        long start2 = System.nanoTime();
        for (Map.Entry<String, JsonValue> entry : map.entrySet())
        {
            map.put(entry.getKey(), entry.getValue());
        }

        long end2 = System.nanoTime();
        long duration2 = (end2 - start2);

        assertEquals(true, duration1 < duration2);
    }

    @Test
    void testKeySet_2()
    {
        long start1 = System.nanoTime();
        for (String name : map.keySet())
        {
            JsonValue j=map.get(name);
        }

        long end1 = System.nanoTime();
        long duration1 = (end1 - start1);


        long start2 = System.nanoTime();
        for (Map.Entry<String, JsonValue> entry : map.entrySet())
        {
            JsonValue j = entry.getValue();
        }

        long end2 = System.nanoTime();
        long duration2 = (end2 - start2);

        assertEquals(true, duration1 < duration2);
    }

}
