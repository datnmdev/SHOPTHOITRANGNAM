import * as map from './google-map.js'

$(document).ready(function () {
    $('#change-address-btn').click(function () {
        $('#address-box').css('display', 'block')
    })

    $('#cancel-btn').click(function () {
        $('#address-box').css('display', 'none')
    })

    $('#add-adress-btn').click(function() {
        $('#address-choice-box').css('display', 'none');
        $('#add-address-box').css('display', 'block');
        map.initMap('add-address__map')
    })

    $('#add-address__cancel-btn').click(function () {
        $('#add-address-box').css('display', 'none')
        $('#address-choice-box').css('animation', 'none')
        $('#address-choice-box').css('display', 'block');
    })

    $('.wrapper #add-address-form label input').on('input', function () {
        if ($(this).val()) {
            $(this).prev().css('display', 'block');
        } else {
            $(this).prev().css('display', 'none');
        }
    })

    $('#address-choice-box .address-update-btn').click(function () {
        var addAddressForm = $(this).parents('.address-box__info')
        $('#update-address-form input[name="full-name"]').val(addAddressForm.find('.contact-info__full-name').text().trim())
        $('#update-address-form input[name="telephone"]').val(addAddressForm.find('.contact-info__telephone').text().trim())
        $('#update-address-form input[name="address"]').val(addAddressForm.find('.standard-address').text().trim())
        $('#update-address-form input[name="home-address-input"]').val(addAddressForm.find('.address-detail').text().trim())
        $('.wrapper #update-address-form label input').each(function (index, element) {
            $(element).prev().css('display', 'block');
        })
        $('#address-choice-box').css('display', 'none');
        $('#update-address-box').css('display', 'block');
        map.initMap('update-address__map')
    })

    $('#update-address__cancel-btn').click(function () {
        $('#update-address-box').css('display', 'none')
        $('#address-choice-box').css('animation', 'none')
        $('#address-choice-box').css('display', 'block');
    })

    $('.wrapper #update-address-form label input').on('input', function () {
        if ($(this).val()) {
            $(this).prev().css('display', 'block');
        } else {
            $(this).prev().css('display', 'none');
        }
    })

    $('#shipping-carrier__change-btn').click(function() {
        $('#shipping-carrier__container').css('display', 'block')
    })

    $('#shipping-carrier__box .shipping-carrier-item').click(function() {
        $('#shipping-carrier__box .shipping-carrier-item').each((index, element) => {
            if (element.classList.contains('shipping-carrier-active')) {
                $(element).removeClass('shipping-carrier-active')
            }
        })
        $(this).addClass('shipping-carrier-active')
    })

    $('#shipping-carrier__cancel-btn').click(function() {
        $('#shipping-carrier__container').css('display', 'none')
    })
});