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
 * @author Administrador
 */
@Entity
@Table(name = "ordencompra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordencompra.findAll", query = "SELECT o FROM Ordencompra o")
    , @NamedQuery(name = "Ordencompra.findByIdOrdenCompra", query = "SELECT o FROM Ordencompra o WHERE o.idOrdenCompra = :idOrdenCompra")
    , @NamedQuery(name = "Ordencompra.findByFechaEmision", query = "SELECT o FROM Ordencompra o WHERE o.fechaEmision = :fechaEmision")})
public class Ordencompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdenCompra")
    private Integer idOrdenCompra;
    @Basic(optional = false)
    @Column(name = "FechaEmision")
    private String fechaEmision;
    @OneToMany(mappedBy = "idOrdenCompra")
    private List<Movimientoalmacen> movimientoalmacenList;
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne
    private Proveedor idProveedor;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado")
    @ManyToOne
    private Empleado idEmpleado;
    @OneToMany(mappedBy = "idOrdenCompra")
    private List<OrdencompraDetalle> ordencompraDetalleList;

    public Ordencompra() {
    }

    public Ordencompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Ordencompra(Integer idOrdenCompra, String fechaEmision) {
        this.idOrdenCompra = idOrdenCompra;
        this.fechaEmision = fechaEmision;
    }

    public Integer getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(Integer idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @XmlTransient
    public List<Movimientoalmacen> getMovimientoalmacenList() {
        return movimientoalmacenList;
    }

    public void setMovimientoalmacenList(List<Movimientoalmacen> movimientoalmacenList) {
        this.movimientoalmacenList = movimientoalmacenList;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @XmlTransient
    public List<OrdencompraDetalle> getOrdencompraDetalleList() {
        return ordencompraDetalleList;
    }

    public void setOrdencompraDetalleList(List<OrdencompraDetalle> ordencompraDetalleList) {
        this.ordencompraDetalleList = ordencompraDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenCompra != null ? idOrdenCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordencompra)) {
            return false;
        }
        Ordencompra other = (Ordencompra) object;
        if ((this.idOrdenCompra == null && other.idOrdenCompra != null) || (this.idOrdenCompra != null && !this.idOrdenCompra.equals(other.idOrdenCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Ordencompra[ idOrdenCompra=" + idOrdenCompra + " ]";
    }
    
}
