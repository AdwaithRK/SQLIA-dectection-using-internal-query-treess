/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_project;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author adwaithrk
 */
public class path_to_num {
    
    String[] paths=new String[100];
     String[] path_file_names_ex=new String[5000];
     int file_count;
     double no_value= java.lang.Double.NaN;
     
     double[][] converted_values=new double[43][50];
     
     
     String[][] paths_values=new String[43][50];
  //  int[] paths_values_index=new int[44];
       int[] paths_values_index=new int[43];    
    
    int loop_traverse=0;
    int[] path_tot=new int[43];
    
   boolean tryv=false;
    
    
    
    void path_values(String value,int path_index)
    {
      //System.out.println(value);
        paths_values_index[path_index]++;
        System.out.println("\npath index:"+path_index+"\npaths_values_index:"+paths_values_index[path_index]+"value is"+value);
        paths_values[path_index][paths_values_index[path_index]]=value;
        
    }
    
    void path_value_convert(String file_name) throws InterruptedException
    {
     // System.out.println("THE ARRAY CONSIDERED FOR FILE :"+file_name);
        
        for(int h=0;h<43;h++)
        {
          System.out.println("for the path:"+paths[h]+"the path value index is"+paths_values_index[h]);   
            for(int n=0;n<=paths_values_index[h];n++)
            {
                System.out.println("\nvalue is "+paths_values[h][n]+"ark");
                
                if(paths_values[h][n].equalsIgnoreCase("and")||paths_values[h][n].equalsIgnoreCase("or")||paths_values[h][n].equalsIgnoreCase("not"))
                    
                {
                    if(paths_values[h][n].equalsIgnoreCase("and"))
                    {
                        converted_values[h][n]=0;
                        //System.out.println(converted_values[h][n]);
                    }
                    else if(paths_values[h][n].equalsIgnoreCase("or"))
                    {
                        converted_values[h][n]=1;
                         //System.out.println(converted_values[h][n]);
                    }
                    else if(paths_values[h][n].equalsIgnoreCase("not"))
                    {
                        converted_values[h][n]=2;
                         //System.out.println(converted_values[h][n]);
                    }
                    
                    
                }
                    
                else if(Pattern.matches("[0-9]+", paths_values[h][n]) == true)
                {
                     double nm=Double.parseDouble(paths_values[h][n]);
                       converted_values[h][n] =nm;
                        //System.out.println(converted_values[h][n]);
                }                
                else if(paths_values[h][n].equalsIgnoreCase("true")||paths_values[h][n].equalsIgnoreCase("false"))
                {
                    if(paths_values[h][n].equalsIgnoreCase("true"))
                    {
                        converted_values[h][n] =1;
                         //System.out.println(converted_values[h][n]);
                        
                    }
                    else
                    {
                        converted_values[h][n] =0;
                        // System.out.println(converted_values[h][n]);
                    }
                    
                    
                }
                
                else if(paths_values[h][n].equalsIgnoreCase("<>"))
                {
                      converted_values[h][n] =0;
                       //System.out.println(converted_values[h][n]);
                }
                else if(paths_values[h][n].equalsIgnoreCase("r")||paths_values[h][n].equalsIgnoreCase("s")||paths_values[h][n].equalsIgnoreCase("j")||paths_values[h][n].equalsIgnoreCase("f")||paths_values[h][n].equalsIgnoreCase("v")||paths_values[h][n].equalsIgnoreCase("c"))
                {
                    if(paths_values[h][n].equalsIgnoreCase("r") )
                    {
                       converted_values[h][n] =0;
                       //System.out.println(converted_values[h][n]);
                       
                    }
                    else if(paths_values[h][n].equalsIgnoreCase("s"))
                    {
                         converted_values[h][n] =1;
                         //System.out.println(converted_values[h][n]);
                    }
                    else if(paths_values[h][n].equalsIgnoreCase("j"))
                    {
                         converted_values[h][n] =2;
                         //System.out.println(converted_values[h][n]);
                    }
                     else if(paths_values[h][n].equalsIgnoreCase("f"))
                    {
                         converted_values[h][n] =3;
                        //System.out.println(converted_values[h][n]);
                    }
                    else if(paths_values[h][n].equalsIgnoreCase("v"))
                    {
                         converted_values[h][n] =4;
                         //System.out.println(converted_values[h][n]);
                    }
                    else if(paths_values[h][n].equalsIgnoreCase("c"))
                    {
                         converted_values[h][n] =5;
                        // System.out.println(converted_values[h][n]);
                    }
                }
                else if(paths_values[h][n].equalsIgnoreCase("?"))
                {
                    converted_values[h][n] =1;
                }
                
                else
                {
                   // System.out.println("all option exhausted....\n checking for constantvalue");
                    //System.out.println(paths_values[h][n]);
                    String[] kl=paths_values[h][n].split(" ",2);
                    //System.out.println(kl[0]+"is the stuff");
                    double nm=Double.parseDouble(kl[0]);
                    //System.out.println(nm+"is number stuff");
                       converted_values[h][n] =nm;
                       //System.out.println(converted_values[h][n]);
                }
                    
                    
            }
                
        }
       //path_complete_index();
       normalise_array();
    }
    
    
    void path_complete_index()
    {
        path_tot[0]=1;
        path_tot[1]=1;
        path_tot[2]=1;
        path_tot[3]=1;
        path_tot[4]=1;
        path_tot[5]=1;
        path_tot[6]=1;
        path_tot[7]=1;
        path_tot[8]=1;
        path_tot[9]=1;
        path_tot[10]=1;
        path_tot[11]=1;
        path_tot[12]=1; 
        path_tot[13]=1;
        path_tot[14]=1;
        path_tot[15]=1;
        path_tot[16]=3;
        path_tot[17]=3;
        path_tot[18]=9;
        path_tot[19]=5;
        path_tot[20]=5;
        path_tot[21]=5;
        path_tot[22]=5;
        path_tot[23]=5;
        path_tot[24]=5;
        path_tot[25]=5;
        path_tot[26]=5;
        path_tot[27]=5;
        path_tot[28]=10;
        path_tot[29]=10;
        path_tot[30]=5;
        path_tot[31]=5;
        path_tot[32]=1;
        path_tot[33]=1;
        path_tot[33]=1;
        path_tot[34]=1;
        path_tot[35]=1;
        path_tot[36]=1;
        path_tot[37]=1;
        path_tot[38]=1;
        path_tot[39]=1;
        path_tot[40]=1;
        path_tot[41]=1;
        path_tot[42]=1;
            
        
    }
    
    
    
    
    
    void normalise_array() throws InterruptedException
    {
        // path_complete_index();
        System.out.println("Before normalization:");
        for(int y=0;y<43;y++)
        {
            for(int k=0;k<=paths_values_index[y];k++)
            {
                System.out.print(converted_values[y][k]);
            }
            System.out.println("");
            
        }
        
        System.out.println("after normalization:");
      //  Thread.sleep(5000);
        
        for(int y=0;y<43;y++)
        {
            for(int k=paths_values_index[y]+1;k<path_tot[y];k++)
            {
                converted_values[y][k]=no_value;
            }
            System.out.println("");
        }
        
        
        for(int y=0;y<43;y++)
        {
            for(int k=0;k<path_tot[y];k++)
            {
                System.out.print(converted_values[y][k]);
            }
            System.out.println("");
            
        }
        //Thread.sleep(20000);
        
    }
    
    
    
    
    
      void path_initiator() throws FileNotFoundException, IOException, InterruptedException, Exception //checked
      {
    
       
       int path_i=0;
    
       String file_to_read= "/home/adwaithrk/project_logs/paths_for_checking.txt";
       try( FileReader freader = new FileReader(file_to_read) )
        {
            
           // System.out.println("hello path_ini");
            BufferedReader br = new BufferedReader(freader);
            while((paths[path_i] = br.readLine()) != null) {
              
              //System.out.println(paths[path_i]);
                path_i++;
                //System.out.println(path_i);
            }
            freader.close();
            
        }
        //System.out.println(path_i);
        path_complete_index();
       final File folder=new File("/home/adwaithrk/project_logs_paths_extracted");
          path_file_names(folder);
          
      
      }
      
      
      
      
      
      void path_file_names(final File folder) throws IOException, FileNotFoundException, InterruptedException, Exception     //checked
      {
          
           String name_file;
           int i=0;
      for (final File fileEntry : folder.listFiles()) {
        
        
        if (fileEntry.isDirectory()) {
            path_file_names(fileEntry);
         
        } else {
            name_file=fileEntry.getName();
            
            path_file_names_ex[i++]="/home/adwaithrk/project_logs_paths_extracted/"+name_file;
            //System.out.println(path_file_names_ex[i-1]);
            
        }
        
        }
    
    file_count=i;
        //System.out.println("count is"+i);
       path_value_extractor();

      }
      
      
      
      
      
      
      
      void path_value_extractor() throws FileNotFoundException, IOException, InterruptedException, Exception
      {
          String ss;
          
         PrintWriter pw = new PrintWriter(new File("/home/adwaithrk/training_non_malicious_final.csv"));
           // StringBuilder[] sb = new StringBuilder[50000];
            StringBuilder sa = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            
            for(int i=1;i<=117;i++)
            {
                sa.append("v").append(i).append(",");
                
            }
          
            sa.append("character");
            sa.append('\n');
            System.out.print(sa.toString());
            System.out.println("\n");
            pw.write(sa.toString());
           // pw.close();
        
        
        
          
          for(int i=0;i<file_count;i++)
          {
              
              
              
              
               
              //System.out.println("File number:"+i+"\n"+"STARTED.....");
              
              try( FileReader fpreader = new FileReader(path_file_names_ex[i]) )
                  {
                      
                  BufferedReader br = new BufferedReader(fpreader);
                  
                  for(int p=0;p<43;p++)
                  {
                    paths_values_index[p]=-1;
                     // System.out.println("all path values are reset");
                  }
                //  if(file_count==2)break;
              
                 while((ss = br.readLine()) != null) {
                 
                      String[] arr = ss.split(" ",2);
                     // System.out.println(arr[0]);
                     System.out.println(arr[0]+"is the path considered");
                      for(int c=0;c<43;c++)
                      {
                         
                       System.out.println(paths[c]+"is path no:"+c+"path_index"+paths_values_index[c]);
                       Pattern pattern = Pattern.compile(paths[c]);
                         Matcher matcher = pattern.matcher(arr[0]);
                         if(matcher.matches())
                         {
                           System.out.println("Matched...!!!!!");
                            // System.out.println(paths[c]+"=="+arr[0]);
                             path_values(arr[1],c);
                            // paths_values_index[c]=paths_values_index[c]+1;
                             
                          //  paths_values[c][paths_values_index[c]]=arr[1];
                             break;
                              //  System.out.println("index of path:"+c+"is"+paths_values_index[c]);
                          // paths_values_index++;
                           //paths_values[c]=arr[1];
                             //System.out.println("values "+paths_values[c][paths_values_index[c]]);
                             //System.out.println("\n");
                         }
                      }
                      
                    //  System.out.println("\n");
                 }
                      System.out.println("Filename:"+path_file_names_ex[i]);    
                 
                path_value_convert(path_file_names_ex[i]);
                
                
                                     
                  String Filename_start="/home/adwaithrk/final_array/";
                       
                       final File parent = new File(Filename_start);
                  
                       if (!parent.mkdirs()&&!tryv)
                        {
                               System.err.println("Couldn't create parent directories ");
                               tryv=true;
                        }
                       int g=path_file_names_ex[i].indexOf("count"); 
                       int z=path_file_names_ex[i].indexOf("."); 
                       
                       
                       String Array_file_name=path_file_names_ex[i].substring(g ,z );
                       //System.out.println(Array_file_name);
                        String Array_file_name_full=Array_file_name+"_Array.txt";
                        
                       String Array_file_name_with_path=Filename_start+Array_file_name_full;
                       
                        
                       final File Array_File = new File(parent, Array_file_name_full);
             
                      boolean text_file=Array_File.createNewFile();
                      if(text_file==true)
                      {
                         // System.out.println("File created for paths");
                      }
                      
                      try (FileWriter Array_filewriter = new FileWriter(Array_file_name_with_path);PrintWriter Array_printWriter = new PrintWriter(Array_filewriter)) {
                          
                         
                          for(int h=0;h<43;h++)
                          {
                              //System.out.print("Values of path"+h+":"+paths[h]+"is");
                              
                               
                              Array_printWriter.print("Values of path"+h+":");
                              for(int m=0;m<path_tot[h];m++)
                              {
                                  //System.out.print(converted_values[h][m]+" ");
                                  Array_printWriter.print(converted_values[h][m]+" ");
                                  //sb.append(converted_values[h][m]).append(",");
                                  pw.print(converted_values[h][m]+",");
                              }
                              //System.out.println(" ");
                              Array_printWriter.print("\n");
                              
                          }
                          pw.print("non malicious");
                          pw.print("\n");
                         // System.out.println(sb.toString());
                          //pw.write(sb.toString());
                         // System.out.println("");
                        Array_printWriter.close();
                        Array_filewriter.close();
                      }
                
                 
                 fpreader.close();
                 
                 
                 
                 }
              
             
          
          
         
          
          
      }
      
          
       //   dispaly_values();
       pw.close();
        
       classify s=new classify();
       s.combine_csv();
    
      }
      
      
      
  /*    void dispaly_values()
      {
          for(int i=0;i<43;i++)
          {
              System.out.println(paths[i]);
              System.out.println("Values:");
              for(int j=0;j<paths_values_index[i];j++)
              {
                  System.out.println(paths_values[i][j]);
              }
          }
      }
      
   */   
      
      
      
      
      
      
      
}
    
    
    

