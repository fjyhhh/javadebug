package com.joonwhee.study.debug;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: 程序员囧辉
 * @date: 2022/4/23 11:10
 */
public class DebugServiceFactory {

    private final DebugService advanceDebugService = new AdvanceDebugServiceImpl();

    private final DebugService generalDebugService = new GeneralDebugServiceImpl();

    public DebugService getDebugService() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return advanceDebugService;
        } else {
            return generalDebugService;
        }
    }
}
