import java.io.IOException;

public class Driver
{

	public static void main(String[] args) throws IOException
	{
		LotteryScheduler ls50Data1 = new LotteryScheduler("testdata1.txt", 50, "lotteryScheduler_testdata1.csv");
		LotteryScheduler ls50Data2 = new LotteryScheduler("testdata2.txt", 50, "lotteryScheduler_testdata2.csv");
		LotteryScheduler ls50Data3 = new LotteryScheduler("testdata3.txt", 50, "lotteryScheduler_testdata3.csv");
		LotteryScheduler ls50Data4 = new LotteryScheduler("testdata4.txt", 50, "lotteryScheduler_testdata4.csv");
		
		System.out.println("Lotery Scheduler Time Quantum 50 Using testdata1");
		System.out.println("-------------------------------------------------");
		ls50Data1.initProcesses();
		ls50Data1.scheduleProcess();
		
		System.out.println("\n\nLotery Scheduler Time Quantum 50 Using testdata2");
		System.out.println("-------------------------------------------------");
		ls50Data2.initProcesses();
		ls50Data2.scheduleProcess();
		
		System.out.println("\n\nLotery Scheduler Time Quantum 50 Using testdata3");
		System.out.println("-------------------------------------------------");
		ls50Data3.initProcesses();
		ls50Data3.scheduleProcess();
		
		System.out.println("\n\nLotery Scheduler Time Quantum 50 Using testdata4");
		System.out.println("-------------------------------------------------");
		ls50Data4.initProcesses();
		ls50Data4.scheduleProcess();
		

		
		System.out.println("\n\nRound Robbin Scheduler Time Quantum 25 Using testdata1");
		System.out.println("-------------------------------------------------");
		RoundRobbinScheduler rb25Data1 = new RoundRobbinScheduler("testdata1.txt", 25, "roundrobbin_tq25_testdata1.csv");
		rb25Data1.initProcesses();
		rb25Data1.scheduleProcess();
		
		System.out.println("\n\nRound Robbin Scheduler Time Quantum 25 Using testdata2");
		System.out.println("-------------------------------------------------");
		RoundRobbinScheduler rb25Data2 = new RoundRobbinScheduler("testdata2.txt", 25,"roundrobbin_tq25_testdata2.csv");
		rb25Data2.initProcesses();
		rb25Data2.scheduleProcess();
		
		System.out.println("\n\nRound Robbin Scheduler Time Quantum 25 Using testdata3");
		System.out.println("-------------------------------------------------");
		RoundRobbinScheduler rb25Data3 = new RoundRobbinScheduler("testdata3.txt", 25,"roundrobbin_tq25_testdata3.csv");
		rb25Data3.initProcesses();
		rb25Data3.scheduleProcess();
		
		System.out.println("\n\nRound Robbin Scheduler Time Quantum 25 Using testdata4");
		System.out.println("-------------------------------------------------");
		RoundRobbinScheduler rb25Data4 = new RoundRobbinScheduler("testdata4.txt", 25,"roundrobbin_tq25_testdata4.csv");
		rb25Data4.initProcesses();
		rb25Data4.scheduleProcess();
		
		
		System.out.println("\n\nRound Robbin Scheduler Time Quantum 50 Using testdata1");
		System.out.println("-------------------------------------------------");
		RoundRobbinScheduler rb50Data1 = new RoundRobbinScheduler("testdata1.txt", 50,"roundrobbin_tq50_testdata1.csv");
		rb50Data1.initProcesses();
		rb50Data1.scheduleProcess();
		
		System.out.println("\n\nRound Robbin Scheduler Time Quantum 50 Using testdata2");
		System.out.println("-------------------------------------------------");
		RoundRobbinScheduler rb50Data2 = new RoundRobbinScheduler("testdata2.txt", 50,"roundrobbin_tq50_testdata2.csv");
		rb50Data2.initProcesses();
		rb50Data2.scheduleProcess();
		
		System.out.println("\n\nRound Robbin Scheduler Time Quantum 50 Using testdata3");
		System.out.println("-------------------------------------------------");
		RoundRobbinScheduler rb50Data3 = new RoundRobbinScheduler("testdata3.txt", 50,"roundrobbin_tq50_testdata3.csv");
		rb50Data3.initProcesses();
		rb50Data3.scheduleProcess();
		
		System.out.println("\n\nRound Robbin Scheduler Time Quantum 50 Using testdata4");
		System.out.println("-------------------------------------------------");
		RoundRobbinScheduler rb50Data4 = new RoundRobbinScheduler("testdata4.txt", 50,"roundrobbin_tq50_testdata4.csv");
		rb50Data4.initProcesses();
		rb50Data4.scheduleProcess();
		
	}

}
