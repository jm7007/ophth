"use strict"
const menuSwitch = (()=>{
    const menus = ["rs","user","inquiry"];
    for(let menu of menus){
        document.getElementById(`btn-${menu}`).setAttribute("onclick",`menuSwitch("${menu}")`);
    }
    return((targetMenu)=>{
        for(let menu of menus){
            document.getElementById(`table-${menu}`).classList.add("hidden");
            document.getElementById(`btn-${menu}`).style.borderBottom = "2px solid black";
        }
        document.getElementById(`table-${targetMenu}`).classList.remove("hidden");
        document.getElementById(`btn-${targetMenu}`).style.borderBottom = "none";
    });
})();
menuSwitch("rs");

//관리자페이지 유저정보 수정기능
const modifyUserButton = (() => {
    //포스트 보낼 폼 초기화
    let form = document.createElement("form");
    form.setAttribute("action", "/admin/modifyuser/new");
    form.setAttribute("method", "post");
    form.style.display = "none";
    document.body.appendChild(form);
    //수정과 서브밋 도중 노드정보 임시저장
    let usingId;
    let usingButton;
    let nameInput;
    let contactInput;
    let passwordInput;
    let emailInput;
    let adminInput;
    //편집을 가능하게 만들고 css를 바꿔줌
    function changeToModify() {
        nameInput = document.getElementById(`admin-user-name-${usingId}`);
        nameInput.removeAttribute("readonly");
        nameInput.style.border = "1px dotted red";

        contactInput = document.getElementById(`admin-user-contact-${usingId}`);
        contactInput.removeAttribute("readonly");
        contactInput.style.border = "1px dotted red";

        passwordInput = document.getElementById(`admin-user-password-${usingId}`);
        passwordInput.removeAttribute("readonly");
        passwordInput.style.border = "1px dotted red";

        emailInput = document.getElementById(`admin-user-email-${usingId}`);
        emailInput.removeAttribute("readonly");
        emailInput.style.border = "1px dotted red";

        adminInput = document.getElementById(`admin-user-admin-${usingId}`);
        adminInput.removeAttribute("readonly");
        adminInput.style.border = "1px dotted red";

        nameInput.focus();

        usingButton.innerHTML = "저장";
        usingButton.classList.remove("modify-button");
        usingButton.classList.add("submit-button");
        usingButton.setAttribute("onclick", "modifyUserButton(-1)");
    }
    //원래 상태로 되돌리기
    function returnFromModify() {
        usingButton.innerHTML = "수정";
        usingButton.classList.remove("submit-button");
        usingButton.classList.add("modify-button");
        usingButton.setAttribute("onclick", `modifyUserButton(${usingId})`);
        nameInput.setAttribute("readonly", "true");
        nameInput.style.border = "none";

        contactInput.setAttribute("readonly", "true");
        contactInput.style.border = "none";

        passwordInput.setAttribute("readonly", "true");
        passwordInput.style.border = "none";

        emailInput.setAttribute("readonly", "true");
        emailInput.style.border = "none";

        adminInput.setAttribute("readonly", "true");
        adminInput.style.border = "none";
    }
    //임시저장된 값으로 html을 완성시켜 리퀘스트
    function submit() {
        form.innerHTML = `
            <input name="id" value="${usingId}">
            <input name="name" value="${nameInput.value}">"
            <input name="password" value="${passwordInput.value}">
            <input name="email" value="${emailInput.value}">
            <input name="admin" value="${adminInput.value}">
        `;
        form.submit();
    }
    return ((id) => {
        if (id == -1) {
            submit();
            alert("수정 완료");
        } else {
            if (usingButton != null) {
                returnFromModify();
            }
            usingId = id;
            usingButton = document.getElementById(`modify-user-button-${usingId}`);
            changeToModify();
        }
    });
})();
//회원삭제기능
function deleteUserButton(id){
    if(confirm("정말로 회원을 지우시겠습니까?")){
    let form = document.createElement("form");
    form.setAttribute("action", "/admin/user/delete");
    form.setAttribute("method", "post");
    form.style.display = "none";
    document.body.appendChild(form);
    form.innerHTML=`
        <input name="id" value="${id}">
    `;
    form.submit();
    }
}