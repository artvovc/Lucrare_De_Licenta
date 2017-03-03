// $(function(){
//     var storage = localStorage.getItem("json");
//     console.log(storage);
//     if(storage!=null){
//         localStorage.setItem("json",storage.substr(0,storage.length-2).concat(",\"value\"]}"));
//         // $(".content").text(storage)
//     }
//
// });

function checkFunction(elem) {
    if (elem.style["background-color"] != "white") {
        elem.style["background-color"] = "white";
        elem.style["color"] = "black";
    } else {
        elem.style["background-color"] = null;
        elem.style["color"] = "white";
    }
}