/**
 * customMain.js
 */
$(document).ready(function () {
    $.ajax({
        method: "get",
        url: "/ajax/walkerCnt",
        contextType: "application/json"
    })
        .done(result => {
            if (result > 0) {
                $("#walkerMenu").text(result);
            }
        })
        .fail(err => console.log(err));
});
