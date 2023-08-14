package models;

import beans.EstadoBean;
import beans.FiltroEstadoBean;
import beans.FiltroPessoaBean;
import beans.PessoaBean;
import beans.search.SearchResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import play.db.jpa.JPA;
import utils.StringUtils;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
@Entity
@Table(name = "estado")
public class Estado extends BaseEntidade {

    @Id
    @SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estado")
    private Long id;

    @NotEmpty
    @Column(length = 100)
    private String nome;

    @Column(name = "nome_fonetico", length = 200)
    private String nomeFonetico;

    @NotEmpty
    @Column(length = 20)
    private String sigla;


    public static Estado buscarPor(String sigla) {
        try {
            TypedQuery<Estado> typedQuery = JPA.em().createQuery("SELECT e FROM Estado e WHERE ( UPPER(e.sigla) = :sigla OR UPPER(e.nome)= :sigla ) ", Estado.class);
            typedQuery.setParameter("sigla", sigla.toUpperCase().trim());
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static List<Estado> buscarTodos() {
        Query query = JPA.em().createQuery("SELECT e FROM Estado e");
        return query.getResultList();
    }

    public static Estado buscarPorId(Long id) {
        Query query = JPA.em().createQuery("SELECT e FROM Estado e WHERE e.id = :id ");
        query.setParameter("id", id);
        return (Estado) query.getSingleResult();
    }

    public static List<Estado> buscarTodosAtivos() {
        Query query = JPA.em().createQuery("SELECT e FROM Estado e");
        return query.getResultList();
    }

    public static List<Estado> buscarEstadosPor(String nome) {
        Query query = JPA.em().createQuery("SELECT e FROM Estado e WHERE ( UPPER(e.nome) LIKE :nome OR UPPER(e.sigla) LIKE :nome ) ");
        query.setParameter("nome", "%" + nome.toUpperCase().trim() + "%");
        return query.getResultList();
    }

    public static SearchResult<EstadoBean> buscarPor(FiltroEstadoBean filtro) {
        String select = "SELECT new beans.EstadoBean(e.id, e.nome, e.sigla) FROM Estado e ";

        StringBuffer where = new StringBuffer(" WHERE 1=1 ");

        if (StringUtils.isNotEmpthOrNull(filtro.nome)) {
            where.append(" AND UPPER(e.nome) LIKE :nome ");
        }
        if (StringUtils.isNotEmpthOrNull(filtro.sigla)) {
            where.append(" AND UPPER(e.nome) LIKE :sigla ");
        }

        Query query = JPA.em().createQuery(select + where + " ORDER BY e.id ");
        query.setFirstResult(filtro.start.intValue());
        query.setMaxResults(filtro.length.intValue());

        if (StringUtils.isNotEmpthOrNull(filtro.nome)) {
            query.setParameter("nome", "%" + filtro.nome.trim().toUpperCase() + "%");
        }
        if (StringUtils.isNotEmpthOrNull(filtro.sigla)) {
            query.setParameter("sigla", "%" + filtro.sigla.trim().toUpperCase() + "%");
        }

        List<EstadoBean> estadoBeans = query.getResultList();

        select = "SELECT COUNT (e.id) FROM Estado e ";

        query = JPA.em().createQuery(select + where);

        if (StringUtils.isNotEmpthOrNull(filtro.nome)) {
            query.setParameter("nome", "%" + filtro.nome.trim().toUpperCase() + "%");
        }
        if (StringUtils.isNotEmpthOrNull(filtro.sigla)) {
            query.setParameter("nome", "%" + filtro.sigla.trim().toUpperCase() + "%");
        }

        Long totalRegistros = (Long) query.getSingleResult();

        return new SearchResult<>(filtro.draw, estadoBeans, totalRegistros, filtro);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFonetico() {
        return nomeFonetico;
    }

    public void setNomeFonetico(String nomeFonetico) {
        this.nomeFonetico = nomeFonetico;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
