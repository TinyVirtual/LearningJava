public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");
        // Great, this is so fucking simple, but so fucking hard since i still know nothing
        System.out.println("Your arguments are \"" + String.join("\", \"", args) + "\" with a length of " + String.valueOf(args.length) );
    }
}