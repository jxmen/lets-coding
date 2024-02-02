package org.example.dispatch;

import java.util.Set;

/**
 * Double Dispatch 예제 코드 - SNS
 */
public class DoubleDispatchExample {

	interface Post {
		void postOn(SNS sns);
	}

	static class Text implements Post {
		@Override
		public void postOn(SNS sns) {
			sns.post(this);
		}
	}

	static class Picture implements Post {

		@Override
		public void postOn(SNS sns) {
			sns.post(this);
		}
	}

	interface SNS {
		void post(Text text);
		void post(Picture picture);
	}

	static class Facebook implements SNS {
		@Override
		public void post(Text text) {
			System.out.println("text - facebook");
		}

		@Override
		public void post(Picture picture) {
			System.out.println("picture - facebook");
		}
	}

	static class Twitter implements SNS {
		@Override
		public void post(Text text) {
			System.out.println("text - twitter");
		}

		@Override
		public void post(Picture picture) {
			System.out.println("picture - twitter");
		}
	}

	static class Instagram implements SNS {
		@Override
		public void post(Text text) {
			System.out.println("text - instagram");
		}

		@Override
		public void post(Picture picture) {
			System.out.println("picture - instagram");
		}
	}

	public static void main(String[] args) {
		Set<Post> posts = Set.of(new Text(), new Picture());
		Set<SNS> snsSet = Set.of(new Facebook(), new Twitter(), new Instagram());

		posts.forEach(post -> snsSet.forEach(post::postOn));
	}

}
