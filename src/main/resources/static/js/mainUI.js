"use strict"
/*
    메인네비게이션 만들기
    id = "top-nav" 객체 안에 html 작성 및 생성됨

 */

function makeTopNav(){
    //top-nav id 객체 필요
    const header = document.getElementById("top-nav");
    if(header == null)
        return
    //header 에 headarea id div 생성
    const headArea = document.createElement("div");
    headArea.setAttribute("id","headarea");
    headArea.setAttribute("class","background-grad")
    header.appendChild(headArea);
    //id:headarea 안에 로고 area 생성
    const logo_area = document.createElement("div");
    logo_area.setAttribute("style","margin:0px;");
    logo_area.setAttribute("class","col-2");
    headArea.appendChild(logo_area); 
    //id:headarea 안에 a 생성
    const a_logo = document.createElement("a");
    a_logo.setAttribute("class","a_logo");
    a_logo.setAttribute("href","/");
    logo_area.appendChild(a_logo);
    //a 안에 이미지 생성
    let logo = document.createElement("img");
    logo.setAttribute("id","mainlogo");
    logo.setAttribute("src","/images/002.png");
    a_logo.appendChild(logo);
    //nav 내용
    const navArea = document.createElement("div");
    navArea.setAttribute("class","col-8");
    headArea.appendChild(navArea);
    const nav = document.createElement("ul");
    nav.setAttribute("class","topnav");
    nav.innerHTML=""
    +"<li><a href='join'>회원가입</a></li>"
    +"<li><a href='news'>JM 소식</a></li>"
    +"<li><a href='admin'>관리자</a></li>";
    navArea.appendChild(nav);
    //로그인창
    const loginArea = document.createElement("div");
    loginArea.setAttribute("class","col-2");
    headArea.appendChild(loginArea);
    
    const login = document.createElement("div");
    login.setAttribute("class","head-login");
    login.setAttribute("onclick","loginWindowOnOff()");
    login.innerHTML = "로그인";
    loginArea.appendChild(login);
    //로그인 됨
    const logined = document.createElement("div");
    logined.setAttribute("class","head-login");
    logined.setAttribute("class","hidden");
    logined.innerHTML = "로그인됨";
    loginArea.appendChild(logined);
    //로그인창
    const loginWindow = document.createElement("div");
    loginWindow.setAttribute("class","window-login");
    loginWindow.setAttribute("class","hidden");
    loginWindow.setAttribute("onclick","loginWindowOnOff()");
    loginWindow.innerHTML = "아이디:<br>비밀번호:";
    loginArea.appendChild(loginWindow);

    const loginWindowClose = document.createElement("img");
    loginWindowClose.setAttribute("class","ic_close");
    loginWindowClose.setAttribute("src","/images/ic_close.png");
    loginWindowClose.setAttribute("onclick","loginWindowOnOff()");
    loginWindow.appendChild(loginWindowClose);
}
