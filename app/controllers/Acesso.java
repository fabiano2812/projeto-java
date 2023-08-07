package controllers;

import beans.UsuarioBean;
import models.Usuario;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import security.AppSecurity;

@SuppressWarnings("deprecation")
public class Acesso extends Controller {

    public Result login() {
        session().clear();
        return ok(views.html.login.login_Usuario.render());
    }
    @Transactional
    public Result acessoLoguin(){
          DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
          String strEmail = dynamicForm.get("email");
          String strSenha = dynamicForm.get("senha");

          Usuario usuario = Usuario.buscarUsuarioPorEmailSenha(strEmail, strSenha);

          if(usuario != null){
              String id = usuario.getId().toString();
              session().put(AppSecurity.USUARIO_LOGADO, id);
              UsuarioBean usuarioBean = new UsuarioBean();

              Long strID = Long.valueOf(id);
              usuarioBean.setEmail(usuario.getEmail());
              usuarioBean.setId(strID);
              usuarioBean.setNome(usuario.getNome());
              return ok(Json.toJson(usuarioBean));
          }else {
              return ok();
          }
    }
}
