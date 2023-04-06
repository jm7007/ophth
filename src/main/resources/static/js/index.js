"use strict"
const sidePageDisplay = (function () {
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