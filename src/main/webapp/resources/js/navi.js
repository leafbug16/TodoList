const hamburger = document.querySelector("#header-hamburger-ico");
const hd = document.querySelector("#header-dropmenu");

hamburger.addEventListener("click", handleHeaderDropmenu);

function handleHeaderDropmenu(){
    hd.classList.toggle("header-dropmenu-displaynone");
}