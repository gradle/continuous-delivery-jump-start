package org.gradle.sample.service;

import org.gradle.sample.data.GradlePluginsSummary;
import org.gradle.sample.http.HttpClient;
import org.gradle.sample.http.HttpClientException;
import org.gradle.sample.http.HttpResponse;
import org.gradle.sample.parser.GradlePluginsApiParser;
import org.gradle.sample.parser.ApiParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradlePluginsServiceImpl implements GradlePluginsService {
    private final Logger logger = LoggerFactory.getLogger(GradlePluginsServiceImpl.class);
    public static final String PLUGINS_STATUS_URL = "http://plugins.gradle.org/status/catalog/status";
    public static final String ERROR_MESSAGE = "Failed to successfully retrieve Gradle plugins summary";
    
    private final HttpClient httpClient;
    private final GradlePluginsApiParser parser;

    @Autowired
    public GradlePluginsServiceImpl(HttpClient httpClient, GradlePluginsApiParser parser) {
        this.httpClient = httpClient;
        this.parser = parser;
    }
    
    @Override
    public GradlePluginsSummary getSummary() {
        HttpResponse response;

        try {
            response = httpClient.get(PLUGINS_STATUS_URL);
        }
        catch(HttpClientException e) {
            throw new ServiceException(ERROR_MESSAGE);
        }

        if(!response.isOK()) {
            throw new ServiceException(ERROR_MESSAGE);
        }

        GradlePluginsSummary summary;
        
        try {
            summary = parser.parseSummary(response.getBody());
        }
        catch(ApiParserException e) {
            throw new ServiceException(ERROR_MESSAGE);
        }

        if(logger.isDebugEnabled()) {
            logger.debug("Retrieved plugin summary: " + summary.toString());
        }

        return summary;
    }
}