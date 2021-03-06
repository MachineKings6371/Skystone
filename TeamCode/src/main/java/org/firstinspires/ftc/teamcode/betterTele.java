
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.tfod.Timer;


@TeleOp (name = "betterTele")//current mother
public class betterTele extends OpMode {
    RobotContainer robot = new RobotContainer();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        //Base motors and stuff
        //Right side base                                       //
        if (Math.abs(gamepad1.right_stick_y) > .1) {          //
            robot.setRightSide(gamepad1.right_stick_y);
        } else {
            robot.setRightSide(0);
        }
        //Left side base
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            robot.setLeftSide(gamepad1.left_stick_y);
        } else {
            robot.setLeftSide(0);
        }

        //strafing
        if (Math.abs(gamepad1.right_stick_x) > .2) {
            robot.setStrafe(gamepad1.right_stick_x);
        } else {
            robot.setStrafe(0);
        }

        //wheel intake
        if ((gamepad1.left_trigger) > .1) {
            robot.setIntake(gamepad1.left_trigger);
        } else if ((gamepad1.right_trigger) > .1) {
            robot.setIntake(-gamepad1.right_trigger);
        } else {
            robot.setIntake(0);
        }


        //open clamp
        if (gamepad2.y) {
            robot.Clamp.setPosition(.15);//951 923 5464s
        }
        //close clamp
        if (gamepad2.x) {

            robot.Clamp.setPosition(.37);
        }


        //Rotates dah Arm
        if (Math.abs(gamepad2.left_trigger) > .1) {
            robot.setArmTurn(gamepad2.left_trigger / 2);
        } else if (Math.abs(gamepad2.right_trigger) > .1) {
            robot.setArmTurn(-gamepad2.right_trigger / 2);
        } else {
            robot.setArmTurn(0);
        }


        //Clamp lift
        if (Math.abs(gamepad2.left_stick_y) > .1) {
            robot.setClampLift(-gamepad2.left_stick_y / 1.5);
        } else if (Math.abs(gamepad2.left_stick_y) < .1) {
            robot.setClampLift(gamepad2.left_stick_y / 1.5);
        } else {
            robot.setClampLift(0);
        }

        //Pull Stuff
        if (gamepad1.right_bumper) {          //Ready to throw down w/ hands down

            robot.RightPull.setPosition(.30);
            robot.LeftPull.setPosition(.70);
        }
        if (gamepad1.left_bumper) {           //Puts the hands up
            robot.RightPull.setPosition(1);      //we messed up the directions, right is left and vise versa

            robot.LeftPull.setPosition(.30);
        }
    }
}

