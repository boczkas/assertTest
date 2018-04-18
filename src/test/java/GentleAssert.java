public class GentleAssert {

    static Report report;

    public GentleAssert() {
        report = new Report();
    }

    public void assertTrue(boolean condition, String failMessage){
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        if(methodName.equals("assertFalse")){
            methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        }

        try {
            assert condition : "";
            report.addPass(methodName);
        } catch (AssertionError e){
            report.addFail(methodName, failMessage);
        }
    }

    public void assertFalse(boolean condition, String failMessage){
        assertTrue(!condition, failMessage);
    }

    public void showReport(){
        report.showReport();
    }
}
