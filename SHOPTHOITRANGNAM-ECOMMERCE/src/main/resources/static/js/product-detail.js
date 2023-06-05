$(document).ready(function () {
    var showImage = $(".product-image-section > img")
    var oldSrc = showImage.attr("src")

    $(".color-item").mouseover(function () { 
        showImage.attr("src", $(this).attr("data-src-product-img"))
    });

    $(".color-item").mouseleave(function () { 
        showImage.attr("src", oldSrc)
    });

    $(".product-image-container img").mouseover(function () { 
        showImage.attr("src", $(this).attr("src"))
        $(".product-image-container img").removeClass("img-active")
        $(this).addClass("img-active")
    });

    $(".arrow-left").click(function (e) {
        var imageNotHide = $(".product-image-container").children(":not(.hide)")
        var lastImage = imageNotHide.first().prev()
        if (imageNotHide.length < $(".product-image-container").children().length) {
            lastImage.removeClass("hide")
            console.log(lastImage)
        }
    });

    $(".arrow-right").click(function (e) {
        var lastImage
        var imageNotHide = $(".product-image-container").children(":not(.hide)")
        var hideImage = $(".product-image-container").children(".hide")
        if (hideImage.length == 0) {
            lastImage = $(".product-image-container").children().first()
        } else {
            lastImage = hideImage.last().next()
        }
        if (imageNotHide.length > 5) {
            lastImage.addClass("hide")
        }
    });

    $('#pagination-previous').click(function (e) { 
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        var productCode = $('product').val()
        location.assign('/product-details/' + productCode + '?page-number=' + (currentPageNumber - 1 == 0 ? pageCount : currentPageNumber - 1))        
    });

    $('#pagination-next').click(function (e) {
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        var productCode = $('product').val()
        location.assign('/product-details/' + productCode + '?page-number=' + (currentPageNumber + 1 > pageCount ? 1 : currentPageNumber + 1))        
    });

    $('.increase-btn').click(function(e) {
        let unitPrice = Number.parseInt($(this).parent().parent().parent().find('.new-price').text().match(/\d*/g).join(''))
        let clickedElement = $(e.target).parent().parent().find('input[name="quantity"]')
        if (Number.parseInt(clickedElement.val())+1 > Number.parseInt(clickedElement.attr('max'))) {
            clickedElement.val(1)
        } else {
            clickedElement.val(Number.parseInt(clickedElement.val())+1)
        }
        $(this).parent().parent().parent().find('.total-price').html((Number.parseInt(clickedElement.val()) * unitPrice).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
        if ($(this).parent().parent().parent().find('input[type="checkbox"].select').prop('checked')) {
            let totalPrice = 0
            $('.total-price').each((index, element) => {
                if ($(element).parent().parent().find('input[type="checkbox"].select').prop('checked')) {
                    let priceString = element.innerHTML.match(/\d*/g).join('')
                    totalPrice += Number.parseInt(priceString)
                }
            })
            $('#total-price #price-number').text(totalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
        }
    })

    $('.decrease-btn').click(function(e) {
        let unitPrice = Number.parseInt($(this).parent().parent().parent().find('.new-price').text().match(/\d*/g).join(''))
        let clickedElement = $(e.target).parent().parent().find('input[name="quantity"]')
        if (Number.parseInt(clickedElement.val())-1 <= 0) {
            clickedElement.val(Number.parseInt(clickedElement.attr('max')))
        } else {
            clickedElement.val(Number.parseInt(clickedElement.val())-1)
        }
        $(this).parent().parent().parent().find('.total-price').html((Number.parseInt(clickedElement.val()) * unitPrice).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
        if ($(this).parent().parent().parent().find('input[type="checkbox"].select').prop('checked')) {
            let totalPrice = 0
            $('.total-price').each((index, element) => {
                if ($(element).parent().parent().find('input[type="checkbox"].select').prop('checked')) {
                    let priceString = element.innerHTML.match(/\d*/g).join('')
                    totalPrice += Number.parseInt(priceString)
                }
            })
            $('#total-price #price-number').text(totalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
        }
    })

    $('input[name="quantity"]').change(function(e) {
        if (Number.parseInt($(this).val()) > Number.parseInt($(this).attr('max'))) {
            $(this).val($(this).attr('max'))
        } else if (Number.parseInt($(this).val()) <= 0) {
            $(this).val(1)
        } else if (isNaN(Number.parseInt($(this).val()))) {
            $(this).val(1)
        }
    })
});