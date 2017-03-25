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
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhaktishah on 3/23/17.
 */
public class Interface_type {
    private  String name;
    private String final_string = "";
    private NodeList<ClassOrInterfaceType> parent;
    private NodeList<ClassOrInterfaceType> implemented_type;
    private List<MethodDeclaration> methods;
    private List<String> method_ext;


    public Interface_type(ClassOrInterfaceDeclaration c)
    {
        name = c.getNameAsString();
        parent = c.getExtendedTypes();
        implemented_type = c.getImplementedTypes();
        ArrayList<String> method_ext = new ArrayList<String>();
        methods = c.getMethods();
        for (MethodDeclaration m : methods) {

            Method_extractor m_e = new Method_extractor(m);
            method_ext.add(m_e.get_string());
        }
    }
    public String create_final_string () {
        for (ClassOrInterfaceType p:parent)
        {
            final_string += "class "+name;
            final_string += "<|--"+p.getNameAsString()+"\n";
        }
        final_string +="class "+name+"{";

        if(method_ext!=null) {
            for (int i = 0; i < method_ext.size(); i++) {
                final_string += method_ext.get(i) + "\n";

            }
        }
        final_string+="}";
        System.out.println(final_string);
        return final_string;
    }
}
