package controllers;

import beans.EstadoBean;
import beans.FiltroEstadoBean;
import beans.FiltroPessoaBean;
import beans.search.SearchResult;
import models.Estado;
import models.Pessoa;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Security.Authenticated(AppSecurity.class)
public class Estados extends Controller {

    private static Form<FiltroEstadoBean> formFiltro = Form.form(FiltroEstadoBean.class);

    public Result inicio() {
        return ok(views.html.estado.crud.render());
    }

    @Transactional
    public Result editar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");

            Estado estado = new Estado();

            if (StringUtils.isNotEmpthOrNull(strId)) {
                Long id = Long.valueOf(strId);
                estado = Estado.buscarPorId(id);
            }

            return ok(views.html.estado.cadastro.render(estado));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result filtrar(){
        try {
            Form<FiltroEstadoBean> form = formFiltro.bindFromRequest();
            FiltroEstadoBean filtro = form.get();
            SearchResult searchResult = Estado.buscarPor(filtro);
            return ok(Json.toJson(searchResult));
        }catch (Exception e){
            return badRequest();
        }
    }

    @Transactional
    public Result salvar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");
            String nome = dynamicForm.get("nome");
            String sigla = dynamicForm.get("sigla");

            Long id = null;
            if (StringUtils.isNotEmpthOrNull(strId)){
                id = Long.valueOf(strId);
            }
            Estado estado = new Estado();
            estado.setId(id);
            estado.setNome(nome);
            estado.setSigla(sigla);

            estado = (Estado) estado.alterar();

            return ok(Json.toJson(estado));
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
            Estado estado = Estado.buscarPorId(id);
            estado.excluir();
            return ok();
        } catch (Exception e){
            return badRequest();
        }
    }

    @Transactional
    public Result buscarEstados(String query) {
        try {
            List<Estado> estados = Estado.buscarEstadosPor(query);
            return ok(Json.toJson(estados));
        } catch (Exception e) {
            return badRequest();
        }
    }
}

