$(document).ready(function () {
    var supplierCode = $('#supplier-code').val()

    $('#pagination-previous').click(function (e) { 
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign(`/owner/purchase-orders?supplier-code=${supplierCode}&page-number=` + (currentPageNumber - 1 == 0 ? pageCount : currentPageNumber - 1))        
    });

    $('#pagination-next').click(function (e) {
        e.preventDefault()
        var currentPageNumber = Number.parseInt($('#pagination').attr('current-page-number'))
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign(`/owner/purchase-orders?supplier-code=${supplierCode}&page-number=` + (currentPageNumber + 1 > pageCount ? 1 : currentPageNumber + 1))        
    });

    $('.message-wrapper').click(function () {
        if ($(this).hasClass('show')) {
            $(this).removeClass('show');
        }
    })
    
    fetch(`/owner/purchase-orders?supplier-code=${supplierCode}&export`, {method: "GET"})
        .then(response => response.json())
        .then(data => {
            var convertedPurchaseOrderData = [["Mã phiếu đặt", "Tên phiếu đặt", "Mô tả", "Thời điểm lập", "Mã nhà cung cấp", "Trạng thái"]]
            data.forEach(purchaseOrderData => {
                let purchaseOrderDetail = []
                purchaseOrderDetail.push(purchaseOrderData.purchaseOrderCode)
                purchaseOrderDetail.push(purchaseOrderData.purchaseOrderName)
                purchaseOrderDetail.push(purchaseOrderData.description)
                purchaseOrderDetail.push(purchaseOrderData.creationTime)
                purchaseOrderDetail.push(purchaseOrderData.supplierCode)
                if (purchaseOrderData.status === null) {
                    purchaseOrderDetail.push('Chờ lấy hàng')
                } else {
                    purchaseOrderData.push(purchaseOrderData.status ? 'Thành công' : 'Đã hủy')
                }

                convertedPurchaseOrderData.push(purchaseOrderDetail)
            })

            const workbook = new ExcelJS.Workbook();
            const worksheet = workbook.addWorksheet('Danh sách phiếu đặt');
            worksheet.addRows(convertedPurchaseOrderData);
            workbook.xlsx.writeBuffer().then(buffer => {
                const blob = new Blob([buffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
                const url = URL.createObjectURL(blob);
                $('#export').attr('href', url);
                $('#export').attr('download', 'danh_sách_phiếu_đặt.xlsx')
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
        location.assign(`/owner/purchase-orders?supplier-code=${supplierCode}&page-number=` + pageNumber)
    })

    $("#first-page-btn").click(function() {
        location.assign(`/owner/purchase-orders?supplier-code=${supplierCode}&page-number=` + 1)
    })

    $("#last-page-btn").click(function() {
        var pageCount = Number.parseInt($('#pagination').attr('page-count'))
        location.assign(`/owner/purchase-orders?supplier-code=${supplierCode}&page-number=` + (isNaN(pageCount) ? 1 : pageCount))
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

    $(".orther-option-btn").click(function() {
        var displayValue =  $(this).siblings(".orther-option-box").css("display")
        $(this).siblings(".orther-option-box").css("display", displayValue == "block" ? "none" : "block")
    })
});