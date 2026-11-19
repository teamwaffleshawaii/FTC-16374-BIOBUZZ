package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class transfer {
    public DcMotor transferMotor;
    public void init(HardwareMap hwMap) {
        transferMotor = hwMap.get(DcMotor.class, "transferMotor");

        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        transferMotor.setDirection(DcMotorSimple.Direction.FORWARD);





    }

}



