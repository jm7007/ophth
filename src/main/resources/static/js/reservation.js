"use strict"

function makeCalendar(tableId) {
    const calendar = document.getElementById(tableId);
    for (let e of ["일", "월", "화", "수", "목", "금", "토"]) {
        let th = document.createElement("th");
        th.innerHTML = e;
        calendar.appendChild(th);
    }
    let tr;
    let trId;
    let td;
    let tdId;
    for (let i = 1; i <= 42; i++) {
        if (i % 7 == 1) {
            if (i != 1) {
                calendar.appendChild(tr);
            }
            tr = document.createElement("tr");
            trId = tableId + "-r-" + (Math.ceil(i / 7));
            tr.setAttribute("id", trId);
        }
        td = document.createElement("td");
        tdId = tableId + "-d-" + i;
        td.setAttribute("id", tdId);
        td.innerHTML = i;
        tr.appendChild(td);
        if (i == 42) {
            calendar.appendChild(tr);
        }
    }
}
makeCalendar("cal-days");
//달력에서 화살표를 누르면 해당 달의 예약정보 가져오기 기능
const updateCalendar = (() => {
    let time = new Date();
    let year = time.getFullYear();
    let month = time.getMonth();
    document.getElementById("cal-year-month").innerHTML = year + "." + (month + 1);
    //화살표를 눌렀을때 왼쪽(0) 오른쪽(1)의 값을 받아서 날짜를 지정
    return function (arrow) {
        //현재 날짜 이전으로는 이동 불가
        if (arrow == 0 & (Date.parse(new Date()) - Date.parse(time)) < 0) {
            if (month == 0) {
                year -= 1;
                month = 11;
            } else {
                month -= 1;
            }
            time.setFullYear(year);
            time.setMonth(month);
            document.getElementById("cal-year-month").innerHTML = year + "." + (month + 1);
            fillCalendar(time);
            //현재 시간부터 90일 이후의 날짜가 포함된 달까지 함수 작동 가능
        } else if (arrow == 1 & (Date.parse(time)) - Date.parse(new Date()) < 7776000000) {
            if (month == 11) {
                year += 1;
                month = 0;
            } else {
                month += 1;
            }
            time.setFullYear(year);
            time.setMonth(month);
            document.getElementById("cal-year-month").innerHTML = year + "." + (month + 1);
            //내용 및 속성 채우기
            fillCalendar(time);
        }
    };
})();
//빈 달력칸마다 내용 채우고 속성주기
const fillCalendar = (() => {
    let year;
    let month;
    let firstday;
    let firstE;
    let daybox;
    let lastday;
    return function (time) {
        year = time.getFullYear();
        month = time.getMonth();
        //해당 달의 1일의 날짜 대입
        firstday = new Date((month + 1) + "/01/" + year);
        //1일의 요일
        firstE = firstday.getDay();
        //해당 달의 마지막날 날짜를 다음달 1일 0시에서 12시간을 빼서 구하기
        lastday = new Date(Date.parse((month + 2) + "/01/" + year) - (1000 * 60 * 60 * 12)).getDate();
        for (let i = 1; i <= 42; i++) {
            //id로 내용물을 바꿀 객체를 타게팅
            daybox = document.getElementById(("cal-days-d-" + i));
            //달이 지나가면 내용물 비우기
            if ((i - firstE) > lastday) {
                daybox.innerHTML = "";
            }
            //첫번째 요일보다 이전 내용물 비우기
            else if (i <= firstE) {
                daybox.innerHTML = "";
            } else {
                daybox.innerHTML = i - firstE;
            }
            daybox.setAttribute("onclick", `showRSwithCal(${year},${month},${i - firstE})`);
        }
    };
})();
fillCalendar(new Date());
//날짜를 누르면 옆에 예약상황을 보여주는 function

const showRSwithCal = (function (reservations) {
    const container = document.getElementById("rs-hours");
    let hourinput;
    let hourlabel;
    let rshour;
    let day;
    let tmp;
    const rs = reservations;
    console.log(rs);
    return ((year, month, dayOfMonth) => {
        //이전 데이터 초기화
        container.innerHTML = "";
        
        for (let i = 0; i < 6; i++) {
            rshour = (i < 4) ? i + 9 : i + 10;
            day = new Date(`${month + 1},${dayOfMonth},${year},${rshour}:00:00`);
            hourinput = document.createElement("input");
            hourinput.setAttribute("type", "radio");
            hourinput.setAttribute("id", `rs-date-${year}-${month + 1}-${dayOfMonth}-${rshour}`);
            hourinput.setAttribute("name", `rs_datetime`);
            hourinput.setAttribute("value", day);
            hourlabel = document.createElement("label");
            hourlabel.setAttribute("for", `rs-date-${year}-${month + 1}-${dayOfMonth}-${rshour}`);
            for (let reserv of rs) {
                //예약정보에서 가져온 예약일정
                tmp = new Date(reserv.rs_datetime);
                if (day.getTime() == tmp.getTime()) {
                    hourlabel.innerHTML = `${month + 1}월 ${dayOfMonth}일 ${rshour}:00 <span style = "color:red;">예약 완료<span>`;
                    hourinput.setAttribute("disabled","");
                    break;
                }else{
                    hourlabel.innerHTML = `${month + 1}월 ${dayOfMonth}일 ${rshour}:00 <span style="color:dodgerblue;">예약 가능<span>`;
                }
            }
            container.appendChild(hourinput);
            container.appendChild(hourlabel);
        }
    });
})(reservations);

//서브밋 기능(가상의 포스트 객체 생성 및 리퀘스트
function submitRS(){
    //value 추출
    const rs_name = document.getElementsByName("rs_name")[0].value;
    const rs_contact = document.getElementsByName("rs_contact")[0].value;
    const rs_datetime = document.querySelector("input[name='rs_datetime']:checked").value;
    const rs_info = document.getElementsByName("rs_info")[0].value;
    //입력 조건 설정, 타입 검증
}