package Colony;
import java.util.*;
import java.io.*;
public class Discrete_Stochastic_Simulation  {
    protected HashMap<String, Integer> Parameters = new HashMap<String, Integer>();
    private String[] Parameters_Name ={"n","n1","a","alpha","beta","delta","eta","p","y","v","t"}; //Parameters
    protected ArrayList<Integer[]> Map=new ArrayList<Integer[]>();//Graph from file
    protected WeightedGraph graph;
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
            this.graph = new WeightedGraph(this.Parameters.get("n"), this.Parameters.get("a"));
            this.graph.createGraphWithHamiltonianCircuit();
            
        }
        else{
            System.out.println("Invalid arguments given\nExiting Program");
            System.exit(0);
        }
        
//         graph.createGraphWithHamiltonianCircuit();
//         graph.printGraph();
        Print_Parameters_Graph();


        
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

    }
    public void Simulation(){
        PriorityQueue<AntMoveEvent> antMoveEvents = new PriorityQueue<>();
        PriorityQueue<PheronomeEVEvent> PheronomeEVEvents = new PriorityQueue<>();
        double currentTime=0;
        //PriorityQueue<> Pheno = new PriorityQueue<>();
        for (AntI ant : colony.getAnts()) {
            antMoveEvents.add(new AntMoveEvent(ant, currentTime + ant.exponentialDistribution(Parameters)));
        }

        while (currentTime< this.Parameters.get("t")) {
            double AntTime= antMoveEvents.peek().getMoveTime();
        
            //double Pheromonetime=PheronomeEVEvents.peek().getMoveTime();
            AntMoveEvent antEvent=antMoveEvents.poll();
            if (currentTime>antEvent.getMoveTime()){
                currentTime=antEvent.getMoveTime();
                AntI ant=antEvent.getAnt();
                antMoveEvents.add(new AntMoveEvent(ant, currentTime + ant.exponentialDistribution(Parameters)));
            }

            // if (AntTime>Pheromonetime){
            //     AntMoveEvent antEvent=PheronomeEVEvents.poll();

            // }else{

            // }

            
            
            
        }
    }
    
    public void Print_Parameters_Graph(){
        System.out.println("Input Parameters");
        this.Parameters.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("With Graph");
        this.graph.printGraph();
        

    }


}