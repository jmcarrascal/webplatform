//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.29 at 01:06:17 PM ART 
//


package ar.com.jmc.webplatform.base.model.dto.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}PersonaFisica"/>
 *         &lt;element ref="{}Domicilio"/>
 *         &lt;element ref="{}BeneficiosSolicitados" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Documentos" maxOccurs="unbounded"/>
 *         &lt;element ref="{}RespuestaVerificacionAlta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personaFisica",
    "domicilio",
    "beneficiosSolicitados",
    "documentos",
    "respuestaVerificacionAlta"
})
@XmlRootElement(name = "Beneficiario")
public class Beneficiario {

    @XmlElement(name = "PersonaFisica", required = true)
    protected PersonaFisica personaFisica;
    @XmlElement(name = "Domicilio", required = true)
    protected Domicilio domicilio;
    @XmlElement(name = "BeneficiosSolicitados")
    protected List<BeneficiosSolicitados> beneficiosSolicitados;
    @XmlElement(name = "Documentos", required = true)
    protected List<Documentos> documentos;
    @XmlElement(name = "RespuestaVerificacionAlta")
    protected RespuestaVerificacionAlta respuestaVerificacionAlta;

    /**
     * Gets the value of the personaFisica property.
     * 
     * @return
     *     possible object is
     *     {@link PersonaFisica }
     *     
     */
    public PersonaFisica getPersonaFisica() {
        return personaFisica;
    }

    /**
     * Sets the value of the personaFisica property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonaFisica }
     *     
     */
    public void setPersonaFisica(PersonaFisica value) {
        this.personaFisica = value;
    }

    /**
     * Gets the value of the domicilio property.
     * 
     * @return
     *     possible object is
     *     {@link Domicilio }
     *     
     */
    public Domicilio getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the value of the domicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Domicilio }
     *     
     */
    public void setDomicilio(Domicilio value) {
        this.domicilio = value;
    }

    /**
     * Gets the value of the beneficiosSolicitados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the beneficiosSolicitados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBeneficiosSolicitados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BeneficiosSolicitados }
     * 
     * 
     */
    public List<BeneficiosSolicitados> getBeneficiosSolicitados() {
        if (beneficiosSolicitados == null) {
            beneficiosSolicitados = new ArrayList<BeneficiosSolicitados>();
        }
        return this.beneficiosSolicitados;
    }

    /**
     * Gets the value of the documentos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Documentos }
     * 
     * 
     */
    public List<Documentos> getDocumentos() {
        if (documentos == null) {
            documentos = new ArrayList<Documentos>();
        }
        return this.documentos;
    }

    /**
     * Gets the value of the respuestaVerificacionAlta property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaVerificacionAlta }
     *     
     */
    public RespuestaVerificacionAlta getRespuestaVerificacionAlta() {
        return respuestaVerificacionAlta;
    }

    /**
     * Sets the value of the respuestaVerificacionAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaVerificacionAlta }
     *     
     */
    public void setRespuestaVerificacionAlta(RespuestaVerificacionAlta value) {
        this.respuestaVerificacionAlta = value;
    }

}