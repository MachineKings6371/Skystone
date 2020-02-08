package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="BlockAutonomous(BLUE)")

public class BlockAutonomousBLUE extends LinearOpMode {
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

        /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();


    static final double COUNTS_PER_MOTOR_REV = 537.6;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.5;
    static final double TURN_SPEED = 0.6;

    @Override
    public void runOpMode() {
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


            LeftB.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftF.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftPull.setPosition(0);
        RightPull.setPosition(.80);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        LeftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // ClampLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       // ClampLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        //phase 1
        encoderDrive(DRIVE_SPEED, -30, -30, 3.0);
        StrafeLeft(.4,650);
        Pause(250);
        RightPull.setPosition(.30);
        LeftPull.setPosition(.70);
        sleep(800);
        Pause(250);
        encoderDrive(DRIVE_SPEED,38,38,3.0);
        encoderTurnLeft(TURN_SPEED, 60,60,5.0);
        Pause(250);
        RightPull.setPosition(1);
        LeftPull.setPosition(0);
        sleep(800);
        Pause(250);
        //phase 2
        StrafeRight(.5,500);
        encoderDrive(DRIVE_SPEED,70,70,5.0);
        encoderTurnRight(TURN_SPEED,50,50,3.0);
        Intake();//succ block
        Pause(100);
        encoderDrive(DRIVE_SPEED,6,6,2.0);
        encoderTurnRight(TURN_SPEED,50,50,3.0);
        encoderDrive(DRIVE_SPEED,70,70,5.0);
        ClampLift.setPower(.8);
        sleep(700);
        ArmRotate.setPower(.8);
        sleep(500);
        Clamp.setPosition(.30);
        sleep(800);
        Clamp.setPosition(.17);
        sleep(800);
        ArmRotate.setPower(.8);
        sleep(500);
        ClampLift.setPower(-.8);
        sleep(700);
        encoderDrive(DRIVE_SPEED,-28,-28,3.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    public void encoderArmRotate(double speed, double Inches, double timeoutS){
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {
            newLeftTarget = ArmRotate.getCurrentPosition() + (int) (Inches * COUNTS_PER_INCH);

            ArmRotate.setTargetPosition(newLeftTarget);

            ArmRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            ArmRotate.setPower(Math.abs(speed));

            ArmRotate.setPower(0);

            ArmRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = LeftF.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newLeftTarget = LeftB.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = RightF.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newRightTarget = RightB.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            LeftF.setTargetPosition(newLeftTarget);
            LeftB.setTargetPosition(newLeftTarget);
            RightF.setTargetPosition(newRightTarget);
            RightB.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            LeftF.setPower(Math.abs(speed));
            LeftB.setPower(Math.abs(speed));
            RightF.setPower(Math.abs(speed));
            RightB.setPower(Math.abs(speed));

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && LeftF.isBusy() && LeftB.isBusy() && RightF.isBusy() && RightB.isBusy()) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d");
                telemetry.addData("Path2", "Running at %7d :%7d",
                        LeftF.getCurrentPosition(),
                        LeftB.getCurrentPosition());
                RightF.getCurrentPosition();
                RightB.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            LeftF.setPower(0);
            LeftB.setPower(0);
            RightF.setPower(0);
            RightB.setPower(0);

            // Turn off RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }

    public void encoderTurnRight(double speed,
                                 double leftInches, double rightInches,
                                 double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = LeftF.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newLeftTarget = LeftB.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = RightF.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);
            newRightTarget = RightB.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);

            LeftF.setTargetPosition(newLeftTarget);
            LeftB.setTargetPosition(newLeftTarget);
            RightF.setTargetPosition(newRightTarget);
            RightB.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            LeftF.setPower(speed);
            LeftB.setPower(speed);
            RightF.setPower(speed);
            RightB.setPower(speed);

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && LeftF.isBusy() && LeftB.isBusy() && RightF.isBusy() && RightB.isBusy()) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d");
                telemetry.addData("Path2", "Running at %7d :%7d",
                        LeftF.getCurrentPosition(),
                        LeftB.getCurrentPosition());
                RightF.getCurrentPosition();
                RightB.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            LeftF.setPower(0);
            LeftB.setPower(0);
            RightF.setPower(0);
            RightB.setPower(0);

            // Turn off RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
    public void encoderTurnLeft(double speed,
                                double leftInches, double rightInches,
                                double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = LeftF.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newLeftTarget = LeftB.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = RightF.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);
            newRightTarget = RightB.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);

            LeftF.setTargetPosition(newLeftTarget);
            LeftB.setTargetPosition(newLeftTarget);
            RightF.setTargetPosition(newRightTarget);
            RightB.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            LeftF.setPower(speed);
            LeftB.setPower(speed);
            RightF.setPower(speed);
            RightB.setPower(speed);

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && LeftF.isBusy() && LeftB.isBusy() && RightF.isBusy() && RightB.isBusy()) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d");
                telemetry.addData("Path2", "Running at %7d :%7d",
                        LeftF.getCurrentPosition(),
                        LeftB.getCurrentPosition());
                RightF.getCurrentPosition();
                RightB.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            LeftF.setPower(0);
            LeftB.setPower(0);
            RightF.setPower(0);
            RightB.setPower(0);

            // Turn off RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
    public void encoderStrafeRight(double speed,
                                   double leftInches, double rightInches,
                                   double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = LeftF.getCurrentPosition() + (int) (-leftInches * COUNTS_PER_INCH);
            newLeftTarget = LeftB.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = RightF.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newRightTarget = RightB.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);

            LeftF.setTargetPosition(newLeftTarget);
            LeftB.setTargetPosition(newLeftTarget);
            RightF.setTargetPosition(newRightTarget);
            RightB.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            LeftF.setPower(speed);
            LeftB.setPower(speed);
            RightF.setPower(speed);
            RightB.setPower(speed);

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && LeftF.isBusy() && LeftB.isBusy() && RightF.isBusy() && RightB.isBusy()) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d");
                telemetry.addData("Path2", "Running at %7d :%7d",
                        LeftF.getCurrentPosition(),
                        LeftB.getCurrentPosition());
                RightF.getCurrentPosition();
                RightB.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            LeftF.setPower(0);
            LeftB.setPower(0);
            RightF.setPower(0);
            RightB.setPower(0);

            // Turn off RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
    public void encoderStrafeLeft(double speed,
                                  double leftInches, double rightInches,
                                  double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = LeftF.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newLeftTarget = LeftB.getCurrentPosition() + (int) (-leftInches * COUNTS_PER_INCH);
            newRightTarget = RightF.getCurrentPosition() + (int) (-rightInches * COUNTS_PER_INCH);
            newRightTarget = RightB.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            LeftF.setTargetPosition(newLeftTarget);
            LeftB.setTargetPosition(newLeftTarget);
            RightF.setTargetPosition(newRightTarget);
            RightB.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            LeftF.setPower(speed);
            LeftB.setPower(speed);
            RightF.setPower(speed);
            RightB.setPower(speed);

            while (opModeIsActive() && (runtime.seconds() < timeoutS) && LeftF.isBusy() && LeftB.isBusy() && RightF.isBusy() && RightB.isBusy()) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d");
                telemetry.addData("Path2", "Running at %7d :%7d",
                        LeftF.getCurrentPosition(),
                        LeftB.getCurrentPosition());
                RightF.getCurrentPosition();
                RightB.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            LeftF.setPower(0);
            LeftB.setPower(0);
            RightF.setPower(0);
            RightB.setPower(0);

            // Turn off RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }


    public void Pause (int time) {
        LeftF.setPower(0);
        LeftB.setPower(0);
        RightF.setPower(0);
        RightB.setPower(0);
        sleep(time);
    }
    public void StrafeLeft ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(power);
        LeftF.setPower(power);
        LeftB.setPower(-power);
        sleep(time);
    }
    public void StrafeRight ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(-power);
        LeftF.setPower(-power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void turnLeft ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void goBackward ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);

    }

//    public void goForward ( double power, int time)
//    {
//        RightF.setPower(-power);
//        RightB.setPower(-power);
//        LeftF.setPower(-power);
//        LeftB.setPower(-power);
//        sleep();
//
//    }


    public void Intake() {
        encoderDrive(.3,-5,-5,1.0);
        Right_wheel.setPower(1);
        Left_wheel.setPower(1);
        sleep(3000);
        Clamp.setPosition(.17);
        sleep(500);

    }
}
