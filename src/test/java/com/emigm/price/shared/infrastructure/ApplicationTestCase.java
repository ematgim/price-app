package com.emigm.price.shared.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class ApplicationTestCase {

    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected void assertResponse(String endpoint,
                                  Integer expectedStatusCode,
                                  String expectedResponse)
            throws Exception {
        ResultMatcher response = expectedResponse.isEmpty() ? content().string("") : content().json(expectedResponse);

        mockMvc.perform(get(endpoint))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(response);
    }

    protected void assertResponse(String endpoint,
                                  Integer expectedStatusCode,
                                  String expectedResponse,
                                  HttpHeaders headers) throws Exception {
        ResultMatcher response = expectedResponse.isEmpty() ? content().string("") : content().json(expectedResponse);

        mockMvc.perform(get(endpoint).headers(headers))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(response);
    }

    protected void assertResponse(String endpoint,
                                  Integer expectedStatusCode)
            throws Exception {
        mockMvc.perform(get(endpoint))
                .andExpect(status().is(expectedStatusCode));
    }

    protected void assertResponseContains(String endpoint,
                                          Integer expectedStatusCode,
                                          String expression)
            throws Exception {

        mockMvc.perform(get(endpoint))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(jsonPath(expression).exists());
    }

    protected void assertRequestWithBody(String method,
                                         String endpoint,
                                         String body,
                                         Integer expectedStatusCode)
            throws Exception {
        mockMvc.perform(request(HttpMethod.valueOf(method),
                        endpoint).content(body)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(content().string(""));
    }

    protected void assertRequest(String method,
                                 String endpoint,
                                 Integer expectedStatusCode) throws Exception {
        mockMvc.perform(request(HttpMethod.valueOf(method),
                        endpoint))
                .andExpect(status().is(expectedStatusCode))
                .andExpect(content().string(""));
    }


    protected List<HashMap<String, String>> givenStringResponseToMap(String response) throws JsonProcessingException {
        return objectMapper.readValue(response,
                new TypeReference<List<HashMap<String, String>>>() {
                });
    }
}
