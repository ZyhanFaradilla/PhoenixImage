 (function(){
        addCloseButtonListener();
        addInsertButtonListener();
        addUpdateButtonListener();
        addSubmitInsertFormListener();
        addSubmitUpdateFormListener();
        addSubmitDeleteButtonListener();
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
            let name = $(this).attr('data-id');
            $.ajax({
                url: `/api/inventories/${name}`,
                success: function(response){
                populateInputForm(response);
                $('.modal-layer').addClass('modal-layer--opened');
                $('.update-dialog').addClass('popup-dialog--opened');
                }
            });
        });
    }

    function populateInputForm({name, stock, description}){
        $('.update-dialog .name').val(name);
        $('.update-dialog .stock').val(stock);
        $('.update-dialog .description').val(description);
    }

    function addSubmitInsertFormListener(){
       $('.form-dialog button').click(function(event){
          event.preventDefault();
          let dto = collectInsertDataForm();
          $.ajax({
              method: 'POST',
              url: '/api/inventories',
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
       let name = $('.form-dialog .name').val();
       let dto = {
             name: name,
             stock: $('.form-dialog .stock').val(),
             description: $('.form-dialog .description').val()
       };
       return dto;
    }

    function addSubmitUpdateFormListener(){
       $('.update-dialog button').click(function(event){
         event.preventDefault();
         let dto = collectUpdateDataForm();
         $.ajax({
             method: 'PUT',
             url: '/api/inventories',
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
      let name = $('.update-dialog .name').val();
      let dto = {
          name: name,
          stock: $('.update-dialog .stock').val(),
          description: $('.update-dialog .description').val()
      };
      return dto;
    }

    function writeValidationMessage(errorMessages){
        for(let error of errorMessages){
            let {field, message} = error;
            $(`.form-dialog [data-for=${field}]`).text(message);
        }
    }

    function populateDeleteForm(dependencies){
      $(".delete-dialog .dependencies").text(dependencies);
    }

    function addSubmitDeleteButtonListener(){
       $('.delete-button').click(function(event){
         event.preventDefault();
         let name = $(this).attr('data-id');
         $.ajax({
            method: 'DELETE',
            url: `/api/inventories/${name}`,
            success: function(dependencies){
               if(dependencies == 0){
                   location.reload();
               } else {
                   populateDeleteForm(dependencies);
                   $('.modal-layer').addClass('modal-layer--opened');
                   $('.delete-dialog').addClass('popup-dialog--opened');
               }
            }
         });
       });
     }