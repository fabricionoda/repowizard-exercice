package com.repowizard;

import com.codahale.metrics.annotation.Timed;
import com.repowizard.api.Fala;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@Path("/principal")
@Produces(MediaType.APPLICATION_JSON)
public class PrincipalResource {

    private final String template;
    private final String defaltName;
    private final AtomicLong counter;

    protected PrincipalResource(String template, String defaltName) {
        this.template = template;
        this.defaltName = defaltName;
        this.counter = new AtomicLong();
    }

    public static PrincipalResource of(String template, String defaltName){
        return new PrincipalResource(template,defaltName);
    }

    @GET
    @Timed
    public Fala digaOi(@QueryParam("name") Optional<String> name){
        final String value = String.format(template, name.orElse(defaltName));
        return new Fala(counter.incrementAndGet(),value);
    }
}
