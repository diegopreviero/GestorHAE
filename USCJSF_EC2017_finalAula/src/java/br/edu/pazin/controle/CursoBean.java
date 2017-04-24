/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pazin.controle;

import br.edu.pazin.dao.CursoDAO;
import br.edu.pazin.modelo.Curso;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Seven
 */
@ManagedBean
@RequestScoped
public class CursoBean {

    private Curso curso;
    private List<Curso> listaCursos;
    private FacesContext fc;
    private FacesMessage fm;
    private String msg;
    private int idTab;

    /**
     * Creates a new instance of CursoBean
     */
    public CursoBean() {
    }

    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getListaCursos() {
        if(listaCursos == null){
            CursoDAO dao = new CursoDAO();
            listaCursos = dao.getTodosCursos();
        }
        
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public FacesContext getFc() {
        return fc;
    }

    public void setFc(FacesContext fc) {
        this.fc = fc;
    }

    public FacesMessage getFm() {
        return fm;
    }

    public void setFm(FacesMessage fm) {
        this.fm = fm;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }
    
    
    
    
    
    
    private void prepararTela() {
        curso = new Curso();
        listaCursos = null;
        fc = FacesContext.getCurrentInstance();
    }

//    public void prepararTipoUsuario() {
//        tpUsuario = new TipoUsuario();
//    }

//    public void salvarTipoUsuario(ActionEvent evento) {
//        try {
//            TipoUsuarioDAO dao = new TipoUsuarioDAO();
//            msg = dao.salvar(tpUsuario);
//            listaTodosPerfil = null;
//            getListaTodosPerfil();
//        } catch (Exception ex) {
//            msg = ex.getMessage();
//            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                    "Erro", msg);
//        }
//    }

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

    public void alterarCurso(Curso curso) {
//        this.usuario = user;
//        UsuarioDAO dao = new UsuarioDAO();
//        listaPerfilUsuario
//                = dao.listarTiposPerfisUsuario(user.getId());
        this.idTab = 1;
    }

    public void excluirCurso(Curso curso) {
//        UsuarioDAO dao = new UsuarioDAO();
//        dao.excluir(user);
//        listaTodosUsuarios = null;
        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão",
                "Usuario excluido com sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
/*
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
     */

    
    
}
