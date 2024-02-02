package org.example.dispatch;

import java.util.Set;

/**
 * Double Dispatch 예제 코드 - SNS
 */
public class NonDoubleDispatchExample {

	interface Post {
		void postOn(SNS sns);
	}

	static class Text implements Post {
		@Override
		public void postOn(SNS sns) {
			if (sns instanceof Facebook) {
				System.out.println("text - facebook");
			}

			if (sns instanceof Twitter) {
				System.out.println("text - twitter");
			}
		}
	}

	static class Picture implements Post {

		@Override
		public void postOn(SNS sns) {
			if (sns instanceof Facebook) {
				System.out.println("picture - facebook");
			}

			if (sns instanceof Twitter) {
				System.out.println("picture - twitter");
			}
		}
	}

	interface SNS {
	}

	static class Facebook implements SNS {
	}

	static class Twitter implements SNS {}

	public static void main(String[] args) {
		Set<Post> posts = Set.of(new Text(), new Picture());
		Set<SNS> snsSet = Set.of(new Facebook(), new Twitter());

		posts.forEach(post -> snsSet.forEach(post::postOn));
	}
}
