(function(){
       addTotalCostListener();
       totalIncomeAddListener();
       checkTotalIncomeListener();
       addCloseButtonListener();
       addDetailButtonListener();
}())

function addTotalCostListener(){
    $('.totalCost').click(function(event){
       let checkIn = $('.checkIn').val();
       let checkOut = $('.checkOut').val();
       let checkInDate = new Date(checkIn);
       let checkOutDate = new Date(checkOut);
       let countDays = days_between(checkInDate, checkOutDate);
       let total = $('.cost-hidden').text();
       let cost = Number(total);
       let totalCost = cost * countDays;
       const formatter = new Intl.NumberFormat('id-ID', {
         style: 'currency',
         currency: 'IDR',
       });
       $(this).text(formatter.format(totalCost));
    });
}

function days_between(date1, date2) {
    const ONE_DAY = 1000 * 60 * 60 * 24;
    const differenceMs = Math.abs(date1 - date2);
    return Math.round(differenceMs / ONE_DAY);
}

function totalIncomeAddListener(){
    $('.total-button').click(function(event){
        event.preventDefault();
         $('.modal-layer').addClass('modal-layer--opened');
         $('.form-dialog').addClass('popup-dialog--opened');
    });
}

function checkTotalIncomeListener(){
    $('.form-dialog button').click(function(event){
       event.preventDefault();
        let month = $('.month').val();
        let year = $('.year').val();
        const formatter = new Intl.NumberFormat('id-ID', {
                 style: 'currency',
                 currency: 'IDR',
        });
        $.ajax({
            url: `/api/reservationLog/${month}/${year}`,
            success: function({totalIncome}){
                     $('.form-dialog .totalIncome').text(formatter.format(totalIncome));
            }
        });
    });
}

function addCloseButtonListener(){
   $('.close-button').click(function(event){
      $('.modal-layer').removeClass('modal-layer--opened');
      $('.popup-dialog').removeClass('popup-dialog--opened');
      $('.popup-dialog input').val("");
      $('.popup-dialog textarea').val("");
      $('.popup-dialog .validation-message').text("");
   });
}

function addDetailButtonListener(){
    $('.detail-button').click(function(event){
        event.preventDefault();
        let code = $(this).attr('data-id');
        const formatter = new Intl.NumberFormat('id-ID', {
                         style: 'currency',
                         currency: 'IDR',
        });
        $.ajax({
                url: `/api/reservationLog/detail/${code}`,
                success: function({code, reservationMethod, roomNumber, roomFloor, roomType, guestUsername, guestFullName, bookDate, checkIn, checkOut, cost, paymentDate, paymentMethod, remark}){
                $('.detail-dialog .code').text(code);
                $('.detail-dialog .reservationMethod').text(reservationMethod);
                $('.detail-dialog .roomNumber').text(roomNumber);
                $('.detail-dialog .roomFloor').text(roomFloor);
                $('.detail-dialog .roomType').text(roomType);
                $('.detail-dialog .guestUsername').text(guestUsername);
                $('.detail-dialog .guestFullName').text(guestFullName);
                $('.detail-dialog .bookDate').text(bookDate);
                $('.detail-dialog .checkIn').text(checkIn);
                $('.detail-dialog .checkOut').text(checkOut);
                $('.detail-dialog .total-cost').text(formatter.format(cost));
                $('.detail-dialog .paymentDate').text(paymentDate);
                $('.detail-dialog .paymentMethod').text(paymentMethod);
                $('.detail-dialog .remark').text(remark);
                 $('.modal-layer').addClass('modal-layer--opened');
                 $('.detail-dialog').addClass('popup-dialog--opened');
                }
        });
    });
}