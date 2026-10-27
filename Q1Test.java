import java.util.Arrays;

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
     }

     // arr1 = preorder, arr2 = postorder, arr3 = inorder
     // Rebuild the postorder from pre + in and compare it with arr2.
     public static boolean isPrePostRespectively(String[] arr1, String[] arr2, String[] arr3) {
          if (arr1.length != arr2.length || arr1.length != arr3.length) {
               return false;
          }
          // ponytail: assumes a BST (inorder strictly increasing); drop if general trees are intended
          for (int j = 1; j < arr3.length; j++) {
               if (Integer.parseInt(arr3[j - 1]) >= Integer.parseInt(arr3[j])) {
                    return false;
               }
          }
          java.util.List<String> post = new java.util.ArrayList<>();
          if (!buildPost(arr1, 0, arr3, 0, arr1.length, post)) {
               return false;
          }
          return post.equals(Arrays.asList(arr2));
     }

     private static boolean buildPost(String[] pre, int p, String[] in, int i, int n, java.util.List<String> post) {
          if (n == 0) {
               return true;
          }
          int k = 0; // size of left subtree
          while (k < n && !in[i + k].equals(pre[p])) {
               k++;
          }
          if (k == n) {
               return false; // root missing from inorder
          }
          if (!buildPost(pre, p + 1, in, i, k, post) || !buildPost(pre, p + 1 + k, in, i + k + 1, n - k - 1, post)) {
               return false;
          }
          post.add(pre[p]);
          return true;
     }

     // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3) {
          if (traversal1.length() != traversal2.length()
                    || traversal1.length() != traversal3.length()
                    || traversal2.length() != traversal3.length()) {
               return "Invalid traversals";
          }
          // First, we split the traversals into arrays first
          String[] t1 = traversal1.split("-");
          String[] t2 = traversal2.split("-");
          String[] t3 = traversal3.split("-");

          String r1;
          String r2;
          String r3;

          if (isPrePostRespectively(t1, t2, t3)) {
               r1 = "Preorder";
               r2 = "Postorder";
               r3 = "Inorder";
          } else if (isPrePostRespectively(t2, t1, t3)) {
               r2 = "Preorder";
               r1 = "Postorder";
               r3 = "Inorder";
          } else if (isPrePostRespectively(t1, t3, t2)) {
               r1 = "Preorder";
               r3 = "Postorder";
               r2 = "Inorder";
          } else if (isPrePostRespectively(t3, t1, t2)) {
               r3 = "Preorder";
               r1 = "Postorder";
               r2 = "Inorder";
          } else if (isPrePostRespectively(t2, t3, t1)) {
               r2 = "Preorder";
               r3 = "Postorder";
               r1 = "Inorder";
          } else if (isPrePostRespectively(t3, t2, t1)) {
               r3 = "Preorder";
               r2 = "Postorder";
               r1 = "Inorder";
          } else {
               return "Invalid traversals";
          }

          return String.format("Traversal 1 - %s, Traversal 2 - %s, Traversal 3 - %s", r1, r2, r3);
     }
}