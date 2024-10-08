# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. 
L'assertion échoue à cause des imprécisions lors des calculs avec des nombres à virgule flottante. Le résultat de `3 * .4` n'est pas parfaitement égale à `1.2`. En effet, un flottant en Java est représenté en binaire ce qui amène des imprécisions. Afin de réaliser cette vérification, il faut utiliser `assertEquals(expected, actual, delta)` avec un delta afin de vérifier que la différence entre la valeur attendue et la valeur obtenue est inférieure à une certaine tolérance.

2. 
`assertSame(expected, actual)` vérifie si le expected et le actual font référence au même objet en utilisant l'opérateur d'égalité `==`
`assertEquals(expected, actual)` vérifie si les objets expected et actual sont égaux en utilisant la méthode `equals()`
- Cas dans lequel ils retournent le même résultat : 
`assertEquals("a", "a");` et `assertSame("a", "a");`
- Cas dans lequel ils ne retournent pas le même résultat :
`assertEquals("a", new String("a"));` et `assertSame("a", new String("a"));`

3. 
Il existe d'autres cas d'utilisation de `fail`:
- Test pas encore implémenté : On peut l'utiliser pour marquer des tests pas encore implémenté ou en cours d'implémentation :
```
@Test
public void test{
    fail("test pas implémenté")
}
```
- Exception non attendue : On peut utiliser `fail` quand une exception non attendue est levée :
```
@Test
public void test() {
    try {
        // du code
    } catch (Exception e) {
        fail("exception non attendue");
    }
}
```
- On peut utiliser `fail` lorsqu'un résultat ne correspond pas à certaines conditions :
```
@Test
public void test() {
    int r = randomInteger();
    if(r >  ) {
        fail("Le résultat dépasse la valeur maximale pour un integer");
    }
}
```
- On peut `fail` si le code ne `return/break` pas :
```
@Test
public void test() {
    int j = randomInteger();
    for (int i = 0; i < 5; i++) {
        // le code est censé return
    }
    fail("le code est censé return avant");
}
```