package refactored;

import io.jooby.Jooby;

public class App extends Jooby {

  {
    mvc(new Init());
    mvc(new Divisao());
  }

  public static void main(final String[] args) {
    runApp(args, App::new);
  }

}
