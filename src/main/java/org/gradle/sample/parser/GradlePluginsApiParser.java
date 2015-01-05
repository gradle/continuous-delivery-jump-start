package org.gradle.sample.parser;

import org.gradle.sample.data.GradlePluginsSummary;

public interface GradlePluginsApiParser {
    GradlePluginsSummary parseSummary(String data);
}