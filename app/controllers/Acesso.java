package controllers;

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
        return ok(views.html.acesso.login.render());
    }
    @Transactional
    public Result acessoLoguin(){
      try {
          DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
          String strEmail = dynamicForm.get("email");
          String strSenha = dynamicForm.get("senha");

          Usuario usuario = Usuario.buscarUsuarioPorEmailSenha(strEmail, strSenha);

          if(usuario != null){
              session().put(AppSecurity.USUARIO_LOGADO, usuario.getId().toString());
              return ok(Json.toJson(usuario));
          }else {
              return ok();
          }

      } catch (Exception e){
            return badRequest();
        }

    }
}
