"use client";

import { useState } from "react";

export default function Home() {
  const [data, setData] = useState([]);
  const [input, setInput] = useState("");

  const setupEventSource = () => {
    const eventSource = new EventSource("http://localhost:8080/ai/anthropic/chat/stream?message=" + input);

    eventSource.onmessage = (event) => {
      const newMessage = JSON.parse(event.data).result?.output.content;
      if (newMessage) {
        setData((prevData) => [...prevData, newMessage]);
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
    setData([])
    e.preventDefault();
    setupEventSource(e.target.value)
  };

  return (
      <main>
        <h1>Chat Stream Test</h1>

        <form onSubmit={handleSubmit}>
          <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
          />
          <button type="submit">Send</button>
        </form>

        <p>Messages:</p>
        {data.map((message) => ( message ))}
      </main>
  );
}
