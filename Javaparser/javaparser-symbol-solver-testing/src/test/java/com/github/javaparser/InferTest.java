package com.github.javaparser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.junit.jupiter.api.Test;

import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;

public class InferTest {

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
