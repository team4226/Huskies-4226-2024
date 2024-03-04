package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PneumaticsSubsystem;

public class retractPiston extends Command{

  private PneumaticsSubsystem m_pneumatics;

  public retractPiston(PneumaticsSubsystem m_pneumatics) {
    this.m_pneumatics=m_pneumatics;
    addRequirements(m_pneumatics);
  }

  public void initialize(){

  }

  public void execute(){
    m_pneumatics.retractPiston();
  }

  public void end(boolean interrupted){

  }

  public boolean isFinished(){
    return false;
  }
}