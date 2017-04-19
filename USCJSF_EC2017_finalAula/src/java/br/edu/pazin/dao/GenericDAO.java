/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.pazin.dao;

import org.hibernate.Session;

/**
 *
 * @author Anderson Pazin
 */
public abstract class GenericDAO<T>  {
    private Session sessao;

    public GenericDAO() {
        sessao = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public GenericDAO(Session sessao) {
        this.sessao = sessao;
    }

    public Session getSessao() {
        return sessao;
    }

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }
    
    public void inserir(T obj) {
        try{
            this.sessao.clear();
            this.sessao.save(obj);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void alterar(T obj) {
        try{
            this.sessao.merge(obj);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void excluir(T obj) {
        try{
            this.sessao.delete(obj);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
