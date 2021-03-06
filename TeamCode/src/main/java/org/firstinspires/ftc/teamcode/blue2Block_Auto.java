package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

@Autonomous(name="2Block(BLUE)")

public class blue2Block_Auto extends LinearOpMode {
    RobotContainer robot = new RobotContainer();
    public DcMotor LeftF;
    public DcMotor RightF;
    public DcMotor LeftB;
    public DcMotor RightB;
    public Servo Clamp;
    public Servo LeftPull;
    public Servo RightPull;
    public DcMotor Left_wheel;
    public DcMotor Right_wheel;


    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.6;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.4;
    static final double TURN_SPEED = 0.5;


    @Override
    public void runOpMode() {


        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");
        LeftPull = hardwareMap.servo.get("LeftPull");
        RightPull = hardwareMap.servo.get("RightPull");
        Left_wheel = hardwareMap.dcMotor.get("Left_wheel");
        Left_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Right_wheel = hardwareMap.dcMotor.get("Right_wheel");
        Right_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Clamp = hardwareMap.servo.get("Clamp");

        LeftB.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftF.setDirection(DcMotorSimple.Direction.REVERSE);

//        LeftPull.setPosition(0);
//        RightPull.setPosition(.90);

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

        waitForStart();


        Clamp.setPosition(.35);
        sleep(500);
        goForward(29,2.0); //align succ wheel with first block
        Succ();
        Clamp.setPosition(.15);
        sleep(500);
        goBackward(38,2.0);
        stopIntake();
        turnLeft(20.7, 3.0);
        goForward(45,3.0);
        Clamp.setPosition(.40);
        sleep(500);
        spitt();
        stopIntake();


        goBackwardsP2(43,2.0);
        turnRight(20.7,2.0);
        goForward(20,2.0);
        Succ();
        Clamp.setPosition(.15);
        sleep(500);
        goBackward(44,2.0);
        stopIntake();
        turnLeft(21,2.0);
        goForward(55,2.0);
        Clamp.setPosition(.40);
        sleep(500);
        spitt();
        stopIntake();

//        goBackward(25,2.0);
//        turnLeft(40,3.0);
//        StrafeRight(38,3.0);
//        Succ2();
//        Clamp.setPosition(.15);
//        sleep(500);
//        stopIntake();
//        StrafeLeft(35,3.0);
//        turnRight(40,3.0);
//        StrafeRight(10,2.0);
//        goForward(54,2.0);
//        Clamp.setPosition(.40);
//        sleep(500);
//        spitt();
//        stopIntake();
//        goBackward(8,2.0);


        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    public  void Succ (){
        Right_wheel.setPower(-1);
        Left_wheel.setPower(-1);
        encoderMovement(.2,-15,-15,-15,-15,2.0);
    }
    public  void Succ2 (){
        Right_wheel.setPower(-1);
        Left_wheel.setPower(-1);
        encoderMovement(.2,-11,-11,-11,-11,2.0);
    }
    public  void Succ3 (){
        Right_wheel.setPower(-1);
        Left_wheel.setPower(-1);
        encoderMovement(.2,-10,-10,-10,-10,2.0);
    }
    public void spitt () {

        Right_wheel.setPower(1);
        Left_wheel.setPower(1);
        encoderMovement(.4,15,15,15,15,2.0);
    }

    public  void stopIntake(  ) {
        Right_wheel.setPower(0);
        Left_wheel.setPower(0);
    }

    public void Pause (int time) {
        LeftF.setPower(0);
        LeftB.setPower(0);
        RightF.setPower(0);
        RightB.setPower(0);
        sleep(time);
    }

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
    public void simpLeftTurn (){
        LeftF.setPower(.4);
        LeftB.setPower(.4);
        RightF.setPower(-.4);
        RightB.setPower(-.4);
    }
    public void simpRightTurn (){
        LeftF.setPower(-.4);
        LeftB.setPower(-.4);
        RightF.setPower(.4);
        RightB.setPower(.4);
    }

    public void StrafeLeft ( double inches, double timeoutS)
    {
        encoderMovement(.5, inches, -inches, -inches, inches, timeoutS);
    }
    public void StrafeRight ( double inches, double timeoutS)
    {
        encoderMovement(.5, -inches, inches, inches, -inches, timeoutS);
    }
    public void turnRight ( double inches,double timeoutS)
    {
        encoderMovement(.5, -inches, inches, -inches, inches, timeoutS);
    }
    public void turnLeft ( double inches, double timeoutS)
    {
        encoderMovement(.5, inches, -inches, inches, -inches, timeoutS);
    }
    public void goBackward ( double inches, double timeoutS)
    {
        encoderMovement(.5, inches, inches, inches, inches, timeoutS);
    }
    public void goBackwardsP2 ( double inches, double timeoutS)
    {
        encoderMovement(.7, inches, inches, inches, inches, timeoutS);
    }
    public void goForward(double inches, double timeoutS)
    {
        encoderMovement(.5,-inches,-inches,-inches,-inches,timeoutS);
    }
}