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

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhaktishah on 3/23/17.
 */
public class Interface_type {
    private  String name;
    private String final_string = "";
    private List<FieldDeclaration> attributes;
    private List<MethodDeclaration> methods;
    private ArrayList<String> method_ext;
    private ArrayList<String> member_variable;



    public Interface_type(ClassOrInterfaceDeclaration c) {
        name = c.getNameAsString();
        method_ext = new ArrayList<String>();
        methods = c.getMethods();
        attributes=c.getFields();
        member_variable=new ArrayList<String >();
        for (MethodDeclaration m : methods) {

            Method_extractor m_e = new Method_extractor(m,name);
            method_ext.add(m_e.get_string());
        }
    }

    public String get_name()
    {
        return "interface "+name;
    }

    public String get_method_members()
    {
        final_string +="interface "+name+"{\n";

        if (attributes != null) {
            for (int i = 0; i < attributes.size(); i++) {
                final_string += to_string_attribute(attributes.get(i)) + "\n";
            }
        }


        if(method_ext!=null) {
            for (int i = 0; i < method_ext.size(); i++) {
                final_string += method_ext.get(i) + "\n";

            }
        }
        final_string+="}";
        return final_string;
    }
    public String to_string_attribute(FieldDeclaration a) {
        String final_att = "";
        for(Modifier e:a.getModifiers())
        {
            if(e.toString()=="PRIVATE")
            {
                final_att += "- " +a.getVariables().get(0).getNameAsString() +": "+a.getVariables().get(0).getType()
                        .toString();
            }
            else if(e.toString()=="PUBLIC")
            {
                final_att += "+ " +a.getVariables().get(0).getNameAsString() +": "+a.getVariables().get(0).getType()
                        .toString();
            }
        }
        return final_att;

    }

}