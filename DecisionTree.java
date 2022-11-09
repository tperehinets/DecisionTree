
/**
 * Implements a binary decision tree
 *
 * @author Tetiana Perehinets
 * @version Fall 2022
 *
 */
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DecisionTree extends BinaryTree<String> {
  /** leaf constructor */
  public DecisionTree(String s) {
    super(s);
  }

  /** @override */
  public void setLeft(BinaryTree<String> left) {
    if ((left == null) || (left instanceof DecisionTree)) {
      super.setLeft(left);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  public void setRight(BinaryTree<String> right) {
    if ((right == null) || (right instanceof DecisionTree)) {
      super.setRight(right);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** @override */
  public DecisionTree getLeft() {
    return (DecisionTree) super.getLeft();
  }

  public DecisionTree getRight() {
    return (DecisionTree) super.getRight();
  }

  public DecisionTree followPath(String path) {
    DecisionTree current = this;
    for (int i = 0; i < path.length(); i++) {
      if (path.charAt(i) == 'Y') {
        current = current.getLeft();
      } else {
        current = current.getRight();
      }
    }

    return current;
  }

  public void updateLeaf(String data, String rightData) {
    String question = this.getData();
    this.setData(data);
    this.setLeft(new DecisionTree(question));
    DecisionTree right = new DecisionTree(rightData);
    this.setRight(right);
  }

  public void writeToFile(DecisionTree root) {
    try {
      PrintWriter out = new PrintWriter(new FileWriter("AnimalTree.txt"));
      ArrayDeque<DecisionTree> questions = new ArrayDeque<DecisionTree>();
      ArrayDeque<String> paths = new ArrayDeque<String>();

      questions.add(root);
      // paths.add(" ");

      while (!questions.isEmpty()) {
        DecisionTree n = questions.poll();
        String path = "";
        if (!paths.isEmpty()) {
          path = paths.poll();

        }

        if (n == root) {
          out.println(" " + n.getData());
        } else {
          out.println(path + " " + n.getData());

        }

        if (n.getLeft() != null) {
          questions.add(n.getLeft());
          paths.add(path + "Y");
        }

        if (n.getRight() != null) {
          questions.add(n.getRight());
          paths.add(path + "N");
        }

      }
      out.close();

    } catch (Exception e) {
      System.out.println(e);
    }

  }

}