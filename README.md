lightclock
==========

Clock utilities for the blink(1) device

## Talktimer

This is a timer that can be used to give you a quick "at a glance" status of how much time you have left when you are speaking. The time you specify for the talk is split up into 5 segments that are represented by different colours:

1. Green
2. Yellow
3. Orange
4. Red
5. Pulsating red

When the time is up, the light will flash red.

### Example usage

````
./talktimer.sh -time 60:00 -explicit 45:00 05:00 05:00 04:00 01:00 -warning 01:00
````

- The talk time is 60 minutes
- Green light for 45 minutes
- Yellow light for 5 minutes
- Orange light for 5 minutes
- Red light for 4 minutes
- Pulsating red light for 1 minute
- Flashing light for 1 minute (warning time)

### Parameters

````
 -?                    print this message
 -blink1 <id>          Use the specified blink(1) device
 -equal                5 equal segments
 -explicit <value>     5 segments specified explicitly (e.g. 30:00 10:00 10:00 5:00 5:00)
 -halflife             Segments of 50%, 25%, 12.5%, 6.5% and 6.5%
 -percentage <value>   5 segments specified by percentage (e.g. 70 10 10 5 5)
 -test                 Don't use the blink(1) device
 -time <value>         Talk time in minutes and seconds (default is 60:00)
 -warning <value>      Warning time when talk time is up, in minutes and seconds (default is 01:00)
````

## Pomodoro

This is a simple customisable pomodoro timer where the pomodoro length, short break and long break are represented by different colours:

- Pomodoro: red
- Short break: yellow
- Long break (after 4 pomodoros): green

### Example usage

````
./pomodoro.sh
````

### Parameters

````
 -?                    print this message
 -length <value>       Pomodoro length in minutes and seconds (default is 25:00)
 -longbreak <value>    Long break length in minutes and seconds (default is 15:00)
 -shortbreak <value>   Short break length in minutes and seconds (default is 05:00)
 -test                 Don't use the blink(1) device
 ````