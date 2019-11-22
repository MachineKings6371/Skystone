package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "Parking(")
public class duel_auton extends LinearOpMode {

        public DcMotor LeftM;
        public DcMotor RightM;

        @Override
        public void runOpMode() throws InterruptedException {

            LeftM = hardwareMap.dcMotor.get("LeftM");
            RightM = hardwareMap.dcMotor.get("RightM");
            waitForStart();

            goForward(.5, 1200);
            turnLeft(.5, 1050);
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
            RightM.setPower(.5);
            LeftM.setPower(-.5);
            sleep(time);
        }
        public void turnLeft ( double power, int time)
        {
            RightM.setPower(-.5);
            LeftM.setPower(-.5);
            sleep(time);
        }
        public void turnRight ( double power, int time)
        {
            RightM.setPower(.5);
            LeftM.setPower(.5);
            sleep(time);
        }


}


