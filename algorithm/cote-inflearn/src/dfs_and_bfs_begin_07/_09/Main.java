package dfs_and_bfs_begin_07._09;

class Node {
	Node lt,rt;
	int val;

	public Node(int val) {
		this.val = val;
	}
}

public class Main {

	private Node root;

	public static void main(String[] args)  {
		Main tree = new Main();
		tree.root = new Node(1);
		tree.root.lt = new Node(2);
		tree.root.rt = new Node(3);
		tree.root.lt.lt = new Node(4);
		tree.root.lt.rt = new Node(5);

		System.out.println(tree.dfs(0, tree.root));
	}

	private int dfs(int level, Node root)  {
		if (root.lt == null && root.rt == null) {
			return level;
		}

		int lt = dfs(level + 1, root.lt);
		int rt = dfs(level + 1, root.rt);

		if (lt >= rt) {
			return rt;
		} else {
			return lt;
		}
	}
}
