"use strict"
const sidePageDisplay = (function(){
    const sidePageClassList = document.getElementById("side-page").classList;
    let onOff = 0;
    return function(){
        if(onOff == 0){
            sidePageClassList.remove("side-page-slide-aniOff");
            sidePageClassList.add("side-page-slide-ani");
            onOff = 1;
        }else{
            sidePageClassList.remove("side-page-slide-ani");
            sidePageClassList.add("side-page-slide-aniOff");
            onOff = 0;
        }
    }
})();