class Main {

  public static void main(String[] args) {
    DecisionTree tree = new DecisionTree("Is it a Mikky?");
    tree.setRight(new DecisionTree("Mouse"));
    tree.setLeft(new DecisionTree("Lives in desert?"));
    tree.getLeft().setLeft(new DecisionTree("Crockodile?"));
    System.out.println(tree.toString());
    

    tree.getRight().updateLeaf("Bee", "Boo");
    System.out.println(tree.toString());  
  }

  

}