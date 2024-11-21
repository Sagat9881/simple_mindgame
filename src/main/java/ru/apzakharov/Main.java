package ru.apzakharov;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import ru.apzakharov.engine.GameEngine;
import ru.apzakharov.game.Game;
import ru.apzakharov.game.MathGame;
import ru.apzakharov.game.GuessTheNumber;

public class Main {

  public static void main(String[] args) {
    Map<Long, Game> games = new HashMap<>();
    games.put(0L,new GuessTheNumber());
    games.put(1L,new MathGame());
    GameEngine gameEngine =
        new GameEngine(games, System.out, new BufferedReader(new InputStreamReader(System.in)));
    gameEngine.start();
  }

}
