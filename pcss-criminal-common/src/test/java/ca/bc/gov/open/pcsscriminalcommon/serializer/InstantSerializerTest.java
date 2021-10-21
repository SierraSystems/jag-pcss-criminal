package ca.bc.gov.open.pcsscriminalcommon.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.StringWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Create Account Test Suite")
public class InstantSerializerTest {

    @Mock
    JsonParser jsonParserMock;

    InstantSerializer sut;

    @BeforeAll
    public void beforeAll() {

        MockitoAnnotations.openMocks(this);

        sut = new InstantSerializer();

    }

    @Test
    @DisplayName("Success object serialized")
    public void objectSerialized() throws IOException {

        Mockito.when(jsonParserMock.getText()).thenReturn("2021-10-21T14:48:50+00:00");

        JsonFactory factory = new JsonFactory();
        StringWriter jsonObjectWriter = new StringWriter();
        JsonGenerator generator = factory.createGenerator(jsonObjectWriter);

        LocalDate testDate = LocalDate.parse("2021-04-17");
        LocalDateTime testDateTime = testDate.atStartOfDay();

        Assertions.assertDoesNotThrow(() -> sut.serialize(testDateTime.toInstant(ZoneOffset.UTC), generator, null));

    }

    @Test
    @DisplayName("Success object converted")
    public void objectConverted() throws IOException {

        LocalDate testDate = LocalDate.parse("2021-04-17");
        LocalDateTime testDateTime = testDate.atStartOfDay();

        String result = InstantSerializer.convert(testDateTime.toInstant(ZoneOffset.UTC));

        Assertions.assertEquals("16-Apr-2021", result);

    }

    @Test
    @DisplayName("Null object not converted")
    public void objecNotConverted() throws IOException {

        Mockito.when(jsonParserMock.getText()).thenReturn("GARBAGE");

        Assertions.assertNull(InstantSerializer.convert(null));

    }

    @Test
    @DisplayName("Success object printed")
    public void objectPrinted() throws IOException {

        LocalDate testDate = LocalDate.parse("2021-04-17");
        LocalDateTime testDateTime = testDate.atStartOfDay();

        String result = InstantSerializer.print(testDateTime.toInstant(ZoneOffset.UTC));

        Assertions.assertEquals("2021-04-17T00:00:00", result);

    }

    @Test
    @DisplayName("Success object parsed")
    public void objectParsed() throws IOException {

        Instant result = InstantSerializer.parse("26-NOV-01 12.00.00.0000000 PM -08:00");

        Assertions.assertEquals("2001-11-26T20:00:00Z", result.toString());

    }

    @Test
    @DisplayName("Success object parsed no time")
    public void objectParsedNoTime() throws IOException {

        Instant result = InstantSerializer.parse("16-Apr-21");

        Assertions.assertEquals("2021-04-16T07:00:00Z", result.toString());

    }


    @Test
    @DisplayName("Error object not parsed")
    public void objecNotParsed() throws IOException {

        Mockito.when(jsonParserMock.getText()).thenReturn("GARBAGE");

        Instant result = InstantSerializer.parse(null);

        Assertions.assertNull(result);


    }

}