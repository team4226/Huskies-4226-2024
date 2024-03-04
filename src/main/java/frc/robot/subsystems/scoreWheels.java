package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.score;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.*;

public class scoreWheels extends SubsystemBase {
    
    private CANSparkMax scoreTop = new CANSparkMax(Constants.ShooterConstants.topShooterMotorID, MotorType.kBrushless);
    private CANSparkMax scoreBottom = new CANSparkMax(Constants.ShooterConstants.bottomShooterMotorID, MotorType.kBrushless);
    private double k_P, k_I, k_D, k_FF;
    private SparkPIDController m_topPidController, m_bottomPidController;


    public scoreWheels(){
        k_P = Constants.ShooterConstants.shooter_P;
        k_I = Constants.ShooterConstants.shooter_I;
        k_D = Constants.ShooterConstants.shooter_D;
        k_FF = Constants.ShooterConstants.shooter_FF;

        scoreTop.restoreFactoryDefaults();
        scoreTop.setInverted(false);
        scoreTop.setIdleMode(IdleMode.kCoast);

        scoreBottom.restoreFactoryDefaults();
        scoreBottom.setInverted(true);
        scoreBottom.setIdleMode(IdleMode.kCoast);

        m_topPidController = scoreTop.getPIDController();
        m_bottomPidController = scoreBottom.getPIDController();

        m_topPidController.setP(k_P);
        m_topPidController.setI(k_I);
        m_topPidController.setD(k_D);
        m_topPidController.setFF(k_FF);

        m_bottomPidController.setP(k_P);
        m_bottomPidController.setI(k_I);
        m_bottomPidController.setD(k_D);
        m_bottomPidController.setFF(k_FF);

        m_topPidController.setOutputRange(Constants.minMaxOutputConstants.minOutput, Constants.minMaxOutputConstants.maxOutput);
        m_topPidController.setOutputRange(Constants.minMaxOutputConstants.minOutput, Constants.minMaxOutputConstants.maxOutput);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Top Motor Speed",scoreTop.getEncoder().getVelocity());
        SmartDashboard.putNumber("Bottom Motor Speed",scoreBottom.getEncoder().getVelocity());
        //System.out.println(scoreTop.getEncoder().getVelocity());
        //System.out.println(scoreBottom.getEncoder().getVelocity());

    }

    public void mymotorrun(double speed){
        scoreTop.set(speed);
        scoreBottom.set(speed);
    }

    public void runShooter(int topVelocity, int bottomVelocity){
        m_bottomPidController.setReference(bottomVelocity, ControlType.kVelocity);
        m_topPidController.setReference(topVelocity,ControlType.kVelocity);

    }

    public void stop(){
        m_bottomPidController.setReference(0, ControlType.kVelocity);
        m_topPidController.setReference(0,ControlType.kVelocity);
    }
}
