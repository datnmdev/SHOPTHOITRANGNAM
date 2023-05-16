$(document).ready(function () {
    $('#pagination-previous').click(function (e) { 
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign('/owner/sizes?page-number=' + (currentPageNumber - 1 == 0 ? pageCount : currentPageNumber - 1))        
    });

    $('#pagination-next').click(function (e) {
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign('/owner/sizes?page-number=' + (currentPageNumber + 1 > pageCount ? 1 : currentPageNumber + 1))        
    });

    $('.message-wrapper').click(function () {
        if ($(this).hasClass('show')) {
            $(this).removeClass('show');
        }
    })
    
    fetch("/owner/sizes?export", {method: "GET"})
        .then(response => response.json())
        .then(data => {
            var convertedSizeData = [["Mã kích thước", "Tên kích thước"]]
            data.forEach(sizeData => {
                let size = []
                size.push(sizeData.sizeCode)
                size.push(sizeData.sizeName)

                convertedSizeData.push(size)
            })

            const workbook = new ExcelJS.Workbook();
            const worksheet = workbook.addWorksheet('Danh sách kích thước sản phẩm');
            worksheet.addRows(convertedSizeData);
            workbook.xlsx.writeBuffer().then(buffer => {
                const blob = new Blob([buffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
                const url = URL.createObjectURL(blob);
                $('#export').attr('href', url);
                $('#export').attr('download', 'danh_sách_kích_thước_sản_phẩm.xlsx')
                $('#export').click()
            })
        })
        .catch(error => console.log(error.message))

    $("#goto-page-number-btn").click(function() {
        var displayValue = $("form.goto-page-box").css("display")
        $("form.goto-page-box").css("display", displayValue == "block" ? "none" : "block");
    })

    $("form#goto-page-form").submit(function(e) {
        e.preventDefault()
        var pageNumber = $("input[name='page-number'").val()
        location.assign("/owner/sizes?page-number=" + pageNumber)
    })

    $("#first-page-btn").click(function() {
        location.assign("/owner/sizes?page-number=" + 1)
    })

    $("#last-page-btn").click(function() {
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign("/owner/sizes?page-number=" + (isNaN(pageCount) ? 1 : pageCount))
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