package com.adamjan.business;


import org.apache.wicket.util.crypt.Base64;
import org.apache.wicket.util.crypt.CharEncoding;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
@Component
public class TokenFactory {
    public static final String DELIMITER = "#";
    private String key;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${app.tokenValidityTime:60}")
    private int tokenValidityTime;

    @PostConstruct
    public void init() {
        Security.addProvider(new BouncyCastleProvider());
        SecureRandom random = new SecureRandom();
        byte[] bytes = random.generateSeed(50);
        key = Base64.encodeBase64String(bytes);
    }

    public String sign(String msg) {
        long time = new Date().getTime();

        String signatureSource = msg + key + time;
        String signature = getHash(signatureSource);

        return new StringBuilder()
                .append(msg)
                .append(DELIMITER)
                .append(time)
                .append(DELIMITER)
                .append(signature)
                .toString();
    }

    public String getMsg(String signedMsg) {
        if (!check(signedMsg)) {
            return null;
        }
        String[] split = StringUtils.split(signedMsg, DELIMITER);
        return split[0];
    }

    public boolean check(String signedMsg) {
        String[] split = StringUtils.split(signedMsg, DELIMITER);
        if (split != null && split.length == 3) {
            String msg = split[0];
            String time = split[1];
            String signature = split[2];
            String signatureSource = msg + key + time;
            String generatedSignature = getHash(signatureSource);

            long l;
            try {
                l = Long.parseLong(time);
            } catch (NumberFormatException e) {
                return false;
            }

            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.SECOND, -tokenValidityTime);

            return generatedSignature.equals(signature) && new Date(l).after(cal.getTime());
        }

        return false;
    }

    private String getHash(String signatureSource) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(signatureSource.getBytes(CharEncoding.UTF_8));
            byte[] digest = md.digest();
            return Base64.encodeBase64String(digest);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("java environment incompatible", e);
        }

        return null;
    }
}
