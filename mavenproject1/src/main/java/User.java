
import java.util.List;

/**
 *
 * @author jorgeAleman
 */
public class User {
    private String phoneNumber;
    private List<String> stationIds;
    private String telegramToken;
    
    public User(){};
    
    public User(String phoneNumber, List<String> stationIds, String telegramToken) {
        this.phoneNumber = phoneNumber;
        this.stationIds = stationIds;
        this.telegramToken = telegramToken;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getStationIds() {
        return stationIds;
    }

    public void setStationIds(List<String> stationIds) {
        this.stationIds = stationIds;
    }

    public String getTelegramToken() {
        return telegramToken;
    }

    public void setTelegramToken(String telegramToken) {
        this.telegramToken = telegramToken;
    }

    @Override
    public String toString() {
        String SstationIds="[ ";
        for(int i = 0 ; i < stationIds.size() ; ++i){
            SstationIds += stationIds.get(i);
            if(i != stationIds.size()-1)
                SstationIds += " , ";
        }
        SstationIds += " ]";
        return "Subscription{" + "phoneNumber=" + phoneNumber + ", stationIds=" + SstationIds + ", telegramToken=" + telegramToken + '}';
    }
}
