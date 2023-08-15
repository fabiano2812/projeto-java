var Login = function (){

    const URL_LOGUIN = '/acesso/loguin';


   var init = function (){
    validarLogin()
   }

 /*  var validarLogin = function (){
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
               var emailValue = null;
               for (var i = 0; i < formData.length; i++) {
                   if (formData[i].name === 'email') {
                       emailValue = formData[i].value;
                       break;
                   }
               }
               var exclude = /[^@-w]|^[_@.-]|[._-]{2}|[@.]{2}|(@)[^@]*1/;
               var check = /@[\w-]+/;
               var checkend = /.[a-zA-Z]{2,3}$/;


               if(((emailValue.search(exclude) != -1)||(emailValue.search(check)) == -1)||(emailValue.search(checkend) == -1)){
                   console.log('Email inválido');
               } else {
                   console.log('Email válido');
               }
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
   }*/

    var validarLogin = function (){
        $('#formLoguin').validate({
            rules: {
                email: {
                    required: true,
                    customEmailValidation: true
                },
                senha: {
                    required: true
                }
            },
            messages: {
                email: {
                    required: 'Email obrigatório',
                    customEmailValidation: 'Digite um endereço de email válido'
                },
                senha: {
                    required: 'Senha obrigatória'
                }
            },
            errorClass: "text-danger",
            submitHandler: function (form) {
                var formData = $(form).serializeArray();
                var emailValue = null;
                for (var i = 0; i < formData.length; i++) {
                    if (formData[i].name === 'email') {
                        emailValue = formData[i].value;
                        break;
                    }
                }
                var exclude = /[^@-\w]|^[_@.-]|[._-]{2}|[@.]{2}|(@)[^@]*1/;
                var check = /@[\w-]+/;
                var checkend = /.[a-zA-Z]{2,3}$/;

                if (((emailValue.search(exclude) != -1) || (emailValue.search(check)) == -1) || (emailValue.search(checkend) == -1)) {
                    console.log('Email inválido');
                } else {
                    console.log('Email válido');
                }
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
                });
            }
        });

        // Defina a regra de validação personalizada e a função para verificar o "@".
        $.validator.addMethod('customEmailValidation', function(value, element) {
            return this.optional(element) || /@[\w-]+/.test(value);
        }, 'Digite um endereço de email válido');
    };



    return{
        init: function (){
            init();
        }
    }
}()