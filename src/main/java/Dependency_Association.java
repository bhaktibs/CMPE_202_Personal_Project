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

import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bhaktishah on 4/26/17.
 */
public class Dependency_Association {
    private HashMap<String,String>map;
    private ArrayList<TypeDeclaration> t_list;
    private ArrayList<String>class_list;
    private String relations;

//constructor for dependecy
    public Dependency_Association(ArrayList<TypeDeclaration> t_list)
    {
        this.t_list = t_list;
        this.class_list = new ArrayList<String>();
        this.map = new HashMap<String,String>();
        this.relations ="";
        get_names_as_string();
    }

}
