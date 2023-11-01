import {useEffect, useState} from "react";

type Message = {
  name: string;
  message: string;
}
export const useMessageStream = (url: string) => {
  const [messages, setMessages] = useState<Message[]>([])

  useEffect(() => {

    if (!url) return

    const eventSource = new EventSource(url, {withCredentials: true});

    eventSource.onmessage = (event) => {
      setMessages((prevEvents) => [JSON.parse(event.data), ...prevEvents]);
    };

    eventSource.onerror = (error) => {
      console.error('EventSource failed:', error);
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  }, [url]);

  return messages
}