import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Main Program Driver
public class Driver
{

	public static void main(String[] args) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter("Averages.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("First Come First Serve");
		/*
		 * First Come First Serve Scheduler
		 */
		FCFSScheduler fcfs1 = new FCFSScheduler("testdata1.txt", "firstComeFirstServe_testdata1.csv");
		fcfs1.initProcesses();
		fcfs1.schedule();
		sb.append("\nTest Data 1, " + String.valueOf(fcfs1.getAvgCPU()));
		
		FCFSScheduler fcfs2 = new FCFSScheduler("testdata2.txt", "firstComeFirstServe_testdata2.csv");
		fcfs2.initProcesses();
		fcfs2.schedule();
		sb.append("\nTest Data 2," + String.valueOf(fcfs2.getAvgCPU()));
		
		FCFSScheduler fcfs3 = new FCFSScheduler("testdata3.txt", "firstComeFirstServe_testdata3.csv");
		fcfs3.initProcesses();
		fcfs3.schedule();
		sb.append("\nTest Data 3," + String.valueOf(fcfs3.getAvgCPU()));

		FCFSScheduler fcfs4 = new FCFSScheduler("testdata4.txt", "firstComeFirstServe_testdata4.csv");
		fcfs4.initProcesses();
		fcfs4.schedule();
		sb.append("\nTest Data 4," + String.valueOf(fcfs4.getAvgCPU()));

		
		/*
		 * Shortest Job First Scheduler
		 */
		sb.append("\n\nShortest Job First");
		ShortestJobFirstScheduler sjf1 = new ShortestJobFirstScheduler("testdata1.txt", "shortestJobFirst_testdata1.csv");
		sjf1.initProcesses();
		sjf1.schedule();
		sb.append("\nTest Data 1," + String.valueOf(sjf1.getAvgCPU()));
		
		ShortestJobFirstScheduler sjf2 = new ShortestJobFirstScheduler("testdata2.txt", "shortestJobFirst_testdata2.csv");
		sjf2.initProcesses();
		sjf2.schedule();
		sb.append("\nTest Data 2," + String.valueOf(sjf2.getAvgCPU()));
		
		ShortestJobFirstScheduler sjf3 = new ShortestJobFirstScheduler("testdata3.txt", "shortestJobFirst_testdata3.csv");
		sjf3.initProcesses();
		sjf3.schedule();
		sb.append("\nTest Data 3," + String.valueOf(sjf3.getAvgCPU()));
		
		ShortestJobFirstScheduler sjf4 = new ShortestJobFirstScheduler("testdata4.txt", "shortestJobFirst_testdata4.csv");
		sjf4.initProcesses();
		sjf4.schedule();
		sb.append("\nTest Data 4," + String.valueOf(sjf4.getAvgCPU()));
		
		
		/*
		 * Round Robbin Scheduler (Time Quantum = 25)
		 */
		sb.append("\n\nRound Robbin Time Quantum = 25");
		RoundRobbinScheduler rb25Data1 = new RoundRobbinScheduler("testdata1.txt", 25, "roundrobbin_tq25_testdata1.csv");
		rb25Data1.initProcesses();
		rb25Data1.scheduleProcess();
		sb.append("\nTest Data 1," + String.valueOf(rb25Data1.getAvgTurnAround()));

		RoundRobbinScheduler rb25Data2 = new RoundRobbinScheduler("testdata2.txt", 25,"roundrobbin_tq25_testdata2.csv");
		rb25Data2.initProcesses();
		rb25Data2.scheduleProcess();
		sb.append("\nTest Data 2," + String.valueOf(rb25Data2.getAvgTurnAround()));

		RoundRobbinScheduler rb25Data3 = new RoundRobbinScheduler("testdata3.txt", 25,"roundrobbin_tq25_testdata3.csv");
		rb25Data3.initProcesses();
		rb25Data3.scheduleProcess();
		sb.append("\nTest Data 3," + String.valueOf(rb25Data3.getAvgTurnAround()));

		RoundRobbinScheduler rb25Data4 = new RoundRobbinScheduler("testdata4.txt", 25,"roundrobbin_tq25_testdata4.csv");
		rb25Data4.initProcesses();
		rb25Data4.scheduleProcess();
		sb.append("\nTest Data 4," + String.valueOf(rb25Data4.getAvgTurnAround()));

		
		/*
		 * Round Robbin Scheduler (Time Quantum = 50)
		 */
		sb.append("\n\nRound Robbin Time Quantum = 50");
		RoundRobbinScheduler rb50Data1 = new RoundRobbinScheduler("testdata1.txt", 50,"roundrobbin_tq50_testdata1.csv");
		rb50Data1.initProcesses();
		rb50Data1.scheduleProcess();
		sb.append("\nTest Data 1," + String.valueOf(rb50Data1.getAvgTurnAround() + ','));

		RoundRobbinScheduler rb50Data2 = new RoundRobbinScheduler("testdata2.txt", 50,"roundrobbin_tq50_testdata2.csv");
		rb50Data2.initProcesses();
		rb50Data2.scheduleProcess();
		sb.append("\nTest Data 2," + String.valueOf(rb50Data2.getAvgTurnAround() + ','));

		RoundRobbinScheduler rb50Data3 = new RoundRobbinScheduler("testdata3.txt", 50,"roundrobbin_tq50_testdata3.csv");
		rb50Data3.initProcesses();
		rb50Data3.scheduleProcess();
		sb.append("\nTest Data 3," + String.valueOf(rb50Data3.getAvgTurnAround() + ','));

		RoundRobbinScheduler rb50Data4 = new RoundRobbinScheduler("testdata4.txt", 50,"roundrobbin_tq50_testdata4.csv");
		rb50Data4.initProcesses();
		rb50Data4.scheduleProcess();
		sb.append("\nTest Data 4," + String.valueOf(rb50Data4.getAvgTurnAround() + ','));


		/*
		 * Lottery Scheduler
		 */
		sb.append("\n\nLottery Scheduler");
		LotteryScheduler ls50Data1 = new LotteryScheduler("testdata1.txt", 50, "lotteryScheduler_testdata1.csv");
		ls50Data1.initProcesses();
		ls50Data1.scheduleProcess();
		sb.append("\nTest Data 1," + String.valueOf(ls50Data1.getAvgTurnAround()));

		LotteryScheduler ls50Data2 = new LotteryScheduler("testdata2.txt", 50, "lotteryScheduler_testdata2.csv");
		ls50Data2.initProcesses();
		ls50Data2.scheduleProcess();
		sb.append("\nTest Data 2," + String.valueOf(ls50Data2.getAvgTurnAround()));

		LotteryScheduler ls50Data3 = new LotteryScheduler("testdata3.txt", 50, "lotteryScheduler_testdata3.csv");
		ls50Data3.initProcesses();
		ls50Data3.scheduleProcess();
		sb.append("\nTest Data 3," + String.valueOf(ls50Data3.getAvgTurnAround()));

		LotteryScheduler ls50Data4 = new LotteryScheduler("testdata4.txt", 50, "lotteryScheduler_testdata4.csv");
		ls50Data4.initProcesses();
		ls50Data4.scheduleProcess();
		sb.append("\nTest Data 4," + String.valueOf(ls50Data4.getAvgTurnAround()));

		writer.write(sb.toString());
		writer.close();
		System.out.print("Program terminated. Please check local directory for all .csv output files");
		
	}

}
