var Usuario = function (){

    const URL_SALVAR = "/usuarios/salvar";

    var init = function (){
        console.log("teste")
        validarCadastroUsuario()
    }

    var validarCadastroUsuario = function (){
        $('#formCadastroUsuario').validate({
            rules: {
                nome: {
                    required: true
                },
                email: {
                    required: true
                },
                senha: {
                    required: true
                },
            },
            messages: {
                nome: {
                    required: 'Informe seu seu nome'
                },
                email: {
                    required: 'Informe seu Email'
                },
                senha: {
                    required: 'Informe sua Senha'
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
                        swal("Muito bom!", "Usuario salvo com!", "success");
                    },
                    error: function (jqXHR, exception) {
                        console.log("Ocorreu um erro no servidor");
                    }
                });
            }
        });
    }

    var voltar = function (){
        window.location.href = "http://localhost:9000/";
    }

    return{
        init: function (){
            init();
        },
        menuLogin: function (){
            voltar()
        }
    }
}()