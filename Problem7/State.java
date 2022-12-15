import java.util.ArrayList;

public class State {

    int reward;
    double value;
    Action policy;
    ArrayList<Action> actions;

    public State(int reward) {
        this.reward = reward;
        this.value = reward;
        this.policy = null;
        this.actions = new ArrayList<Action>();
    }

    public State(int reward, double value, Action policy, ArrayList<Action> actions) {
        this.reward = reward;
        this.value = value;
        this.policy = policy;
        this.actions = actions;
    }

    // copy array (due to referencing runtime errors)
    public static State[] copy(State[] states) {
        State[] toReturn = new State[4];
        for(int i = 0; i < states.length; i++) {
            State s = states[i];
            toReturn[i] = new State(s.reward, s.value, s.policy, s.actions);
        }
        return toReturn;
    }


    public void valuePolicy(double gamma) {
        Action maxAction = null;
        double maxVal = 0;

        for(int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            double currVal = action.prob1 * action.next1.value;
            if(action.next2 != null) currVal += action.prob2 * action.next2.value;

            if(currVal >= maxVal) {
                maxVal = currVal;
                maxAction = action;
            }
        }

        this.value = this.reward + (gamma * maxVal);
        this.policy = maxAction;
    }

    public static boolean converged(State[] prev, State[] curr) {
        for(int i = 0; i < curr.length; i++) {
            // System.out.println("curr[i] = " + curr[i].value + ", prev[i] = " + prev[i].value);
            if(prev[i].value != curr[i].value) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        
        // initializing network
        State s1 = new State(0);
        State s2 = new State(0);
        State s3 = new State(1);
        State s4 = new State(0);
        State[] states = {s1, s2, s3, s4};

        s1.actions.add(new Action("a1", s1, 0.2, s2, 0.8));
        s1.actions.add(new Action("a2", s1, 0.2, s4, 0.8));
        s2.actions.add(new Action("a2", s2, 0.2, s3, 0.8));
        s2.actions.add(new Action("a3", s2, 0.2, s1, 0.8));
        s3.actions.add(new Action("a3", s4, 1));
        s3.actions.add(new Action("a4", s2, 1));
        s4.actions.add(new Action("a1", s4, 0.1, s3, 0.9));
        s4.actions.add(new Action("a4", s4, 0.2, s1, 0.8));

        // calculations
        State[] prev = copy(states);
        System.out.println("--- ITERATION 1:---");
        for(State state : states) {
            state.valuePolicy(0.9);
            System.out.println("POLICY: " + state.policy.name + ", VALUE: " + state.value);
        }
        int count = 1;
        long start = System.currentTimeMillis();
        while(!converged(prev, states)) {
            prev = copy(states);
            count++;
            // Bellman calculation
            System.out.println("--- ITERATION " + count + ":---");
            for(State state : states) {
                state.valuePolicy(0.9);
                System.out.println("POLICY: " + state.policy.name + ", VALUE: " + state.value);
            }
        }
        long finish = System.currentTimeMillis();
        double time = (finish - start)/1000.0; // in seconds
        System.out.println("Number of iterations = " + count);
        System.out.printf("Time = %,.5f seconds", time);
    }
}