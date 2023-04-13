package gametheory.snowball.students2022;
public class MahmoudMousatatCode implements Player {
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
