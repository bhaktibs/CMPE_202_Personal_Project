/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2016 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */


import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * Created by bhaktishah on 2/25/17.
 */
public class ParseFiles {
    ArrayList<CompilationUnit> cu_list;
    ArrayList<String> classes;
    private String input_file_path;
    private String output_file;
    private String name_class_interface;
    private String relations_class_interface;
    private String methods_members;
    private String parser_file;
    private ArrayList<TypeDeclaration> list_class_interface;


    ParseFiles(String in_path, String out_path) {
        input_file_path = in_path;
        output_file = out_path;
        classes = new ArrayList<String>();
        list_class_interface = new ArrayList<TypeDeclaration>();
        name_class_interface = "";
        relations_class_interface = "";
        methods_members = "";
        parser_file = "";

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
                    //System.out.println(cu.toString());
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
            ArrayList<String> name_of_class_interface = get_names(cu_list);
            ArrayList<String> dp = new ArrayList<String>();
            for (CompilationUnit c : cu_list) {

                TypeDeclaration t = c.getType(0);
                list_class_interface.add(t);
                if (t instanceof ClassOrInterfaceDeclaration) {
                    ClassOrInterfaceDeclaration classDec = (ClassOrInterfaceDeclaration) t;
                    if (classDec.isInterface()) {
                        Interface_type int_t = new Interface_type(classDec);
                        name_class_interface += int_t.get_name() + "\n";
                        methods_members += int_t.get_method_members() + "\n";

                    } else {
                        Class_type class_t = new Class_type(classDec, name_of_class_interface);
                        name_class_interface += class_t.get_name() + "\n";
                        relations_class_interface += class_t.get_relations() + "\n";
                        methods_members += class_t.get_method_members() + "\n";


                    }
                }
            }
            Dependency_Association d_a = new Dependency_Association(list_class_interface);
            d_a.get_dependency_relations();

            //System.out.println(name_of_class_interface);
            parser_file += "@startuml\n" + d_a.dependency_relations() + name_class_interface +
                    relations_class_interface +
                    methods_members +
                    "@enduml";
            System.out.println(parser_file);
        } finally {
            createUML();
        }

    }

    public ArrayList<String> get_names(ArrayList<CompilationUnit> cu) {
        ArrayList<String> names = new ArrayList<String>();
        for (CompilationUnit c : cu) {
            names.add(c.getType(0).getName().toString());
            //System.out.println(c.getType(0).getName().toString());
        }


        return names;
    }


    public void createUML() throws Exception {

        SourceStringReader r = new SourceStringReader(parser_file);
        OutputStream png = new FileOutputStream(output_file);
        r.generateImage(png);
    }

}



