"use strict"

function makeCalendar(tableId) {
    const calendar = document.getElementById(tableId);
    for(let e of ["일","월","화","수","목","금","토"]){
        let th = document.createElement("th");
        th.innerHTML=e;
        calendar.appendChild(th);
    }
    let tr;
    let trId;
    let td;
    let tdId;
    for (let i = 1; i <= 35 ; i++) {
        if (i % 7 == 1) {
            if(i!=1){
                calendar.appendChild(tr);
            }
            tr = document.createElement("tr");
            trId = tableId+"-"+(Math.ceil(i/7));
            tr.setAttribute("id",trId);
        }
        td = document.createElement("td");
        tdId = trId+"-"+i;
        td.setAttribute("id",tdId);
        td.innerHTML=i;
        tr.appendChild(td);
        if(i == 35){
            calendar.appendChild(tr);
        }
    }

}