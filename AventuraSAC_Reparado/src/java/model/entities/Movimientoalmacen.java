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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "movimientoalmacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientoalmacen.findAll", query = "SELECT m FROM Movimientoalmacen m")
    , @NamedQuery(name = "Movimientoalmacen.findByIdMovimiento", query = "SELECT m FROM Movimientoalmacen m WHERE m.idMovimiento = :idMovimiento")
    , @NamedQuery(name = "Movimientoalmacen.findByTipoMovimiento", query = "SELECT m FROM Movimientoalmacen m WHERE m.tipoMovimiento = :tipoMovimiento")
    , @NamedQuery(name = "Movimientoalmacen.findByFecha", query = "SELECT m FROM Movimientoalmacen m WHERE m.fecha = :fecha")})
public class Movimientoalmacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMovimiento")
    private Integer idMovimiento;
    @Column(name = "TipoMovimiento")
    private String tipoMovimiento;
    @Basic(optional = false)
    @Column(name = "fecha")
    private String fecha;
    @JoinColumn(name = "idTipoItem", referencedColumnName = "idTipoItem")
    @ManyToOne
    private Tipoitem idTipoItem;
    @JoinColumn(name = "idOrdenCompra", referencedColumnName = "idOrdenCompra")
    @ManyToOne
    private Ordencompra idOrdenCompra;
    @OneToMany(mappedBy = "idMovimiento")
    private List<Detallemovimiento> detallemovimientoList;

    public Movimientoalmacen() {
    }

    public Movimientoalmacen(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Movimientoalmacen(Integer idMovimiento, String fecha) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Tipoitem getIdTipoItem() {
        return idTipoItem;
    }

    public void setIdTipoItem(Tipoitem idTipoItem) {
        this.idTipoItem = idTipoItem;
    }

    public Ordencompra getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Ordencompra idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    @XmlTransient
    public List<Detallemovimiento> getDetallemovimientoList() {
        return detallemovimientoList;
    }

    public void setDetallemovimientoList(List<Detallemovimiento> detallemovimientoList) {
        this.detallemovimientoList = detallemovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientoalmacen)) {
            return false;
        }
        Movimientoalmacen other = (Movimientoalmacen) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Movimientoalmacen[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
