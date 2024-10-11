package com.example.cms.payload;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class APIResponseTest {

    @Test
    void testAPIResponseConstructorAndGetter() {
        APIResponse apiResponse = new APIResponse("success", true);

        assertEquals("success", apiResponse.getMessage());
        assertEquals(true, apiResponse.getStatus());
    }

    @Test
    void testAPIResponseSetter() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage("failed");
        apiResponse.setStatus(false);

        assertEquals("failed", apiResponse.getMessage());
        assertEquals(false, apiResponse.getStatus());
    }
}
