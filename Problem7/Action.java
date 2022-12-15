public class Action {

    public String name;  // action name
    public State next1;  // first tuple - destination state
    public State next2;  // first tuple - probability
    public double prob1; // second tuple - destination state
    public double prob2; // second tuple - probability

    public Action(String name, State next1, double prob1, State next2, double prob2) {
        this.name = name;
        this.next1 = next1;
        this.prob1 = prob1;
        this.next2 = next2;
        this.prob2 = prob2;
    }

    // if there is 100% probability for an action
    public Action(String name, State next1, double prob1) {
        this.name = name;
        this.next1 = next1;
        this.prob1 = prob1;
        this.next2 = null;
        this.prob2 = 0;
    }
}