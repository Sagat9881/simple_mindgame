package ru.apzakharov.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

  public static void main(String[] args) {
//    String resolve = MathUtil.infixToPostfix("2+ 2*2+2");
    String expression ="26/54+3*48+67+99+75+58";
//        expression();
    System.out.println(expression);
    String resolve = MathUtil.resolve(expression);
    System.out.println(expression + "=" + resolve);
  }

  public static String expression() {
    String[] operands = {"+", "-", "*", "/"};
    Random random = new Random();
    int blocks = random.nextInt(4,5);

    return IntStream.range(0, blocks)
        .mapToObj(i -> simpleExpression(operands, random))
        .collect(Collectors.joining(operands[random.nextInt(4)]));
  }

  private static String simpleExpression(String[] operands, Random random) {
    return random.nextInt(100) + "" + operands[random.nextInt(4)] + "" + random.nextInt(100);
  }

  public static String resolve(String mathExpression) {
    mathExpression = mathExpression.replace(" ", "");
    mathExpression = MathUtil.infixToPostfix(mathExpression);
    return String.valueOf(
        resolve(mathExpression.toCharArray())
    );
  }

  private static BigDecimal resolve(char[] mathExpression) {
    Deque<String> stack = new LinkedList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < mathExpression.length; i++) {
      char token = mathExpression[i];
      switch (token) {
        case '+': {
          String operand1 = stack.poll();
          String operand2 = stack.poll();
          stack.push(
              String.valueOf(Double.parseDouble(operand2) + Double.parseDouble(operand1)));
          break;
        }
        case '-': {
          String operand1 = stack.poll();
          String operand2 = stack.poll();
          stack.push(String.valueOf(Double.parseDouble(operand2) - Double.parseDouble(operand1)));
          break;
        }
        case '*': {
          String operand1 = stack.poll();
          String operand2 = stack.poll();
          stack.push(String.valueOf(Double.parseDouble(operand2) * Double.parseDouble(operand1)));
          break;
        }
        case '/': {
          String operand1 = stack.poll();
          String operand2 = stack.poll();
          stack.push(String.valueOf(Double.parseDouble(operand2) / Double.parseDouble(operand1)));
          break;
        }
        default:
          if (token == ' ' && !sb.isEmpty()) {
            stack.push(sb.toString());
            sb = new StringBuilder();
          } else if (token!=' ') {
            sb.append(token);
          }

      }

    }
    return BigDecimal.valueOf(
        Double.parseDouble(String.join("", stack)))
        .setScale(1, RoundingMode.HALF_UP);
  }

  public static String infixToPostfix(String infixMathExpression) {
    Deque<String> deque = new LinkedList<>();
    Deque<String> stack = new LinkedList<>();
    char[] mathExpression = infixMathExpression.toCharArray();
    int startIndx = 0;
    int endIndx = mathExpression.length;
    for (int i = startIndx; i < endIndx; i++) {
      char charToken = mathExpression[i];
      String token = String.valueOf(charToken);
      switch (token) {
        case "+", "-", "*", "/": {
          int stackOperandPriority = getPriority(stack.peek());
          int tokenOperandPriority = getPriority(token);
          if (stack.isEmpty() || stackOperandPriority < tokenOperandPriority) {
            stack.push(token);
            deque.offer(" ");
          }
          if (stackOperandPriority == tokenOperandPriority) {
            deque.offer(" ");
            deque.offer(stack.poll());
            deque.offer(" ");
            stack.push(token);
          }
          if (stackOperandPriority > tokenOperandPriority) {
            while (!stack.isEmpty()) {
              deque.offer(" ");
              deque.offer(stack.poll());
              deque.offer(" ");
              String peek = stack.peek();
              if (getPriority(peek) == tokenOperandPriority) {
                deque.offer(stack.poll());
                deque.offer(" ");
                stack.push(token);
                break;
              }
              if (getPriority(peek) < tokenOperandPriority) {
                stack.push(token);
                break;
              }
            }
          }
          break;
        }
        default:
          deque.offer(token);
      }
    }
    return String.join("", deque) + " " + String.join(" ", stack);
  }


  private static int getPriority(String token) {
    return switch (token) {
      case "^" -> 100;
      case "*", "/" -> 50;
      case "+", "-" -> 10;
      case null -> 0;
      default -> throw new IllegalArgumentException();
    };
  }

}
