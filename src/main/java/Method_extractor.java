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
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by bhaktishah on 3/23/17.
 */
public class Method_extractor {
    MethodDeclaration m;
    List method_parm;
    String class_name;


    public Method_extractor(MethodDeclaration method, String name) {
        m = method;
        method_parm = method.getParameters();
        class_name = name;

    }

    public String get_string() {


        EnumSet<Modifier> e = m.getModifiers();
        String method_string = "";
        if (e.contains(Modifier.PUBLIC)) {
            method_string = "+ ";
            method_string += m.getNameAsString() + "(";

            for (Object t : method_parm) {
                if(t.toString().isEmpty())
                {

                }
                else
                {
                    String[] s_list = t.toString().split("\\s+");
                    method_string+= s_list[1] + ":" + s_list[0];
                }
            }
            method_string += ") : ";
            //System.out.println(method_string);
            method_string += m.getType();

        }

        return method_string;
    }

    public String dependecies(ArrayList<String> cu_list) {
        String dependecy = "";

        for (Object m : method_parm) {
            if (m.toString().isEmpty()) {

            } else {
                String[] s_list = m.toString().split("\\s+");

                if (cu_list.contains(s_list[0])) {
                    dependecy += class_name + "..>" + s_list[0] + "\n";
                }
            }
        }

        return dependecy;
    }

}

