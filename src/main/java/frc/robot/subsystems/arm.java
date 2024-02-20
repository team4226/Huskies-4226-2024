package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeHead;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;

public class arm extends SubsystemBase {
    
    private CANSparkMax arm;

    public arm(){
    
        arm = new CANSparkMax(IntakeHead.arm, MotorType.kBrushless);
        arm.setInverted(false);
        arm.setIdleMode(IdleMode.kBrake);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void mymotorrun(double speed){

        arm.set(speed);
    }

}
