window.onload = function() {
    // Sự kiện slider
    const sliderElements = document.querySelectorAll('.slider__product__link')
    const sliderBarElements = document.querySelectorAll(".slider__bar__element")
    new Sliding(0, sliderElements, sliderBarElements).run()

    function Sliding(slideNumber, sliderElements, sliderBarElements) {
        let _this = this
        this.slideNumber = slideNumber
        
        this.run = function() {
            return setInterval(function() {
                if (_this.slideNumber == 0) {
                    sliderElements[_this.slideNumber].classList.remove('hide')
                    sliderBarElements[_this.slideNumber].style.backgroundColor = '#000'
                } else if (_this.slideNumber < sliderBarElements.length) {
                    sliderElements[_this.slideNumber-1].classList.add('hide')
                    sliderElements[_this.slideNumber].classList.remove('hide')
                    sliderBarElements[_this.slideNumber-1].style.backgroundColor = '#fff'
                    sliderBarElements[_this.slideNumber].style.backgroundColor = '#000'
                } else {
                    sliderElements[_this.slideNumber-1].classList.add('hide')
                    sliderBarElements[_this.slideNumber-1].style.backgroundColor = '#fff'
                    _this.slideNumber = 0
                    sliderElements[_this.slideNumber].classList.remove('hide')
                    sliderBarElements[_this.slideNumber].style.backgroundColor = '#000'
                }
                ++_this.slideNumber
            }, 3000)
        }
    }
}