package com.repowizard.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    private TemplateHealthCheck(String template) {
        this.template = template;
    }

    public static TemplateHealthCheck of(String template){
        return new TemplateHealthCheck(template);
    }

    @Override
    protected Result check() throws Exception {
        final String fala = String.format(template,"TEST");
        if (!fala.contains("TEST")){
            return Result.unhealthy("template não possui valor em 'name'");
        }
        return Result.healthy();
    }
}
