package controllers;

import beans.CompraBean;
import beans.FiltroCompraBean;
import beans.FiltroEstadoBean;
import models.Compra;
import models.Estado;
import models.Imobiliaria;
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
import utils.StringUtils;

@Security.Authenticated(AppSecurity.class)
public class Compras extends Controller {

    private static Form<FiltroCompraBean> formFiltro = Form.form(FiltroCompraBean.class);

    public Result inicio() {
        return ok(views.html.compra.crud.render());
    }

    @Transactional
    public Result filtrar() {
        try {
            Form<FiltroCompraBean> form = formFiltro.bindFromRequest();
            FiltroCompraBean filtro = form.get();
            SearchResult searchResult = Compra.buscarPor(filtro);
            return ok(Json.toJson(searchResult));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result editar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");

            Compra compra = new Compra();

            if (StringUtils.isNotEmpthOrNull(strId)) {
                Long id = Long.valueOf(strId);
                compra = Compra.buscarPorId(id);
            }

            return ok(views.html.compra.cadastro.render(compra));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result salvar() {
        try {

            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();

            String strId = dynamicForm.get("id");
            String dataInicio = dynamicForm.get("dataInicio");
            String dataFim = dynamicForm.get("dataFim");
            String strPlanoId = dynamicForm.get("plano");
            String strImobiliariaId = dynamicForm.get("imobiliaria");

            Long id = null;
            if (StringUtils.isNotEmpthOrNull(strId)){
                id = Long.valueOf(strId);
            }

            Compra compra = new Compra();
            compra.id = id;
            compra.dataInicio = DateUtil.converteData(dataInicio);
            compra.dataFim = DateUtil.converteData(dataFim);

            if (StringUtils.isNotEmpthOrNull(strImobiliariaId)){
                Long imobiliariaId = Long.valueOf(strImobiliariaId);
                Imobiliaria imobiliaria = Imobiliaria.buscarPorId(imobiliariaId);
                compra.imobiliaria = imobiliaria;
            }
            compra = (Compra) compra.alterar();

            return ok(Json.toJson(compra));
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
            Compra compra = Compra.buscarPorId(id);
            compra.excluir();
            return ok();
        } catch (Exception e){
            return badRequest();
        }
    }
}

