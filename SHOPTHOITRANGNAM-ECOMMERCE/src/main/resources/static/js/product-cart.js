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
            var selectedAll = true
            $('input[type="checkbox"].select').each((index, element) => {
                if (!element.checked) {
                    selectedAll = false
                }
            })
            console.log(selectedAll)
            if (selectedAll) {
                $('#select-all').get()[0].checked = true
            }

            $('#total-price #price-number').text((Number.parseInt($('#total-price #price-number').text().match(/\d*/g).join('')) + Number.parseInt(price)).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
        } else {
            $('#total-price #price-number').text((Number.parseInt($('#total-price #price-number').text().match(/\d*/g).join('')) - Number.parseInt(price)).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace(/\s/g, ""))
            if ($('#select-all').prop('checked')) {
                $('#select-all').get()[0].checked = false
            }
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
        let clickedElement = $(e.target).parent().parent().find('.quantity')
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
        let clickedElement = $(e.target).parent().parent().find('.quantity')
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

    $('.quantity').change(function(e) {
        if (Number.parseInt($(this).val()) > Number.parseInt($(this).attr('max'))) {
            $(this).val($(this).attr('max'))
        } else if (Number.parseInt($(this).val()) <= 0) {
            $(this).val(1)
        } else if (isNaN(Number.parseInt($(this).val()))) {
            $(this).val(1)
        }
    })

    $('input[name="sizeCode"]').each((index, element) => {
        var sizeCode = $(element).siblings('.classification-select').first().children('.select-box').first().children('.select-content').first().children('.size-list').children('.style-active').first().attr('data-size-code');
        $(element).val(sizeCode)
    })

    $('input[name="colorCode"]').each((index, element) => {
        var colorCode = $($(element).siblings('.classification-select').first().children('.select-box').first().children('.select-content')[1]).children('.color-list').children('.style-active').first().attr('data-color-code');
        $(element).val(colorCode)
    })

    $(".size-item").click(function(e) {
        $(this).siblings().removeClass('style-active')
        $(this).addClass('style-active')
    })

    $(".color-item").click(function(e) {
        $(this).siblings().removeClass('style-active')
        $(this).addClass('style-active')
    })

    $(".back-btn").click(function(e) {
        e.preventDefault()
        $(this).parent().parent().css('display', 'none')
    })

    $(".apply-btn").click(function(e) {
        e.preventDefault()
        var size = $($(this).parent().siblings('.select-content')[0]).children('.size-list').first().children('.style-active').first()
        var color = $($(this).parent().siblings('.select-content')[1]).children('.color-list').first().children('.style-active').first()
        $(this).parent().parent().parent().siblings('input[name="sizeCode"]').first().val(size.attr('data-size-code'))
        $(this).parent().parent().parent().siblings('input[name="colorCode"]').first().val(color.attr('data-color-code'))
        $(this).parent().parent().parent().siblings('.classification-desc').first().text(`${size.text()}, ${color.text()}`)
        console.log
        $(this).parent().parent().css('display', 'none')
    })
})