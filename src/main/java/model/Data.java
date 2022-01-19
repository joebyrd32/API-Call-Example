package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Data {
    private String currency;
    private Map<String, Double> rates;

    public Data(){
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}

