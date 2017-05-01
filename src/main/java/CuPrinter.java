/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * Created by bhaktishah on 2/20/17.
 */
public class CuPrinter {
    public static void main(String[] args) throws Exception {
        //Get the path of the file
        ParseFiles parse_file = new ParseFiles(args[0], args[1]);
        //ParseFiles parse_file = new ParseFiles("/Users/bhaktishah/Desktop/uml-parser-test-4","uml-parser-test-2.png");
        //System.out.println(args[0]+ "this" +args[1]);
        parse_file.run();


    }
}
