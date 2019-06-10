/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author san
 */
@Entity
@Table(name = "karyawan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Karyawan.findAll", query = "SELECT k FROM Karyawan k"),
    @NamedQuery(name = "Karyawan.findById", query = "SELECT k FROM Karyawan k WHERE k.id = :id"),
    @NamedQuery(name = "Karyawan.findByNama", query = "SELECT k FROM Karyawan k WHERE k.nama like :nama"),
    @NamedQuery(name = "Karyawan.findByBarang", query = "SELECT k FROM Karyawan k WHERE k.barang = :barang"),
    @NamedQuery(name = "Karyawan.findByJumlah", query = "SELECT k FROM Karyawan k WHERE k.jumlah = :jumlah"),
    @NamedQuery(name = "Karyawan.findByTglLahir", query = "SELECT k FROM Karyawan k WHERE k.tglLahir = :tglLahir"),
    @NamedQuery(name = "Karyawan.findByIdJenisKelamin", query = "SELECT k FROM Karyawan k WHERE k.idJenisKelamin = :idJenisKelamin"),
    @NamedQuery(name = "Karyawan.findByIdStatus", query = "SELECT k FROM Karyawan k WHERE k.idStatus = :idStatus")})
public class Karyawan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "barang")
    private String barang;
    @Column(name = "jumlah")
    private Integer jumlah;
    @Column(name = "tgl_lahir")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;
    @Column(name = "id_jenis_kelamin")
    private Integer idJenisKelamin;
    @Column(name = "id_status")
    private Integer idStatus;

    public Karyawan() {
    }

    public Karyawan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public Integer getIdJenisKelamin() {
        return idJenisKelamin;
    }

    public void setIdJenisKelamin(Integer idJenisKelamin) {
        this.idJenisKelamin = idJenisKelamin;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Karyawan)) {
            return false;
        }
        Karyawan other = (Karyawan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Karyawan[ id=" + id + " ]";
    }
    
}
