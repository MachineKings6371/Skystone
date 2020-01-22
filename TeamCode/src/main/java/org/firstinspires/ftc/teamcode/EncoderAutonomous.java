package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@Autonomous(name="encoder_auton")
public class EncoderAutonomous extends LinearOpMode {
    public DcMotor LeftF;
    public DcMotor RightF;
    public DcMotor LeftB;
    public DcMotor RightB;
    public Servo LeftPull;
    public Servo RightPull;


    /* Declare OpMode members. */

    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 537.6 ;    // eg: TETRIX Motor Encoder
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.4;

    @Override
    public void runOpMode() {
        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");
        LeftPull = hardwareMap.servo.get("LeftPull");
        RightPull = hardwareMap.servo.get("RightPull");

        LeftB.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftF.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftPull.setPosition(0);
        RightPull.setPosition(.80);

        LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Wait for the game to start (driver presses PLAY)//_____________________________________________________________________________________________________________________________________////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        waitForStart();


    }//_________________________________________________________________________________________________________________________________________________________________________________________/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void encoderDrive(double power, int distance, double timeoutS) {

        //reset
        LeftF.setMode(DcMotor.RunMode.RESET_ENCODERS);
        LeftB.setMode(DcMotor.RunMode.RESET_ENCODERS);
        RightF.setMode(DcMotor.RunMode.RESET_ENCODERS);
        RightB.setMode(DcMotor.RunMode.RESET_ENCODERS);

        //set position
        RightF.setTargetPosition(distance);
        RightB.setTargetPosition(distance);
        LeftF.setTargetPosition(distance);
        LeftB.setTargetPosition(distance);

            // Turn On RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //set power
        LeftF.setPower(power);
        LeftB.setPower(power);
        RightF.setPower(power);
        RightB.setPower(power);



        while (opModeIsActive() && (runtime.seconds() < timeoutS) && LeftF.isBusy() && LeftB.isBusy() && RightF.isBusy() && RightB.isBusy()) {
                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d");
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        LeftF.getCurrentPosition(),
                        LeftB.getCurrentPosition());
                        RightF.getCurrentPosition();
                        RightB.getCurrentPosition();
                telemetry.update();
            }
            // Turn off RUN_TO_POSITION
            LeftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
    public void stopDriving (double power, int time){
        RightF.setPower(0);
        RightB.setPower(0);
        LeftF.setPower(0);
        LeftB.setPower(0);
        sleep(250);
    }
    public void goBackward ( double power, int distance)
    {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(power);
        LeftB.setPower(power);

    }
    public void goForward ( double power, int time)
    {
        RightF.setPower(-.5);
        RightB.setPower(-.5);
        LeftF.setPower(-.4);
        LeftB.setPower(-.4);
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
    public void turnRight ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
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
}
