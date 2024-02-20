package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeHead;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;

public class scoreWheels extends SubsystemBase {
    


    private CANSparkMax scoreTop;
    private CANSparkMax scoreBottom;



    public scoreWheels(){
    
        scoreTop = new CANSparkMax(IntakeHead.topthrow, MotorType.kBrushless);
        scoreTop.setInverted(false);
        scoreTop.setIdleMode(IdleMode.kBrake);

        scoreBottom = new CANSparkMax(IntakeHead.botthrow, MotorType.kBrushless);
        scoreBottom.setInverted(false);
        scoreBottom.setIdleMode(IdleMode.kBrake);

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
        scoreTop.set(speed);
        scoreBottom.set(speed);
    }

}
