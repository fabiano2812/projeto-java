var Perfil = function (){

    const URL_SALVAR = '/perfil/salvar/alteracaoEmail';
    const URL_SALVAR_SENHA = '/perfil/salvar/senha';

    var init = function (){
        validarDadosPerfilEmail()
        salvarNovaSenha();
    }

    var salvarNovaSenha = function (){
            $('#novaSenha').validate({
            rules: {
                password: {
                    required: true
                },
                newpassword: {
                    required: true
                },
                renewpassword: {
                    required: true
                }
            },
            messages: {
                password: {
                    required: 'Informe a sua senha'
                },
                newpassword: {
                    required: 'Informe nova senha'
                },
                renewpassword: {
                    required: 'Informe novamente a senha'
                },
            },
            errorClass: "text-danger",
            submitHandler: function (form) {
                var formData = $(form).serializeArray();

                $.ajax({
                    method: 'POST',
                    url: URL_SALVAR_SENHA,
                    data: formData,
                    success: function () {
                        SnackBar.show("Senha Alterada com sucesso!", 8000);
                    },
                    error: function (jqXHR, exception) {
                        console.log("Ocorreu um erro no servidor");
                    }
                });
            }
        })
    }

    var validarDadosPerfilEmail = function (){
        $('#formUsuariovalidacao').validate({
            rules: {
                id: {
                    required: true
                },
                fullName: {
                    required: true
                },
                fullEmail: {
                    required: true
                },
            },
            messages: {
                fullName: {
                    required: 'Informe seu seu nome'
                },
                fullEmail: {
                    required: 'Informe seu Email'
                },
            },
            errorClass: "text-danger",
            submitHandler: function (form) {
                var formData = $(form).serializeArray();
                $.ajax({
                    method: 'POST',
                    url: URL_SALVAR,
                    data: formData,
                    success: function () {
                        SnackBar.show("Perfil Alterado com sucesso!", 8000);
                    },
                    error: function (jqXHR, exception) {
                        console.log("Ocorreu um erro no servidor");
                    }
                });
            }
        });
    }
    return{
        init: function (){
            init();
        },
    }

}()