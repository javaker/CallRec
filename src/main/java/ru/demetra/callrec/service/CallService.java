package ru.demetra.callrec.service;

import ru.demetra.callrec.model.Call;
import ru.demetra.callrec.model.User;

import java.util.Date;
import java.util.List;

/**
 * Service class for {@link Call}
 *
 * @author Vyacheslav Y.
 * @version 1.0
 */
public interface CallService {


    List<Call> listCallsDemetra(int firstIndex, Date beginDate, Date endDate);

    List<Call> listCallsTrapeza(int firstIndex, Date beginDate, Date endDate);

    List<Call> listFilter(Date beginDate, Date endDate, String criteria, String critValue, int firstIndex, User user);

}

