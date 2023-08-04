package controllers;

import models.Imobiliaria;
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

    public Result inicio() {
        Usuario usuario = Application.obterUsuarioLogado();
        return ok(views.html.perfil.perfil.render(usuario));
    }

    @Transactional
    public Result salvarSenha(){
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String strId = dynamicForm.get("id");
            String senha2 = dynamicForm.get("renewpassword");

            Long id = null;

            if (StringUtils.isNotEmpthOrNull(strId)){
                id = Long.valueOf(strId);
            }

            Usuario usuario = Usuario.buscarPorId(id);
            usuario.senha = senha2;

            usuario = (Usuario) usuario.alterar();

            return ok(Json.toJson(usuario));
        } catch (Exception e){
            return badRequest();
        }
    }

    @Transactional
    public Result salvar() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();

            String strId = dynamicForm.get("id");
            String nome = dynamicForm.get("fullName");
            String email = dynamicForm.get("fullEmail");
            String strSobre = dynamicForm.get("fullSobre");
            String strImobiliaria = dynamicForm.get("fullimobiliaria");
            String senha = dynamicForm.get("fullSenha");
            String strTrabalho = dynamicForm.get("fullTrabalho");
            String strEmpresa = dynamicForm.get("fullEmpresa");
            String strPais = dynamicForm.get("fullPais");
            String strEndereco = dynamicForm.get("fullEndereco");

            Long id = null;
            if (StringUtils.isNotEmpthOrNull(strId)) {
                id = Long.valueOf(strId);
            }

            Usuario usuario = new Usuario();
            usuario.nome = nome;
            usuario.email = email;
            usuario.id = id;
            usuario.senha = senha;
            usuario.sobre = strSobre;
            usuario.trabalho = strTrabalho;
            usuario.empresa = strEmpresa;
            usuario.pais = strPais;
            usuario.endereco = strEndereco;

            if (StringUtils.isNotEmpthOrNull(strImobiliaria)){
                Long imobiliariaId = Long.valueOf(strImobiliaria);
                Imobiliaria imobiliaria = Imobiliaria.buscarPorId(imobiliariaId);
                usuario.imobiliaria = imobiliaria;
            }


            usuario = (Usuario) usuario.alterar();

            return ok(Json.toJson(usuario));
        } catch (Exception e) {
            return badRequest();
        }
    }
}
