"use strict"
/*
    메인네비게이션 만들기
    id = "top-nav" 객체 안에 html 작성 및 생성됨
 */
//세션 저장 아이디
let ss_account;
//세션 관리자 판별
let ss_admin;
//세션 불러오기
const session_tmp = document.createElement("div");
session_tmp.innerHTML =
'<div id="ss_account" class="hidden" th:text="${session.account}"></div>'
+'<div id="ss_admin" class="hidden" th:text="${session.admin}"></div>';
document.body.appendChild(session_tmp);
//세션 값 대입하기
try {
    ss_account = document.getElementById("ss_account").innerHTML;
    ss_admin = document.getElementById("ss_admin").innerHTML;
} catch (e) {
    ss_account = '';
    ss_admin = '';
    console.log("no login info");
}
function makeTopNav() {
    //top-nav id 객체 필요
    const header = document.getElementById("top-nav");
    if (header == null)
        return
    //header 에 headarea id div 생성
    const headArea = document.createElement("div");
    headArea.setAttribute("id", "headarea");
    headArea.setAttribute("class", "background-grad")
    header.appendChild(headArea);
    //id:headarea 안에 로고 area 생성
    const logo_area = document.createElement("div");
    logo_area.setAttribute("style", "margin:0px;");
    logo_area.setAttribute("class", "col-2");
    headArea.appendChild(logo_area);
    //id:headarea 안에 a 생성
    const a_logo = document.createElement("a");
    a_logo.setAttribute("class", "a_logo");
    a_logo.setAttribute("href", "/");
    logo_area.appendChild(a_logo);
    //a 안에 이미지 생성
    let logo = document.createElement("img");
    logo.setAttribute("id", "mainlogo");
    logo.setAttribute("src", "/images/002.png");
    a_logo.appendChild(logo);
    //nav 내용
    const navArea = document.createElement("div");
    navArea.setAttribute("class", "col-8");
    headArea.appendChild(navArea);
    const nav = document.createElement("ul");
    nav.setAttribute("class", "topnav");
    nav.innerHTML =
        `<li><a href='join'>회원가입</a></li>
        <li><a href='news'>JM 소식</a></li>`;
    if (ss_account != '') {
        nav.innerHTML += `<li><a href='logout'>로그아웃</a></li>`;
    };
    if (ss_admin == 1) {
        nav.innerHTML += `<li><a href='admin'>관리자</a></li>`;

    }
    navArea.appendChild(nav);
    //로그인창
    const loginArea = document.createElement("div");
    loginArea.setAttribute("class", "col-2");
    headArea.appendChild(loginArea);
    if (ss_account != '') {
        const logined = document.createElement("div");
        logined.setAttribute("class", "head-login");
        logined.innerHTML = ss_account;
        loginArea.appendChild(logined);
    } else {
        console.log("account not ditected")
        const login = document.createElement("a");
        login.setAttribute("class", "head-login");
        login.setAttribute("href", "login");
        login.innerHTML = "로그인";
        loginArea.appendChild(login);
    }
    //로그인창
    const loginWindow = document.createElement("div");
    loginWindow.setAttribute("class", "window-login");
    loginWindow.setAttribute("class", "hidden");
    loginWindow.setAttribute("onclick", "loginWindowOnOff()");
    loginWindow.innerHTML = "아이디:<br>비밀번호:";
    loginArea.appendChild(loginWindow);

    const loginWindowClose = document.createElement("img");
    loginWindowClose.setAttribute("class", "ic_close");
    loginWindowClose.setAttribute("src", "/images/ic_close.png");
    loginWindowClose.setAttribute("onclick", "loginWindowOnOff()");
    loginWindow.appendChild(loginWindowClose);
}
makeTopNav();