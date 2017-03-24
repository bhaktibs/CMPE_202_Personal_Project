/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */


import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.EnumSet;

;

/**
 * Created by bhaktishah on 2/25/17.
 */
public class ParseFiles {
    ArrayList<CompilationUnit> cu_list;
    ArrayList<String> classes;
    private String input_file_path;
    private String output_file;

    ParseFiles(String in_path, String out_path) {
        this.input_file_path = in_path;
        this.output_file = out_path;
    }

    public ArrayList<CompilationUnit> get_cu_list() throws Exception {
        File input_folder = new File(input_file_path);
        File[] input_files = input_folder.listFiles();

        ArrayList<CompilationUnit> cu_arr_list = new ArrayList<CompilationUnit>();
        for (int i = 0; i < input_files.length; i++) {
            if (input_files[i].isFile() && input_files[i].getName().endsWith(".java")) {
                FileInputStream in = new FileInputStream(input_files[i]);
                try {
                    CompilationUnit cu;
                    cu = JavaParser.parse(in);
                    cu_arr_list.add(cu);
                } finally {
                    in.close();
                }
            }
        }
        return cu_arr_list;

    }


    public void run() throws Exception {
        try {
            cu_list = get_cu_list();
            for(CompilationUnit c:cu_list) {
                //System.out.println(c.toString());
                TypeDeclaration t = c.getType(0);
                if(t instanceof ClassOrInterfaceDeclaration)
                {
                    ClassOrInterfaceDeclaration classDec = (ClassOrInterfaceDeclaration) t;
                    EnumSet<Modifier> e = classDec.getModifiers();
                    if (e.contains(Modifier.PUBLIC)) {
                        //The class or interface is public
                        if (classDec.isInterface()) {
                            Interface_type int_t = new Interface_type(classDec);  
                        }
                        else
                        {
                            Class_type class_t = new Class_type(classDec);
                        }
                    }
                }
            }


        } finally {

        }
    }
}


