package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;

public class intake extends SubsystemBase {
    

    CANSparkMax intakeMotor = new CANSparkMax(Constants.IntakeConstants.intakeMotorID, MotorType.kBrushless);
    private SparkPIDController m_intakePidController;
    public double k_kP, k_kI, k_kD, k_kFF;
    public int intakeVelocity, intakeVelocityRev;

    public intake(){
        k_kP = Constants.IntakeConstants.intake_P;
        k_kI = Constants.IntakeConstants.intake_I;
        k_kD = Constants.IntakeConstants.intake_D;
        k_kFF = Constants.IntakeConstants.intake_FF;

        intakeMotor.restoreFactoryDefaults();

        m_intakePidController = intakeMotor.getPIDController();
        m_intakePidController.setP(k_kP);
        m_intakePidController.setI(k_kI);
        m_intakePidController.setD(k_kD);
        m_intakePidController.setFF(k_kFF);

        //m_intakePidController.setOutputRange(Constants.minMaxOutputConstants.minOutput,Constants.minMaxOutputConstants.maxOutput);

        intakeMotor.setInverted(false);
        intakeMotor.setIdleMode(IdleMode.kCoast);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("Intake Motor Velocity",intakeMotor.getEncoder().getVelocity());
        
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void mymotorrun(double speed){
        intakeMotor.set(speed);
    }

    //int velocity sets velocity of motor
    public void runIntake(int velocity){
        m_intakePidController.setReference(velocity,CANSparkBase.ControlType.kVelocity);
    }

    public void stop(){
        m_intakePidController.setReference(0, CANSparkBase.ControlType.kVelocity);
    }

}
