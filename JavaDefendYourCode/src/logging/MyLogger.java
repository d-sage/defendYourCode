package logging;


import java.io.IOException;
import java.util.logging.*;

/*
Team: Abusement Park 3.0
Members: Kenny White, Jordan Lambert, Daric Sage
 */

//Singleton pattern of a logger
public class MyLogger {
    /* Filehandlers are connected to their respective log files, with APPEND set to TRUE
     * Filehandlers/Levels;
     *      Level.WARNING or greater    Writes to the error log, 'ErrorLog.txt'
     */

    private static Logger LOGGER;
    public MyLogger() {
        try {
            LogManager.getLogManager().reset();
            Formatter format = new SimpleFormatter();
            LOGGER = Logger.getLogger("");
            LOGGER.setUseParentHandlers(false);

            Handler errOut = new FileHandler("ErrorLog.txt", true);
            errOut.setFilter(new SFilter(Level.WARNING));
            errOut.setFormatter(format);
            LOGGER.addHandler(errOut);

        } catch (IOException ex) {
            System.out.println("Error creating log files");
            System.out.println(ex.getMessage() + "\n");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error setting up LOGGER");
            System.out.println(ex.getMessage() + "\n");
            ex.printStackTrace();
        }
    }


    /**
     * Overloaded method that calls default log method on this classes Logger
     * @param lvl LEVEL of the log
     * @param msg String message to log
     * @param objects Array of Objects to be added to log
     */
    public static void log(Level lvl, String msg, Object ... objects){
        LOGGER.log(lvl, msg, objects);
    }

    public static void log(Level lvl, String msg){
        LOGGER.log(lvl, msg);
    }

    /**This method properly closes the Handlers of LOGGER*/
    public static void closeLogger(){
        if(LOGGER != null){
            Handler[] handlers = LOGGER.getHandlers();
            LOGGER.log(Level.INFO, "Program exit: ", handlers);
            for (Handler h:handlers) {
                h.close();
            }
        }

    }
}