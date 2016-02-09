package com.github.saem.admin;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.AdminServlet;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.codahale.metrics.servlets.MetricsServlet;
import io.undertow.server.HttpHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import javax.servlet.ServletException;

public final class AdminHandlerFactory {

    public static HttpHandler buildHandler(
            final HealthCheckRegistry healthChecks,
            final MetricRegistry metrics) {
        final DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(AdminHandlerFactory.class.getClassLoader())
                .setContextPath("/")
                .setDeploymentName("admin.war")
                .addServletContextAttribute(
                        MetricsServlet.METRICS_REGISTRY,
                        metrics
                )
                .addServletContextAttribute(
                        HealthCheckServlet.HEALTH_CHECK_REGISTRY,
                        healthChecks)
                .addServlets(
                        Servlets.servlet("AdminServlet", AdminServlet.class)
                                .addMapping("/*"));

        final DeploymentManager manager = Servlets.defaultContainer()
                .addDeployment(servletBuilder);
        manager.deploy();

        try {
            return manager.start();
        } catch (final ServletException e) {
            throw new RuntimeException("The admin servlet isn't working", e);
        }
    }
}
