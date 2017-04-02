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

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhaktishah on 3/23/17.
 */
public class Class_type {
    private  String name;
    private String final_string = "";
    private NodeList<ClassOrInterfaceType> parent;
    private NodeList<ClassOrInterfaceType> implemented_type;
    private List<BodyDeclaration<?>> attributes;
    private List<MethodDeclaration> methods;
    private ArrayList<String> method_ext;
    private ArrayList<String> member_variable;

    }
public String get_name()
{
    return "class "+name;
}
public String get_relations () {
        String relations="";
        for (ClassOrInterfaceType p : parent) {
            relations += name;
            relations += "<|--" + p.getNameAsString() + "\n";
        }
        for (ClassOrInterfaceType i : implemented_type) {
            relations += name;
            relations += "<|--" + i.getNameAsString() + "\n";
        }
        return relations;
    }
public String get_method_members()
    {
        final_string +="class "+name+"{\n";

        if(member_variable!=null)
        {
            for(int i=0;i<member_variable.size();i++)
            {
                final_string +=member_variable.get(i) + "\n";
            }
        }

        if(method_ext!=null) {
            for (int i = 0; i < method_ext.size(); i++) {
                final_string += method_ext.get(i) + "\n";

            }
        }
        final_string+="}";
        //System.out.println(final_string);
        return final_string;
    }



