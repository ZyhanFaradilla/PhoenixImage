(function(){
        addCloseButtonListener();
        addInsertButtonListener();
        addSubmitInsertFormListener();
}())

    function addCloseButtonListener(){
        $('.close-button').click(function(event){
            $('.modal-layer').removeClass('modal-layer--opened');
            $('.popup-dialog').removeClass('popup-dialog--opened');
            $('.popup-dialog input').val("");
            $('.popup-dialog textarea').val("");
            $('.popup-dialog .validation-message').text("");
        });
    }

     function addInsertButtonListener(){
        $('.create-button').click(function(event){
           event.preventDefault();
           let roomNumber = $(this).attr('data-id');
           $.ajax({
              url: `/api/roomInventories/${roomNumber}`,
              success: function(response){
                      addInputForm(response);
                      $('.modal-layer').addClass('modal-layer--opened');
                      $('.input-dialog').addClass('popup-dialog--opened');
                      }
           });
        });
     }

     function addInputForm({roomNumber}){
        $('.input-dialog .roomNumber').val(roomNumber);
     }

     function addSubmitInsertFormListener(){
         $('.input-dialog button').click(function(event){
           event.preventDefault();
           let dto = addInsertForm();
           $.ajax({
               method: 'POST',
               url: `/api/roomInventories`,
               data: JSON.stringify(dto),
               contentType: 'application/json',
               success: function(response){
                       location.reload();
               },
               error: function({status, responseJSON}){
                      if(status === 422){
                         writeValidationMessage(responseJSON);
                      }
               }
           });
         });
     }

     function addInsertForm(){
        let roomNumber = $('.input-dialog .roomNumber').val();
        let dto = {
              roomNumber: roomNumber,
              inventoryName: $('.input-dialog .inventoryName').val(),
              quantity: $('.input-dialog .quantity').val()
        };
        return dto;
     }

    function writeValidationMessage(errorMessages){
        for(let error of errorMessages){
            let {field, message} = error;
            $(`.form-dialog [data-for=${field}]`).text(message);
        }
    }