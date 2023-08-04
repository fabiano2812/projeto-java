var dashboard = function () {
    const URL_BUSCAR_COMPRAS = '/dashboard/buscarCompras';
    const URL_BUSCAR_VENDAS = '/dashboard/buscaVenda';
    const URL_BUSCAR_ANUNCIO = '/dashboard/buscarAnuncio';
    const URL_BUSCAR_DESPESAS = '/dashboard/despesas';
    const URL_BUSCAR_RECEITAS = '/dashboard/receitas';
    const URL_BUSCAR_COMPROMISSOS = '/dashboard/compromissos';
    const URL_BUSCAR_ATENDIMENTO = '/dashboard/atendimento';
    const URL_BUSCAR_EVENTOS = '/dashboard/eventos';
    const URL_BUSCAR_EVENTOS_POR_DATA = '/dashboard/eventos/paraHoje';
    const URL_BUSCAR_PESSOAS = '/dashboard/evento/pessoa';


    var init = function () {
        buscarQuantidadeCompras();
        buscarTotalVendas();
        buscarQuantidadeAnuncios();
        buscarDespesas();
        buscarReceitas();
        buscarQuantidadeCompromissos();
        buscarQuantidaDeAtendimento();
        buscarQuantidaDeEventos();
        pessoasEventohoje();
    }
    var buscarQuantidadeCompras = function () {
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_COMPRAS,
            success: function (quantidade) {
                $('#quantidadeCompras').html(quantidade);
                quantidadePessoas();
            },
            error: function (jqXHR, exception) {
            }
        });
    }
    var quantidadePessoas = function (){
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_PESSOAS,
            success: function (quantidade) {
                $('#quantidadePessoas').html(quantidade);
            },
            error: function (jqXHR, exception) {
            }
        });
    }


    var buscarTotalVendas = function () {
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_VENDAS,
            //data: formData,
            success: function (venda) {
                $('#totalFmt').html("$" + venda);
            },
            error: function (jqXHR, exception) {
                console.log("Ocorreu um erro no servidor");
            }
        });
    };
    var buscarDespesas = function () {
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_DESPESAS,
            //data: formData,
            success: function (Despesas) {
                $('#buscarDespesas').html(Despesas);
            },
            error: function (jqXHR, exception) {
                console.log("Ocorreu um erro no servidor");
            }
        });
    }
    var buscarReceitas = function () {
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_RECEITAS,
            //data: formData,
            success: function (Receitas) {
                $('#buscarReceitas').html(Receitas);
            },
            error: function (jqXHR, exception) {
                console.log("Ocorreu um erro no servidor");
            }
        });
    }

    var buscarQuantidadeAnuncios = function () {
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_ANUNCIO,
            //data: formData,
            success: function (QuantidadeAnuncio) {
                $('#buscarQuantidadeAnuncios').html(QuantidadeAnuncio);
            },
            error: function (jqXHR, exception) {
                console.log("Ocorreu um erro no servidor");
            }
        });
    };
    var buscarQuantidadeCompromissos = function () {
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_COMPROMISSOS,
            //data: formData,
            success: function (QuantidadeCompromissos) {
                $('#QuantidadeDeCompromissos').html(QuantidadeCompromissos);
            },
            error: function (jqXHR, exception) {
                console.log("Ocorreu um erro no servidor");
            }
        });
    };
    var buscarQuantidaDeAtendimento = function () {
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_ATENDIMENTO,
            //data: formData,
            success: function (QuantidadeAtendimento) {
                $('#quantidaDeAtendimento').html(QuantidadeAtendimento);
            },
            error: function (jqXHR, exception) {
                console.log("Ocorreu um erro no servidor");
            }
        });
    };

    var  buscarQuantidaDeEventos = function (){
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_EVENTOS,
            success: function (QuantidadeEvento) {
                $('#pessoasEvento').html(QuantidadeEvento);
            },
            error: function (jqXHR, exception) {
                console.log("Ocorreu um erro no servidor");
            }
        })
    }
    var  pessoasEventohoje = function (){
        $.ajax({
            method: 'POST',
            url: URL_BUSCAR_EVENTOS_POR_DATA,
            success: function (QuantidadeEvento) {
                $('#pessoasEventoParaHoje').html(QuantidadeEvento);
            },
            error: function (jqXHR, exception) {
                console.log("Ocorreu um erro no servidor");
            }
        })
    }
    return {
        init: function () {
            init();
        }
    }
}();