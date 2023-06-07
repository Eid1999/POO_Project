package Colony;
import java.util.*;
import java.io.*;
public class Discrete_Stochastic_Simulation  {
    protected HashMap<String, Float> Parameters = new HashMap<String, Float>();
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
            this.graph = new WeightedGraph(Math.round( this.Parameters.get("n")), Math.round(this.Parameters.get("a")));
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
        float aux;
        if (arg.length!=this.Parameters_Name.length){
                System.out.println("Wrong number of Parameters\nExiting Program");
                System.exit(0);
            }
        for (int i = 0; i < arg.length; i++){
                try {
                    aux = Float.parseFloat(arg[i]);
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
        PriorityQueue<Events> events = new PriorityQueue<>();
        double currentTime=0;
        //PriorityQueue<> Pheno = new PriorityQueue<>();
        //for (AntI ant : colony.getAnts()) {
        for (int index = 0; index < Math.round(Parameters.get("v")); index++) {
            
            events.add(new AntMoveEvent(index, currentTime,Parameters.get("delta")));
        }
        double obs_message_time=this.Parameters.get("t")/20;
        int num_moves=0;
        int num_evaporations=0;
        while (currentTime< this.Parameters.get("t")) {
            Events Event=events.poll();
            if (this.Parameters.get("t")>Event.getTime()){
                
                currentTime=Event.getTime();
                if (Event instanceof AntMoveEvent) {
                    AntMoveEvent event = (AntMoveEvent) Event;
                    int ant=event.get();
                    //ant.move();
                    num_moves+=1;
                    events.add(new AntMoveEvent(ant, currentTime, Parameters.get("delta")));
                }
                else{
                    PheromoneEVEvent event = (PheromoneEVEvent) Event;
                    Edge edge=event.get();
                    num_evaporations+=1;
                    events.add(new PheromoneEVEvent(edge, currentTime, Parameters.get("n")));
                }
            }
            else{
                break;
            }
                
            if(currentTime>=obs_message_time){
                obs_message_time+=this.Parameters.get("t")/20;
                System.out.println("\n\nObservation numbers:");
                System.out.println("\tPresent instant:"+currentTime);
                System.out.println("\tNumber of move events:"+num_moves);
                System.out.println("\tNumber of evaporations:"+num_evaporations);
                System.out.println("\tTop candidate cycles:");
                System.out.println("\tBest Hamiltonian cycle:\n\n");


            }
    
            
            
        }
    }
    
    public void Print_Parameters_Graph(){
        System.out.println("\nInput Parameters:");
        this.Parameters.forEach((key, value) -> System.out.println("\t" +key + ":" + value));
        System.out.println("\nWith Graph:\n");
        graph.printAdjMatrix();
        

    }


}