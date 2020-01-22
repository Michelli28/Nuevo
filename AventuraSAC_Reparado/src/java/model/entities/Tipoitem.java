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
 * @author Administrador
 */
@Entity
@Table(name = "tipoitem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoitem.findAll", query = "SELECT t FROM Tipoitem t")
    , @NamedQuery(name = "Tipoitem.findByIdTipoItem", query = "SELECT t FROM Tipoitem t WHERE t.idTipoItem = :idTipoItem")
    , @NamedQuery(name = "Tipoitem.findByNombre", query = "SELECT t FROM Tipoitem t WHERE t.nombre = :nombre")})
public class Tipoitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoItem")
    private Integer idTipoItem;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTipoItem")
    private List<Movimientoalmacen> movimientoalmacenList;

    public Tipoitem() {
    }

    public Tipoitem(Integer idTipoItem) {
        this.idTipoItem = idTipoItem;
    }

    public Tipoitem(Integer idTipoItem, String nombre) {
        this.idTipoItem = idTipoItem;
        this.nombre = nombre;
    }

    public Integer getIdTipoItem() {
        return idTipoItem;
    }

    public void setIdTipoItem(Integer idTipoItem) {
        this.idTipoItem = idTipoItem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Movimientoalmacen> getMovimientoalmacenList() {
        return movimientoalmacenList;
    }

    public void setMovimientoalmacenList(List<Movimientoalmacen> movimientoalmacenList) {
        this.movimientoalmacenList = movimientoalmacenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoItem != null ? idTipoItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoitem)) {
            return false;
        }
        Tipoitem other = (Tipoitem) object;
        if ((this.idTipoItem == null && other.idTipoItem != null) || (this.idTipoItem != null && !this.idTipoItem.equals(other.idTipoItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Tipoitem[ idTipoItem=" + idTipoItem + " ]";
    }
    
}
