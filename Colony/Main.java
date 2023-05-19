package Colony;

public class Main {
    public static void main(String[] args) {
        if(args.length==0){
            System.out.println("No arguments given\nExiting Program");
            System.exit(0);
        }
        else if (args[0].equals("-f")) {
            System.out.println("Reading File");
            
        }
        else if (args[0].equals("-r")){
            System.out.println("Saving Parameters");
        }
        else{
            System.out.println("Invalid arguments given\nExiting Program");
            System.exit(0);
        }


    }
}
