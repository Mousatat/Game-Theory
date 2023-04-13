package gametheory.snowball.students2022;
class wise_Random_Agent implements Player {
    /*
        This Agent Choose Randomly between neither Shot to both fields or wait.
        IF the result of the randomization is Shot the Agent will shot the maximum allowed number of snowballs
        to Both side.
    */
    public int ShotNow=0; // This variable to let the agent now if there is a shot not or not
    // because if there is it will be better to show to both side not only to opponent side
    public void reset(){ShotNow=0;}
    public int shootToOpponentField(int opponentLastShotToYourField, int
            snowballNumber, int minutesPassedAfterYourShot){
        int min = 0, max = 1; // this two variables are for the range of the randomization.
        int random_param = (int)(Math.random()*(max-min+1)+min); // Random_param is neither 1 ir 0
        // 1 means Shot for this minute : 0 means do not shot
        if(random_param == 1) {
            int numberOfBallsShotToOpponentField = Math.min(maxSnowballsPerMinute(minutesPassedAfterYourShot), snowballNumber);
            ShotNow=1;
            return numberOfBallsShotToOpponentField;
        }
        ShotNow=0;
        return 0;
    }
    public int shootToHotField(int opponentLastShotToYourField, int snowballNumber,
                               int minutesPassedAfterYourShot){
        int min = 0;
        int max = 1;
        int random_param = (int)(Math.random()*(max-min+1)+min);
        if(random_param == 1 || ShotNow==1) {          // ShotNow==1 means if I have already shot to opponent field this minute
            int numberOfBallsShotToHotField = Math.min(maxSnowballsPerMinute(minutesPassedAfterYourShot), snowballNumber);
            ShotNow=1;
            return numberOfBallsShotToHotField;
        }
        ShotNow=0;
        return 0;
    }
    public String getEmail(){
        return "m.mousatat@innopolis.university";
    }

}

class completlyRandomAgent implements Player {
    /*
    This Agent Shot random number of Balls every minute
    It is completely Random Agent
     */
    public void reset(){}
    public int shootToOpponentField(int opponentLastShotToYourField, int
            snowballNumber, int minutesPassedAfterYourShot){
        int min =0;
        int max = Math.min(maxSnowballsPerMinute(minutesPassedAfterYourShot), snowballNumber );
        int numberOfBallsShotToOpponentField = (int)(Math.random()*(max-min+1)+min);
        return numberOfBallsShotToOpponentField;
    }
    public int shootToHotField(int opponentLastShotToYourField, int snowballNumber,
                               int minutesPassedAfterYourShot){
        int min =0;
        int max =Math.min(maxSnowballsPerMinute(minutesPassedAfterYourShot), snowballNumber);
        int numberOfBallsShotToHotField = (int)(Math.random()*(max-min+1)+min);
        return numberOfBallsShotToHotField;
    }
    public String getEmail(){
        return "m.mousatat@innopolis.university";
    }

}
class greedyMaxAgent implements Player {
    /*
    This Agent Always Shot to both Sides after waiting number of minutes (So Greedy)
     */
    public int numberOfWaitingMinutes=3;
    public void reset(){}
    public int shootToOpponentField(int opponentLastShotToYourField, int
            snowballNumber, int minutesPassedAfterYourShot){
        if (numberOfWaitingMinutes == minutesPassedAfterYourShot)
            return Math.min(maxSnowballsPerMinute(minutesPassedAfterYourShot), snowballNumber );
        else return 0;
    }
    public int shootToHotField(int opponentLastShotToYourField, int snowballNumber,
                               int minutesPassedAfterYourShot){
        if (numberOfWaitingMinutes ==minutesPassedAfterYourShot)
            return Math.min(maxSnowballsPerMinute(minutesPassedAfterYourShot), snowballNumber );
        else return 0;
    }
    public String getEmail(){
        return "m.mousatat@innopolis.university";
    }

}

class agent_Shot_to_Only_Hot_Field implements Player {
    /*
    This Agent only Shot To Hot Field after number of minutes
     */
    public int numberOfWaitingMinutes=3;
    public void reset(){}
    public int shootToOpponentField(int opponentLastShotToYourField, int
            snowballNumber, int minutesPassedAfterYourShot){
        return 0;
    }
    public int shootToHotField(int opponentLastShotToYourField, int snowballNumber,
                               int minutesPassedAfterYourShot){
        if (numberOfWaitingMinutes ==minutesPassedAfterYourShot)
            return Math.min(maxSnowballsPerMinute(minutesPassedAfterYourShot), snowballNumber );
        else return 0;
    }
    public String getEmail(){
        return "m.mousatat@innopolis.university";
    }
}
class agent_Shot_to_Only_opponent_field implements Player{
    /*
    This Agent only Shot To oponnent Field after number of minutes
     */
    public int numberOfWaitingMinutes=3;
    public void reset(){}
    public int shootToOpponentField(int opponentLastShotToYourField, int
            snowballNumber, int minutesPassedAfterYourShot){
        if (numberOfWaitingMinutes == minutesPassedAfterYourShot)
            return Math.min(maxSnowballsPerMinute(minutesPassedAfterYourShot), snowballNumber );
        else return 0;
    }
    public int shootToHotField(int opponentLastShotToYourField, int snowballNumber,
                               int minutesPassedAfterYourShot){
        return 0;
    }
    public String getEmail(){
        return "m.mousatat@innopolis.university";
    }

}

public class MahmoudMousatatTesting {
    public static int[] tournament(Player a,Player b){
        /*
        This function to test make the tournament between two agents
         */
        int numOfSnowBallsInAField=100;
        int numOfSnowBallsInBField=100;
        int ALastShotToFieldB=0;
        int BLastShotToFieldA=0;
        int minutesPassedAfterAShot=0;
        int minutesPassedAfterBShot=0;
        // Reseting the agents
        a.reset();
        b.reset();
        // Start the tournament with 60 rounds
        for(int i=0;i<60;i++){
            int AShotToFieldB=a.shootToOpponentField(BLastShotToFieldA,numOfSnowBallsInAField,minutesPassedAfterAShot);
            int BShotToFieldA=b.shootToOpponentField(ALastShotToFieldB,numOfSnowBallsInBField,minutesPassedAfterBShot);
            numOfSnowBallsInAField -= AShotToFieldB;
            numOfSnowBallsInBField -= BShotToFieldA;
            int AShotToHotField = a.shootToHotField(BLastShotToFieldA,numOfSnowBallsInAField,minutesPassedAfterAShot);
            int BShotToHotField = b.shootToHotField(ALastShotToFieldB,numOfSnowBallsInBField,minutesPassedAfterBShot);
            ALastShotToFieldB = AShotToFieldB;
            BLastShotToFieldA = BShotToFieldA;
            numOfSnowBallsInAField -= AShotToHotField;
            numOfSnowBallsInBField -= BShotToHotField;
            if(AShotToFieldB>=1||AShotToHotField>=1)
                minutesPassedAfterAShot=1;
            else
                minutesPassedAfterAShot++;
            if(BShotToFieldA>=1||BShotToHotField>=1)
                minutesPassedAfterBShot=1;
            else
                minutesPassedAfterBShot++;
            // you can run the next line to see the tournament rounds
            //System.out.println("Round "+i+", Number of Snowball in Field A: "+numOfSnowBallsInAField + " . Number Of Snowball in Field B: " + numOfSnowBallsInBField);
            numOfSnowBallsInAField += BShotToFieldA + 1;
            numOfSnowBallsInBField += AShotToFieldB + 1;
        }
        //System.out.println("Final Result After Finishing the Tournament: "+numOfSnowBallsInAField + " : " + numOfSnowBallsInBField);
        int[] ans = new int[2];
        ans[0]=numOfSnowBallsInAField;
        ans[1]=numOfSnowBallsInBField;
        return ans;
    }
    public static int[] multibletests(Player a, Player b){
        /*
        This Function is for testing the agents on multible games
        because the random startegies usually give deferent results.
        So it will be better to test and get average payoff
         */
        int sum_fieldA=0, sum_fieldB=0;
        for(int i=0;i<100000;i++){
            int[] answer = tournament(a,b);
            sum_fieldA += answer[0];
            sum_fieldB += answer[1];
        }
        sum_fieldA /= 100000;
        sum_fieldB /= 100000;
        int[] ans = new int[2];
        ans[0]=sum_fieldA;
        ans[1]=sum_fieldB;
        return ans;
    }
    public static void main(String[] args) {
        /*
        Inside main function we creat the objects from every class
        and print the result that return from multibletests for two deferent agents
         */
        greedyMaxAgent greedyAgent = new greedyMaxAgent();
        wise_Random_Agent wiseRandom = new wise_Random_Agent();
        completlyRandomAgent fullyRandom = new completlyRandomAgent();
        agent_Shot_to_Only_Hot_Field toOnlyHot = new agent_Shot_to_Only_Hot_Field();
        agent_Shot_to_Only_opponent_field toOnlyOpponent = new agent_Shot_to_Only_opponent_field();
        System.out.println("greedy Agent vs wise Random");
        int[] answer = multibletests(greedyAgent,wiseRandom);
        System.out.println("The avarage payoff Of first Agent "+answer[0] + " : The avarage payoff Of second Agent " + answer[1]);

        System.out.println("\ngreedy Agent vs fully Random Agent");
        answer = multibletests(greedyAgent,fullyRandom);
        System.out.println("The avarage payoff Of first Agent "+answer[0] + " : The avarage payoff Of second Agent " + answer[1]);

        System.out.println("\ngreedyAgent vs agent Shot to Only Hot Field");
        answer = multibletests(greedyAgent,toOnlyHot);
        System.out.println("The avarage payoff Of first Agent "+answer[0] + " : The avarage payoff Of second Agent " + answer[1]);

        System.out.println("\ngreedyAgent vs agent Shot to Only Opponent Field");
        answer = multibletests(greedyAgent,toOnlyOpponent);
        System.out.println("The avarage payoff Of first Agent "+answer[0] + " : The avarage payoff Of second Agent " + answer[1]);
    }
}