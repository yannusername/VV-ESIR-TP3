# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

1) Voici comment on a résonné pour confectionner l'ensemble de test en fonction de chaque méthode.
Pour tester la méthode du constructeur de Date, on a créé des tests pour:
- vérifier qu'un jour, un mois ou une année négative renvoie une erreur
- vérifier que chaque mois comporte le bon nombre de jour (ex: Janvier 31 jours, Avril 30 jours)
- vérifier les particularités du mois de février avec les années bisextilles et le nombre de jours par mois. 

Pour tester la méthode isLeapYear, on vérifie que pour une année bissextile le résultat est true et false pour le cas contraire.

Pour tester les méthodes nextDate et previousDate, on vérifie 4 cas :
- Date normal
- Dernier jour de Février pendant une année bissextile
- Fin d'année
- Changement de mois

Pour tester la méthode compareTo, on vérifie les 3 cas possible, Date1=Date2, Date1>Date2 et Date1<Date2

2) Avec ces premiers tests, nous obtenons une couverture de 75 % des instructions, 100 % des fonctions et 72 % des branches. En créant une méthode de test "testPourAmeliorerLaCouverture", nous obtenons un score de 100% pour chaque couverture.

3) N'ayant pas de condition avec plus que 2 opérandes, on a choisi d'évaluer la couverture de choix de base de la ligne:  if (this.day==31 && this.month==12){ // Cas du 31 Décembre
Les combinaisons pour cette condition sont :
this.day=31, this.month=12 return true
this.day=1, this.month=12 return false
this.day=31, this.month=2 return false
this.day=1, this.month=2 return false

4) 
- Statistics
================================================================================
>> Generated 116 mutations Killed 114 (98%)
>> Ran 141 tests (1.22 tests per mutation)

2 mutants n'ont pas été tué, nous les avons identifiés et ils ne posent pas de problème au code. Avec une meilleure implémentation de la classe Date, il aurait été possible d'obtenir un résultat de 100% de mutants tués.
