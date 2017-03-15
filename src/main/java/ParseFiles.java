/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
/**
 * Created by bhaktishah on 2/25/17.
 */
public class ParseFiles {
   private ArrayList<CompilationUnit> cu_list;
    private String file_path;
    private String output_file;
    String yumlCode;
    ArrayList<CompilationUnit> cuArray;


ParseFiles(String in_path,String out_path)
{
    this.file_path=in_path;
    this.output_file=out_path;
   run();

}
public ArrayList<CompilationUnit> get_cu_list() throws Exception
{
    File input_folder = new File(file_path);
    File [] input_files = input_folder.listFiles();
    ArrayList<CompilationUnit> cu_arr_list = new ArrayList<CompilationUnit>();
    for (int i=0;i< input_files.length; i++) {
        if (input_files[i].isFile() && input_files[i].getName().endsWith(".java")) {
            FileInputStream in = new FileInputStream(input_files[i]);
            try {
                CompilationUnit cu;
                cu = JavaParser.parse(in);
                cu_arr_list.add(cu);
            }
            catch();
               finally {
                in.close();
            }


        }
    }
    return cu_arr_list;

}

public void run();
    ArrayList<CompilationUnit> cu_arr_lis = get_cu_list();

}
   }




}
