var Anuncio = function () {

    const URL_FILTRAR = '/anuncios/filtrar';
    const URL_EDITAR = '/anuncios/editar';
    const URL_SALVAR = '/anuncios/salvar';
    const URL_EXCLUIR = '/anuncios/excluir';
    const URL_Visualizar = '/anuncio/visualizar';
    const URL_PUBLICAR = '/anuncio/publicar';

    var init = function () {
        Crud.init({
            searchMethod: URL_FILTRAR,
            columns: [
                {
                    data: 'id',
                    wisth: '50px'
                },
                {
                    data: 'titulo',
                    wisth: '50px'
                },
                {
                    data: 'descricao',
                    wisth: '50px'
                },
                {
                    data: 'id',
                    wisth: '50px'
                }
            ],
            columnDefs: [
                {
                    targets: -1,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        var acoesHtml = '';
                        acoesHtml += '<button class="btn btn-sm btn-warning me-3" title="Publicar"onclick="Anuncio.publicar(' + full.id + ')">';
                        acoesHtml += 'Publicar';
                        acoesHtml += '</button>';
                        acoesHtml += '<button class="btn btn-sm btn-success me-3" title="Visualizar"onclick="Anuncio.visualizar(' + full.id + ')">';
                        acoesHtml += 'Visualizar';
                        acoesHtml += '</button>';
                        acoesHtml += '<button class="btn btn-sm btn-primary me-3" title="Editar"onclick="Anuncio.abrirEdicao(' + full.id + ')">';
                        acoesHtml += 'Editar';
                        acoesHtml += '</button>';
                        acoesHtml += '<button class="btn btn-sm btn-danger mt-1" title="Excluir"onclick="Anuncio.excluir(' + full.id + ')">';
                        acoesHtml += 'Excluir';
                        acoesHtml += '</button>';
                        return acoesHtml;
                    }
                }
            ]
        })
    }

    var iniciarValidacaoAnuncio = function () {
        $('#formAnuncio').validate({
            rules: {
                titulo: {
                    required: true
                },
                descricao: {
                    required: true
                }
            },
            messages: {
                titulo: {
                    required: 'Informe o titulo'
                },
                descricao: {
                    required: 'Informe a descrição'
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
                        Dialog.openSuccessDialog({
                            title: 'Tudo certo!',
                            text: "Anuncio salvo com sucesso!"
                        })
                        Crud.refreshDataTable();
                        Anuncio.voltarTelaPrincipal();
                    },
                    error: function (jqXHR, exception) {
                        console.log("Ocorreu um erro no servidor");
                    }
                });
            }
        });
    }

    var abrirEdicao = function (id) {
        $.ajax({
            method: 'POST',
            url: URL_EDITAR,
            data: {
                id: id
            },
            success: function (htmlCadastro) {
                Anuncio.esconderTelaPrincipal();
                $('#area-edicao').html(htmlCadastro)
                iniciarComponentesEdicao();
            }
        });
    }

    var iniciarComponentesEdicao = function () {
        // iniciar select2 aqui

        new Select2Commons().init({
            id: "estado",
            searchMethod: "/estados/buscarEstados",
            placeholder: "Selecione",
            placeholderEmptyParent: "Selecione um estado para habilitar este campo",
            displayField: "nome",
            required: true
        });

        new Select2Commons().init({
            id: "cidade",
            searchMethod: "/cidades/buscarCidadesPorEstado",
            placeholder: "Selecione",
            displayField: "nome",
            parentId: "estado",
            required: true
        });

        new Select2Commons().init({
            id: "bairro",
            searchMethod: "/bairro/buscarBairro",
            placeholder: "Selecione",
            displayField: "nome",
            parentId: "cidade",
            required: true
        });

        iniciarValidacaoAnuncio()
        GenericComponents.initDecimalInput();
    }

    var excluir = function (id) {
        Dialog.askConfirmation({
            title: 'Tem certeza?',
            text: "Você não poderá reverter isso!",
            afterConfirm: function () {
                $.ajax({
                    method: 'POST',
                    url: URL_EXCLUIR,
                    data: {
                        id: id
                    },
                    success: function () {
                        $('.linha-' + id).remove();
                        Dialog.openSuccessDialog({
                            title: 'Excluído!',
                            text: 'Seu arquivo foi excluído.'
                        })
                        Crud.refreshDataTable();
                    },
                    error: function (jqXHR, exception) {
                        console.log("Erro no servidor ao excluir.");
                    }
                });
            }
        })
    }

    var visualizar = function (id) {
        $.ajax({
            method: 'POST',
            url: URL_Visualizar,
            data: {
                id: id
            },
            success: function (html) {
                Anuncio.esconderTelaPrincipal();
                $('#area-visualizacao').html(html);
            }
        });
    }

    var publicar = function (id) {
        $.ajax({
            method: 'POST',
            url: URL_PUBLICAR,
            data: {
                id: id
            },
            success: function (html) {
                Popup.show({
                    title: 'Anuncio',
                    content: html,
                    aoAbrir: () => {
                        aoAbrirPopupPublicacao();
                    }
                })
            }
        });
    }

    var aoAbrirPopupPublicacao = function (){
        new Select2Commons().init({
            id: "situacao",
            dropdownParent: '#modalPrincipal',
        });
        $('#formPublicacao').validate({
            rules: {
                situacao: {
                    required: true
                }
            },
            messages: {
                situacao: {
                    required: 'Informe a descrição'
                }
            },
            errorClass: "text-danger",
            submitHandler: function (form) {
                var formData = $(form).serializeArray();
                    console.log(formData)
            }
        })
    }

    return {
        init: function () {
            init();
        },
        abrirEdicao: function (id) {
            abrirEdicao(id);
        },
        excluir: function (id) {
            excluir(id);
        },
        visualizar: function (id) {
            visualizar(id);
        },
        publicar: function (id) {
            publicar(id);
        },
        esconderTelaPrincipal: function () {
            $('#area-datatable').hide();
        },
        voltarTelaPrincipal: function () {
            $('#area-datatable').show();
            $('#area-edicao').html('');
            $('#area-visualizacao').html('');
        },
    }
}()