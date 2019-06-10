/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author san
 */
@Entity
@Table(name = "jenis_kelamin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JenisKelamin.findAll", query = "SELECT j FROM JenisKelamin j"),
    @NamedQuery(name = "JenisKelamin.findByIdJenisKelamin", query = "SELECT j FROM JenisKelamin j WHERE j.idJenisKelamin = :idJenisKelamin"),
    @NamedQuery(name = "JenisKelamin.findByNamaJenisKelamin", query = "SELECT j FROM JenisKelamin j WHERE j.namaJenisKelamin = :namaJenisKelamin")})
public class JenisKelamin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_jenis_kelamin")
    private Integer idJenisKelamin;
    @Column(name = "nama_jenis_kelamin")
    private String namaJenisKelamin;

    public JenisKelamin() {
    }

    public JenisKelamin(Integer idJenisKelamin) {
        this.idJenisKelamin = idJenisKelamin;
    }

    public Integer getIdJenisKelamin() {
        return idJenisKelamin;
    }

    public void setIdJenisKelamin(Integer idJenisKelamin) {
        this.idJenisKelamin = idJenisKelamin;
    }

    public String getNamaJenisKelamin() {
        return namaJenisKelamin;
    }

    public void setNamaJenisKelamin(String namaJenisKelamin) {
        this.namaJenisKelamin = namaJenisKelamin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJenisKelamin != null ? idJenisKelamin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JenisKelamin)) {
            return false;
        }
        JenisKelamin other = (JenisKelamin) object;
        if ((this.idJenisKelamin == null && other.idJenisKelamin != null) || (this.idJenisKelamin != null && !this.idJenisKelamin.equals(other.idJenisKelamin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.JenisKelamin[ idJenisKelamin=" + idJenisKelamin + " ]";
    }
    
}
