package lightclock.talktimer;

import blink1.Blink1LightViaJava;
import blink1.Light;
import blink1.MockLight;
import lightclock.domain.TimePeriod;
import lightclock.domain.Talk;
import lightclock.talktimer.segment.*;
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

        TimePeriod talkTime = new TimePeriod(60, 0);
        if (line.hasOption("time")) {
            talkTime = new TimePeriod(line.getOptionValue("time"));
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

        SegmentStrategy segmentStrategy = new EqualSegmentStrategy();
        if (line.hasOption("equal")) {
            segmentStrategy = new EqualSegmentStrategy();
        } else if (line.hasOption("halflife")) {
            segmentStrategy = new HalfLifeSegmentStrategy();
        } else if (line.hasOption("percentage")) {
            String[] values = line.getOptionValues("percentage");
            PercentageSegmentStrategy pss = new PercentageSegmentStrategy(
                    Double.parseDouble(values[0]),
                    Double.parseDouble(values[1]),
                    Double.parseDouble(values[2]),
                    Double.parseDouble(values[3]),
                    Double.parseDouble(values[4])
            );
            segmentStrategy = pss;
        } else if (line.hasOption("explicit")) {
            String[] values = line.getOptionValues("explicit");
            ExplicitSegmentStrategy ess = new ExplicitSegmentStrategy();
            ess.setSegment1(new TimePeriod(values[0]));
            ess.setSegment2(new TimePeriod(values[1]));
            ess.setSegment3(new TimePeriod(values[2]));
            ess.setSegment4(new TimePeriod(values[3]));
            ess.setSegment5(new TimePeriod(values[4]));
            segmentStrategy = ess;
        }

        TimePeriod warningPeriod = new TimePeriod(1, 0);
        if (line.hasOption("warning")) {
            warningPeriod = new TimePeriod(line.getOptionValue("warning"));
        }

        Talk talk = new Talk(talkTime);

        final TalkTimer talkTimer = new TalkTimer(talk, segmentStrategy, light, warningPeriod);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                talkTimer.stop();
            }
        });

        talkTimer.start();
    }

    private static void showHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("talktimer", options);

        System.out.println("Examples");
        System.out.println("Sixty minute talk with decreasing segments: -time 60:00 -halflife");
        System.out.println("Fifty minute talk with percentage segments: -time 50:00 -percentage 70 10 10 5 5");
        System.out.println("Ninety minute talk with explicit segments: -time 90:00 -explicit 60:00 20:00 5:00 4:00 1:00");
    }

    private static Options createOptions() {
        Options options = new Options();

        options.addOption("?", false, "print this message");

        options.addOption(OptionBuilder.withDescription("Talk time in minutes and seconds (default is 60:00)")
                .hasArg()
                .withArgName("value")
                .create("time"));

        options.addOption(OptionBuilder.withDescription("Warning time when talk time is up, in minutes and seconds (default is 01:00)")
                .hasArg()
                .withArgName("value")
                .create("warning"));

        options.addOption(OptionBuilder.withDescription("Use the specified blink(1) device")
                .hasOptionalArg()
                .withArgName("id")
                .create("blink1"));

        options.addOption(OptionBuilder.withDescription("5 segments specified by percentage (e.g. 70 10 10 5 5)")
                .hasArgs(5)
                .withArgName("value")
                .create("percentage"));

        options.addOption(OptionBuilder.withDescription("5 segments specified explicitly (e.g. 30:00 10:00 10:00 5:00 5:00)")
                .hasArgs(5)
                .withArgName("value")
                .create("explicit"));

        options.addOption("test", false, "Don't use the blink(1) device");
        options.addOption("equal", false, "5 equal segments");
        options.addOption("halflife", false, "Segments of 50%, 25%, 12.5%, 6.5% and 6.5%");

        return options;
    }

}
