
package frc.robot.lib;

/**
 * Add your docs here.
 */
public class PIDBase {
    //PID Base Code
    //Written By: Rishee Nithyananda 
    //01/20/2021

    public double proportionalGain = 0;
    public double integralGain = 0;
    public double derivativeGain = 0;
    public double integral_charge = 0;
  
    //errors outside of the bounds result in integrator being shut off.
    public double upperBoundLimit = 0;
    public double lowerBoundLimit = 0;
  
    public double upperDeadbandLimit = 0;
    public double lowerDeadbandLimit = 0;
  
    public double maxOutput = 0;
    public double minOutput = 0;
  
    public double output = 0;
    //public double input = 0;
    public double error = 0;
  
    public double derivativeCalculation = 0;
  
    public double previousError = 0;
  
    public double deltaTime = 0.02;
  
    public double cmd = 0;
    public double feed = 0;
  
    public boolean deadband_active = false;
  
    // These are used to turn off the limiter bounds and the deadband 2/15/20
    public boolean enableBound = true;
    public boolean enableDeadBand = true;
  
    public static int deadband_counter = 0;
  
    // Called just before this Command runs the first time to set your values
    public void initialize(double _P,double _I, double _D,
                           double Bound_Limit,double Deadband,double MaxOutput) {
      proportionalGain = _P;
      integralGain = _I;
      derivativeGain = _D;
      
      upperBoundLimit = +Bound_Limit;
      lowerBoundLimit = -Bound_Limit;
    
      upperDeadbandLimit = +Deadband;
      lowerDeadbandLimit = -Deadband;
    
      maxOutput = +MaxOutput;
      minOutput = -MaxOutput;
    
      output = 0;
      error = 0;
    
      derivativeCalculation = 0;
      integral_charge = 0;
      previousError = 0;
    
      deltaTime = 0.02;
  
      deadband_active = false; 
  
      enableBound = true;
      enableDeadBand = true;
      
    }
  
    public void initialize2(double _P,double _I, double _D,
                    double Bound_Limit,double Deadband,double MaxOutput, boolean enable_Bound, boolean enable_DeadBand) { 
      // We are implementing this function to turn off the bound function and the dead band                
      initialize(_P, _I, _D, Bound_Limit, Deadband, MaxOutput);
  
      enableBound = enable_Bound;
      enableDeadBand = enable_DeadBand;
  
  
    }
    // Called repeatedly when this Command is scheduled to run
    // This is the function that takes in what you told the motors to do and 
    // what the motors actually did to create what we know as PID control
    public double execute(double command, double feedback) {
      cmd = command;
      feed = feedback;
  
      //calculate the error
      error = command - feedback;
  
      // calculate rate of change of error
      derivativeCalculation = (error - previousError) / deltaTime;
      
      // save internal error's state for next time
      previousError = error; 
  
      // If inside deadband, don't integrate the error
      if (enableDeadBand == true) {
        if((error < upperDeadbandLimit) && (error > lowerDeadbandLimit)) {
          deadband_counter++;
          if(deadband_counter > 13) { // 13 = 50 / 4 = 50 ms split into 4 quarters
            deadband_active = true;
            deadband_counter = 13;
          }
        }
      }
      
      // bound check
      if (enableBound == true) {
        if((error < upperBoundLimit) && (error > lowerBoundLimit)){
          //integrate if error is small and we are close to command needed
          deadband_counter = 0;
          integral_charge = integral_charge + error*deltaTime;
          deadband_active = false; 
        }
      }
  
      else { 
        integral_charge = integral_charge + error*deltaTime;
      }
  
      output = error*proportionalGain + integral_charge*integralGain + derivativeCalculation*derivativeGain;
      if(output > maxOutput) {
        output = maxOutput;
      } else if(output < minOutput) {
        output = minOutput;
      }
      return output;
    }
  
    // for use if in standby and not running any power
    public void reset() {
      output = 0;
      //input = 0;
      error = 0;
    
      derivativeCalculation = 0;
      integral_charge = 0;
      previousError = 0;
  
      deadband_active = false;
    }
  }
