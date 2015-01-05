package org.gradle.sample.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BuildInfoReader {
    private final Logger logger = LoggerFactory.getLogger(BuildInfoReader.class);

    public BuildInfo read() {
        Properties props = new Properties();

        try {
            props.load(getClass().getResourceAsStream("/build-info.properties"));
            BuildInfo buildInfo = new BuildInfo();
            buildInfo.setVersion(props.getProperty("version"));
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            buildInfo.setTimestamp(formatter.parse(props.getProperty("timestamp")));
            return buildInfo;
        } catch (Exception e) {
            if(logger.isWarnEnabled()) {
                logger.warn("Failed to read build info file. Reason: " + e.getMessage());
            }
        }

        return null;
    }
}