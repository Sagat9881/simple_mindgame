package ru.apzakharov.engine.operation;

import java.io.BufferedReader;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import ru.apzakharov.game.Game;

@RequiredArgsConstructor
public class AcceptResult implements EngineOperation {

  private final BufferedReader input;

  @Override
  public void visit(Game game) {
    try {
      game.accept(input.readLine());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
