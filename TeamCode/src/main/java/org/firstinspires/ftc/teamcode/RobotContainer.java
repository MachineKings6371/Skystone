package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotContainer {
    public DcMotor LeftF, RightF, RightB, LeftB, Left_wheel, Right_wheel, ArmRotate, ClampLift;
    public Servo Clamp, LeftPull, RightPull;

    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 537.6;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.4;
    static final double TURN_SPEED = 0.5;
    public String autonomous;

    public void init(HardwareMap hwMap) {
        //Motors
        LeftF = hwMap.dcMotor.get("LeftF");
        RightF = hwMap.dcMotor.get("RightF");
        LeftB = hwMap.dcMotor.get("LeftB");
        RightB = hwMap.dcMotor.get("RightB");
        Left_wheel = hwMap.dcMotor.get("Left_wheel");
        Left_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Right_wheel = hwMap.dcMotor.get("Right_wheel");
        Right_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        ArmRotate = hwMap.dcMotor.get("ArmRotate");
        ArmRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ClampLift = hwMap.dcMotor.get("ClampLift");
        Clamp = hwMap.servo.get("Clamp");



        //Servos
        LeftPull = hwMap.servo.get("LeftPull");
        RightPull = hwMap.servo.get("RightPull");

    }

    //Basic Movement
    public void setLeftSide(double power) {
        LeftF.setPower(-power);
        LeftB.setPower(-power);
    }
    public void setRightSide(double power) {
        RightF.setPower(power);
        RightB.setPower(power);
    }
    public void setStrafe(double power) {
        RightF.setPower(power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(-power);
    }

    //Intake
    public void setIntake(double power) {
        Left_wheel.setPower(power);
        Right_wheel.setPower(power);
    }

    //Arm rotation & clamping
    public void setArmTurn(double power) {
        ArmRotate.setPower(power);
    }
    public void setClampLift(double power) {
        ClampLift.setPower(power);
    }

    //Pulls

    public void encoderMovement(double speed,
                                double FrontLeftInches, double FrontRightInches, double BackLeftInches, double BackRightInches,
                                double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;


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

