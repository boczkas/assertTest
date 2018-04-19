import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Report {

    Map<String, Result> methodsResults;

    public Report() {
        methodsResults = new HashMap<>();
    }

    public void addPass(String methodName){
        Result result = getMethodResults(methodName);
        result.addPass();
        methodsResults.put(methodName, result);
    }

    public void addFail(String methodName, String failMessage){
        Result result = getMethodResults(methodName);
        result.addFail(failMessage);
        methodsResults.put(methodName, result);
    }

    public void showReport(){
        Result result;
        StringBuilder reportString = new StringBuilder();
        int totalPasses = 0;
        int totalFails = 0;
        int methodPasses;
        int methodFails;

        reportString.append("\n\n\n");
        reportString.append("====================================================================\n");

        for(Map.Entry<String, Result> entry : methodsResults.entrySet()){
            result = entry.getValue();
            methodPasses = result.getMethodPasses();
            methodFails = result.getMethodFails();

            reportString.append("||\t" + Color.ANSI_BLUE + entry.getKey() + Color.ANSI_GREEN + " pass: " + methodPasses +
                    Color.ANSI_RED + " fail: "+ methodFails);

            if(methodFails != 0){
                reportString.append(" - with message: " + result.getMethodFailMessage());
            }

            reportString.append("\n");
            totalPasses += methodPasses;
            totalFails += methodFails;
        }

        reportString.append("====================================================================\n");
        reportString.append(Color.ANSI_BLUE + "\nTotal:\n" +
                            Color.ANSI_GREEN + " passes: " + totalPasses + Color.ANSI_RED + " fails: " + totalFails);

        reportString.append("\n\n\n");

        Logger logger = Logger.getLogger(getClass().getName());
        logger.log(Level.INFO, reportString.toString());
    }

    private Result getMethodResults(String methodName) {
        Result result;
        if(methodsResults.containsKey(methodName)){
            result = methodsResults.get(methodName);
        }
        else{
            result = new Result();
        }
        return result;
    }


}
