/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import dao.Karyawan;
import dao.VKaryawan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author san
 */
public class index_ extends GenericForwardComposer{
    static EntityManagerFactory emf= Persistence.createEntityManagerFactory("Latihan1PU");
    static EntityManager em= emf.createEntityManager();
    
    protected List lstKaryawan, lstVKaryawan;
    
    private Karyawan tbKaryawan;
    private VKaryawan   tbVKaryawan;
    private Textbox txtNama;    
    private Textbox txtBarang;
    private Button  btnAdd;
    private Button  btnEdit;
    private Button  btnHapus;
    private Button  btnTutup;
    private Button  btnRefresh;
    private Button  btnCari;
    private Window  winUtama;
//    
//    private ResultSet   rsTotal;
//    private Connection  cn;
//    private Statement   st;
    private ResultSet   rsTotal;
    private Connection  cn;
    private Statement   st;
    private Label   lblJml;

    public void onCreate$winUtama() throws InterruptedException, SQLException{
        koneksi_ k = new koneksi_();
        cn  = k.getConnection();
        st  = cn.createStatement();
        
        rsTotal = st.executeQuery("SELECT count(*) as jml FROM karyawan");
        rsTotal.next();
        //Messagebox.show(rsTotal.getString("jml"));        
        
        jumlah();
                
        
        
        
        
        
        
        
        lstKaryawan = em.createNamedQuery("Karyawan.findAll")
            .setHint("eclipselink.refresh", "true")
            .getResultList();
//        lstVKaryawan = em.createNamedQuery("VKaryawan.findAll")
//            .setHint("eclipselink.refresh", "true")
//            .getResultList();
        String sql  ="SELECT * FROM v_karyawan LIMIT 10";
        lstVKaryawan=em.createNativeQuery(sql, VKaryawan.class)
                .setHint("eclipselink.refresh", "true")
                .getResultList();
//        tbKaryawan = (Karyawan) lstKaryawan.get(3);
//        Messagebox.show(tbKaryawan.getNama());
    }
    
    public void jumlah() throws InterruptedException, SQLException{
        rsTotal = st.executeQuery("SELECT count(*) as jml FROM karyawan");
        rsTotal.next();
        lblJml.setValue("Jumlah = "+rsTotal.getString("jml")+" org");        
    }
    public void onClick$btnAdd() throws InterruptedException{
        session.setAttribute("sessNavi", "1");
        execution.createComponents("/zul/crudKaryawan.zul",null,null);
    }
    public void onClick$btnEdit() throws InterruptedException{
        try {
            session.setAttribute("sessNavi", "0");
            session.setAttribute("sessId", tbKaryawan.getId());
            execution.createComponents("/zul/crudKaryawan.zul",null,null);
        } catch (Throwable e) {
            Messagebox.show("Data belum dipilih");
            em.getTransaction().rollback();
        }
    }
    
    public void onClick$btnRefresh() throws InterruptedException, SQLException{
        lstVKaryawan = em.createNamedQuery("VKaryawan.findAll")
            .setHint("eclipselink.refresh", "true")
            .getResultList();
        jumlah();
//        Messagebox.show("oke");
    }
    
    public void onSelect$lsbDaftar() throws InterruptedException{
        lstKaryawan=em.createNamedQuery("Karyawan.findById")
                .setHint("eclipselink.refresh", "true")
                .setParameter("id", tbVKaryawan.getId())
                .getResultList();
        tbKaryawan= (Karyawan) lstKaryawan.get(0);
    }
    public void onClick$btnHapus() throws InterruptedException{
        try {
//            if (!em.getTransaction().isActive()) {
//                em.getTransaction().begin();
//                em.remove(tbKaryawan);
//                em.flush();
//                em.getTransaction().commit();
//                
//                lstVKaryawan = em.createNamedQuery("VKaryawan.findAll")
//                    .setHint("eclipselink.refresh", "true")
//                    .getResultList();
//            }

                if (!em.getTransaction().isActive()) {
                    em.getTransaction().begin();
                }
                
                String sql  ="DELETE FROM karyawan WHERE id='"+tbVKaryawan.getId()+"'";
                    em.createNativeQuery(sql).executeUpdate();
                    em.flush();
                    em.getTransaction().commit();
                    
                lstVKaryawan = em.createNamedQuery("VKaryawan.findAll")
                    .setHint("eclipselink.refresh", "true")
                    .getResultList();
        jumlah();
        } catch (Throwable e) {
            Messagebox.show("Data belum dipilih");
            em.getTransaction().rollback();
        }
    }
    
    public void onClick$btnTutup() throws InterruptedException{
        winUtama.detach();
    }
    
    public void onOK$txtNama() throws InterruptedException {
        cari();
    }
    
    public void onClick$btnCari() throws InterruptedException {
        cari();
    }
    
    public void cari (){
//        lstVKaryawan = em.createNamedQuery("VKaryawan.findByNama")
//                .setHint("eclipselink.refresh", "true")
//                .setParameter("nama",txtNama.getValue()+'%')
//                .getResultList();

        String sql  ="SELECT * FROM v_karyawan WHERE nama LIKE '"+txtNama.getValue()+"%'";
        lstVKaryawan=em.createNativeQuery(sql, VKaryawan.class)
                .setHint("eclipselink.refresh", "true")
                .getResultList();
    }
    
    public void onOK$txtBarang() throws InterruptedException {
        lstVKaryawan = em.createNamedQuery("VKaryawan.findByBarang")
                .setHint("eclipselink.refresh", "true")
                .setParameter("barang",txtBarang.getValue()+'%')
                .getResultList();
    }
    
    public List getLstKaryawan() {
        return lstKaryawan;
    }

    public void setLstKaryawan(List lstKaryawan) {
        this.lstKaryawan = lstKaryawan;
    }

    public Karyawan getTbKaryawan() {
        return tbKaryawan;
    }

    public void setTbKaryawan(Karyawan tbKaryawan) {
        this.tbKaryawan = tbKaryawan;
    }

    public List getLstVKaryawan() {
        return lstVKaryawan;
    }

    public void setLstVKaryawan(List lstVKaryawan) {
        this.lstVKaryawan = lstVKaryawan;
    }

    public VKaryawan getTbVKaryawan() {
        return tbVKaryawan;
    }

    public void setTbVKaryawan(VKaryawan tbVKaryawan) {
        this.tbVKaryawan = tbVKaryawan;
    }

    
    
}
