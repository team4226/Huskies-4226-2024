package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.scoreWheels;

public class scoreOut extends Command {
    
    private final scoreWheels m_scoreOut;
    
    public scoreOut(scoreWheels subsystem){    
        m_scoreOut = subsystem;
        addRequirements(m_scoreOut);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_scoreOut.runShooter(-Constants.ShooterConstants.topShooterMotorVelocity, -Constants.ShooterConstants.bottomShooterMotorVelocity);
    }

    @Override
    public void end(boolean interrupted) {
        m_scoreOut.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}