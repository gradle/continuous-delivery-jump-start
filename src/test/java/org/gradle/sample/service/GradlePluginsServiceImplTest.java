package org.gradle.sample.service;

import org.gradle.sample.data.GradlePluginsSummary;
import org.gradle.sample.http.HttpClient;
import org.gradle.sample.http.HttpClientException;
import org.gradle.sample.http.HttpResponse;
import org.gradle.sample.parser.ApiParserException;
import org.gradle.sample.parser.GradlePluginsApiParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GradlePluginsServiceImplTest {
    private HttpClient httpClient = mock(HttpClient.class);
    private GradlePluginsApiParser parser = mock(GradlePluginsApiParser.class);
    private GradlePluginsService service;

    @Before
    public void setup() {
        service = new GradlePluginsServiceImpl(httpClient, parser);
    }

    @Test
    public void successfullyGetSummary() {
        String json = "{ \"package_count\": 217 }";
        HttpResponse expectedResponse = new HttpResponse();
        expectedResponse.setStatusCode(200);
        expectedResponse.setBody(json);
        GradlePluginsSummary expectedSummary = new GradlePluginsSummary();
        expectedSummary.setCount(217L);

        when(httpClient.get(GradlePluginsServiceImpl.PLUGINS_STATUS_URL)).thenReturn(expectedResponse);
        when(parser.parseSummary(json)).thenReturn(expectedSummary);

        GradlePluginsSummary summary = service.getSummary();
        assertEquals(expectedSummary, summary);

        verify(httpClient).get(GradlePluginsServiceImpl.PLUGINS_STATUS_URL);
        verify(parser).parseSummary(json);
    }

    @Test(expected = ServiceException.class)
    public void httpCallRespondsWithStatusCodeOtherThan200() {
        HttpResponse expectedResponse = new HttpResponse();
        expectedResponse.setStatusCode(400);

        when(httpClient.get(GradlePluginsServiceImpl.PLUGINS_STATUS_URL)).thenReturn(expectedResponse);

        service.getSummary();

        verify(httpClient).get(GradlePluginsServiceImpl.PLUGINS_STATUS_URL);
    }

    @Test(expected = ServiceException.class)
    public void httpCallRespondsWithHttpException() {
        when(httpClient.get(GradlePluginsServiceImpl.PLUGINS_STATUS_URL)).thenThrow(new HttpClientException());

        service.getSummary();

        verify(httpClient).get(GradlePluginsServiceImpl.PLUGINS_STATUS_URL);
    }

    @Test(expected = ServiceException.class)
    public void jsonParserApiParserException() {
        String json = "{ \"count\": 217 }";
        HttpResponse expectedResponse = new HttpResponse();
        expectedResponse.setStatusCode(200);
        expectedResponse.setBody(json);
        GradlePluginsSummary expectedSummary = new GradlePluginsSummary();
        expectedSummary.setCount(217L);

        when(httpClient.get(GradlePluginsServiceImpl.PLUGINS_STATUS_URL)).thenReturn(expectedResponse);
        when(parser.parseSummary(json)).thenThrow(new ApiParserException());

        service.getSummary();

        verify(httpClient).get(GradlePluginsServiceImpl.PLUGINS_STATUS_URL);
        verify(parser);
    }
}
