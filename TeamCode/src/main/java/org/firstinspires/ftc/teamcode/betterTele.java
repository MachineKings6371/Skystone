package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "ImprovedTeleOp")
public class betterTele extends OpMode {
    public DcMotor LeftF;
    public DcMotor RightF;
    public DcMotor LeftB;
    public DcMotor RightB;
    public DcMotor Left_wheel;
    public DcMotor Right_wheel;
    public DcMotor ArmRotate;
    public DcMotor ClampLift;
    public Servo Clamp;
    public Servo LeftPull;
    public Servo RightPull;
    @Override
    public void init() {
        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");
        Left_wheel = hardwareMap.dcMotor.get("Left_wheel");
        Right_wheel = hardwareMap.dcMotor.get("Right_wheel");
        ArmRotate = hardwareMap.dcMotor.get("ArmRotate");
        ClampLift = hardwareMap.dcMotor.get("ClampLift");
        Clamp = hardwareMap.servo.get("Clamp");
        Clamp.setPosition(.30);
        LeftPull = hardwareMap.servo.get("LeftPull");
        RightPull = hardwareMap.servo.get("RightPull");
    }
    @Override
    public void loop() {

        //Base motors and stuff
        //Right side base
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            RightF.setPower(-gamepad1.right_stick_y - .25);
            RightB.setPower(-gamepad1.right_stick_y - .25);
        } else{
            RightF.setPower(0);
            RightB.setPower(0);}
        //Left side base
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            LeftF.setPower(gamepad1.left_stick_y + .25);
            LeftB.setPower(gamepad1.left_stick_y + .25);
        }else{
            LeftF.setPower(0);
            LeftB.setPower(0);}

        //strafing
        if (Math.abs(gamepad1.right_stick_x) > .1){
            RightF.setPower(-gamepad1.right_stick_x);
            RightB.setPower(gamepad1.right_stick_x);
            LeftF.setPower(-gamepad1.right_stick_x);
            LeftB.setPower(gamepad1.right_stick_x);
        }else{
            RightF.setPower(0);
            RightB.setPower(0);
            LeftF.setPower(0);
            LeftB.setPower(0);
        }

        //wheel intake
        if (Math.abs(gamepad1.left_trigger) > .1) {
            Right_wheel.setPower(.7);
            Left_wheel.setPower(-.7);
        } else{
            Right_wheel.setPower(0);
            Left_wheel.setPower(0);}

        if (Math.abs(gamepad1.right_trigger) > .1) {
            Right_wheel.setPower(-.7);
            Left_wheel.setPower(.7);
        } else{
            Right_wheel.setPower(0);
            Left_wheel.setPower(0);}

        //open clamp
        if (gamepad2.y ){
            Clamp.setPosition(.35);//951 923 5464s
        }
        //close clamp
        if (gamepad2.x){
            Clamp.setPosition(.20);
        }
        //Rotates dah Arm
        if (Math.abs(gamepad2.left_trigger) > .1) {
            ArmRotate.setPower(.30);
        } else if (Math.abs(gamepad2.right_trigger) > .1) {
            ArmRotate.setPower(-.30);
        } else{
            ArmRotate.setPower(0);}

        //Clamp lift
        if ((gamepad2.left_stick_y) > .1) {
            ClampLift.setPower(.3);
        }else if ((gamepad2.left_stick_y) < -.1){
            ClampLift.setPower(-.3);
        }else{
            ClampLift.setPower(0);}

        //Pull Stuff
        if (gamepad1.right_bumper){
            RightPull.setPosition(.30);
            LeftPull.setPosition(.30);
        }
        if (gamepad1.left_bumper){
            RightPull.setPosition(.5);
            LeftPull.setPosition(.5);
        }

    }
}
