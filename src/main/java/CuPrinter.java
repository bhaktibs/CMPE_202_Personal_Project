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
        parse_file.run();
        //System.out.println("File path : " + final_path);
        //pass the path of the file to parser to parse the folder and extract seperate files

    }
}
