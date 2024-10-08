# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1) Voici les blocs de partionnement qui seront à vérifier et les résultat qui sont censer renvoyer
Chaîne vide : "" return True.
Chaîne sans symboles de parenthèses : "abc", "123" return True. 
Chaîne bien formée avec des symboles correctement imbriqués : "{}", "[] return True.
", "()" et des versions imbriquées plus complexes comme "{[]()}", "{[()]}" return True.

Chaîne avec un seul symbole d'ouverture ou de fermeture : "{", "[", "(", "}", "]", ")" return False
Chaîne mal formée avec des symboles d'ouverture non fermés : "({[", "{[()"} return False
Chaîne mal formée avec des symboles de fermeture avant les ouvertures : "][", ")(}" return False
Chaîne mal formée avec des symboles mal appariés : "([)]", "{{)}" return False

2) En créant des tests pour chaque blocs de partionnement, nous avons pu observer le pourcentage de couverture assuré par les tests sur le code. Grâce à la fonction testing de VS code, nous avons identifier les zones de codes qui n'étaient pas couvertes par les tests et avons créer de nouveaux tests jusqu'à obtenir une couverture totale du code.

3) Pour évaluer la couverture de choix de base de la ligne:
 ((c == ')' && open != '(') || (c == '}' && open != '{') || (c == ']' && open != '['))
 Nous avons décomposer l'expression en 3 conditions, puis nous avons testé toutes les combinaisons de ces conditions, en rajoutant un test supplémentaire: testComplementairePourBCC()

4) 
- Statistics
================================================================================
>> Generated 17 mutations Killed 17 (100%)
>> Ran 33 tests (1.94 tests per mutation)

En obtenant un score de 100%, cela signifie que tous les mutants ont été tué et que les tests couvrent correctement toutes les branches. 