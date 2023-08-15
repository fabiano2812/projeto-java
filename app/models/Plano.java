package models;

import beans.FiltroPlanoBean;
import beans.PlanoBean;
import beans.search.SearchResult;
import play.db.jpa.JPA;
import utils.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Plano extends BaseEntidade{

    @Id
    @SequenceGenerator(name = "seq_plano", sequenceName = "seq_plano", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_plano")
    public Long id;

    public String nome;

    public String descricao;

    public BigDecimal preco;

    @Temporal(TemporalType.TIMESTAMP)
    public Date dataCadastro;

    public Plano() {
    }
    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public static SearchResult<PlanoBean> buscarPor(FiltroPlanoBean filtro) {
        String select = "SELECT new beans.PlanoBean(" +
                "p.id, " +
                "p.nome," +
                "p.descricao) " +
                "FROM Plano p";
        StringBuffer where = new StringBuffer(" WHERE 1=1 ");

        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            where.append(" AND UPPER(p.nome) LIKE :nome ");
        }

        if (StringUtils.isNotEmpthOrNull(filtro.descricao)){
            where.append(" AND UPPER(p.descricao) LIKE :descricao ");
        }

        if(filtro.id != null){
            where.append(  " AND p.id = :id ");
        }

        Query query = JPA.em().createQuery(select + where + " ORDER BY p.id DESC ");

        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            query.setParameter("nome","%"+ filtro.nome.toUpperCase()+"%");
        }
        if (StringUtils.isNotEmpthOrNull(filtro.descricao)){
            query.setParameter("descricao","%"+ filtro.descricao.toUpperCase()+"%");
        }

        if (filtro.id != null){
            query.setParameter("id", filtro.id);
        }
        query.setFirstResult(filtro.start.intValue());
        query.setMaxResults(filtro.length.intValue());

        List<PlanoBean> produtos = query.getResultList();

        String count = "SELECT COUNT(p) FROM Plano p";

        query = JPA.em().createQuery(count + where);

        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            query.setParameter("nome","%"+ filtro.nome.toUpperCase()+"%");
        }
        if (StringUtils.isNotEmpthOrNull(filtro.descricao)){
            query.setParameter("descricao","%"+ filtro.descricao.toUpperCase()+"%");
        }

        if (filtro.id != null){
            query.setParameter("id", filtro.id);
        }

        Long total = (Long) query.getSingleResult();

        return new SearchResult<PlanoBean>(filtro.draw, produtos, total, filtro);
    }

    public static Plano buscarPorId(Long id){
        Query query = JPA.em().createQuery("SELECT p FROM Plano p WHERE p.id = :id ");
        query.setParameter("id", id);
        return (Plano) query.getSingleResult();
    }

    public static List<Plano> buscarTodosAtivos(){
        Query query = JPA.em().createQuery("SELECT p FROM Plano p");
        return query.getResultList();
    }
    public static List<Plano> buscarTodas(){
        Query query = JPA.em().createQuery(" SELECT p FROM Plano p ");
        return query.getResultList();
    }
}

