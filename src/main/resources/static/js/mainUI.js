"use strict"
/*
    사이드 페이지 만들기
    id = "sidepage" 객체 안에 html 작성 및 생성됨
    ----------!!!!주의!!!!-----------------------------
    sidepage는 body 바로 밑에 위치해야하며 topnav 위에 있어야함
    --------------------------------------------------
    메인네비게이션 만들기
    id = "top-nav" 객체 안에 html 작성 및 생성됨

    세션을 사용하려면 html 안에 아래 코드를 추가할것
    <div id="ss_account" class="hidden" th:text="${session.account}"></div>
    <div id="ss_admin" class="hidden" th:text="${session.admin}"></div>

    footer 만들기
    body 맨 아래 id = "footer" 객체 안에 작성 및 생성됨

    맨위로 버튼 만들기

 */
//세션 저장 아이디
let ss_id;
//세션 저장 이름
let ss_name;
//세션 관리자 판별
let ss_admin;
//세션 값 대입하기 
try {
    ss_id = document.getElementById("ss_id").innerHTML;
    ss_name = document.getElementById("ss_name").innerHTML;
    ss_admin = document.getElementById("ss_admin").innerHTML;
} catch (e) {
    ss_id = '';
    ss_name = '';
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
    navArea.appendChild(nav);
    //mainmenu - JM안과
    const mainmenu1 = document.createElement("li");
    const mainmenu1_a = document.createElement("a");
    mainmenu1_a.setAttribute("href", "/info");
    mainmenu1_a.innerHTML = "JM 안과";
    mainmenu1.appendChild(mainmenu1_a);
    nav.appendChild(mainmenu1);
    //mainmenu - 시력교정
    const mainmenu2 = document.createElement("li");
    mainmenu2.innerHTML = "시력교정";
    const dropnav1 = document.createElement("ul"); //시력교정의 드랍다운메뉴
    dropnav1.setAttribute("id", "dropnav1");
    dropnav1.innerHTML =
        `<li><a href='/lasik'>스마일 라식</a></li>
    <li><a href='/implantable'>렌즈삽입수술</a></li>
    <li><a href='ldryeye'>안구건조증</a></li>`;
    mainmenu2.appendChild(dropnav1);
    nav.appendChild(mainmenu2);
    //mainmenu - 노안*백내장
    const mainmenu3 = document.createElement("li");
    mainmenu3.innerHTML = "노안•백내장";
    const dropnav2 = document.createElement("ul"); //시력교정의 드랍다운메뉴
    dropnav2.setAttribute("id", "dropnav2");
    dropnav2.innerHTML =
        `<li><a href='/oldeye'>노안</a></li>
    <li><a href='/cataract'>백내장</a></li>`;
    mainmenu3.appendChild(dropnav2);
    nav.appendChild(mainmenu3);
    //mainmenu - 커뮤니티
    const mainmenu4 = document.createElement("li");
    mainmenu4.innerHTML = "커뮤니티";
    const dropnav3 = document.createElement("ul"); //시력교정의 드랍다운메뉴
    dropnav3.setAttribute("id", "dropnav3");
    dropnav3.innerHTML =
        `<li><a href='/news'>병원 소식</a></li>
        <li><a href='/inquiry'>문의하기</a></li>`;
    mainmenu4.appendChild(dropnav3);
    nav.appendChild(mainmenu4);
    //로그인창
    //관리자 세션 1이면 관리자 메뉴 띄우기
    if (ss_admin == 1) {
        nav.innerHTML += `<li><a href='/admin'>관리자</a></li>`;

    }
    const loginArea = document.createElement("div");
    loginArea.setAttribute("class", "col-2");
    headArea.appendChild(loginArea);
    //로그인 세션 있음
    if (ss_id != '') {
        const logined = document.createElement("div");
        logined.setAttribute("class", "head-login");
        logined.innerHTML = ss_name;
        loginArea.appendChild(logined);
        //로그인시 보조메뉴 만들기
        const loginFunction = document.createElement("ul"); //보조메뉴 리스트를 담을 ul
        // loginFunction.setAttribute("class","hidden");
        loginFunction.innerHTML =
            `<li><a href='#'>회원 정보</a></li>
        <li><a href='/modifyuser'>회원 수정</a></li>
        <li><a href='/logout'>로그 아웃</a></li>`
        logined.appendChild(loginFunction);
        //로그인 세션 없음
    } else {
        console.log("account not ditected")
        const login = document.createElement("a");
        login.setAttribute("class", "head-login");
        login.setAttribute("href", "/login");
        login.innerHTML = "로그인";
        loginArea.appendChild(login);
    }
}
try {
    makeTopNav();
} catch (e) { }

function footer() {
    const footer = document.getElementById("footer");
    const container_left = document.createElement("div");
    const container_center = document.createElement("div");
    const container_right = document.createElement("div");
    container_left.setAttribute("class", "col-3");
    container_center.setAttribute("class", "col-6");
    container_right.setAttribute("class", "col-3");
    const footer_img = document.createElement("img");
    footer_img.setAttribute("src", "/images/002-w.png");
    container_left.appendChild(footer_img);
    container_center.innerHTML =
        `<p>상호 : JM안과의원 &nbsp; &nbsp; | &nbsp; &nbsp; TEL : 02-444-4444<br>주소 : 서울시 동대문구 장안동 345-7 &nbsp; | &nbsp; 도로명주소 : 서울시 장한로 22길 13<br>
    사업자등록번호 : 123-45-67890 (대표자: 이종민)<br>
    reference : 비앤빛 강남밝은세상안과의원, 눈에미소안과</p>`;
    container_right.innerHTML =
        `<h2>대표번호</h2>
    <h2>02-1234-5678</h2>`;
    //footer append container
    footer.appendChild(container_left);
    footer.appendChild(container_center);
    footer.appendChild(container_right);

}
try {
    footer();
} catch (e) { }

function makeSidePage() {
    const sidepage = document.getElementById("sidepage");
    sidepage.innerHTML = `
    <div id="side-page" class="col-8">
        <div id="side-page-nav" class = "col-2 row-12">
            <ul>
                <img src="/images/ic_calendar.png" onclick="sidePageChange('rs')">
                <li>온라인 예약</li>
                <img src="/images/ic_support.png" onclick="sidePageChange('rs')">
                <li>비용 상담</li>
                <img src="/images/ic_kakao.png" onclick="sidePageChange('rs')">
                <li>카톡상담</li>
                <img src="/images/ic_footprint.png" onclick="sidePageChange('location')">
                <li>오시는길</li>
                <li style="margin-top:20px;border-top:0.5px solid white; border-bottom: 0.5px solid white;font-size:12px">010-3393-4738</li>
            </ul>
        </div>
        <div id="side-page-content" class = "col-10">
            <img class="ic_close" src="/images/ic_close.png" onclick="sidePageDisplay()">
            <div id="side-wrapper" class="col-10">
                <div id="side-title" class="row"></div>
                <iframe id="side-iframe" src="rs" class="col-12 row-10 iframe-container" frameborder="0"></iframe>
            </div>
        </div>
    </div>
    <div id=top-nav></div>


    <!--side nav-->
    <nav class="quicknav">
        <ul>
            <img src="/images/ic_calendar.png" onclick="sidePageDisplay(),sidePageChange('rs')">
            <li>온라인 예약</li>
            <img src="/images/ic_support.png" onclick="sidePageDisplay(),sidePageChange('rs')">
            <li>비용 상담</li>
            <img src="/images/ic_kakao.png" onclick="sidePageDisplay(),sidePageChange('rs')">
            <li>카톡상담</li>
            <img src="/images/ic_footprint.png" onclick="sidePageDisplay(),sidePageChange('location')">
            <li>오시는길</li>
        </ul>
    </nav>`
}
try {
    makeSidePage();
} catch (e) { }

const sidePageDisplay = (function () {
    try {
        const sidePageClassList = document.getElementById("side-page").classList;
        let onOff = 0;
        return function () {
            if (onOff == 0) {
                sidePageClassList.remove("side-page-slide-aniOff");
                sidePageClassList.add("side-page-slide-ani");
                onOff = 1;
            } else {
                sidePageClassList.remove("side-page-slide-ani");
                sidePageClassList.add("side-page-slide-aniOff");
                onOff = 0;
            }
        }
    } catch (e) { console.log("sidepage-display-off"); }
})();
const sidePageChange = ((page) => {
    switch (page) {
        case 'rs':
            document.getElementById("side-title").innerHTML = "온라인 예약";
            break;
        case 'location':
            document.getElementById("side-title").innerHTML = "오시는 길";
            break;
        default:
            document.getElementById("side-title").innerHTML = "side-title";
    };
    document.getElementById("side-iframe").setAttribute("src", page);
});