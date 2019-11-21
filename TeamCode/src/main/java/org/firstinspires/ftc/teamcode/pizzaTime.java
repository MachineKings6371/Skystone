package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="encoder_auton")
public class pizzaTime extends LinearOpMode {
    public DcMotor LeftF;
    public DcMotor RightF;
    public DcMotor LeftB;
    public DcMotor RightB;


    /* Declare OpMode members. */

    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {
        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        LeftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                LeftF.getCurrentPosition(),
                RightB.getCurrentPosition());
                LeftB.getCurrentPosition();
                RightF.getCurrentPosition();
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  48,  48, 5.0);
        encoderDrive(TURN_SPEED,   12, -12, 4.0);
        encoderDrive(DRIVE_SPEED, -24, -24, 4.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = LeftB.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newLeftTarget = LeftF.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = RightF.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = RightB.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);

            RightF.setTargetPosition(newRightTarget);
            RightB.setTargetPosition(newRightTarget);
            LeftF.setTargetPosition(newLeftTarget);
            LeftB.setTargetPosition(newLeftTarget);

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

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) && LeftF.isBusy() && LeftB.isBusy() && RightF.isBusy() && RightB.isBusy()) {



                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
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

            //  sleep(250);   // optional pause after each move
        }
    }
}
