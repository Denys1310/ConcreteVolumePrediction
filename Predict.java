/**Java deployment code of Neural Networks Model**/

/**==========================================================================
Before running the Java deployment code please read the following.

 STATISTICA variable names will be exported as-is into the Java deployment script;
please verify the resulting script to ensure that the variable names follow the Java
naming conventions and modify the names if necessary.

==========================================================================**/

import java.io.*;

import java.util.*;





public class Predict

{

   public static void __Spreadsh_MLP_4_3_1( double[] ContInputs, String[] CatInputs )

   {

     //"Input Variable" comment is added besides Input(Response) variables.



     int Cont_idx=0;

     int Cat_idx=0;

     double _Temperature__ = ContInputs[Cont_idx++]; //Input Variable

     String _Productivity__ = CatInputs[Cat_idx++]; //Input Variable

    double[] __statist_max_input = new double[1];

    __statist_max_input[0]= 3.60000000000000e+001;



    double[] __statist_min_input = new double[1];

    __statist_min_input[0]= 5.00000000000000e+000;



    double[] __statist_max_target = new double[1];

    __statist_max_target[0]= 1.03000000000000e+001;



    double[] __statist_min_target = new double[1];

    __statist_min_target[0]= 3.60000000000000e+000;





    double[][] __statist_i_h_wts = new double[3][4];



    __statist_i_h_wts[0][0]=-8.78797669331374e-001;

    __statist_i_h_wts[0][1]=2.27077996334649e-001;

    __statist_i_h_wts[0][2]=-1.12041435614644e-001;

    __statist_i_h_wts[0][3]=1.38526059455244e-001;



    __statist_i_h_wts[1][0]=-1.55017649821868e+000;

    __statist_i_h_wts[1][1]=4.04991335479045e-001;

    __statist_i_h_wts[1][2]=-7.59303072352987e-002;

    __statist_i_h_wts[1][3]=1.11795315622857e-001;



    __statist_i_h_wts[2][0]=-4.69920008231996e-001;

    __statist_i_h_wts[2][1]=3.66461741273306e-001;

    __statist_i_h_wts[2][2]=-4.34525403569148e-001;

    __statist_i_h_wts[2][3]=-5.25844089613977e-002;



    double[][] __statist_h_o_wts = new double[1][3];



    __statist_h_o_wts[0][0]=3.94875873263659e-001;

    __statist_h_o_wts[0][1]=4.73603923331589e-001;

    __statist_h_o_wts[0][2]=-4.70821203434215e-001;



    double[] __statist_hidden_bias = new double[3];

    __statist_hidden_bias[0]=1.63969918531686e-001;

    __statist_hidden_bias[1]=4.73319509089725e-001;

    __statist_hidden_bias[2]=-1.38172338518948e-001;



    double[] __statist_output_bias = new double[1];

    __statist_output_bias[0]=4.49926814069351e-001;



    double[] __statist_inputs = new double[4];



    double[] __statist_hidden = new double[3];



    double[] __statist_outputs = new double[1];

    __statist_outputs[0] = -1.0e+307;



    __statist_inputs[0]=_Temperature__;



    if( _Productivity__.equals("High"))

    {

     __statist_inputs[1]=1;

    }

    else

    {

     __statist_inputs[1]=0;

    }



    if( _Productivity__.equals("Low"))

    {

     __statist_inputs[2]=1;

    }

    else

    {

     __statist_inputs[2]=0;

    }



    if( _Productivity__.equals("Medium"))

    {

     __statist_inputs[3]=1;

    }

    else

    {

     __statist_inputs[3]=0;

    }



    double __statist_delta=0;

    double __statist_maximum=1;

    double __statist_minimum=0;

    int __statist_ncont_inputs=1;



    /*scale continuous inputs*/

    for(int __statist_i=0;__statist_i < __statist_ncont_inputs;__statist_i++)

    {

     __statist_delta = (__statist_maximum-__statist_minimum)/(__statist_max_input[__statist_i]-__statist_min_input[__statist_i]);

     __statist_inputs[__statist_i] = __statist_minimum - __statist_delta*__statist_min_input[__statist_i]+ __statist_delta*__statist_inputs[__statist_i];

    }



    int __statist_ninputs=4;

    int __statist_nhidden=3;



    /*Compute feed forward signals from Input layer to hidden layer*/

    for(int __statist_row=0;__statist_row < __statist_nhidden;__statist_row++)

    {

      __statist_hidden[__statist_row]=0.0;

      for(int __statist_col=0;__statist_col < __statist_ninputs;__statist_col++)

      {

       __statist_hidden[__statist_row]= __statist_hidden[__statist_row] + (__statist_i_h_wts[__statist_row][__statist_col]*__statist_inputs[__statist_col]);

      }

     __statist_hidden[__statist_row]=__statist_hidden[__statist_row]+__statist_hidden_bias[__statist_row];

    }



    for(int __statist_row=0;__statist_row < __statist_nhidden;__statist_row++)

    {

      if(__statist_hidden[__statist_row]>100.0)

      {

       __statist_hidden[__statist_row] = 1.0;

      }

      else

      {

       if(__statist_hidden[__statist_row]<-100.0)

       {

        __statist_hidden[__statist_row] = -1.0;

       }

       else

       {

        __statist_hidden[__statist_row] = Math.tanh(__statist_hidden[__statist_row]);

       }

      }

    }



    int __statist_noutputs=1;



    /*Compute feed forward signals from hidden layer to output layer*/

    for(int __statist_row2=0;__statist_row2 < __statist_noutputs;__statist_row2++)

    {

     __statist_outputs[__statist_row2]=0.0;

    for(int __statist_col2=0;__statist_col2 < __statist_nhidden;__statist_col2++)

      {

       __statist_outputs[__statist_row2]= __statist_outputs[__statist_row2] + (__statist_h_o_wts[__statist_row2][__statist_col2]*__statist_hidden[__statist_col2]);

      }

     __statist_outputs[__statist_row2]=__statist_outputs[__statist_row2]+__statist_output_bias[__statist_row2];

    }







    /*Unscale continuous targets*/

    __statist_delta=0;

    for(int __statist_i=0;__statist_i < __statist_noutputs;__statist_i++)

    {

     __statist_delta = (__statist_maximum-__statist_minimum)/(__statist_max_target[__statist_i]-__statist_min_target[__statist_i]);

     __statist_outputs[__statist_i] = (__statist_outputs[__statist_i] - __statist_minimum + __statist_delta*__statist_min_target[__statist_i])/__statist_delta;

    }





      for(int __statist_ii=0; __statist_ii < __statist_noutputs; __statist_ii++)

      {

        System.out.println(" Prediction_"+ __statist_ii + " = " + __statist_outputs[__statist_ii]);

      }





   }



   public static void main (String[] args) {

     int argID = 0;

     double[] ContInputs = new double[1];

     int contID = 0;

     String[] CatInputs = new String[1];

     int catID = 0;



     if (args.length >= 2)

     {

       ContInputs[contID++] =  Double.parseDouble(args[argID++]);

       CatInputs[catID++] = args[argID++];

     }

     else

     {

       String Comment = "";

       String Comment1 = "**************************************************************************\n";

       Comment += Comment1;

       String Comment2 = "Please enter at least 2 command line parameters in the following order for \nthe program to Predict.\n";

       Comment += Comment2;

       Comment += Comment1;

       String Comment3 = "Temperature  Type - double (or) integer\n";

       Comment += Comment3;

       String Comment4 = "Productivity  Type - String (categories are { \"High\"  \"Low\"  \"Medium\" } )\n";

       Comment += Comment4;

       Comment += Comment1;

       System.out.println(Comment);

       System.exit(1);

     }

     __Spreadsh_MLP_4_3_1( ContInputs, CatInputs );

   }



}