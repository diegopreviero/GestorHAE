package br.edu.pazin.controle;

import br.edu.pazin.dao.TipoUsuarioDAO;
import br.edu.pazin.dao.UsuarioDAO;
import br.edu.pazin.modelo.TipoUsuario;
import br.edu.pazin.modelo.Usuario;
import br.edu.pazin.modelo.UsuarioPerfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author apazi
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{

    private Usuario usuario;
    private TipoUsuario tpUsuario;
    private List<TipoUsuario> listaTodosPerfil;
    private List<TipoUsuario> listaPerfilUsuario;
    private List<Usuario> listaTodosUsuarios;
    private String senha1, senha2;
    private FacesContext fc;
    private FacesMessage fm;
    private String msg;
    private int idTab;

    public UsuarioBean() {
        prepararTela();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoUsuario getTpUsuario() {
        return tpUsuario;
    }

    public void setTpUsuario(TipoUsuario tpUsuario) {
        this.tpUsuario = tpUsuario;
    }

    public List<TipoUsuario> getListaTodosPerfil() {
        if (listaTodosPerfil == null) {
            TipoUsuarioDAO dao = new TipoUsuarioDAO();
            listaTodosPerfil = dao.getTodosTipoUsuarios();
        }
        return listaTodosPerfil;
    }

    public void setListaTodosPerfil(List<TipoUsuario> listaTodosPerfil) {
        this.listaTodosPerfil = listaTodosPerfil;
    }

    public List<TipoUsuario> getListaPerfilUsuario() {
        return listaPerfilUsuario;
    }

    public void setListaPerfilUsuario(List<TipoUsuario> listaPerfilUsuario) {
        this.listaPerfilUsuario = listaPerfilUsuario;
    }

    public List<Usuario> getListaTodosUsuarios() {
        if (listaTodosUsuarios == null) {
            UsuarioDAO dao = new UsuarioDAO();
            listaTodosUsuarios = dao.getTodosUsuarios();
        }
        return listaTodosUsuarios;
    }

    public void setListaTodosUsuarios(List<Usuario> listaTodosUsuarios) {
        this.listaTodosUsuarios = listaTodosUsuarios;
    }

    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    private void prepararTela() {
        usuario = new Usuario();
        listaPerfilUsuario = null;
        listaTodosPerfil = null;
        listaTodosUsuarios = null;
        tpUsuario = new TipoUsuario();
        fc = FacesContext.getCurrentInstance();
    }

    public void prepararTipoUsuario() {
        tpUsuario = new TipoUsuario();
    }

    public void salvarTipoUsuario(ActionEvent evento) {
        try {
            TipoUsuarioDAO dao = new TipoUsuarioDAO();
            msg = dao.salvar(tpUsuario);
            listaTodosPerfil = null;
            getListaTodosPerfil();
        } catch (Exception ex) {
            msg = ex.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro", msg);
        }
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getId().equalsIgnoreCase("lista")) {
            idTab = 0;
        } else if (event.getTab().getId().equalsIgnoreCase("cadastro")) {
            idTab = 1;
        }
    }

    public void limparTela() {
        prepararTela();
    }

    public void alterarUsuario(Usuario user) {
        this.usuario = user;
        UsuarioDAO dao = new UsuarioDAO();
        listaPerfilUsuario
                = dao.listarTiposPerfisUsuario(user.getId());
        this.idTab = 1;
    }

    public void excluirUsuario(Usuario user) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.excluir(user);
        listaTodosUsuarios = null;
        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão",
                "Usuario excluido com sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public void salvarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        // verificar atribuição da senha ao usuario
        if ((!senha1.isEmpty())
                && (senha1.equals(senha2))) {
            usuario.setSenha(senha1);
        }

        try {
            if (listaPerfilUsuario.isEmpty()) {
                msg = "Você deve selecionar ao menos um perfil.";
                fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Aviso!", msg);
            } else {
                // aqui vem a regra para salvar
                // id == null representa usuário "novo"
                if(usuario.getId()==null){
                    usuario.setPerfis(new ArrayList<>());
                } else {
                    usuario.setPerfis(dao.listarPerfisUsuario(usuario.getId()));
                }
                // tratar remoção
                List<UsuarioPerfil> auxRemocao = new ArrayList<>();
                // percorre para encontrar quem deve ser removido!
                for(UsuarioPerfil perfil : usuario.getPerfis()){
                    if(!listaPerfilUsuario.contains(perfil.getTipo())){
                        auxRemocao.add(perfil);
                    }
                }
                for(UsuarioPerfil perfilRemover :auxRemocao){
                    usuario.getPerfis().remove(perfilRemover);
                    dao.excluirPerfil(perfilRemover);
                }
                
                List<TipoUsuario> jaSalvo = 
                              dao.listarTiposPerfisUsuario(usuario.getId());
                for(TipoUsuario tp : listaPerfilUsuario){
                    // percorrer o que está selecionado na tela
                    // comparar com o que está salvo no banco
                    if(!jaSalvo.contains(tp)){
                        UsuarioPerfil perfil = new UsuarioPerfil();
                        perfil.setUsuario(usuario);
                        perfil.setTipo(tp);
                        usuario.getPerfis().add(perfil);
                    }
                }
                msg = dao.salvar(usuario);
                fm = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Sucesso", msg);
                limparTela();                
            }
        } catch (Exception ex){
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro!", ex.getMessage());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }
}
