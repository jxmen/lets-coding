import java.util.List;

interface Observer {
	void update();

	void printName();
}

interface Subject {

	void notifyUpdateToObservers();

	void addObserver(Observer postSubscriber);
}

class MyBlog implements Subject {

	private List<Observer> observers;

	@Override
	public void notifyUpdateToObservers() {
		this.observers.forEach(Observer::update);
	}

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}


	public void writeNewPost() {
		System.out.println("새 글 발행");

		this.notifyUpdateToObservers();
	}
}

class PostSubscriber implements Observer {

	private final Subject subject;
	private final String name;

	public PostSubscriber(Subject subject, String name) {
		this.subject = subject;
		this.name = name;
	}

	@Override
	public void update() {
		System.out.println("새 글이 발행되었습니다.");
	}

	@Override
	public void printName() {
		System.out.println("name : " + this.name);
	}
}

public class Main {
	public static void main(String[] args) {
		MyBlog myBlog = new MyBlog();
		Observer postSubscriber = new PostSubscriber(myBlog, "jxmen");

		postSubscriber.printName();

		myBlog.addObserver(postSubscriber);
		myBlog.writeNewPost();

		postSubscriber.printName();
	}
}
