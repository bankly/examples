package com.fw.spring;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public class FwHibernateTemplate extends HibernateTemplate {
    private int firstResult = 0;

    /**
     * Create a new IRPHibernateTemplate instance.
     */
    public FwHibernateTemplate() {
    }

    /**
     * Create a new IRPHibernateTemplate instance.
     * 
     * @param sessionFactory
     *            SessionFactory to create Sessions
     */
    public FwHibernateTemplate(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Create a new IRPHibernateTemplate instance.
     * 
     * @param sessionFactory
     *            SessionFactory to create Sessions
     * @param allowCreate
     *            if a non-transactional Session should be created when no
     *            transactional Session can be found for the current thread
     */
    public FwHibernateTemplate(SessionFactory sessionFactory,
            boolean allowCreate) {
        super(sessionFactory, allowCreate);
    }

    /**
     * Return the first number of rows specified for this HibernateTemplate.
     */
    public int getFirstResult() {
        return firstResult;
    }

    /**
     * Set the first row to retrieve. If not set, rows will be retrieved
     * beginnning from row <tt>0</tt>.
     * 
     * @param firstResult
     *            a row number, numbered from <tt>0</tt>
     */
    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int executeNativeSQL(final String sql, final String paramName,
            final Object value) {
        return executeNativeSQL(sql, new String[] { paramName },
                new Object[] { value });
    }

    public int executeNativeSQL(final String sql, final String[] paramNames,
            final Object[] values) {
        if (paramNames != null && values != null
                && paramNames.length != values.length) {
            throw new IllegalArgumentException(
                    "Length of paramNames array must match length of values array");
        }

        Integer updateCount = (Integer) execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query queryObject = session.createSQLQuery(sql);
                prepareQuery(queryObject);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        applyNamedParameterToQuery(queryObject, paramNames[i],
                                values[i]);
                    }
                }
                return new Integer(queryObject.executeUpdate());
            }
        }, true);
        return updateCount.intValue();
    }

    public List findByNativeSQL(String sql, String paramName, Object value) {
        return findByNativeSQL(sql, new String[] { paramName },
                new Object[] { value });
    }

    public List findByNativeSQL(final String sql, final String[] paramNames,
            final Object[] values) {
        if (paramNames != null && values != null
                && paramNames.length != values.length) {
            throw new IllegalArgumentException(
                    "Length of paramNames array must match length of values array");
        }

        return (List) execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query queryObject = session.createSQLQuery(sql);
                prepareQuery(queryObject);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        applyNamedParameterToQuery(queryObject, paramNames[i],
                                values[i]);
                    }
                }
                return queryObject.list();
            }
        }, true);
    }

    /**
     * Prepare the given Query object, applying cache settings and/or a
     * transaction timeout.
     * 
     * @param queryObject
     *            the Query object to prepare
     */
    @Override
    protected void prepareQuery(Query queryObject) {
        if (isCacheQueries()) {
            queryObject.setCacheable(true);
            if (getQueryCacheRegion() != null) {
                queryObject.setCacheRegion(getQueryCacheRegion());
            }
        }
        if (getFetchSize() > 0) {
            queryObject.setFetchSize(getFetchSize());
        }
        if (getMaxResults() > 0) {
            queryObject.setMaxResults(getMaxResults());
        }
        if (getFirstResult() >= 0) {
            queryObject.setFirstResult(getFirstResult());
        }
        SessionFactoryUtils.applyTransactionTimeout(queryObject,
                getSessionFactory());
    }

    /**
     * Prepare the given Criteria object, applying cache settings and/or a
     * transaction timeout.
     * 
     * @param criteria
     *            the Criteria object to prepare
     * @see #setCacheQueries
     * @see #setQueryCacheRegion
     * @see SessionFactoryUtils#applyTransactionTimeout
     */
    @Override
    protected void prepareCriteria(Criteria criteria) {
        if (isCacheQueries()) {
            criteria.setCacheable(true);
            if (getQueryCacheRegion() != null) {
                criteria.setCacheRegion(getQueryCacheRegion());
            }
        }
        if (getFetchSize() > 0) {
            criteria.setFetchSize(getFetchSize());
        }
        if (getMaxResults() > 0) {
            criteria.setMaxResults(getMaxResults());
        }
        if (getFirstResult() >= 0) {
            criteria.setFirstResult(getFirstResult());
        }
        SessionFactoryUtils.applyTransactionTimeout(criteria,
                getSessionFactory());
    }
}
