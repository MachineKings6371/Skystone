package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp (name = "ExperimentalTeleOp")
public class betterTele_experimental extends OpMode {
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
        //Right side base                                       //
        if (Math.abs(gamepad1.right_stick_y) > .2 ) {          //
            RightF.setPower(.5);   //remember to test this before succ
            RightB.setPower(.5);
        } else if (Math.abs(gamepad1.right_stick_y) < .2 ) {
            RightF.setPower(-.5);   //remember to test this before succ
            RightB.setPower(-.5);
        }else{
            RightF.setPower(0);
            RightB.setPower(0);
            LeftF.setPower(0);
            LeftB.setPower(0);
        }
            //Left side base
        if (Math.abs(gamepad1.left_stick_y) > .2) {
            LeftF.setPower(-.5);
            LeftB.setPower(-.5);
        }else if (Math.abs(gamepad1.left_stick_y) < .2) {
            LeftF.setPower(.5);
            LeftB.setPower(.5);
        }else{
            LeftF.setPower(0);
            LeftB.setPower(0);
            RightF.setPower(0);
            RightB.setPower(0);
        }

        //strafing
        if (Math.abs(gamepad1.right_stick_x) > .1){
            RightF.setPower(gamepad1.right_stick_x);
            RightB.setPower(-gamepad1.right_stick_x);
            LeftF.setPower(gamepad1.right_stick_x);
            LeftB.setPower(-gamepad1.right_stick_x);
        }else{
            RightF.setPower(0);
            RightB.setPower(0);
            LeftF.setPower(0);
            LeftB.setPower(0);
        }

        //wheel intake
        if (Math.abs(gamepad1.left_trigger) > .1) {
            Right_wheel.setPower(-1);
            Left_wheel.setPower(1);
        } else{
            Right_wheel.setPower(0);
            Left_wheel.setPower(0);}

        if (Math.abs(gamepad1.right_trigger) > .1) {
            Right_wheel.setPower(1);
            Left_wheel.setPower(-1);
        } else{
            Right_wheel.setPower(0);
            Left_wheel.setPower(0);}

        //open clamp
        if (gamepad2.y ){
            Clamp.setPosition(.35);//951 923 5464s
        }
        //close clamp
        if (gamepad2.x){
            Clamp.setPosition(.16);
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
        if (gamepad1.right_bumper){          //Ready to throw down w/ hands down
            RightPull.setPosition(0);
            LeftPull.setPosition(.75);
        }
        if (gamepad1.left_bumper){           //Puts the hands up
            RightPull.setPosition(.75);      //we messed up the directions, right is left and vise versa
            LeftPull.setPosition(0);
        }

        //half speed strafe
        if (gamepad1.dpad_left){
            RightF.setPower(-.5);
            RightB.setPower(.5);
            LeftF.setPower(-.5);
            LeftB.setPower(.5);
        }else if(gamepad1.dpad_right){
            RightF.setPower(.5);
            RightB.setPower(-.5);
            LeftF.setPower(.5);
            LeftB.setPower(-.5);
        }else{
            RightF.setPower(0);
            RightB.setPower(0);
            LeftF.setPower(0);
            LeftB.setPower(0);
        }

    }
}
