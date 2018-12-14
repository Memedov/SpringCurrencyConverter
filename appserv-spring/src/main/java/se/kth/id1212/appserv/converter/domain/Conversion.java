package se.kth.id1212.appserv.converter.domain;

import se.kth.id1212.appserv.converter.util.Util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONVERSION")
public class Conversion implements ConversionDTO {
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "CONVERSION_SEQUENCE")

    @Column(name = "CON_FROMTO")
    private String fromto;

    @Column(name = "CON_RATE")
    private double rate;

    public Conversion(String fromto, double rate) {
        this.fromto = fromto;
        this.rate = rate;
    }

    protected Conversion() {
    }

    @Override
    public double getRate() {
        return rate;
    }

    @Override
    public void setNewRate(double newRate) {
        this.rate = newRate;
    }

    @Override
    public int hashCode() {
        //return Long.valueOf(acctNo).hashCode();
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Conversion)) {
            return false;
        }
        Conversion other = (Conversion) object;
        return true;
    }

    @Override
    public String toString() {
        return Util.toString(this);
    }
}