package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp
 */
@TeleOp(name = "Driving Period")
public class RTeleOPREA extends OpMode {
    Servo jewel_vertical;
     DcMotor motor_right;
     DcMotor motor_left;
     DcMotor motor_backleft;
     DcMotor motor_backright;
     Servo servo_leftarm;
     Servo servo_rightarm;
     Servo servo_middleservo;
    //double currentpos;

    @Override
    public void init() {
        jewel_vertical = hardwareMap.servo.get("jewel_vertical");
        servo_leftarm = hardwareMap.servo.get("left_glyph");
        servo_rightarm = hardwareMap.servo.get("right_glyph");
        servo_middleservo = hardwareMap.servo.get("lift_glyph");
        motor_left = hardwareMap.dcMotor.get("left_drive");
        motor_backleft = hardwareMap.dcMotor.get("left_back_drive");
        motor_backright = hardwareMap.dcMotor.get("right_back_drive");
        motor_right = hardwareMap.dcMotor.get("right_drive");

        motor_left.setDirection(DcMotorSimple.Direction.REVERSE);
        motor_backleft.setDirection(DcMotorSimple.Direction.REVERSE);

    }


    @Override
    public void loop() {
        float x1 = -gamepad1.left_stick_x, y1 = gamepad1.left_stick_y;
        float x2 = gamepad1.right_stick_x;

        jewel_vertical.setPosition(-.5);

        // Reset variables
        float leftFrontPower = 0;
        float leftBackPower = 0;
        float rightFrontPower = 0;
        float rightBackPower = 0;
        // Handle regular movement
        leftFrontPower += y1;
        leftBackPower += y1;
        rightFrontPower += y1;
        rightBackPower += y1;

        // Handle strafing movement
        leftFrontPower += x1;
        leftBackPower -= x1;
        rightFrontPower -= x1;
        rightBackPower += x1;

        // Handle turning movement

        leftFrontPower += x2;
        leftBackPower += x2;
        rightFrontPower -= x2;
        rightBackPower -= x2;

        // Scale movement
        double max = Math.max(Math.abs(leftFrontPower), Math.max(Math.abs(leftBackPower),
                Math.max(Math.abs(rightFrontPower), Math.abs(rightBackPower))));

        if (max > 1) {
            leftFrontPower = (float)Range.scale(leftFrontPower, -max, max, -.5, .5);
            leftBackPower = (float)Range.scale(leftBackPower, -max, max, -.5, .5);
            rightFrontPower = (float)Range.scale(rightFrontPower, -max, max, -.5, .5);
            rightBackPower = (float)Range.scale(rightBackPower, -max, max, -.5, .5);
        }

        motor_backleft.setPower(-leftBackPower);
        motor_left.setPower(-leftFrontPower);
        motor_right.setPower(-rightFrontPower);
        motor_backright.setPower(-rightBackPower);

        // Here you set the motors' power to their respected power double.


        // start of controller movements
        if(gamepad2.dpad_down) {
            servo_middleservo.setPosition(.64);
        }
        if(gamepad2.dpad_up) {
            servo_middleservo.setPosition(0);
        }
        if(gamepad2.a) {
            servo_leftarm.setPosition(.55);
            servo_rightarm.setPosition(.47);

        }
        if(gamepad2.b){
            servo_rightarm.setPosition(.78);
            servo_leftarm.setPosition(.2);

        }


        // end of loop
    }


    @Override
    public void stop() {
    }
    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.04, 0.08, 0.9, 0.11, 0.14, 0.17, 0.23, 0.29, 0.35, 0.42, 0.49, 0.59, 0.71, 0.84, 0.99, 1.00};
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        }
        if (index > 16) {
            index = 16;
        }
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }
        return dScale;
    }
}