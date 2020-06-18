import sys, serial, time
#pip3 install pyserial
#python3 Python_arduino.py 2
# https://en.wikibooks.org/wiki/Serial_Programming/Serial_Java

if len(sys.argv) == 2:
    #print("le nombre que tu a mis est " + sys.argv[1])
    arduino = serial.Serial('/dev/cu.usbmodem1411', 9600, timeout=.1)
    print(arduino.name)

    if arduino.isOpen(): #on vérifie si le port a été ouvert
        time.sleep(1) # on met en pause le programme pendant 1 seconde pour laisser le temps à la connection entre l'ordi et le serial
        arduino.write(bytes(sys.argv[1],encoding='utf8') + "\n".encode('ascii')) #on envoie le message en byte en format utf8
        while True:
            try:
                
                data = arduino.readline() #on lit le retour de l'arduino
                if data:
                    print(data) #strip out the new lines for now
                    arduino.close() #on ferme le port
                    break #on quitte la boucle
            except Exception:
                print("Error: impossible d'ouvrir le port")
                break
    else:
        print("Le port est pas ouvert")


elif len(sys.argv) > 2:
    print("Erreur: Il y a trop d'arguments, 1 seul suffit")
    print("Veillez marquez la commande comme ceci: python Python_arduino.py [arg1]")



elif len(sys.argv) < 2:
    print("Erreur: Argument inexistant")
    print("Veillez marquez la commande comme ceci: python Python_arduino.py [arg1]")