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
@Table(name = "fichatecnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fichatecnica.findAll", query = "SELECT f FROM Fichatecnica f")
    , @NamedQuery(name = "Fichatecnica.findByIdFicha", query = "SELECT f FROM Fichatecnica f WHERE f.idFicha = :idFicha")
    , @NamedQuery(name = "Fichatecnica.findByDescripcion", query = "SELECT f FROM Fichatecnica f WHERE f.descripcion = :descripcion")
    , @NamedQuery(name = "Fichatecnica.findByCantidad", query = "SELECT f FROM Fichatecnica f WHERE f.cantidad = :cantidad")
    , @NamedQuery(name = "Fichatecnica.findByEtiqueta", query = "SELECT f FROM Fichatecnica f WHERE f.etiqueta = :etiqueta")
    , @NamedQuery(name = "Fichatecnica.findByColor1", query = "SELECT f FROM Fichatecnica f WHERE f.color1 = :color1")
    , @NamedQuery(name = "Fichatecnica.findByColor2", query = "SELECT f FROM Fichatecnica f WHERE f.color2 = :color2")
    , @NamedQuery(name = "Fichatecnica.findByColor3", query = "SELECT f FROM Fichatecnica f WHERE f.color3 = :color3")})
public class Fichatecnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFicha")
    private Integer idFicha;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "Cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "Etiqueta")
    private String etiqueta;
    @Column(name = "Color1")
    private String color1;
    @Column(name = "Color2")
    private String color2;
    @Column(name = "Color3")
    private String color3;
    @OneToMany(mappedBy = "idFicha")
    private List<PedidoDetalle> pedidoDetalleList;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne
    private Cliente idCliente;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne(optional = false)
    private Estado idEstado;
    @JoinColumn(name = "idTalla", referencedColumnName = "idTalla")
    @ManyToOne
    private Talla idTalla;
    @JoinColumn(name = "idTipoModelo", referencedColumnName = "idTipoModelo")
    @ManyToOne
    private Tipomodelo idTipoModelo;
    @JoinColumn(name = "idTipo", referencedColumnName = "idTipo")
    @ManyToOne
    private Tipotela idTipo;

    public Fichatecnica() {
    }

    public Fichatecnica(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Fichatecnica(Integer idFicha, String descripcion, int cantidad, String etiqueta) {
        this.idFicha = idFicha;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.etiqueta = etiqueta;
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    @XmlTransient
    public List<PedidoDetalle> getPedidoDetalleList() {
        return pedidoDetalleList;
    }

    public void setPedidoDetalleList(List<PedidoDetalle> pedidoDetalleList) {
        this.pedidoDetalleList = pedidoDetalleList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public Talla getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Talla idTalla) {
        this.idTalla = idTalla;
    }

    public Tipomodelo getIdTipoModelo() {
        return idTipoModelo;
    }

    public void setIdTipoModelo(Tipomodelo idTipoModelo) {
        this.idTipoModelo = idTipoModelo;
    }

    public Tipotela getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipotela idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFicha != null ? idFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fichatecnica)) {
            return false;
        }
        Fichatecnica other = (Fichatecnica) object;
        if ((this.idFicha == null && other.idFicha != null) || (this.idFicha != null && !this.idFicha.equals(other.idFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Fichatecnica[ idFicha=" + idFicha + " ]";
    }
    
}
