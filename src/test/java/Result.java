public class Result {
    private int pass;
    private int fail;
    private String failMessage;

    public Result() {
        this.pass = 0;
        this.fail = 0;
        this.failMessage = "";
    }

    public void addPass(){
        this.pass++;
    }

    public void addFail(String failMessage){
        if(this.failMessage.equals("")){
            this.failMessage = failMessage;
        }
        this.fail++;
    }

    public int getMethodPasses(){
        return pass;
    }

    public int getMethodFails(){
        return fail;
    }

    public String getMethodFailMessage(){
        return failMessage;
    }
}
