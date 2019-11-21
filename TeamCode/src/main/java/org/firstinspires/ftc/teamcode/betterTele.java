package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp (name = "ImprovedTeleOp")
public class betterTele extends OpMode {
    public DcMotor LeftM;
    public DcMotor RightM;
    public DcMotor Left_wheel;
    public DcMotor Right_wheel;
    public DcMotor ArmRotate;
    public DcMotor ClampLift;
    public Servo Clamp;
    @Override
    public void init() {
        LeftM = hardwareMap.dcMotor.get("LeftM");
        RightM = hardwareMap.dcMotor.get("RightM");
        Left_wheel = hardwareMap.dcMotor.get("Left_wheel");
        Right_wheel = hardwareMap.dcMotor.get("Right_wheel");
        ArmRotate = hardwareMap.dcMotor.get("ArmRotate");
        ClampLift = hardwareMap.dcMotor.get("ClampLift");
        Clamp = hardwareMap.servo.get("Clamp");

        Clamp.setPosition(.30);
    }
    @Override
    public void loop() {

        //Base motors and stuff
        //Right side base
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            RightM.setPower(gamepad1.right_stick_y - .25);
        } else{
            RightM.setPower(0);}
        //Left side base
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            LeftM.setPower(-gamepad1.left_stick_y + .25);
        }else{
            LeftM.setPower(0);}

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

    }
}
