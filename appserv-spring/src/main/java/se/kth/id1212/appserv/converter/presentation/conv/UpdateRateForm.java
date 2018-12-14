package se.kth.id1212.appserv.converter.presentation.conv;

import se.kth.id1212.appserv.converter.util.Util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

class UpdateRateForm {
    @NotNull(message = "Please specify rate")
    @Positive(message = "Amount must be greater than zero")
    private Integer rate;

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    private int number;
    private String from;
    private String to;
    private String conversionResult;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFrom() {
        return from;
    }
    //public void setFrom(String from) {this.from = from;}

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setConversionResult(double conversionResult) {
        this.conversionResult = conversionResult + " " + this.to;
    }

    public String getConversionResult() {
        return conversionResult;
    }

    @Override
    public String toString() {
        return Util.toString(this);
    }
}
