package com.adamjan.business;

import com.adamjan.dto.AccountDto;
import com.adamjan.model.AccountModel;
import com.gs.collections.impl.list.mutable.FastList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
@Transactional(isolation = Isolation.SERIALIZABLE)
public class AccountBusiness extends AbstractBusiness {

    public List<AccountDto> getAllAccounts() {
        TCriteria<AccountModel> criteria = getCriteriaForClass(AccountModel.class);
        FastList<AccountModel> list = FastList.newList(criteria.list());
        Collection<AccountDto> collection = list.collect(getConvertingFunction(AccountDto.class));
        return new ArrayList<>(collection);
    }

    public void addAccount(String accName) {
        Random random = new Random(new Date().getTime());
        TCriteria<AccountModel> criteria = getCriteriaForClass(AccountModel.class);

        Integer maxId = (Integer) criteria.getCriteria()
                .setProjection(Projections.max("id")).uniqueResult();

        AccountModel a = new AccountModel();
        a.setId(maxId + 1);
        a.setName(accName);
        a.setPassword(String.valueOf(random.nextDouble()));
        getSession().saveOrUpdate(a);
    }
}
