/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.samples.connectors.orgsync.hrim.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Bonus
{
    private String pnum = null;
    private Date   paymentDate = null;
    private int    amount = 0;
    private String currency = null;


    public Bonus()
    {
    }


    public String getPnum()
    {
        return pnum;
    }


    public void setPnum(String pnum)
    {
        this.pnum = pnum;
    }


    public Date getPaymentDate()
    {
        return paymentDate;
    }


    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }


    public int getAmount()
    {
        return amount;
    }


    public void setAmount(int amount)
    {
        this.amount = amount;
    }


    public String getCurrency()
    {
        return currency;
    }


    public void setCurrency(String currency)
    {
        this.currency = currency;
    }


    @Override
    public String toString()
    {
        return "Bonus{" +
                       "pnum='" + pnum + '\'' +
                       ", paymentDate=" + paymentDate +
                       ", amount=" + amount +
                       ", currency='" + currency + '\'' +
                       '}';
    }
}
