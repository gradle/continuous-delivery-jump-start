package org.gradle.sample.http;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApacheComponentsHttpClientIntegrationTest {
    private HttpClient httpClient = new ApacheComponentsHttpClient();

    @Test
    public void canMakeSuccessfulGetCall() {
        HttpResponse httpResponse = httpClient.get("http://www.google.com");
        assertTrue(httpResponse.isOK());
        assertEquals(httpResponse.getStatusCode(), 200);
        assertNotNull(httpResponse.getBody());
        assertEquals(httpResponse.getReasonPhrase(), "OK");
    }

    @Test(expected = HttpClientException.class)
    public void throwsExceptionForGetCallWithUnknownURL() {
        httpClient.get("http://www.unknown-url-234ndasdfq34234151351345sdf.com");
    }
}