package br.edu.pazin.dao;

import br.edu.pazin.modelo.TipoUsuario;
import br.edu.pazin.modelo.Usuario;
import br.edu.pazin.modelo.UsuarioPerfil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Anderson Pazin
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    public UsuarioDAO() {

    }

    public Usuario buscarUsuario(String email) {
        try {
            Criteria c = getSessao().createCriteria(Usuario.class);
            Criterion filtro = Restrictions.eq("email", email);
            c.add(filtro);
            return (Usuario) c.uniqueResult();
        } catch (Exception e) {
            System.out.println("UsuarioDAO.buscarUsuario()");
            return null;
        }
    }

    public List<Usuario> getTodosUsuarios() {
        try {
            Criteria c = getSessao().createCriteria(Usuario.class);
            c.addOrder(Order.asc("nome"));
            return c.list();
        } catch (Exception ex) {
            System.out.println("UsuarioDAO.getTodosUsuarios()");
            return null;
        }
    }

    public Usuario getPorId(Integer id) {
        try {
            return (Usuario) getSessao().get(Usuario.class, id);
        } catch (Exception e) {
            System.out.println("UsuarioDAO.getPorId()");
            return null;
        }
    }

    public String salvar(Usuario usuario) {
        String msg = "";
        try {
            if (getPorId(usuario.getId()) == null) {
                this.inserir(usuario);
                msg = "Usuário inserido com sucesso!";
            } else {
                this.alterar(usuario);
                msg = "Usuário atualizado com sucesso!";
            }
            return msg;
        } catch (Exception ex) {
            System.out.println("UsuarioDAO.salvar()");
            return "Problemas ao salvar o usuário: " + ex.getMessage();
        }
    }

    public List<TipoUsuario> listarTiposPerfisUsuario(Integer id) {
        try {
            Criteria c = getSessao().createCriteria(UsuarioPerfil.class);
            Criterion filtro = Restrictions.eq("usuario.id", id);
            c.add(filtro);
            List<TipoUsuario> listaTP = new ArrayList<TipoUsuario>();
            List<UsuarioPerfil> listaPerfil = c.list();

            for (UsuarioPerfil objeto : listaPerfil) {
                listaTP.add(objeto.getTipo());
            }
            return listaTP;
        } catch (Exception e) {
            System.out.println("listarPerfilUsuario: " + e.getMessage());
            return null;
        }
    }

    public List<UsuarioPerfil> listarPerfisUsuario(Integer id) {
        try {
            Criteria c = getSessao().createCriteria(UsuarioPerfil.class);
            Criterion filtro = Restrictions.eq("usuario.id", id);
            c.add(filtro);
            return c.list();
        } catch (Exception e) {
            System.out.println("listaPerfisUsuario: " + e.getMessage());
            return null;
        }
    }
    
    public void excluirPerfil(UsuarioPerfil obj) {
        try{
            this.getSessao().delete(obj);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
