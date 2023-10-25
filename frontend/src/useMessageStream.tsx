import {useEffect, useState} from "react";
import axios from "axios";

type Message = {
  name: string;
  message: string;
}
export const useMessageStream = () => {
  const [messages, setMessages] = useState<Message[]>([])
  const [url, setUrl] = useState('');

  useEffect(() => {
    axios.get('/api/messages-config').then(r => {
      setUrl(r.data)
    })
  }, []);

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