# Really Simple Robot Framework Configuration Specification

The purpose of the Really Simple Robot Framework (RSRF) is to allow teams to easily and quickly set up a robot, bypassing the hassle of programming. Even though programming should be an essential part of every FIRST Robotics team, this tool has been made for teams who:

 - Do not have student programmers
 - Have just started participating in the competition
 - Lack the resources to leave time for programming during the season

To aid teams in these situations (and many others), the RSRF depends solely on a configuration file written in [YAML](http://yaml.org/).  YAML is a human friendly data serialization standard for all programming languages. The language has a simple syntax and structure which makes is extremely easy to have a working robot in a couple of minutes.

The configuration file is composed of various modules, which are being developed to increse the functionality of the framework. As of right now, the only modules available are the `robot` and `subsystems` module. There is a `logging` module being developed to ease debugging of the robot during matches and a `webUI` module for real time monitoring of the robot.

## Robot

The robot module is the first module declared in any RSRF config file.

```yaml
robot:
    include:
        - subsystems
    pindefs:
        PWM0: talon
        PWM1: talon
    controls:
        USB0: joystick
        USB1: joystick
```

It includes three important definitions:

 - `include`: Tells the robot which modules will be used. Each one of the specified modules must appear later in the configuration file to avoid errors.
 - `pindefs`: An abbreviation of pin definitions. Here is where every connected component to the robot is defined. Keys (the values before the colon in definitions) may be of the form `PWMX`, `DIOX`, `ANAY` or `RELY`, where `X` is a number between 0 and 9 (inclusive) and `Y` is a number between 0 and 3 (inclusive). There numbers obviously refer to the pin numbers on the roboRIO, but do not include the additional pinouts available on the myRIO Expansion Port (MXP).
 - `controls`: This definition details all the controllers connected to the Driver Station computer. Currently, the only available type is `joystick`, but better support for other joysticks will be added in the future (for example,  `xbox-360`, `mad-catz` or `extreme-3d-pro`)