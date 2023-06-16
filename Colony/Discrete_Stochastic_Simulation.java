package Colony;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Discrete_Stochastic_Simulation {
    protected HashMap<String, Float> Parameters = new HashMap<String, Float>();
    private String[] Parameters_Name = { "n", "a", "n1", "alpha", "beta", "delta", "eta", "rho", "gamma", "nu", "tau" };
    private String[] Parameters_Name_file = { "n", "n1", "alpha", "beta", "delta", "eta", "rho", "gamma", "nu", "tau" };
    private String[] parameters_Description = { "number of nodes in the graph", "the nest node", "alpha, ant move event", "beta, ant move event", "delta, ant move event", "eta, pheromone evaporation event", "rho, pheromone evaporation event", "pheromone level", "ant colony size", "final instant" };
    private ArrayList<ArrayList<String>> top_Candidates=new ArrayList<ArrayList<String>> () ;// Parameters
    PriorityQueue<Hamiltonian_Candidates> nodes_Queue = new PriorityQueue<Hamiltonian_Candidates>();
    protected WeightedGraph graph;
    protected Colony colony;
    PriorityQueue<Events> events = new PriorityQueue<>();

    public Discrete_Stochastic_Simulation(String[] args) {
        if (args.length < 1) {
            System.out.println("No arguments given\nExiting Program");
            System.exit(0);
        } else if (args[0].equals("-f")) {
            System.out.println("Reading File");

            // Reading File object
            int i = 0;

            try {
                BufferedReader br = new BufferedReader(new FileReader(args[1]));
                String lines;
                String[] line;
                String[] line_copy = new String[11];

                while ((lines = br.readLine()) != null) {
                    line = lines.split(" ");
                    if (i == 0) {
                        line_copy[0] = line[0];
                        line_copy[1] = "0";
                        System.arraycopy(line, 1, line_copy, 2, 9);
                        System.out.println(line_copy);
                        this.Read_parameters(line_copy);
                        this.graph = new WeightedGraph(Math.round(this.Parameters.get("n")),
                                0, "v" + line_copy[2]);
                    } else {
                        if (line.length != this.Parameters.get("n")) {
                            System.out.println("Wrong Numbers Nodes\nExiting Program");
                            System.exit(0);
                        }
                        // Converte Line String in Intergers
                        for (int j = 0; j < line.length; j++) {
                            double weight = Double.parseDouble(line[j]);
                            if (weight != 0) {
                                String source = "v" + Integer.toString(i);
                                String destination = "v" + Integer.toString(j + 1);
                                graph.add1Edge(source, destination, weight);
                            }
                        }
                    }
                    i += 1;
                }
                br.close();
                
            } catch (IOException e) {
                System.out.println("Error Reading File\nExiting Program");
                System.exit(0);
            }

        } else if (args[0].equals("-r")) {
            System.out.println("Saving Parameters");
            Read_parameters(Arrays.copyOfRange(args, 1, args.length));
            this.graph = new WeightedGraph(Math.round(this.Parameters.get("n")), Math.round(this.Parameters.get("a")),
                    "v" + args[3]);
            this.graph.createGraphWithHamiltonianCircuit();
            this.graph.setNestNode("v" + Math.round(this.Parameters.get("n1")));


        } else {
            System.out.println("Invalid arguments given\nExiting Program");
            System.exit(0);
        }

        // graph.createGraphWithHamiltonianCircuit();
        // graph.printGraph();
        
        colony = new Colony(Math.round(Parameters.get("nu")), Integer.toString(Math.round(Parameters.get("n1"))));
        Print_Parameters_Graph();
    }

    public void Read_parameters(String[] arg) {
        float aux;
        if (arg.length != this.Parameters_Name.length) {
            System.out.println("Wrong number of Parameters\nExiting Program");
            System.exit(0);
        }
        for (int i = 0; i < arg.length; i++) {
            try {
                aux = Float.parseFloat(arg[i]);
            } catch (NumberFormatException e) {
                aux = 0;
            }

            this.Parameters.put(this.Parameters_Name[i], aux);
        }
        // this.colony=new ColonyI(this.Parameters,this.Map);
        // this.graph=new GraphI(this.Parameters, this.Map);

    }

    public void Simulation() {
        double currentTime = 0;
        // PriorityQueue<> Pheno = new PriorityQueue<>();
        // for (AntI ant : colony.getAnts()) {
        for (Ant ant : colony.getAnts()) {

            events.add(new AntMoveEvent(graph, ant, currentTime,
                    Parameters.get("alpha"), Parameters.get("beta"), Parameters.get("delta")));
        }
        double obs_message_time = this.Parameters.get("tau") / 20;
        int num_moves = 0;
        int num_evaporations = 0;
        while (currentTime < this.Parameters.get("tau")) {
            Events Event = events.poll();
            if (this.Parameters.get("tau") > Event.getTime()) {

                currentTime = Event.getTime();
                if (Event instanceof AntMoveEvent) {
                    AntMoveEvent event = (AntMoveEvent) Event;
                    event.get(events, currentTime, Parameters, nodes_Queue,top_Candidates);
                    // ant.move();
                    num_moves += 1;

                } else {
                    PheromoneEVEvent event = (PheromoneEVEvent) Event;
                    event.get(events, currentTime, Parameters.get("eta"), Parameters.get("rho"));
                    num_evaporations += 1;

                }
            } else {
                break;
            }

            if (currentTime >= obs_message_time) {
                obs_message_time += this.Parameters.get("tau") / 20;
                System.out.println("\n\nObservation numbers:");
                System.out.println("\tPresent instant:" + currentTime);
                System.out.println("\tNumber of move events:" + num_moves);
                System.out.println("\tNumber of evaporations:" + num_evaporations);

                System.out.println("\tTop candidate cycles:");
                PriorityQueue<Hamiltonian_Candidates> tmp_Queue = new PriorityQueue<Hamiltonian_Candidates>(
                        nodes_Queue);
                int i=0;
                Hamiltonian_Candidates best=null;
                if (!nodes_Queue.isEmpty()) {
                    best = tmp_Queue.poll();
                }
                while (!tmp_Queue.isEmpty()) {
                    
                    if (i==5){
                        break;
                    }
                    Hamiltonian_Candidates obj = tmp_Queue.poll();
                    if (tmp_Queue.peek()==null || (!obj.getNodes().equals(tmp_Queue.peek().getNodes()))){
                        String elements = obj.getNodes().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                        System.out.println("\t\t{" + elements + "}" + ":" + obj.getWeight());
                        i+=1;
                        
                    }
                }
                System.out.println("\tBest Hamiltonian cycle:");
                if (!nodes_Queue.isEmpty()) {
                    String elements = best.getNodes().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                    System.out.println("\t\t{" + elements + "}" + ":" + best.getWeight());
                }

            }

        }
    }

    public void Print_Parameters_Graph() {
        System.out.println("\nInput Parameters:");
        for (int i=0; i<Parameters_Name_file.length;i++) {
            System.out.println("\t" + Parameters.get(Parameters_Name_file[i]) + "  :  " +parameters_Description[i]);
        }
        System.out.println("\nWith Graph:\n");
        graph.printAdjMatrix();

    }

}