$(function(){
    var storage = localStorage.getItem("json");
    console.log(storage);
    if(storage!=null){
        localStorage.setItem("json",storage.substr(0,storage.length-2).concat(",\"value\"]}"));
        // $(".content").text(storage)
    }

});