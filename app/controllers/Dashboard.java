package controllers;

import models.Compra;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;

@Security.Authenticated(AppSecurity.class)
public class Dashboard extends Controller {

    public Result inicio() {
        return ok(views.html.dashboard.render());
    }


    @Transactional
    public Result dashboardBuscarCompras() {
        try {
            Long quantidadeCompras = Compra.buscarQuantidadeCompras();
            return ok(quantidadeCompras.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

   /* @Transactional
    public Result dashboardBuscarAnuncio() {
        try {
            Long buscarQuantidadeAnuncios = Anuncio.buscarQuantidadeAnuncios();
            return ok(buscarQuantidadeAnuncios.toString());
        } catch (Exception e) {
            return badRequest();
        }
    }*/

    /*@Transactional
    public Result despesas() {
        try {
            List<Lancamento> lancamento = Lancamento.buscarTodosDespesas();

            BigDecimal total = BigDecimal.ZERO;

            for (Lancamento lancamentos : lancamento) {
                total = total.add(lancamentos.valor);
            }
            String totalFmt = MoedaUtil.formatarBigDecimal(total, 2);

            return ok(totalFmt);
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }*/

   /* @Transactional
    public Result receitas() {
        try {
            List<Lancamento> lancamento = Lancamento.buscarTodosReceitas();

            BigDecimal total = BigDecimal.ZERO;

            for (Lancamento lancamentos : lancamento) {
                total = total.add(lancamentos.valor);
            }
            String totalFmt = MoedaUtil.formatarBigDecimal(total, 2);

            return ok(totalFmt);
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }*/

  /*  @Transactional
    public Result compromissos() {
        try {
            Long buscarQuantidadeCompromissos = Compromisso.buscarQuantidadeCompromissos();
            return ok(buscarQuantidadeCompromissos.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }*/

   /* @Transactional
    public Result atendimento() {
        try {
            Long buscarQuantidadeAtendimento = Atendimento.buscarQuantidadeAtendimento();
            return ok(buscarQuantidadeAtendimento.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }*/

   /* @Transactional
    public Result eventos() {
        try {
            Long buscarQuantidadeEvento = Evento.buscarQuantidadeEventos();
            return ok(buscarQuantidadeEvento.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }
*/

    /*@Transactional
    public Result eventosHoje() {
        try {
            Date hoje = new Date();
            Long buscarQuantidadeEvento = Evento.buscarQuantidadeEventosPor(hoje);
            return ok(buscarQuantidadeEvento.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }*/

   /* @Transactional
    public Result eventoPessoas() {
        try {
            Date hoje = new Date();
            Long buscarQuantidadePessoa = EventoPessoa.buscarQuantidadeDePessoasEmEventosPor(hoje);
            return ok(buscarQuantidadePessoa.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }*/
}
