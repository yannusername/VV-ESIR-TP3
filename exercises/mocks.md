# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.


Dans le cadre de cet exercice, nous disposons d'un ensemble de tests initiaux qui ne réalise pas correctement ce qu'on attend. Le but de cet exercice est de corriger ces tests pour vérifier la méthode prepareSocket. Cette méthode permet de configurer une socket SSL en activant les protocoles de sécurité dans le bon ordre de préférence. 
La classe de test initiale "TLSSocketFactoryTest" essaie de tester la méthode prepapreSocket, au final on se rend compte qu'en les lançant, les 2 tests ne vérifient pas le comportement attendu. En effet, même si l'implémentation de prepareSocket entièrement supprimée, les tests ne signalent pas d'erreurs, ce qui montre qu'ils ne vérifient pas correctement l'activation des protocoles. 

Donc nous allons proposer une solution à ce problème avec le framework de test Mockito qui permet de générer des objets fictifs (mocks) pour simuler le comportement des dépendances, dans notre cas, un objet SSLSocket.

Le première méthode de test preparedSocket_NullProtocols() teste prepareSocket dans le cas où la liste des protocoles supportés est null et la liste des protocoles activés est également null.


La seconde méthode typical() vérifie le comportement de la méthode lorsque les listes des protocoles supportés et activés contiennent des éléments, enfin on vérifie que la méthode d'activation des ports est correctement appelée avec la liste corrcte des protocoles à activer.


Grâce à l'utilisation de Mockito, nous pouvons simuler et vérifier les interactions avec l'objet SSLSocket, garantissant ainsi que les protocoles sont activés conformément aux exigences de sécurité.
