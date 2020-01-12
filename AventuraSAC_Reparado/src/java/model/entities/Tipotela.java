/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CHELLI BONITA
 */
@Entity
@Table(name = "tipotela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipotela.findAll", query = "SELECT t FROM Tipotela t")
    , @NamedQuery(name = "Tipotela.findByIdTipo", query = "SELECT t FROM Tipotela t WHERE t.idTipo = :idTipo")
    , @NamedQuery(name = "Tipotela.findByNombre", query = "SELECT t FROM Tipotela t WHERE t.nombre = :nombre")})
public class Tipotela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo")
    private Integer idTipo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTipo")
    private List<Fichatecnica> fichatecnicaList;

    public Tipotela() {
    }

    public Tipotela(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Tipotela(Integer idTipo, String nombre) {
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Fichatecnica> getFichatecnicaList() {
        return fichatecnicaList;
    }

    public void setFichatecnicaList(List<Fichatecnica> fichatecnicaList) {
        this.fichatecnicaList = fichatecnicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipotela)) {
            return false;
        }
        Tipotela other = (Tipotela) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Tipotela[ idTipo=" + idTipo + " ]";
    }
    
}
