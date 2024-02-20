package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm;

public class armUp extends Command {
    
    private final arm m_armLift;
    
    public armUp(arm subsystem){    

        m_armLift = subsystem;
        addRequirements(m_armLift);
        
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        m_armLift.mymotorrun(-0.5);
    }

    @Override
    public void end(boolean interrupted) {
        m_armLift.mymotorrun(0);
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
