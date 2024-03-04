package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class leftPnuematics {
      // DoubleSolenoid corresponds to a double solenoid.
  // In this case, it's connected to channels 1 and 2 of a PH with the default CAN ID.
  private final DoubleSolenoid m_doubleSolenoid =
  new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 2);
  m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
  m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
}
