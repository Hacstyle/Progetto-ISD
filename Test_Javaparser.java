package com.github.javaparser;
package com.github.javaparser.serialization;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.utils.SourceRoot;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.io.File;
import java.io.IOException;

import javax.json.*;


public class InferTest {

    @Test
    void testForNullDereference_4()
    {
        String value=null;
        CharLiteralExpr c=new CharLiteralExpr(value);

        try
        {
            c.asChar();
        }
        catch (NullPointerException e)
        {
            fail("Null Pointer Exception 4");
        }
    }

    @Test
    void testForNullDereference_5()
    {
        Integer t=3;
        PackageDeclaration p=new PackageDeclaration();
        p.setAnnotations(null);
        CloneVisitor v=new CloneVisitor();

        try
        {
            v.visit(p, t);
        }
        catch (NullPointerException e)
        {
            fail("Null Pointer Exception 4");
        }
    }
    
    JsonObjectBuilder builder;
    JsonObject obj;
    Map<String, JsonValue> map = new HashMap<>();
    
    @BeforeAll
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
    
    @Test
    void testResourceLeak_58() throws IOException
    {
        File f=new File("/home/andre/App-Vulnerabili/glide-6.0.0-beta.2.aar");
        JarFile jarFile = new JarFile(f);
        JarTypeSolver delegate;
        
        try
        {
            ZipEntry classesJarEntry = jarFile.getEntry("classes.jar");
            if (classesJarEntry == null) {
                throw new IllegalArgumentException(String.format("The given file (%s) is malformed: entry classes.jar was not found", f.getAbsolutePath()));
            }
            delegate = new JarTypeSolver(jarFile.getInputStream(classesJarEntry));
        }
        finally
        {
            assertThrows(IOException.class, () -> {jarFile.getEntry("classes.jar");});
        }
        
    }


    
}
