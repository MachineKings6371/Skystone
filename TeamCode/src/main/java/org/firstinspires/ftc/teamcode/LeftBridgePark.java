package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "Parking(LeftBridge)")
public class LeftBridgePark extends LinearOpMode {

    public DcMotor LeftF;
    public DcMotor RightF;
    public DcMotor LeftB;
    public DcMotor RightB;
    public Servo LeftPull;
    public Servo RightPull;

    @Override
    public void runOpMode() throws InterruptedException {
        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");
        LeftPull = hardwareMap.servo.get("RightPull");
        RightPull = hardwareMap.servo.get("LeftPull");

        waitForStart();

        goForward(.6,500);
        StrafeLeft(.6,1500);

    }

    public void goForward ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void goBackwards ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
        sleep(time);
    }
    public void turnLeft ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void turnRight ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
        sleep(time);
    }
    public void StrafeRight ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(-power);
        sleep(time);
    }
    public void StrafeLeft ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(power);
        LeftF.setPower(-power);
        LeftB.setPower(power);
        sleep(time);
    }

}



