/**
* @author rodrigo
*/

package edu.ifrs.tpack.data;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import edu.ifrs.tpack.model.Session;

/**
* Comentário sobre a classe
*/
public class DAOSession extends Repository<Session> {

    /**
     * Returns the first session object/register
     * 
     * @return A Session object
     */
    public Session getFirstSession() {

        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Session> criteria = builder.createQuery(Session.class);
        final Root<Session> root = criteria.from(Session.class);
        // Order by id
        criteria.orderBy(builder.asc(root.get("id")));
        // Get the first register
        Query query = em.createQuery(criteria).setMaxResults(1);
        return (Session) query.getResultList().get(0);
    }

}