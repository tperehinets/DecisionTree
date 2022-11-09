import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AnimalGuess {

  public static boolean answer(String answer) {

    if (answer.equals("y") || answer.equals("Y") || answer.equals("yes") || answer.equals("Yes")) {
      return true;

    } else {
      return false;
    }
  }

  // method to play a game from scratch
  public static void play() {
    DecisionTree root = new DecisionTree("Mouse");

    System.out.println("Enter number of rounds you want to play");
    Scanner scannerRounds = new Scanner(System.in);
    int rounds = Integer.parseInt(scannerRounds.nextLine());
    for (int i = rounds; i > 0; i--) {
      DecisionTree current = root;
      while (current.getRight() != null) {
        System.out.println("Is that " + current.getData());
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        // scanner.close();
        if (answer(answer)) {
          current = current.getLeft();
        } else {
          current = current.getRight();
        }
      }

      // then check if the leaf is a right answer, and if no - update a tree
      System.out.println("Is " + current + " your animal?");
      Scanner YN = new Scanner(System.in);
      String yn = YN.nextLine();
      // YN.close();

      if (answer(yn)) {
        System.out.println("I have guessed!");
      } else {
        System.out.println("I could't guess, let me learn");
        System.out.println("What was your guess?");
        Scanner scannerGuess = new Scanner(System.in);
        String guess = scannerGuess.nextLine();
        // scannerGuess.close();

        System.out.println("Type a yes or a no question that would distinguish " + guess + " from " + current);
        Scanner ScannerQuestion = new Scanner(System.in);
        String question = ScannerQuestion.nextLine();
        // ScannerQuestion.close();
        current.updateLeaf(question, guess);
      }

    }

    scannerRounds.close();

    root.writeToFile(root);

  }

  // method to play a game from the trained tree

  public static void play(String filename) {
    DecisionTree root = readFromFile(filename);
    // play the game with the existing tree
    System.out.println("Enter number of rounds you want to play");
    Scanner scannerRounds = new Scanner(System.in);
    int rounds = Integer.parseInt(scannerRounds.nextLine());
    for (int i = rounds; i > 0; i--) {
      DecisionTree current = root;
      while (current.getRight() != null) {
        System.out.println(current.getData());
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        // scanner.close();
        if (answer(answer)) {
          current = current.getLeft();
        } else {
          current = current.getRight();
        }
      }

      // then check if the leaf is a right answer, and if no - update a tree
      System.out.println("Is " + current.getData() + " your animal?");
      Scanner YN = new Scanner(System.in);
      String yn = YN.nextLine();
      // YN.close();

      if (answer(yn)) {
        System.out.println("I have guessed!");
      } else {
        System.out.println("I could't guess, let me learn");
        System.out.println("What was your guess?");
        Scanner scannerGuess = new Scanner(System.in);
        String guess = scannerGuess.nextLine();
        // scannerGuess.close();

        System.out.println("Type a yes or a no question that would distinguish " + guess + " from " + current);
        Scanner ScannerQuestion = new Scanner(System.in);
        String question = ScannerQuestion.nextLine();
        // ScannerQuestion.close();
        current.updateLeaf(question, guess);
      }

    }

    scannerRounds.close();

    root.writeToFile(root);

  }

  public static DecisionTree readFromFile(String filename) {
    DecisionTree root = null;
    try {
      // read form the file
      File tree = new File(filename);
      Scanner read = new Scanner(tree);
      String rootData = read.nextLine();
      root = new DecisionTree(rootData.substring(1, rootData.length()));

      while (read.hasNextLine()) {
        String line = read.nextLine();

        String path = line.split(" ")[0];
        String data = line.replace(path, "");
        data = data.substring(1, data.length());
        DecisionTree node = new DecisionTree(data);

        if (path.length() == 1) {
          if (path.equals("Y")) {
            root.setLeft(node);
          } else {
            root.setRight(node);
          }
        } else {
          DecisionTree current = root.followPath(path.substring(0, path.length() - 1));
          if (path.charAt(path.length() - 1) == 'Y') {
            current.setLeft(node);
          } else {
            current.setRight(node);
          }
        }

      }

    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    return root;
  }

  public static void main(String[] args) {
    if (args.length != 0) {
      play(args[0]);
    } else {

      // play("AnimalTree.txt");

    }

  }

}
