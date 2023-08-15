var Plano = function () {
    const URL_ABRIR_POPUP = '/plano/abrirPopup';
    const URL_SALRVAR_DADOS = '/plano/salvarDados';

    var abrirPopupCompra = function (id) {
        $.ajax({
            method: 'POST',
            url: URL_ABRIR_POPUP,
            data: {
                id: id
            },
            success: function (html) {
            }
        })
    }

    return {
        abrirPopup: function (id) {
            abrirPopupCompra(id);
        }
    }
}()