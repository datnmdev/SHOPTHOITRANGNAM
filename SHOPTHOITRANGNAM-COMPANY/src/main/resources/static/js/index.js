$(document).ready(function () {
    $("i.show-password").click(function() {
        $(this).parent().removeClass("active")
        $(this).parent().find("input#password").attr("type", "password");
    })

    $("i.hide-password").click(function() {
        $(this).parent().addClass("active")
        $(this).parent().find("input#password").attr("type", "text");
    })
});