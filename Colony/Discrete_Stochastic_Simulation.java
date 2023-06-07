package Colony;
import java.util.*;
import java.io.*;
public class Discrete_Stochastic_Simulation  {
    protected HashMap<String, Integer> Parameters = new HashMap<String, Integer>();
    private String[] Parameters_Name ={"n","a","n1","alpha","beta","delta","eta","p","y","v","t"}; //Parameters
    protected ArrayList<Integer[]> Map=new ArrayList<Integer[]>();//Graph from file
    protected GraphI graph;
    protected ColonyI  colony;



    public Discrete_Stochastic_Simulation(String[] args) {
        if(args.length<1){
            System.out.println("No arguments given\nExiting Program");
            System.exit(0);
        }
        else if (args[0].equals("-f")) {
            System.out.println("Reading File");

            // Reading File object
            int i=0;

            try {
                BufferedReader br = new BufferedReader(new FileReader(args[1]));
                String lines;
                String[] line;
                Integer[] integerLine;
                
                while( (lines = br.readLine()) != null ){
                    line=lines.split(" ");
                    
                    if (i==0){
                        this.Read_parameters(line);
                    }
                    else{
                        if (line.length!=this.Parameters.get("n")){
                            System.out.println("Wrong Numbers Nodes\nExiting Program");
                            System.exit(0);
                        }
                        integerLine = new Integer[line.length];
                        //Converte Line String in Intergers
                        for (int j = 0; j < line.length; j++) {
                            integerLine[j] = Integer.parseInt(line[j]);
                        }
                        Map.add(integerLine);
                    }
                    i+=1;
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error Reading File\nExiting Program");
                System.exit(0);
            }

 

        }
        else if (args[0].equals("-r")){
            System.out.println("Saving Parameters");
            Read_parameters(Arrays.copyOfRange(args, 1, args.length));
            
        }
        else{
            System.out.println("Invalid arguments given\nExiting Program");
            System.exit(0);
        }


        
    }

    public void Read_parameters(String[] arg){
        int aux;
        if (arg.length!=this.Parameters_Name.length){
                System.out.println("Wrong number of Parameters\nExiting Program");
                System.exit(0);
            }
        for (int i = 0; i < arg.length; i++){
                try {
                    aux = Integer.parseInt(arg[i]);
                }
                catch (NumberFormatException e) {
                    aux = 0;
                }

                this.Parameters.put(this.Parameters_Name[i],aux);
        }
        //this.colony=new ColonyI(this.Parameters,this.Map);
        //this.graph=new GraphI(this.Parameters, this.Map);
        this.Print_Parameters_Graph();
    }
    public void Simulation(){
        PriorityQueue<AntI> antMoveEvents = new PriorityQueue<>();
        PriorityQueue<GraphI> Pheno = new PriorityQueue<>();

        int t=0;
        while (t< this.Parameters.get("t")) {
            
            
        }
    }
    
    public void Print_Parameters_Graph(){
        this.Map=graph.Return_Graph();
        System.out.println("Input Parameters");
        this.Parameters.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("With Graph");
        for (Integer[] arr : this.Map) {
            for (Integer num : arr) {
                System.out.print(num + " ");
            }
            System.out.println("With Graph");
        }

    }


}