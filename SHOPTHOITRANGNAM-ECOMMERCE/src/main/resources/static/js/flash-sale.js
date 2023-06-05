$(document).ready(function () {
    // Flash sale - time down
    timeDown($('#time-down').attr('data-time-down'))

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