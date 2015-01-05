package org.gradle.sample.http;

import org.gradle.sample.data.GradlePluginsSummary;
import org.gradle.sample.service.GradlePluginsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" } )
public class GradlePluginsServiceImplIntegrationTest {
    @Autowired
    private GradlePluginsService service;

    @Test
    public void getSummary() {
        GradlePluginsSummary summary = service.getSummary();
        assertNotNull(summary);
        assertTrue(summary.getCount() > 0);
    }
}
