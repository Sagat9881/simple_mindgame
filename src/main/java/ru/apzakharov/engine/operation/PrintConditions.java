package ru.apzakharov.engine.operation;

import java.io.PrintStream;
import lombok.RequiredArgsConstructor;
import ru.apzakharov.game.Game;

@RequiredArgsConstructor
public class PrintConditions implements EngineOperation {

  private final PrintStream out;

  @Override
  public void visit(Game game) {

    out.println(game.condition());

  }
}
