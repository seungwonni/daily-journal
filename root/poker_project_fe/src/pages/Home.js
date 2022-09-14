import React from "react";
import logo from "../logo.svg";
import {Link, Route, Routes} from "react-router-dom";
import Start from "./Start";
import Load from "./Load";
import Home from "./Home";
import '../css/Home.css';
import $ from "jquery";

const useEffect =() => {
    const homeBtn =() => {
        $('.homeDiv').hide();
    }
    return (
        <div className="homeDiv">
            <img src={logo} className="App-logo" alt="logo" />
            <p>
                포커 프로젝트에 오신걸 환영합니다.
            </p>
            <Link to="/start">
                <button className="App-button"
                >게임시작</button>
            </Link>
            <Link to="/load">
                <button className="App-button">불러오기</button>
            </Link>
            <Link to="/home">
                <button className="App-button"
                        onClick={homeBtn}
                >홈으로</button>
            </Link>
            <Routes>
                <Route path="/start" element={<Start/>} />
                <Route path="/load" element={<Load/>} />
                <Route path="/home" element={<Home/>} />
            </Routes>
        </div>
    );
}

export default useEffect;