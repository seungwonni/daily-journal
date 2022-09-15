import logo from './logo.svg';
import './App.css';
import React from 'react';
import {Routes, Route, Link} from 'react-router-dom';
import Home from "./pages/Home";
import Start from "./pages/Start";
import Load from "./pages/Load";


function App() {
  function buttonClick() {
    // axios.get('http://localhost:8080/main.do')
    //     .then((result)=>{ console.log(result.data) }) // 요청 성공시 실행코드
    //     .catch(()=>{  }) // 요청 실패시 실행코드
    }
  return (
    <div className="App">
      <header className="App-header">
            <Home/>
      </header>
    </div>
  );
}

export default App;

