var Login = function (){

    const URL_LOGUIN = '/acesso/loguin';


   var init = function (){
    validarLogin()
   }

   var validarLogin = function (){
       $('#formLoguin').validate({
           rules: {
               email: {
                   required: true
               },
               senha:{
                   required: true
               }
           },
           messages: {
               email: {
                   required: 'Email obrigatorio'
               },
               senha:{
                   required: 'Senha obrigatorio'
               }
           },
           errorClass: "text-danger",
           submitHandler: function (form) {
               var formData = $(form).serializeArray();
               console.log(formData)
               $.ajax({
                   method: 'POST',
                   url: URL_LOGUIN,
                   data: formData,
                   success: function (usuario){
                       if(usuario){
                           SnackBar.show("Bem vindo!", 5000);
                           window.location.href = "http://localhost:9000/dashboard/page/inicial";

                       } else {
                           SnackBar.show("Nenhum usuário encontrado", 5000);
                       }
                   }
               })
           }
       })
   }


    return{
        init: function (){
            init();
        }
    }
}()