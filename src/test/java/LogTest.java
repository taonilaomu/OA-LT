import dto.LoginDto;
import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * author  luhongtao
 * 2022/7/24 16:14:45
 **/
public class LogTest {

    @Test
    public void test() {
        Logger logger = Logger.getLogger(LoginDto.class);
        logger.info("kaixin");
    }
}
