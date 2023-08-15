var Plano = function () {

    const URL_FILTRAR = '/planos/filtrar';
    const URL_EDITAR = '/plano/editar';
    const URL_SALVAR = '/plano/salvar';
    const URL_EXCLUIR = '/plano/excluir';

    var $dataTable;

    var init = function (){
        $dataTable = $('#dataTable').DataTable({
            responsive: true,
            dom: 't<"dt-panelfooter clearfix"<"row m-0"<"col-md-6 p-0" i><"col-md-6 p-0 dataTables_pager" p>>>',
            pageLength: 20,
            "processing": true,
            "serverSide": true,
            "autoWidth": false,
            "ajax": {
                "url": URL_FILTRAR,
                "type": "POST",
                "data": function (d) {
                    var $searchForm = $('#formPesquisa');
                    var formData = $searchForm.serializeArray();

                    for (var i = 0; i < formData.length; i++) {
                        d[formData[i].name] = formData[i].value;
                    }

                    if (d.order !== undefined) {
                        for (var j = 0; j < d.order.length; j++) {
                            d["orderBy[" + j + "].column"] = d.order[j].column;
                            d["orderBy[" + j + "].dir"] = d.order[j].dir;
                        }
                    }

                    delete (d.columns);
                    delete (d.search);
                    delete (d.order);
                }
            },
            "columns": [
                {
                    data: 'id'
                },
                {
                    data: 'nome',
                },
                {
                    data:'descricao'
                },
                {
                    data: 'id',
                }
            ],
            "columnDefs": [
                {
                    targets: -1,
                    orderable: false,
                    render: function (data, type, full, meta) {
                        var acoesHtml = '';
                        acoesHtml += '<button class="btn btn-sm btn-primary me-3" title="Editar"onclick="Plano.abrirEdicao(' + full.id + ')">';
                        acoesHtml += 'Editar';
                        acoesHtml += '</button>';
                        acoesHtml += '<button class="btn btn-sm btn-danger me-3" title="Excluir"onclick="Plano.excluir(' + full.id + ')">';
                        acoesHtml += 'Excluir';
                        acoesHtml += '</button>';
                        return acoesHtml;
                    }
                }
            ],
            "ordering": true,
            "sorting": []
        });

    }

    var iniciarValidaocaoFormulario = function (){
        $('#formProduto').validate({
            rules: {
                nome: {
                    required: true
                },
                descricao: {
                    required: true
                },
                preco: {
                    required: true
                },
                dataCadastro: {
                    required: true
                }
            },
            messages: {
                nome: {
                    required: 'Informe o seu nome'
                },
                descricao: {
                    required: 'Informe a descrição'
                },
                preco: {
                    required: 'Informe o preço'
                },
                dataCadastro:{
                    required: 'Informe a data do cadastro'
                }
            },
            errorClass: "text-danger",
            submitHandler: function (form) {
                var formData = $(form).serializeArray();

                $.ajax({
                    method: 'POST',
                    url: URL_SALVAR,
                    data: formData,
                    success: function () {
                        swal("Muito bom!", "Plano salvo com!", "success");
                    },
                    error: function (jqXHR, exception) {
                        console.log("Ocorreu um erro no servidor");
                    }
                });
            }
        });
    }

    var abrirEdicao = function (id){
        $.ajax({
            method: 'POST',
            url: URL_EDITAR,
            data: {
                id: id
            },
            success: function (htmlCadastro){
                Plano.esconderTelaPrincipal();
                $('#area-edicao').html(htmlCadastro)
                iniciarComponentesEdicao();
            }
        });
    }
    var iniciarComponentesEdicao = function (){
        iniciarValidaocaoFormulario();
    }

    var excluir = function (id){
        Dialog.askConfirmation({
            title: 'Tem certeza?',
            text: "Você não poderá reverter isso!",
            afterConfirm: function (){
                $.ajax({
                    method: 'POST',
                    url: URL_EXCLUIR,
                    data: {
                        id: id
                    },
                    success: function () {
                        $('.linha-'+id).remove();
                        Dialog.openSuccessDialog({
                            title:'Excluído!',
                            text:'Seu arquivo foi excluído.'
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

    return {
        init: function (){
            init();
        },
        abrirEdicao:function (id){
            abrirEdicao(id);
        },
        excluir: function (id){
            excluir(id);
        },
        esconderTelaPrincipal: function (){
            $('#area-datatable').hide();
        },
        voltarTelaPrincipal:function (){
            $('#area-datatable').show();
            $('#area-edicao').html('');
        }
    }
}()