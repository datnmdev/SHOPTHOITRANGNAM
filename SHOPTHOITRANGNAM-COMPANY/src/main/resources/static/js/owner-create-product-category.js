$(document).ready(function () {
    $('#message-wrapper').click(function () {
        if ($(this).hasClass('show')) {
            $(this).removeClass('show');
        }
    })
    
    $('#header .category #product-manage-btn').click(function() {
        $("#product-manage-btn ~ .category-item__children").slideToggle('slow', function() {
            if ($(this).is(":visible")) {
                $("#product-manage-btn span:nth-child(2) i").removeClass('fa-solid fa-angle-right');
                $("#product-manage-btn span:nth-child(2) i").addClass('fa-solid fa-angle-down');
            } else {
                $("#product-manage-btn span:nth-child(2) i").removeClass('fa-solid fa-angle-down');
                $("#product-manage-btn span:nth-child(2) i").addClass('fa-solid fa-angle-right');
            }
        });
    })
});