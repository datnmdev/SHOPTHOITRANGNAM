$(document).ready(function () {
    $("i.show-password").click(function() {
        $(this).parent().removeClass("active")
        $(this).parent().find("input#password").attr("type", "password");
    })

    $("i.hide-password").click(function() {
        $(this).parent().addClass("active")
        $(this).parent().find("input#password").attr("type", "text");
    })

    $("#forgot-password").click(function() {
        $(this).attr('display', 'none')
        $('#forgot-password-section').attr('display', 'block')
    })

    $("#back-home").click(function() {
        $(this).attr('display', 'none')
        $('#login-section').attr('display', 'block')
    })
});