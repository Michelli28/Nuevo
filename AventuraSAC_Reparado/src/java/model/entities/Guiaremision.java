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
@Table(name = "guiaremision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guiaremision.findAll", query = "SELECT g FROM Guiaremision g")
    , @NamedQuery(name = "Guiaremision.findByIdGuiaRemision", query = "SELECT g FROM Guiaremision g WHERE g.idGuiaRemision = :idGuiaRemision")
    , @NamedQuery(name = "Guiaremision.findByFechaEmision", query = "SELECT g FROM Guiaremision g WHERE g.fechaEmision = :fechaEmision")
    , @NamedQuery(name = "Guiaremision.findByPuntoLlegada", query = "SELECT g FROM Guiaremision g WHERE g.puntoLlegada = :puntoLlegada")
    , @NamedQuery(name = "Guiaremision.findByDestinatario", query = "SELECT g FROM Guiaremision g WHERE g.destinatario = :destinatario")
    , @NamedQuery(name = "Guiaremision.findByRUCtransporte", query = "SELECT g FROM Guiaremision g WHERE g.rUCtransporte = :rUCtransporte")
    , @NamedQuery(name = "Guiaremision.findByNombreTransportista", query = "SELECT g FROM Guiaremision g WHERE g.nombreTransportista = :nombreTransportista")
    , @NamedQuery(name = "Guiaremision.findByMarcayPlaca", query = "SELECT g FROM Guiaremision g WHERE g.marcayPlaca = :marcayPlaca")
    , @NamedQuery(name = "Guiaremision.findByLicencia", query = "SELECT g FROM Guiaremision g WHERE g.licencia = :licencia")})
public class Guiaremision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGuiaRemision")
    private Integer idGuiaRemision;
    @Basic(optional = false)
    @Column(name = "FechaEmision")
    private String fechaEmision;
    @Basic(optional = false)
    @Column(name = "PuntoLlegada")
    private String puntoLlegada;
    @Basic(optional = false)
    @Column(name = "Destinatario")
    private String destinatario;
    @Basic(optional = false)
    @Column(name = "RUCtransporte")
    private String rUCtransporte;
    @Basic(optional = false)
    @Column(name = "NombreTransportista")
    private String nombreTransportista;
    @Basic(optional = false)
    @Column(name = "MarcayPlaca")
    private String marcayPlaca;
    @Basic(optional = false)
    @Column(name = "Licencia")
    private String licencia;
    @OneToMany(mappedBy = "idGuiaRemision")
    private List<GuiaremisionDetalle> guiaremisionDetalleList;

    public Guiaremision() {
    }

    public Guiaremision(Integer idGuiaRemision) {
        this.idGuiaRemision = idGuiaRemision;
    }

    public Guiaremision(Integer idGuiaRemision, String fechaEmision, String puntoLlegada, String destinatario, String rUCtransporte, String nombreTransportista, String marcayPlaca, String licencia) {
        this.idGuiaRemision = idGuiaRemision;
        this.fechaEmision = fechaEmision;
        this.puntoLlegada = puntoLlegada;
        this.destinatario = destinatario;
        this.rUCtransporte = rUCtransporte;
        this.nombreTransportista = nombreTransportista;
        this.marcayPlaca = marcayPlaca;
        this.licencia = licencia;
    }

    public Integer getIdGuiaRemision() {
        return idGuiaRemision;
    }

    public void setIdGuiaRemision(Integer idGuiaRemision) {
        this.idGuiaRemision = idGuiaRemision;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getPuntoLlegada() {
        return puntoLlegada;
    }

    public void setPuntoLlegada(String puntoLlegada) {
        this.puntoLlegada = puntoLlegada;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRUCtransporte() {
        return rUCtransporte;
    }

    public void setRUCtransporte(String rUCtransporte) {
        this.rUCtransporte = rUCtransporte;
    }

    public String getNombreTransportista() {
        return nombreTransportista;
    }

    public void setNombreTransportista(String nombreTransportista) {
        this.nombreTransportista = nombreTransportista;
    }

    public String getMarcayPlaca() {
        return marcayPlaca;
    }

    public void setMarcayPlaca(String marcayPlaca) {
        this.marcayPlaca = marcayPlaca;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    @XmlTransient
    public List<GuiaremisionDetalle> getGuiaremisionDetalleList() {
        return guiaremisionDetalleList;
    }

    public void setGuiaremisionDetalleList(List<GuiaremisionDetalle> guiaremisionDetalleList) {
        this.guiaremisionDetalleList = guiaremisionDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGuiaRemision != null ? idGuiaRemision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guiaremision)) {
            return false;
        }
        Guiaremision other = (Guiaremision) object;
        if ((this.idGuiaRemision == null && other.idGuiaRemision != null) || (this.idGuiaRemision != null && !this.idGuiaRemision.equals(other.idGuiaRemision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.Guiaremision[ idGuiaRemision=" + idGuiaRemision + " ]";
    }
    
}
