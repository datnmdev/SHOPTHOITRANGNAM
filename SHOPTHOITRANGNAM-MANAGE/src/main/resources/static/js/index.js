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
        $('#login-section').css('display', 'none')
        $('#forgot-password-section').css('display', 'block')
    })

    $("#back-home").click(function() {
        $('#forgot-password-section').css('display', 'none')
        $('#login-section').css('display', 'block')
    })
});