package refactored;

import io.jooby.annotations.*;

@Path("/")
public class Init {

  @GET
  public String sayHi() {
    return "Linux bota windows pra mamar!";
  }
}
