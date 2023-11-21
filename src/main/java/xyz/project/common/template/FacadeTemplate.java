package xyz.project.common.template;

import org.apache.logging.log4j.Logger;
import org.springframework.util.StopWatch;

import java.util.function.Supplier;

public class FacadeTemplate <T,R>{

    private final StopWatch stopWatch;
    private final Logger logger;

    public FacadeTemplate(StopWatch stopWatch, Logger logger) {
        this.stopWatch = stopWatch;
        this.logger = logger;
    }


    public R function(Supplier<R> function) {
        stopWatch.start();

        try {
            return function.get();
        } catch (Exception e) {
            logger.error("Error executing function", e);
            throw e;
        } finally {
            stopWatch.stop();
            logger.info("Execution time: {} ms", stopWatch.getTotalTimeMillis());
        }
    }

    public static class api <T,R> {
        private final StopWatch stopWatch;
        private final Logger logger;

        public api(Logger logger) {
            this.logger = logger;
            this.stopWatch = new StopWatch();
        }

        public R supply(Supplier<R> func) {
            return new FacadeTemplate<T,R>(stopWatch, logger).function(func);
        }

    }
}
