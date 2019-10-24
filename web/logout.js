document.getElementById("logoutbtn").onclick=function (ev) {
    if(!confirm("是否确定退出？")){
        return false;
    }
}