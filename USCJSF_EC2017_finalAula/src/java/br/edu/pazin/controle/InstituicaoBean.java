package br.edu.pazin.controle;

import br.edu.pazin.dao.InstituicaoDAO;
import br.edu.pazin.modelo.Instituicao;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Seven
 */
@ManagedBean
@ViewScoped
public class InstituicaoBean implements Serializable{

    private Instituicao instituicao;
    private List<Instituicao> listaInstituicoes;
    private FacesContext fc;
    private FacesMessage fm;
    private String msg;
    private int idTab;

    public InstituicaoBean() {
        prepararTela();
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public List<Instituicao> getListaIntituicoes() {
        if (listaInstituicoes == null) {
            InstituicaoDAO dao = new InstituicaoDAO();
            listaInstituicoes = dao.getTodasInstituicoes();
        }

        return listaInstituicoes;
    }

    public void setListaIntituicoes(List<Instituicao> listaInstituicoes) {
        this.listaInstituicoes = listaInstituicoes;
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
        instituicao = new Instituicao();
        listaInstituicoes = null;
        fc = FacesContext.getCurrentInstance();
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

    public void alterarInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
        InstituicaoDAO dao = new InstituicaoDAO();
        this.idTab = 1;
    }

    public void excluirInstituicao(Instituicao instituicao) {
        InstituicaoDAO dao = new InstituicaoDAO();
        dao.excluir(instituicao);
        listaInstituicoes = null;
        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão",
                "Instituicao excluida com sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public void salvarInstituicao() {
        InstituicaoDAO dao = new InstituicaoDAO();
        try {
            msg = dao.salvar(instituicao);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso", msg);
            limparTela();

        } catch (Exception ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro!", ex.getMessage());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
    }

}
