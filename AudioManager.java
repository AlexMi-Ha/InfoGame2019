import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
public class AudioManager  
{
    private static List<GreenfootSound> currentlyPlaying = new ArrayList();
    //Liste mit allen gerade spielenden Liedern

    public static void addPlaying(String path) { //ein Lied zur Liste hinzufügen um es dann abzuspielen
        try {
            currentlyPlaying.add(new GreenfootSound(path));

            currentlyPlaying.get(currentlyPlaying.size()-1).playLoop();
            currentlyPlaying.get(currentlyPlaying.size()-1).setVolume(60);
        } catch(Exception e) {}
    }

    public static void stopPlaying(String path) { //ein Lied stoppen und aus der Liste entfernen
        try {
            for(int i = currentlyPlaying.size()-1;i >= 0;i--){
                String p = currentlyPlaying.get(i).toString();
                String[] pa = p.split(":");
                p = pa[1];
                p = p.replaceFirst(" . Is playing", "");
                p = p.substring(1);
                if (p.equals(path)){ 
                    currentlyPlaying.get(i).stop();
                    currentlyPlaying.remove(i);
                    continue;
                }       
            } 
        }catch(Exception e) {}
    }
    
    public static void setVolume(String path, int volume) {
        try {
            for(int i = currentlyPlaying.size()-1;i >= 0;i--){
                String p = currentlyPlaying.get(i).toString();
                String[] pa = p.split(":");
                p = pa[1];
                p = p.replaceFirst(" . Is playing", "");
                p = p.substring(1);
                if (p.equals(path)){ 
                    currentlyPlaying.get(i).setVolume(volume);
                    continue;
                }       
            } 
        }catch(Exception e) {}
    }

    public static boolean isPlaying(String path) {  //gibt zurück ob das Lied gerade spielt
        try{
            for(int i = currentlyPlaying.size()-1;i >= 0;i--){
                String p = currentlyPlaying.get(i).toString();
                String[] pa = p.split(":");
                p = pa[1];
                p = p.replaceFirst(" . Is playing", "");
                p = p.substring(1);
                if (p.equals(path))
                    return true;     
            } 
            return false;  
        }catch(Exception e) {}
        return false;
    }
}
