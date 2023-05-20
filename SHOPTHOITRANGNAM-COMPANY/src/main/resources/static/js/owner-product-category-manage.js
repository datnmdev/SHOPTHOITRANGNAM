$(document).ready(function () {
    $('#pagination-previous').click(function (e) { 
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign('/owner/product-categories?page-number=' + (currentPageNumber - 1 == 0 ? pageCount : currentPageNumber - 1))        
    });

    $('#pagination-next').click(function (e) {
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign('/owner/product-categories?page-number=' + (currentPageNumber + 1 > pageCount ? 1 : currentPageNumber + 1))        
    });

    $('.message-wrapper').click(function () {
        if ($(this).hasClass('show')) {
            $(this).removeClass('show');
        }
    })
    
    fetch("/owner/product-categories?export", {method: "GET"})
        .then(response => response.json())
        .then(data => {
            var convertedProductCategoryData = [["Mã loại sản phẩm", "Tên loại sản phẩm"]]
            data.forEach(productCategoryData => {
                let productCategory = []
                productCategory.push(productCategoryData.productCategoryCode)
                productCategory.push(productCategoryData.productCategoryName)

                convertedProductCategoryData.push(productCategory)
            })

            const workbook = new ExcelJS.Workbook();
            const worksheet = workbook.addWorksheet('Danh sách danh mục sản phẩm');
            worksheet.addRows(convertedProductCategoryData);
            workbook.xlsx.writeBuffer().then(buffer => {
                const blob = new Blob([buffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
                const url = URL.createObjectURL(blob);
                $('#export').attr('href', url);
                $('#export').attr('download', 'danh_sách_danh_mục_sản_phẩm.xlsx')
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
        location.assign("/owner/product-categories?page-number=" + pageNumber)
    })

    $("#first-page-btn").click(function() {
        location.assign("/owner/product-categories?page-number=" + 1)
    })

    $("#last-page-btn").click(function() {
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign("/owner/product-categories?page-number=" + (isNaN(pageCount) ? 1 : pageCount))
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