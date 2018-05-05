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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author adwaithrk
 */
public class tree_separation {
    String[] Filenames_extracted=new String[200000];
    String name_file=new String();
     int file_count;
     int path_stack_top=-1; 
     String[] path=new String[10000];     
    int i=0;
    int[] special_brace_index=new int[1000];
    int special_brace_index_count=-1;
    boolean special_brace=false;
    
    public void listFilesForFolder(final File folder) throws IOException {
        
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
            name_file=fileEntry.getName();
            Filenames_extracted[i++]="/home/adwaithrk/project_logs_extracted/"+name_file;
            
        }
    }
    file_count=i;
        System.out.println("pro logs exttarted count is"+i);
        
}
    
    
    
 /*   private static boolean checkString(String str) {
    char ch;
    boolean capitalFlag = false;
    
   
    for(int i=0;i < str.length();i++) {
        ch = str.charAt(i);
        
        if (Character.isUpperCase(ch)) {
            capitalFlag = true;
        } 
        else
        {
            capitalFlag=false;
        }
    }
    if( capitalFlag )
            return true;
    
    return false;
}*/
    
    
    
    
    
    
    
    
    public void read_extarct() throws IOException
    {
       // System.out.println("Files available");
     for(i=0;i<file_count;i++)
     {
         //System.out.println(i+")"+Filenames_extracted[i]);
     }
    
    }
    
    
    
    public void path_stack_push(String path_ele)
    {
        path_stack_top++;
      //  System.out.println(path_stack_top+"value after pushing");
        path[path_stack_top]=path_ele;
        
    }
    
     public void path_stack_pop()
    {
        path_stack_top--;
       // System.out.println("value after poping"+path_stack_top);
        //path[path_stack_top]=path_ele;
        
    }
    
    
    
    
    
    
    
    public void correct_form() throws IOException, FileNotFoundException, InterruptedException, Exception
    {
       // String[] split=new String[2];
        String[] ex_log= new String[2000];
        String[] original=new String[2000];
        String brace="(";
        int log_i;
        int j=0;
        boolean tryv=false;
        String ku;
        
        
        
        
        
        for(i=0;i<file_count;i++)
         {
        
             
             
             
             
             FileReader log_filereader = new FileReader(Filenames_extracted[i]);
             
              ku=Filenames_extracted[i].substring(Filenames_extracted[i].indexOf(":")+1,Filenames_extracted[i].indexOf("."));
              
              //System.out.println(ku+"is the count of log file");
             
             BufferedReader br = new BufferedReader(log_filereader);
             
             log_i=0;
             while((ex_log[log_i] = br.readLine()) != null) {
                
                //System.out.println(ex_log[log_i]);
                 if(log_i==0)
                 {
                     log_i++;
                     continue;
                 }
                 
                   original[log_i-1]=ex_log[log_i];
                  // System.out.println(original[log_i-1]+"is considered");
                 log_i++;
                 
             }
             
                      DateFormat df = new SimpleDateFormat("dd_MM_yy");
                        Date dateobj = new Date();
                       String date_file_name=df.format(dateobj);
                       
                       
                     
               String Filename_start="/home/adwaithrk/project_logs_paths_extracted/";
                       String path_file_name=date_file_name+"path_file_count:"+ku+".txt";
                     //  System.out.println(path_file_name);
                    
                       final File parent = new File(Filename_start);
                  
                       if (!parent.mkdirs()&&!tryv)
                        {
                               System.err.println("Could not create parent directories ");
                               tryv=true;
                        }
                       
                       final File log_File = new File(parent, path_file_name);
             
                      boolean text_file=log_File.createNewFile();
                      if(text_file==true)
                      {
                          //System.out.println("File created for paths");
                      }
                      
                      String file_name=Filename_start+path_file_name;
                      
                      FileWriter log_filewriter = new FileWriter(file_name);
                      
                      
                      
                      
            try (PrintWriter log_printWriter = new PrintWriter(log_filewriter)) {
                //log_printWriter.println("hello");
               // System.out.println("NEW FILE STARTED");
                path_stack_top=-1;
                special_brace_index_count=-1;
                
                
                
                
                for(int k=0;k<log_i-1;k++)
                {
                    //System.out.println(original[k]+"in original");
                    
                    if(k==0)
                    {
                  
                        continue;
                    }
                    
                    int count_elements=0;
                    original[k]=original[k].trim();
                    //System.out.println(original[k]+"is taken into log");
                    String[] arr = original[k].split(" ",2);
                    //int number_elements=arr.length;
                    //System.out.println("number of elements is"+number_elements);
                    for ( String ss : arr) {
                        
                        //System.out.println(ss);
                        //System.out.println(arr[count_elements]);
                        // System.out.println(a[count_elements]);
                        count_elements++;
                    }
                    
                    
                    //System.out.println("number of elements:"+count_elements);
                    // path_and_lenght(arr, count_elements);
                    
                    if(count_elements==1&& !arr[0].contains("{")&& !arr[0].contains("}")&& !arr[0].contains(")"))
                    {
                      //  System.out.println(arr[0]+"start of new path");
                        path_stack_push(arr[0]);
                    }
                    
                    
                    
                    else if(count_elements==2 && (arr[1].compareTo("(")==0 ) )
                    {
                        
                        //System.out.println(arr[0]+"start of new path as"+arr[1]+"is found");
                        path_stack_push(arr[0]);
                        //System.out.println("special brace");
                        special_brace_index[++special_brace_index_count]=path_stack_top;
                        //System.out.println(special_brace_index_count+"is thr index value of special brace array AT PUSHING");
                    }
                    
                    
                    
                    else if(count_elements==1 && (arr[0].contains("}") || arr[0].contains(")")) )
                    {
                        //System.out.println("path ended");
                        
                        //System.out.println(special_brace_index_count+"is thr index value of special brace array AT POPING");
                        
                        
                        if( (special_brace_index_count!=-1) && (path_stack_top==special_brace_index[special_brace_index_count]) )
                        {
                            
                            
                            if(arr[0].contains(")"))
                            {
                                //System.out.println("special case brace");
                                path_stack_pop();
                                special_brace_index_count--;
                              //  System.out.println(special_brace_index_count+"is thr index value of special brace array AT AFTER POPING");
                            }
                            special_brace=true;
                            
                            
                        }
                        
                        
                        if(!special_brace)
                        {
                            path_stack_pop();
                        }
                        
                        
                        special_brace=false;
                    }
                    
                    
                    
                    
                    
                    else
                    {
                      //  System.out.println("path:");
                        
                        
                        if(!arr[0].contains("{"))
                        {
                            if(path_stack_top!=-1)
                            {
                                for(int m=0;m<=path_stack_top;m++)
                                {
                                    // System.out.print(path[m]);
                                    ku=path[m].substring(path[m].indexOf(":")+1);
                              //      System.out.print(ku);
                                    log_printWriter.print(ku);
                                //    System.out.print("/");
                                    log_printWriter.print("/");
                                }
                                
                                ku=arr[0].substring(arr[0].indexOf(":")+1);
                                //System.out.print(ku);
                                log_printWriter.print(ku);
                                //System.out.print(" "+arr[1]);
                                log_printWriter.print(" "+arr[1]);
                                
                            }
                            else
                            {
                                ku=arr[0].substring(arr[0].indexOf(":")+1);
                              //  System.out.print(ku);
                                log_printWriter.print(ku);
                                //System.out.print(" "+arr[1]);
                                log_printWriter.print(" "+arr[1]);
                                
                            }
                            
                            //System.out.println("\n");
                            log_printWriter.println("");
                            
                        }
                    }
                    
                    
                    
                }
                log_printWriter.close();
            }
             log_filewriter.close();
         
         }
           path_to_num a=new path_to_num();
           a.path_initiator();  
            
           
         } 
        
     
    
    
    
   
    
    
    
}
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
   

