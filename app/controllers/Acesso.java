package controllers;

import beans.UsuarioBean;
import models.Usuario;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import security.AppSecurity;

import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@SuppressWarnings("deprecation")
public class Acesso extends Controller {

    public Result login() {
        session().clear();
        return ok(views.html.login.login_Usuario.render());
    }

    @Transactional
    public Result acessoLogin() {
        DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
        String strEmail = dynamicForm.get("email");
        String strSenha = dynamicForm.get("senha");

        Usuario usuario = Usuario.buscarUsuarioPorEmailSenha(strEmail, strSenha);

        if (usuario != null) {
            String id = usuario.getId().toString();
            session().put(AppSecurity.USUARIO_LOGADO, id);
            UsuarioBean usuarioBean = new UsuarioBean();

            Long strID = Long.valueOf(id);
            usuarioBean.setEmail(usuario.getEmail());
            usuarioBean.setId(strID);
            usuarioBean.setNome(usuario.getNome());
            return ok(Json.toJson(usuarioBean));
        } else {
            return ok();
        }
    }

    public static String hashSenha(String senha, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedBytes = md.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }



    private boolean verificarSenha(String senhaFornecida, String hashedSenhaArmazenada, byte[] salt) {
        String hashedSenhaFornecida = hashSenha(senhaFornecida, salt);
        boolean usuarioEncontrado = hashedSenhaFornecida.equals(hashedSenhaArmazenada);
        return usuarioEncontrado;
    }

}
