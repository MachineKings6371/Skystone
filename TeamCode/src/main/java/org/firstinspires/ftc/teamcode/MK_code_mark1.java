package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@Autonomous (name = "Parking(Right)")
public class MK_code_mark1 extends LinearOpMode {

    public DcMotor LeftM;
    public DcMotor RightM;

    @Override
    public void runOpMode() throws InterruptedException {

        LeftM = hardwareMap.dcMotor.get("LeftM");
        RightM = hardwareMap.dcMotor.get("RightM");

        waitForStart();

        goForward(.5, 1250);
        turnRight(.5, 1150);
        goForward(.5, 1000);
    }


        public void goForward ( double power, int time)
        {
            RightM.setPower(-.5);
            LeftM.setPower(.5);
            sleep(time);
        }
        public void goBackwards ( double power, int time)
        {
            RightM.setPower(power);
            LeftM.setPower(-power);
            sleep(time);
        }
        public void turnLeft ( double power, int time)
        {
            RightM.setPower(-power);
            LeftM.setPower(-power);
    sleep(time);
}
        public void turnRight ( double power, int time)
        {
            RightM.setPower(power);
            LeftM.setPower(power);
            sleep(time);
        }


}
