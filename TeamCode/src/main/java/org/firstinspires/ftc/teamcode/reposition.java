package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "Reposition(Please)")
public class reposition extends LinearOpMode {

    public DcMotor LeftF;
    public DcMotor RightF;
    public DcMotor LeftB;
    public DcMotor RightB;

    @Override
    public void runOpMode() throws InterruptedException {
        LeftF = hardwareMap.dcMotor.get("LeftF");
        RightF = hardwareMap.dcMotor.get("RightF");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightB = hardwareMap.dcMotor.get("RightB");

        waitForStart();


    }

    public void goForward ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void goBackwards ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
        sleep(time);
    }
    public void turnLeft ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(-power);
        LeftF.setPower(-power);
        LeftB.setPower(-power);
        sleep(time);
    }
    public void turnRight ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(power);
        LeftF.setPower(power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void straifRight ( double power, int time)
    {
        RightF.setPower(power);
        RightB.setPower(-power);
        LeftF.setPower(-power);
        LeftB.setPower(power);
        sleep(time);
    }
    public void StraifLeft ( double power, int time)
    {
        RightF.setPower(-power);
        RightB.setPower(power);
        LeftF.setPower(power);
        LeftB.setPower(-power);
        sleep(time);
    }
}
