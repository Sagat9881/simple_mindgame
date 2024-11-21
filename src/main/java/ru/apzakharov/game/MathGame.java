package ru.apzakharov.game;

import ru.apzakharov.util.MathUtil;

public class MathGame implements Game{
  private static String EXPRESSION;
  boolean isAccepted;
  @Override
  public String condition() {
    EXPRESSION = MathUtil.expression();
    return "Решить пример: "+ EXPRESSION +("\nТочность после запятой до 1-ого знака со стандартным округлением вверх");
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
    return MathUtil.resolve(EXPRESSION);
  }
}
