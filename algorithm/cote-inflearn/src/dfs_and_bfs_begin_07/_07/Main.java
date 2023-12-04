package dfs_and_bfs_begin_07._07;

import java.util.LinkedList;
import java.util.Queue;

class Node {
	Node lt;
	Node rt;

	int data;

	public Node(int data) {
		this.data = data;
	}
}

public class Main {
	Node root;

	public static void main(String[] args) {
		Main tree = new Main();
		tree.root = new Node(1);
		tree.root.lt = new Node(2);
		tree.root.rt = new Node(3);
		tree.root.lt.lt = new Node(4);
		tree.root.lt.rt = new Node(5);
		tree.root.rt.lt = new Node(6);
		tree.root.rt.rt = new Node(7);

		tree.bfs(tree.root);
	}

	private void bfs(Node root) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root); // q에 넣는다.

		int level = 0; // 트리의 깊이
		while (!queue.isEmpty()) {
			int len = queue.size();
			System.out.print(level + " : ");
			for (int i = 0; i < len; i++) {
				Node cur = queue.poll();
				System.out.print(cur.data + " ");

				if (cur.lt != null) {
					queue.offer(cur.lt);
				}

				if (cur.rt != null) {
					queue.offer(cur.rt);
				}
			}
			level++;
			System.out.println();
		}


	}
}
