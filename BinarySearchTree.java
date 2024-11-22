/*
 * Name : Rahul Ganeshwar Patil
 * Date : 22/11/24
 * Description : [ This file makes the Binary Search Tree and performs operations like Display Binary Search Tree, mirror given BST,
 * 				check whether the two BST's are equal or not 
 * 				and add a key value to all nodes except root also it will update the existing tree. ]
 * **/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BinarySearchTree {
    static Scanner input = new Scanner(System.in);
    private Node root;
    static class Node {
        private int key;
        private Node left, right;
        
        public Node(int value) {
            key = value;
            left = right = null;
        }
    }
    /*This function is used to insert nodes in the binary search tree*/
    public Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }
    
    /*This function prints the binary search tree */
    public String toString() {
        String tree = "";
        if (root == null) {
            return tree + Constant.EMPTY_TREE;
        }
        
        int height = getHeight(root);
        int width = (int)Math.pow(2, height + 1);
        
        String[][] levels = new String[height][width];
        fillLevels(root, 0, 0, width - 1, levels);
        
        for (String[] level : levels) {
            for (String node : level) {
                tree += (node == null ? " " : node);
            }
            tree += "\n";
        }
        return tree;
    }
    /*This function returns the maximum height of the binary search tree.*/
    private int getHeight(Node node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private void fillLevels(Node node, int level, int left, int right, String[][] levels) {
        if (node == null) return;

        int mid = (left + right) / 2;
        
        levels[level][mid] = String.valueOf(node.key);
        
        if (node.left != null) {
            for (int i = mid - 1; i > (left + mid) / 2; i--) {
                levels[level][i] = "_";
            }
        }
        if (node.right != null) {
            for (int i = mid + 1; i < (right + mid) / 2; i++) {
                levels[level][i] = "_";
            }
        }

        fillLevels(node.left, level + 1, left, mid - 1, levels);
        fillLevels(node.right, level + 1, mid + 1, right, levels);
    }
    
    /*This function mirrors the existing binary search tree*/
    public static Node mirror(Node root) {
        if (root == null) return null;
        
        Node left = mirror(root.left);
        Node right = mirror(root.right);
        root.left = right;
        root.right = left;

        return root;
    }
    /*This function checks whether the two binary trees are equal or not*/
    public boolean isEqual(BinarySearchTree newBST) {
        return isEqualHelper(this.root, newBST.root);
    }

    private boolean isEqualHelper(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return (node1.key == node2.key) && 
               isEqualHelper(node1.left, node2.left) && 
               isEqualHelper(node1.right, node2.right);
    }
    public void collectValues(Node node, ArrayList<Integer> values) {
        if (node == null) return;
        collectValues(node.left, values);
        values.add(node.key);
        collectValues(node.right, values);
    }
    /*This function is used to add key values to all the nodes except root*/
    public void updateNodes(Node node, int value) {
        if (node == null) return;
        if (node != root) {
            node.key += value;
        }
        updateNodes(node.left, value);
        updateNodes(node.right, value);
    }
    /*This function helps to rebuild the binary search tree after adding key values*/
    public Node rebuildBST(ArrayList<Integer> values) {
        if (values.isEmpty()) {
            return null;
        }
        Collections.sort(values);
        return rebuildBSTFromSortedArray(values, 0, values.size() - 1);
    }
    private Node rebuildBSTFromSortedArray(ArrayList<Integer> values, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(values.get(mid));
        node.left = rebuildBSTFromSortedArray(values, start, mid - 1);
        node.right = rebuildBSTFromSortedArray(values, mid + 1, end);

        return node;
    }
    public static void main(String args[]) {
        int key = 0;
        BinarySearchTree BST = new BinarySearchTree();
        BinarySearchTree BST1 = new BinarySearchTree();
        System.out.println(Constant.ENTER_RANGE);
        int range = input.nextInt();
        input.nextLine();
        //Input BST 1
        System.out.println(Constant.ENTER_PROMPT);
        for (int i = 0; i < range; i++) {
            key = input.nextInt();
            input.nextLine();
            BST.root = BST.insert(BST.root, key);
        }
        System.out.println(BST);
        System.out.println(Constant.ENTER_RANGE);
        range = input.nextInt();
        input.nextLine();
        //Enter BST 2 
        System.out.println(Constant.ENTER_PROMPT);
        for (int i = 0; i < range; i++) {
            key = input.nextInt();
            input.nextLine();
            BST1.root = BST1.insert(BST1.root, key);
        }
        System.out.println(BST1);
        //Compare BST 1 and BST 2
        if (BST.isEqual(BST1)) {
            System.out.println(Constant.EQUAL_BST);
        } else {
            System.out.println(Constant.NOT_EQUAL_BST);
        }
        //Add Key values to all the nodes
        System.out.println(Constant.ENTER_ADD);
        int addValue = input.nextInt();
        input.nextLine();
        BST.updateNodes(BST.root, addValue);
        ArrayList<Integer> updatedValues = new ArrayList<>();
        BST.collectValues(BST.root, updatedValues);
        BST.root = BST.rebuildBST(updatedValues);
        System.out.println(Constant.UPDATED_TREE);
        System.out.println(BST);
}
}
