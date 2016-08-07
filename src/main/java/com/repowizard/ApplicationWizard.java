package com.repowizard;

import com.repowizard.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ApplicationWizard extends Application<ConfiguracaoWizard> {

    @Override
    public String getName() {
        return "Hello Word: getName()";
    }

    @Override
    public void initialize(Bootstrap<ConfiguracaoWizard> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(ConfiguracaoWizard configuracaoWizard, Environment environment) throws Exception {
        final PrincipalResource resource = PrincipalResource.of(
                configuracaoWizard.getTemplate(),
                configuracaoWizard.getDefaultName()
        );
        final TemplateHealthCheck healthCheck = TemplateHealthCheck.of(
                configuracaoWizard.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
