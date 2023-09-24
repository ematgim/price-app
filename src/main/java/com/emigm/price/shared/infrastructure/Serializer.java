package com.emigm.price.shared.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class Serializer {

    public static String jsonEncode(HashMap<String, Serializable> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static HashMap<String, Serializable> jsonDecode(String body) {
        try {
            return new ObjectMapper().readValue(body, HashMap.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static LocalDateTime decodeLocalDateTime(String localDateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(localDateTime);
        return zonedDateTime.toLocalDateTime();
    }

    public static String encodeLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.atOffset(ZoneOffset.UTC).format(ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
    }
}
