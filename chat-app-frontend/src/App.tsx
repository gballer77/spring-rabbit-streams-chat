import React, {useState} from "react";
import axios from 'axios';
import {AiOutlineSend} from "react-icons/ai";
import {SiRabbitmq} from "react-icons/si";
import {useLocalStorage} from "./useLocalStorage.tsx";
import {useMessageStream} from "./useMessageStream.tsx";
import Map from "./Map.tsx";
import {LatLngExpression} from "leaflet";

const App = () => {
  const defaultMarkers = [{message: 'sample marker in Austin', position: [30.2672, -97.7431] as LatLngExpression}];

  const {value: username, updateValue} = useLocalStorage('username')
  const [message, setMessage] = useState('')
  const messages = useMessageStream('http://127.0.0.1:8080/api/messages-stream')

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    axios.post('/api/messages', {name: username, message}).then(() => {
      setMessage('')
    })
  }

  return (
    <div className='bg-gray-900 text-white h-screen w-screen flex flex-col items-center'>
      <form onSubmit={handleSubmit} className='w-full p-4 flex flex-col h-full gap-2'>
        <div className='flex items-center justify-between'>
          <div className='flex items-center gap-4 text-3xl'>
            <SiRabbitmq className='text-sky-700'/>
            <h1 className='text-2xl md:text-3xl'>Rabbit Messenger</h1>
          </div>
          <input
            name='user'
            className='bg-transparent outline-none focus:border-sky-700 p-4 border border-gray-400/50 rounded-full'
            placeholder='Name'
            value={username}
            type='text'
            maxLength={20}
            minLength={2}
            required
            onChange={(e) => updateValue(e.target.value)}
          />
        </div>
        <div className='h-full flex gap-4 flex-col-reverse lg:flex-row justify-center items-center overflow-y-auto '>
          <div className='h-full w-full flex flex-col gap-2 flex-1 max-w-3xl'>
            <div
              className='flex flex-col-reverse p-4 gap-4 bg-gradient-to-b from-white/20 to-gray-400/20 shadow-lg rounded-3xl w-full min-h-[300px] m-auto flex-1 overflow-y-auto'>
              {messages.map((m, i) => (
                <div
                  key={`${m.message}-${i}`}
                  className={`bg-gradient-to-r shadow-lg pl-2 pr-5 py-2 flex gap-2 max-w-[70%] items-center rounded-full ${m.name.toLowerCase() === username.toLowerCase() ? 'from-gray-500 to-gray-600 self-end' : 'from-violet-700 to-fuchsia-700 self-start'}`}>
                  <div className='rounded-full h-10 w-10 bg-gray-800 flex items-center justify-center'>
                    {m.name.toUpperCase().substring(0, 2)}
                  </div>
                  <div className='flex-1'>
                    {m.message}
                  </div>
                </div>
              ))}
              <div/>
            </div>
            <div className='flex items-center gap-2 m-auto w-full'>
              <input
                name='message'
                value={message}
                autoFocus
                maxLength={500}
                placeholder='Message'
                onChange={e => setMessage(e.target.value)}
                className='shadow-lg bg-transparent outline-none focus:border-sky-700 p-4 border border-gray-400/50 rounded-full w-full'/>
              <button type='submit' className='shadow-lg p-4 rounded-full bg-sky-700 text-3xl'><AiOutlineSend/></button>
            </div>
          </div>
          <div className='lg:self-start overflow-hidden bg-gray-800 rounded-3xl min-h-[400px]'>
            <Map markers={defaultMarkers}/>
          </div>
        </div>
      </form>
    </div>
  )
};

export default App

