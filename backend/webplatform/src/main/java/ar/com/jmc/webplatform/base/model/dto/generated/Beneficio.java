//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.14 at 03:30:38 PM ART 
//


package ar.com.jmc.webplatform.base.model.dto.generated;

import java.math.BigInteger;
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
 *         &lt;element name="PaisPrestacion" type="{}TipoPais"/>
 *         &lt;element name="TipoPrestacion" type="{}TipoPrestacion"/>
 *         &lt;element name="IdentPrestacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "paisPrestacion",
    "tipoPrestacion",
    "identPrestacion"
})
@XmlRootElement(name = "Beneficio")
public class Beneficio {

    @XmlElement(name = "PaisPrestacion", required = true)
    protected BigInteger paisPrestacion;
    @XmlElement(name = "TipoPrestacion", required = true)
    protected BigInteger tipoPrestacion;
    @XmlElement(name = "IdentPrestacion", required = true)
    protected String identPrestacion;

    /**
     * Gets the value of the paisPrestacion property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPaisPrestacion() {
        return paisPrestacion;
    }

    /**
     * Sets the value of the paisPrestacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPaisPrestacion(BigInteger value) {
        this.paisPrestacion = value;
    }

    /**
     * Gets the value of the tipoPrestacion property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTipoPrestacion() {
        return tipoPrestacion;
    }

    /**
     * Sets the value of the tipoPrestacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTipoPrestacion(BigInteger value) {
        this.tipoPrestacion = value;
    }

    /**
     * Gets the value of the identPrestacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentPrestacion() {
        return identPrestacion;
    }

    /**
     * Sets the value of the identPrestacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentPrestacion(String value) {
        this.identPrestacion = value;
    }

}
