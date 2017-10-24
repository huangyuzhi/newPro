package support;

import com.chains.pwqxfwjk.util.SpringContext;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class Support {
	protected ApplicationContext context = SpringContext.context();
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected void consoleInfo(Object object) {
		logger.info(JSONHelper.parseObject(object));
	}
}
