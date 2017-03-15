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
   get_cu_list(file_path);

}
public void run()
{
    cuArray = cu_list(file_path);
        buildMap(cuArray);
        for (CompilationUnit cu : cuArray)
            yumlCode += parser(cu);
        yumlCode += parseAdditions();
        yumlCode = yumlCodeUniquer(yumlCode);
        System.out.println("Unique Code: " + yumlCode);
        GenerateDiagram.generatePNG(yumlCode, Output_file);

}
   public void cu_list(String file_path)
   {
      ArrayList<CompilationUnit> cu_list;
    File input_file = new File(file_path);
    ArrayList<CompilationUnit> cu_arr_list = new ArrayList<CompilationUnit>();
    for (final File f : input_file.listFiles()) {
        if (f.isFile() && f.getName().endsWith(".java")) {
            FileInputStream in = new FileInputStream(f);
            CompilationUnit cu;
            try {
                cu = JavaParser.parse(in);
                cu_arr_list.add(cu);
            } finally {
                in.close();
            }
        }
   }
   }




}
