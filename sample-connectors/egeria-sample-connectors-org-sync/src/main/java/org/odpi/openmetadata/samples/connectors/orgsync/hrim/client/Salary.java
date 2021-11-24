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
public class Salary
{
    private String pnum = null;
    private Date   startDate = null;
    private Date   endDate = null;
    private int    amount = 0;
    private String currency = null;


    public Salary()
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


    public Date getStartDate()
    {
        return startDate;
    }


    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }


    public Date getEndDate()
    {
        return endDate;
    }


    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
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
        return "Salary{" +
                       "pnum='" + pnum + '\'' +
                       ", startDate=" + startDate +
                       ", endDate=" + endDate +
                       ", amount=" + amount +
                       ", currency='" + currency + '\'' +
                       '}';
    }
}
