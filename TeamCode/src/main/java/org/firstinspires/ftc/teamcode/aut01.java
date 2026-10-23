package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Tank Drive Autonomous")
public class aut01 extends LinearOpMode {
    Actuators actuator = new Actuators();

    @Override
    public void runOpMode() {
        actuator.init(hardwareMap);
        waitForStart();
        ElapsedTime runtime = new ElapsedTime();

        if (opModeIsActive() && !isStopRequested()) {
            runtime.reset(); while (opModeIsActive() && !isStopRequested()) {
                if (runtime.seconds() > 0 && runtime.seconds() < 15) {
                    actuator.shooterMotor.setPower(-0.75);
                } else {
                    actuator.shooterMotor.setPower(0);
                }
                if (runtime.seconds() > 4 && runtime.seconds() < 15) {
                    actuator.intakeMotor.setPower(0.7);
                }
            else {
                    actuator.intakeMotor.setPower(0);

                }
                if (runtime.seconds()>16&&runtime.seconds()<16.1){
                    actuator.leftBack.setPower(1);
                    actuator.leftFront.setPower(1);
                    actuator.rightFront.setPower(1);
                    actuator.rightBack.setPower(1);
                }




            }
        }
    }

}