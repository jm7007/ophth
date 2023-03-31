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
    for (let i = 1; i <= 35; i++) {
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
        if (i == 35) {
            calendar.appendChild(tr);
        }
    }
}
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
            //현재 시간부터 90일 이후의 날짜가 포함된 달까지 예약가능
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
    let day;
    let daybox;
    let thismonth;
    return function(time){
        alert("ddd");
        year = time.getFullYear();
        month = time.getMonth();
        //해당 달의 1일의 요일 뽑기
        firstday = new Date("01/01/2020");
        firstday.setMonth(month);
        firstday.setFullYear(year);
        firstE = firstday.getDay();
        //day에 1일 날짜를 대입
        day = firstday;
        thismonth = day.getMonth();
        for (let i = 1; i <= 35; i++) {
            //id로 내용물을 바꿀 객체를 타게팅
            daybox = document.getElementById(("cal-days-d-" + i));
            //달이 지나가면 내용물 비우기
            if ((day++).getMonth() != thismonth) {
                daybox.innerHTML = "#";
            }
            //첫번째 요일보다 이전 내용물 비우기
            if (i < firstE) {
                daybox.innerHTML = "#";
            }
        }
    };
})();