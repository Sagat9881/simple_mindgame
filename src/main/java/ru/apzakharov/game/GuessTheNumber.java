package ru.apzakharov.game;

import java.util.Random;

public class GuessTheNumber implements Game {

  private static String VALUE = "1";
  private boolean isAccepted;

  @Override
  public String condition() {
    VALUE = String.valueOf(new Random().nextInt(1,3));
    return "Угадайте число ( 1 или 2 )";
  }

  @Override
  public void accept(String answer) {
    isAccepted = this.solution().equals(answer);
  }

  @Override
  public boolean isAccepted() {
    return isAccepted;
  }

  @Override
  public String solution() {
    return VALUE;
  }
}
