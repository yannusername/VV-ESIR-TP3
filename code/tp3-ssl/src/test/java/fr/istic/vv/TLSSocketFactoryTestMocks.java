package fr.istic.vv;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        //création de l'objet mock: sslSocketMocket
        SSLSocket sslSocketMock = Mockito.mock(SSLSocket.class);

        //config des 2 méthodes pour qu'elle renvoie null
        when(sslSocketMock.getSupportedProtocols()).thenReturn(null);
        when(sslSocketMock.getEnabledProtocols()).thenReturn(null);

        TLSSocketFactory f = new TLSSocketFactory();
        //appelle de la fonction prepareSocket avec l'objet mock en paramètre
        f.prepareSocket(sslSocketMock);
        
        //verification que setEnabledProtocols n'a jamais été appelé
        verify(sslSocketMock, never()).setEnabledProtocols(any());
    }

    @Test
    public void typical() {
        SSLSocket sslSocketMock = Mockito.mock(SSLSocket.class);

        //config des 2 méthodes pour qu'elles renvoient des listes spécifiques
        when(sslSocketMock.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
        when(sslSocketMock.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

        TLSSocketFactory f = new TLSSocketFactory();
        
        f.prepareSocket(sslSocketMock);
        
        //verification que setEnabled a été appelée avec les bons paramètres correctement triés
        verify(sslSocketMock).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }
}
