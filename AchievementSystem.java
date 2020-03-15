import java.util.Date;

//CURRENTLY UNUSED

public class AchievementSystem {

    private static int time;
    private static boolean timeTrophy;

    private static Date dateStart;

    public static void resetAchievements() {
        /*DataManager.store("false", "coin_trophy_1", "achievement.data");    //100 coins X
        DataManager.store("false", "coin_trophy_2", "achievement.data");    //1000 coins X
        DataManager.store("false", "coin_trophy_3", "achievement.data");    //10000 coins X
        DataManager.store("false", "game_trophy", "achievement.data");      //play every game
        DataManager.store("false", "time_trophy", "achievement.data");      //play the game for 1h
        DataManager.store("false", "egg_trophy", "achievement.data");       //<Easteregg> X
        DataManager.store("false", "cheater_trophy", "achievement.data");   //use cheat codes X

        DataManager.store("false", "snake_trophy", "achievement.data");     //score 100 points in snake
        DataManager.store("false", "space_trophy", "achievement.data");     //clear all enemies in space invader
        DataManager.store("false", "lucky_trophy", "achievement.data");     //get one of the best 3 multipliers in the slotmachine
        DataManager.store("false", "asteroids_trophy", "achievement.data"); //score 1000000 points in Asteroids
        DataManager.store("false", "flappy_trophy", "achievement.data");    //score 20 points flappy bird
        DataManager.store("false", "pong_trophy", "achievement.data");      //win pong without letting the AI score
        DataManager.store("false", "tetris_trophy", "achievement.data");    //clear 3 or more rows at the same time in tetris

        DataManager.store("0", "PLAY_TIME", "achievement.data");            //reset the play time
        DataManager.store("0,0,0,0,0,0,0", "GAMES_PLAYED", "achievement.data");  //reset the game trophy stats   "Asteroid,flappy,pong,slot,snake,space,tetris"
*/
    }


    public static void startTimeCounter() {
        /*time = Integer.parseInt(DataManager.getData("PLAY_TIME", "achievement.data"));
        timeTrophy = Boolean.parseBoolean(DataManager.getData("time_trophy", "achievement.data"));
        dateStart = new Date();*/
    }

    public static int evalGameTime() {
        /*Date dateEnd = new Date();
        long timeDiff = dateEnd.getTime() - dateStart.getTime();
        int timeSecDiff = (int)(timeDiff / 1000);

        dateStart = new Date();

        return timeSecDiff;*/
        return 0;
    }

    public static void timeDifference(int secondsToAdd) {
        /*time += secondsToAdd;

        if(time == 3600) {
            DataManager.store("true", "time_trophy", "achievement.data");
            timeTrophy = true;
        }
        DataManager.store(time + "", "PLAY_TIME", "achievement.data");
        dateStart = new Date();*/
    }

    //"Asteroid,flappy,pong,slot,snake,space,tetris"
    public static void addGameCount(int gameIndex) {
        /*String[] games = DataManager.getData("GAMES_PLAYED", "achievement.data").split(",");
        games[gameIndex] = Integer.parseInt(games[gameIndex]) + 1 + "";

        if(checkForAllTrue(games))
            DataManager.store("true", "game_trophy", "achievement.data");

        String tempStr = games[0] + "," + games[1] + "," + games[2] + "," + games[3] + "," + games[4] + "," + games[5] + "," + games[6];
        DataManager.store(tempStr, "GAMES_PLAYED", "achievement.data");*/
    }

    public static boolean checkForAllTrue(String[] arr) {
        /*for(int i = 0; i < arr.length; i++) {
            if(Integer.parseInt(arr[i]) == 0)
                return false;
        }
        return true;*/
        return false;
    }

    public static void addAchievement(String id) {
        //DataManager.store("true", id, "achievement.data");
    }

    public static boolean getTimeTrophyState() {
        //return timeTrophy;
        return true;
    }
}
