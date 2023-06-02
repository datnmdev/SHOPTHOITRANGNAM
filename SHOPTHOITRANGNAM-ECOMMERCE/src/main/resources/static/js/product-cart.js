$(document).ready(function() {
    $('#select-all').click(function() {
        $('input[type="checkbox"].select').each((index, element) => {
            if ($('#select-all').prop('checked')) {
                element.checked = true
            } else {
                element.checked = false
            }
        });

        let totalPrice = 0
        $('.total-price').each((index, element) => {
            let priceString = element.innerHTML.match(/\d*/g).join('')
            totalPrice += Number.parseInt(priceString)
        })

        if ($('#select-all').prop('checked')) {
            $('#total-price #price-number').text(totalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
        } else {
            $('#total-price #price-number').html('0&#8363')
        }
    })

    $('input[type="checkbox"].select').click(function(e) {
        let price = $(e.target).parent().parent().parent().find('.total-price').text().match(/\d*/g).join('')
        if ($(e.target).prop('checked')) {
            $('#total-price #price-number').text((Number.parseInt($('#total-price #price-number').text().match(/\d*/g).join('')) + Number.parseInt(price)).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
        } else {
            $('#total-price #price-number').text((Number.parseInt($('#total-price #price-number').text().match(/\d*/g).join('')) - Number.parseInt(price)).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
        }
    })

    $('.classification').click(function(e) {
        let status = $(e.target).parent().find('.select-box').css('display')
        if (status == 'none') {
            $(e.target).parent().find('.select-box').css('display', 'block')
        } else {
            $(e.target).parent().find('.select-box').css('display', 'none')
        }
    })


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
})