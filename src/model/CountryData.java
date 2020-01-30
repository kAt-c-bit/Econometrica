/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bill
 */
@Entity
@Table(name = "COUNTRY_DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CountryData.findAll", query = "SELECT c FROM CountryData c")
    , @NamedQuery(name = "CountryData.findByDataYear", query = "SELECT c FROM CountryData c WHERE c.dataYear = :dataYear")
    , @NamedQuery(name = "CountryData.findByValue", query = "SELECT c FROM CountryData c WHERE c.value = :value")
    , @NamedQuery(name = "CountryData.findByDataset", query = "SELECT c FROM CountryData c WHERE c.dataset = :dataset")})
public class CountryData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "DATA_YEAR")
    private String dataYear;
    @Basic(optional = false)
    @Column(name = "VALUE")
    private String value;
    @Id
    @Basic(optional = false)
    @Column(name = "DATASET")
    private Long dataset;
    @JoinColumn(name = "DATASET", referencedColumnName = "DATASET_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private CountryDataset countryDataset;

    public CountryData() {
    }

    public CountryData(Long dataset) {
        this.dataset = dataset;
    }

    public CountryData(Long dataset, String dataYear, String value) {
        this.dataset = dataset;
        this.dataYear = dataYear;
        this.value = value;
    }

    public String getDataYear() {
        return dataYear;
    }

    public void setDataYear(String dataYear) {
        this.dataYear = dataYear;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getDataset() {
        return dataset;
    }

    public void setDataset(Long dataset) {
        this.dataset = dataset;
    }

    public CountryDataset getCountryDataset() {
        return countryDataset;
    }

    public void setCountryDataset(CountryDataset countryDataset) {
        this.countryDataset = countryDataset;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataset != null ? dataset.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountryData)) {
            return false;
        }
        CountryData other = (CountryData) object;
        if ((this.dataset == null && other.dataset != null) || (this.dataset != null && !this.dataset.equals(other.dataset))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CountryData[ dataset=" + dataset + " ]";
    }
    
}
