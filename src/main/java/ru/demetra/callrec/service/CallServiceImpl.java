package ru.demetra.callrec.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demetra.callrec.dao.CallDao;
import ru.demetra.callrec.model.Call;
import ru.demetra.callrec.model.User;

import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link CallService} interface.
 * @author Vyacheslav Y.
 * @version 1.0
 */
@Service
public class CallServiceImpl implements CallService {

    private CallDao callDao;

    public void setCallDao(CallDao callDao){
        this.callDao = callDao;
    }



    @Transactional
    public List<Call> listCallsDemetra(int firstIndex, Date beginDate, Date endDate) {
        return this.callDao.listCallsDemetra(firstIndex, beginDate,endDate );
    }


    @Transactional
    public List<Call> listCallsTrapeza(int firstIndex, Date beginDate, Date endDate) {
        return this.callDao.listCallsTrapeza(firstIndex, beginDate,endDate );
    }


    @Transactional
    public List<Call> listFilter(Date beginDate, Date endDate, String criteria, String critValue, int firstIndex, User user) {
        return this.callDao.listFilter(beginDate, endDate, criteria, critValue, firstIndex, user);
    }
}
