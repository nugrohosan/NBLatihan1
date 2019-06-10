/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import static ctrl.index_.em;
import dao.JenisKelamin;
import dao.Karyawan;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import org.codehaus.groovy.control.messages.Message;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author san
 */
public class crudKaryawan_  extends GenericForwardComposer{
    static EntityManagerFactory emf= Persistence.createEntityManagerFactory("Latihan1PU");
    static EntityManager em= emf.createEntityManager();
    
    protected List      lstKaryawan, lstJenisKelamin;
    
    private JenisKelamin    tbJenisKelamin;
    private Karyawan    tbKaryawan;
    private Textbox     txtNama, txtBarang;
    private Intbox      txtJumlah;
    private Datebox     dtTglLhr;
    private Button      btnSimpan;
    private Window      winUtamaCrud;
    private ComboBox    cbJenisKelamin;
    private SimpleDateFormat    sdf=new SimpleDateFormat("yyy-MM-dd");
    private Radiogroup      rdStatus;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); //To change body of generated methods, choose Tools | Templates.
        lstJenisKelamin=em.createNamedQuery("JenisKelamin.findAll")
                .setHint("eclipselink.refresh", "true")
                .getResultList();
    }
    
    public void onCreate$winUtamaCrud() throws InterruptedException{
        if (session.getAttribute("sessNavi").toString()=="1") {
            lstKaryawan=em.createNamedQuery("Karyawan.findAll")
                    .setHint("eclipselink.refresh", "true")
                    .setMaxResults(1)
                    .getResultList();
            Karyawan baru=new Karyawan();
            lstKaryawan.add(baru);
            tbKaryawan=(Karyawan) lstKaryawan.get(lstKaryawan.size()-1);
            rdStatus.setSelectedIndex(0);
        }else{
            lstKaryawan=em.createNamedQuery("Karyawan.findById")
                    .setHint("eclipselink.refresh", "true")
                    .setParameter("id", Integer.parseInt(session.getAttribute("sessId").toString()))
                    .getResultList();
            tbKaryawan= (Karyawan) lstKaryawan.get(0);
//            txtNama.setValue(tbKaryawan.getNama());
//            txtBarang.setValue(tbKaryawan.getBarang());            
//            txtJumlah.setValue(tbKaryawan.getJumlah());        
            dtTglLhr.setValue(tbKaryawan.getTglLahir());
            rdStatus.setSelectedIndex(tbKaryawan.getIdStatus());

            
        }
    }
    public void onClick$btnSimpan() throws InterruptedException{
        try {
//            if (!em.getTransaction().isActive()) {
//                em.getTransaction().begin();
//                em.persist(tbKaryawan);
////                tbKaryawan.setNama(txtNama.getValue());
////                tbKaryawan.setBarang(txtBarang.getValue()); 
////                tbKaryawan.setJumlah(txtJumlah.getValue());  
//                tbKaryawan.setTglLahir(dtTglLhr.getValue());   
//
//                em.flush();
//                em.getTransaction().commit();

              if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
              }
              String sql=null;
              
        if (session.getAttribute("sessNavi").toString()=="1") {
            sql="INSERT INTO karyawan (nama,barang,jumlah,tgl_lahir,id_jenis_kelamin,id_status)"
                    + " VALUES ('"+
                    txtNama.getValue()+"','"+
                    txtBarang.getValue()+"','"+
                    txtJumlah.getValue()+"','"+
                    sdf.format(dtTglLhr.getValue())+"','"+
                    tbKaryawan.getIdJenisKelamin()+"','"+
                    rdStatus.getSelectedIndex()+"')";
        }else{
            sql="UPDATE karyawan SET "
                    + "`nama` = '"+txtNama.getValue()+"' , "
                    + "`barang` = '"+txtBarang.getValue()+"' , "
                    + "`jumlah` = '"+txtJumlah.getValue()+"' , "
                    + "`tgl_lahir` = '"+sdf.format(dtTglLhr.getValue())+"', "
                    + "`id_jenis_kelamin` = '"+tbKaryawan.getIdJenisKelamin()+"' , "
                    + "`id_status` = '"+rdStatus.getSelectedIndex()+"' "
                    + "WHERE `id` = '"+session.getAttribute("sessId").toString()+"'; ";
        }
        
            em.createNativeQuery(sql).executeUpdate();
            em.flush();
            em.getTransaction().commit();
            winUtamaCrud.detach();
            Messagebox.show("Simpan Sukses","Informasi", Messagebox.OK,Messagebox.INFORMATION);
            
        } catch (Throwable e) {
            Messagebox.show(e.getMessage());
            em.getTransaction().rollback();
        }
        
    }
    
    public void onSelect$cbJenisKelamin() throws InterruptedException{ 
        
             
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

    public List getLstJenisKelamin() {
        return lstJenisKelamin;
    }

    public void setLstJenisKelamin(List lstJenisKelamin) {
        this.lstJenisKelamin = lstJenisKelamin;
    }

    public JenisKelamin getTbJenisKelamin() {
        return tbJenisKelamin;
    }

    public void setTbJenisKelamin(JenisKelamin tbJenisKelamin) {
        this.tbJenisKelamin = tbJenisKelamin;
    }

    public ComboBox getCbJenisKelamin() {
        return cbJenisKelamin;
    }

    public void setCbJenisKelamin(ComboBox cbJenisKelamin) {
        this.cbJenisKelamin = cbJenisKelamin;
    }

 
    
}
