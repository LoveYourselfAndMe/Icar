/*
 * Copyright (C) 2011-2015 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.greenrobot.dao.query;

import de.greenrobot.dao.AbstractDao;

import java.util.Date;

/**
 * Base class for queries returning data (entities or cursor).
 *
 * @param <T> The entity class the query will return results for.
 * @author Markus
 */
// TODO Query for PKs/ROW IDs
abstract class AbstractQueryWithLimit<T> extends AbstractQuery<T> {
    protected final int limitPosition;
    protected final int offsetPosition;

    protected AbstractQueryWithLimit(AbstractDao<T, ?> dao, String sql, String[] initialValues, int limitPosition,
                                     int offsetPosition) {
        super(dao, sql, initialValues);
        this.limitPosition = limitPosition;
        this.offsetPosition = offsetPosition;
    }

    /**
     * Sets the parameter (0 based) using the position in which it was added during building the query. Note: all
     * standard WHERE parameters come first. After that come the WHERE parameters of joins (if any).
     */
    public AbstractQueryWithLimit<T> setParameter(int index, Object parameter) {
        if (index >= 0 && (index == limitPosition || index == offsetPosition)) {
            throw new IllegalArgumentException("Illegal parameter index: " + index);
        }
        return (AbstractQueryWithLimit<T>) super.setParameter(index, parameter);
    }

    public AbstractQueryWithLimit<T> setParameter(int index, Date parameter) {
        Long converted = parameter != null ? parameter.getTime() : null;
        return setParameter(index, converted);
    }

    public AbstractQueryWithLimit<T> setParameter(int index, Boolean parameter) {
        Integer converted = parameter != null ? (parameter ? 1 : 0) : null;
        return setParameter(index, converted);
    }

    /**
     * Sets the limit of the maximum number of results returned by this Query. {@link
     * QueryBuilder#limit(int)} must
     * have been called on the QueryBuilder that created this Query object.
     */
    public void setLimit(int limit) {
        checkThread();
        if (limitPosition == -1) {
            throw new IllegalStateException("Limit must be set with QueryBuilder before it can be used here");
        }
        parameters[limitPosition] = Integer.toString(limit);
    }

    /**
     * Sets the offset for results returned by this Query. {@link QueryBuilder#offset(int)} must
     * have been called on
     * the QueryBuilder that created this Query object.
     */
    public void setOffset(int offset) {
        checkThread();
        if (offsetPosition == -1) {
            throw new IllegalStateException("Offset must be set with QueryBuilder before it can be used here");
        }
        parameters[offsetPosition] = Integer.toString(offset);
    }

}
