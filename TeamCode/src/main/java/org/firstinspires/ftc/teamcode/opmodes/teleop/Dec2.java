package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.EncoderInstructionSet;
import org.firstinspires.ftc.teamcode.exceptions.UnfoundHardwareException;
import org.firstinspires.ftc.teamcode.opmodes.MasterOpMode;
import org.firstinspires.ftc.teamcode.robots.TetrixConstants;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotEncodedTankDrive;
import org.firstinspires.ftc.teamcode.robots.configurations.RobotTankDriveBeacon;
import org.firstinspires.ftc.teamcode.util.Converter;

/**
 * Created by Spencer on 11/30/2016.
 */
@TeleOp(name="Dec2 TeleOp", group="Competition")
@Disabled
public class Dec2 extends MasterOpMode {

    public RobotTankDriveBeacon robot;

    @Override
    public void setup() {
        setRobot(new RobotTankDriveBeacon());
        robot = (RobotTankDriveBeacon) robotI;
    }

    @Override
    public void or_init() {
        runtime.reset();
    }

    @Override
    public void or_loop() {
        if (gh1.onDown("Y"))
            robot.reverseDrive();

        if (gamepad1.right_trigger > 0.25)
            robot.beaconSwitcher.increment(0.01);
        if (gamepad1.left_trigger > 0.25)
            robot.beaconSwitcher.increment(-0.01);

        robot.leftDrive.motor.setPower(-gamepad1.left_stick_y);
        robot.rightDrive.motor.setPower(-gamepad1.right_stick_y);

        telemetry.addData("Servo Pos",robot.beaconSwitcher.servo.getPosition());
        telemetry.addData("Red,Blue", "%d,%d", robot.colorSensor.red(), robot.colorSensor.blue());

        String col = "";
        if (robot.colorSensor.red() - robot.colorSensor.blue() > 100)
            col = "RED";
        else if (robot.colorSensor.red() - robot.colorSensor.blue() < -100)
            col = "BLUE";
        else
            col = "UNKNOWN";

        telemetry.addData("Color",col);
    }
}
