
import java.io.*;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WordCountRunnable implements Runnable {

	String fileName;
	String line;
	File input;
	int wcount = 0;
    int ccount = 0;
    Lock lock = new ReentrantLock();

	
	public WordCountRunnable (String aFileName) {
		fileName = aFileName;
		input = new File(aFileName);
	}
	
	// each thread writes to output.txt as its reading , finishes then next thread comes does the same
	public void run(){
		
		
        try(BufferedReader in = new BufferedReader(new FileReader(input))) {
        
        FileWriter filewriter = new FileWriter("Output.txt", true);
		BufferedWriter buffwriter = new BufferedWriter(filewriter);
		PrintWriter writer1 = new PrintWriter(buffwriter);
        
        {
			while ((line = in.readLine()) != null) {
				String[] words = line.split(" ");
				wcount += words.length;
				
				writer1.println(line);
				
				//totalCount();
				//Thread.interrupt();
			
				
			}
        in.close();
        writer1.close();
		} }
        
        catch(FileNotFoundException e) {
        	System.out.println("file not provided!");
        	wcount = 0; 
        }
        
        catch (IOException e) {
        	
        }
		catch(Exception e) {
            System.out.println(e);
            System.out.println("you have an error");
		}  
        finally{
        	System.out.println("The count for " + fileName +" " + getCount());
            
            System.out.println("The combined count is: " + getTotalCount());
            //wcount = 0;
        }
	}
	
	public int getCount() {
		return wcount;
	}
	
	// this is not right
	public void totalCount(String filename) throws FileNotFoundException{
		
		File TempFile = new File("Output.txt");
		
		File file = new File(filename);
		lock.lock();
		Condition condition = lock.newCondition();
		BufferedReader scan = new BufferedReader(new FileReader(file));
		try {
			
			while ((line = scan.readLine()) != null) {
				condition.await();
				
				String [] word = line.split(" ");
				ccount += word.length;
				
		}
			//scan.close();
		}
			catch (IOException e) {
			}
		
			catch (InterruptedException e) {
				
			}
		 	
			catch (Exception e) {
				
			}
		
		finally {
			System.out.println("combined count: " + ccount);
			condition.signalAll();
			lock.unlock();
			
			
		}
	}
	
	public int getTotalCount() {
		return ccount;
	}
}
