package de.Enigma.Core;

/**
 * (@brief) Logger Klasse die eine in C++ geschriebene .dll einbindet, um die Logs zuschreiben
 * (@details) Realisiert als Singelton -> nur eine Instanz pro Programminstanz
 * (@details) Zugriff über getLogger() Methode von überall aus möglich
 *
 * @author Nikolai
 */
public class Log {

    private static Log logger;

    private Log() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Info Logeintrag:
     * erstellt über die dll einen Info Log der über Vorgänge innerhalb der Anwendung informieren soll
     *
     * @param className  Name der Klasse von der der Aufruf erfolgt ist
     * @param methodName Name der Methode von der der Aufruf erfolgt ist
     * @param info       Info Nachricht, die vom Logger geloggt werden soll
     */
    public void i(String className, String methodName, String info) {

    }

    /**
     * Warning Logeintrag:
     * erstellt über die dll einen Warning Log der über potentiell gefährliche Vorgänge innerhalb der Anwendung informieren soll
     *
     * @param className  Name der Klasse von der der Aufruf erfolgt ist
     * @param methodName Name der Methode von der der Aufruf erfolgt ist
     * @param warning    Warning Nachricht, die vom Logger geloggt werden soll
     */
    public void w(String className, String methodName, String warning) {

    }

    /**
     * Error Logeintrag:
     * erstellt über die dll einen Error Log der über kritische Fehler innerhalb der Anwendung informieren soll
     *
     * @param className  Name der Klasse von der der Aufruf erfolgt ist
     * @param methodName Name der Methode von der der Aufruf erfolgt ist
     * @param error      Error Nachricht, die vom Logger geloggt werden soll
     */
    public void e(String className, String methodName, String error) {

    }

    /**
     * (@details) erstellt ein Singleton Objekt, sobald die Instanz benötigt wird
     * (@details) Das stellt sicher, dass es nur einen Logger gibt und dieser von allen Objekten von überall aus genutzt werden kann.
     *
     * @return gibt das logger Singelton Objekt zurück
     */
    public static Log getLogger() {
        if (logger == null) {
            logger = new Log();
        }
        return logger;
    }

}
