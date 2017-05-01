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
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhaktishah on 3/23/17.
 */
public class Class_type {
    private String name;
    private String final_string = "";
    private NodeList<ClassOrInterfaceType> parent;
    private NodeList<ClassOrInterfaceType> implemented_type;
    private List<FieldDeclaration> attributes;
    private List<MethodDeclaration> methods;
    private ArrayList<String> method_ext;
    private NodeList<BodyDeclaration<?>>  constructor;
    private ArrayList<String> cu_list_string;
    private String dep_rel;
    public Class_type(ClassOrInterfaceDeclaration c,ArrayList<String> cu_list) {
        name = c.getNameAsString();
        parent = c.getExtendedTypes();
        implemented_type = c.getImplementedTypes();
        method_ext = new ArrayList<String>();
        methods = c.getMethods();
        attributes = c.getFields();
        constructor = c.getMembers();
        cu_list_string=cu_list;
        dep_rel ="";

        //method extractor converts method to final string form which can be used directly to create class
        for (MethodDeclaration m : methods) {
            Method_extractor m_e = new Method_extractor(m,name);
            String temp=m_e.dependecies(cu_list_string);
            if(dep_rel.contains(temp))
            {
            }
            else {
                dep_rel += m_e.dependecies(cu_list_string) + "\n";
            }
            method_ext.add(m_e.get_string());

        }
    }

    public String get_name() {
        return "class " + name;
    }

    public String get_relations() {
        String relations = "";
        for (ClassOrInterfaceType p : parent) {
            relations += p.getNameAsString();
            relations += "<|--" + name + "\n";
        }
        for (ClassOrInterfaceType i : implemented_type) {
            relations += i.getNameAsString();
            relations += "<|.." + name + "\n";
        }
        //System.out.println("relaions" + relations);
        return relations;
    }
public String get_constructor_string(BodyDeclaration b)
{
    String constructor_to_string="";


              if (b instanceof ConstructorDeclaration)
              {
                  constructor_to_string += "+ " + ((ConstructorDeclaration) b).getName().toString() +" (";

                  for (Parameter p:((ConstructorDeclaration) b).getParameters()
                       ) {
                      constructor_to_string += p.getNameAsString()+": "+ p.getType().toString();
                  }
                  constructor_to_string +=")";
              }
              return constructor_to_string;

}
    // return all the methods and attributes of the class.
    public String get_method_members() {
        final_string += "class " + name + "{\n";
        if(constructor!= null) {
            for (BodyDeclaration b : constructor) {
                final_string +=get_constructor_string(b)+"\n";
            }
        }


        if (attributes != null) {
            for (int i = 0; i < attributes.size(); i++) {

                final_string += to_string_attribute(attributes.get(i)) + "\n";
            }
        }

        if (method_ext != null) {
            for (int i = 0; i < method_ext.size(); i++) {
                final_string += method_ext.get(i) + "\n";
            }
        }
        final_string += "}\n";
        if(dep_rel!=null)
        {
            final_string += dep_rel;
        }
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

