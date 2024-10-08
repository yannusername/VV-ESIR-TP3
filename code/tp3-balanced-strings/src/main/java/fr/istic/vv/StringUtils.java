package fr.istic.vv;
import java.util.Stack;


public class StringUtils {

    public static boolean isBalanced(String str) {
        Stack<Character> pile = new Stack<>();

        for (char c : str.toCharArray()){
            // Si c'est un symbole d'ouverture, on l'empile
            if (c == '(' || c == '{' || c == '[') {
                pile.push(c);
            }
            // Si c'est un symbole de fermeture
            else if (c == ')' || c == '}' || c == ']') {
                // Si la pile est vide ou ne correspond pas au symbole d'ouverture, ce n'est pas équilibré
                if (pile.isEmpty()) {
                    return false;
                }
                char open = pile.pop();
                if ((c == ')' && open != '(') ||(c == '}' && open != '{') ||(c == ']' && open != '[')) {
                    return false;

                }
            }
        }
        return pile.isEmpty();

    }


}
