$(document).ready(function () {
    // Slider
    setInterval(function() {
        var slide = $(".slider__product__link").get()[0]
        $(slide).css('animation', 'width-out 0.2s ease-out forwards')
        setTimeout(function() {
            $(slide).css('animation', '')
            $('#slider__product').append($(slide))
        }, 1000)
    }, 3500)

    // Flash sale - time down
    // timeDown($('input[name="time-down"]').val())

    // Products
    setInterval(function() {
        var products =  $(".product-list").get()
        products.forEach(product => {
            if ($(product).children('.product-item').get().length > 4) {
                var firstProduct = $(product).children('.product-item').get()[0]
                $(firstProduct).css('animation', 'width-out 0.3s ease-out forwards')
                setTimeout(function() {
                    $(firstProduct).css('animation', '')
                    $(product).append($(firstProduct))
                }, 300)
            }
        });
    }, 4500)

    // Functions
    function timeDown(seconds) {
        setInterval(function() {
            --seconds;
            $('#time-down').text(convertSecondsToTimeString(seconds))
        }, 1000)
    }

    function convertSecondsToTimeString(seconds) {
        var date = new Date(null);
        date.setSeconds(seconds);
        return date.toISOString().substr(11, 8);
    }
});