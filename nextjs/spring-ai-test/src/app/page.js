"use client";

import { useState } from "react";

export default function Home() {
  const [input, setInput] = useState("");
  const [answer, setAnswer] = useState("");

  const setupEventSource = () => {
    const eventSource = new EventSource("http://localhost:8080/ai/anthropic/chat/stream?message=" + input);

    eventSource.onmessage = (event) => {
      const newMessage = JSON.parse(event.data).result?.output.content
      if (newMessage) {
        setAnswer((prevData) => prevData + newMessage);
      }
    };

    eventSource.onerror = (error) => {
      console.error("EventSource failed:", error);
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  };

  const handleSubmit = async (e) => {
    setAnswer("");
    e.preventDefault(); // 기본 양식 제출 동작을 방지
    setupEventSource();
  };

  return (
      <main>
        <h1>Chat Stream Test</h1>

        <form onSubmit={handleSubmit}>          <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
          />
          <button type="submit">Send</button>
        </form>

        <p>Answer:</p>
        {answer?.split("\n").map((message, index) => (
            <div key={index}>{message}</div>
        ))}
      </main>
  );
}
