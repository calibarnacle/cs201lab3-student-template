import java.util.*;

import javax.swing.tree.TreeNode;

public class Q1Test {
    public static void main(String[] args) {

        String result, traversal1, traversal2, traversal3;

        traversal1 = "1-2";
        traversal2 = "2-1-3";
        traversal3 = "1-3-2";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println("Expected Result : Invalid traversals");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "1-2-3";
        traversal2 = "2-1-3";
        traversal3 = "1-3-2";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "1-2-3";
        traversal2 = "3-2-1";
        traversal3 = "2-3-1";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println("Expected Result : Invalid traversals");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "3-1-2-5-4";
        traversal2 = "2-1-4-5-3";
        traversal3 = "1-2-3-4-5";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "10-20-30-40-50";
        traversal2 = "20-10-30-40-50";
        traversal3 = "10-50-40-30-20";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "10-12-13-15-16-18-19-23-24-25-27-31-32-35-37-39-47-55-57-58-62-75-79-83-94-102-108-109-112-130-138-139-142-143-144-145-147-148-149-150-156-161-162-167-171-177-183-190-195-197";
        traversal2 = "83-39-13-10-12-19-15-18-16-25-23-24-32-31-27-35-37-55-47-62-58-57-75-79-102-94-167-138-130-112-108-109-150-142-139-145-143-144-148-147-149-162-161-156-190-177-171-183-197-195";
        traversal3 = "12-10-16-18-15-24-23-27-31-37-35-32-25-19-13-47-57-58-79-75-62-55-39-94-109-108-112-130-139-144-143-147-149-148-145-142-156-161-162-150-138-171-183-177-195-197-190-167-102-83";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "10-20"; // Inorder
        traversal2 = "10-20"; // Preorder
        traversal3 = "20-10"; // Postorder
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("  Actual Result : " + result);

    } 

    // write your codes here
    public static String verify(String traversal1, String traversal2, String traversal3) {
        
        // validation of inputs
        String[] rawInputs = {traversal1, traversal2, traversal3};
        int[][] inputs = new int[3][];
        Set<Integer>[] sets = new HashSet[3];

        for (int i = 0; i < 3; i++) {
            if (rawInputs[i] == null || rawInputs[i].isEmpty()) return "Invalid traversals";

            String[] parts = rawInputs[i].split("-");
            inputs[i] = new int[parts.length];
            sets[i] = new HashSet<>();

            for (int j = 0; j < parts.length; j++) {
                try {
                    int val = Integer.parseInt(parts[j]);
                    inputs[i][j] = val;
                    sets[i].add(val);
                } catch (NumberFormatException e) { return "Invalid traversals"; }

            }
            if (sets[i].size() != inputs[i].length || inputs[i].length == 0) 
                return "Invalid traversals";
        }
        // check all 3 traversals contain identical elements
        if (!sets[0].equals(sets[1]) || !sets[1].equals(sets[2])) {
            return "Invalid traversals";
        }

        // possible permutations of 3 input traversals as in/pre/postorder
        int[][] permutations = {
            {0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}
        };
        for (int[] p : permutations) {
            int[] in   = inputs[p[0]];
            int[] pre  = inputs[p[1]];
            int[] post = inputs[p[2]];

            if (!isValidBSTCombination(in, pre, post)) continue;
            
            String[] types = new String[3];
            types[p[0]] = "Inorder";
            types[p[1]] = "Preorder";
            types[p[2]] = "Postorder";

            return String.format("Traversal 1 - %s, Traversal 2 - %s, Traversal 3 - %s",
                    types[0], types[1], types[2]);
        
        }
        // fallthrough if 3 traversals do not match any combi of int/pre/post
        return "Invalid traversals";
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
    private static int preIndex;
    private static int smallest;
    private static int biggest;

    private static boolean isValidBSTCombination(int[] in, int[] pre, int[] post) {
        int n = in.length;

        // inorder strictly in ascending order
        for (int i = 0; i < n - 1; i++) {
            if (in[i] >= in[i + 1]) return false;
        }
        try {   // setup tree, check input postorder traversal matches actual
            preIndex = 0;
            smallest = in[0];
            biggest = in[n-1];
            TreeNode root = treeify(pre, smallest, biggest);

            List<Integer> actualIn = new ArrayList<>();
            List<Integer> actualPost = new ArrayList<>();
            getIn(root, actualIn);
            getPost(root, actualPost);

            for (int i = 0; i < n; i++)
                if (actualIn.get(i) != in[i] ||
                    actualPost.get(i) != post[i]) return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private static TreeNode treeify(int[] pre, int min, int max) {
        if (preIndex >= pre.length) return null;
        int rootVal = pre[preIndex];
        if (rootVal < min || rootVal > max) return null;
        preIndex++;
        TreeNode root = new TreeNode(rootVal);
        root.left  = treeify(pre, min, rootVal);
        root.right = treeify(pre, rootVal, max);
        return root;
    }
    // recursively add left > root > right
    private static void getIn(TreeNode node, List<Integer> list) {
        if (node == null) return;
        getIn(node.left, list);
        list.add(node.val);
        getIn(node.right, list);
    }
    // recursively add left > right > root
    private static void getPost(TreeNode node, List<Integer> list) {
        if (node == null) return;
        getPost(node.left, list);
        getPost(node.right, list);
        list.add(node.val);
    }
}
