package com.adamjan.pages;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Bytes;

import java.util.ArrayList;
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
public class AddArticleForm extends TemplatePage {
    private static final List<String> topics = Arrays.asList("Biomdeical Engineering",
            "Chemistry and Pharmacy", "Civil Engineering");

    private ArrayList<String> topicSelect = new ArrayList<>();
    private FileUploadField fileUpload;

    public AddArticleForm() {
        add(new FeedbackPanel("feedback"));

        Form<?> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                FileUpload uploadedFile = fileUpload.getFileUpload();
                byte[] fileContent = null;
                if (uploadedFile != null) {
                    fileContent = uploadedFile.getBytes();
                }
            }
        };

        form.setMultiPart(true);
        form.setMaxSize(Bytes.megabytes(3));

        form.add(new CheckBoxMultipleChoice<>("topics", new Model<>(topicSelect), topics));
        form.add(new TextField<>("keywords"));
        form.add(new TextField<>("author"));
        form.add(new TextField<>("tittle"));
        form.add(new TextArea<>("summary"));
        form.add(new TextArea<>("comment"));
        form.add(fileUpload = new FileUploadField("fileUpload"));
        add(form);
    }
}
