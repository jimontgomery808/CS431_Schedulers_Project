import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotteryScheduler
{
	private List<Process> processes;
	private int burstNumber;
	private int swap;
	private int timeQuantum;
	private String dataSource;
	private int sum;
	private boolean firstProcess;
	private int cpu;
	private int prioritiesCount;
	private String outputFile;
	
	public LotteryScheduler(String fileName, int timeQuantum, String outFile)
	{
		this.dataSource = fileName;
		processes = new ArrayList<Process>();
		swap = 3;
		this.timeQuantum = timeQuantum;
		sum = 0;
		firstProcess = true;
		cpu = 0;
		prioritiesCount = 0;
		outputFile = outFile;
	}
	
	public void initProcesses() throws IOException
	{
		File file = new File(dataSource);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		
		int id;
		int burst;
		int priority;
		
		while ((line = br.readLine()) != null)
		{
			id = Integer.valueOf(line);
			burst = Integer.valueOf(br.readLine());
			priority = Integer.valueOf(br.readLine());
			Process lp = new Process(id, burst, priority);
			processes.add(lp);
			prioritiesCount ++;
			//System.out.println(id + ", " + burst + ", " + priority);
		}
		
		generateSum();
	}
	
	public void generateSum()
	{
		for(int i = 0; i < processes.size(); i ++)
		{
			sum += processes.get(i).getPriority();
		}
	}

	
	public void scheduleProcess() throws IOException
	{
		int index = 0;
		int bursts[] = {24, 48, 16, 55, 33, 81, 48, 13};
		boolean lastProcessTerminated = false;
		StringBuilder sb = new StringBuilder();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write("CpuTime, PID, StartingBurstTime, EndingBurstTime, CompletionTime\n");
		
		while(prioritiesCount > 0)
		{
			burstNumber = randomNumber();
			//burstNumber = bursts[index];
			System.out.printf("%7d", burstNumber);
			System.out.print(" ");
			index ++;
			
			int currentPID = 0;
			int currentStartB = 0;
			int currentEndB = 0;
			int completionTime = 0;
			
			currentPID = pickProcess();
			
			if(!lastProcessTerminated)
			{
				if(!firstProcess)
				{
					if(!lastProcessTerminated)
					{
						cpu += (swap + timeQuantum);
					}
					else
					{
						cpu += completionTime + swap;
					}
				}
				else
				{
					cpu = 0;
					firstProcess = false;
				}
			}
			
			System.out.printf("%7d", cpu);
			sb.append(String.valueOf(cpu));
			sb.append(',');
			System.out.print(" ");
			System.out.printf("%7d",(currentPID + 1));
			sb.append(String.valueOf(currentPID + 1));
			sb.append(',');
			System.out.print(" ");
			currentStartB = processes.get(currentPID).getStartB();
			System.out.printf("%7d",currentStartB);
			sb.append(String.valueOf(currentStartB));
			sb.append(',');
			System.out.print(" ");

			currentEndB = currentStartB - timeQuantum;
			if(currentEndB < 0)
			{
				currentEndB = 0;
			}
			System.out.printf("%7d",currentEndB);
			sb.append(String.valueOf(currentEndB));
			sb.append(',');
			System.out.print(" ");

			
			processes.get(currentPID).setStartB(currentEndB);
			
			if(currentEndB <= 0)
			{
				completionTime = cpu + currentStartB;
				cpu = completionTime + swap;
				sum -= processes.get(currentPID).getPriority();
				processes.get(currentPID).setPriority(0);
				prioritiesCount --;
				lastProcessTerminated = true;
			}
			else
			{
				completionTime = 0;
				lastProcessTerminated = false;
			}
			
			System.out.printf("%7d",completionTime);
			sb.append(String.valueOf(completionTime));
			sb.append('\n');
			System.out.println();
			writer.write(sb.toString());
			sb.delete(0, sb.length());
		}
		
		writer.close();
	}
	
	public int randomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(sum) + 1;
	}
	
	public int pickProcess()
	{
		int tempBurstNumber = this.burstNumber;
		int index = 0;
		
		while(tempBurstNumber > 0)
		{
			tempBurstNumber -= processes.get(index).getPriority();
			index ++;	
		}
		
		return index - 1;
	}
	
	public int getBurstNumber()
	{
		return burstNumber;
	}
	public void setBurstNumber(int burstNumber)
	{
		this.burstNumber = burstNumber;
	}
	
	
}
