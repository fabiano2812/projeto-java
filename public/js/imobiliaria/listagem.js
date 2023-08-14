var Listagem = function (){
    const URL_VISUALIZAR_LISTAGEM_IMOBILIARIA = '/imobiliaria/visualizar/listagem';

    var visualizar = function (id){
        $.ajax({
            method: 'POST',
            url: URL_VISUALIZAR_LISTAGEM_IMOBILIARIA,
            data: {
                id: id
            },
            success: function (html) {
                SnackBar.show("Voce apertou no botão!", 8000);
            }
        });
    }
    return{
        abrirPopup: function (id){
            visualizar(id);
        }
    }
}()