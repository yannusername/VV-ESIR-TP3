package fr.istic.vv;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;

public class StringUtilsTest {

    // Test pour une chaîne vide
    @Test
    public void testEmptyString() {
        assertTrue(isBalanced(""));
    }

    // Test pour des chaînes sans symboles de parenthèses
    @Test
    public void testNoParentheses() {
        assertTrue(isBalanced("abc")); 
        assertTrue(isBalanced("123")); 
    }

    // Test pour des chaînes bien formées avec des symboles correctement imbriqués
    @Test
    public void testWellFormed() {
        assertTrue(isBalanced("{}"));      
        assertTrue(isBalanced("[]"));      
        assertTrue(isBalanced("()"));      
        assertTrue(isBalanced("{[]()}"));   
        assertTrue(isBalanced("{[()]}"));   
    }

    // Test pour des chaînes avec un seul symbole d'ouverture ou de fermeture
    @Test
    public void testSingleOpeningClosing() {
        assertFalse(isBalanced("{")); 
        assertFalse(isBalanced("[")); 
        assertFalse(isBalanced("(")); 
        assertFalse(isBalanced("}")); 
        assertFalse(isBalanced("]")); 
        assertFalse(isBalanced(")")); 
    }

    // Test pour des chaînes mal formées avec des symboles d'ouverture non fermés
    @Test
    public void testUnmatchedOpenings() {
        assertFalse(isBalanced("({["));  
        assertFalse(isBalanced("{[()"));  
    }

    // Test pour des chaînes mal formées avec des symboles de fermeture avant les ouvertures
    @Test
    public void testUnmatchedClosings() {
        assertFalse(isBalanced("]["));   
        assertFalse(isBalanced(")(}"));   
    }

    // Test pour des chaînes mal formées avec des symboles mal appariés
    @Test
    public void testMismatchedSymbols() {
        assertFalse(isBalanced("([)]"));  
        assertFalse(isBalanced("{{)}"));   
    }

    // Test pour une chaîne complexe avec des caractères
    @Test
    public void testComplexCharacters() {
        assertTrue(isBalanced("{é[(tre)]rere}"));
    }

    //
    @Test
    public void testComplementairePourBCC(){
        assertFalse(isBalanced("{)"));
        assertFalse(isBalanced("[)")); 
        assertFalse(isBalanced("(}"));  
        assertFalse(isBalanced("[}"));  
        assertFalse(isBalanced("(]"));   
        assertFalse(isBalanced("{]"));  


    }

}
