package resource;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.impl.Slf4jLogger;

import dto.MessageDTO;
import io.quarkus.logging.Log;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

  @Channel("quarkus-rabbitmq")
  Emitter<MessageDTO> emmiter;

  @GET
  public String test() {
    MessageDTO obj = create();
    Log.infof("--- enviando message ---", obj.toString());
    emmiter.send(obj);
    return "OK";
  }

  public MessageDTO create() {
    return MessageDTO.builder().age(30)
        .name("Victor")
        .build();
  }

}
