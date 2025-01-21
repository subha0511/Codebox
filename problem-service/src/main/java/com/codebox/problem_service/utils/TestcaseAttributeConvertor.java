package com.codebox.problem_service.utils;

import com.codebox.problem_service.pojo.Testcase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class TestcaseAttributeConvertor implements AttributeConverter<List<Testcase>, String> {
  private static final String DELIMITER = ";"; // Delimiter for encoding
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<Testcase> attribute) {
    if (attribute == null || attribute.isEmpty()) {
      return null;
    }
    return attribute.stream().map(this::objectToString).collect(Collectors.joining(DELIMITER));
  }

  @Override
  public List<Testcase> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.isEmpty()) {
      return null;
    }
    return Arrays.stream(dbData.split(DELIMITER)).map(this::stringToObject)
        .collect(Collectors.toList());
  }

  private String objectToString(Testcase testcase) {
    try {
      return OBJECT_MAPPER.writeValueAsString(testcase);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Failed to serialize Testcase", e);
    }
  }

  private Testcase stringToObject(String string) {
    try {
      return OBJECT_MAPPER.readValue(string, Testcase.class);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Failed to deserialize MyObject", e);
    }
  }

}
