package dfs_and_bfs_begin_07._05;

class Node {
	public int value;
	public Node lt;
	public Node rt;

	public Node(int value) {
		this.value = value;
	}
}


public class Main {
	Node root;

	public void DFS(Node root) {
		if (root == null) return;

		// 여기서 출력시 전위 순위
		DFS(root.lt);
		// 여기서 출력시 중위 순위
		System.out.println(root.value);
		DFS(root.rt);
		// 여기서 출력시 후위 순위
	}

	public static void main(String[] args) {
		Main tree = new Main();
		tree.root = new Node(1);
		tree.root.lt = new Node(2);
		tree.root.rt = new Node(3);
		tree.root.lt.lt = new Node(4);
		tree.root.lt.rt = new Node(5);
		tree.root.rt.lt = new Node(6);
		tree.root.rt.rt = new Node(7);

		tree.DFS(tree.root);
	}
}
