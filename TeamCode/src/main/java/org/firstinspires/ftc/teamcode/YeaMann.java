package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@Autonomous (name = "Best_Code")
public class YeaMann extends LinearOpMode {
    public DcMotor myMom;
    public DcMotor myDad;
    public DcMotor myUncle;
    public DcMotor mySister;
    public DcMotor nigBen;

    @Override
    public void runOpMode() throws InterruptedException {
        myMom = hardwareMap.dcMotor.get ("motherClark");//front right
        myDad = hardwareMap.dcMotor.get("daddyRuben");//front left
        myUncle = hardwareMap.dcMotor.get("tioCaleb");//back right
        mySister = hardwareMap.dcMotor.get("sisterRyssa");//back left
        nigBen = hardwareMap.dcMotor.get("nigBen");

        waitForStart();

        myMom.setPower(.5);
        myDad.setPower(.5);
        myUncle.setPower(-.5);
        mySister.setPower(-.5);
        sleep(5000);
        //go forward for 5 seconds

        myMom.setPower(.5);
        myDad.setPower(.5);
        myUncle.setPower(.5);
        mySister.setPower(.5);
        sleep(4000);
        //pivot turn

        myMom.setPower(0);
        myDad.setPower(0);
        myUncle.setPower(.5);
        mySister.setPower(.5);
        sleep(5000);


    }
    public void turn_left (double power, int sleep)
    {
        myMom.setPower(.5);
        myDad.setPower(.5);
        myUncle.setPower(.5);
        mySister.setPower(.5);
    }

    public void turn_right (double power, int sleep)
    {
        myMom.setPower(-.5);
        myDad.setPower(-.5);
        mySister.setPower(-.5);
        myUncle.setPower(-.5);
    }

    public void go_backwards (double power, int sleep)
    {
        myMom.setPower(-.5);
        myDad.setPower(-.5);
        mySister.setPower(.5);
        myUncle.setPower(.5);
    }
    public void go_forward (double power, int sleep)
    {
        myMom.setPower(.5);
        myDad.setPower(-5);
        mySister.setPower(-.5);
        myUncle.setPower(-.5);
    }











}

/*

 */
