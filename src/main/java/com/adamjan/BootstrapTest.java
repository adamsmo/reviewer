package com.adamjan;

import com.adamjan.business.RolesBusiness;
import com.adamjan.dto.RolesDto;
import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
@Component
public class BootstrapTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${app.performBootstrapTest}")
    private boolean bootTest;

    @Autowired
    private RolesBusiness rolesBusiness;

    /**
     * method that perform self test of application
     */
    @PostConstruct
    public void bootstrapTest() {
        if (!bootTest) {
            return;
        }

        //check if all roles present in database are available in application
        List<RolesDto> all = rolesBusiness.getAll();
        FastList.newList(all).forEach(new Procedure<RolesDto>() {
            @Override
            public void value(RolesDto each) {
                if (!SystemRoles.getAllSystemRoles().contains(each.getName())) {
                    printErrorMsg(String.format("Role %s is present in database but is not present in system", each.getName()));
                }
            }
        });

        //check rest
    }

    private void printErrorMsg(String msg) {
        logger.error("\n");
        logger.error("!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!");
        logger.error(String.format("!!!!ERROR!!!!  %s  !!!!ERROR!!!!", msg));
        logger.error("!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!ERROR!!!!");
        logger.error("\n");
    }
}
