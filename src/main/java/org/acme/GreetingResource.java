package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.mutiny.Uni;

import java.util.Collections;

@Path("/hello")
public class GreetingResource {

    @Inject
    Mailer mailer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Path("/mail")
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public Object mail(MailModel ma) {
        Mail mail = new Mail()
                .setFrom(ma.from)
                .setTo(Collections.singletonList(ma.to))
                .setSubject(ma.subject)
                .setText(ma.text);
        mailer.send(mail);
        return mail;
    }

}