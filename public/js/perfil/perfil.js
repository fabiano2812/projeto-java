var Perfil = function (){

    const URL_SALVAR = '/perfil/salvar';
    const URL_SALVAR_SENHA = '/perfil/salvar/senha';

    var init = function (){
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
                    success: function (formData) {
                        console.log(formData)

                        Dialog.openSuccessDialog({
                            title:'Tudo certo!',
                            text: "Senha alterado com sucesso!"
                        })
                    },
                    error: function (jqXHR, exception) {
                        console.log("Ocorreu um erro no servidor");
                    }
                });
            }
        })
    }

    var iniciarValidacao = function (){
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
                fullimobiliaria: {
                    required: true
                },
                fullSenha: {
                    required: true
                },
                fullSobre: {
                    required: true
                },
                fullTrabalho: {
                    required: true
                },
                fullEmpresa: {
                    required: true
                },
                fullPais: {
                    required: true
                },
                fullEndereco: {
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
                fullimobiliaria: {
                    required: 'Informe sua Imobiliaria'
                },
                fullSenha: {
                    required: 'Informe sua Senha'
                },
                fullSobre: {
                    required: 'Informe sobre'
                },
                fullTrabalho: {
                    required: 'Informe seu trabalho'
                },
                fullEmpresa: {
                    required: 'Informe sua empresa'
                },
                fullPais: {
                    required: 'Informe seu pais'
                },
                fullEndereco: {
                    required: 'Informe seu pais'
                },
            },
            errorClass: "text-danger",
            submitHandler: function (form) {
                var formData = $(form).serializeArray();

                $.ajax({
                    method: 'POST',
                    url: URL_SALVAR,
                    data: formData,
                    success: function (formData) {
                       Dialog.openSuccessDialog({
                           title:'Tudo certo!',
                           text: "Perfil alterado com sucesso!"
                       })
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
            iniciarValidacao();
            salvarNovaSenha();
        },
        voltar: function (){
        }
    }

}()