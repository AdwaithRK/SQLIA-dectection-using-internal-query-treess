/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
/**
 *
 * @author adwaithrk
 */
public class Main_project {

    

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, Exception {
       //System.out.println("hello world");
      // box gh= new box();
       //gh.hello();
       //String read_file_name=args[0];
       String query=new String();
       String[] log=new String[2000000];
       //String[] tree=new String[500000];
       String[] extracted_tree=new String[500000];
       boolean query_start=false;
       //int lenght;
       int right_brace=0;
       int left_brace=0;
       int log_count=0;
       boolean tryv=false;
       //int j;
       //boolean contain_jasil;
       //boolean contain_movielens;
       boolean contain_parse_tree;
       //int line_no=0;
       int log_i = 0;
       int extracted_tree_line_no=0;
       String deloc="DEALLOCATE";
       //String read_file_name_path="/home/adwaithrk/project_logs/";
      // "/home/adwaithrk/project_logs/postgresql-2018-03-28_010214.csv"
       String file_to_read= "/home/adwaithrk/refined_non_malicious.log";      //postgresql-2018-03-28_213901.log
               /*read_file_name_path+read_file_name; */
       //System.out.println(file_to_read);
       
       
        try (FileReader freader = new FileReader(file_to_read)) {
            
            
            BufferedReader br = new BufferedReader(freader);
           // String s;
           
            //log[0]="hello";
            
            while((log[log_i] = br.readLine()) != null) {
              //  System.out.println(log[i]);
                //log[i]=s;
               
                //contain_jasil= log[log_i].toLowerCase().contains("jasil");
                //contain_movielens= log[log_i].toLowerCase().contains("movielens");
                
                
              contain_parse_tree= log[log_i].toLowerCase().contains("parse tree:");
                 //System.out.println(log[log_i]);
                 
                 if(/*contain_jasil &&  contain_movielens &&*/ contain_parse_tree)
                 {
               //  System.out.println(log[log_i]+"contains jasil");
                  query_start=true;
                  //   System.out.println("parse tree located\n");
                 continue;
                 }
                 
                 
                 
                 
                 if(query_start==true)
                 {
                     
                     
                    if(extracted_tree_line_no==0)
                       {  
                         //  System.out.println("started here");
                          extracted_tree[extracted_tree_line_no]=log[log_i].substring(log[log_i].indexOf("{"));
                           //System.out.println(extracted_tree[extracted_tree_line_no]);
                       }
                 
                    else
                       {    
                           extracted_tree[extracted_tree_line_no]=log[log_i];
                           //System.out.println(extracted_tree[extracted_tree_line_no]);
                       }
                  
                 
                    extracted_tree_line_no++;
                     
                     
                     
                     
                     
                     
                   if( log[log_i].contains("{") )
                   {
                      // System.out.println(log[log_i]+"contains {");
                       right_brace++;   
                   }
                   
                   if( log[log_i].contains("}") )
                   {
                       //System.out.println(log[log_i]+"contains }");
                       left_brace++;   
                   }
                   
                   
                   if(right_brace!=0 && left_brace!=0 && right_brace==left_brace)
                   {
                       
                       //System.out.println("going to write the query");
                       log_i++;
                       log[log_i] = br.readLine();
                       
                       log_i++;
                       log[log_i] = br.readLine();
                       //System.out.println(log[log_i]+" is line considered for statement");
                       //System.out.println(deloc+"is in the line");
                       if(log[log_i].contains(deloc)) 
                       {
                         //  System.out.println("dealloc detected");
                            query_start=false; 
                         //  System.out.println("log over");
                           extracted_tree_line_no=0;
                           right_brace=left_brace=0;
                           continue;
                           
                       }
                       
                       //System.out.println(log[log_i]);
                       right_brace=left_brace=0;
                       query_start=false;
                       
                       DateFormat df = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
                       Date dateobj = new Date();
                       String date_file_name=df.format(dateobj);
                     //  System.out.println(date_file_name);
                       String Filename_end= date_file_name+"count:"+ ++log_count +".txt";
                       String Filename_start="/home/adwaithrk/project_logs_extracted/";
                       
                       final File parent = new File(Filename_start);
                  
                       if (!parent.mkdirs()&&!tryv)
                        {
                               System.err.println("Could not create parent directories ");
                               tryv=true;
                        }
                       final File log_File = new File(parent, Filename_end);
                        log_File.createNewFile();
                       String File_name=Filename_start+Filename_end;
                       
                      // System.out.println(File_name);
                       File file_log=new File(File_name);
                     boolean created_file = file_log.createNewFile();
                     
                     
                     
                     if(created_file==true)
                     {
                         System.out.println("file created");
                     }
                         
                       
                     try (FileWriter log_filewriter = new FileWriter(File_name);PrintWriter log_printWriter = new PrintWriter(log_filewriter)) {
                         
                           query=log[log_i];
                            log_printWriter.println(query);
                       
                       for(int i=0;i<extracted_tree_line_no;i++)
                         {   
                             
                             
                            // System.out.println(extracted_tree[i]);
                             log_printWriter.println(extracted_tree[i]);
                         }
                     
                      
                       
                   
                           
                    query_start=false; 
                       //System.out.println("log over");
                       extracted_tree_line_no=0;
                       log_printWriter.close();
                       log_filewriter.close();
                 }
                  
                  
                
                  
            }
                
           // lenght=i;
        }
       // int i;
        
        //for(i=0;i<lenght;i++)
        //{
            // System.out.println(log[0]);
        //}
        //tree_separation a=new tree_separation();
        
        
        
    log_i++;   
        
        
        
	}
       
       
      
       
       
}
  final File folder = new File("/home/adwaithrk/project_logs_extracted");
  tree_separation a=new tree_separation();
  a.listFilesForFolder(folder);
  a.read_extarct();
  a.correct_form();
  //a.read_extarct();
  
}
    
}


