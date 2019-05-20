package de.Enigma.Core;

public class Log {

    private static Log logger;

    private Log() {
        // TODO Auto-generated constructor stub
    }

    public void i(String className, String methodName, String message) {

    }

    public void e(String className, String methodName, String message) {

    }

    public void w(String className, String methodName, String message) {

    }

    /*
    erstellt ein Singleton Objekt, sobald die Instanz benötigt wird,
    oder gibt das bereits instanziierte Singelton Objekt zurück, wenn es benötigt wird.
    Das stellt sicher, dass es nur einen Logger gibt und dieser von allen Objekten von überall aus genutzt werden kann.
     */
    public static Log getLogger() {
        if (logger == null) {
            logger = new Log();
        }
        return logger;
    }

}
