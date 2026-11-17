package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class shooter {




    /**
     * Created by Tom on 9/26/17.  Updated 9/24/2021 for PIDF.
     * This assumes that you are using a REV Robotics Control Hub or REV Robotics Expansion Hub
     * as your DC motor controller.  This OpMode uses the extended/enhanced
     * PIDF-related functions of the DcMotorEx class.
     */



        // our DC motor
        public DcMotorEx shooterMotor;

        public static final double NEW_P = 2.5;
        public static final double NEW_I = 0.1;
        public static final double NEW_D = 0.2;
        public static final double NEW_F = 0.5;


        // These values are for illustration only; they must be set
        // and adjusted for each motor based on its planned usage.

        public void init(HardwareMap hardwareMap) {

            shooterMotor=hardwareMap.get(DcMotorEx.class," shooterMotor");

            shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            shooterMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfOrig = shooterMotor.getPIDFCoefficients( DcMotor.RunMode.RUN_USING_ENCODER);

            PIDFCoefficients pidfNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
          shooterMotor.setPIDFCoefficients (DcMotor.RunMode.RUN_USING_ENCODER, pidfNew);

            // Re-read coefficients and verify change.
            PIDFCoefficients pidfModified = shooterMotor.getPIDFCoefficients( DcMotor.RunMode.RUN_USING_ENCODER);



        }
    }

