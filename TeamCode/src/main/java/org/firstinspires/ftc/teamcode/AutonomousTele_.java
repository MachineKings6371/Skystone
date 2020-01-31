package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@TeleOp (name = "BetterTele_VOL.2")
public class AutonomousTele_ extends LinearOpMode {
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

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.6;
    static final double COUNTS_PER_MOTOR_HEX = 288;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_HEX_INCH = (COUNTS_PER_MOTOR_HEX) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.4;
    static final double TURN_SPEED = 0.6;

    @Override
    public void runOpMode() throws InterruptedException {
        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");
        Left_wheel = hardwareMap.dcMotor.get("Left_wheel");
        Right_wheel = hardwareMap.dcMotor.get("Right_wheel");
        ArmRotate = hardwareMap.dcMotor.get("ArmRotate");
        ArmRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        ClampLift = hardwareMap.dcMotor.get("ClampLift");
        Clamp = hardwareMap.servo.get("Clamp");
        Clamp.setPosition(.30);
        LeftPull = hardwareMap.servo.get("LeftPull");

        RightPull = hardwareMap.servo.get("RightPull");

        ArmRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        while (opModeIsActive()) {


            //Base motors and stuff
            //Right side base
            if (Math.abs(gamepad1.right_stick_y) > .1) {
                RightF.setPower(gamepad1.right_stick_y);
                RightB.setPower(gamepad1.right_stick_y);
            } else {
                RightF.setPower(0);
                RightB.setPower(0);
            }
            //Left side base
            if (Math.abs(gamepad1.left_stick_y) > .1) {
                LeftF.setPower(-gamepad1.left_stick_y);
                LeftB.setPower(-gamepad1.left_stick_y);
            } else {
                LeftF.setPower(0);
                LeftB.setPower(0);
            }

            //strafing
            if (Math.abs(gamepad1.right_stick_x) > .2) {
                RightF.setPower(gamepad1.right_stick_x);
                RightB.setPower(-gamepad1.right_stick_x);
                LeftF.setPower(gamepad1.right_stick_x);
                LeftB.setPower(-gamepad1.right_stick_x);
            } else {
                RightF.setPower(0);
                RightB.setPower(0);
                LeftF.setPower(0);
                LeftB.setPower(0);
            }

            //wheel intake
            if (Math.abs(gamepad1.left_trigger) > .1) {
                Right_wheel.setPower(gamepad1.left_trigger);
                Left_wheel.setPower(gamepad1.left_trigger);
            } else if (Math.abs(gamepad1.right_stick_x) > .1) {
                Right_wheel.setPower(-gamepad1.right_trigger);
                Left_wheel.setPower(-gamepad1.right_trigger);
            } else {
                Right_wheel.setPower(0);
                Left_wheel.setPower(0);
            }

            //open clamp
            if (gamepad2.y) {
                Clamp.setPosition(.33);//951 923 5464s
            }
            //close clamp
            if (gamepad2.x) {
                Clamp.setPosition(.17);
            }
            //Rotates dah Arm
            if (Math.abs(gamepad2.right_stick_x) > .1) {
                ArmRotate.setPower(gamepad2.left_trigger / 2.5);
            } else if (Math.abs(gamepad2.right_trigger) < .1) {
                ArmRotate.setPower(-gamepad2.right_trigger / 2.5);
            } else {
                ArmRotate.setPower(0);
            }
            //encoder stack set up
            if (gamepad2.a) {
                ArmRotate.setPower(1);
                sleep(600);
            } else if (gamepad2.b) {
                ArmRotate.setPower(-1);
                sleep(600);
            } else {
                ArmRotate.setPower(0);
            }

            //Clamp lift
            if ((gamepad2.left_stick_y) > .1) {
                ClampLift.setPower(.5);
            } else {
                ClampLift.setPower(0);
            }
            if ((gamepad2.left_stick_y) < -.1) {
                ClampLift.setPower(-.5);
            } else {
                ClampLift.setPower(0);
            }

            //Pull Stuff
            if (gamepad1.right_bumper) {          //Ready to throw down w/ hands down
                RightPull.setPosition(.30);
                LeftPull.setPosition(.70);
            }
            if (gamepad1.left_bumper) {           //Puts the hands up
                RightPull.setPosition(1);      //we messed up the directions, right is left and vise versa
                LeftPull.setPosition(-.10);
            }

            //half speed strafe
//            if (gamepad1.dpad_left) {
//                RightF.setPower(-.7);//left right?
//                RightB.setPower(.7);
//                LeftF.setPower(-.7);
//                LeftB.setPower(.7);
//            } else if (gamepad1.dpad_right) {
//                RightF.setPower(.7);
//                RightB.setPower(-.7);//right right?
//                LeftF.setPower(.7);
//                LeftB.setPower(-.7);
//            } else {
//                stopBase(0);
//            }

        }

    }

    public void encoderArmRotate ( double speed, double Inches, double timeoutS){
        int newLeftTarget;

        if (opModeIsActive()) {
            newLeftTarget = ArmRotate.getCurrentPosition() + (int) (Inches * COUNTS_PER_HEX_INCH);

            ArmRotate.setTargetPosition(newLeftTarget);

            ArmRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            ArmRotate.setPower(Math.abs(speed));

            ArmRotate.setPower(0);

            ArmRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}