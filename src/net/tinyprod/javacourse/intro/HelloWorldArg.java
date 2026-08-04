package net.tinyprod.javacourse.intro;

public class HelloWorldArg {
    public static void main(String[] args) {
        if(args.length >= 1){
            System.out.println("Hello \""+String.join("\", \"", args)+"\"!");
        } else {
            System.out.println("Hello World!");
        }
    }
}
