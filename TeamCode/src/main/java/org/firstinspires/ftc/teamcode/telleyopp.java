/* This template drives a four wheel mecanum drive as a POV controller with yaw measurements
    This code is "field centric", which means driving with the human's point of view.
    Going forward is always going forward, with respect to you and the field.
    This code has quite a bit of math involved and requires trigonometry
    You should learn MecanumPOV first (Template 2) before attempting to use this.
    Compatible with both the built-in imu or the navX-micro sensor (external library required)
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Field Centric Drive")
public class telleyopp extends LinearOpMode {

    double reset_angle = 0;
    Actuators actuator = new Actuators();
    Sensors sensor = new Sensors();
    private int lastPosition = 0;
    private double lastTime = 0;
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        actuator.init(hardwareMap);
        sensor.init(hardwareMap);
        timer.reset();
        lastPosition = actuator.shooterMotor.getCurrentPosition();
        lastTime = timer.seconds();

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                drive();
                resetAngle();
                intake();
                Shooter();


                telemetry.update();
            }
        }
    }

    public void drive() {
        double Protate = - gamepad1.right_stick_x;
        double stick_x = - gamepad1.left_stick_x * Math.sqrt(Math.pow(1-Math.abs(Protate), 2) );
        //Accounts for Protate when limiting magnitude to be less than 1
        double stick_y = - gamepad1.left_stick_y * Math.sqrt(Math.pow(1-Math.abs(Protate), 2) );
        double theta = 0;
        double Px = 0;
        double Py = 0;

        double gyroAngle = getHeading() * Math.PI / 180; //Converts gyroAngle into radians
        if (gyroAngle <= 0) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (0 < gyroAngle && gyroAngle < Math.PI / 2) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (Math.PI / 2 <= gyroAngle) {
            gyroAngle = gyroAngle - (3 * Math.PI / 2);
        }
        gyroAngle = -1 * gyroAngle;

        if(gamepad1.right_bumper){ //Disables gyro, sets to -Math.PI/2 so front is defined correctly.
            gyroAngle = -Math.PI/2;
        }

        //Linear directions in case you want to do straight lines.
        if(gamepad1.dpad_right){
            stick_x = 0.5;
        }
        else if(gamepad1.dpad_left){
            stick_x = -0.5;
        }
        if(gamepad1.dpad_up){
            stick_y = -0.5;
        }
        else if(gamepad1.dpad_down){
            stick_y = 0.5;
        }


        //MOVEMENT
        theta = Math.atan2(stick_y, stick_x) - gyroAngle - (Math.PI / 2);
        Px = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta + Math.PI / 4));
        Py = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta - Math.PI / 4));

        telemetry.addData("Stick_X", stick_x);
        telemetry.addData("Stick_Y", stick_y);
        telemetry.addData("Magnitude",  Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)));
        telemetry.addData("Motor Front Left", Py - Protate);
        telemetry.addData("Motor Front Right", Px + Protate);
        telemetry.addData("Motor Back Left", Px - Protate);
        telemetry.addData("Motor Back Right", Py + Protate);


        actuator.leftFront((Py - Protate));
        actuator.leftBack((Px - Protate));
        actuator.rightBack((Py + Protate));
        actuator.rightFront((Px + Protate));

    }
  public void intake() {
      if (gamepad2.x) {
          actuator.intakeMotor.setPower(1);
          actuator.shooterMotor.setPower(0.2);
      }else if (gamepad2.right_bumper) {
          actuator.intakeMotor.setPower(-0.5);
      }
      else if(gamepad2.a){
          actuator.intakeMotor.setPower(0.7);
      }
      else if (gamepad2.y) {
          actuator.intakeMotor.setPower(0.0);
      }
      else {
          actuator.intakeMotor.setPower(0.3);
      }

  }

    public void Shooter() {
        if (gamepad2.dpad_up) {
            actuator.shooterMotor.setPower(-0.75);
        }else if (gamepad2.dpad_left) {
            actuator.shooterMotor.setPower(0.3);
        }

        else if (gamepad2.dpad_down) {
            actuator.shooterMotor.setPower(-0.2);
        } else  {
            actuator.shooterMotor.setPower(0.15);


        }
        // Example: Core Hex or REV Motor with a known ticks-per-revolution value (e.g., 28 ticks/rev on motor shaft)
      //  double ticksPerRev = 28.0;
      //  double velocityTicksPerSec = actuator.shooterMotor.get();
      //  double rpm = (velocityTicksPerSec / ticksPerRev) * 60.0;

        //telemetry.addData("Motor Velocity (Ticks/Sec)", velocityTicksPerSec);
        //telemetry.addData("Motor RPM", rpm);
        //telemetry.update(); // Sends the data to the Driver Station
         /*int currentPosition = actuator.shooterMotor.getCurrentPosition();
      double currentTime = timer.seconds();
      int deltaPosition = currentPosition - lastPosition;
      double deltaTime = currentTime - lastTime;
      double calculatedVelocity = 0;
      if (deltaTime > 0) {
          calculatedVelocity = deltaPosition / deltaTime; // Ticks per second
      }

      telemetry.addData("Calculated Velocity (ticks/s)", calculatedVelocity);
      telemetry.update();
      lastPosition = currentPosition;
      lastTime = currentTime;*/

    }

    public void resetAngle(){
        if(gamepad1.left_bumper){
            reset_angle = getHeading() + reset_angle;
        }
    }

    public double getHeading(){

        double heading;
        //Get heading from the IMU or the navX
        heading = sensor.getHeading(AngleUnit.DEGREES); //Detecting yaw value from imu

        if(heading < -180) {
            heading = heading + 360;
        }

        else if(heading > 180) {
            heading = heading - 360;
        }

        heading = heading - reset_angle;
        telemetry.addData("Heading (Degrees)", heading);
        telemetry.addData("Heading (Radians)", heading * 3.1415926535 / 90);
        return heading;
    }
}
