package controllers;

import models.Usuario;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import security.AppSecurity;
import play.mvc.Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Usuarios extends Controller {

    public Result novo() {
        return ok(views.html.cadastro.usuario.render());
    }


    /*@Transactional
    public Result filtrar() {
        try {
            FiltroUsuarioBean filtro = Form.form(FiltroUsuarioBean.class).bindFromRequest().get();
            SearchResult<UsuarioBean> result = Usuario.buscarPor(filtro);
            return ok(Json.toJson(result));
        } catch (Exception e) {
            return badRequest();
        }
    }*/

   /* @Transactional
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
    }*/

    public Result perfilUsuario() throws Throwable {
        Usuario usuario = Application.obterUsuarioLogado();
        return ok(views.html.perfil.usuario.render(usuario));
    }


    @Transactional
    public Result salvar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String nome = dynamicForm.get("nome");
            String senha = dynamicForm.get("senha");
            String email = dynamicForm.get("email");

            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            String hashedSenha = hashSenha(senha, salt);

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(hashedSenha);
            usuario.salt = salt;
            usuario.salvar();

            return ok(Json.toJson(usuario));
        } catch (Exception e) {
            return badRequest();
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

   /* @Transactional
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
    }*/
}