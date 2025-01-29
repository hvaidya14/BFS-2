/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> result = new ArrayList<Integer>();
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    public List<Integer> rightSideView(TreeNode root) {
        
        if (root == null) {
            return result;
        }
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            boolean added=false;
            for (int i=0;i<size;i++) {
                TreeNode curr = q.poll();
                if (i==size-1) {
                    result.add(curr.val);
                }
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
        }

        return result;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> result = new ArrayList<Integer>();

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return result;
        }

        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int lvl) {
        if (root == null) {
            return;
        }

        if (lvl == result.size()) {
            result.add(root.val);
        }

        dfs(root.right, lvl+1);
        dfs(root.left, lvl+1);
    }
}



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode xparent=null;
    TreeNode yparent=null;   
    int xlvl=0;
    int ylvl=0;     
    public boolean isCousins(TreeNode root, int x, int y) {
        
        dfs(root, null, 0, x, y);
        if (xparent == yparent || xlvl != ylvl) {
            System.out.println(xparent);
            System.out.println(yparent);
            System.out.println(xlvl);
            System.out.println(ylvl);
            return false;
        }
        return true;

    }
    private void dfs(TreeNode root, TreeNode parent, int lvl, int x, int y) {
        if (root == null) {
            return;
        }
        if (xlvl != 0 && ylvl != 0) {
            return;
        }
        if(root.val == x ) {
            xparent = parent;
            xlvl=lvl;
        }
        if (root.val == y) {
            yparent = parent;
            ylvl=lvl;
        }
        dfs(root.left, root, lvl+1, x, y);
        dfs(root.right, root, lvl+1, x, y);
    }
}


class Solution {
    Map<Integer, List<Integer>> m = new HashMap<Integer, List<Integer>>();
    Queue<Integer> q = new LinkedList<Integer>();
    int countpush=0;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        int[] dependency = new int[numCourses];
        for (int[] prerequisite:prerequisites) {
                dependency[prerequisite[0]] = dependency[prerequisite[0]]+1;
                if (!m.containsKey(prerequisite[1])) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(prerequisite[0]);
                    m.put(prerequisite[1], temp);
                } else {
                    List<Integer> temp = m.get(prerequisite[1]);
                    temp.add(prerequisite[0]);
                    m.put(prerequisite[1], temp);
                }
        }

        for (int i=0;i<dependency.length;i++) {
            if (dependency[i] == 0) {
                q.add(i);
                countpush++;
            }
        }

        while (!q.isEmpty()) {
            int val = q.poll();
            List<Integer> courses = m.get(val);
            if (courses == null) {
                continue;
            }
            for (int e:courses) {
                dependency[e] = dependency[e]-1;
                if (dependency[e] == 0) {
                    q.add(e);
                    countpush++;
                }
            }
        }
    
        if (countpush == dependency.length) {
            return true;
        }
        return false;
    }
}
