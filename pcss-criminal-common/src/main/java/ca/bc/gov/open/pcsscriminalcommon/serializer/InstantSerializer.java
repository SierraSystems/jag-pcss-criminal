package ca.bc.gov.open.pcsscriminalcommon.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class InstantSerializer extends JsonSerializer<Instant> {
    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        String out =
                DateTimeFormatter.ofPattern("dd-MMM-yyyy")
                        .withZone(ZoneId.of("GMT-7"))
                        .withLocale(Locale.US)
                        .format(value);
        gen.writeString(out);
    }

    public static String convert(Instant value) {
        if (value == null) {
            return null;
        }

        return DateTimeFormatter.ofPattern("dd-MMM-yyyy")
                .withZone(ZoneId.of("GMT-7"))
                .withLocale(Locale.US)
                .format(value);
    }

    public static String print(Instant xml) {
        String first = xml.toString();
        return first.substring(0, first.length() - 1);
    }

    public static Instant parse(String value) {
        try {
            Date d;
            try {
                d = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSS a", Locale.US).parse(value);
            } catch (ParseException ex) {
                d = new SimpleDateFormat("dd-MMM-yy", Locale.US).parse(value);
            }
            return d.toInstant();

        } catch (Exception ex) {
            return null;
        }
    }

}
