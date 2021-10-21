package ca.bc.gov.open.pcsscriminalcommon.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.Instant;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Create Account Test Suite")
public class InstantDeserializerTest {

    @Mock
    JsonParser jsonParserMock;

    InstantDeserializer sut;

    @BeforeAll
    public void beforeAll() {

        MockitoAnnotations.openMocks(this);

        sut = new InstantDeserializer();
    }

    @Test
    @DisplayName("Success object deserialized")
    public void objectDeserialized() throws IOException {

        Mockito.when(jsonParserMock.getText()).thenReturn("2021-10-21T14:48:50+00:00");

        Instant result = sut.deserialize(jsonParserMock, null);

        Assertions.assertEquals(null, result);

    }

    @Test
    @DisplayName("Error object not deserialized")
    public void objecNotDeserialized() throws IOException {

        Mockito.when(jsonParserMock.getText()).thenReturn("GARBAGE");

        Instant result = sut.deserialize(jsonParserMock, null);

        Assertions.assertNull(result);

    }

}
