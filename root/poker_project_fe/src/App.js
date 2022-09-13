import logo from './logo.svg';
import './App.css';
import * as React from 'react'
import Home from './home';
import {Link, Router, Route} from 'react-router-dom';
import home from "./home";


function App() {
  function buttonClick() {
    // axios.get('http://localhost:8080/main.do')
    //     .then((result)=>{ console.log(result.data) }) // 요청 성공시 실행코드
    //     .catch(()=>{  }) // 요청 실패시 실행코드
    home();
    }
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          포커 프로젝트에 오신걸 환영합니다.
        </p>
      </header>
    </div>
  );
}

export default App;

