package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Parking(RightWall)")

public class parkingrightwall extends LinearOpMode {


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


    }
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.6;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.4;
    static final double TURN_SPEED = 0.5;
    public void encoderMovement(double speed,
                                double FrontLeftInches, double FrontRightInches, double BackLeftInches, double BackRightInches,
                                double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = LeftF.getCurrentPosition() + (int) (FrontLeftInches * COUNTS_PER_INCH);
            newBackLeftTarget = LeftB.getCurrentPosition() + (int) (BackLeftInches * COUNTS_PER_INCH);
            newFrontRightTarget = RightF.getCurrentPosition() + (int) (FrontRightInches * COUNTS_PER_INCH);
            newBackRightTarget = RightB.getCurrentPosition() + (int) (BackRightInches * COUNTS_PER_INCH);

            LeftF.setTargetPosition(newFrontLeftTarget);
            LeftB.setTargetPosition(newBackLeftTarget);
            RightF.setTargetPosition(newFrontRightTarget);
            RightB.setTargetPosition(newBackRightTarget);

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


    public void Pause(int time) {
        LeftF.setPower(0);
        LeftB.setPower(0);
        RightF.setPower(0);
        RightB.setPower(0);
        sleep(time);
    }

    public void StrafeLeft(double inches, double timeOuts) {
        encoderMovement(DRIVE_SPEED, inches, -inches, -inches, inches, timeOuts);
    }

    public void StrafeRight(double inches, double timeOuts) {
        encoderMovement(DRIVE_SPEED, -inches, inches, inches, -inches, timeOuts);
    }

    public void turnRight(double inches, double timeOuts) {
        encoderMovement(DRIVE_SPEED, -inches, inches, -inches, inches, timeOuts);
    }

    public void turnLeft(double inches, double timeOuts) {
        encoderMovement(DRIVE_SPEED, inches, -inches, inches, -inches, timeOuts);
    }

    public void goBackward(double inches, double timeOuts) {
        encoderMovement(DRIVE_SPEED, inches, inches, inches, inches, timeOuts);
    }

    public void goForward(double inches, double timeOuts) {
        encoderMovement(DRIVE_SPEED, -inches, -inches, -inches, -inches, timeOuts);
    }

}

}
