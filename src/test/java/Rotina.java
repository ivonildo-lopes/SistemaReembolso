import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Rotina implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
				System.out.println("JSF 2 + quartz for example");
	}
	
	
	

}
