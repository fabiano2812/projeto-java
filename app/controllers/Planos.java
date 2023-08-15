package controllers;

import beans.FiltroPlanoBean;
import beans.PlanoBean;
import models.Compra;
import models.Imobiliaria;
import models.Plano;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import beans.search.SearchResult;
import security.AppSecurity;
import utils.DateUtil;
import utils.MoedaUtil;
import utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Security.Authenticated(AppSecurity.class)
public class Planos extends Controller {
    public Result inicio() {
        return ok(views.html.plano.crud.render());
    }

    @Transactional
    public Result filtrar() {
        try {
            FiltroPlanoBean filtro = Form.form(FiltroPlanoBean.class).bindFromRequest().get();
            SearchResult<PlanoBean> result = Plano.buscarPor(filtro);
            return ok(Json.toJson(result));
        } catch (Exception e) {
            return badRequest();
        }
    }
    @Transactional
    public Result listagem() {
        try{
            List<Plano> planos = Plano.buscarTodas();
            return ok(views.html.plano.listagem.render(planos));
        }catch (Exception e){
            return badRequest();
        }
    }

    @Transactional
    public Result editar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");

            Plano plano = new Plano();

            if (StringUtils.isNotEmpthOrNull(strId)) {
                Long id = Long.valueOf(strId);
                plano = Plano.buscarPorId(id);
            }

            return ok(views.html.plano.cadastro.render(plano));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result salvar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();

            String strId = dynamicForm.get("id");
            String nome = dynamicForm.get("nome");
            String descricao = dynamicForm.get("descricao");
            String strpreco = dynamicForm.get("preco");
            String strDataCadastro = dynamicForm.get("dataCadastro");

            Date dataCadastro = DateUtil.converteData(strDataCadastro);
            BigDecimal preco = MoedaUtil.converterBigDecimal(strpreco);

            Long id = null;

            if (StringUtils.isNotEmpthOrNull(strId)){
                id = Long.valueOf(strId);
            }

            Plano plano = new Plano();
            plano.id = id;
            plano.nome = nome;
            plano.descricao = descricao;
            plano.preco = preco;
            plano.dataCadastro = dataCadastro;
            plano = (Plano) plano.alterar();

            return ok(Json.toJson(plano));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result excluir(){
        try{
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");
            Long id = Long.valueOf(strId);
            Plano plano = Plano.buscarPorId(id);
            plano.excluir();
            return ok();
        } catch (Exception e){
            return badRequest();
        }
    }
    @Transactional
    public Result planoAbrirPopup() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strid = dynamicForm.get("id");
            Long id = Long.valueOf(strid);
            Plano plano = Plano.buscarPorId(id);
            return ok(views.html.plano.popup.render(plano.getId()));
        } catch (Exception e) {
            return badRequest();
        }
    }
    @Transactional
    public  Result salvarNovaCompra() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strNome = dynamicForm.get("nome");
            String strEmail = dynamicForm.get("email");
            String strEndereco = dynamicForm.get("endereco");
            String strPlanoId = dynamicForm.get("planoId");
            String dataInicio = dynamicForm.get("dataInicio");
            String dataFim = dynamicForm.get("dataFim");

            Long id = Long.valueOf(strPlanoId);
            Plano plano = Plano.buscarPorId(id);

            Imobiliaria imobiliaria = new Imobiliaria();
            imobiliaria.nome = strNome;
            imobiliaria.email = strEmail;
            imobiliaria.endereco = strEndereco;
            imobiliaria = (Imobiliaria) imobiliaria.alterar();

            Compra compra = new Compra();
            compra.plano = plano;
            compra.imobiliaria = imobiliaria;
            compra.dataInicio = DateUtil.converteData(dataInicio);
            compra.dataFim = DateUtil.converteData(dataFim);
            compra = (Compra) compra.alterar();


            return ok(Json.toJson(compra));
        } catch (Exception e) {
            return badRequest();
        }
    }

}

