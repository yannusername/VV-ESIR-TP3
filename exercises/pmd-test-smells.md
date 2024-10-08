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


