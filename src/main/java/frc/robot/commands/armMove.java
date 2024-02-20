package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.arm;
import edu.wpi.first.wpilibj.XboxController;

public class armMove extends Command {
    
    private final arm m_armLift;
    private final XboxController xboxControl;
    
    public armMove(arm subsystem, XboxController xbox){    

        m_armLift = subsystem;
        addRequirements(m_armLift);

        xboxControl = xbox;
        
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        
        if (Math.abs(xboxControl.getRightY()) > 0.01){

            m_armLift.mymotorrun(xboxControl.getRightY());

        }
        
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
