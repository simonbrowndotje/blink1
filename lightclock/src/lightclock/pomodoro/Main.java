package lightclock.pomodoro;

import blink1.Blink1LightViaJava;
import blink1.Light;
import blink1.MockLight;
import lightclock.domain.TimePeriod;
import org.apache.commons.cli.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        CommandLineParser parser = new PosixParser();
        Options options = createOptions();

        CommandLine line = null;
        try {
            line = parser.parse( options, args );
        } catch (ParseException e) {
            log.fatal(e.getMessage());
            showHelp(options);
            System.exit(0);
        }

        if (line.hasOption("?")) {
            showHelp(options);
            System.exit(0);
        }

        TimePeriod length = new TimePeriod(25, 0);
        if (line.hasOption("length")) {
            length = new TimePeriod(line.getOptionValue("length"));
        }

        TimePeriod shortBreak = new TimePeriod(5, 0);
        if (line.hasOption("shortbreak")) {
            shortBreak = new TimePeriod(line.getOptionValue("shortbreak"));
        }

        TimePeriod longBreak = new TimePeriod(15, 0);
        if (line.hasOption("longbreak")) {
            longBreak = new TimePeriod(line.getOptionValue("longbreak"));
        }

        Light light = null;
        if (line.hasOption("blink1")) {
            int id = 0;
            if (line.getOptionValue("blink1") != null) {
                id = Integer.parseInt(line.getOptionValue("blink1"));
            }
            light = new Blink1LightViaJava(id);
        } else if (line.hasOption("test")) {
            light = new MockLight();
        } else {
            light = new Blink1LightViaJava();
        }

        final Pomodoro pomodoro = new Pomodoro(light, length, shortBreak, longBreak);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                pomodoro.stop();
            }
        });

        pomodoro.start();
    }

    private static void showHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("pomodoro", options);
    }

    private static Options createOptions() {
        Options options = new Options();

        options.addOption("?", false, "print this message");

        options.addOption(OptionBuilder.withDescription("Pomodoro length in minutes and seconds (default is 25:00)")
                .hasArg()
                .withArgName("value")
                .create("length"));

        options.addOption(OptionBuilder.withDescription("Short break length in minutes and seconds (default is 05:00)")
                .hasArg()
                .withArgName("value")
                .create("shortbreak"));

        options.addOption(OptionBuilder.withDescription("Long break length in minutes and seconds (default is 15:00)")
                .hasArg()
                .withArgName("value")
                .create("longbreak"));

        options.addOption("test", false, "Don't use the blink(1) device");

        return options;
    }

}
