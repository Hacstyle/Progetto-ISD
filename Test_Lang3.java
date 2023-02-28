package org.apache.commons.lang3;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.commons.lang3.builder.*;
import org.apache.commons.lang3.math.*;

import java.lang.annotation.Annotation;

import java.lang.StringBuilder;
import org.junit.Test;
import static org.junit.Assert.fail;


public class InferTest {

    @Test
    public void testForNullPointerDereference_0() {

        java.lang.Class<?> cls=null;

        try{

            Class<? extends Annotation> annotationType = null;
            for (Class<?> iface : ClassUtils.getAllInterfaces(cls)) {
                if (Annotation.class.isAssignableFrom(iface)) {
                    @SuppressWarnings("unchecked")
                    //because we just checked the assignability
                    Class<? extends Annotation> found = (Class<? extends Annotation>) iface;
                    annotationType = found;
                    break;
                }
            }
            String s = new StringBuilder(annotationType == null ? "" : annotationType.getName()).insert(0, '@').toString();
            return;
        }
        catch(NullPointerException e){

            fail();
        }

    }


    @Test
    public void testForNullPointerDereference_1()
    {
        String str = "a"; String[] set = null; Boolean expect=true;
        try
        {
           String c = CharSetUtils.keep(str, set);
        }
        catch(NullPointerException e)
        {
            fail("Null Pointer Exception 1:keep");
        }

        try
        {
           String c = CharSetUtils.delete(str, set);
        }
        catch(NullPointerException e)
        {
            fail("Null Pointer Exception 1:delete");
        }
    }
    

    @Test
    public void testForNullPointerDereference_2()
    {
        try{

            ToStringBuilder B=new ToStringBuilder(3, null,null);
        }
        catch(NullPointerException e){

            fail("Null Pointer Exception 2");
        }
    }

    /*@Test
    public void testForNullPointerDereference_4()
    {
        String str = "F"; 
        
        try{

           Number f=NumberUtils.createNumber(str);

        }
        catch(NumberFormatException c)
        {

        }
        catch(NullPointerException e){

            fail("Null Pointer Exception 4");

        }
    }


    @Test
    public void testForNullPointerDereference_5()
    {
        String str = "D"; 
        
        try{

           Number f=NumberUtils.createNumber(str);

        }
        catch(NumberFormatException c)
        {
            
        }
        catch(NullPointerException e){

            fail("Null Pointer Exception 5");

        }
    }*/
       
    
}
