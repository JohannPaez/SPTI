package threads;

import java.io.IOException;

public class CommandThread extends Thread{
	
	private String comando;
	public CommandThread(String comando) {
		this.comando = comando;
	}
	
	
	@Override
	public void run() {
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		 processBuilder.command("bash", "-c", comando);
			
			try {
				Process process = processBuilder.start();			
				
				process.getInputStream();
				
				process.waitFor();
				

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

}
