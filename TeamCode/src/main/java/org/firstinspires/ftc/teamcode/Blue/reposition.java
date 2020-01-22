package org.firstinspires.ftc.teamcode.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@Autonomous (name = "Reposition(blue)")
public class reposition extends LinearOpMode {
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
        LeftPull.setPosition(0);
        RightPull.setPosition(.75);

        waitForStart();
        strafeLeft(.4,900);
        goForward(.5,500);
        Pause(0,500);
        RightPull.setPosition(0);//closing pull
        LeftPull.setPosition(.75);
        sleep(500);
        Pause(0,700);
        goBackwards(.3,1000);
        Pause(0,500);
        turnLeft(.5,1000);
        Pause(0,600);
        RightPull.setPosition(.75);
        LeftPull.setPosition(0);
        sleep(500);
        Pause(0,500);
        goBackwards(.5,1000);


    }
    public void Pause ( double power, int time){
        RightF.setPower(0);
        RightB.setPower(0);
        LeftF.setPower(0);
        LeftB.setPower(0);
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
    public void goForward ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void turnLeft ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
        sleep(time);
    }
    public void turnRight ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void strafeLeft ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(power);
        LeftF.setPower(-power);
        LeftB.setPower(power);
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
}
