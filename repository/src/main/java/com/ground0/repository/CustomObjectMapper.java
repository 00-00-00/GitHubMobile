package com.ground0.repository;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by zer0 on 23/10/16.
 */

@Singleton public class CustomObjectMapper extends ObjectMapper {

  @Inject public CustomObjectMapper() {
    enable(SerializationFeature.INDENT_OUTPUT);
    configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    registerModule(new CustomLocalDateTimeJsonModule());
    registerModule(new CustomLocalDateJsonModule());
    registerModule(new CustomTimeInstantJsonModule());
    registerModule(new CustomLocalTimeJsonModule());
  }

  private static class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final CustomLocalDateSerializer INSTANCE = new CustomLocalDateSerializer();

    @Override public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider arg2)
        throws IOException, JsonProcessingException {
      System.out.println("Formatting date: " + value);
      gen.writeString(DATE_TIME_FORMATTER.format(value));
    }
  }

  private static class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final CustomLocalDateDeserializer INSTANCE = new CustomLocalDateDeserializer();

    @Override public LocalDate deserialize(JsonParser jsonParser,
        DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
      JsonToken currentToken = jsonParser.getCurrentToken();
      if (currentToken == JsonToken.VALUE_STRING) {
        String dateTimeAsString = jsonParser.getText().trim();
        return LocalDate.parse(dateTimeAsString, DATE_TIME_FORMATTER);
      } else {
        throw deserializationContext.mappingException(LocalDate.class);
      }
    }
  }

  public class CustomLocalDateJsonModule extends SimpleModule {
    private static final long serialVersionUID = 1L;

    public CustomLocalDateJsonModule() {
      super();
      addSerializer(LocalDate.class, CustomLocalDateSerializer.INSTANCE);
      addDeserializer(LocalDate.class, CustomLocalDateDeserializer.INSTANCE);
    }
  }

  private static class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final CustomLocalDateTimeSerializer INSTANCE =
        new CustomLocalDateTimeSerializer();

    @Override public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider arg2)
        throws IOException, JsonProcessingException {
      gen.writeString(DATE_TIME_FORMATTER.format(
          ZonedDateTime.of(value, ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC)));
    }
  }

  private static class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final CustomLocalDateTimeDeserializer INSTANCE =
        new CustomLocalDateTimeDeserializer();

    @Override public LocalDateTime deserialize(JsonParser jsonParser,
        DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
      JsonToken currentToken = jsonParser.getCurrentToken();
      if (currentToken == JsonToken.VALUE_STRING) {
        String dateTimeAsString = jsonParser.getText().trim();
        return ZonedDateTime.parse(dateTimeAsString, DATE_TIME_FORMATTER.withZone(ZoneOffset.UTC))
            .withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime();
      } else {
        throw deserializationContext.mappingException(LocalDateTime.class);
      }
    }
  }

  public class CustomLocalDateTimeJsonModule extends SimpleModule {
    private static final long serialVersionUID = 1L;

    public CustomLocalDateTimeJsonModule() {
      super();
      addSerializer(LocalDateTime.class, CustomLocalDateTimeSerializer.INSTANCE);
      addDeserializer(LocalDateTime.class, CustomLocalDateTimeDeserializer.INSTANCE);
    }
  }

  private static class CustomTimeInstantDeserializer extends JsonDeserializer<Instant> {

    public static final CustomTimeInstantDeserializer INSTANCE =
        new CustomTimeInstantDeserializer();

    @Override public Instant deserialize(JsonParser jsonParser, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
      JsonToken currentToken = jsonParser.getCurrentToken();
      if (currentToken == JsonToken.VALUE_STRING) {
        String dateTimeAsString = jsonParser.getText().trim();
        return Instant.parse(dateTimeAsString);
      } else {
        throw ctxt.mappingException(Instant.class);
      }
    }
  }

  private static class CustomTimeInstantSerializer extends JsonSerializer<Instant> {
    public static final CustomTimeInstantSerializer INSTANCE = new CustomTimeInstantSerializer();

    @Override public void serialize(Instant value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonProcessingException {
      jgen.writeString(value.toString());
    }
  }

  private static class CustomTimeInstantJsonModule extends SimpleModule {
    private static final long serialVersionUID = 1L;

    public CustomTimeInstantJsonModule() {
      super();
      addSerializer(Instant.class, CustomTimeInstantSerializer.INSTANCE);
      addDeserializer(Instant.class, CustomTimeInstantDeserializer.INSTANCE);
    }
  }

  public class CustomLocalTimeJsonModule extends SimpleModule {
    public CustomLocalTimeJsonModule() {
      super();
      addSerializer(LocalTime.class, CustomLocalTimeSerializer.INSTANCE);
      addDeserializer(LocalTime.class, CustomLocalTimeDeserializer.INSTANCE);
    }
  }

  private static class CustomLocalTimeSerializer extends JsonSerializer<LocalTime> {
    public static final CustomLocalTimeSerializer INSTANCE = new CustomLocalTimeSerializer();

    @Override
    public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
      gen.writeString(value.toString());
    }
  }

  private static class CustomLocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    public static final CustomLocalTimeDeserializer INSTANCE = new CustomLocalTimeDeserializer();

    @Override public LocalTime deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
      JsonToken currentToken = p.getCurrentToken();
      if (currentToken == JsonToken.VALUE_STRING) {
        String timeString = p.getText().trim();
        return LocalTime.parse(timeString);
      } else {
        throw ctxt.mappingException(LocalDateTime.class);
      }
    }
  }
}
