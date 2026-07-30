import java.util.Date;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello world!");
        // Great, this is so fucking simple, but so fucking hard since i still know nothing
        System.out.println("Your arguments are \"" + String.join("\", \"", args) + "\" with a length of " + String.valueOf(args.length) );

        for(int i = 0; i < 10; i+=1){
            System.out.println(String.format("%.4f",Math.random()) + " generated at "+ new Date().toString());
        }
        // ok, still bullshit, but here is something to start with
    }
}