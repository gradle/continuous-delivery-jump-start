package org.gradle.sample.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApacheComponentsHttpClient implements HttpClient {
    private final Logger logger = LoggerFactory.getLogger(ApacheComponentsHttpClient.class);

    @Override
    public HttpResponse get(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(createRequestConfig());
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setBody(IOUtils.toString(response.getEntity().getContent(), "UTF-8"));
            httpResponse.setStatusCode(response.getStatusLine().getStatusCode());
            httpResponse.setReasonPhrase(response.getStatusLine().getReasonPhrase());
            return httpResponse;
        }
        catch(IOException e) {
            logger.error(String.format("Failed to resolve URL '%s'", url), e);
            throw new HttpClientException(e);
        }
        finally {
            IOUtils.closeQuietly(httpClient);
        }
    }

    private RequestConfig createRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
    }
}