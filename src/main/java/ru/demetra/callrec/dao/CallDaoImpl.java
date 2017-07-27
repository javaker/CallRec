package ru.demetra.callrec.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.demetra.callrec.model.Call;
import ru.demetra.callrec.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vyacheslav Y.
 * @version 2.0
 */
@Repository
public class CallDaoImpl implements CallDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Org Demetra
     * Complete list from MySQL DB
     */

    @SuppressWarnings("unchecked")
    public List<Call> listCallsDemetra(int firstIndex, Date beginDate, Date endDate) {

        Session session = this.sessionFactory.getCurrentSession();

        String hql = "from Call call where (call.src='6801' or call.src='6702') and call.callDate between :beginDate and :endDate order by call.callDate";

        Query result = session.createQuery(hql)
                .setParameter("beginDate", beginDate)
                .setParameter("endDate", endDate);
        result.setFirstResult(firstIndex);
        result.setMaxResults(20);

        List<Call> tmpList = result.getResultList();

        System.out.println("Demetra list");

        return result.getResultList();
    }

    /**
     * Org Trapeza
     * Complete list from MySQL DB
     */

    @SuppressWarnings("unchecked")
    public List<Call> listCallsTrapeza(int firstIndex, Date beginDate, Date endDate) {

        Session session = this.sessionFactory.getCurrentSession();


        String hql = "from Call call where (call.src!='6801' and call.src!='6702') and call.callDate between :beginDate and :endDate  order by call.callDate"; //(call.src!='6602' or call.src!='6606')


        Query result = session.createQuery(hql)
                .setParameter("beginDate", beginDate)
                .setParameter("endDate", endDate);
        result.setFirstResult(firstIndex);
        result.setMaxResults(20);
        return result.getResultList();
    }

    /**
     * Complete list from MySQL DB with filter condition
     * <p>
     * Also restrict search among Demetra & Trapeza
     * Demetra extensions 6801/6702
     */

    @SuppressWarnings("unchecked")
    public List<Call> listFilter(Date beginDate, Date endDate, String criteria, String critValue, int firstIndex, User user) {

        if (user.getLogin().equals("Trapeza") && ((critValue.equals("6801") || (critValue.equals("6702"))))) {
            return errorList();
        }
        if (user.getLogin().equals("Demetra") && (!critValue.equals("6801") && !critValue.equals("6702"))) {
            return errorList();
        }

        Session session = this.sessionFactory.getCurrentSession();

        if (criteria.equals("src")) {

            String hql = "from Call call where call.src =:callSrc and call.callDate between :beginDate and :endDate order by call.callDate";

            Query result = session.createQuery(hql)
                    .setParameter("beginDate", beginDate)
                    .setParameter("endDate", endDate)
                    .setParameter("callSrc", critValue);
            result.setFirstResult(0);
            result.setMaxResults(20);
            return result.getResultList();
        }

        if (criteria.equals("dst")) {
            String hql = "from Call call where call.dst =:callDst and call.callDate between :beginDate and :endDate order by call.callDate";

            Query result = session.createQuery(hql)
                    .setParameter("beginDate", beginDate)
                    .setParameter("endDate", endDate)
                    .setParameter("callDst", critValue);
            result.setFirstResult(0);
            result.setMaxResults(20);

            return result.getResultList();
        }

        String hql = "from Call call where call.callDate between :beginDate and :endDate  order by call.callDate";

        Query result = session.createQuery(hql)
                .setParameter("beginDate", beginDate)
                .setParameter("endDate", endDate);
        result.setFirstResult(firstIndex);
        result.setMaxResults(20);
        System.out.println("Filter, no crit");
        return result.getResultList();
    }

    List<Call> errorList() {
        List<Call> listError = new ArrayList<>();
        Call callError = new Call();
        callError.setCallDate("1996-01-23 00:00");  //Date release Java 1.0 :)
        callError.setSrc("Доступ запрещен");
        listError.add(callError);
        return listError;
    }
}





