# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

En executant cette ligne :
pmd check -f text -R category/java/errorprone.xml/DetachedTestCase -d ../test/commons-collections/ -r report

On cherche à détecter les codes smells dans le projet "commons-collections" qui respectent la règle décrite pas DetachedTestCase. 
Cette règle sert à identifier les méthodes dans une classe de tests JUnit qui ressemblent à des tests avec l'outil PMD. 
Dans notre cas elle détecte ceci:

../test/commons-collections/src/test/java/org/apache/commons/collections4/IterableUtilsTest.java:71:	DetachedTestCase:	Probable detached JUnit test case.
../test/commons-collections/src/test/java/org/apache/commons/collections4/IterableUtilsTest.java:78:	DetachedTestCase:	Probable detached JUnit test case.


71    public void firstFromIterable() throws Exception {
72        // Collection, entry exists
73        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", IterableUtils.first(bag));
    }

78    public void getFromIterable() throws Exception {
79        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", IterableUtils.get(bag, 0));
    }

On remarque bien que ces 2 méthodes sont des tests mais elles n'ont pas l'annotation @Test, ce qui constitue une violation de la règle.
Pour les corriger afin qu'elles ne représentent plus de test smells, il faudrait leur rajouter l'annotation @Test avant chaque déclaration de méthode.

On cherche à détecter d'autres tests smells en lançant l'analyseur pmd avec une nouvelle règle:
pmd check -f text -R category/java/bestpractices.xml/JUnit4SuitesShouldUseSuiteAnnotation -d ../test/commons-col
lections/ -r report

Cette règle cherche à détecter les suites de tests qui ne respectent pas les normes Junit4. 
Dans JUnit3, les suites de test été déclarées par une méthode statique suite() cependant avec Junit4, il faut déclarer ce type de tests avec des annotations telles que: @RunWith(Suite.class) et @Suite.SuiteClasses.

Le fichier report.txt indique qu'il y a un code de smells qui a été détecté :
../test/commons-collections/src/test/java/org/apache/commons/collections4/GuavaTestlibTest.java:55:	JUnit4SuitesShouldUseSuiteAnnotation:	JUnit 4 indicates test suites via annotations, not the suite method.

En vérifiant dans GuavaTestlibTest.java à la ligne 55 :
    public static Test suite() {

On remarque que l'analyseur a correctement détecté la mauvaise pratique car en effet cette méthode utilise toujours les pratiques de JUnit3. 
Il faudrait remplacer la ligne 55 par ces 2 lignes:
@RunWith(Suite.class)
@Suite.SuiteClasses({

Cela permettrait de respecter les normes de JUnit4.


Enfin nous faisons une dernière analyse en utilisant une règle qui permet de détecter les tests qui n'utilisent pas d'assert() ou de fail ().

pmd check -f text -R category/java/bestpractices.xml/JUnitTestsShouldIncludeAssert -d ../test/commons-collection
s/ -r report

Le fichier report.txt indique qu'il y a un code de smells qui a été détecté :
../test/commons-collections/src/test/java/org/apache/commons/collections4/map/LazyMapTest.java:64:	JUnitTestsShouldIncludeAssert:	JUnit tests should include assert() or fail()

    @Test
    public void testAnyPredicateEx5() {
        PredicateUtils.anyPredicate(Collections.emptyList());
    }

Comme on peut le voir, aucun assert n'a été utilisé dans cette méthode de test. On peut corriger cette méthode de cette manière:

    @Test
    public void testAnyPredicateEx5() {
        assertThrows(NullPointerException.class, () -> PredicateUtils.anyPredicate(Collections.emptyList()) null));
    }
Cela permet de vérifier que la fonction ne renvoie pas un pointeur nul.
