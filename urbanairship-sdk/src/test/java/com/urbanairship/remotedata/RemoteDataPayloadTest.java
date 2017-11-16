/* Copyright 2017 Urban Airship and Contributors */

package com.urbanairship.remotedata;

import com.urbanairship.BaseTestCase;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.DateUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RemoteDataPayloadTest extends BaseTestCase {

    private JsonValue payloads;
    private JsonValue payload;
    String timestamp;
    private JsonMap data;

    @Before
    public void setup() {
        timestamp = DateUtils.createIso8601TimeStamp(System.currentTimeMillis());
        data = JsonMap.newBuilder().put("foo", "bar").build();
        payload = JsonMap.newBuilder().put("type", "test").put("timestamp", timestamp).put("data", data).build().toJsonValue();
        payloads = new JsonList(Arrays.asList(payload)).toJsonValue();
    }

    @Test
    public void testParsePayload() throws Exception {
        RemoteDataPayload parsedPayload = RemoteDataPayload.parsePayload(payload);
        verifyPayload(parsedPayload);
    }

    @Test
    public void testParsePayloads() {
        List<RemoteDataPayload> parsedPayloads = RemoteDataPayload.parsePayloads(payloads);
        Assert.assertEquals("Parsed payloads should have a size of one", parsedPayloads.size(), 1);
        for (RemoteDataPayload parsedPayload : parsedPayloads) {
            verifyPayload(parsedPayload);
        }
    }

    private void verifyPayload(RemoteDataPayload parsedPayload) {
        Assert.assertEquals("Payload should have type 'test'", parsedPayload.getType(), "test");
        Assert.assertEquals("Payload should have timestamp: " + timestamp, DateUtils.createIso8601TimeStamp(parsedPayload.getTimestamp()), timestamp);
        Assert.assertEquals("Payload should have data: " + data, parsedPayload.getData(), data);
    }
}