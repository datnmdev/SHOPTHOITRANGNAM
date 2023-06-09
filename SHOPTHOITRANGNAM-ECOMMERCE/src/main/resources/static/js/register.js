$(document).ready(function () {
    fetch("/provinces", {method: "GET"})
    .then(response => response.json())
    .then(data => {
        data.forEach(element => {
            $('#province').append(`<option value='${element.provinceCode}'>${element.provinceName}</option>`);
        });
    })

    fetch("/districts?provinceCode=01", {method: "GET"})
    .then(response => response.json())
    .then(data => {
        data.forEach(element => {
            $('#district').append(`<option value='${element.districtCode}'>${element.districtName}</option>`);
        });
    })

    fetch("/wards?districtCode=001", {method: "GET"})
    .then(response => response.json())
    .then(data => {
        data.forEach(element => {
            $('#ward').append(`<option value='${element.wardCode}'>${element.wardName}</option>`);
        });
    })

    $('#province').change(function (e) {
        $('#district').empty()
        fetch(`/districts?provinceCode=${e.target.value}`, {method: "GET"})
        .then(response => response.json())
        .then(data => {
            data.forEach(element => {
                $('#district').append(`<option value='${element.districtCode}'>${element.districtName}</option>`);
            });
        })
    });

    $('#district').change(function (e) {
        $('#ward').empty()
        fetch(`/wards?districtCode=${e.target.value}`, {method: "GET"})
        .then(response => response.json())
        .then(data => {
            data.forEach(element => {
                $('#ward').append(`<option value='${element.wardCode}'>${element.wardName}</option>`);
            });
        })
    });
});