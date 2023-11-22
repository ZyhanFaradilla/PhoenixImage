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
            let username = $(this).attr('data-id');
            $.ajax({
                url: `/api/administrators/${username}`,
                success: function(response){
                populateInputForm(response);
                $('.modal-layer').addClass('modal-layer--opened');
                $('.update-dialog').addClass('popup-dialog--opened');
                }
            });
        });
    }

    function populateInputForm({username, jobTitle}){
        $('.update-dialog .username').val(username);
        $('.update-dialog .jobTitle').val(jobTitle);
    }

    function addSubmitInsertFormListener(){
       $('.form-dialog button').click(function(event){
          event.preventDefault();
          let dto = collectInsertDataForm();
          $.ajax({
              method: 'POST',
              url: '/api/administrators',
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
       let username = $('.form-dialog .username').val();
       let dto = {
             username: username,
             jobTitle: $('.form-dialog .jobTitle').val()
       };
       return dto;
    }

    function addSubmitUpdateFormListener(){
       $('.update-dialog button').click(function(event){
         event.preventDefault();
         let dto = collectUpdateDataForm();
         $.ajax({
             method: 'PUT',
             url: '/api/administrators',
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
      let username = $('.update-dialog .username').val();
      let dto = {
          username: username,
          jobTitle: $('.update-dialog .jobTitle').val()
      };
      return dto;
    }

    function writeValidationMessage(errorMessages){
        for(let error of errorMessages){
            let {field, message} = error;
            $(`.form-dialog [data-for=${field}]`).text(message);
        }
    }