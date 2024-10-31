package params;

public class GlobalParams {
    private static ThreadLocal<String> browserName = new ThreadLocal<String>();

    public void setBrowserName(String browser){
        browserName.set(browser);
    }

    public String getBrowserName(){
        return browserName.get();
    }

    public void initializeGlobalParams(){
        GlobalParams params = new GlobalParams();
        params.setBrowserName("chrome");
    }
}
