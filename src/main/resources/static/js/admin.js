"use strict"
const menuSwitch = (()=>{
    const menus = ["rs","user"];
    for(let menu of menus){
        document.getElementById(`btn-${menu}`).setAttribute("onclick",`menuSwitch("table-${menu}")`);
    }
    return((tableName)=>{
        for(let menu of menus){
            document.getElementById(`table-${menu}`).classList.add("hidden");
        }
        document.getElementById(`${tableName}`).classList.remove("hidden");
    });
})(); 