package org.gradle.sample.service;

import org.gradle.sample.utils.BuildInfo;
import org.gradle.sample.utils.BuildInfoReader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BuildInfoServiceImpl implements BuildInfoService {
    private BuildInfo buildInfo;

    @PostConstruct
    public void readBuildInfo() {
        BuildInfoReader buildInfoReader = new BuildInfoReader();
        buildInfo = buildInfoReader.read();
    }

    @Override
    public BuildInfo getBuildInfo() {
        return buildInfo;
    }
}
