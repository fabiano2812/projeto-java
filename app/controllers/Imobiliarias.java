package controllers;

import beans.FiltroImobiliariaBean;
import beans.ImobiliariaBean;
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
import utils.StringUtils;

import java.util.List;

@SuppressWarnings("deprecation")
@Security.Authenticated(AppSecurity.class)
public class Imobiliarias extends Controller {

    public Result inicio() {
        return ok(views.html.imobiliaria.crud.render());
    }

    @Transactional
    public Result filtrar() {
        try {
            FiltroImobiliariaBean filtro = Form.form(FiltroImobiliariaBean.class).bindFromRequest().get();
            SearchResult<ImobiliariaBean> result = Imobiliaria.buscarPor(filtro);
            return ok(Json.toJson(result));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result listagem(){
        try {
            List<Imobiliaria> imobiliarias = Imobiliaria.buscarTodos();
            return ok(views.html.imobiliaria.listagem.render(imobiliarias));
        }catch (Exception e){
            return badRequest();
        }
    }
    @Transactional
    public Result visulizarListagem() {
        try{
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");
            Long id = Long.valueOf(strId);
            Imobiliaria imobiliaria = Imobiliaria.buscarPorId(id);
            return ok(views.html.imobiliaria.visualizarListagem.render(imobiliaria));
        }catch (Exception e){
            return badRequest();
        }
    }

    @Transactional
    public Result editar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");

            Imobiliaria imobiliaria = new Imobiliaria();

            if (StringUtils.isNotEmpthOrNull(strId)) {
                Long id = Long.valueOf(strId);
                imobiliaria = Imobiliaria.buscarPorId(id);
            }

            return ok(views.html.imobiliaria.cadastro.render(imobiliaria));
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
            String endereco = dynamicForm.get("endereco");

            Imobiliaria imobiliaria = new Imobiliaria();

            Long id = null;

            if (StringUtils.isNotEmpthOrNull(strId)){
                id = Long.valueOf(strId);
            }

            imobiliaria.nome = nome;
            imobiliaria.endereco = endereco;
            imobiliaria.id = id;
            imobiliaria = (Imobiliaria) imobiliaria.alterar();

            return ok(Json.toJson(imobiliaria));
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
            Imobiliaria imobiliaria = Imobiliaria.buscarPorId(id);
            imobiliaria.excluir();
            return ok();
        } catch (Exception e){
            return badRequest();
        }
    }
}
