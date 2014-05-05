package com.adamjan.common;

/**
 * The MIT License
 * <p/>
 * Copyright (c) 2013 Adam Smolarek
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
public interface Account {
    public int getId();
    public String getUserName();
    public String getOrganization();
    public String getDepartament();
    public String getTittle();
    public String getOccupation();
    public String getName();
    public String getSurname();
    public String getAddressFirstLine();
    public String getAddressSecondLine();
    public String getZipCode();
    public String getCity();
    public String getProvince();
    public String getCountry();
    public String getTelephone();
    public String getFax();
    public String getEmail();
    public String getHompage();
    public String getAdditionalComment();

    public void setId(int id);
    public void setUserName(String userName);
    public void setOrganization(String organization);
    public void setDepartament(String departament);
    public void setTittle(String tittle);
    public void setOccupation(String occupation);
    public void setName(String name);
    public void setSurname(String surname);
    public void setAddressFirstLine(String addressFirstLine);
    public void setAddressSecondLine(String addressSecondLine);
    public void setZipCode(String zipCode);
    public void setCity(String city);
    public void setProvince(String province);
    public void setCountry(String country);
    public void setTelephone(String telephone);
    public void setFax(String fax);
    public void setEmail(String email);
    public void setHompage(String hompage);
    public void setAdditionalComment(String additionalComment);
}
