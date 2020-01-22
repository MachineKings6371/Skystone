package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "Reposition(Red)")
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
        LeftPull.setPosition(.75);
        RightPull.setPosition(0);

        waitForStart();

        goForward(.5, 1000);
        StrafeRight(.5, 500);
        goForward(.5, 250);
        Pause(0, 500);
        RightPull.setPosition(.75);//closing pull
        LeftPull.setPosition(0);
        sleep(500);
        Pause(0, 500);
        goBackwards(.5, 1800);
        turnRight(.5, 2000);
        Pause(0, 500);
        RightPull.setPosition(0);
        LeftPull.setPosition(.75);
        sleep(500);
        Pause(0, 500);
        goBackwards(.5, 1500);
    }

    public void Pause(double power, int time) {
        RightF.setPower(0);
        RightB.setPower(0);
        LeftF.setPower(0);
        LeftB.setPower(0);
        sleep(time);
    }

    public void goBackwards(double power, int time) {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }

    public void goForward(double power, int time) {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
        sleep(time);
    }

    public void turnLeft(double power, int time) {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }

    public void turnRight(double power, int time) {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
        sleep(time);
    }

    public void strafeLeft(double power, int time) {
        RightF.setPower(power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(-power);
        sleep(time);
    }

    public void StrafeRight(double power, int time) {
        RightF.setPower(-power);
        RightB.setPower(power);
        LeftF.setPower(-power);
        LeftB.setPower(power);
    }


}
