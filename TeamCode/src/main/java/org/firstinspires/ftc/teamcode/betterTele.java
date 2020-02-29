package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.tfod.Timer;


@TeleOp (name = "betterTele")//current mother
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
        Left_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Right_wheel = hardwareMap.dcMotor.get("Right_wheel");
        Right_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        ArmRotate = hardwareMap.dcMotor.get("ArmRotate");
        ArmRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ClampLift = hardwareMap.dcMotor.get("ClampLift");
        Clamp = hardwareMap.servo.get("Clamp");
        Clamp.setPosition(.35);
        LeftPull = hardwareMap.servo.get("LeftPull");
        RightPull = hardwareMap.servo.get("RightPull");

    }
    @Override
    public void loop() {

        //Base motors and stuff
        //Right side base                                       //
        if (Math.abs(gamepad1.right_stick_y) > .1) {          //
            RightF.setPower(gamepad1.right_stick_y);   //remember to test this before succ
            RightB.setPower(gamepad1.right_stick_y);
        } else{
            RightF.setPower(0);
            RightB.setPower(0);
        }
        //Left side base
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            LeftF.setPower(-gamepad1.left_stick_y);
            LeftB.setPower(-gamepad1.left_stick_y);
        }else{
            LeftF.setPower(0);
            LeftB.setPower(0);
        }

        //strafing
        if (Math.abs(gamepad1.right_stick_x) > .2){
            RightF.setPower(gamepad1.right_stick_x );
            RightB.setPower(-gamepad1.right_stick_x );
            LeftF.setPower(gamepad1.right_stick_x );
            LeftB.setPower(-gamepad1.right_stick_x );
        }else{
            RightF.setPower(0);
            RightB.setPower(0);
            LeftF.setPower(0);
            LeftB.setPower(0);
        }

        //wheel intake
        if ((gamepad1.left_trigger) > .1){
            Left_wheel.setPower(gamepad1.left_trigger);
            Right_wheel.setPower(gamepad1.left_trigger);
        }else if ((gamepad1.right_trigger) > .1){
            Left_wheel.setPower(-gamepad1.right_trigger);
            Right_wheel.setPower(-gamepad1.right_trigger);
        }else {
            Left_wheel.setPower(0);
            Right_wheel.setPower(0);
        }

        //open clamp
        if (gamepad2.y){
            Clamp.setPosition(.15);//951 923 5464s
        }
        //close clamp
        if (gamepad2.x){
            Clamp.setPosition(.37);
        }

        //Rotates dah Arm
        if (Math.abs(gamepad2.left_trigger) > .1) {
            ArmRotate.setPower(gamepad2.left_trigger/2);
        } else if (Math.abs(gamepad2.right_trigger) > .1) {
            ArmRotate.setPower(-gamepad2.right_trigger/2);
        } else{
            ArmRotate.setPower(0);}

        //Clamp lift
        if (Math.abs(gamepad2.left_stick_y) > .1){
            ClampLift.setPower(-gamepad2.left_stick_y/1.5);
        }else if (Math.abs(gamepad2.left_stick_y) < .1){
            ClampLift.setPower(gamepad2.left_stick_y/1.5);
        }else {
            ClampLift.setPower(0);
        }

        //Pull Stuff
        if (gamepad1.right_bumper){          //Ready to throw down w/ hands down
            RightPull.setPosition(.30);
            LeftPull.setPosition(.70);
        }
        if (gamepad1.left_bumper){           //Puts the hands up
            RightPull.setPosition(1);      //we messed up the directions, right is left and vise versa
            LeftPull.setPosition(.10);
        }

    }
}