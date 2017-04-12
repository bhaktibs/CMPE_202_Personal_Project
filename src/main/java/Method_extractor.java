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
import java.util.EnumSet;
import java.util.List;

/**
 * Created by bhaktishah on 3/23/17.
 */
public class Method_extractor {
    MethodDeclaration m;
    String method_full;
     List method_parm;



    public Method_extractor(MethodDeclaration met)
    {
        m=met;
        method_full=m.getDeclarationAsString();
        method_parm=met.getParameters();

    }

    public String get_string() {

        String method_string="";
        EnumSet<Modifier> e = m.getModifiers();
        if (e.contains(Modifier.PUBLIC)) {
            method_string = "+ ";
            method_string += m.getNameAsString() + "(";
            for (Object t : method_parm) {
                method_string += t.toString();
            }
            method_string += ")";
        }
        return method_string;
    }

    }

