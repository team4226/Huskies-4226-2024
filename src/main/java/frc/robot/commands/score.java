package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.scoreWheels;

public class score extends Command {
    
    private final scoreWheels m_score;
    
    public score(scoreWheels subsystem){    
        m_score = subsystem;
        addRequirements(m_score);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_score.runShooter(Constants.ShooterConstants.topShooterMotorVelocity, Constants.ShooterConstants.bottomShooterMotorVelocity);

    }

    @Override
    public void end(boolean interrupted) {
        m_score.stop();
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
