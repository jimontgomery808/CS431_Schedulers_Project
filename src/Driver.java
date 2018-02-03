import java.io.IOException;

// Main Program Driver
public class Driver
{

	public static void main(String[] args) throws IOException
	{
		/*
		 * First Come First Serve Scheduler
		 */
		FCFSScheduler fcfs1 = new FCFSScheduler("testdata1.txt", "firstComeFirstServe_testdata1.csv");
		fcfs1.initProcesses();
		fcfs1.schedule();
		
		FCFSScheduler fcfs2 = new FCFSScheduler("testdata2.txt", "firstComeFirstServe_testdata2.csv");
		fcfs2.initProcesses();
		fcfs2.schedule();
		
		FCFSScheduler fcfs3 = new FCFSScheduler("testdata3.txt", "firstComeFirstServe_testdata3.csv");
		fcfs3.initProcesses();
		fcfs3.schedule();
		
		
		/*
		 * Shortest Job First Scheduler
		 */
		ShortestJobFirstScheduler sjf1 = new ShortestJobFirstScheduler("testdata1.txt", "shortestJobFirst_testdata1.csv");
		sjf1.initProcesses();
		sjf1.schedule();
		
		ShortestJobFirstScheduler sjf2 = new ShortestJobFirstScheduler("testdata2.txt", "shortestJobFirst_testdata2.csv");
		sjf2.initProcesses();
		sjf2.schedule();
		
		ShortestJobFirstScheduler sjf3 = new ShortestJobFirstScheduler("testdata3.txt", "shortestJobFirst_testdata3.csv");
		sjf3.initProcesses();
		sjf3.schedule();
		
		ShortestJobFirstScheduler sjf4 = new ShortestJobFirstScheduler("testdata4.txt", "shortestJobFirst_testdata4.csv");
		sjf4.initProcesses();
		sjf4.schedule();
		
		
		/*
		 * Round Robbin Scheduler (Time Quantum = 25)
		 */
		RoundRobbinScheduler rb25Data1 = new RoundRobbinScheduler("testdata1.txt", 25, "roundrobbin_tq25_testdata1.csv");
		rb25Data1.initProcesses();
		rb25Data1.scheduleProcess();
		
		RoundRobbinScheduler rb25Data2 = new RoundRobbinScheduler("testdata2.txt", 25,"roundrobbin_tq25_testdata2.csv");
		rb25Data2.initProcesses();
		rb25Data2.scheduleProcess();
		
		RoundRobbinScheduler rb25Data3 = new RoundRobbinScheduler("testdata3.txt", 25,"roundrobbin_tq25_testdata3.csv");
		rb25Data3.initProcesses();
		rb25Data3.scheduleProcess();
		
		RoundRobbinScheduler rb25Data4 = new RoundRobbinScheduler("testdata4.txt", 25,"roundrobbin_tq25_testdata4.csv");
		rb25Data4.initProcesses();
		rb25Data4.scheduleProcess();
		
		
		/*
		 * Round Robbin Scheduler (Time Quantum = 50)
		 */
		RoundRobbinScheduler rb50Data1 = new RoundRobbinScheduler("testdata1.txt", 50,"roundrobbin_tq50_testdata1.csv");
		rb50Data1.initProcesses();
		rb50Data1.scheduleProcess();
		
		RoundRobbinScheduler rb50Data2 = new RoundRobbinScheduler("testdata2.txt", 50,"roundrobbin_tq50_testdata2.csv");
		rb50Data2.initProcesses();
		rb50Data2.scheduleProcess();
		
		RoundRobbinScheduler rb50Data3 = new RoundRobbinScheduler("testdata3.txt", 50,"roundrobbin_tq50_testdata3.csv");
		rb50Data3.initProcesses();
		rb50Data3.scheduleProcess();
		
		RoundRobbinScheduler rb50Data4 = new RoundRobbinScheduler("testdata4.txt", 50,"roundrobbin_tq50_testdata4.csv");
		rb50Data4.initProcesses();
		rb50Data4.scheduleProcess();
		

		/*
		 * Lottery Scheduler
		 */
		LotteryScheduler ls50Data1 = new LotteryScheduler("testdata1.txt", 50, "lotteryScheduler_testdata1.csv");
		ls50Data1.initProcesses();
		ls50Data1.scheduleProcess();
		
		LotteryScheduler ls50Data2 = new LotteryScheduler("testdata2.txt", 50, "lotteryScheduler_testdata2.csv");
		ls50Data2.initProcesses();
		ls50Data2.scheduleProcess();
		
		LotteryScheduler ls50Data3 = new LotteryScheduler("testdata3.txt", 50, "lotteryScheduler_testdata3.csv");
		ls50Data3.initProcesses();
		ls50Data3.scheduleProcess();
		
		LotteryScheduler ls50Data4 = new LotteryScheduler("testdata4.txt", 50, "lotteryScheduler_testdata4.csv");
		ls50Data4.initProcesses();
		ls50Data4.scheduleProcess();
	
		System.out.print("Program terminated. Please check local directory for all .csv output files");
		
	}

}
