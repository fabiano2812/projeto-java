package controllers;

import beans.FiltroUsuarioBean;
import beans.UsuarioBean;
import models.Imobiliaria;
import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import search.SearchResult;
import security.AppSecurity;
import utils.StringUtils;

@Security.Authenticated(AppSecurity.class)
public class Usuarios extends Controller {

    public Result inicio() {
        return ok(views.html.usuario.crud.render());
    }


    @Transactional
    public Result filtrar() {
        try {
            FiltroUsuarioBean filtro = Form.form(FiltroUsuarioBean.class).bindFromRequest().get();
            SearchResult<UsuarioBean> result = Usuario.buscarPor(filtro);
            return ok(Json.toJson(result));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result editar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");

            Usuario usuario = new Usuario();

            if (StringUtils.isNotEmpthOrNull(strId)) {
                Long id = Long.valueOf(strId);
                usuario = Usuario.buscarPorId(id);
            }

            return ok(views.html.usuario.cadastro.render(usuario));
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
            String senha = dynamicForm.get("senha");
            String email = dynamicForm.get("email");
            String strImobiliariaId = dynamicForm.get("imobiliaria");
            String strSobre = dynamicForm.get("sobre");
            String strTrabalho = dynamicForm.get("trabalho");
            String strEmpresa = dynamicForm.get("empresa");
            String strPais = dynamicForm.get("pais");
            String strEndereco = dynamicForm.get("endereco");

            Usuario usuario = new Usuario();

            Long id = null;

            if (StringUtils.isNotEmpthOrNull(strId)){
                id = Long.valueOf(strId);
            }

            usuario.id = id;
            usuario.nome = nome;
            usuario.email = email;
            usuario.senha = senha;
            usuario.sobre = strSobre;
            usuario.trabalho = strTrabalho;
            usuario.empresa = strEmpresa;
            usuario.pais = strPais;
            usuario.endereco = strEndereco;

            if (StringUtils.isNotEmpthOrNull(strImobiliariaId)){
                Long imobiliariaId = Long.valueOf(strImobiliariaId);
                Imobiliaria imobiliaria = Imobiliaria.buscarPorId(imobiliariaId);
                usuario.imobiliaria = imobiliaria;
            }
            usuario = (Usuario) usuario.alterar();

            return ok(Json.toJson(usuario));
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

            Usuario usuario = Usuario.buscarPorId(id);
            usuario.excluir();
            return ok();
        } catch (Exception e){
            return badRequest();
        }
    }
}