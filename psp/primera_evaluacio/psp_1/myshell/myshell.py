import subprocess
from colorama import init, Fore, Style

def main():
    init(autoreset=True)
    
    print("Starting...")
    while True:
        prompt = "# Myshell>"
        comand = input(prompt)
        
        if comand.lower() == "quit":
            break
        
    execute_comand(comand)
    
def execute_comand(comand):
    try: 
        process = subprocess.Popen(comand.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
        for line in process.stdout:
            print(f"{Fore.GREEN}{line.strip()}{Style.RESET_ALL}")

        exit_code = process.wait()
        if exit_code != 0:
            print(f"{Fore.RED}Error: El comando salió con el código de salida {exit_code}{Style.RESET_ALL}")
    except Exception as e:
        print(f"{Fore.RED}Error: {str(e)}{Style.RESET_ALL}")

if __name__ == "__main__":
    main()