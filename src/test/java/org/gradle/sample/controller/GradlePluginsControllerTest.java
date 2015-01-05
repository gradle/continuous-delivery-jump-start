package org.gradle.sample.controller;

import org.gradle.sample.data.GradlePluginsSummary;
import org.gradle.sample.service.BuildInfoService;
import org.gradle.sample.service.GradlePluginsService;
import org.gradle.sample.utils.BuildInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GradlePluginsControllerTest {
    private GradlePluginsService gradlePluginsService = mock(GradlePluginsService.class);
    private BuildInfoService buildInfoService = mock(BuildInfoService.class);
    private GradlePluginsController controller;

    @Before
    public void setup() {
        controller = new GradlePluginsController(gradlePluginsService, buildInfoService);
    }

    @Test
    public void getPluginsSummary() {
        GradlePluginsSummary expectedSummary = new GradlePluginsSummary();
        BuildInfo expectedBuildInfo = new BuildInfo();
        expectedSummary.setCount(217L);

        when(gradlePluginsService.getSummary()).thenReturn(expectedSummary);
        when(buildInfoService.getBuildInfo()).thenReturn(expectedBuildInfo);

        ModelAndView modelAndView = controller.getPluginsSummary();
        assertEquals("plugins", modelAndView.getViewName());
        assertEquals(expectedSummary, modelAndView.getModelMap().get("summary"));
        assertEquals(expectedBuildInfo, modelAndView.getModelMap().get("buildInfo"));

        verify(gradlePluginsService).getSummary();
        verify(buildInfoService).getBuildInfo();
    }
}
