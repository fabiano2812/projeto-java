package beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.Imobiliaria;
import beans.search.FiltroBaseBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltroImobiliariaBean extends FiltroBaseBean<Imobiliaria> {
    public Long id;
    public String nome;
    public String endereco;

    public FiltroImobiliariaBean(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public FiltroImobiliariaBean() {
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
