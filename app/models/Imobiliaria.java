package models;

import beans.FiltroImobiliariaBean;
import beans.ImobiliariaBean;
import play.db.jpa.JPA;
import beans.search.SearchResult;
import utils.StringUtils;

import javax.persistence.*;
import java.util.List;

@Entity
public class Imobiliaria extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_imobiliaria", sequenceName = "seq_imobiliaria", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imobiliaria")
    public Long id;

    public String nome;
    public String email;

    public String endereco;

    public Boolean ativo;

    public Imobiliaria(Long id, String nome) {
        this.id = id;
        this.nome = nome;

    }
    public static List<Imobiliaria> buscarTodosAtivos(){
        Query query = JPA.em().createQuery("SELECT i FROM Imobiliaria i");
        return query.getResultList();
    }

    public static Imobiliaria buscarPorId(Long id) {
        Query query = JPA.em().createQuery("SELECT i FROM Imobiliaria i WHERE i.id = :id ");
        query.setParameter("id", id);
        return (Imobiliaria) query.getSingleResult();
    }
    public static SearchResult<ImobiliariaBean> buscarPor(FiltroImobiliariaBean filtro) {
        String select = "SELECT new beans.ImobiliariaBean(" +
                "i.id," +
                "i.nome," +
                "i.endereco) " +
                "FROM Imobiliaria i";
        //----------------------------------
        StringBuffer where = new StringBuffer(" WHERE 1=1 ");

        if (filtro.id != null) {
            where.append(" AND i.id = :id ");
        }
        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            where.append(" AND UPPER(i.nome) LIKE :nome ");
        }
        if (StringUtils.isNotEmpthOrNull(filtro.endereco)){
            where.append(" AND UPPER(i.endereco) LIKE :endereco ");
        }

        //-----------------------
        Query query = JPA.em().createQuery(select + where + " ORDER BY i.id DESC ");


        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            query.setParameter("nome","%"+ filtro.nome.toUpperCase()+"%");
        }

        if (filtro.id != null) {
            query.setParameter("id", filtro.id);
        }


        if (StringUtils.isNotEmpthOrNull(filtro.endereco)){
            query.setParameter("endereco","%"+ filtro.endereco.toUpperCase()+"%");
        }
        //---------------------------------------------
        query.setFirstResult(filtro.start.intValue());
        query.setMaxResults(filtro.length.intValue());

        List<ImobiliariaBean> imobiliarias = query.getResultList();

        String count = "SELECT COUNT(i) FROM Imobiliaria i";

        query = JPA.em().createQuery(count + where);

        if (filtro.id != null) {
            query.setParameter("id", filtro.id);
        }
        if (StringUtils.isNotEmpthOrNull(filtro.nome)){
            query.setParameter("nome","%"+ filtro.nome.toUpperCase()+"%");
        }
        if (StringUtils.isNotEmpthOrNull(filtro.endereco)){
            query.setParameter("endereco","%"+ filtro.endereco.toUpperCase()+"%");
        }

        Long total = (Long) query.getSingleResult();

        return new SearchResult<ImobiliariaBean>(filtro.draw,imobiliarias, total, filtro);

    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Imobiliaria(String endereco) {
        this.endereco = endereco;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Imobiliaria() {
    }

    public static List<Imobiliaria> buscarTodos(){
        Query query = JPA.em().createQuery(" SELECT i FROM Imobiliaria i ");
        return query.getResultList();
    }
}






