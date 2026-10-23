package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "2")
public class aut02 extends LinearOpMode {
    Actuators actuator = new Actuators();

    @Override
    public void runOpMode() {
        actuator.init(hardwareMap);
        waitForStart();
        ElapsedTime runtime = new ElapsedTime();

        if (opModeIsActive() && !isStopRequested()) {
            runtime.reset(); while (opModeIsActive() && !isStopRequested()) {

                }
                if (runtime.seconds()>16&&runtime.seconds()<16.2){
                    actuator.leftBack.setPower(1);
                    actuator.leftFront.setPower(1);
                    actuator.rightFront.setPower(1);
                    actuator.rightBack.setPower(1);
                }
                else {
                    actuator.rightBack.setPower(0);
                    actuator.leftFront.setPower(0);
                    actuator.leftBack.setPower(0);
                    actuator.rightFront.setPower(0);
                }




            }
        }
    }

