package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp (name = "test_tele")
public class Ruben_tele extends OpMode
{
    public DcMotor fr;
    public DcMotor fl;
    public DcMotor br;
    public DcMotor bl;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("Front_Right");
        fl = hardwareMap.dcMotor.get("Front_Left");
        br = hardwareMap.dcMotor.get("Back_Right");
        bl = hardwareMap.dcMotor.get("Back_Left");
    }

    @Override
    public void loop() {

       if (Math.abs(gamepad1.right_stick_y) > .1)
       {
           fr.setPower(-gamepad1.right_stick_y);
           br.setPower(-gamepad1.right_stick_y);
       }

       else{
           fr.setPower(0);
           br.setPower(0);
       }

       if (Math.abs(gamepad1.left_stick_y) > .1)
       {
           fl.setPower(gamepad1.left_stick_y);
           bl.setPower(gamepad1.left_stick_y);
       }

       else{
           fl.setPower(0);
           bl.setPower(0);
       }



    }
}
