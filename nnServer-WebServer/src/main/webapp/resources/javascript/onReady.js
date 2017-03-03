$(function () {
    var storage = localStorage.getItem("feeds");
    console.log(storage);
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
        var feeds = "{\"feeds\":[]}";
        var i;
        for (i = 0; i < list.length; ++i) {
            if (i != 0) {
                feeds = feeds.substr(0, feeds.length - 2).concat(",\"" + list[i].id + "\"]}");
            }
            else {
                feeds = feeds.substr(0, feeds.length - 2).concat("\"" + list[i].id + "\"]}");
            }
        }
        localStorage.setItem("feeds", feeds);

        window.location.replace("/")
    }
}