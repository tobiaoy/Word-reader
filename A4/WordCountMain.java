import java.io.File;
import java.io.IOException;

public class WordCountMain {

	public static void main(String [] args) throws IOException, IllegalMonitorStateException {
	
	String fileName;
	
	if (args.length == 0){
		System.out.println("No file provided");
		System.out.println("Please enter a valid file");
		//System.
		// end the program
		return;
		
	}
	
	// removes these comments after you are don
	// make for loop then check if files provided in arg exists here . arg itself is array anyway , dont forget to end program if files dont exist
	for(int x = 0; x <= args.length-1; x++)
	{
		File afile = new File(args[x]);
		if(afile.exists()== false)
		{
			
		}
		else
		{
			
		}
	}
	
	//if its not -1 you will get out of bound exception
	for (int i = 0; i <= args.length-1; i++) {
		
		fileName = args[i];
		WordCountRunnable wc = new WordCountRunnable(fileName);
		
		Thread t = new Thread(wc);
		t.start();
		
		t.interrupt();
		
		wc.totalCount(fileName);
		//System.out.println("This is a test of this class");	
	}
	
	// 
	
	
	
}
}