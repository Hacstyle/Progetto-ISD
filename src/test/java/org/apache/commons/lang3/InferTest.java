package test.java.org.apache.commons.lang3;
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

        try{

            java.lang.Class<?> cls=null;
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
    public void testForNullPointerDereference_1() {
        String str = "a"; String set = null; Boolean expect=true;
        try {

            CharSet chars = CharSet.getInstance(set);
            StringBuilder buffer = new StringBuilder(str.length());
            char[] chrs = str.toCharArray();
            int sz = chrs.length;
            for(int i=0; i<sz; i++) {
                if(chars.contains(chrs[i]) == expect) {
                    buffer.append(chrs[i]);
                }
            }
            String b = buffer.toString();
        } catch (NullPointerException e) {

            fail("Null Pointer Exception 1");
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

    @Test
    public void testForNullPointerDereference_4()
    {
        String numeric = null; 
        boolean allZeros = true;
        try{

            try {
                Float f = NumberUtils.createFloat(numeric);
                if (!(f.isInfinite() || (f.floatValue() == 0.0F && !allZeros))) {
                    //If it's too big for a float or the float value = 0 and the string
                    //has non-zeros in it, then float does not have the precision we want
                    return;
                }

            } catch (NumberFormatException nfe) { // NOPMD
                // ignore the bad number
            }

        }
        catch(NullPointerException e){

            fail("Null Pointer Exception 4");

        }
    } 

    @Test
    public void testForNullPointerDereference_5()
    {
        String numeric = null; 
        boolean allZeros = true;
        try{

            try {
                Double d = NumberUtils.createDouble(numeric);
                if (!(d.isInfinite() || (d.floatValue() == 0.0D && !allZeros))) {
                    return;
                }
            } catch (NumberFormatException nfe) { // NOPMD
                // ignore the bad number
            }

        }
        catch(NullPointerException e){

            fail("Null Pointer Exception 5");

        }
    }
       
    
}
