/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;



/**
 *
 * @author adwaithrk
 */
public class classify {
    
    
    void combine_csv() throws FileNotFoundException, IOException, Exception
    {
        String s=new String();
        String a=new String();
        FileReader fpreader = new FileReader("/home/adwaithrk/training_malicious_final.csv");
        FileReader foreader = new FileReader("/home/adwaithrk/training_non_malicious_final.csv");
        
         BufferedReader br = new BufferedReader(fpreader);
          BufferedReader bi = new BufferedReader(foreader);
        
          
        try (PrintWriter pw = new PrintWriter(new File("/home/adwaithrk/training_final.csv"))) {
            int count=0;
            while((s = br.readLine()) != null)
            {
                count++;
                System.out.print(count+")");
                System.out.print(s+"\n");
                pw.print(s);
                pw.print("\n");
                
            }
            int count1=count;
            System.out.println("non malicious query..");
            count=0;
            while((a = bi.readLine()) != null)
            {
                count++;
                if(count==1)continue;
                
                System.out.print(count+count1+")");
                System.out.print(a+"\n");
                pw.print(a);
                pw.print("\n");
            }
        }
              
          classify_weka();
          
         
        
    }
    
    
    void classify_weka() throws IOException, Exception
    {
    
         CSVLoader loader = new CSVLoader();
          loader.setSource(new File("/home/adwaithrk/training_final.csv"));
          Instances data = loader.getDataSet();
          
          ArffSaver saver = new ArffSaver();
          saver.setInstances(data);
          
          
           saver.setFile(new File("/home/adwaithrk/training_final.arff"));
           saver.setDestination(new File("/home/adwaithrk/training_final.arff"));
           saver.writeBatch();
           
           
            BufferedReader reader = new BufferedReader(new FileReader("/home/adwaithrk/training_final.arff"));
           
           
           weka.classifiers.functions.SMO rf= new weka.classifiers.functions.SMO();
           
           //rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.RBFKernel -C 250007 -G 7 \" ") );
           
           //java.io.FileReader jr = new java.io.FileReader("/home/adwaithrk/training_final.arff");
           // weka.core.Instances inst = new weka.core.Instances(jr);
           DataSource source = new DataSource("/home/adwaithrk/training_final.arff");
           Instances data1 = source.getDataSet();
           
           data1.setClassIndex(data1.numAttributes() - 1);
           
           rf.buildClassifier(data1);
           
           
            Evaluation eval = new Evaluation(data1);
            Evaluation eval1 = new Evaluation(data1);
            Evaluation eval2 = new Evaluation(data1);
            Evaluation eval3 = new Evaluation(data1);
            Evaluation eval4 = new Evaluation(data1);
            Evaluation eval5 = new Evaluation(data1);
              Evaluation eval6 = new Evaluation(data1);
            Evaluation eval7 = new Evaluation(data1);
             Evaluation eval8 = new Evaluation(data1);
            
              eval.crossValidateModel(rf, data1,10, new Random(1));
              
              System.out.println("SMO:");
           
           System.out.println(eval.toSummaryString());
           
           rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.RBFKernel -C 250007 -G 10\" ") );
           
           eval1.crossValidateModel(rf, data1,10, new Random(1));
           
            System.out.println("SMO using RBF Kernel -Gamma 10:");
           
           System.out.println(eval1.toSummaryString());
           
           
           rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.RBFKernel -C 250007 -G 20\" ") );
           
           eval7.crossValidateModel(rf, data1,10, new Random(1));
           
            System.out.println("SMO using RBF Kernel -Gamma 20:");
           
           System.out.println(eval7.toSummaryString());
           
           
            rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.RBFKernel -C 250007 -G 30\" ") );
           
           eval8.crossValidateModel(rf, data1,10, new Random(1));
           
            System.out.println("SMO using RBF Kernel -Gamma 30:");
           
           System.out.println(eval8.toSummaryString());
           
           
           
           
           
           
           
           
           rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.RBFKernel -C 250007 -G 45\" ") );
           
           eval2.crossValidateModel(rf, data1,10, new Random(1));
           
            System.out.println("SMO using RBF Kernel -Gamma 45:");
           
           System.out.println(eval2.toSummaryString());
           
           
           
            rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.PolyKernel -C 250007 -E 10 \" ") );
           
           eval6.crossValidateModel(rf, data1,10, new Random(1));
           
           System.out.println("SMO using polynomial kernel -Exponent 10:");
           
           System.out.println(eval6.toSummaryString());
           
           
           
           rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.PolyKernel -C 250007 -E 7 \" ") );
           
           eval3.crossValidateModel(rf, data1,10, new Random(1));
           
           System.out.println("SMO using polynomial kernel -Exponent 7:");
           
           System.out.println(eval3.toSummaryString());
           
           
           
           
           rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.PolyKernel -C 250007 -E 4 \" ") );
           
           eval4.crossValidateModel(rf, data1,10, new Random(1));
           
           System.out.println("SMO using polynomial kernel -Exponent 4:");
           
           System.out.println(eval4.toSummaryString());
           
           
           rf.setOptions(weka.core.Utils.splitOptions("-K \" weka.classifiers.functions.supportVector.PolyKernel -C 250007 -E 1 \" ") );
           
           eval5.crossValidateModel(rf, data1,10, new Random(1));
           
           System.out.println("SMO using polynomial kernel -Exponent 1:");
           
           System.out.println(eval5.toSummaryString());
           
           
        
    }
    
    
   
    
    
    
    
    
}

