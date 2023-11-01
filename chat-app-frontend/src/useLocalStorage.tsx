import {useEffect, useState} from "react";

export const useLocalStorage = (key: string) => {
  const [value, setValue] = useState('');
  useEffect(() => {
    setValue(localStorage.getItem(key) as string || '')
  }, []);

  const updateValue = (newValue: string) => {
    localStorage.setItem(key, newValue)
    setValue(newValue)
  }

  return {value, updateValue}
}