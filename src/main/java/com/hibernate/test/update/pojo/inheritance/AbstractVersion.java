package com.hibernate.test.update.pojo.inheritance;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Versionen")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "REPRAESENTATION", discriminatorType = DiscriminatorType.STRING)
@DynamicUpdate()
public abstract  class AbstractVersion {
    @Id
    @Column(name = "VERS_NUMMER")
    private String id;

    @Column(name = "CHECKSUM")
    protected String checkSumMD5;

    @Column(name = "VARIANTE", nullable = false)
    private String variantName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckSumMD5() {
        return checkSumMD5;
    }

    public void setCheckSumMD5(String checkSumMD5) {
        this.checkSumMD5 = checkSumMD5;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }
}
