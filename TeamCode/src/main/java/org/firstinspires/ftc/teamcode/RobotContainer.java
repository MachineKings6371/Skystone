package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotContainer {
    public DcMotor LeftF, RightF, RightB, LeftB, Left_wheel, Right_wheel, ArmRotate, ClampLift;
    public Servo Clamp, LeftPull, RightPull;


    public void init(HardwareMap hwMap) {
        //Motors
        LeftF = hwMap.dcMotor.get("LeftF");
        RightF = hwMap.dcMotor.get("RightF");
        LeftB = hwMap.dcMotor.get("LeftB");
        RightB = hwMap.dcMotor.get("RightB");
        Left_wheel = hwMap.dcMotor.get("Left_wheel");
        Left_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Right_wheel = hwMap.dcMotor.get("Right_wheel");
        Right_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        ArmRotate = hwMap.dcMotor.get("ArmRotate");
        ArmRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ClampLift = hwMap.dcMotor.get("ClampLift");
        Clamp = hwMap.servo.get("Clamp");

        //Servos
        LeftPull = hwMap.servo.get("LeftPull");
        RightPull = hwMap.servo.get("RightPull");

    }

    //Basic Movement
    public void setLeftSide(double power) {
        LeftF.setPower(power);
        LeftB.setPower(power);
    }
    public void setRightSide(double power) {
        RightF.setPower(power);
        RightB.setPower(power);
    }
    public void setStrafe(double power) {
        RightF.setPower(power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(-power);
    }

    //Intake
    public void setIntake(double power) {
        Left_wheel.setPower(power);
        Right_wheel.setPower(power);
    }

    //Arm rotation & clamping
    public void setClamp(double power) {
        Clamp.setPosition(power);
    }
    public void setArmTurn(double power) {
        ArmRotate.setPower(power);
    }
    public void setClampLift(double power) {
        ClampLift.setPower(power);
    }

    //Pulls
    public void setRightPull(double power) {
        RightPull.setPosition(power);
    }
    public void setLeftPull(double power) {
        LeftPull.setPosition(power);
    }
}
