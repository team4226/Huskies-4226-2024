package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake;

public class in extends Command {
    
    private final intake m_intake;
    
    public in(intake subsystem){    

        m_intake = subsystem;
        addRequirements(m_intake);
        
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_intake.mymotorrun(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.mymotorrun(0);
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
