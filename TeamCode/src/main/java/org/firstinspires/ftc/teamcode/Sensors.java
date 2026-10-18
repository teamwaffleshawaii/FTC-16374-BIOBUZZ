package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


//If using navX sensor (needs external libraries)
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.kauailabs.navx.ftc.AHRS;

public class Sensors {
    // TODO: If using a limit switch, magnetic limit switch, or touch sensor, add them here

   // public IMU imu;
    // TODO: If using the navX micro sensor, include this
    public AHRS navx_device;

    public void init(HardwareMap hwMap) {




        //TODO: If using the navX-micro sensor
         navx_device = AHRS.getInstance(hwMap.get(NavxMicroNavigationSensor.class, "navx"),
        AHRS.DeviceDataType.kProcessedData);
    }






    //TODO: Sample class for measuring the REV Control Hub heading through the IMU or navX
    public double getHeading(AngleUnit angleUnit) {
       // return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES); // if using the imu
         return -navx_device.getYaw();  // if using the navX
    }

}