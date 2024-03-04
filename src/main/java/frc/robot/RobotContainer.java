// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.armDown;
import frc.robot.commands.armMove;
import frc.robot.commands.armUp;
import frc.robot.commands.extendPiston;
import frc.robot.commands.in;
import frc.robot.commands.out;
import frc.robot.commands.retractPiston;
import frc.robot.commands.runCompressor;
import frc.robot.commands.score;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.scoreWheels;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.commands.scoreOut;
import frc.robot.commands.scorearm;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import java.util.List;

import com.ctre.phoenix.ButtonMonitor;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  public final SendableChooser<Command> autoChooser;

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve/neo"));

  private arm m_arm = new arm();
  private intake m_intake = new intake();
  private scoreWheels m_scoreWheels = new scoreWheels();
  private PneumaticsSubsystem m_PnuematicsSubsytem = new PneumaticsSubsystem();

  XboxController driverXbox = new XboxController(0);
  XboxController secondaryXbox = new XboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Build an auto chooser. This will use Commands.none() as the default option.
    autoChooser = AutoBuilder.buildAutoChooser();

    //commands from pathplanner
    NamedCommands.registerCommand("Shoot1", new score(m_scoreWheels));
    NamedCommands.registerCommand("PickUp", new in(m_intake).withTimeout(3));

    //new runCompressor(m_PnuematicsSubsytem);

    // Another option that allows you to specify the default auto by its name
    // autoChooser = AutoBuilder.buildAutoChooser("My Default Auto");

    SmartDashboard.putData("Auto Chooser", autoChooser);




    // Configure the trigger bindings
    configureBindings();


    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
                                                                   () -> MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                   () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                OperatorConstants.LEFT_X_DEADBAND),
                                                                   () -> MathUtil.applyDeadband(driverXbox.getRightX(),
                                                                                                OperatorConstants.RIGHT_X_DEADBAND),
                                                                   driverXbox::getYButtonPressed,
                                                                   driverXbox::getAButtonPressed,
                                                                   driverXbox::getXButtonPressed,
                                                                   driverXbox::getBButtonPressed);

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation
    Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverXbox.getRightX(),
        () -> driverXbox.getRightY());

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the angular velocity of the robot
    Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverXbox.getRawAxis(2));

    Command driveFieldOrientedDirectAngleSim = drivebase.simDriveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
        () -> driverXbox.getRawAxis(2));

    drivebase.setDefaultCommand(closedAbsoluteDriveAdv);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */


  private void configureBindings()
  {

        new JoystickButton(driverXbox, XboxController.Button.kStart.value).onTrue((new InstantCommand(drivebase::zeroGyro)));
        new JoystickButton(driverXbox, XboxController.Button.kLeftBumper.value).whileTrue(new in(m_intake));
        new JoystickButton(driverXbox, XboxController.Button.kRightBumper.value).whileTrue(new out(m_intake));
        new JoystickButton(secondaryXbox, XboxController.Button.kBack.value).toggleOnTrue(new InstantCommand(() -> m_arm.home()));
        new JoystickButton(secondaryXbox, XboxController.Button.kB.value).toggleOnTrue(new InstantCommand(() -> m_arm.amp()));
        new JoystickButton(secondaryXbox, XboxController.Button.kA.value).toggleOnTrue(new InstantCommand(() -> m_arm.pickup()));
        //new JoystickButton(secondaryXbox, XboxController.Button.kLeftBumper.value).whileTrue(new in(m_intake));
        //new JoystickButton(secondaryXbox, XboxController.Button.kRightBumper.value).whileTrue(new out(m_intake));

        new JoystickButton(secondaryXbox, XboxController.Button.kRightBumper.value).whileTrue(new score(m_scoreWheels));
        new JoystickButton(secondaryXbox, XboxController.Button.kLeftBumper.value).whileTrue(new scoreOut(m_scoreWheels));
        //new JoystickButton(secondaryXbox, XboxController.Button.kStart.value).whileTrue(new armUp(m_arm));
        //new JoystickButton(secondaryXbox, XboxController.Button.kBack.value).whileTrue(new armDown(m_arm));

        new JoystickButton(secondaryXbox, XboxController.Button.kRightStick.value).toggleOnTrue(new armMove(m_arm, secondaryXbox));
        new JoystickButton(secondaryXbox, XboxController.Button.kX.value).whileTrue(new retractPiston(m_PnuematicsSubsytem));
        new JoystickButton(secondaryXbox, XboxController.Button.kY.value).whileTrue(new extendPiston(m_PnuematicsSubsytem));

        //new JoystickButton(secondaryXbox, XboxController.Axis.kLeftTrigger).whileTrue(new armMove(m_arm, secondaryXbox));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    PathPlannerPath path = PathPlannerPath.fromPathFile("BottomPath");
    //return AutoBuilder.followPath(path);
    return AutoBuilder.followPath(path);
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
