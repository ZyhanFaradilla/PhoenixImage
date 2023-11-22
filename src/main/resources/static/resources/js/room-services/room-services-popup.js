 (function(){
        addCloseButtonListener();
        addInsertButtonListener();
        addUpdateButtonListener();
        addSubmitInsertFormListener();
        addSubmitUpdateFormListener();
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
            $('.modal-layer').addClass('modal-layer--opened');
            $('.form-dialog').addClass('popup-dialog--opened');
        });
    }

    function addUpdateButtonListener(){
        $('.update-button').click(function(event){
            event.preventDefault();
            let employeeNumber = $(this).attr('data-id');
            $.ajax({
                url: `/api/roomServices/${employeeNumber}`,
                success: function(response){
                populateInputForm(response);
                $('.modal-layer').addClass('modal-layer--opened');
                $('.update-dialog').addClass('popup-dialog--opened');
                }
            });
        });
    }

    function populateInputForm({employeeNumber, firstName, middleName, lastName, outsourcingCompany}){
        $('.update-dialog .employeeNumber').val(employeeNumber);
        $('.update-dialog .firstName').val(firstName);
        $('.update-dialog .middleName').val(middleName);
        $('.update-dialog .lastName').val(lastName);
        $('.update-dialog .outsourcingCompany').val(outsourcingCompany);
    }

    function addSubmitInsertFormListener(){
       $('.form-dialog button').click(function(event){
          event.preventDefault();
          let dto = collectInsertDataForm();
          $.ajax({
              method: 'POST',
              url: `/api/roomServices`,
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

    function collectInsertDataForm(){
       let employeeNumber = $('.form-dialog .employeeNumber').val();
       let dto = {
             employeeNumber: employeeNumber,
             firstName: $('.form-dialog .firstName').val(),
             middleName: $('.form-dialog .middleName').val(),
             lastName: $('.form-dialog .lastName').val(),
             outsourcingCompany: $('.form-dialog .outsourcingCompany').val()
       };
       return dto;
    }

    function addSubmitUpdateFormListener(){
       $('.update-dialog button').click(function(event){
         event.preventDefault();
         let dto = collectUpdateDataForm();
         $.ajax({
             method: 'PUT',
             url: `/api/roomServices`,
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

    function collectUpdateDataForm(){
      let employeeNumber = $('.update-dialog .employeeNumber').val();
             let dto = {
                   employeeNumber: employeeNumber,
                   firstName: $('.update-dialog .firstName').val(),
                   middleName: $('.update-dialog .middleName').val(),
                   lastName: $('.update-dialog .lastName').val(),
                   outsourcingCompany: $('.update-dialog .outsourcingCompany').val()
      };
      return dto;
    }

    function writeValidationMessage(errorMessages){
        for(let error of errorMessages){
            let {field, message} = error;
            $(`.form-dialog [data-for=${field}]`).text(message);
        }
    }