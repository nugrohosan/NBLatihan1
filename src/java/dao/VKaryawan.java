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
@Table(name = "v_karyawan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VKaryawan.findAll", query = "SELECT v FROM VKaryawan v"),
    @NamedQuery(name = "VKaryawan.findById", query = "SELECT v FROM VKaryawan v WHERE v.id = :id"),
    @NamedQuery(name = "VKaryawan.findByNama", query = "SELECT v FROM VKaryawan v WHERE v.nama LIKE :nama"),
    @NamedQuery(name = "VKaryawan.findByBarang", query = "SELECT v FROM VKaryawan v WHERE v.barang = :barang"),
    @NamedQuery(name = "VKaryawan.findByJumlah", query = "SELECT v FROM VKaryawan v WHERE v.jumlah = :jumlah"),
    @NamedQuery(name = "VKaryawan.findByTglLahir", query = "SELECT v FROM VKaryawan v WHERE v.tglLahir = :tglLahir"),
    @NamedQuery(name = "VKaryawan.findByNamaJenisKelamin", query = "SELECT v FROM VKaryawan v WHERE v.namaJenisKelamin = :namaJenisKelamin")})
public class VKaryawan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id")
    @Id
    private int id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "barang")
    private String barang;
    @Column(name = "jumlah")
    private Integer jumlah;
    @Column(name = "tgl_lahir")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;
    @Column(name = "nama_jenis_kelamin")
    private String namaJenisKelamin;

    public VKaryawan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getNamaJenisKelamin() {
        return namaJenisKelamin;
    }

    public void setNamaJenisKelamin(String namaJenisKelamin) {
        this.namaJenisKelamin = namaJenisKelamin;
    }
    
}
