package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeHead;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;

public class intake extends SubsystemBase {
    

    private CANSparkMax intakeMotor;
    //public CANSparkMax intakeMotor2;
    //public CANSparkMax intakeMotor3;



    public intake(){
    
        //intakeMotor = new CANSparkMax(TestArm.botthrow, MotorType.kBrushless);
        //intakeMotor.setInverted(false);
        //intakeMotor.setIdleMode(IdleMode.kBrake);

        intakeMotor = new CANSparkMax(IntakeHead.intake, MotorType.kBrushless);
        intakeMotor.setInverted(false);
        intakeMotor.setIdleMode(IdleMode.kBrake);

        //intakeMotor3 = new CANSparkMax(TestArm.intake, MotorType.kBrushless);
        //intakeMotor3.setInverted(false);
        //intakeMotor3.setIdleMode(IdleMode.kBrake);

        //intakeMotor2.follow(intakeMotor);
        //intakeMotor3.follow(intakeMotor, true);
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
        intakeMotor.set(speed);
    }

}
