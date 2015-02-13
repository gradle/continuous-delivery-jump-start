package org.gradle.sample.parser;

import org.gradle.sample.data.GradlePluginsSummary;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JsonGradlePluginsApiParserTest {
    private GradlePluginsApiParser parser = new JsonGradlePluginsApiParser();

    @Test
    public void parseSuccessfulResponse() {
        GradlePluginsSummary summary = parser.parseSummary("{ \"name\": \"gradle-plugins\", \"owner\": \"gradle\", \"desc\": \"Repository for open source Gradle plugins\", \"created\": \"2013-06-06T09:10:51.149Z\", \"labels\": [ ], \"package_count\": 217 }");
        assertEquals(Long.valueOf(217L), summary.getCount());
        assertNull(summary.getExceptionMessage());
    }

    @Test
    public void parseFailedResponse() {
        GradlePluginsSummary summary = parser.parseSummary("{ \"tookSeconds\": 15.561, \"package_count\": 0, \"errors\": [], \"stackTrace\": null, \"exceptionMessage\": \"java.util.concurrent.TimeoutException\", \"sinceMins\": 9 }");
        assertEquals(Long.valueOf(0L), summary.getCount());
        assertEquals("java.util.concurrent.TimeoutException", summary.getExceptionMessage());
    }

    @Test(expected = ApiParserException.class)
    public void parseInvalidJson() {
        parser.parseSummary("{ hello }");
    }
}