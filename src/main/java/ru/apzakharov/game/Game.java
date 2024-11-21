package ru.apzakharov.game;

import ru.apzakharov.engine.operation.EngineOperation;

public interface Game {

  default void visit(EngineOperation visitor) {
    visitor.visit(this);
  }

  String condition();

   void accept(String answer);
  boolean isAccepted();

  String solution();

}
