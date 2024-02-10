package org.example.pattern.observer.push;

import java.util.ArrayList;
import java.util.List;

interface Observer {
	void update(String lastPostName);
}

interface Subject {

	void notifyUpdateToObservers();

	void addObserver(Observer observer);
}

class MyBlog implements Subject {

	private final List<Observer> observers = new ArrayList<>();

	private String lastPostName;

	@Override
	public void notifyUpdateToObservers() {
		this.observers.forEach(o -> o.update(this.lastPostName));
	}

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}


	public void writeNewPost(String name) {
		System.out.println("새 글 발행");
		this.lastPostName = name;

		this.notifyUpdateToObservers();
	}
}

class PostSubscriber implements Observer {

	private final Subject subject;
	private final String name;

	public PostSubscriber(Subject subject, String name) {
		this.subject = subject;
		this.subject.addObserver(this);

		this.name = name;
	}

	@Override
	public void update(String lastPostName) {
		System.out.println(this.name + "- 새 글이 발행되었습니다 : " + lastPostName);
	}
}

public class Main {
	public static void main(String[] args) {
		MyBlog myBlog = new MyBlog();
		new PostSubscriber(myBlog, "name1");
		new PostSubscriber(myBlog, "name2");

		myBlog.writeNewPost("옵저버 패턴이란");
	}
}
