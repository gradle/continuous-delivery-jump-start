package org.gradle.sample.parser;

import org.gradle.sample.data.GradlePluginsSummary;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonGradlePluginsApiParser implements GradlePluginsApiParser {
    private final Logger logger = LoggerFactory.getLogger(JsonGradlePluginsApiParser.class);
    private final JSONParser parser = new JSONParser();
    
    @Override
    public GradlePluginsSummary parseSummary(String data) {
        try {
            JSONObject json = (JSONObject)parser.parse(data);
            
            GradlePluginsSummary summary = new GradlePluginsSummary();
            summary.setCount((Long)json.get("count"));
            summary.setExceptionMessage((String)json.get("exceptionMessage"));
            return summary;
        }
        catch(ParseException e) {
            logger.error("Failed to parse JSON for Gradle plugin summary response", e);
            throw new ApiParserException(e);
        }
    }
}