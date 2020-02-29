/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name="(BLUE)BlockAuto&Repo")

public class BLUESkystoneAuton extends LinearOpMode {
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
    public ColorSensor ColorSensor1;
    public ColorSensor ColorSensor2;
//    public BNO055IMU IMU;
    public String autonomous;

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.6;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.4;
    static final double TURN_SPEED = 0.5;

    Orientation angles;

    @Override
    public void runOpMode() {

//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//        parameters.loggingEnabled      = true;
//        parameters.loggingTag          = "IMU";
//        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//
//        IMU.initialize(parameters);


        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");
        LeftPull = hardwareMap.servo.get("LeftPull");
        RightPull = hardwareMap.servo.get("RightPull");

        ColorSensor1 = hardwareMap.colorSensor.get("sensor_color1");
       // ColorSensor2 = hardwareMap.colorSensor.get("sensor_color2");

        //IMU = hardwareMap.get(BNO055IMU.class, "imu");

        LeftB.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftF.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftPull.setPosition(0);
        RightPull.setPosition(.90);

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

        while (opModeIsActive()){

            while ((ColorSensor1.red() & ColorSensor1.blue()) >= 20){
                simpLeftStrafe();
            } stop();
        }



//        RightPull.setPosition(.60);
//        sleep(800);
//        goForward(29,2.0);
//        StrafeRight(23,2.0);
//        if (ColorSensor1.blue() > 50){
//            //autonomous 1
//            autonomous = "1";
//        }
//        StrafeRight(6,2.0);
//        if (ColorSensor1.blue() > 100){
//            //autonomous 2
//            autonomous = "2";
//        } else {
//            autonomous = "3";
//        }
//        StrafeRight(6, 2.0);
//
//        if (autonomous == "1") {
//
//        } else if (autonomous == "2") {
//
//        } else if (autonomous == "3") {
//
//        }
//        // autonomous 3

        telemetry.addData("Path", "Complete");
        telemetry.update();
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



    public  void Succ (){
        Right_wheel.setPower(.8); Left_wheel.setPower(.8);
        goForward(2,62.0);
    }

    public void simpLeftStrafe (){
        LeftF.setPower(-.4);
        LeftB.setPower(.4);
        RightF.setPower(.4);
        RightB.setPower(-.4);
    }
    public void simpRightStrafe (){
        LeftF.setPower(.4);
        LeftB.setPower(-.4);
        RightF.setPower(-.4);
        RightB.setPower(.4);
    }

    public void StrafeLeft ( double inches, double timeoutS)
    {
        encoderMovement(DRIVE_SPEED, inches, -inches, -inches, inches, timeoutS);
    }
    public void StrafeRight ( double inches, double timeoutS)
    {
        encoderMovement(DRIVE_SPEED, -inches, inches, inches, -inches, timeoutS);
    }
    public void turnRight ( double inches,double timeoutS)
    {
        encoderMovement(DRIVE_SPEED, -inches, inches, -inches, inches, timeoutS);
    }
    public void turnLeft ( double inches, double timeoutS)
    {
        encoderMovement(DRIVE_SPEED, inches, -inches, inches, -inches, timeoutS);
    }
    public void goBackward ( double inches, double timeoutS)
    {
        encoderMovement(DRIVE_SPEED, inches, inches, inches, inches, timeoutS);
    }
    public void goForward(double inches, double timeoutS)
    {
        encoderMovement(DRIVE_SPEED,-inches,-inches,-inches,-inches,timeoutS);
    }

}