package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Hello World")
public class EncoderTest2 extends LinearOpMode{
    public void runOpMode(){
        DcMotor leftDrive;
        DcMotor rightDrive;
        DcMotor leftBackDrive;
        DcMotor rightBackDrive;

        leftDrive = (DcMotor) hardwareMap.get("left_drive");
        rightDrive = (DcMotor) hardwareMap.get("right_drive");
        rightBackDrive = (DcMotor) hardwareMap.get("right_back_drive");
        leftBackDrive = (DcMotor) hardwareMap.get("left_back_drive");

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        rightDrive.setTargetPosition((rightDrive.getCurrentPosition() + (int) 4000));
        leftDrive.setTargetPosition((leftDrive.getCurrentPosition() + (int) 4000));
        rightBackDrive.setTargetPosition((rightBackDrive.getCurrentPosition() + (int) 4000));
        leftBackDrive.setTargetPosition((leftBackDrive.getCurrentPosition() + (int) 4000));

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDrive.setPower(1);
        leftBackDrive.setPower(1);
        rightDrive.setPower(1);
        rightBackDrive.setPower(1);
        while (opModeIsActive() && leftBackDrive.isBusy() && rightBackDrive.isBusy() &&
                rightDrive.isBusy() && leftDrive.isBusy()) {
                telemetry.addData("Running", "Encoders");
                telemetry.update();
        }
    }
}