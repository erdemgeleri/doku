

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
    private static final Logger logger = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.trace("Bu bir trace mesajıdır");
        logger.debug("Bu bir debug mesajıdır");
        logger.info("Bu bir info mesajıdır");
        logger.warn("Bu bir warn mesajıdır");
        logger.error("Bu bir error mesajıdır");
        logger.fatal("Bu bir fatal mesajıdır");
    }
}
