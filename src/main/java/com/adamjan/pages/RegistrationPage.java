package com.adamjan.pages;

import com.adamjan.business.AccountBusiness;
import com.adamjan.business.MailSender;
import com.adamjan.model.AccountModel;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;

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
public class RegistrationPage extends TemplatePage {
    @SpringBean
    private AccountBusiness accountBusiness;

    @SpringBean
    private MailSender mailSender;

    private boolean dr;
    private boolean hab;
    private boolean inz;
    private boolean mgr;
    private boolean mrs;
    private boolean ms;
    private boolean prof;

    private String userName;
    private String password;

    private String organization;
    private String departament;
    private String occupation;
    private String name;
    private String surname;
    private String addressFirstLine;
    private String addressSecondLine;
    private String zipCode;
    private String city;
    private String province;
    private String country;
    private String telephone;
    private String fax;
    private String email;
    private String hompage;
    private String additionalComment;

    private static final List<String> countries = Arrays.asList( "Polska", "Irlandia", "Wielka Brytania", "Norwegia", "Szwecja", "Finlandia", "Portugalia", "Hiszpania", "Andora", "Francja", "Holandia", "Belgia", "Luxemburg", "Niemcy", "Dania", "Szwajcaria", "Lichtenstein", "Włochy", "Monako", "San Marino", "Watykan", "Czechy", "Słowacja", "Austria", "Węgry", "Słowenia", "Chorwacja", "Bośnia i Hercegowina", "Serbia i Czarnogóra", "Rumunia", "Mołdawia", "Albania", "Macedonia", "Bułgaria", "Grecja", "Estonia", "Łotwa", "Litwa", "Białoruś", "Ukraina", "Rosja", "Islandia", "Malta", "Cypr");
    private static final List<String> provinces = Arrays.asList("dolnośląskie", "kujawsko-pomorskie", "lubelskie", "lubuskie", "łódzkie", "małopolskie", "mazowieckie", "opolskie", "podkarpackie", "podlaskie", "pomorskie", "śląskie", "świętokrzyskie", "warmińsko-mazurskie", "wielkopolskie", "zachodniopomorskie");

    private Logger logger = LoggerFactory.getLogger(getClass());

    public RegistrationPage() {


        Form<?> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {

                String tittle = "";

                if(mrs){
                    tittle += "Pan";
                }
                if(ms){
                    tittle += "Pani";
                }
                if(prof){
                    tittle += " prof.";
                }
                if(dr){
                    tittle += " dr";
                }
                if(hab){
                    tittle += " hab.";
                }
                if(mgr){
                    tittle += " mgr";
                }
                if(inz){
                    tittle += " inż.";
                }


                AccountModel model = new AccountModel();

                model.setUserName(userName);
                model.setPassword(password);
                //model.setRoles(List<RolesModel> roles);
                model.setOrganization(organization);
                model.setDepartament(departament);
                model.setTittle(tittle);
                model.setOccupation(occupation);
                model.setName(name);
                model.setSurname(surname);
                model.setAddressFirstLine(addressFirstLine);
                model.setAddressSecondLine(addressSecondLine);
                model.setZipCode(zipCode);
                model.setCity(city);
                model.setProvince(province);
                model.setCountry(country);
                model.setTelephone(telephone);
                model.setFax(fax);
                model.setEmail(email);
                model.setHompage(hompage);
                model.setAdditionalComment(additionalComment);

                accountBusiness.saveAccount(model);

//                try {
//                    mailSender.send("Rejestracja", "Gratuluje rejestracji", email);
//                } catch (MessagingException e) {
//                    logger.error("fail to send mail", e);
//                }
            }
        };

        form.add(new CheckBox("Dr", new PropertyModel<Boolean>(this, "dr")));
        form.add(new CheckBox("Hab.", new PropertyModel<Boolean>(this, "hab")));
        form.add(new CheckBox("Inz.", new PropertyModel<Boolean>(this, "inz")));
        form.add(new CheckBox("Mgr.", new PropertyModel<Boolean>(this, "mgr")));
        form.add(new CheckBox("Mrs.", new PropertyModel<Boolean>(this, "mrs")));
        form.add(new CheckBox("Ms.", new PropertyModel<Boolean>(this, "ms")));
        form.add(new CheckBox("Prof.", new PropertyModel<Boolean>(this, "prof")));

        form.add(new TextField<>("organization", new PropertyModel<String>(this, "organization")));
        form.add(new TextField<>("departament", new PropertyModel<String>(this, "departament")));
        form.add(new TextField<>("occupation", new PropertyModel<String>(this, "occupation")));
        form.add(new TextField<>("name", new PropertyModel<String>(this, "name")));
        form.add(new TextField<>("surname", new PropertyModel<String>(this, "surname")));
        form.add(new TextField<>("addressFirstLine", new PropertyModel<String>(this, "addressFirstLine")));
        form.add(new TextField<>("addressSecondLine", new PropertyModel<String>(this, "addressSecondLine")));
        form.add(new TextField<>("zipCode", new PropertyModel<String>(this, "zipCode")));
        form.add(new TextField<>("city", new PropertyModel<String>(this, "city")));

        form.add(new DropDownChoice<>("province", new PropertyModel<String>(this, "province"), provinces));
        form.add(new DropDownChoice<>("country", new PropertyModel<String>(this, "country"), countries));

        form.add(new TextField<>("telephone", new PropertyModel<String>(this, "telephone")));
        form.add(new TextField<>("fax", new PropertyModel<String>(this, "fax")));
        form.add(new TextField<>("email", new PropertyModel<String>(this, "email")));
        form.add(new TextField<>("hompage", new PropertyModel<String>(this, "hompage")));
        form.add(new TextField<>("additionalComment", new PropertyModel<String>(this, "additionalComment")));

        form.add(new TextField<>("userName", new PropertyModel<String>(this, "userName")));
        form.add(new PasswordTextField("password", new PropertyModel<String>(this, "password")));

        add(form);
    }

}
