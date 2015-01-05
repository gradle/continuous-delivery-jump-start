package org.gradle.sample.controller;

import org.gradle.sample.data.GradlePluginsSummary;
import org.gradle.sample.service.BuildInfoService;
import org.gradle.sample.service.GradlePluginsService;
import org.gradle.sample.service.ServiceException;
import org.gradle.sample.utils.BuildInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GradlePluginsController {
    private final Logger logger = LoggerFactory.getLogger(GradlePluginsController.class);

    private final GradlePluginsService gradlePluginsService;
    private final BuildInfoService buildInfoService;

    @Autowired
    public GradlePluginsController(GradlePluginsService gradlePluginsService, BuildInfoService buildInfoService) {
        this.gradlePluginsService = gradlePluginsService;
        this.buildInfoService = buildInfoService;
    }

    @RequestMapping(value = "/plugins/summary", method = RequestMethod.GET)
    public ModelAndView getPluginsSummary() {
        logger.debug("Getting Gradle plugins summary");

        ModelAndView model = new ModelAndView();
        model.setViewName("plugins");
        addSummaryToModel(model);
        addBuildInfoToModel(model);

        return model;
    }

    private void addSummaryToModel(ModelAndView model) {
        try {
            GradlePluginsSummary summary = gradlePluginsService.getSummary();
            model.addObject("summary", summary);
        }
        catch(ServiceException e) {
            model.addObject("error", "At this time we cannot retrieve any plugin information.");
        }
    }

    private void addBuildInfoToModel(ModelAndView model) {
        BuildInfo buildInfo = buildInfoService.getBuildInfo();

        if (buildInfo != null) {
            model.addObject("buildInfo", buildInfo);
        }
    }
}