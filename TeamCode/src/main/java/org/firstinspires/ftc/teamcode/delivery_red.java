package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class delivery_red extends LinearOpMode {
    public DcMotor LeftF;
    public DcMotor RightF;
    public DcMotor LeftB;
    public DcMotor RightB;
    public Servo LeftPull;
    public Servo RightPull;
    public DcMotor Left_wheel;
    public DcMotor Right_wheel;
    @Override
    public void runOpMode() throws InterruptedException {
        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");
        LeftPull = hardwareMap.servo.get("RightPull");
        RightPull = hardwareMap.servo.get("LeftPull");
        Left_wheel = hardwareMap.dcMotor.get("Left_wheel");
        Right_wheel = hardwareMap.dcMotor.get("Right_wheel");
        LeftPull.setPosition(.75);
        RightPull.setPosition(0);

        waitForStart();

        goForward(.5,900);
        turnLeft(.5,1000);
        StrafeRight(.5,1000);
        Succ(.5,500);
        goBackwards(.5,200);
        strafeLeft(.5,1000);
        turnLeft(.5,4000);
        goForward(.5,3000);
        Spit(.5,500);
        StrafeRight(.5,1000);


    }
    public void Succ (double power, int time){
        Left_wheel.setPower(.7);
        Right_wheel.setPower(-.7);
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
        sleep(time);
    }

    public void Spit (double power, int time){
        Right_wheel.setPower(.7);
        Left_wheel.setPower(-.7);
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
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
