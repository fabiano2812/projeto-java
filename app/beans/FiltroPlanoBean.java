package beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.Plano;
import beans.search.FiltroBaseBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltroPlanoBean extends FiltroBaseBean<Plano> {
    public Long id;
    public String nome;
    public String descricao;

    public FiltroPlanoBean() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
