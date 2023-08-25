package org.acme;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.vertx.ext.mail.MailMessage;
import io.vertx.mutiny.ext.mail.MailClient;
//import org.eclipse.microprofile.config.inject.ConfigProperty;
//import org.jboss.resteasy.annotations.jaxrs.FormParam;
//import org.jboss.resteasy.annotations.jaxrs.PathParam;
//import org.jboss.resteasy.annotations.jaxrs.QueryParam;
//import org.jboss.resteasy.annotations.jaxrs.QueryParams;
import io.smallrye.mutiny.Uni;
//import io.vertx.mutiny.mail.MailClient;
//import io.vertx.mutiny.mail.MailConfig;
//import io.vertx.mutiny.mail.MailMessage;

@Path("/send")
public class MailResource {
    @Inject
    MailClient mailClient;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> sendEmail(
            @FormParam("to") String to,
            @FormParam("subject") String subject,
            @FormParam("body") String body
    ) {
        MailMessage message = new MailMessage()
                .setFrom("noreply@example.com")
                .setTo(to)
                .setSubject(subject)
                .setText(body);
        return mailClient.sendMail(message).map(id -> "Mail sent with id " + id);
    }
}
