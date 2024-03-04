package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticsSubsystem extends SubsystemBase {
    private DoubleSolenoid doubleSolenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,0,1);
    private DoubleSolenoid doubleSolenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,2,3);
    private final Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);

public void startCompressor(){
    m_compressor.enableDigital();
    System.out.println("Enabling Compressor");
}

public void extendPiston(){
    doubleSolenoid1.set(DoubleSolenoid.Value.kForward);
    doubleSolenoid2.set(DoubleSolenoid.Value.kForward);
}

public void retractPiston(){
    doubleSolenoid1.set(DoubleSolenoid.Value.kReverse);
    doubleSolenoid2.set(DoubleSolenoid.Value.kReverse);
}

}

