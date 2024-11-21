package ru.apzakharov.engine.operation;

import java.io.PrintStream;
import lombok.RequiredArgsConstructor;
import ru.apzakharov.engine.operation.EngineOperation;
import ru.apzakharov.game.Game;

@RequiredArgsConstructor
public class PrintResult implements EngineOperation {

  public static final String EXPECTED_SOLUTION = "Ожидаемое решение: ";
  private static final String SUCCESS = "Верное решение!";
  private static final String FAIL = "Решение не верное";
  private final PrintStream out;

  @Override
  public void visit(Game game) {
    String isAccepted = game.isAccepted() ? SUCCESS : FAIL;
    out.println(isAccepted);

    if (!game.isAccepted()) out.printf(EXPECTED_SOLUTION + game.solution()+"\n");
  }
}
