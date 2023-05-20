$(document).ready(function () {
    $("#content .image-chooser input[type='file']").change(function(e) {
        var files = e.target.files;

        if (files.length === 1) {
            var objectURL = URL.createObjectURL(files[0]);
            $("#content .image-chooser img").attr("src", objectURL)
            $("#product-image-path").val(files[0].name)
        } else {
            e.target.value = null;
        }
    })

    $('#message-wrapper').click(function () {
        if ($(this).hasClass('show')) {
            $(this).removeClass('show');
        }
    })
    
    $('#header .category .product-manage-btn').click(function() {
        $(this).siblings(".category-item__children").slideToggle('slow', function() {
            if ($(this).is(":visible")) {
                $(this).siblings("#header .category .product-manage-btn").find("span:nth-child(2) i").removeClass('fa-solid fa-angle-right');
                $(this).siblings("#header .category .product-manage-btn").find("span:nth-child(2) i").addClass('fa-solid fa-angle-down');
            } else {
                $(this).siblings("#header .category .product-manage-btn").find("span:nth-child(2) i").removeClass('fa-solid fa-angle-down');
                $(this).siblings("#header .category .product-manage-btn").find("span:nth-child(2) i").addClass('fa-solid fa-angle-right');
            }
        });
    })
});