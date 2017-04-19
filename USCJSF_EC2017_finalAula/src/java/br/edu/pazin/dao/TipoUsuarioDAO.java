/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pazin.dao;

import br.edu.pazin.modelo.TipoUsuario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author Anderson Pazin
 */
public class TipoUsuarioDAO extends GenericDAO<TipoUsuario> {

    public TipoUsuario getPorId(Integer id) {
        try {
            return (TipoUsuario) getSessao().get(TipoUsuario.class, id);
        } catch (Exception e) {
            System.out.println("TipoUsuarioDAO.getPorId()");
            return null;
        }
    }

    public List<TipoUsuario> getTodosTipoUsuarios() {
        try {
            Criteria c = getSessao().createCriteria(TipoUsuario.class);
            c.addOrder(Order.asc("papel"));
            return c.list();
        } catch (Exception ex) {
            System.out.println("TipoUsuarioDAO.getTodosTipoUsuarios()");
            return null;
        }
    }

    public String salvar(TipoUsuario tp){
        String msg = "";
        try {
            if (getPorId(tp.getId()) == null) {
                this.inserir(tp);
                msg = "Tipo de usuário inserido com sucesso!";
            } else {
                this.alterar(tp);
                msg = "Tipo de usuário atualizado com sucesso!";
            }
            return msg;
        } catch (Exception ex) {
            return "Problemas ao salvar o tipo de usuário: " + ex.getMessage();
        }
    }

}
