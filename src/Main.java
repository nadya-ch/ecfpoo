import Controleur.ControleurAccueil;
import Log.FormatterLog;
import Log.LoggerExo;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class Main {
    private static FileHandler fh=null;
    public static void main(String[] args) {

        try {

            fh = new FileHandler("fichierlog.log", true);
            LoggerExo.LOGGER.setUseParentHandlers(false);
            LoggerExo.LOGGER.addHandler(fh);

            fh.setFormatter(new FormatterLog());
            LoggerExo.LOGGER.log(Level.INFO, "début pg");
            System.out.println("l'application desktop de reverso ");

        }

    catch (Exception ee ) {

            LoggerExo.LOGGER.log(Level.SEVERE, "erreur "+ ee.getMessage() + " " + ee);

        }
        LoggerExo.LOGGER.log(Level.INFO, "fin pg");
        System.out.println("fin du main");


        ControleurAccueil.init();

    }

}