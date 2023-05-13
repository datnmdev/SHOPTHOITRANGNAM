$(document).ready(function () {
    $('#product-search-form').on('submit', function (e) {
        e.preventDefault();
        var inputValue = $('#product-search').val()
        window.location.assign('/owner/product-manage?search=' + inputValue)
    })

    $('.update-product-btn').click(function (e) {
        var productCode = $(this).attr('product-code')
        location.href = "/owner/product-manage/update-product/" + productCode
    })

    $('.delete-product-btn').click(function (e) {
        var productCode = $(this).attr('product-code')
        location.href = "/owner/product-manage/delete-product/" + productCode
    })

    $('#pagination-previous').click(function (e) { 
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign('/owner/product-manage?page-number=' + (currentPageNumber - 1 == 0 ? pageCount : currentPageNumber - 1))        
    });

    $('#pagination-next').click(function (e) {
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign('/owner/product-manage?page-number=' + (currentPageNumber + 1 > pageCount ? 1 : currentPageNumber + 1))        
    });

    $('#message-wrapper').click(function () {
        if ($(this).hasClass('show')) {
            $(this).removeClass('show');
        }
    })
    
    fetch("/owner/product-manage/products", {method: "GET"})
        .then(response => response.json())
        .then(data => {
            var convertedProductData = [["Mã sản phẩm", "Tên sản phẩm", "Mô tả", "Loại sản phẩm", "Trạng thái"]]
            data.forEach(productData => {
                let product = []
                product.push(productData.productCode)
                product.push(productData.productName)
                product.push(productData.description)
                product.push(productData.productCategory.productCategoryName)
                product.push(productData.productDetails.length == 0 ? "Hết hàng" : "Còn hàng")

                convertedProductData.push(product)
            })

            const workbook = new ExcelJS.Workbook();
            const worksheet = workbook.addWorksheet('Danh sách sản phẩm');
            worksheet.addRows(convertedProductData);
            workbook.xlsx.writeBuffer().then(buffer => {
                const blob = new Blob([buffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
                const url = URL.createObjectURL(blob);
                $('#export').attr('href', url);
                $('#export').attr('download', 'danh_sách_sản_phẩm.xlsx')
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
        location.assign("/owner/product-manage?page-number=" + pageNumber)
    })

    $("#first-page-btn").click(function() {
        location.assign("/owner/product-manage?page-number=" + 1)
    })

    $("#last-page-btn").click(function() {
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign("/owner/product-manage?page-number=" + pageCount)
    })
});