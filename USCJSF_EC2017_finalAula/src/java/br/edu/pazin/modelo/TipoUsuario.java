/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pazin.modelo;

import br.edu.pazin.dao.HibernateUtil;
import br.edu.pazin.dao.TipoUsuarioDAO;
import br.edu.pazin.dao.UsuarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author apazi
 */
@Entity
public class TipoUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 30, nullable = false)
    private String papel;
    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private List<UsuarioPerfil> listaUsuarios;

    public TipoUsuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public List<UsuarioPerfil> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioPerfil> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoUsuario other = (TipoUsuario) obj;
        return Objects.equals(this.id, other.id);
    }

    public static void main(String[] args) {
        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        List<TipoUsuario> lista = new ArrayList<>();
        HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

        lista.add(dao.getPorId(1));
        lista.add(dao.getPorId(2));

        Usuario u = new Usuario();
        u.setNome("Ze ruela");
        u.setEmail("ruela@email.com");
        u.setSenha("098");
        u.setStatusAtivo(true);
        u.setPerfis(new ArrayList<>());
        for (TipoUsuario tp : lista) {
            UsuarioPerfil perfil = new UsuarioPerfil();
            perfil.setId(0);
            perfil.setUsuario(u);
            perfil.setTipo(tp);
            u.getPerfis().add(perfil);
        }
        UsuarioDAO daoU = new UsuarioDAO();
        daoU.salvar(u);
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//        List<TipoUsuario> lista = new ArrayList<>();
//        lista.add(tp);
//     //   u.setPerfis(lista);
//        UsuarioDAO daoU = new UsuarioDAO();
//        daoU.setSessao(HibernateUtil.getSessionFactory().getCurrentSession());
//        daoU.getSessao().beginTransaction();
//        daoU.inserir(u);
//        daoU.getSessao().getTransaction().commit();
        System.exit(0);
    }

}
