package com.github.javaparser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.utils.SourceRoot;

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


    
}
