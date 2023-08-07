package controllers;

import beans.UsuarioBean;
import models.Usuario;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;
import utils.StringUtils;


@Security.Authenticated(AppSecurity.class)

public class Perfil extends Controller {

    @Transactional
    public Result salvarAlteracaoSenha() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");
            String senha2 = dynamicForm.get("renewpassword");

            Long id = Long.valueOf(strId);
            Usuario usuario = Usuario.buscarPorId(id);
            usuario.setSenha(senha2);

            usuario = (Usuario) usuario.alterar();
            UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.setEmail(usuario.getEmail());
            usuarioBean.setNome(usuario.getNome());

            return ok(Json.toJson(usuarioBean));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result salvarAlteracaoEmail() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();

            String strId = dynamicForm.get("id");
            String nome = dynamicForm.get("fullName");
            String email = dynamicForm.get("fullEmail");

            Long id = Long.valueOf(strId);
            Usuario usuario = Usuario.buscarPorId(id);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setId(id);
            usuario = (Usuario) usuario.alterar();

            UsuarioBean usuarioBean = new UsuarioBean();
            usuarioBean.setEmail(usuario.getEmail());
            usuarioBean.setNome(usuario.getNome());

            return ok(Json.toJson(usuarioBean));
        } catch (Exception e) {
            return badRequest();
        }
    }
}
