package ru.apzakharov.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.apzakharov.engine.operation.AcceptResult;
import ru.apzakharov.engine.operation.PrintConditions;
import ru.apzakharov.engine.operation.PrintResult;
import ru.apzakharov.game.Game;

@RequiredArgsConstructor
public class GameEngine {

  private static final String PRINT_FOR_EXIT = "Для выхода введите -1";
  private static final String PRINT_FOR_CONTINUATION = "Для продолжения введите 1";
  private static final String PRINT_NUMBER = "Введите число, соответствующее выбранной игре";
  private static final String GAME_LIST = "Список представленных игр:";
  private static final String NOT_NUMBER_INPUT = "Введено не число";
  private final Map<Long, Game> games;
  private final PrintStream out;
  private final BufferedReader in;


  public void start() {
    try {
      printExistingGames();
      long key = Long.parseLong(in.readLine());
      if (key >= 0) {
        Game game = games.get(key);
        processGame(game);
        start();
      } else System.exit(0);
    } catch (NumberFormatException ne) {
      out.println(NOT_NUMBER_INPUT);
      start();
    } catch (Exception e) {
      out.println(e.getMessage());
    }
  }

  private void processGame(Game game) throws IOException {
    while (true) {
      new PrintConditions(out).visit(game);
      new AcceptResult(in).visit(game);
      new PrintResult(out).visit(game);

      out.println(PRINT_FOR_EXIT+" "+PRINT_FOR_CONTINUATION);
      long key = Long.parseLong(in.readLine());
      if (key < 0) break;
    }
  }

  private void printExistingGames() {
    String games =
        this.games.entrySet().stream().map(entry -> entry.getKey() + " :  " + entry.getValue().getClass().getSimpleName())
            .collect(Collectors.joining("\n"));
    out.println(GAME_LIST);
    out.println(games);
    out.println(PRINT_NUMBER);
    out.println(PRINT_FOR_EXIT);
  }

}
