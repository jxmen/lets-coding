package dfs_and_bfs_begin_07._10;

import java.util.LinkedList;
import java.util.Queue;

class Node {
	Node lt,rt;
	int val;

	public Node(int val) {
		this.val = val;
	}
}

public class Main {

	private Node root;

	public static void main(String[] args) {
		Main tree = new Main();
		tree.root = new Node(1);
		tree.root.lt = new Node(2);
		tree.root.rt = new Node(3);
		tree.root.lt.lt = new Node(4);
		tree.root.lt.rt = new Node(5);

		System.out.println(tree.bfs(tree.root));
	}

	private int bfs(Node root) {
		if (root == null) {
			return 0;
		}

		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node poll = queue.poll();
				if (poll.lt == null && poll.rt == null) {
					return level;
				}

				// 큐에 나머지 자식들 넣기!!!
				if (poll.lt != null) {
					queue.offer(poll.lt);
				}

				if (poll.rt != null) {
					queue.offer(poll.rt);
				}
			}

			level++;
		}

		return level;
	}
}
