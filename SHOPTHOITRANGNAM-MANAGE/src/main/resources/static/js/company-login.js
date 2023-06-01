$(document).ready(function () {
    $("i.show-password").click(function() {
        $(this).parent().removeClass("active")
        $(this).parent().find("input#password").attr("type", "password");
        console.log(1)
    })

    $("i.hide-password").click(function() {
        $(this).parent().addClass("active")
        $(this).parent().find("input#password").attr("type", "text");
        console.log(0)
    })
});