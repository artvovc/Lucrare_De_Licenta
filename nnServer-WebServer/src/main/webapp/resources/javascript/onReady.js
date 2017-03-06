$(function () {
});

function checkFunction(elem) {
    if (elem.style["background-color"] != "white") {
        elem.style["background-color"] = "white";
        elem.style["color"] = "black";
    } else {
        elem.style["background-color"] = null;
        elem.style["color"] = "white";
    }
}

function addFeeds() {
    var listElements = document.getElementsByClassName("check-box-feed");
    if (listElements.length != 0) {
        var list = [];
        var index;
        for (index = 0; index < listElements.length; ++index) {
            if (listElements[index].style["background-color"] == "white") {
                list.push(listElements[index])
            }
        }
        var feeds = [];
        var i;
        for (i = 0; i < list.length; ++i) {
            feeds.push(list[i].id)
        }
        localStorage.setItem("feeds", feeds);

        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "/serv/content");

        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "urls");
        hiddenField.setAttribute("value", localStorage.getItem("feeds"));
        form.appendChild(hiddenField);

        var hiddenField2 = document.createElement("input");
        hiddenField2.setAttribute("type", "hidden");
        hiddenField2.setAttribute("name", "page");
        hiddenField2.setAttribute("value", "0");
        form.appendChild(hiddenField2);

        var hiddenField3 = document.createElement("input");
        hiddenField3.setAttribute("type", "hidden");
        hiddenField3.setAttribute("name", "pageSize");
        hiddenField3.setAttribute("value", "100");
        form.appendChild(hiddenField3);


        document.body.appendChild(form);
        form.submit();
        // window.location.replace("/")
    }
}

function viewFrame(elem){
    var elems = document.getElementsByClassName("content-items");
    var i;
    for(i=0;i<elems.length;++i){
        elems[i].style["background-color"] = null;
        elems[i].style["color"] = "white"
    }

    if (elem.style["background-color"] != "white") {
        elem.style["background-color"] = "white";
        elem.style["color"] = "black";
    }

    console.log(elem.id);
    document.getElementById("myFrame").src = elem.id;
    document.getElementById("myFrame").innerHTML = elem.id;
}