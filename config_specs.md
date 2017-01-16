# Really Simple Robot Framework Specification

The purpose of the Really Simple Robot Framework (RSRF) is to allow teams to easily and quickly set up a robot, bypassing the hassle of programming. Even though programming should be an essential part of every FIRST Robotics team, this tool has been made for teams who:

 - Do not have student programmers
 - Have just started participating in the competition
 - Lack the resources to leave time for programming during the season

To aid teams in these situations (and many others), the RSRF depends solely on a configuration file written in [YAML](http://yaml.org/).  YAML is a human friendly data serialization standard for all programming languages. The language has a simple syntax and structure which makes is extremely easy to have a working robot in a couple of minutes.

The configuration file is composed of various modules, which are being developed to increse the functionality of the framework. As of right now, the only modules available are the `robot` and `subsystems` module. There is a `logging` module being developed to ease debugging of the robot during matches and a `webUI` module for real time monitoring of the robot.

```yaml
# Robot Module
robot:
  include: 
    - subsystems
    - logging
  pindefs:
    PWM0: talon
    PWM1: talon
  controls:
    USB0: joystick
    USB1: joystick
# Subsystems Module
subsystems:
  chassis:
    type: tank-drive-2
    config:
      motor-left: PWM0
      motor-right: PWM1
      control: tank
      joysticks:
        - USB0
        - USB1
```

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
 - `pindefs`: An abbreviation of *pin definitions*. Here is where every connected component to the robot is defined. Keys (the values before the colon in definitions) may be of the form `PWMX`, `DIOX`, `ANAY` or `RELY`, where `X` is a number between 0 and 9 (inclusive) and `Y` is a number between 0 and 3 (inclusive). There numbers obviously refer to the pin numbers on the roboRIO, but do not include the additional pinouts available on the myRIO Expansion Port (MXP).
 - `controls`: This definition *details all the controllers* connected to the Driver Station computer. Currently, the only available type is `joystick`, but better support for other joysticks will be added in the future (for example,  `xbox-360`, `mad-catz` or `extreme-3d-pro`)

## Subsystems

The subsystems module is used to define *the relation between RSRF controllers and the inputs/outputs connected to the roboRIO*. RSRF controllers are preprogrammed functions of the framework which work by taking inputs from the sensors and driver station and sending output to the actuators connected to the robot. A code sample of valid subsystems definition can be seen below:

```yaml
subsystems:
  chassis:
    type: tank-drive-2
    config:
      motor-left: PWM0
      motor-right: PWM1
      control: tank
      joysticks:
        - USB0
        - USB1
```

In this case, we have defined only one subsystem, `chassis`. `chassis` is the **unique** name of the subsystem (it could have also been called `drivebase` or `platform-on-wheels` or even `plowie`). Within the definition of this subsystem, two additional parameters must be defined:

 - `type`: This defines the RSRF Controller to be used. Controllers are defined by a unique string and are part of RSRF. A detailed list of all available Controllers can be found below.
 - `config`: The configurations necessary for the RSRF Controller previously chosen are defined here. The parameters which must be specified for each Controller can be found in the list of available Controllers.

For example, let's analyze the subsystem definition from above. We already know we have a subsystem named chassis. It is of type tank-drive-2, and from the specification of Controllers, we know that we must define 4 parameters:

 - `motor-left`
 - `motor-right`
 - `control`
 - `joysticks`

For `motor-left` and `motor-right`, we simply specify the pin on the robot which controls the motors on the corresponding sides of the chassis. For `control`, we can either choose `tank`, `arcade` or `fps`. Each type allows different configuration for driving the robot. They also use different numbers of joysticks, so instead of having `joystick-left` and `joystick-right` parameters, we define a list of joysticks. For `tank` and `fps` we need two joysticks, but the order of appearance is important, because each drive mode uses the joysticks for different things. In case of using an `arcade` drive, the definition would be:

```yaml
joysticks:
    - USB0
```

***

Subsystems are very versatile and generic way of defining the important parts of your robot while dividing it into logical separations. That is why, while more complicated the subsystem definition, the larger the chance that bugs may arise from Controllers. Care should be taken to avoid loops in definition or using the same pin definition in more than one Controller (at least in the case of outputs).

## RSRF Controllers

This section has not yet been written.